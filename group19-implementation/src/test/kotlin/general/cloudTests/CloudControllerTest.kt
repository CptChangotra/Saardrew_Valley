package general.cloudTests

import de.unisaarland.cs.se.selab.cloud.Cloud
import de.unisaarland.cs.se.selab.cloud.CloudController
import de.unisaarland.cs.se.selab.map.MapController
import de.unisaarland.cs.se.selab.map.Tile
import de.unisaarland.cs.se.selab.map.TileType
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito

class CloudControllerTest {
    val mockMapController: MapController = Mockito.mock(MapController::class.java)
    lateinit var cloudController: CloudController

    @BeforeEach
    fun setup() {
        cloudController = CloudController(mutableListOf(), mutableMapOf(), mockMapController)
    }

    @Test
    fun addingCloudsToMapAndList() {
        val cloud = Cloud(0, 2, 2, 3000, 4)
        val tile = Mockito.mock(Tile::class.java)
        Mockito.`when`(mockMapController.getTile(cloud.location)).thenReturn(tile)
        cloudController.maxId = 19
        cloudController.createCloud(cloud)
        Assertions.assertTrue(cloudController.clouds.contains(cloud))
        Assertions.assertTrue(cloudController.cloudMap[cloud.location] == cloud)
        Assertions.assertEquals(20, cloud.id)
        Assertions.assertEquals(20, cloudController.cloudMap[2]!!.id)
    }

    @Test
    fun correctIDAddedToMap() {
        val cloud = Cloud(45, 2, 2, 1500, 5)
        val tile = Mockito.mock(Tile::class.java)
        Mockito.`when`(mockMapController.getTile(cloud.location)).thenReturn(tile)
        cloudController.maxId = 67
        cloudController.createCloud(cloud)
        Assertions.assertEquals(68, cloud.id)
    }

    @Test
    fun correctIDWithMoreClouds() {
        val cloud1 = Cloud(45, 2, 2, 1500, 5)
        val cloud2 = Cloud(89, 5, 8, 5000, 7)
        val tile1 = Mockito.mock(Tile::class.java)
        val tile2 = Mockito.mock(Tile::class.java)
        Mockito.`when`(mockMapController.getTile(cloud1.location)).thenReturn(tile1)
        Mockito.`when`(mockMapController.getTile(cloud2.location)).thenReturn(tile2)
        cloudController.maxId = 15
        cloudController.createCloud(cloud1)
        Assertions.assertEquals(16, cloud1.id)
        cloudController.createCloud(cloud2)
        Assertions.assertEquals(17, cloud2.id)
    }

    @Test
    fun addingListOfClouds() {
        val cloud1 = Cloud(101, 3, 2, 1500, 1)
        val cloud2 = Cloud(217, 5, 8, 6000, 4)
        val cloud3 = Cloud(42, Cloud.INFINITE_CLOUD_DURATION, 15, 8000, 0)
        val cloud4 = Cloud(309, 0, 4, 3000, 2)
        val cloud5 = Cloud(78, 7, 20, 5000, 3)
        val cloud6 = Cloud(512, 2, 25, 1200, 1)
        val cloud7 = Cloud(999, 10, 30, 7000, 5)
        val cloud8 = Cloud(333, 1, 11, 4900, 2)
        val cloud9 = Cloud(6, 4, 16, 5200, 6)
        val cloud10 = Cloud(404, 9, 99, 2500, 0)

        val cloudsToBeAdded = mutableListOf(cloud1, cloud2, cloud3, cloud4, cloud5)
        cloudsToBeAdded += cloud6
        cloudsToBeAdded += cloud7
        cloudsToBeAdded += cloud8
        cloudsToBeAdded += cloud9
        cloudsToBeAdded += cloud10

        cloudsToBeAdded.forEach {
            val tile = Mockito.mock(Tile::class.java)
            Mockito.`when`(mockMapController.getTile(it.location)).thenReturn(tile)
        }

        cloudController.maxId = 37
        var tracker = cloudController.maxId

        cloudsToBeAdded.forEach { cloudController.createCloud(it) }
        for (cloud in cloudController.clouds) {
            Assertions.assertEquals(++tracker, cloud.id)
        }
        cloudController.clouds.forEach { Assertions.assertTrue(cloudController.cloudMap[it.location] == it) }
    }

    @Test
    fun mergingTwoClouds() {
        val t1: Tile = Mockito.mock(Tile::class.java)
        Mockito.`when`(t1.id).thenReturn(1)
        val t2 = Mockito.mock(Tile::class.java)
        Mockito.`when`(t2.id).thenReturn(9)
        val cloud1 = Cloud(7, 4, t1.id, 4000, 3)
        val cloud2 = Cloud(2, 2, t2.id, 3000, 5)
        cloudController.maxId = 19
        val mergedCloud = cloudController.merge(cloud1, cloud2)
        Assertions.assertEquals(20, mergedCloud.id)
        Assertions.assertEquals(2, mergedCloud.duration)
        Assertions.assertEquals(t1.id, mergedCloud.location)
        Assertions.assertEquals(7000, mergedCloud.amount)
        Assertions.assertEquals(5, mergedCloud.distance)
    }

    @Test
    fun mergingTwoCloudsWithEqualProperties() {
        val t1: Tile = Mockito.mock(Tile::class.java)
        Mockito.`when`(t1.id).thenReturn(1)
        val t2 = Mockito.mock(Tile::class.java)
        Mockito.`when`(t2.id).thenReturn(9)
        val cloud1 = Cloud(7, 4, t1.id, 4000, 3)
        val cloud2 = Cloud(2, 4, t2.id, 4000, 3)
        cloudController.maxId = 19
        val mergedCloud = cloudController.merge(cloud1, cloud2)
        Assertions.assertEquals(20, mergedCloud.id)
        Assertions.assertEquals(4, mergedCloud.duration)
        Assertions.assertEquals(t1.id, mergedCloud.location)
        Assertions.assertEquals(8000, mergedCloud.amount)
        Assertions.assertEquals(3, mergedCloud.distance)
    }

    @Test
    fun mergingOneEmptyCloud() {
        val t1: Tile = Mockito.mock(Tile::class.java)
        Mockito.`when`(t1.id).thenReturn(1)
        val t2 = Mockito.mock(Tile::class.java)
        Mockito.`when`(t2.id).thenReturn(9)
        val cloud1 = Cloud(7, 4, t1.id, 4000, 3)
        val cloud2 = Cloud(2, 4, t2.id, 0, 3)
        cloudController.maxId = 19
        val ex = assertThrows<IllegalArgumentException> { cloudController.merge(cloud1, cloud2) }
        Assertions.assertEquals("A dissipated cloud cannot be merged", ex.message)
    }

    @Test
    fun mergingTwoEmptyClouds() {
        val t1: Tile = Mockito.mock(Tile::class.java)
        Mockito.`when`(t1.id).thenReturn(1)
        val t2 = Mockito.mock(Tile::class.java)
        Mockito.`when`(t2.id).thenReturn(9)
        val cloud1 = Cloud(7, 4, t1.id, 0, 3)
        val cloud2 = Cloud(2, 4, t2.id, 0, 3)
        cloudController.maxId = 19
        val ex = assertThrows<IllegalArgumentException> { cloudController.merge(cloud1, cloud2) }
        Assertions.assertEquals("A dissipated cloud cannot be merged", ex.message)
    }

    @Test
    fun createCloudOnVillageTile() {
        val cloud = Cloud(-1, 5, 10, 2000, 3)
        val villageTile = Mockito.mock(Tile::class.java)
        Mockito.`when`(villageTile.category).thenReturn(TileType.VILLAGE)
        Mockito.`when`(mockMapController.getTile(cloud.location)).thenReturn(villageTile)

        cloudController.maxId = 50
        cloudController.createCloud(cloud)

        // Assert that the cloud was not added to the active lists
        Assertions.assertFalse(cloudController.clouds.contains(cloud))
        Assertions.assertFalse(cloudController.cloudMap.containsKey(cloud.location))

        // Assert that the ID was still assigned before dissipation
        Assertions.assertEquals(51, cloud.id)
        Assertions.assertEquals(51, cloudController.maxId)
    }

    @Test
    fun testRainWithAmountBelowRainable() {
        val cloud = Cloud(1, 3, 5, 4999, 2) // Amount below 5000
        val tile = Mockito.mock(Tile::class.java)
        Mockito.`when`(mockMapController.getTile(cloud.location)).thenReturn(tile)

        cloudController.clouds.add(cloud)
        cloudController.rain(cloud)

        // Cloud should still exist and amount unchanged
        Assertions.assertTrue(cloudController.clouds.contains(cloud))
        Assertions.assertEquals(4999, cloud.amount)
    }

    @Test
    fun testRainOnTileWithoutCapacity() {
        val cloud = Cloud(1, 3, 5, 6000, 2)
        val tile = Mockito.mock(Tile::class.java)
        Mockito.`when`(tile.maxCapacity).thenReturn(null)
        Mockito.`when`(tile.category).thenReturn(TileType.ROAD) // Non-farmable tile
        Mockito.`when`(mockMapController.getTile(cloud.location)).thenReturn(tile)

        cloudController.clouds.add(cloud)
        cloudController.cloudMap[cloud.location] = cloud
        cloudController.rain(cloud)

        // Cloud should be dissipated
        Assertions.assertFalse(cloudController.clouds.contains(cloud))
        Assertions.assertFalse(cloudController.cloudMap.containsKey(cloud.location))
    }

    @Test
    fun testRainOnTileAtMaxCapacity() {
        val cloud = Cloud(1, 3, 5, 6000, 2)
        val tile = Mockito.mock(Tile::class.java)
        Mockito.`when`(tile.maxCapacity).thenReturn(1000)
        Mockito.`when`(tile.soilMoisture).thenReturn(1000) // Already at max
        Mockito.`when`(mockMapController.getTile(cloud.location)).thenReturn(tile)

        cloudController.clouds.add(cloud)
        cloudController.rain(cloud)

        // Cloud should still exist and amount unchanged
        Assertions.assertTrue(cloudController.clouds.contains(cloud))
        Assertions.assertEquals(6000, cloud.amount)
    }

    @Test
    fun testRainCloudFullyConsumed() {
        val cloud = Cloud(1, 3, 5, 5000, 2)
        val tile = Mockito.mock(Tile::class.java)
        Mockito.`when`(tile.maxCapacity).thenReturn(8000)
        Mockito.`when`(tile.soilMoisture).thenReturn(2000)
        Mockito.`when`(tile.category).thenReturn(TileType.FIELD) // Add category for dissipate method
        Mockito.`when`(mockMapController.getTile(cloud.location)).thenReturn(tile)

        cloudController.clouds.add(cloud)
        cloudController.cloudMap[cloud.location] = cloud
        cloudController.rain(cloud)

        // Cloud should be dissipated
        Assertions.assertFalse(cloudController.clouds.contains(cloud))
        Assertions.assertFalse(cloudController.cloudMap.containsKey(cloud.location))
        // Verify soil moisture updated
        Mockito.verify(tile).soilMoisture = 7000
    }

    @Test
    fun testRainPartialConsumption() {
        val cloud = Cloud(1, 3, 5, 8000, 2)
        val tile = Mockito.mock(Tile::class.java)
        Mockito.`when`(tile.maxCapacity).thenReturn(5000)
        Mockito.`when`(tile.soilMoisture).thenReturn(2000)
        Mockito.`when`(mockMapController.getTile(cloud.location)).thenReturn(tile)

        cloudController.clouds.add(cloud)
        cloudController.rain(cloud)

        // Cloud should still exist with reduced amount
        Assertions.assertTrue(cloudController.clouds.contains(cloud))
        Assertions.assertEquals(5000, cloud.amount) // 8000 - 3000 (rain needed)
        // Verify soil moisture set to max capacity
        Mockito.verify(tile).soilMoisture = 5000
    }

    @Test
    fun testDissipateOnVillage() {
        val cloud = Cloud(1, 3, 5, 6000, 2)
        val villageTile = Mockito.mock(Tile::class.java)
        Mockito.`when`(villageTile.category).thenReturn(TileType.VILLAGE)
        Mockito.`when`(mockMapController.getTile(cloud.location)).thenReturn(villageTile)

        cloudController.clouds.add(cloud)
        cloudController.cloudMap[cloud.location] = cloud
        cloudController.processingClouds.add(cloud)

        cloudController.dissipate(cloud)

        // Cloud should be removed from all collections
        Assertions.assertFalse(cloudController.clouds.contains(cloud))
        Assertions.assertFalse(cloudController.cloudMap.containsKey(cloud.location))
        Assertions.assertFalse(cloudController.processingClouds.contains(cloud))
    }

    @Test
    fun testDissipateOnNonVillage() {
        val cloud = Cloud(1, 3, 5, 6000, 2)
        val fieldTile = Mockito.mock(Tile::class.java)
        Mockito.`when`(fieldTile.category).thenReturn(TileType.FIELD)
        Mockito.`when`(mockMapController.getTile(cloud.location)).thenReturn(fieldTile)

        cloudController.clouds.add(cloud)
        cloudController.cloudMap[cloud.location] = cloud
        cloudController.processingClouds.add(cloud)

        cloudController.dissipate(cloud)

        // Cloud should be removed from all collections
        Assertions.assertFalse(cloudController.clouds.contains(cloud))
        Assertions.assertFalse(cloudController.cloudMap.containsKey(cloud.location))
        Assertions.assertFalse(cloudController.processingClouds.contains(cloud))
    }

    @Test
    fun testMoveCloudWithZeroDuration() {
        val cloud = Cloud(1, 0, 5, 6000, 2)
        val tile = Mockito.mock(Tile::class.java)
        Mockito.`when`(tile.category).thenReturn(TileType.FIELD) // Add category for dissipate method
        Mockito.`when`(mockMapController.getTile(cloud.location)).thenReturn(tile)

        cloudController.clouds.add(cloud)
        cloudController.cloudMap[cloud.location] = cloud

        val result = cloudController.move(cloud)

        // Should return null as cloud gets dissipated
        Assertions.assertNull(result)
        Assertions.assertFalse(cloudController.clouds.contains(cloud))
    }

    @Test
    fun testMoveCloudWithNoMovesLeft() {
        val cloud = Cloud(1, 3, 5, 6000, 0) // distance = 0

        val result = cloudController.move(cloud)

        // Should return the cloud unchanged
        Assertions.assertEquals(cloud, result)
    }

    @Test
    fun testMoveCloudNoValidNeighbor() {
        val cloud = Cloud(1, 3, 5, 6000, 2)
        val tile = Mockito.mock(Tile::class.java)
        Mockito.`when`(tile.amountSunlight).thenReturn(100)
        Mockito.`when`(mockMapController.getTile(cloud.location)).thenReturn(tile)
        Mockito.`when`(mockMapController.getNeighbourTile(cloud.location)).thenReturn(null)

        cloudController.clouds.add(cloud)

        val result = cloudController.move(cloud)

        // Should return the cloud unchanged
        Assertions.assertEquals(cloud, result)
    }

    @Test
    fun testMoveCloudSimpleMovement() {
        val cloud = Cloud(1, 3, 5, 6000, 2)
        val originTile = Mockito.mock(Tile::class.java)
        val neighborTile = Mockito.mock(Tile::class.java)

        Mockito.`when`(originTile.id).thenReturn(5)
        Mockito.`when`(originTile.amountSunlight).thenReturn(100)
        Mockito.`when`(originTile.category).thenReturn(TileType.FIELD)
        Mockito.`when`(neighborTile.id).thenReturn(10)
        Mockito.`when`(neighborTile.category).thenReturn(TileType.FIELD)

        Mockito.`when`(mockMapController.getTile(5)).thenReturn(originTile)
        Mockito.`when`(mockMapController.getTile(10)).thenReturn(neighborTile)
        Mockito.`when`(mockMapController.getNeighbourTile(5)).thenReturn(neighborTile)

        cloudController.clouds.add(cloud)
        cloudController.cloudMap[5] = cloud

        val result = cloudController.move(cloud)

        // Cloud should have moved to new location
        Assertions.assertEquals(10, result?.location)
        Assertions.assertEquals(1, result?.distance) // distance decremented
        Assertions.assertEquals(cloud, cloudController.cloudMap[10])
        Assertions.assertFalse(cloudController.cloudMap.containsKey(5))
    }

    @Test
    fun testMoveCloudToVillage() {
        val cloud = Cloud(1, 3, 5, 6000, 2)
        val originTile = Mockito.mock(Tile::class.java)
        val villageTile = Mockito.mock(Tile::class.java)

        Mockito.`when`(originTile.id).thenReturn(5)
        Mockito.`when`(originTile.amountSunlight).thenReturn(100)
        Mockito.`when`(originTile.category).thenReturn(TileType.FIELD)
        Mockito.`when`(villageTile.id).thenReturn(10)
        Mockito.`when`(villageTile.category).thenReturn(TileType.VILLAGE)

        Mockito.`when`(mockMapController.getTile(5)).thenReturn(originTile)
        Mockito.`when`(mockMapController.getTile(10)).thenReturn(villageTile)
        Mockito.`when`(mockMapController.getNeighbourTile(5)).thenReturn(villageTile)

        cloudController.clouds.add(cloud)
        cloudController.cloudMap[5] = cloud

        val result = cloudController.move(cloud)

        // Cloud should be dissipated
        Assertions.assertNull(result)
        Assertions.assertFalse(cloudController.clouds.contains(cloud))
    }

    @Test
    fun testMoveCloudWithMerge() {
        val movingCloud = Cloud(1, 3, 5, 3000, 2)
        val stationaryCloud = Cloud(2, 4, 10, 4000, 1)

        val originTile = Mockito.mock(Tile::class.java)
        val neighborTile = Mockito.mock(Tile::class.java)

        Mockito.`when`(originTile.id).thenReturn(5)
        Mockito.`when`(originTile.amountSunlight).thenReturn(100)
        Mockito.`when`(originTile.category).thenReturn(TileType.FIELD)
        Mockito.`when`(neighborTile.id).thenReturn(10)
        Mockito.`when`(neighborTile.category).thenReturn(TileType.FIELD)

        Mockito.`when`(mockMapController.getTile(5)).thenReturn(originTile)
        Mockito.`when`(mockMapController.getTile(10)).thenReturn(neighborTile)
        Mockito.`when`(mockMapController.getNeighbourTile(5)).thenReturn(neighborTile)

        cloudController.clouds.addAll(listOf(movingCloud, stationaryCloud))
        cloudController.cloudMap[5] = movingCloud
        cloudController.cloudMap[10] = stationaryCloud
        cloudController.maxId = 2

        val result = cloudController.move(movingCloud)

        // Should return null as clouds were merged
        Assertions.assertNull(result)
        // Original clouds should be removed
        Assertions.assertFalse(cloudController.clouds.contains(movingCloud))
        Assertions.assertFalse(cloudController.clouds.contains(stationaryCloud))
        // New merged cloud should exist
        Assertions.assertEquals(1, cloudController.clouds.size)
        val mergedCloud = cloudController.clouds.first()
        Assertions.assertEquals(7000, mergedCloud.amount)
    }

    @Test
    fun testUpdateOnTickWithMultipleClouds() {
        val cloud1 = Cloud(1, 2, 5, 6000, 2)
        val cloud2 = Cloud(2, 3, 10, 7000, 1)

        val tile1 = Mockito.mock(Tile::class.java)
        val tile2 = Mockito.mock(Tile::class.java)

        Mockito.`when`(tile1.id).thenReturn(5)
        Mockito.`when`(tile1.category).thenReturn(TileType.FIELD)
        Mockito.`when`(tile1.amountSunlight).thenReturn(100)
        Mockito.`when`(tile2.id).thenReturn(10)
        Mockito.`when`(tile2.category).thenReturn(TileType.PLANTATION)
        Mockito.`when`(tile2.amountSunlight).thenReturn(120)

        Mockito.`when`(mockMapController.getTile(5)).thenReturn(tile1)
        Mockito.`when`(mockMapController.getTile(10)).thenReturn(tile2)
        Mockito.`when`(mockMapController.getNeighbourTile(5)).thenReturn(null)
        Mockito.`when`(mockMapController.getNeighbourTile(10)).thenReturn(null)

        cloudController.clouds.addAll(listOf(cloud1, cloud2))
        cloudController.cloudMap[5] = cloud1
        cloudController.cloudMap[10] = cloud2

        cloudController.updateOnTick()

        // Verify sunlight reduction at end of tick
        Mockito.verify(tile1).amountSunlight = 50 // 100 - 50
        Mockito.verify(tile2).amountSunlight = 70 // 120 - 50

        // Verify distances reset
        Assertions.assertEquals(10, cloud1.distance)
        Assertions.assertEquals(10, cloud2.distance)

        // Verify cloud phase is over
        Assertions.assertTrue(cloudController.isCloudPhaseOver)
    }

    @Test
    fun testUpdateOnTickWithCloudDissipation() {
        val cloud = Cloud(1, 1, 5, 6000, 2) // duration = 1, will become 0
        val tile = Mockito.mock(Tile::class.java)

        Mockito.`when`(tile.id).thenReturn(5)
        Mockito.`when`(tile.category).thenReturn(TileType.FIELD)
        Mockito.`when`(tile.amountSunlight).thenReturn(100)

        Mockito.`when`(mockMapController.getTile(5)).thenReturn(tile)
        Mockito.`when`(mockMapController.getNeighbourTile(5)).thenReturn(null)

        cloudController.clouds.add(cloud)
        cloudController.cloudMap[5] = cloud

        cloudController.updateOnTick()

        // Cloud should be dissipated due to duration reaching 0
        Assertions.assertFalse(cloudController.clouds.contains(cloud))
        Assertions.assertFalse(cloudController.cloudMap.containsKey(5))
    }

    @Test
    fun testUpdateOnTickWithInfiniteCloudDuration() {
        val cloud = Cloud(1, Cloud.INFINITE_CLOUD_DURATION, 5, 6000, 2)
        val tile = Mockito.mock(Tile::class.java)

        Mockito.`when`(tile.id).thenReturn(5)
        Mockito.`when`(tile.category).thenReturn(TileType.FIELD)
        Mockito.`when`(tile.amountSunlight).thenReturn(100)

        Mockito.`when`(mockMapController.getTile(5)).thenReturn(tile)
        Mockito.`when`(mockMapController.getNeighbourTile(5)).thenReturn(null)

        cloudController.clouds.add(cloud)
        cloudController.cloudMap[5] = cloud

        cloudController.updateOnTick()

        // Cloud should still exist (infinite duration)
        Assertions.assertTrue(cloudController.clouds.contains(cloud))
        Assertions.assertEquals(Cloud.INFINITE_CLOUD_DURATION, cloud.duration)
    }

    @Test
    fun testCreateCloudWithExistingCloud() {
        val existingCloud = Cloud(1, 3, 5, 4000, 2)
        val newCloud = Cloud(-1, 2, 5, 3000, 1)
        val tile = Mockito.mock(Tile::class.java)

        Mockito.`when`(tile.category).thenReturn(TileType.FIELD)
        Mockito.`when`(mockMapController.getTile(5)).thenReturn(tile)

        cloudController.clouds.add(existingCloud)
        cloudController.cloudMap[5] = existingCloud
        cloudController.maxId = 1

        cloudController.createCloud(newCloud)

        // Should result in a merged cloud - the existing cloud is removed and new merged cloud added
        Assertions.assertEquals(1, cloudController.clouds.size)
        val mergedCloud = cloudController.clouds.first()
        Assertions.assertEquals(7000, mergedCloud.amount) // 4000 + 3000
        Assertions.assertEquals(3, mergedCloud.id)
        // new ID assigned (maxId gets incremented twice: once for newCloud, once for merge)
        // Verify the original clouds are no longer in the system
        Assertions.assertFalse(cloudController.clouds.contains(existingCloud))
        Assertions.assertFalse(cloudController.clouds.contains(newCloud))
    }

    @Test
    fun testCreateCloudDuringCloudPhase() {
        val cloud = Cloud(-1, 3, 5, 6000, 2)
        val tile = Mockito.mock(Tile::class.java)

        Mockito.`when`(tile.category).thenReturn(TileType.FIELD)
        Mockito.`when`(mockMapController.getTile(5)).thenReturn(tile)

        cloudController.isCloudPhaseOver = false
        cloudController.maxId = 10

        cloudController.createCloud(cloud)

        // Cloud should be added to processing queue
        Assertions.assertTrue(cloudController.processingClouds.contains(cloud))
        Assertions.assertTrue(cloudController.clouds.contains(cloud))
    }

    @Test
    fun testCreateCloudAfterCloudPhase() {
        val cloud = Cloud(-1, 3, 5, 6000, 2)
        val tile = Mockito.mock(Tile::class.java)

        Mockito.`when`(tile.category).thenReturn(TileType.FIELD)
        Mockito.`when`(mockMapController.getTile(5)).thenReturn(tile)

        cloudController.isCloudPhaseOver = true
        cloudController.maxId = 10

        cloudController.createCloud(cloud)

        // Cloud should not be added to processing queue
        Assertions.assertFalse(cloudController.processingClouds.contains(cloud))
        Assertions.assertTrue(cloudController.clouds.contains(cloud))
    }

    @Test
    fun testMergeWithInfiniteDurationClouds() {
        val cloud1 = Cloud(1, Cloud.INFINITE_CLOUD_DURATION, 5, 3000, 2)
        val cloud2 = Cloud(2, 4, 5, 4000, 3)

        cloudController.maxId = 2

        val mergedCloud = cloudController.merge(cloud1, cloud2)

        // Duration should be the finite one (4)
        Assertions.assertEquals(4, mergedCloud.duration)
        Assertions.assertEquals(7000, mergedCloud.amount)
        Assertions.assertEquals(3, mergedCloud.distance) // max of 2 and 3
    }

    @Test
    fun testMergeWithBothInfiniteDuration() {
        val cloud1 = Cloud(1, Cloud.INFINITE_CLOUD_DURATION, 5, 3000, 2)
        val cloud2 = Cloud(2, Cloud.INFINITE_CLOUD_DURATION, 5, 4000, 3)

        cloudController.maxId = 2

        val mergedCloud = cloudController.merge(cloud1, cloud2)

        // Duration should remain infinite
        Assertions.assertEquals(Cloud.INFINITE_CLOUD_DURATION, mergedCloud.duration)
        Assertions.assertEquals(7000, mergedCloud.amount)
        Assertions.assertEquals(3, mergedCloud.distance)
    }

    @Test
    fun testMaxIdInitializationWithExistingClouds() {
        val cloud1 = Cloud(5, 3, 10, 4000, 2)
        val cloud2 = Cloud(8, 2, 15, 3000, 1)
        val clouds = mutableListOf(cloud1, cloud2)

        val controller = CloudController(clouds, mutableMapOf(), mockMapController)

        // maxId should be initialized to the highest existing ID
        Assertions.assertEquals(8, controller.maxId)
    }

    @Test
    fun testMaxIdInitializationWithEmptyList() {
        val controller = CloudController(mutableListOf(), mutableMapOf(), mockMapController)

        // maxId should be initialized to -1 for empty list
        Assertions.assertEquals(-1, controller.maxId)
    }
}
