package general.farmTests

import de.unisaarland.cs.se.selab.farms.Action
import de.unisaarland.cs.se.selab.farms.CuttingAction
import de.unisaarland.cs.se.selab.farms.HarvestAction
import de.unisaarland.cs.se.selab.farms.Machine
import de.unisaarland.cs.se.selab.farms.MowingAction
import de.unisaarland.cs.se.selab.map.Coordinate
import de.unisaarland.cs.se.selab.map.Tile
import de.unisaarland.cs.se.selab.map.TileType
import de.unisaarland.cs.se.selab.plant.FieldPlant
import de.unisaarland.cs.se.selab.plant.PlantationPlant
import de.unisaarland.cs.se.selab.utils.Tick
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertNull

class HarvestActionTest {
    private lateinit var tile1: Tile
    private lateinit var tile2: Tile
    private lateinit var tile3: Tile
    private lateinit var tile4: Tile
    private lateinit var tile5: Tile
    private lateinit var tile6: Tile
    private lateinit var tile7: Tile

    // Data from data.txt
    private val machine = Machine(
        1,
        "cool",
        listOf("SOWING", "WEEDING"),
        listOf("WHEAT"),
        2,
        0,
        0,
        null,
        1,
        0
    )

    @BeforeEach
    fun resetTicks() {
        Tick.currentTick = 0
        Tick.yTick = 0
    }

    @BeforeTest
    fun setup() {
        tile1 = Tile(
            id = 0, coordinate = Coordinate(1, 1), category = TileType.FARMSTEAD, farm = 0,
            airflow = false, direction = null, maxCapacity = null, plant = null,
            possiblePlants = null, harvestEstimate = 0, startOfTickEstimate = 0,
            shed = true, fallowPeriod = null, soilMoisture = 0, amountSunlight = 0,
            neighbours = emptyMap(), penalties = mutableListOf()
        )

        tile2 = Tile(
            id = 1, coordinate = Coordinate(2, 0), category = TileType.FIELD, farm = 0,
            airflow = false, direction = null, maxCapacity = 1000, plant = null,
            possiblePlants = listOf(FieldPlant.OAT, FieldPlant.WHEAT),
            harvestEstimate = 100000,
            startOfTickEstimate = 100000,
            shed = false, fallowPeriod = 0, soilMoisture = 100, amountSunlight = 100,
            neighbours = emptyMap(), penalties = mutableListOf()
        )

        tile3 = Tile(
            id = 2, coordinate = Coordinate(6, 0), category = TileType.FIELD, farm = 0,
            airflow = false, direction = null, maxCapacity = 1000, plant = null,
            possiblePlants = listOf(FieldPlant.OAT, FieldPlant.WHEAT), harvestEstimate = 0, startOfTickEstimate = 0,
            shed = false, fallowPeriod = 0, soilMoisture = 100, amountSunlight = 100,
            neighbours = emptyMap(), penalties = mutableListOf()
        )

        tile4 = Tile(
            id = 3, coordinate = Coordinate(4, 2), category = TileType.VILLAGE, farm = null,
            airflow = false, direction = null, maxCapacity = null, plant = null,
            possiblePlants = null, harvestEstimate = 0, startOfTickEstimate = 0,
            shed = false, fallowPeriod = null, soilMoisture = 0, amountSunlight = 0,
            neighbours = emptyMap(), penalties = mutableListOf()
        )

        tile5 = Tile(
            id = 4, coordinate = Coordinate(2, 4), category = TileType.FOREST, farm = null,
            airflow = false, direction = null, maxCapacity = null, plant = null,
            possiblePlants = null, harvestEstimate = 0, startOfTickEstimate = 0,
            shed = false, fallowPeriod = null, soilMoisture = 0, amountSunlight = 0,
            neighbours = emptyMap(), penalties = mutableListOf()
        )

        tile6 = Tile(
            id = 6, coordinate = Coordinate(8, 0), category = TileType.PLANTATION, farm = 0,
            airflow = false, direction = null, maxCapacity = 1000, plant = PlantationPlant.APPLE,
            possiblePlants = null,
            harvestEstimate = 100000,
            startOfTickEstimate = 100000,
            shed = false, fallowPeriod = 0, soilMoisture = 100, amountSunlight = 100,
            neighbours = emptyMap(), penalties = mutableListOf()
        )

        tile7 = Tile(
            id = 6, coordinate = Coordinate(8, 0), category = TileType.PLANTATION, farm = 0,
            airflow = false, direction = null, maxCapacity = 1000, plant = PlantationPlant.CHERRY,
            possiblePlants = null,
            harvestEstimate = 100000,
            startOfTickEstimate = 100000,
            shed = false, fallowPeriod = 0, soilMoisture = 100, amountSunlight = 100,
            neighbours = emptyMap(), penalties = mutableListOf()
        )
    }

    @Test
    fun execute_field_resets_and_sets_fallow_and_clears_schedule() {
        val action = HarvestAction(FieldPlant.WHEAT, startTick = 0, endTick = 2, tile = tile2)
        val schedule = mutableMapOf<Int, MutableList<Action>>(
            0 to mutableListOf(action)
        )

        val result = action.execute(schedule, machine)

        assertTrue(result)
        assertEquals(0, tile2.harvestEstimate)
        assertEquals(0, tile2.startOfTickEstimate)
        assertNull(tile2.plant)
        assertEquals(5, tile2.fallowPeriod)
    }

    @Test
    fun remove_actions_clears_all_actions_for_this_tile() {
        val action = HarvestAction(FieldPlant.WHEAT, startTick = 0, endTick = 2, tile = tile2)
        val schedule = mutableMapOf<Int, MutableList<Action>>(
            0 to mutableListOf(action)
        )

        action.removeActions(schedule)

        assertTrue(schedule[0]?.isEmpty() ?: true)
    }

    @Test
    fun plantation_harvest_schedules_cutting_and_mowing() {
        val action = HarvestAction(PlantationPlant.APPLE, 0, 0, tile6)
        val schedule = mutableMapOf<Int, MutableList<Action>>()

        action.plantationHarvest(schedule)

        val all = schedule.values.flatten()
        assertTrue(all.any { it is CuttingAction && it.tile.id == tile6.id })
        assertEquals(2, all.count { it is MowingAction && it.tile.id == tile6.id })
    }

    @Test
    fun plantation_harvest_cherry_only_schedules_one_mowing() {
        val action = HarvestAction(PlantationPlant.CHERRY, 0, 0, tile7)
        val schedule = mutableMapOf<Int, MutableList<Action>>()

        action.plantationHarvest(schedule)

        val all = schedule.values.flatten()
        assertTrue(all.any { it is CuttingAction && it.tile.id == tile7.id })
        assertEquals(1, all.count { it is MowingAction && it.tile.id == tile7.id })
    }
}
