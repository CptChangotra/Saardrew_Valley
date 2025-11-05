package de.unisaarland.cs.se.selab.parser

import de.unisaarland.cs.se.selab.cloud.Cloud
import de.unisaarland.cs.se.selab.cloud.CloudController
import de.unisaarland.cs.se.selab.farms.Farm
import de.unisaarland.cs.se.selab.farms.FarmController
import de.unisaarland.cs.se.selab.incidents.AnimalAttack
import de.unisaarland.cs.se.selab.incidents.BeeHappy
import de.unisaarland.cs.se.selab.incidents.BrokenMachine
import de.unisaarland.cs.se.selab.incidents.CityExpand
import de.unisaarland.cs.se.selab.incidents.CloudCreation
import de.unisaarland.cs.se.selab.incidents.Drought
import de.unisaarland.cs.se.selab.incidents.Incident
import de.unisaarland.cs.se.selab.map.MapController
import de.unisaarland.cs.se.selab.map.SimulationMap
import de.unisaarland.cs.se.selab.map.Tile
import de.unisaarland.cs.se.selab.map.TileType
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonClassDiscriminator

/**
 * Scenario Parser
 */
class ScenarioParser(val config: String) {
    private var incidents: List<Incident>? = null
    private var clouds: List<Cloud>? = null

    private var scenario: JSONScenario? = null

    private val cloudsException: Result<List<Cloud>> = Result.failure(Parser.ParsingBuildError("could not parse JSON"))
    private val incidentException: Result<List<Incident>> = Result.failure(
        Parser.ParsingBuildError("could not parse JSON")
    )

    /**
     * Parses scenario JSON config & creates clouds
     */
    fun createClouds(map: SimulationMap): Result<List<Cloud>> {
        try {
            val strictJson = Json {
                ignoreUnknownKeys = false
                isLenient = false
            }
            val s = scenario ?: strictJson.decodeFromString<JSONScenario>(config)
            scenario = s

            clouds = s.clouds.map { it.toCloud() }

            if (ENABLE_VALIDATION && !validateClouds(map)) return cloudsException

            return Result.success(clouds ?: error(UNREACHABLE))
        } catch (e: SerializationException) {
            eprintln(e.message)
        } catch (e: IllegalArgumentException) {
            eprintln(e.message)
        } catch (_: Exception) {
            eprintln("unknown exception in createClouds()")
        }

        return cloudsException
    }

    /**
     * Parses scenario JSON config & creates incidents
     */
    fun createIncidents(
        mapC: MapController,
        farmC: List<FarmController>,
        cloudC: CloudController
    ): Result<List<Incident>> {
        try {
            val strictJson = Json {
                ignoreUnknownKeys = false
                isLenient = false
            }
            val s = scenario ?: strictJson.decodeFromString<JSONScenario>(config)
            scenario = s

            incidents = s.incidents.map { it.toIncident(mapC, cloudC, farmC) }

            val farms = farmC.map { it.farm }
            if (ENABLE_VALIDATION && !validateIncidents(mapC, farms)) return incidentException

            return Result.success(incidents ?: error(UNREACHABLE))
        } catch (e: SerializationException) {
            eprintln(e.message)
        } catch (e: IllegalArgumentException) {
            eprintln(e.message)
        } catch (_: Exception) {
            eprintln("unknown exception in incidentsClouds()")
        }

        return incidentException
    }

    private fun validateClouds(map: SimulationMap): Boolean {
        val clouds = clouds ?: error("should create before validating")
        val idToTile = map.idToTile
        val cloudIds = clouds.map { it.id }
        val locations = clouds.map { it.location }

        val validCloudIds = cloudIds.all { it >= 0 }
        val noRepeatingCloudIds = cloudIds.size == cloudIds.toSet().size
        var valid = validCloudIds && noRepeatingCloudIds
        eprintln("issue with cloud ids", valid)

        val validLocations = locations.all { it in idToTile }
        val noRepeatingCloudLocations = locations.size == locations.toSet().size
        valid = valid && validLocations && noRepeatingCloudLocations
        eprintln("issue with cloud locations", valid)

        val validDurations = clouds.all { it.duration == -1 || it.duration > 0 }
        val validAmounts = clouds.all { it.amount > 0 }
        valid = valid && validDurations && validAmounts
        eprintln("issue with cloud duration/amount", valid)

        return valid
    }

    private fun validateIncidents(mapC: MapController, farms: List<Farm>): Boolean {
        val map = mapC.simulationMap
        val incidents = incidents ?: error("should create before validating")
        val idToTile = map.idToTile
        val incidentIds = incidents.map { it.id }
        val machineIds = farms.flatMap { it.machines }.map { it.id }

        val cloudCreation = incidents.mapNotNull { it as? CloudCreation }
        val brokenMachine = incidents.mapNotNull { it as? BrokenMachine }
        val beeHappy = incidents.mapNotNull { it as? BeeHappy }

        val validIncidentsIds = incidentIds.all { it >= 0 }
        val noRepeatingIncidentIds = incidentIds.size == incidentIds.toSet().size
        val validTicks = incidents.all { it.tick >= 0 }
        var valid = validIncidentsIds && noRepeatingIncidentIds && validTicks
        eprintln("issue with incident ids/ticks", valid)

        val correctDurations = cloudCreation.all { it.duration == -1 || it.duration > 0 } &&
            brokenMachine.all { it.duration == -1 || it.duration > 0 }
        valid = valid && correctDurations
        eprintln("issue with incident durations", valid)

        valid = valid && validateLocationsAndRadius(incidents, idToTile)
        eprintln("issue with incident locations/radii", valid)

        val validAmount = cloudCreation.all { it.amount > 0 }
        val validEffect = beeHappy.all { it.effect > 0 }
        val validMachineIds = brokenMachine.all { it.machineId in machineIds }
        valid = valid && validAmount && validEffect && validMachineIds
        eprintln("issue with incident amount/effect/machineId", valid)

        valid = valid && checkSimulatedIncidents(mapC, farms, incidents)
        eprintln("issue with incident simulation", valid)

        return valid
    }

    private fun validateLocationsAndRadius(incidents: List<Incident>, idToTile: Map<Int, Tile>): Boolean {
        val cloudCreation = incidents.mapNotNull { it as? CloudCreation }
        val animalAttack = incidents.mapNotNull { it as? AnimalAttack }
        val beeHappy = incidents.mapNotNull { it as? BeeHappy }
        val drought = incidents.mapNotNull { it as? Drought }
        val cityExpand = incidents.mapNotNull { it as? CityExpand }

        val validLocationIds = cloudCreation.all { it.location in idToTile } &&
            animalAttack.all { it.location in idToTile } &&
            beeHappy.all { it.location in idToTile } &&
            drought.all { it.location in idToTile } &&
            cityExpand.all { it.location in idToTile }
        val validRadius = cloudCreation.all { it.radius >= 0 } &&
            animalAttack.all { it.radius >= 0 } &&
            beeHappy.all { it.radius >= 0 } &&
            drought.all { it.radius >= 0 }
        return validLocationIds && validRadius
    }

    /**
     * Simulates the incidents happening one by one, and checks that whenever they are executed, their respective
     * conditions are met. Also checks sowing plans are valid.
     */
    private fun checkSimulatedIncidents(
        mapC: MapController,
        farms: List<Farm>,
        incidents: List<Incident>
    ): Boolean {
        if (incidents.isEmpty()) return true

        val idToTile = mapC.simulationMap.idToTile
        val idToTileType = idToTile.mapValues { it.value.category }.toMutableMap()

        val sortedIncidents = incidents.sortedWith(compareBy<Incident> { it.tick }.thenBy { it.id })

        var tick = sortedIncidents.first().tick
        val cloudTilesInTick = emptySet<Int>().toMutableSet()

        for (incident in sortedIncidents) {
            if (tick != incident.tick) {
                tick = incident.tick
                cloudTilesInTick.clear()
            }

            val validSowingPlans = checkSowingPlansHaveFields(mapC, idToTileType, farms)
            val validIncident = checkIncident(mapC, idToTileType, incident)
            var valid = validSowingPlans && validIncident

            // QUESTION: can a machine be re-broken?

            valid = valid && updateCloudAffectedTiles(incident, cloudTilesInTick, mapC, idToTileType)

            if (valid && incident is CityExpand) {
                idToTileType[incident.location] = TileType.VILLAGE
                cloudTilesInTick.remove(incident.location)

                valid = valid && checkSowingPlansHaveFields(mapC, idToTileType, farms)
            }

            if (!valid) return false
        }

        return true
    }

    /**
     * Checks if the incident is valid based on the current tile types in [idToTileType].
     */
    private fun checkIncident(
        mapC: MapController,
        idToTileType: Map<Int, TileType>,
        incd: Incident
    ): Boolean = when (incd) {
        is CloudCreation -> vicinityHasAny(mapC, idToTileType, incd.location, incd.radius) { it != TileType.VILLAGE }
        is AnimalAttack -> vicinityHasAny(mapC, idToTileType, incd.location, incd.radius) { it == TileType.FOREST }
        is BeeHappy -> vicinityHasAny(mapC, idToTileType, incd.location, incd.radius) { it == TileType.MEADOW }
        is Drought ->
            vicinityHasAny(mapC, idToTileType, incd.location, incd.radius) {
                it == TileType.FIELD || it == TileType.PLANTATION
            }
        is CityExpand ->
            vicinityHasAny(mapC, idToTileType, incd.location, 1) { it == TileType.VILLAGE } &&
                !vicinityHasAny(mapC, idToTileType, incd.location, 1) { it == TileType.FOREST } &&
                idToTileType[incd.location] in listOf(TileType.FIELD, TileType.ROAD)
        is BrokenMachine -> true
    }

    /**
     * Checks whether all sowing plans have at least on Field tile that belongs to the same farm as the plan
     * @param idToTileType stores the current tile type information for each tile id
     * @param mapC all other information about tiles, apart from tile type (which is in idToTileType), is stored here
     */
    private fun checkSowingPlansHaveFields(
        mapC: MapController,
        idToTileType: Map<Int, TileType>,
        farms: List<Farm>,
    ): Boolean =
        farms
            .flatMap { f -> f.sowingPlans.map { s -> f.id to s } }
            .all { (farmId, plan) ->
                val tiles = plan.location?.let { location ->
                    mapC.getNeighboursGeometries(location, plan.radius ?: error("location+radius should exist"))
                        .map { it.id }
                } ?: plan.tileIDs

                tiles
                    ?.any { tileId ->
                        val isField = idToTileType[tileId] == TileType.FIELD
                        val isFarm = mapC.simulationMap.idToTile[tileId]?.farm == farmId
                        val hasPlant = mapC.getTile(tileId).possiblePlants?.contains(plan.plant) ?: false
                        isField && isFarm && hasPlant
                    }
                    ?: false
            }

    /**
     * Checks if at least one tile in [radius] distance of [location] respects the [predicate], based on [idToTileType]
     */
    private fun vicinityHasAny(
        mapC: MapController,
        idToTileType: Map<Int, TileType>,
        location: Int,
        radius: Int,
        predicate: (TileType) -> Boolean
    ): Boolean =
        mapC.getNeighboursGeometries(location, radius)
            .mapNotNull { idToTileType[it.id] }
            .any(predicate)

    private fun updateCloudAffectedTiles(
        incident: Incident,
        affectedTiles: MutableSet<Int>,
        mapC: MapController,
        idToTileType: Map<Int, TileType>
    ): Boolean {
        if (incident !is CloudCreation) return true

        val incidentTiles = mapC
            .getNeighboursGeometries(incident.location, incident.radius)
            .filter { idToTileType[it.id] != TileType.VILLAGE }
            .map { it.id }
        val doesNotContainTiles = incidentTiles.all { !affectedTiles.contains(it) }
        affectedTiles += incidentTiles

        return doesNotContainTiles
    }

    @Serializable
    private data class JSONScenario(
        val clouds: List<JSONCloud>,
        val incidents: List<JSONIncident>
    )

    @Serializable
    private data class JSONCloud(
        val id: Int,
        val location: Int,
        val duration: Int,
        val amount: Int
    ) {
        fun toCloud(): Cloud = Cloud(id, duration, location, amount, Cloud.MAX_MOVES)
    }

    @OptIn(ExperimentalSerializationApi::class)
    @Serializable
    @JsonClassDiscriminator("type")
    private sealed class JSONIncident {
        abstract val id: Int
        abstract val tick: Int
        abstract fun toIncident(mapC: MapController, cloudC: CloudController, farmC: List<FarmController>): Incident

        @Serializable
        @SerialName("CLOUD_CREATION")
        data class JSONCloudCreation(
            override val id: Int,
            override val tick: Int,
            val location: Int,
            val radius: Int,
            val duration: Int,
            val amount: Int
        ) : JSONIncident() {
            override fun toIncident(
                mapC: MapController,
                cloudC: CloudController,
                farmC: List<FarmController>
            ): Incident = CloudCreation(id, tick, mapC, location, radius, duration, amount, cloudC)
        }

        @Serializable
        @SerialName("ANIMAL_ATTACK")
        data class JSONAnimalAttack(
            override val id: Int,
            override val tick: Int,
            val location: Int,
            val radius: Int
        ) : JSONIncident() {
            override fun toIncident(
                mapC: MapController,
                cloudC: CloudController,
                farmC: List<FarmController>
            ): Incident = AnimalAttack(id, tick, mapC, location, radius, farmC)
        }

        @Serializable
        @SerialName("BEE_HAPPY")
        data class JSONBeeHappy(
            override val id: Int,
            override val tick: Int,
            val location: Int,
            val radius: Int,
            val effect: Int
        ) : JSONIncident() {
            override fun toIncident(
                mapC: MapController,
                cloudC: CloudController,
                farmC: List<FarmController>
            ): Incident = BeeHappy(id, tick, mapC, location, radius, effect, false)
            // TODO isFirst is there bcs if beeHappy takes multiple ticks, only the first one should log, therefore
            // isFirst is true for the first beeHappy incident and false for all following ones
        }

        @Serializable
        @SerialName("DROUGHT")
        data class JSONDrought(
            override val id: Int,
            override val tick: Int,
            val location: Int,
            val radius: Int
        ) : JSONIncident() {
            override fun toIncident(
                mapC: MapController,
                cloudC: CloudController,
                farmC: List<FarmController>
            ): Incident = Drought(id, tick, mapC, location, radius, farmC)
        }

        @Serializable
        @SerialName("BROKEN_MACHINE")
        data class JSONBrokenMachine(
            override val id: Int,
            override val tick: Int,
            val duration: Int,
            val machineId: Int
        ) : JSONIncident() {
            override fun toIncident(
                mapC: MapController,
                cloudC: CloudController,
                farmC: List<FarmController>
            ): Incident = BrokenMachine(id, tick, mapC, machineId, duration, farmC.flatMap { it.farm.machines })
        }

        @Serializable
        @SerialName("CITY_EXPANSION")
        data class JSONCityExpansion(
            override val id: Int,
            override val tick: Int,
            val location: Int
        ) : JSONIncident() {
            override fun toIncident(
                mapC: MapController,
                cloudC: CloudController,
                farmC: List<FarmController>
            ): Incident = CityExpand(id, tick, mapC, location, cloudC, farmC)
        }
    }

    private var hasSeenInvalid = false

    /**
     * Prints [message] to stderr, only if [hasSeenInvalid] is `false`.
     */
    private fun eprintln(message: Any?) {
        if (DEBUG_SCENARIO_PARSER && !hasSeenInvalid) {
            System.err.println("[SCENARIO PARSER] ${message?.toString().orEmpty()}")
        }
    }

    /**
     * Prints [message] to stderr, only if [valid] is `true` and [hasSeenInvalid] is `false`.
     */
    private fun eprintln(message: Any?, valid: Boolean) {
        if (DEBUG_SCENARIO_PARSER && !valid && !hasSeenInvalid) {
            System.err.println("[SCENARIO PARSER] ${message?.toString().orEmpty()}")
        }
        hasSeenInvalid = hasSeenInvalid || !valid
    }
}
