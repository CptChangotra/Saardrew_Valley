package general.mapTests

import de.unisaarland.cs.se.selab.map.Coordinate
import de.unisaarland.cs.se.selab.map.MapController
import de.unisaarland.cs.se.selab.map.SimulationMap
import de.unisaarland.cs.se.selab.map.Tile
import de.unisaarland.cs.se.selab.map.TileType
import de.unisaarland.cs.se.selab.plant.PlantationPlant
import de.unisaarland.cs.se.selab.utils.Direction.NORTH
import de.unisaarland.cs.se.selab.utils.Direction.NORTHWEST
import de.unisaarland.cs.se.selab.utils.Direction.SOUTH
import de.unisaarland.cs.se.selab.utils.Direction.SOUTHEAST
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class PathFinding {
    lateinit var tiles: List<Tile>
    val baseTile = Tile(
        -1,
        Coordinate(1, -1),
        TileType.ROAD,
        -1,
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

    @BeforeEach
    fun setup() {
        val farmTile = baseTile.copy(
            id = 0,
            coordinate = Coordinate(1, -1),
            category = TileType.FARMSTEAD,
            farm = 0,
            shed = true,
            neighbours = mapOf(SOUTHEAST to 1)
        )

        // start tile for each test
        val machineTile = baseTile.copy(
            id = 20, coordinate = Coordinate(2, 2), category = TileType.PLANTATION,
            farm = 0, maxCapacity = 1000, plant = PlantationPlant.APPLE, harvestEstimate = 1000,
            startOfTickEstimate = 1000, soilMoisture = 1000, neighbours = mapOf(NORTH to 1)
        )

        tiles = listOf(farmTile, machineTile)
    }

    @Test
    fun `test findPath with forest`() {
        val forestTile = baseTile.copy(
            id = 1,
            coordinate = Coordinate(2, 0),
            category = TileType.FOREST,
            neighbours = mapOf(NORTHWEST to 0, SOUTH to 20)
        )
        val tiles = tiles + forestTile

        val simMap = SimulationMap(tiles.associateBy { it.id })
        val mapC = MapController(simMap)

        assert(!mapC.findPath(20, 0, false))
    }

    @Test
    fun `test findPath with field of same farm`() {
        val fieldTile = baseTile.copy(
            id = 1, coordinate = Coordinate(2, 0), category = TileType.PLANTATION,
            farm = 0, maxCapacity = 1000, plant = PlantationPlant.APPLE, harvestEstimate = 1000,
            startOfTickEstimate = 1000, soilMoisture = 1000, neighbours = mapOf(NORTHWEST to 0, SOUTH to 20)
        )
        val tiles = tiles + fieldTile

        val simMap = SimulationMap(tiles.associateBy { it.id })
        val mapC = MapController(simMap)

        assert(mapC.findPath(20, 0, false))
    }

    @Test
    fun `test findPath with field of different farm`() {
        val fieldTile = baseTile.copy(
            id = 1, coordinate = Coordinate(2, 0), category = TileType.PLANTATION,
            farm = 1, maxCapacity = 1000, plant = PlantationPlant.APPLE, harvestEstimate = 1000,
            startOfTickEstimate = 1000, soilMoisture = 1000, neighbours = mapOf(NORTHWEST to 0, SOUTH to 20)
        )
        val tiles = tiles + fieldTile

        val simMap = SimulationMap(tiles.associateBy { it.id })
        val mapC = MapController(simMap)

        assert(!mapC.findPath(20, 0, false))
    }

    @Test
    fun `test findPath through road or meadow tile`() {
        // ROAD always traversable
        val roadTile = baseTile.copy(
            id = 1,
            coordinate = Coordinate(2, 0),
            category = TileType.ROAD,
            neighbours = mapOf(NORTHWEST to 0, SOUTH to 20)
        )
        val tiles = tiles + roadTile

        val simMap = SimulationMap(tiles.associateBy { it.id })
        val mapC = MapController(simMap)

        // Path 20 -> 1 (ROAD) -> 0
        assert(mapC.findPath(20, 0, false))
        assert(mapC.findPath(20, 0, true))
    }

    @Test
    fun `test findPath village with emptyMachine`() {
        // traversable
        val villageTile = baseTile.copy(
            id = 1,
            coordinate = Coordinate(2, 0),
            category = TileType.VILLAGE,
            neighbours = mapOf(NORTHWEST to 0, SOUTH to 20)
        )
        val tiles = tiles + villageTile

        val simMap = SimulationMap(tiles.associateBy { it.id })
        val mapC = MapController(simMap)

        // Path 20 -> 1 (VILLAGE) -> 0
        assert(mapC.findPath(20, 0, false))
    }

    @Test
    fun `test findPath VIllAGE with fullMachine`() {
        // not traversable
        val villageTile = baseTile.copy(
            id = 1,
            coordinate = Coordinate(2, 0),
            category = TileType.VILLAGE,
            neighbours = mapOf(NORTHWEST to 0, SOUTH to 20)
        )
        val tiles = tiles + villageTile

        val simMap = SimulationMap(tiles.associateBy { it.id })
        val mapC = MapController(simMap)

        assert(!mapC.findPath(20, 0, true))
    }

    // findPathAStar
    @Test
    fun `test findPathAStar village with emptyMachine`() {
        val villageTile = baseTile.copy(
            id = 1,
            coordinate = Coordinate(2, 0),
            category = TileType.VILLAGE,
            neighbours = mapOf(NORTHWEST to 0, SOUTH to 20)
        )
        val tiles = tiles + villageTile

        val simMap = SimulationMap(tiles.associateBy { it.id })
        val mapC = MapController(simMap)

        assert(mapC.findPathAStar(20, 0, false))
    }

    @Test
    fun `test findPathAStar VILLAGE fullMachine`() {
        val villageTile = baseTile.copy(
            id = 1,
            coordinate = Coordinate(2, 0),
            category = TileType.VILLAGE,
            neighbours = mapOf(NORTHWEST to 0, SOUTH to 20)
        )
        val tiles = tiles + villageTile

        val simMap = SimulationMap(tiles.associateBy { it.id })
        val mapC = MapController(simMap)

        assert(!mapC.findPathAStar(20, 0, true))
    }

    @Test
    fun `test findPathAStar road`() {
        // always traversable
        val roadTile = baseTile.copy(
            id = 1,
            coordinate = Coordinate(2, 0),
            category = TileType.ROAD, // Or TileType.MEADOW
            neighbours = mapOf(NORTHWEST to 0, SOUTH to 20)
        )
        val tiles = tiles + roadTile

        val simMap = SimulationMap(tiles.associateBy { it.id })
        val mapC = MapController(simMap)

        assert(mapC.findPathAStar(20, 0, false))
        assert(mapC.findPathAStar(20, 0, true))
    }

    @Test
    fun `test findPathAStar different farm plantation`() {
        // tile 1 not traversable (different farm plantation)
        val fieldTile = baseTile.copy(
            id = 1, coordinate = Coordinate(2, 0), category = TileType.PLANTATION,
            farm = 1, maxCapacity = 1000, plant = PlantationPlant.APPLE, harvestEstimate = 1000,
            startOfTickEstimate = 1000, soilMoisture = 1000, neighbours = mapOf(NORTHWEST to 0, SOUTH to 20)
        )
        val tiles = tiles + fieldTile

        val simMap = SimulationMap(tiles.associateBy { it.id })
        val mapC = MapController(simMap)

        assert(!mapC.findPathAStar(20, 0, false))
    }

    @Test
    fun `test findPathAStar same farm plantation`() {
        val fieldTile = baseTile.copy(
            id = 1, coordinate = Coordinate(2, 0), category = TileType.PLANTATION,
            farm = 0, maxCapacity = 1000, plant = PlantationPlant.APPLE, harvestEstimate = 1000,
            startOfTickEstimate = 1000, soilMoisture = 1000, neighbours = mapOf(NORTHWEST to 0, SOUTH to 20)
        )
        val tiles = tiles + fieldTile

        val simMap = SimulationMap(tiles.associateBy { it.id })
        val mapC = MapController(simMap)

        assert(mapC.findPathAStar(20, 0, false))
    }

    @Test
    fun `test findPathAStar blocked by forest`() {
        // forest not traversable
        val forestTile = baseTile.copy(
            id = 1,
            coordinate = Coordinate(2, 0),
            category = TileType.FOREST,
            neighbours = mapOf(NORTHWEST to 0, SOUTH to 20)
        )
        val tiles = tiles + forestTile

        val simMap = SimulationMap(tiles.associateBy { it.id })
        val mapC = MapController(simMap)

        assert(!mapC.findPathAStar(20, 0, false))
        assert(!mapC.findPathAStar(20, 0, true))
    }
}
