package general.farmTests

import de.unisaarland.cs.se.selab.farms.Action
import de.unisaarland.cs.se.selab.farms.CuttingAction
import de.unisaarland.cs.se.selab.farms.Farm
import de.unisaarland.cs.se.selab.farms.FarmController
import de.unisaarland.cs.se.selab.farms.Machine
import de.unisaarland.cs.se.selab.farms.MachineRouter
import de.unisaarland.cs.se.selab.farms.MachineTurnExecutor
import de.unisaarland.cs.se.selab.map.Coordinate
import de.unisaarland.cs.se.selab.map.MapController
import de.unisaarland.cs.se.selab.map.Tile
import de.unisaarland.cs.se.selab.map.TileType
import de.unisaarland.cs.se.selab.plant.PlantationPlant
import de.unisaarland.cs.se.selab.utils.Stats
import de.unisaarland.cs.se.selab.utils.Tick
import de.unisaarland.cs.se.selab.utils.YearTick
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever
import kotlin.test.assertNotNull

/**
 * test cherry cutting
 */
class CherryUnitTests {

    private lateinit var mockMapController: MapController
    private lateinit var farm: Farm
    private lateinit var farmController: FarmController
    private val schedule = mutableMapOf<Int, MutableList<Action>>()
    private lateinit var machineRouter: MachineRouter
    private lateinit var machineTurnExecutor: MachineTurnExecutor
    private val stats: Stats = Stats(mutableMapOf(), mutableMapOf())

    private val machine0 = Machine(
        id = 0, name = "Tractor A", duration = 10, location = 0, lastShedLocation = 0, farmId = 0,
        actions = listOf("CUTTING", "MOWING"), plants = listOf(PlantationPlant.CHERRY.toString()),
        currentAction = null, isReady = 0
    )
    private val machine1 = Machine(
        id = 1, name = "Tractor B", duration = 7, location = 0, lastShedLocation = 0, farmId = 0,
        actions = listOf("MOWING", "HARVESTING"), plants = listOf(PlantationPlant.CHERRY.toString()),
        currentAction = null, isReady = 0
    )

    private val cherry = PlantationPlant.CHERRY

    private val cherry1 = Tile(
        id = 1, coordinate = Coordinate(0, 0), category = TileType.PLANTATION, farm = 0,
        airflow = false, direction = null, maxCapacity = 100000, plant = cherry,
        possiblePlants = null, harvestEstimate = 1200000, startOfTickEstimate = 1200000,
        shed = false, fallowPeriod = 0, soilMoisture = 100000, amountSunlight = 100,
        neighbours = emptyMap(), penalties = mutableListOf()
    )

    private val cherry2 = Tile(
        id = 2, coordinate = Coordinate(2, 0), category = TileType.PLANTATION, farm = 0,
        airflow = false, direction = null, maxCapacity = 100000, plant = cherry,
        possiblePlants = null, harvestEstimate = 1200000, startOfTickEstimate = 1200000,
        shed = false, fallowPeriod = 0, soilMoisture = 100000, amountSunlight = 100,
        neighbours = emptyMap(), penalties = mutableListOf()
    )

    private val cherry3 = Tile(
        id = 3, coordinate = Coordinate(0, 2), category = TileType.PLANTATION, farm = 0,
        airflow = false, direction = null, maxCapacity = 100000, plant = cherry,
        possiblePlants = null, harvestEstimate = 1200000, startOfTickEstimate = 1200000,
        shed = false, fallowPeriod = 0, soilMoisture = 100000, amountSunlight = 100,
        neighbours = emptyMap(), penalties = mutableListOf()
    )

    private val cherry4 = Tile(
        id = 3, coordinate = Coordinate(2, 2), category = TileType.PLANTATION, farm = 0,
        airflow = false, direction = null, maxCapacity = 100000, plant = cherry,
        possiblePlants = null, harvestEstimate = 1200000, startOfTickEstimate = 1200000,
        shed = false, fallowPeriod = 0, soilMoisture = 100000, amountSunlight = 100,
        neighbours = emptyMap(), penalties = mutableListOf()
    )

//    private fun cutCherryAction(tick: Int, tile: Tile) = CuttingAction(
//        plant = PlantationPlant.CHERRY,
//        startTick = tick,
//        endTick = tick,
//        tile = tile
//    )
//
//    private fun setTick(tick: Int, yearTick: Int, tile: Tile) {
//        Tick.currentTick = tick
//        Tick.yTick = yearTick
//        val action = CuttingAction(PlantationPlant.CHERRY, yearTick, yearTick, tile)
//        schedule[tick] = mutableListOf(action)
//
//        farmController.updateOnTick()
//        farmController.updateHarvest()
//    }

    @BeforeEach
    fun setUp() {
        mockMapController = mock()
        farm = Farm(
            id = 1, name = "Cherry Farm", farmsteads = listOf(0),
            sheds = listOf(0), fields = emptyList(), plantations = listOf(1, 2, 3, 4),
            machines = listOf(machine0, machine1),
            stuckMachines = emptyList(), sowingPlans = emptyList()
        )
        machineRouter = MachineRouter(farm, mockMapController, mutableMapOf())
        machineTurnExecutor = MachineTurnExecutor(farm, mockMapController)
        whenever(mockMapController.findPath(any(), any(), any())).thenReturn(true)
        // whenever(farm.plantations).thenReturn(listOf(1, 2, 3, 4))

        whenever(mockMapController.getTile(1)).thenReturn(cherry1)
        whenever(mockMapController.getTile(2)).thenReturn(cherry2)
        whenever(mockMapController.getTile(3)).thenReturn(cherry3)
        whenever(mockMapController.getTile(4)).thenReturn(cherry4)
    }

    @Test
    fun `test cutting on cherry`() {
        farmController = FarmController(
            farm,
            mockMapController,
            schedule = schedule,
            router = machineRouter,
            stats = stats
        )

        Tick.currentTick = 1
        Tick.yTick = YearTick.NOVEMBER_1

        val cutCherry1 = CuttingAction(cherry, 1, 1, cherry1)
        val machinesCut1 = emptySet<Int>()

        val cut1 = machineTurnExecutor.findSuitableMachineForAction(cutCherry1, machinesCut1)

        assertNotNull(cut1)
        assertEquals(machine0, cut1)

        Tick.currentTick = 1
        Tick.yTick = YearTick.NOVEMBER_1

        val cutCherry2 = CuttingAction(cherry, 2, 2, cherry2)
        val machineCut2 = emptySet<Int>()

        val cut2 = machineTurnExecutor.findSuitableMachineForAction(cutCherry2, machineCut2)

        assertNotNull(cut2)
        assertEquals(machine0, cut2)
    }
}
