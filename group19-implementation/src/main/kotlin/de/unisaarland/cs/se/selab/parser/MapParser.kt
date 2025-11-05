package de.unisaarland.cs.se.selab.parser
import de.unisaarland.cs.se.selab.map.Coordinate
import de.unisaarland.cs.se.selab.map.SimulationMap
import de.unisaarland.cs.se.selab.map.Tile
import de.unisaarland.cs.se.selab.map.TileType
import de.unisaarland.cs.se.selab.map.plus
import de.unisaarland.cs.se.selab.utils.Direction.EAST
import de.unisaarland.cs.se.selab.utils.Direction.NORTH
import de.unisaarland.cs.se.selab.utils.Direction.NORTHEAST
import de.unisaarland.cs.se.selab.utils.Direction.NORTHWEST
import de.unisaarland.cs.se.selab.utils.Direction.SOUTH
import de.unisaarland.cs.se.selab.utils.Direction.SOUTHEAST
import de.unisaarland.cs.se.selab.utils.Direction.SOUTHWEST
import de.unisaarland.cs.se.selab.utils.Direction.WEST
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonClassDiscriminator

private const val M2 = -2 // yes, this makes it _so_ much more readable

/**
 * Map Parser
 */
class MapParser(val config: String) {
    var simulationMap: SimulationMap? = null

    private val mapException: Result<SimulationMap> = Result.failure(Parser.ParsingBuildError("could not parse JSON"))

    /**
     * Parses map JSON config & creates map
     */
    fun createMap(): Result<SimulationMap> {
        try {
            val strictJson = Json {
                ignoreUnknownKeys = false
                isLenient = false
            }
            val jsonWrapper = strictJson.decodeFromString<JSONTileWrapper>(config)

            if (jsonWrapper.tiles.isEmpty()) return mapException

            var tiles = jsonWrapper.tiles.map { it.toTile().getOrElse { return mapException } }

            // set tile neighbours based on coordinates
            tiles = tiles.map { tile ->
                val neighIds = getNeighbourCoords(tile.coordinate).mapNotNull { (dir, coord) ->
                    val neigh = tiles.firstOrNull { it.coordinate == coord }
                    if (neigh != null) dir to neigh.id else null
                }.toMap()
                tile.copy(neighbours = neighIds)
            }

            // has to be checked now, since the next line removes duplicates
            val noDuplicateIds = tiles.groupBy { it.id }.values.all { it.size == 1 }

            simulationMap = SimulationMap(tiles.associateBy { it.id })

            if (ENABLE_VALIDATION && (!noDuplicateIds || !validateMap())) return mapException

            return Result.success(simulationMap ?: error("unreachable"))
        } catch (e: SerializationException) {
            eprintln(e.message)
        } catch (e: IllegalArgumentException) {
            eprintln(e.message)
        } catch (_: Exception) {
            eprintln("unknown exception in createMap()")
        }

        return mapException
    }

    private fun validateMap(): Boolean {
        val simulationMap = simulationMap ?: error("should create before validating")
        val tiles = simulationMap.idToTile.values
        val idToTile = simulationMap.idToTile

        val farmsteads = tiles.filter { it.category == TileType.FARMSTEAD }
        val meadows = tiles.filter { it.category == TileType.MEADOW }
        val fields = tiles.filter { it.category == TileType.FIELD }
        val plantations = tiles.filter { it.category == TileType.PLANTATION }
        val villages = tiles.filter { it.category == TileType.VILLAGE }

        val tileIds = tiles.map { it.id }
        val tileCoords = tiles.map { it.coordinate }

        val validTileIds = tileIds.all { it >= 0 }
        val validFarmIds = tiles.mapNotNull { it.farm }.all { it >= 0 }
        val noRepeatingTileIds = tileIds.size == tileIds.toSet().size
        var valid = validTileIds && noRepeatingTileIds && validFarmIds
        eprintln("issue with tile/farm ids", valid)

        val noRepeatingCoords = tileCoords.size == tileCoords.toSet().size
        val validCoordinatePairs = tiles.all { it.coordinate.x.mod(2) == it.coordinate.y.mod(2) }
        valid = valid && noRepeatingCoords && validCoordinatePairs
        eprintln("issue with tile coords", valid)

        valid = valid && validateFarmsteads(farmsteads, idToTile)
        eprintln("issue with farmstead tile", valid)

        val validFieldTileShape = fields.all { isOctagonalTile(it) }
        valid = valid && validFieldTileShape
        eprintln("issue with field tile", valid)

        val validMeadowTileShape = meadows.all { isSquareTile(it) }
        val validMeadowTileNeighbours = validateAllNeighbours(idToTile, meadows) {
            it.category !in listOf(TileType.FARMSTEAD, TileType.MEADOW)
        }
        valid = valid && validMeadowTileShape && validMeadowTileNeighbours
        eprintln("issue with meadow tile", valid)

        val validPlantationTileShape = plantations.all { isOctagonalTile(it) }
        valid = valid && validPlantationTileShape
        eprintln("issue with plantation tile", valid)

        val validVillageTileNeighbours = validateAllNeighbours(idToTile, villages) { it.category != TileType.FOREST }
        valid = valid && validVillageTileNeighbours
        eprintln("issue with neighbour tile", valid)

        val validDirectionForSquare = tiles
            .filter { isSquareTile(it) }
            .mapNotNull { it.direction }
            .all { it in listOf(NORTHWEST, SOUTHWEST, SOUTHEAST, NORTHEAST) }
        val validDirectionForOctagonal = tiles
            .filter { isOctagonalTile(it) }
            .mapNotNull { it.direction }
            .all { it in listOf(NORTH, NORTHWEST, WEST, SOUTHWEST, SOUTH, SOUTHEAST, EAST, NORTHEAST) }
        valid = valid && validDirectionForSquare && validDirectionForOctagonal
        eprintln("issue with tile direction", valid)

        val validCapacity = tiles.mapNotNull { it.maxCapacity }.all { it > 0 }
        valid = valid && validCapacity
        eprintln("issue with tile capacity", valid)

        return valid
    }

    private fun isSquareTile(t: Tile): Boolean = isSquareCoordinate(t.coordinate)
    private fun isOctagonalTile(t: Tile): Boolean = isOctagonalCoordinate(t.coordinate)
    private fun isSquareCoordinate(c: Coordinate): Boolean = c.x.mod(2) == 1 && c.y.mod(2) == 1
    private fun isOctagonalCoordinate(c: Coordinate): Boolean = c.x.mod(2) == 0 && c.y.mod(2) == 0

    private fun validateAllNeighbours(
        idToTile: Map<Int, Tile>,
        tiles: List<Tile>,
        predicate: (Tile) -> Boolean
    ): Boolean =
        tiles
            .flatMap { it.neighbours.values }
            .map { idToTile[it] ?: error("unexpected null neighbour") }
            .all(predicate)

    private fun validateFarmsteads(farmsteads: List<Tile>, idToTile: Map<Int, Tile>): Boolean {
        val validFarmTileShape = farmsteads.all { isSquareTile(it) }
        val validFarmTileNeighbours = validateAllNeighbours(idToTile, farmsteads) {
            it.category !in listOf(TileType.FARMSTEAD, TileType.MEADOW)
        }
        val validFarmTileFarmNeighbours =
            farmsteads.all { tile ->
                tile.neighbours.values
                    .map { idToTile[it] ?: error("unexpected null neighbour") }
                    .filter { it.category in listOf(TileType.PLANTATION, TileType.FIELD) }
                    .map { it.farm ?: return@all false }
                    .all { it == tile.farm }
            }
        return validFarmTileShape && validFarmTileNeighbours && validFarmTileFarmNeighbours
    }

    private fun getNeighbourCoords(c: Coordinate): Map<Int, Coordinate> {
        return if (isSquareCoordinate(c)) {
            listOf(NORTHWEST, SOUTHWEST, SOUTHEAST, NORTHEAST)
                .zip(listOf(-1 to -1, -1 to 1, 1 to 1, 1 to -1))
                .associate { (dir, delta) -> dir to c + delta }
        } else {
            listOf(NORTH, NORTHWEST, WEST, SOUTHWEST, SOUTH, SOUTHEAST, EAST, NORTHEAST)
                .zip(listOf(0 to M2, -1 to -1, M2 to 0, -1 to 1, 0 to 2, 1 to 1, 2 to 0, 1 to -1))
                .associate { (dir, delta) -> dir to c + delta }
        }
    }

    @Serializable
    private data class JSONTileWrapper(val tiles: List<JSONTile>)

    @OptIn(ExperimentalSerializationApi::class)
    @Serializable
    @JsonClassDiscriminator("category")
    private sealed class JSONTile {
        abstract val id: Int
        abstract val coordinates: Coordinate

        abstract fun toTile(): Result<Tile>

        @kotlinx.serialization.Transient protected val tileException: Result<Tile> =
            Result.failure(Parser.ParsingCLIError("could not parse JSON"))

        @Serializable
        @SerialName("FARMSTEAD")
        data class Farmstead(
            override val id: Int,
            override val coordinates: Coordinate,
            val farm: Int,
            val airflow: Boolean,
            val shed: Boolean,
            val direction: Direction? = null
        ) : JSONTile() {
            override fun toTile(): Result<Tile> {
                if (airflow xor (direction != null)) return tileException

                return Result.success(
                    Tile(
                        id,
                        coordinates,
                        TileType.FARMSTEAD,
                        farm,
                        airflow,
                        direction?.directionToInt(),
                        null,
                        null,
                        null,
                        0,
                        0,
                        shed,
                        null,
                        0,
                        0,
                        emptyMap(),
                        mutableListOf()
                    )
                )
            }
        }

        @Serializable
        @SerialName("FIELD")
        data class Field(
            override val id: Int,
            override val coordinates: Coordinate,
            val farm: Int,
            val airflow: Boolean,
            val capacity: Int,
            val possiblePlants: List<JSONFieldPlant>,
            val direction: Direction? = null
        ) : JSONTile() {
            override fun toTile(): Result<Tile> {
                if (airflow xor (direction != null)) return tileException
                if (capacity < 1) return tileException

                return Result.success(
                    Tile(
                        id,
                        coordinates,
                        TileType.FIELD,
                        farm,
                        airflow,
                        direction?.directionToInt(),
                        capacity,
                        null,
                        possiblePlants.map { it.toFieldPlant() },
                        0,
                        0,
                        false,
                        0,
                        capacity,
                        0,
                        emptyMap(),
                        mutableListOf()
                    )
                )
            }
        }

        @Serializable
        @SerialName("PLANTATION")
        data class Plantation(
            override val id: Int,
            override val coordinates: Coordinate,
            val farm: Int,
            val airflow: Boolean,
            val capacity: Int,
            val plant: JSONPlantationPlant,
            val direction: Direction? = null
        ) : JSONTile() {
            override fun toTile(): Result<Tile> {
                if (airflow xor (direction != null)) return tileException
                if (capacity < 1) return tileException
                val plant = plant.toPlantationPlant()

                return Result.success(
                    Tile(
                        id,
                        coordinates,
                        TileType.PLANTATION,
                        farm,
                        airflow,
                        direction?.directionToInt(),
                        capacity,
                        plant,
                        null,
                        plant.harvestEstimate,
                        plant.harvestEstimate,
                        false,
                        null,
                        capacity,
                        0,
                        emptyMap(),
                        mutableListOf()
                    )
                )
            }
        }

        @Serializable
        @SerialName("FOREST")
        data class Forest(
            override val id: Int,
            override val coordinates: Coordinate,
            val airflow: Boolean,
            val direction: Direction? = null
        ) : JSONTile() {
            override fun toTile(): Result<Tile> {
                if (airflow xor (direction != null)) return tileException

                return Result.success(
                    Tile(
                        id,
                        coordinates,
                        TileType.FOREST,
                        null,
                        airflow,
                        direction?.directionToInt(),
                        null,
                        null,
                        null,
                        0,
                        0,
                        false,
                        null,
                        0,
                        0,
                        emptyMap(),
                        mutableListOf()
                    )
                )
            }
        }

        @Serializable
        @SerialName("MEADOW")
        data class Meadow(
            override val id: Int,
            override val coordinates: Coordinate,
            val airflow: Boolean,
            val direction: Direction? = null
        ) : JSONTile() {
            override fun toTile(): Result<Tile> {
                if (airflow xor (direction != null)) return tileException

                return Result.success(
                    Tile(
                        id,
                        coordinates,
                        TileType.MEADOW,
                        null,
                        airflow,
                        direction?.directionToInt(),
                        null,
                        null,
                        null,
                        0,
                        0,
                        false,
                        null,
                        0,
                        0,
                        emptyMap(),
                        mutableListOf()
                    )
                )
            }
        }

        @Serializable
        @SerialName("ROAD")
        data class Road(
            override val id: Int,
            override val coordinates: Coordinate,
            val airflow: Boolean,
            val direction: Direction? = null
        ) : JSONTile() {
            override fun toTile(): Result<Tile> {
                if (airflow xor (direction != null)) return tileException

                return Result.success(
                    Tile(
                        id,
                        coordinates,
                        TileType.ROAD,
                        null,
                        airflow,
                        direction?.directionToInt(),
                        null,
                        null,
                        null,
                        0,
                        0,
                        false,
                        null,
                        0,
                        0,
                        emptyMap(),
                        mutableListOf()
                    )
                )
            }
        }

        @Serializable
        @SerialName("VILLAGE")
        data class Village(
            override val id: Int,
            override val coordinates: Coordinate
        ) : JSONTile() {
            override fun toTile(): Result<Tile> {
                return Result.success(
                    Tile(
                        id,
                        coordinates,
                        TileType.VILLAGE,
                        null,
                        false,
                        null,
                        null,
                        null,
                        null,
                        0,
                        0,
                        false,
                        null,
                        0,
                        0,
                        emptyMap(),
                        mutableListOf()
                    )
                )
            }
        }
    }

    @Serializable
    private enum class Direction {
        @SerialName("0")
        DEG_0,

        @SerialName("45")
        DEG_45,

        @SerialName("90")
        DEG_90,

        @SerialName("135")
        DEG_135,

        @SerialName("180")
        DEG_180,

        @SerialName("225")
        DEG_225,

        @SerialName("270")
        DEG_270,

        @SerialName("315")
        DEG_315;

        fun directionToInt(): Int = when (this) {
            DEG_0 -> NORTH
            DEG_45 -> NORTHEAST
            DEG_90 -> EAST
            DEG_135 -> SOUTHEAST
            DEG_180 -> SOUTH
            DEG_225 -> SOUTHWEST
            DEG_270 -> WEST
            DEG_315 -> NORTHWEST
        }
    }

    private var hasSeenInvalid = false

    /**
     * Prints [message] to stderr, only if [hasSeenInvalid] is `false`.
     */
    private fun eprintln(message: Any?) {
        if (DEBUG_MAP_PARSER && !hasSeenInvalid) {
            System.err.println("[MAP PARSER] ${message?.toString().orEmpty()}")
        }
    }

    /**
     * Prints [message] to stderr, only if [valid] is `true` and [hasSeenInvalid] is `false`.
     */
    private fun eprintln(message: Any?, valid: Boolean) {
        if (DEBUG_MAP_PARSER && !valid && !hasSeenInvalid) {
            System.err.println("[MAP PARSER] ${message?.toString().orEmpty()}")
        }
        hasSeenInvalid = hasSeenInvalid || !valid
    }
}
