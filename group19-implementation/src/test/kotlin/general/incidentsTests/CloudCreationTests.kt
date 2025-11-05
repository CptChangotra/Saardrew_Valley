package general.incidentsTests

import de.unisaarland.cs.se.selab.cloud.Cloud
import de.unisaarland.cs.se.selab.cloud.CloudController
import de.unisaarland.cs.se.selab.incidents.CloudCreation
import de.unisaarland.cs.se.selab.map.MapController
import de.unisaarland.cs.se.selab.map.Tile
import de.unisaarland.cs.se.selab.map.TileType
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import java.io.File
import java.util.Properties
import kotlin.collections.mutableListOf
import kotlin.text.get
// import org.junit.jupiter.api.Assertions.assertEquals
// import org.junit.jupiter.api.Assertions.assertNotNull
// import org.junit.jupiter.api.Assertions.assertTrue

class CloudCreationTests {
    val mockMapController: MapController = Mockito.mock(MapController::class.java)
    lateinit var cloudIncident: CloudCreation
    lateinit var cloudController: CloudController
    lateinit var mapController: MapController

    object MockTiles {
        fun mockTile(id: Int, category: TileType): Tile {
            val t: Tile = Mockito.mock(Tile::class.java)
            Mockito.`when`(t.id).thenReturn(id)
            Mockito.`when`(t.category).thenReturn(category)
            return t
        }

        fun mockTilewithSunshine(id: Int, category: TileType, sunshine: Int): Tile {
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
        val properties = Properties()
        val propertiesFile = File("config/simplelogger.properties")
        if (propertiesFile.exists()) {
            properties.load(propertiesFile.inputStream())
            // Set properties for SimpleLogger
            for (prop in properties) {
                System.setProperty("org.slf4j.simpleLogger.${prop.key}", prop.value.toString())
            }
        }
        cloudController = CloudController(mutableListOf(), mutableMapOf(), mockMapController)
    }

    @Test
    fun cloudsCreated() {
        // use the TestTiles helper which creates the 4 tiles in one line
        val p1 = MockTiles.mockTile(20, TileType.FARMSTEAD)
        val p2 = MockTiles.plantation(21)
        val p3 = MockTiles.plantation(22)
        val f1 = MockTiles.field(23)
        val f2 = MockTiles.field(24)
        val tiles = listOf(p1, p2, p3, f1, f2)

        // mock the mapController to return these tiles when asked
        Mockito.`when`(mockMapController.getNeighboursGeometries(20, 1)).thenReturn(tiles)
        tiles.forEach { Mockito.`when`(mockMapController.getTile(it.id)).thenReturn(it) }

        // create the incident and apply it
        cloudIncident = CloudCreation(1, 1, mockMapController, 20, 1, 1, 4000, cloudController)
        cloudIncident.apply()

        assertEquals(5, cloudController.clouds.size)
        assertTrue(cloudController.cloudMap.containsKey(p1.id))
        assertTrue(cloudController.cloudMap.containsKey(p2.id))
        assertTrue(cloudController.cloudMap.containsKey(p3.id))
        assertTrue(cloudController.cloudMap.containsKey(f1.id))
        assertTrue(cloudController.cloudMap.containsKey(f2.id))
    }

    @Test
    fun noCloudCreatedOnVILLAGE() {
        val v1 = MockTiles.village(20)
        val v2 = MockTiles.village(21)
        val v3 = MockTiles.village(22)
        val f1 = MockTiles.field(23)
        val v4 = MockTiles.village(24)
        val tiles = listOf(v1, v2, v3, f1, v4)
        Mockito.`when`(mockMapController.getNeighboursGeometries(22, 1)).thenReturn(tiles)
        tiles.forEach { Mockito.`when`(mockMapController.getTile(it.id)).thenReturn(it) }

        cloudIncident = CloudCreation(1, 1, mockMapController, 22, 1, 1, 4000, cloudController)
        cloudIncident.apply()

        assertTrue(cloudController.clouds.size == 1)
        assertTrue(!cloudController.cloudMap.containsKey(v1.id))
        assertTrue(!cloudController.cloudMap.containsKey(v2.id))
        assertTrue(!cloudController.cloudMap.containsKey(v3.id))
        assertTrue(cloudController.cloudMap.containsKey(f1.id))
    }

    @Test
    fun cloudCreatedOn2Radius() {
        val tiles = MockTiles.generateTiles(21)
        Mockito.`when`(mockMapController.getNeighboursGeometries(1, 2)).thenReturn(tiles)
        tiles.forEach { Mockito.`when`(mockMapController.getTile(it.id)).thenReturn(it) }

        val addedClouds = tiles.count { it.category != TileType.VILLAGE }

        cloudIncident = CloudCreation(1, 1, mockMapController, 1, 2, 1, 4000, cloudController)
        cloudIncident.apply()

        assertEquals(cloudController.clouds.size, addedClouds)
    }

    @Test
    fun incidentWithMerge() {
        val v1 = MockTiles.mockTile(20, TileType.PLANTATION)
        val v2 = MockTiles.mockTile(21, TileType.PLANTATION) // pre-existing cloud here
        val v3 = MockTiles.mockTile(22, TileType.PLANTATION)
        val v4 = MockTiles.mockTile(23, TileType.PLANTATION)
        val v5 = MockTiles.mockTile(24, TileType.FARMSTEAD)

        // Setup: A cloud already exists on tile v2
        val existingCloud = Cloud(id = 7, duration = 5, location = v2.id, amount = 1000, distance = 0)
        val clouds = mutableListOf(existingCloud)
        val cloudMap = mutableMapOf(v2.id to existingCloud)
        val localCloudController = CloudController(clouds, cloudMap, mockMapController)
        localCloudController.maxId = 56

        Mockito.`when`(mockMapController.getNeighboursGeometries(22, 1)).thenReturn(listOf(v1, v2, v3, v4, v5))
        listOf(v1, v2, v3, v4, v5).forEach { Mockito.`when`(mockMapController.getTile(it.id)).thenReturn(it) }

        cloudIncident = CloudCreation(1, 1, mockMapController, 22, 1, 1, 4000, localCloudController)
        cloudIncident.apply()

        assertEquals(5, localCloudController.clouds.size)
        assertEquals(5000, localCloudController.cloudMap[v2.id]?.amount)
        assertEquals(59, localCloudController.cloudMap[v2.id]?.id)
    }

    @Test
    fun notAffectingTheCurrentTick() {
        val v1 = MockTiles.mockTilewithSunshine(20, TileType.PLANTATION, 140)
        val v2 = MockTiles.mockTilewithSunshine(21, TileType.PLANTATION, 140) // pre-existing cloud here
        val v3 = MockTiles.mockTilewithSunshine(22, TileType.PLANTATION, 140)
        val v4 = MockTiles.mockTilewithSunshine(23, TileType.PLANTATION, 110) // was here before
        val v5 = MockTiles.mockTilewithSunshine(24, TileType.FARMSTEAD, 140)
        val tiles = listOf(v1, v2, v3, v4, v5)

        val existingCloud = Cloud(id = 7, duration = 5, location = v2.id, amount = 1000, distance = 0)
        val clouds = mutableListOf(existingCloud)
        val cloudMap = mutableMapOf(v2.id to existingCloud)
        val localCloudController = CloudController(clouds, cloudMap, mockMapController)

        Mockito.`when`(mockMapController.getNeighboursGeometries(22, 1)).thenReturn(listOf(v1, v2, v3, v4, v5))
        tiles.forEach { Mockito.`when`(mockMapController.getTile(it.id)).thenReturn(it) }

        cloudIncident = CloudCreation(1, 1, mockMapController, 22, 1, 1, 4000, localCloudController)
        cloudIncident.apply()

        // Verify that the amountSunlight setter was never called on any of the tiles.
        Mockito.verify(v1, Mockito.never()).amountSunlight = Mockito.anyInt()
        Mockito.verify(v2, Mockito.never()).amountSunlight = Mockito.anyInt()
        Mockito.verify(v3, Mockito.never()).amountSunlight = Mockito.anyInt()
        Mockito.verify(v4, Mockito.never()).amountSunlight = Mockito.anyInt()
        Mockito.verify(v5, Mockito.never()).amountSunlight = Mockito.anyInt()
    }

    @Test
    fun correctCloudID() {
        val v1 = MockTiles.mockTile(20, TileType.PLANTATION)
        val v2 = MockTiles.mockTile(21, TileType.PLANTATION)
        val v3 = MockTiles.mockTile(22, TileType.PLANTATION)
        val v4 = MockTiles.mockTile(23, TileType.PLANTATION)
        val v5 = MockTiles.mockTile(24, TileType.FARMSTEAD)

        cloudController.maxId = 70

        Mockito.`when`(mockMapController.getNeighboursGeometries(22, 1)).thenReturn(listOf(v1, v2, v3, v4, v5))
        listOf(v1, v2, v3, v4, v5).forEach { Mockito.`when`(mockMapController.getTile(it.id)).thenReturn(it) }

        cloudIncident = CloudCreation(1, 1, mockMapController, 22, 1, 1, 4000, cloudController)
        cloudIncident.apply()

        assertEquals(5, cloudController.clouds.size)
        assertEquals(71, cloudController.clouds[0].id)
        assertEquals(72, cloudController.clouds[1].id)
        assertEquals(73, cloudController.clouds[2].id)
        assertEquals(74, cloudController.clouds[3].id)
        assertEquals(75, cloudController.clouds[4].id)
    }

    @Test
    fun cloudOnVillageGetsStuckAndDissipates() {
        val v1 = MockTiles.mockTile(20, TileType.PLANTATION)
        val v2 = MockTiles.mockTile(21, TileType.VILLAGE) // Village tile
        val v3 = MockTiles.mockTile(22, TileType.PLANTATION)

        cloudController.maxId = 80

        // Mock the map controller responses
        Mockito.`when`(mockMapController.getNeighboursGeometries(21, 1)).thenReturn(listOf(v1, v2, v3))
        listOf(v1, v2, v3).forEach { Mockito.`when`(mockMapController.getTile(it.id)).thenReturn(it) }

        // Create and apply the incident
        val cloudIncident = CloudCreation(1, 1, mockMapController, 21, 1, 1, 4000, cloudController)
        cloudIncident.apply()

        // Assertions
        assertEquals(2, cloudController.clouds.size) // Only 2 clouds should be created
        assertEquals(false, cloudController.cloudMap.containsKey(v2.id)) // No cloud on the village tile
        assertEquals(81, cloudController.clouds[0].id)
        assertEquals(82, cloudController.clouds[1].id)
    }

    @Test
    fun maxIDPlus2() {
        val v1 = MockTiles.mockTile(20, TileType.PLANTATION)

        val existingCloud = Cloud(id = 1, duration = 5, location = v1.id, amount = 1000, distance = 0)
        val clouds = mutableListOf(existingCloud)
        val cloudMap = mutableMapOf(v1.id to existingCloud)
        val localCloudController = CloudController(clouds, cloudMap, mockMapController)
        localCloudController.maxId = 1

        Mockito.`when`(mockMapController.getNeighboursGeometries(v1.id, 1)).thenReturn(listOf(v1))
        Mockito.`when`(mockMapController.getTile(v1.id)).thenReturn(v1)

        cloudIncident = CloudCreation(1, 1, mockMapController, v1.id, 1, 1, 4000, localCloudController)
        cloudIncident.apply()

        assertEquals(3, cloudMap[v1.id]!!.id)
    }

    @Test
    fun `test getLocation getter`() {
        val cloudIncident = CloudCreation(1, 1, mockMapController, 42, 5, 10, 3000, cloudController)
        assertEquals(42, cloudIncident.location, "getLocation should return the location value")
    }

    @Test
    fun `test getRadius getter`() {
        val cloudIncident = CloudCreation(1, 1, mockMapController, 20, 15, 10, 3000, cloudController)
        assertEquals(15, cloudIncident.radius, "getRadius should return the radius value")
    }

    @Test
    fun `test getDuration getter`() {
        val cloudIncident = CloudCreation(1, 1, mockMapController, 20, 5, 25, 3000, cloudController)
        assertEquals(25, cloudIncident.duration, "getDuration should return the duration value")
    }

    @Test
    fun `test getAmount getter`() {
        val cloudIncident = CloudCreation(1, 1, mockMapController, 20, 5, 10, 5500, cloudController)
        assertEquals(5500, cloudIncident.amount, "getAmount should return the amount value")
    }

    @Test
    fun `test getCloudController getter`() {
        val cloudIncident = CloudCreation(1, 1, mockMapController, 20, 5, 10, 3000, cloudController)
        assertEquals(
            cloudController,
            cloudIncident.cloudController,
            "getCloudController should return the cloudController instance"
        )
    }
}
