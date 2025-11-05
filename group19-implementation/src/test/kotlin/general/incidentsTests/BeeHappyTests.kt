package general.incidentsTests

import de.unisaarland.cs.se.selab.incidents.BeeHappy
import de.unisaarland.cs.se.selab.map.Coordinate
import de.unisaarland.cs.se.selab.map.MapController
import de.unisaarland.cs.se.selab.map.Reason
import de.unisaarland.cs.se.selab.map.SimulationMap
import de.unisaarland.cs.se.selab.map.Tile
import de.unisaarland.cs.se.selab.map.TileType
import de.unisaarland.cs.se.selab.plant.FieldPlant
import de.unisaarland.cs.se.selab.plant.PlantationPlant
import de.unisaarland.cs.se.selab.utils.Tick
import kotlin.test.Test
import kotlin.test.assertEquals

class BeeHappyTests {

    private fun createTile(
        id: Int,
        x: Int,
        y: Int,
        type: TileType,
        plant: de.unisaarland.cs.se.selab.plant.Plant? = null
    ): Tile {
        return Tile(
            id = id,
            coordinate = Coordinate(x, y),
            category = type,
            farm = null,
            airflow = false,
            direction = null,
            maxCapacity = null,
            plant = plant,
            possiblePlants = null,
            harvestEstimate = plant?.harvestEstimate() ?: 0,
            startOfTickEstimate = if (plant != null) 1 else 0,
            shed = false,
            fallowPeriod = null,
            soilMoisture = 500,
            amountSunlight = 150,
            neighbours = emptyMap(),
            penalties = mutableListOf()
        )
    }

    @Test
    fun `meadow near blooming pumpkin`() {
        Tick.currentTick = 20 // Use currentTick for pumpkins
        val pumpkin = FieldPlant.PUMPKIN
        pumpkin.lastSowingTick = Tick.currentTick - 3 // Make it bloom (within range 2-4 ticks after sowing)

        val meadow = createTile(0, 0, 0, TileType.MEADOW)
        val pumpkinTile = createTile(1, 1, 1, TileType.FIELD, pumpkin)

        val tiles = mapOf(
            meadow.id to meadow,
            pumpkinTile.id to pumpkinTile
        )
        val simulationMap = SimulationMap(tiles)
        val mapController = MapController(simulationMap)

        val incident = BeeHappy(1, 1, mapController, meadow.id, 3, 10, true) // Increase radius
        incident.apply()

        // Check if BEE_HAPPY penalty (bonus) was added
        val hasBeePenalty = pumpkinTile.penalties.any {
            it.second == Reason.BEE_HAPPY && it.first == 110 // 10 + 100
        }
        assertEquals(true, hasBeePenalty, "Expected BEE_HAPPY penalty to be added to pumpkin")
    }

    @Test
    fun `meadow near blooming apple`() {
        Tick.yTick = 8 // Use APRIL_2 (8) which is within apple's blooming period (8-9)
        Tick.currentTick = 8
        val apple = PlantationPlant.APPLE

        val meadow = createTile(0, 0, 0, TileType.MEADOW)
        val appleTile = createTile(1, 1, 1, TileType.PLANTATION, apple)

        val tiles = mapOf(
            meadow.id to meadow,
            appleTile.id to appleTile
        )
        val simulationMap = SimulationMap(tiles)
        val mapController = MapController(simulationMap)

        val incident = BeeHappy(1, 1, mapController, meadow.id, 3, 10, true) // Increase radius
        incident.apply()

        // Check if BEE_HAPPY penalty (bonus) was added
        val hasBeePenalty = appleTile.penalties.any {
            it.second == Reason.BEE_HAPPY && it.first == 110 // 10 + 100
        }
        assertEquals(true, hasBeePenalty, "Expected BEE_HAPPY penalty to be added to apple")
    }

    @Test
    fun `wrong plant type`() {
        Tick.currentTick = 20
        val wheat = FieldPlant.WHEAT

        val meadow = createTile(0, 0, 0, TileType.MEADOW)
        val wheatTile = createTile(1, 1, 1, TileType.FIELD, wheat)

        val tiles = mapOf(
            meadow.id to meadow,
            wheatTile.id to wheatTile
        )
        val simulationMap = SimulationMap(tiles)
        val mapController = MapController(simulationMap)

        val incident = BeeHappy(1, 1, mapController, meadow.id, 3, 10, true)
        incident.apply()

        // Wheat should not be affected by BeeHappy
        val hasBeePenalty = wheatTile.penalties.any { it.second == Reason.BEE_HAPPY }
        assertEquals(false, hasBeePenalty, "Wheat should not be affected by BEE_HAPPY")
    }

    @Test
    fun `wrong timing for pumpkin`() {
        Tick.currentTick = 20
        val pumpkin = FieldPlant.PUMPKIN
        pumpkin.lastSowingTick = Tick.currentTick - 1 // Not in blooming range (needs to be 2-4 ticks)

        val meadow = createTile(0, 0, 0, TileType.MEADOW)
        val pumpkinTile = createTile(1, 1, 1, TileType.FIELD, pumpkin)

        val tiles = mapOf(
            meadow.id to meadow,
            pumpkinTile.id to pumpkinTile
        )
        val simulationMap = SimulationMap(tiles)
        val mapController = MapController(simulationMap)

        val incident = BeeHappy(1, 1, mapController, meadow.id, 3, 10, true)
        incident.apply()

        // Should not be affected due to wrong timing
        val hasBeePenalty = pumpkinTile.penalties.any { it.second == Reason.BEE_HAPPY }
        assertEquals(false, hasBeePenalty, "Pumpkin should not be affected due to wrong timing")
    }

    @Test
    fun `too far away`() {
        Tick.currentTick = 20
        val pumpkin = FieldPlant.PUMPKIN
        pumpkin.lastSowingTick = Tick.currentTick - 3 // Make it bloom

        val meadow = createTile(0, 0, 0, TileType.MEADOW)
        val pumpkinTile = createTile(1, 0, 10, TileType.FIELD, pumpkin) // Place very far away

        val tiles = mapOf(
            meadow.id to meadow,
            pumpkinTile.id to pumpkinTile
        )
        val simulationMap = SimulationMap(tiles)
        val mapController = MapController(simulationMap)

        val incident = BeeHappy(1, 1, mapController, meadow.id, 1, 10, true) // Small radius
        incident.apply()

        // Should not be affected due to distance
        val hasBeePenalty = pumpkinTile.penalties.any { it.second == Reason.BEE_HAPPY }
        assertEquals(false, hasBeePenalty, "Pumpkin should not be affected due to distance")
    }

    @Test
    fun `multiple meadows same incident`() {
        Tick.currentTick = 20
        val pumpkin = FieldPlant.PUMPKIN
        pumpkin.lastSowingTick = Tick.currentTick - 3 // Make it bloom (within range 2-4 ticks after sowing)

        val meadow1 = createTile(0, 0, 0, TileType.MEADOW)
        val meadow2 = createTile(1, 2, 0, TileType.MEADOW) // Close to meadow1
        val pumpkinTile = createTile(2, 1, 0, TileType.FIELD, pumpkin) // Between both meadows

        val tiles = mapOf(
            meadow1.id to meadow1,
            meadow2.id to meadow2,
            pumpkinTile.id to pumpkinTile
        )
        val simulationMap = SimulationMap(tiles)
        val mapController = MapController(simulationMap)

        val incident = BeeHappy(1, 1, mapController, meadow1.id, 5, 10, true) // Large radius to include both meadows
        incident.apply()

        // Check if BEE_HAPPY penalty (bonus) was added only once
        val beePenalties = pumpkinTile.penalties.filter { it.second == Reason.BEE_HAPPY }
        assertEquals(1, beePenalties.size, "Expected exactly one BEE_HAPPY penalty, even with multiple meadows")
        assertEquals(110, beePenalties.first().first, "Expected penalty value to be 110 (10 + 100)")
    }

    @Test
    fun `test getLocation getter`() {
        val tiles = emptyMap<Int, Tile>()
        val simulationMap = SimulationMap(tiles)
        val mapController = MapController(simulationMap)

        val incident = BeeHappy(1, 1, mapController, 42, 3, 10, true)
        assertEquals(42, incident.location, "getLocation should return the location")
    }

    @Test
    fun `test getRadius getter`() {
        val tiles = emptyMap<Int, Tile>()
        val simulationMap = SimulationMap(tiles)
        val mapController = MapController(simulationMap)

        val incident = BeeHappy(1, 1, mapController, 1, 15, 10, true)
        assertEquals(15, incident.radius, "getRadius should return the radius")
    }

    @Test
    fun `test getEffect getter`() {
        val tiles = emptyMap<Int, Tile>()
        val simulationMap = SimulationMap(tiles)
        val mapController = MapController(simulationMap)

        val incident = BeeHappy(1, 1, mapController, 1, 3, 25, true)
        assertEquals(25, incident.effect, "getEffect should return the effect")
    }

    @Test
    fun `test isFirst getter`() {
        val tiles = emptyMap<Int, Tile>()
        val simulationMap = SimulationMap(tiles)
        val mapController = MapController(simulationMap)

        val incident1 = BeeHappy(1, 1, mapController, 1, 3, 10, true)
        assertEquals(true, incident1.isFirst, "isFirst should return true when set to true")

        val incident2 = BeeHappy(2, 1, mapController, 1, 3, 10, false)
        assertEquals(false, incident2.isFirst, "isFirst should return false when set to false")
    }

    @Test
    fun `no meadows in radius returns early`() {
        Tick.currentTick = 20
        val pumpkin = FieldPlant.PUMPKIN
        pumpkin.lastSowingTick = Tick.currentTick - 3

        val forestTile = createTile(0, 0, 0, TileType.FOREST) // No meadow
        val pumpkinTile = createTile(1, 1, 1, TileType.FIELD, pumpkin)

        val tiles = mapOf(
            forestTile.id to forestTile,
            pumpkinTile.id to pumpkinTile
        )
        val simulationMap = SimulationMap(tiles)
        val mapController = MapController(simulationMap)

        val incident = BeeHappy(1, 1, mapController, forestTile.id, 3, 10, true)
        incident.apply()

        // Should not be affected since no meadows in radius
        val hasBeePenalty = pumpkinTile.penalties.any { it.second == Reason.BEE_HAPPY }
        assertEquals(false, hasBeePenalty, "Should not affect plants when no meadows in radius")
    }

    @Test
    fun `potato blooming test`() {
        Tick.currentTick = 20
        val potato = FieldPlant.POTATO
        potato.lastSowingTick = Tick.currentTick - 4 // 4 ticks after sowing (should trigger)

        val meadow = createTile(0, 0, 0, TileType.MEADOW)
        val potatoTile = createTile(1, 1, 1, TileType.FIELD, potato)

        val tiles = mapOf(
            meadow.id to meadow,
            potatoTile.id to potatoTile
        )
        val simulationMap = SimulationMap(tiles)
        val mapController = MapController(simulationMap)

        val incident = BeeHappy(1, 1, mapController, meadow.id, 3, 10, true)
        incident.apply()

        // For potato, only the ID should be logged, no penalty added
        val hasBeePenalty = potatoTile.penalties.any { it.second == Reason.BEE_HAPPY }
        assertEquals(false, hasBeePenalty, "Potato should not get penalty, only be logged")
    }

    @Test
    fun `grape plantation not affected`() {
        Tick.yTick = 8 // Valid blooming period
        Tick.currentTick = 8
        val grape = PlantationPlant.GRAPE

        val meadow = createTile(0, 0, 0, TileType.MEADOW)
        val grapeTile = createTile(1, 1, 1, TileType.PLANTATION, grape)

        val tiles = mapOf(
            meadow.id to meadow,
            grapeTile.id to grapeTile
        )
        val simulationMap = SimulationMap(tiles)
        val mapController = MapController(simulationMap)

        val incident = BeeHappy(1, 1, mapController, meadow.id, 3, 10, true)
        incident.apply()

        // Grape should not be affected by BeeHappy
        val hasBeePenalty = grapeTile.penalties.any { it.second == Reason.BEE_HAPPY }
        assertEquals(false, hasBeePenalty, "Grape plantation should not be affected by BEE_HAPPY")
    }

    @Test
    fun `tile with null plant is skipped`() {
        Tick.currentTick = 20

        val meadow = createTile(0, 0, 0, TileType.MEADOW)
        val emptyTile = createTile(1, 1, 1, TileType.FIELD, null) // No plant

        val tiles = mapOf(
            meadow.id to meadow,
            emptyTile.id to emptyTile
        )
        val simulationMap = SimulationMap(tiles)
        val mapController = MapController(simulationMap)

        val incident = BeeHappy(1, 1, mapController, meadow.id, 3, 10, true)
        incident.apply()

        // Should not crash and no penalties should be added
        assertEquals(0, emptyTile.penalties.size, "Tile with null plant should not get penalties")
    }

    @Test
    fun `tile with zero startOfTickEstimate is filtered out`() {
        Tick.currentTick = 20
        val pumpkin = FieldPlant.PUMPKIN
        pumpkin.lastSowingTick = Tick.currentTick - 3

        val meadow = createTile(0, 0, 0, TileType.MEADOW)
        val pumpkinTile = createTile(
            id = 1,
            x = 1,
            y = 1,
            type = TileType.FIELD,
            plant = pumpkin
        )
        // Manually set startOfTickEstimate to 0 to simulate filtered condition
        pumpkinTile.startOfTickEstimate = 0

        val tiles = mapOf(
            meadow.id to meadow,
            pumpkinTile.id to pumpkinTile
        )
        val simulationMap = SimulationMap(tiles)
        val mapController = MapController(simulationMap)

        val incident = BeeHappy(1, 1, mapController, meadow.id, 3, 10, true)
        incident.apply()

        // Should not be affected due to startOfTickEstimate being 0
        val hasBeePenalty = pumpkinTile.penalties.any { it.second == Reason.BEE_HAPPY }
        assertEquals(false, hasBeePenalty, "Tile with zero startOfTickEstimate should be filtered out")
    }
}
