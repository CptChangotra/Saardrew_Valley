package general.cloudTests

import de.unisaarland.cs.se.selab.cloud.Cloud
import de.unisaarland.cs.se.selab.cloud.CloudController
import de.unisaarland.cs.se.selab.map.Coordinate
import de.unisaarland.cs.se.selab.map.MapController
import de.unisaarland.cs.se.selab.map.Tile
import de.unisaarland.cs.se.selab.map.TileType
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.doAnswer
import org.mockito.kotlin.any
import org.mockito.kotlin.eq
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class CloudTest {
    private val mockMapController: MapController = Mockito.mock(MapController::class.java)
    private lateinit var cloudController: CloudController
    private lateinit var mockTile: Tile
    private lateinit var mockTile2: Tile

    @BeforeEach
    fun setup() {
        cloudController = CloudController(mutableListOf(), mutableMapOf(), mockMapController)
        mockTile = Mockito.mock(Tile::class.java)
        mockTile2 = Mockito.mock(Tile::class.java)
    }

    @Test
    fun dissipateRemovesCloud() {
        val cloud = Cloud(1, 10, 5, 6000, 0)
        cloudController.clouds.add(cloud)
        cloudController.cloudMap[cloud.location] = cloud
        Mockito.`when`(mockMapController.getTile(5)).thenReturn(mockTile)
        Mockito.`when`(mockTile.category).thenReturn(TileType.FOREST)
        cloudController.dissipate(cloud)

        // dissipation removes cloud everywhere
        Assertions.assertFalse(cloudController.clouds.contains(cloud))
        Assertions.assertNull(cloudController.cloudMap[cloud.location])
    }

    @Test
    fun rainOnRainTile() {
        val cloud = Cloud(1, 10, 5, 6000, 0)
        val tile = Tile(
            id = 5,
            coordinate = Coordinate(0, 0),
            category = TileType.FIELD,
            farm = null,
            airflow = false,
            direction = null,
            maxCapacity = 5000,
            plant = null,
            possiblePlants = null,
            harvestEstimate = 0,
            startOfTickEstimate = 0,
            shed = false,
            fallowPeriod = null,
            soilMoisture = 2000,
            amountSunlight = 0,
            neighbours = emptyMap(),
            penalties = mutableListOf()
        )
        Mockito.`when`(mockMapController.getTile(5)).thenReturn(tile)
        cloudController.rain(cloud)

        // tile needed 3000 to fill for 5000, so in the cloud it remains 3000
        assertEquals(5000, tile.soilMoisture)
        assertEquals(3000, cloud.amount)
    }

    @Test
    fun rainOnNotRainTile() {
        val cloud = Cloud(1, 10, 5, 6000, 0)
        val tile = Mockito.mock(Tile::class.java)
        Mockito.`when`(tile.maxCapacity).thenReturn(null)
        Mockito.`when`(tile.category).thenReturn(TileType.PLANTATION)
        Mockito.`when`(mockMapController.getTile(5)).thenReturn(tile)
        cloudController.rain(cloud)

        // dissipate because fully rained
        Assertions.assertFalse(cloudController.clouds.contains(cloud))
        Assertions.assertNull(cloudController.cloudMap[cloud.location])
    }

    @Test
    fun moveCloudWithVillageDestination() {
        val cloud = Cloud(1, 10, 1, 6000, 10)
        cloudController.clouds.add(cloud)
        cloudController.cloudMap[cloud.location] = cloud
        Mockito.`when`(mockMapController.getTile(1)).thenReturn(mockTile)
        Mockito.`when`(mockTile.category).thenReturn(TileType.FOREST)
        Mockito.`when`(mockTile.id).thenReturn(1)
        Mockito.`when`(mockMapController.getNeighbourTile(eq(1))).thenReturn(mockTile2)
        Mockito.`when`(mockMapController.getTile(2)).thenReturn(mockTile2)
        Mockito.`when`(mockTile2.category).thenReturn(TileType.VILLAGE)
        Mockito.`when`(mockTile2.id).thenReturn(2)
        cloudController.move(cloud)

        // dissipate on village
        Assertions.assertFalse(cloudController.clouds.contains(cloud))
        Assertions.assertNull(cloudController.cloudMap[1])
    }

    @Test
    fun moveCloudIntoExistingCloudTile() {
        val cloud1 = Cloud(1, 10, 1, 6000, 10)
        val cloud2 = Cloud(2, 5, 2, 4000, 10)
        cloudController.clouds.add(cloud1)
        cloudController.clouds.add(cloud2)
        cloudController.cloudMap[1] = cloud1
        cloudController.cloudMap[2] = cloud2
        cloudController.maxId = 2
        Mockito.`when`(mockMapController.getTile(1)).thenReturn(mockTile)
        Mockito.`when`(mockMapController.getTile(2)).thenReturn(mockTile2)
        Mockito.`when`(mockTile.id).thenReturn(1)
        Mockito.`when`(mockMapController.getNeighbourTile(eq(1))).thenReturn(mockTile2)
        Mockito.`when`(mockTile2.id).thenReturn(2)
        Mockito.`when`(mockTile.category).thenReturn(TileType.PLANTATION)
        Mockito.`when`(mockTile2.category).thenReturn(TileType.PLANTATION)
        cloudController.move(cloud1)

        // check how merging creates cloud, removes old ones and check properties of new cloud
        val resultCloud = cloudController.clouds.find { it.id == 3 }
        Assertions.assertNotNull(resultCloud)
        Assertions.assertFalse(cloudController.clouds.contains(cloud1))
        Assertions.assertFalse(cloudController.clouds.contains(cloud2))
        Assertions.assertTrue(cloudController.clouds.contains(resultCloud))
        assertEquals(2, resultCloud!!.location)
        assertEquals(10000, resultCloud.amount)
    }

    @Test
    fun updateOnTickMovesAndDissipatesCloud() {
        val cloud = Cloud(1, 1, 1, 6000, 1)
        cloudController.clouds.add(cloud)
        cloudController.cloudMap[1] = cloud
        cloudController.processingClouds.add(cloud)
        val tile1 = Mockito.mock(Tile::class.java)
        Mockito.`when`(tile1.id).thenReturn(1)
        Mockito.`when`(tile1.category).thenReturn(TileType.FOREST)
        val tile2 = Mockito.mock(Tile::class.java)
        Mockito.`when`(tile2.id).thenReturn(2)
        Mockito.`when`(tile2.category).thenReturn(TileType.FOREST)
        Mockito.`when`(mockMapController.getTile(1)).thenReturn(tile1)
        Mockito.`when`(mockMapController.getTile(2)).thenReturn(tile2)
        Mockito.`when`(mockMapController.getNeighbourTile(eq(1))).thenReturn(tile2)
        cloudController.updateOnTick()

        // cloud should have moved and dissipated
        Assertions.assertFalse(cloudController.clouds.contains(cloud))
        Assertions.assertNull(cloudController.cloudMap[1])
        Assertions.assertNull(cloudController.cloudMap[2])
    }

    @Test
    fun moveCloudOnAirflowTile() {
        val tile1Id = 1
        val tile2Id = 2
        val cloud = Cloud(1, 5, tile1Id, 6000, 10)
        cloudController.clouds.add(cloud)
        cloudController.cloudMap[tile1Id] = cloud

        val tile1: Tile = Mockito.mock(Tile::class.java)
        Mockito.`when`(tile1.id).thenReturn(tile1Id)
        val airflowTile: Tile = Mockito.mock(Tile::class.java)
        Mockito.`when`(airflowTile.id).thenReturn(tile2Id)
        Mockito.`when`(airflowTile.airflow).thenReturn(true)
        Mockito.`when`(mockMapController.getTile(tile1Id)).thenReturn(tile1)
        Mockito.`when`(mockMapController.getTile(tile2Id)).thenReturn(airflowTile)
        Mockito.`when`(mockMapController.getNeighbourTile(eq(tile1Id))).thenReturn(airflowTile)
        cloudController.move(cloud)

        assertTrue(cloudController.clouds.contains(cloud), "Cloud should not be dissipated due to airflow tile")
        Assertions.assertNull(cloudController.cloudMap[tile1Id], "Cloud should be removed from old location")
        Assertions.assertNotNull(
            cloudController.cloudMap[tile2Id],
            "Cloud should be added in the map to the new location"
        )
    }

    @Test
    fun updateOnTickDissipatesCloudWithDurationOne() {
        val tileId = 5
        val cloud = Cloud(id = 3, duration = 1, location = tileId, amount = 1000, distance = 0)
        cloudController.clouds.add(cloud)
        cloudController.processingClouds.add(cloud)
        val tile: Tile = Mockito.mock(Tile::class.java)
        Mockito.`when`(tile.id).thenReturn(tileId)
        Mockito.`when`(tile.category).thenReturn(TileType.FOREST)
        Mockito.`when`(mockMapController.getTile(tileId)).thenReturn(tile)
        Mockito.`when`(tile.maxCapacity).thenReturn(null)

        cloudController.updateOnTick()

        Assertions.assertFalse(
            cloudController.clouds.contains(cloud),
            "Cloud should be dissipated when duration reaches 0"
        )
    }

    @Test
    fun rainOnMaxSoilMoistureTile() {
        val tileId = 15
        val maxCapacity = 5000
        val initialCloudAmount = 6000
        val cloud = Cloud(id = 2, duration = 10, location = tileId, amount = initialCloudAmount, distance = 3)
        val farmTile: Tile = Mockito.mock(Tile::class.java)
        Mockito.`when`(farmTile.id).thenReturn(tileId)
        Mockito.`when`(farmTile.maxCapacity).thenReturn(maxCapacity)
        Mockito.`when`(farmTile.soilMoisture).thenReturn(maxCapacity)
        Mockito.`when`(mockMapController.getTile(tileId)).thenReturn(farmTile)
        cloudController.clouds.add(cloud)
        cloudController.rain(cloud)

        assertTrue(cloudController.clouds.contains(cloud), "Cloud should not be dissipated")
        assertEquals(initialCloudAmount, cloud.amount, "Cloud amount should be unchanged")
    }

    @Test
    fun moveCloudDoesntDissipateWhenDistanceReachesZero() {
        val tile1Id = 1
        val tile2Id = 2
        val cloud = Cloud(1, 10, tile1Id, 6000, 1)
        cloudController.clouds.add(cloud)
        cloudController.processingClouds.add(cloud)
        val tile1: Tile = Mockito.mock(Tile::class.java)
        Mockito.`when`(tile1.id).thenReturn(tile1Id)
        val tile2: Tile = Mockito.mock(Tile::class.java)
        Mockito.`when`(tile2.id).thenReturn(tile2Id)
        Mockito.`when`(tile2.category).thenReturn(TileType.FOREST)
        Mockito.`when`(mockMapController.getTile(tile1Id)).thenReturn(tile1)
        Mockito.`when`(mockMapController.getTile(tile2Id)).thenReturn(tile2)
        Mockito.`when`(mockMapController.getNeighbourTile(eq(tile1Id))).thenReturn(tile2)

        cloudController.updateOnTick()

        assertTrue(
            cloudController.clouds.contains(cloud),
            "Cloud should not dissipate after moving 10 tiles"
        )
    }

    @Test
    fun rainOnInfiniteDurationCloud() {
        val tileId = 20
        val maxCapacity = 1000
        var soilMoisture = 500
        val rainNeeded = maxCapacity - soilMoisture // 500
        val initialCloudAmount = 6000
        val expectedRemainingAmount = initialCloudAmount - rainNeeded // 5500
        val cloud = Cloud(
            id = 4,
            duration = Cloud.INFINITE_CLOUD_DURATION,
            location = tileId,
            amount = initialCloudAmount,
            distance = 5
        )
        val farmTile: Tile = Mockito.mock(Tile::class.java) // farmTile -> farmable Tile
        Mockito.`when`(farmTile.id).thenReturn(tileId)
        Mockito.`when`(farmTile.maxCapacity).thenReturn(maxCapacity)
        Mockito.`when`(farmTile.soilMoisture).thenAnswer { soilMoisture }
        // mocking the setter for soilMoisture
        doAnswer { invocation ->
            soilMoisture = (invocation.arguments[0] ?: error("null assertion message")) as Int
            null
        }.`when`(farmTile).soilMoisture = any()
        Mockito.`when`(mockMapController.getTile(tileId)).thenReturn(farmTile)
        cloudController.clouds.add(cloud)

        cloudController.rain(cloud)

        assertTrue(cloudController.clouds.contains(cloud), "Infinite cloud should not be dissipated")
        assertEquals(Cloud.INFINITE_CLOUD_DURATION, cloud.duration, "Duration must be still infinite")
        assertEquals(maxCapacity, soilMoisture, "Tile soil moisture should be maxCapacity")
        assertEquals(expectedRemainingAmount, cloud.amount, "Cloud amount should be reduced by rain amount")
    }

    @Test
    fun testMergeInfiniteDurationClouds() {
        val tile1 = Mockito.mock(Tile::class.java)
        Mockito.`when`(tile1.id).thenReturn(1)
        val tile2 = Mockito.mock(Tile::class.java)
        Mockito.`when`(tile2.id).thenReturn(2)
        val amount1 = 6000
        val amount2 = 4000
        val cloud1 = Cloud(7, Cloud.INFINITE_CLOUD_DURATION, tile1.id, amount1, 3)
        val cloud2 = Cloud(8, Cloud.INFINITE_CLOUD_DURATION, tile2.id, amount2, 5)
        cloudController.clouds.addAll(listOf(cloud1, cloud2))
        cloudController.maxId = 8
        val mergedCloud = cloudController.merge(cloud1, cloud2)

        Assertions.assertNotNull(mergedCloud)
        assertEquals(
            Cloud.INFINITE_CLOUD_DURATION,
            mergedCloud.duration,
            "Merged cloud duration must remain infinite."
        )
        assertEquals(amount1 + amount2, mergedCloud.amount, "Amounts must be 10000.")
    }

    @Test
    fun testMergeCloudsWithZeroDistance() {
        val tile1 = Mockito.mock(Tile::class.java)
        Mockito.`when`(tile1.id).thenReturn(1)
        val tile2 = Mockito.mock(Tile::class.java)
        Mockito.`when`(tile2.id).thenReturn(2)
        val cloud1 = Cloud(1, 10, tile1.id, 6000, 0)
        val cloud2 = Cloud(2, 10, tile2.id, 4000, 5)
        cloudController.clouds.addAll(listOf(cloud1, cloud2))
        cloudController.maxId = 2
        val mergedCloud = cloudController.merge(cloud1, cloud2)

        Assertions.assertNotNull(mergedCloud)
        assertEquals(5, mergedCloud.distance, "Merged distance should be 5.")
    }

    @Test
    fun testRainBelowRainableThreshold() {
        val tileId = 15
        val cloud = Cloud(1, 10, tileId, Cloud.RAINABLE - 1, 3)
        cloudController.clouds.add(cloud)
        val tile = Mockito.mock(Tile::class.java)
        Mockito.`when`(tile.id).thenReturn(tileId)
        Mockito.`when`(mockMapController.getTile(tileId)).thenReturn(tile)

        cloudController.rain(cloud)

        assertTrue(cloudController.clouds.contains(cloud), "Cloud should not be dissipated.")
        assertEquals(Cloud.RAINABLE - 1, cloud.amount, "Cloud amount should be unchanged.")
    }

    @Test
    fun testRainExactlyRainableAndCloudSurvives() {
        val tileId = 16
        val rainNeeded = 1000
        val initialSoilMoisture = 4000
        val maxCapacity = 5000
        val cloud = Cloud(1, 10, tileId, Cloud.RAINABLE, 3) // amount = 5000
        cloudController.clouds.add(cloud)
        val tile = Mockito.mock(Tile::class.java)
        Mockito.`when`(tile.id).thenReturn(tileId)
        Mockito.`when`(tile.maxCapacity).thenReturn(maxCapacity)
        Mockito.`when`(tile.soilMoisture).thenReturn(initialSoilMoisture)
        Mockito.`when`(mockMapController.getTile(tileId)).thenReturn(tile)

        cloudController.rain(cloud)

        assertTrue(cloudController.clouds.contains(cloud), "Cloud should survive since amount > rain needed.")
        assertEquals(
            Cloud.RAINABLE - rainNeeded,
            cloud.amount,
            "Cloud amount should be reduced by rainNeeded (5000 - 1000 = 4000)."
        )
    }

    @Test
    fun testCloudCreatedOnOccupiedTile() {
        val tileId = 10
        val existingCloud = Cloud(5, 5, tileId, 5000, 5)
        cloudController.cloudMap[tileId] = existingCloud
        cloudController.clouds.add(existingCloud)

        val newCloud = Cloud(-1, 5, tileId, 6000, 3)

        val tile = Mockito.mock(Tile::class.java)
        Mockito.`when`(tile.category).thenReturn(TileType.FIELD)
        Mockito.`when`(mockMapController.getTile(tileId)).thenReturn(tile)

        cloudController.createCloud(newCloud)

        assertFalse(cloudController.clouds.contains(existingCloud)) // old cloud no longer exist
    }

    @Test
    fun testMoveWithZeroDistance() {
        val tileId = 1
        val cloud = Cloud(1, 10, tileId, 6000, 0) // Distance 0
        cloudController.clouds.add(cloud)
        cloudController.cloudMap[tileId] = cloud
        Mockito.`when`(mockMapController.getNeighbourTile(any())).thenReturn(mockTile2)

        val resultCloud = cloudController.move(cloud)

        assertEquals(cloud, resultCloud, "Cloud should be returned unchanged.")
        if (resultCloud != null) {
            assertEquals(tileId, resultCloud.location, "Cloud location must remain the same.")
        }
    }

    @Test
    fun testMoveToNoNeighborTile() {
        val tileId = 1
        val cloud = Cloud(1, 10, tileId, 6000, 1)
        cloudController.clouds.add(cloud)
        cloudController.cloudMap[tileId] = cloud
        val originTile = Mockito.mock(Tile::class.java)
        Mockito.`when`(mockMapController.getTile(tileId)).thenReturn(originTile)
        Mockito.`when`(mockMapController.getNeighbourTile(eq(tileId))).thenReturn(null) // dead end

        val resultCloud = cloudController.move(cloud)

        assertEquals(cloud, resultCloud, "Cloud should be returned unchanged.")
        if (resultCloud != null) {
            assertEquals(tileId, resultCloud.location, "Cloud location must remain the same.")
        }
        if (resultCloud != null) { // just a safe check for future possible merging
            assertEquals(1, resultCloud.distance, "Distance should not be decremented.")
        }
    }

    @Test
    fun testUpdateOnTickDurationDecrement() {
        val tileId = 5
        val initialDuration = 5
        val cloud = Cloud(1, initialDuration, tileId, 3000, 1)
        cloudController.clouds.add(cloud)
        cloudController.processingClouds.add(cloud)
        val tile: Tile = Mockito.mock(Tile::class.java)
        Mockito.`when`(tile.id).thenReturn(tileId)
        Mockito.`when`(tile.category).thenReturn(TileType.FOREST)
        Mockito.`when`(tile.maxCapacity).thenReturn(null) // no dissipation
        Mockito.`when`(mockMapController.getTile(tileId)).thenReturn(tile)

        cloudController.updateOnTick()

        assertTrue(cloudController.clouds.contains(cloud), "Cloud must still exist.")
        assertEquals(initialDuration - 1, cloud.duration, "Duration should be decreased by 1.")
    }
}
