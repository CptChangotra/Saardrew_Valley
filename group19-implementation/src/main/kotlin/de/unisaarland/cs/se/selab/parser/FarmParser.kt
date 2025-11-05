package de.unisaarland.cs.se.selab.parser
import de.unisaarland.cs.se.selab.farms.Farm
import de.unisaarland.cs.se.selab.farms.Machine
import de.unisaarland.cs.se.selab.farms.SowingPlan
import de.unisaarland.cs.se.selab.map.MapController
import de.unisaarland.cs.se.selab.map.Tile
import de.unisaarland.cs.se.selab.map.TileType
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json

const val MIN_MACHINE_DURATION = 1
const val MAX_MACHINE_DURATION = 14
const val INPUT_ERROR = "non-existent tileId"

/**
 * Farm Parser
 */
class FarmParser(val config: String) {
    private val farmsException: Result<List<Farm>> = Result.failure(Parser.ParsingBuildError("could not parse JSON"))

    /**
     * Parses farm JSON config & creates farms
     */
    fun createFarms(mapC: MapController): Result<List<Farm>> {
        try {
            val strictJson = Json {
                ignoreUnknownKeys = false
                isLenient = false
            }
            val jsonWrapper = strictJson.decodeFromString<JSONFarmWrapper>(config)

            if (jsonWrapper.farms.isEmpty()) return farmsException

            val farms = jsonWrapper.farms.map { it.toFarm(mapC) }

            if (ENABLE_VALIDATION && !validateFarms(mapC, farms)) return farmsException

            return Result.success(farms)
        } catch (e: SerializationException) {
            eprintln(e.message)
        } catch (e: IllegalArgumentException) {
            eprintln(e.message)
        } catch (_: Exception) {
            eprintln("unknown exception in createFarms()")
        }

        return farmsException
    }

    private fun validateFarms(mapC: MapController, farms: List<Farm>): Boolean {
        val map = mapC.simulationMap
        val idToTile = map.idToTile
        val tiles = map.idToTile.values

        val farmIds = farms.map { it.id }
        val farmNames = farms.map { it.name }

        val validFarmIds = farmIds.all { it >= 0 }
        val noRepeatingFarmIds = farmIds.size == farmIds.toSet().size
        val noRepeatingFarmNames = farmNames.size == farmNames.toSet().size
        var valid = validFarmIds && noRepeatingFarmIds && noRepeatingFarmNames
        eprintln("issue with farm ids/names", valid)

        valid = valid && checkFarmTilesAreOfCorrectType(farms, idToTile) && checkTilesHaveCorrectFarmIds(tiles, farmIds)
        eprintln("issue with farm tiles", valid)

        val validNeighbourTilesOfFarm = tiles
            .filter { it.category == TileType.FARMSTEAD }
            .all { farmTile ->
                farmTile.neighbours.values
                    .map { idToTile[it] ?: error("unexpected null neighbour") }
                    .filter { it.category in listOf(TileType.PLANTATION, TileType.FIELD) }
                    .mapNotNull { it.farm }
                    .all { it == farmTile.farm }
            }
        valid = valid && validNeighbourTilesOfFarm
        eprintln("issue with farm tiles neighbours", valid)

        val atLeastOneFieldOrPlantation = farms.all { it.fields.isNotEmpty() || it.plantations.isNotEmpty() }
        val atLeastOneMachine = farms.all { it.machines.isNotEmpty() }
        val atLeastOneFarmstead = farms.all { it.farmsteads.isNotEmpty() }
        valid = valid && atLeastOneFieldOrPlantation && atLeastOneMachine && atLeastOneFarmstead
        eprintln("issue with tiles a farm owns", valid)

        val machines = farms.flatMap { it.machines }
        valid = valid && checkMachines(machines, idToTile)
        eprintln("issues with farm machine", valid)

        val sowingPlans = farms.flatMap { it.sowingPlans }
        valid = valid && checkSowingPlans(sowingPlans, idToTile)
        eprintln("issues with farm sowing plans", valid)

        val machinesSowIfSowingPlan = farms.all { farm ->
            farm.sowingPlans.isEmpty() || farm.machines.any { it.actions.contains("SOWING") }
        }
        valid = valid && machinesSowIfSowingPlan
        eprintln("issues with farm sowing plans", valid)

        valid = valid && checkSowingPlansHaveFields(mapC, idToTile.mapValues { it.value.category }, farms)
        eprintln("issues with farm sowing plans fields", valid)

        return valid
    }

    private fun checkTilesHaveCorrectFarmIds(tiles: Collection<Tile>, farmIds: List<Int>): Boolean {
        val validFarmsteadFarmIds = tiles
            .filter { it.category == TileType.FARMSTEAD }
            .map { it.farm ?: error("farmstead must have farm id") }
            .all { it in farmIds }
        val validFieldFarmIds = tiles
            .filter { it.category == TileType.FIELD }
            .map { it.farm ?: error("field must have farm id") }
            .all { it in farmIds }
        val validPlantationFarmIds = tiles
            .filter { it.category == TileType.PLANTATION }
            .map { it.farm ?: error("plantation must have farm id") }
            .all { it in farmIds }

        return validFarmsteadFarmIds && validFieldFarmIds && validPlantationFarmIds
    }

    /**
     * Checks that all fields/plantations/farmsteads ids in a farm point to a tile of the appropriate type.
     * Checks that the farm indicated in each field/plantation/farmstead tile actually owns the respective tile.
     */
    private fun checkFarmTilesAreOfCorrectType(farms: List<Farm>, idToTile: Map<Int, Tile>): Boolean {
        val validFarmsteadTiles = farms
            .flatMap { it.farmsteads }
            .map { idToTile[it]?.category ?: error(INPUT_ERROR) }
            .all { it == TileType.FARMSTEAD }
        val validFieldTiles = farms
            .flatMap { it.fields }
            .map { idToTile[it]?.category ?: error(INPUT_ERROR) }
            .all { it == TileType.FIELD }
        val validPlantationTiles = farms
            .flatMap { it.plantations }
            .map { idToTile[it]?.category ?: error(INPUT_ERROR) }
            .all { it == TileType.PLANTATION }

        val farmsteadsIncludedInFarms = idToTile.values
            .filter { it.category == TileType.FARMSTEAD }
            .all { tile ->
                val farm = farms.firstOrNull { it.id == tile.farm } ?: return@all false
                farm.farmsteads.contains(tile.id)
            }
        val fieldIncludedInFarms = idToTile.values
            .filter { it.category == TileType.FIELD }
            .all { tile ->
                val farm = farms.firstOrNull { it.id == tile.farm } ?: return@all false
                farm.fields.contains(tile.id)
            }
        val plantationsIncludedInFarms = idToTile.values
            .filter { it.category == TileType.PLANTATION }
            .all { tile ->
                val farm = farms.firstOrNull { it.id == tile.farm } ?: return@all false
                farm.plantations.contains(tile.id)
            }

        return validFarmsteadTiles && validFieldTiles &&
            validPlantationTiles && farmsteadsIncludedInFarms &&
            fieldIncludedInFarms && plantationsIncludedInFarms
    }

    private fun checkSowingPlans(sowingPlans: List<SowingPlan>, idToTile: Map<Int, Tile>): Boolean {
        val sowingPlansIds = sowingPlans.map { it.id }
        val validSowingPlansIds = sowingPlansIds.all { it >= 0 }
        val noRepeatingSowingPlansIds = sowingPlansIds.size == sowingPlansIds.toSet().size
        var valid = validSowingPlansIds && noRepeatingSowingPlansIds

        val validFieldSpecification = sowingPlans.all { plan ->
            val hasFields = plan.tileIDs != null && plan.tileIDs.isNotEmpty()
            val hasLocationAndRadius = plan.location != null && plan.radius != null

            hasFields xor hasLocationAndRadius
        }
        val validLocations = sowingPlans.mapNotNull { it.location }.all { it in idToTile }
        val validRadius = sowingPlans.mapNotNull { it.radius }.all { it >= 0 }
        valid = valid && validFieldSpecification && validLocations && validRadius

        return valid
    }

    private fun checkMachines(machines: List<Machine>, idToTile: Map<Int, Tile>): Boolean {
        val machineIds = machines.map { it.id }
        val machineNames = machines.map { it.name }
        val validMachineIds = machineIds.all { it >= 0 }
        val noRepeatingMachineIds = machineIds.size == machineIds.toSet().size
        val noRepeatingMachineNames = machineNames.size == machineNames.toSet().size
        var valid = validMachineIds && noRepeatingMachineIds && noRepeatingMachineNames

        val atLeastOneAction = machines.all { it.actions.isNotEmpty() }
        val atLeastOnePlant = machines.all { it.plants.isNotEmpty() }
        valid = valid && atLeastOneAction && atLeastOnePlant

        val validMachineDurations = machines
            .map { it.duration }
            .all { it in MIN_MACHINE_DURATION..MAX_MACHINE_DURATION }
        val validMachineLocation = machines
            .map { it to (idToTile[it.location] ?: error("field must have location id")) }
            .all { (m, t) ->
                t.category == TileType.FARMSTEAD && t.shed && t.farm == m.farmId
            }
        valid = valid && validMachineDurations && validMachineLocation

        return valid
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
                    mapC.getNeighbourTilesAndCentre(location, plan.radius ?: error("location+radius should exist"))
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

    @Serializable
    private enum class JSONMachineAction {
        SOWING, CUTTING, MOWING, WEEDING, IRRIGATING, HARVESTING;

        override fun toString(): String = when (this) {
            SOWING -> "SOWING"
            CUTTING -> "CUTTING"
            MOWING -> "MOWING"
            WEEDING -> "WEEDING"
            IRRIGATING -> "IRRIGATING"
            HARVESTING -> "HARVESTING"
        }
    }

    @Serializable
    private data class JSONMachine(
        val id: Int,
        val name: String,
        val actions: List<JSONMachineAction>,
        val plants: List<JSONPlant>,
        val duration: Int,
        val location: Int
    ) {
        fun toMachine(farmId: Int): Machine = Machine(
            id,
            name,
            actions.map { it.toString() },
            plants.map { it.toString() },
            duration,
            location,
            location,
            null,
            farmId,
            0
        )
    }

    @Serializable
    private data class JSONSowingPlan(
        val id: Int,
        val tick: Int,
        val plant: JSONFieldPlant,
        val fields: List<Int>? = null,
        val location: Int? = null,
        val radius: Int? = null
    ) {
        fun toSowingPlan(): SowingPlan =
            SowingPlan(id, tick, plant.toFieldPlant(), fields, location, radius)
    }

    @Serializable
    private data class JSONFarm(
        val id: Int,
        val name: String,
        val farmsteads: List<Int>,
        val fields: List<Int>,
        val plantations: List<Int>,
        val machines: List<JSONMachine>,
        val sowingPlans: List<JSONSowingPlan>
    ) {
        fun toFarm(mapC: MapController): Farm = Farm(
            id,
            name,
            farmsteads,
            farmsteads.filter { mapC.getTile(it).shed },
            fields,
            plantations,
            machines.map { it.toMachine(id) },
            emptyList(),
            sowingPlans.map { it.toSowingPlan() }
        )
    }

    @Serializable
    private data class JSONFarmWrapper(val farms: List<JSONFarm>)

    private var hasSeenInvalid = false

    /**
     * Prints [message] to stderr, only if [hasSeenInvalid] is `false`.
     */
    private fun eprintln(message: Any?) {
        if (DEBUG_FARM_PARSER && !hasSeenInvalid) {
            System.err.println("[FARM PARSER] ${message?.toString().orEmpty()}")
        }
    }

    /**
     * Prints [message] to stderr, only if [valid] is `true` and [hasSeenInvalid] is `false`.
     */
    private fun eprintln(message: Any?, valid: Boolean) {
        if (DEBUG_FARM_PARSER && !valid && !hasSeenInvalid) {
            System.err.println("[FARM PARSER] ${message?.toString().orEmpty()}")
        }
        hasSeenInvalid = hasSeenInvalid || !valid
    }
}
