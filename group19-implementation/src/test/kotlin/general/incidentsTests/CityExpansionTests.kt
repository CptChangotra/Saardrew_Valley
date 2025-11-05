package general.incidentsTests

import de.unisaarland.cs.se.selab.cloud.Cloud
import de.unisaarland.cs.se.selab.cloud.CloudController
import de.unisaarland.cs.se.selab.farms.FarmController
import de.unisaarland.cs.se.selab.incidents.CityExpand
import de.unisaarland.cs.se.selab.map.Coordinate
import de.unisaarland.cs.se.selab.map.MapController
import de.unisaarland.cs.se.selab.map.Tile
import de.unisaarland.cs.se.selab.map.TileType
import org.junit.jupiter.api.BeforeEach
import org.mockito.Mockito
import kotlin.test.Test
import kotlin.test.assertEquals

class CityExpansionTests {
    val mockMapController: MapController = Mockito.mock(MapController::class.java)
    val mockFarmController: FarmController = Mockito.mock(FarmController::class.java)
    lateinit var mockCloudController: CloudController

    object MockTiles {
        fun mockTile(id: Int, category: TileType): Tile {
            val t: Tile = Mockito.mock(Tile::class.java)
            Mockito.`when`(t.id).thenReturn(id)
            Mockito.`when`(t.category).thenReturn(category)
            return t
        }

        fun mockTileWithSunshine(id: Int, category: TileType, sunshine: Int): Tile {
            val t: Tile = Mockito.mock(Tile::class.java)
            Mockito.`when`(t.id).thenReturn(id)
            Mockito.`when`(t.category).thenReturn(category)
            Mockito.`when`(t.amountSunlight).thenReturn(sunshine)
            return t
        }

        fun village(id: Int) = mockTile(id, TileType.VILLAGE)
        fun field(id: Int) = mockTile(id, TileType.FIELD)
        fun plantation(id: Int) = mockTile(id, TileType.PLANTATION)

        fun allTypes(startId: Int = 1): List<Tile> =
            TileType.entries.toTypedArray().mapIndexed { idx, type -> mockTile(startId + idx, type) }

        fun generateTiles(count: Int, startId: Int = 1): List<Tile> {
            val types = TileType.entries.toTypedArray()
            return (0 until count).map { i ->
                val id = startId + i
                val type = types[i % types.size]
                mockTile(id, type)
            }
        }
    }

    @BeforeEach
    fun setup() {
        mockCloudController = Mockito.mock(CloudController::class.java)
    }

    @Test
    fun cityExpandedOnRoad() {
        val road = Tile(
            1, Coordinate(0, 0),
            category = TileType.ROAD,
            farm = null,
            airflow = false,
            direction = null,
            maxCapacity = null,
            plant = null,
            possiblePlants = null,
            harvestEstimate = 0,
            startOfTickEstimate = 0,
            shed = false,
            fallowPeriod = null,
            soilMoisture = 0,
            amountSunlight = 0,
            neighbours = emptyMap(),
            penalties = mutableListOf()
        )
        Mockito.`when`(mockMapController.getTile(1)).thenReturn(road)

        val expansion = CityExpand(1, 1, mockMapController, 1, mockCloudController, listOf(mockFarmController))
        expansion.apply()

        assertEquals(TileType.VILLAGE, road.category)
        // Verify that removeFieldFromFarm was NOT called
        Mockito.verify(mockFarmController, Mockito.never()).removeFieldFromFarm(1)
    }

    @Test
    fun cityExpandedOnRoadTileWithCloud() {
        val road = Tile(
            1, Coordinate(0, 0),
            category = TileType.ROAD,
            farm = null,
            airflow = false,
            direction = null,
            maxCapacity = null,
            plant = null,
            possiblePlants = null,
            harvestEstimate = 0,
            startOfTickEstimate = 0,
            shed = false,
            fallowPeriod = null,
            soilMoisture = 0,
            amountSunlight = 0,
            neighbours = emptyMap(),
            penalties = mutableListOf()
        )
        // Configure the mocked CloudController to have one cloud on tile 1
        val cloud = Cloud(1, 5, 1, Cloud.RAINABLE + 1000, Cloud.MAX_MOVES)
        val cloudsList = mutableListOf(cloud)
        val cloudsMap = mutableMapOf(1 to cloud)
        Mockito.`when`(mockCloudController.clouds).thenReturn(cloudsList)
        Mockito.`when`(mockCloudController.cloudMap).thenReturn(cloudsMap)

        Mockito.`when`(mockMapController.getTile(1)).thenReturn(road)

        val expansion = CityExpand(1, 1, mockMapController, 1, mockCloudController, listOf(mockFarmController))
        expansion.apply()

        // Verify that removeFieldFromFarm was NOT called
        Mockito.verify(mockFarmController, Mockito.never()).removeFieldFromFarm(1)

        // Verify that the cloud was removed
        assertEquals(0, mockCloudController.cloudMap.size)
        assertEquals(0, mockCloudController.clouds.size)

        // Verify that the tile properties were reset
        assertEquals(TileType.VILLAGE, road.category) // Category should change to VILLAGE
    }

    @Test
    fun cityExpandedOnFieldTile() {
        val field = Tile(
            1, Coordinate(0, 0),
            category = TileType.FIELD,
            farm = 1,
            airflow = true,
            direction = 45,
            maxCapacity = 10,
            plant = Mockito.mock(),
            possiblePlants = listOf(Mockito.mock()),
            harvestEstimate = 100,
            startOfTickEstimate = 100,
            shed = true,
            fallowPeriod = 5,
            soilMoisture = 50,
            amountSunlight = 50,
            neighbours = mapOf(0 to 2),
            penalties = mutableListOf()
        )
        // no cloud present for this test

        Mockito.`when`(mockMapController.getTile(1)).thenReturn(field)

        val expansion = CityExpand(1, 1, mockMapController, 1, mockCloudController, listOf(mockFarmController))
        expansion.apply()

        // Verify that removeFieldFromFarm was called
        Mockito.verify(mockFarmController).removeFieldFromFarm(1)

        // Verify that the cloud was removed
        // no cloud present -> cloud map is empty (mock returns default empty or null, but we assert no change)
        // We don't check the mockCloudController here because it's not configured in this test

        // Verify that the tile properties were reset
        assertEquals(TileType.VILLAGE, field.category) // Category should change to VILLAGE
        assertEquals(null, field.farm)
        assertEquals(null, field.direction)
        assertEquals(null, field.maxCapacity)
        assertEquals(null, field.plant)
        assertEquals(null, field.possiblePlants)
        assertEquals(0, field.harvestEstimate)
        assertEquals(0, field.startOfTickEstimate)
        assertEquals(false, field.shed)
        assertEquals(0, field.soilMoisture)
        assertEquals(null, field.fallowPeriod)
        assertEquals(0, field.amountSunlight)
        // assertEquals(emptyMap(), field.neighbours)
    }

    @Test
    fun cityExpandedOnFieldTileWithCloud() {
        val field = Tile(
            1, Coordinate(0, 0),
            category = TileType.FIELD,
            farm = 1,
            airflow = true,
            direction = 45,
            maxCapacity = 10,
            plant = Mockito.mock(),
            possiblePlants = listOf(Mockito.mock()),
            harvestEstimate = 100,
            startOfTickEstimate = 100,
            shed = true,
            fallowPeriod = 5,
            soilMoisture = 50,
            amountSunlight = 50,
            neighbours = mapOf(0 to 2),
            penalties = mutableListOf()
        )
        // Configure the mocked CloudController to have one cloud on tile 1
        val cloud = Cloud(1, 5, 1, Cloud.RAINABLE + 1000, Cloud.MAX_MOVES)
        val cloudsList = mutableListOf(cloud)
        val cloudsMap = mutableMapOf(1 to cloud)
        Mockito.`when`(mockCloudController.clouds).thenReturn(cloudsList)
        Mockito.`when`(mockCloudController.cloudMap).thenReturn(cloudsMap)

        Mockito.`when`(mockMapController.getTile(1)).thenReturn(field)

        val expansion = CityExpand(1, 1, mockMapController, 1, mockCloudController, listOf(mockFarmController))
        expansion.apply()

        // Verify that removeFieldFromFarm was called
        Mockito.verify(mockFarmController).removeFieldFromFarm(1)

        // Verify that the cloud was removed
        assertEquals(0, mockCloudController.cloudMap.size)
        assertEquals(0, mockCloudController.clouds.size)

        // Verify that the tile properties were reset
        assertEquals(TileType.VILLAGE, field.category) // Category should change to VILLAGE
        assertEquals(null, field.farm)
        assertEquals(null, field.direction)
        assertEquals(null, field.maxCapacity)
        assertEquals(null, field.plant)
        assertEquals(null, field.possiblePlants)
        assertEquals(0, field.harvestEstimate)
        assertEquals(0, field.startOfTickEstimate)
        assertEquals(false, field.shed)
        assertEquals(0, field.soilMoisture)
        assertEquals(null, field.fallowPeriod)
        assertEquals(0, field.amountSunlight)
        // assertEquals(emptyMap(), field.neighbours)
    }
}
