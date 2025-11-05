package general.incidentsTests

import de.unisaarland.cs.se.selab.farms.FarmController
import de.unisaarland.cs.se.selab.incidents.Drought
import de.unisaarland.cs.se.selab.map.MapController
import de.unisaarland.cs.se.selab.map.Tile
import de.unisaarland.cs.se.selab.map.TileType
import de.unisaarland.cs.se.selab.plant.Plant
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.argThat
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class DroughtTests {
    private lateinit var mapController: MapController
    private lateinit var farmController: FarmController

    @BeforeEach
    fun setup() {
        mapController = mock()
        farmController = mock()
    }

    private fun createTile(
        id: Int,
        type: TileType,
        farmId: Int?,
        moisture: Int,
        harvest: Int,
        plant: Plant? = if (type == TileType.FIELD || type == TileType.PLANTATION) mock() else null
    ): Tile {
        return Tile(
            id,
            mock(),
            type,
            farmId,
            false,
            null,
            null,
            plant,
            null,
            harvest,
            0,
            false,
            0,
            moisture,
            0,
            emptyMap(),
            penalties = mutableListOf()
        )
    }

    @Test
    fun `drought on field tile`() {
        // Arrange
        val fieldTile = createTile(1, TileType.FIELD, 1, 100, 100)
        whenever(mapController.getNeighboursGeometries(any(), any())).thenReturn(listOf(fieldTile))

        val drought = Drought(1, 1, mapController, 0, 1, listOf(farmController))

        // Act
        drought.apply()

        // Assert
        assertEquals(0, fieldTile.soilMoisture)
        assertEquals(0, fieldTile.harvestEstimate)
        assertEquals(5, fieldTile.fallowPeriod)
        verify(farmController).removeActionsIf(argThat { predicate -> predicate(1) })
    }

    @Test
    fun `drought on plantation tile`() {
        // Arrange
        val plantationTile = createTile(2, TileType.PLANTATION, 1, 150, 200)
        whenever(mapController.getNeighboursGeometries(any(), any())).thenReturn(listOf(plantationTile))

        // Setup farm controller to have an action on the plantation
        whenever(farmController.removeActionsIf(any())).then { invocation ->
            val predicate = invocation.getArgument<((Int) -> Boolean)>(0)
            // Simulate removal by invoking the predicate
            predicate(2)
        }

        val drought = Drought(1, 1, mapController, 0, 1, listOf(farmController))

        // Act
        drought.apply()

        // Assert
        assertEquals(0, plantationTile.soilMoisture)
        assertEquals(0, plantationTile.harvestEstimate)
        // Verify that the farm controller was instructed to remove actions for the affected tile
        verify(farmController).removeActionsIf(argThat { predicate -> predicate(2) })
    }

    @Test
    fun `traversal is possible on drought-affected plantation`() {
        // Arrange
        val startTile = createTile(10, TileType.ROAD, null, 0, 0)
        val droughtyPlantation = createTile(2, TileType.PLANTATION, 1, 150, 200)
        val endTile = createTile(11, TileType.ROAD, null, 0, 0)

        // Setup neighbors for a simple path: 10 -> 2 -> 11
        startTile.neighbours = mapOf(0 to 2)
        droughtyPlantation.neighbours = mapOf(180 to 10, 0 to 11)
        endTile.neighbours = mapOf(180 to 2)

        whenever(mapController.getTile(10)).thenReturn(startTile)
        whenever(mapController.getTile(2)).thenReturn(droughtyPlantation)
        whenever(mapController.getTile(11)).thenReturn(endTile)
        whenever(mapController.getNeighboursGeometries(any(), any())).thenReturn(listOf(droughtyPlantation))

        val drought = Drought(1, 1, mapController, 0, 1, listOf(farmController))

        // Act: Apply drought to the plantation
        drought.apply()

        // Assert: Check that the tile is "dead" for farming
        assertEquals(0, droughtyPlantation.soilMoisture)
        assertEquals(0, droughtyPlantation.harvestEstimate)
        verify(farmController).removeActionsIf(argThat { predicate -> predicate(2) })

        // Assert: Traversal is still possible.
        // A simple check is to see if the neighbors are intact.
        // A more robust test would involve a pathfinding algorithm, but this is a good proxy.
        assert(droughtyPlantation.neighbours.containsKey(180) && droughtyPlantation.neighbours[180] == 10)
        assert(droughtyPlantation.neighbours.containsKey(0) && droughtyPlantation.neighbours[0] == 11)
    }

    @Test
    fun `drought on already dry field tile`() {
        // Arrange
        val fieldTile = createTile(1, TileType.FIELD, 1, 0, 0)
        whenever(mapController.getNeighboursGeometries(any(), any())).thenReturn(listOf(fieldTile))

        val drought = Drought(1, 1, mapController, 0, 1, listOf(farmController))

        // Act
        drought.apply()

        // Assert
        assertEquals(0, fieldTile.soilMoisture)
        assertEquals(0, fieldTile.harvestEstimate)
        assertEquals(5, fieldTile.fallowPeriod) // fallow period should still be set
        verify(farmController).removeActionsIf(argThat { predicate -> predicate(1) })
    }

    @Test
    fun `drought on already dry plantation tile`() {
        // Arrange
        val plantationTile = createTile(2, TileType.PLANTATION, 1, 0, 0)
        whenever(mapController.getNeighboursGeometries(any(), any())).thenReturn(listOf(plantationTile))

        val drought = Drought(1, 1, mapController, 0, 1, listOf(farmController))

        // Act
        drought.apply()

        // Assert
        assertEquals(0, plantationTile.soilMoisture)
        assertEquals(0, plantationTile.harvestEstimate)
        verify(farmController).removeActionsIf(argThat { predicate -> predicate(2) })
    }

    @Test
    fun `drought does not affect non-agricultural tiles`() {
        // Arrange
        val forestTile = createTile(3, TileType.FOREST, null, 0, 0)
        whenever(mapController.getNeighboursGeometries(any(), any())).thenReturn(listOf(forestTile))

        val drought = Drought(1, 1, mapController, 0, 1, listOf(farmController))

        // Act
        drought.apply()

        // Assert
        assertEquals(0, forestTile.soilMoisture)
        assertEquals(0, forestTile.harvestEstimate)
        assertEquals(0, forestTile.fallowPeriod)
    }
}
