package general.farmTests

import de.unisaarland.cs.se.selab.farms.Action
import de.unisaarland.cs.se.selab.farms.Farm
import de.unisaarland.cs.se.selab.farms.HarvestAction
import de.unisaarland.cs.se.selab.farms.IrrigatingAction
import de.unisaarland.cs.se.selab.farms.Machine
import de.unisaarland.cs.se.selab.farms.MachineRouter
import de.unisaarland.cs.se.selab.farms.MachineTurnExecutor
import de.unisaarland.cs.se.selab.farms.SowingAction
import de.unisaarland.cs.se.selab.farms.TurnContext
import de.unisaarland.cs.se.selab.map.Coordinate
import de.unisaarland.cs.se.selab.map.MapController
import de.unisaarland.cs.se.selab.map.Tile
import de.unisaarland.cs.se.selab.map.TileType
import de.unisaarland.cs.se.selab.plant.FieldPlant
import de.unisaarland.cs.se.selab.plant.PlantationPlant
import de.unisaarland.cs.se.selab.utils.Stats
import de.unisaarland.cs.se.selab.utils.Tick
import org.junit.jupiter.api.Assertions.assertTrue
import org.mockito.kotlin.any
import org.mockito.kotlin.eq
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

object YearTick {
    const val MARCH_2 = 6
    const val JUNE_1 = 11
    const val JULY_1 = 13
    const val AUGUST_2 = 16
    const val OCTOBER_1 = 19
    const val OCTOBER_2 = 20
}

class MachineRouterTest {

    private lateinit var mockMapController: MapController
    private lateinit var farm: Farm
    private lateinit var machineRouter: MachineRouter
    private lateinit var machineTurnExecutor: MachineTurnExecutor

    private val machine1 = Machine(
        id = 1, name = "Tractor A", duration = 5, location = 101, lastShedLocation = 101, farmId = 1,
        actions = listOf("SOWING"), plants = listOf(FieldPlant.WHEAT.toString()),
        currentAction = null, isReady = 0
    )
    private val machine2 = Machine(
        id = 2, name = "Tractor B", duration = 5, location = 101, lastShedLocation = 101, farmId = 1,
        actions = listOf("HARVESTING"), plants = listOf(FieldPlant.WHEAT.toString()),
        currentAction = null, isReady = 0
    )
    private val machine21 = Machine(
        id = 21, name = "Tractor C", duration = 5, location = 101, lastShedLocation = 101, farmId = 1,
        actions = listOf("IRRIGATING"), plants = listOf(FieldPlant.WHEAT.toString()),
        currentAction = null, isReady = 0
    )

    private fun createDummyTile(id: Int): Tile = Tile(
        id = id, coordinate = Coordinate(0, 0), category = TileType.FIELD, farm = 1,
        airflow = false, direction = null, maxCapacity = 1000, plant = null,
        possiblePlants = null, harvestEstimate = 50000, startOfTickEstimate = 0,
        shed = false, fallowPeriod = 0, soilMoisture = 500, amountSunlight = 100,
        neighbours = emptyMap(), penalties = mutableListOf()
    )

    private fun createFieldTileWithPlant(id: Int, plant: FieldPlant, moisture: Int): Tile = Tile(
        id = id, coordinate = Coordinate(0, 0), category = TileType.FIELD, farm = 1,
        airflow = false, direction = null, maxCapacity = 1000, plant = plant,
        possiblePlants = listOf(plant), harvestEstimate = 1500000, startOfTickEstimate = 1500000,
        shed = false, fallowPeriod = 0, soilMoisture = moisture, amountSunlight = 100,
        neighbours = emptyMap(), penalties = mutableListOf()
    )

    private fun createPlantationTile(id: Int, plant: PlantationPlant, moisture: Int): Tile = Tile(
        id = id, coordinate = Coordinate(0, 0), category = TileType.PLANTATION, farm = 1,
        airflow = false, direction = null, maxCapacity = 1000, plant = plant,
        possiblePlants = null, harvestEstimate = 1500000, startOfTickEstimate = 1500000,
        shed = false, fallowPeriod = 0, soilMoisture = moisture, amountSunlight = 100,
        neighbours = emptyMap(), penalties = mutableListOf()
    )

    @BeforeTest
    fun setUp() {
        mockMapController = mock()
        farm = Farm(
            id = 1, name = "Test Farm", farmsteads = listOf(101, 102),
            sheds = listOf(101, 102), fields = listOf(1, 2, 3), plantations = emptyList(),
            machines = listOf(machine1, machine2),
            stuckMachines = emptyList(), sowingPlans = emptyList()
        )
        machineRouter = MachineRouter(farm, mockMapController, mutableMapOf())
        machineTurnExecutor = MachineTurnExecutor(farm, mockMapController)
        whenever(mockMapController.findPath(any(), any(), any())).thenReturn(true)
    }

    @Test
    fun `findHighestPriorityAction returns correct action based on tick and id`() {
        val action1 = SowingAction(FieldPlant.WHEAT, 2, 2, createDummyTile(1), 10) // Lower tick
        val action2 = SowingAction(FieldPlant.OAT, 3, 3, createDummyTile(2), 5)
        val actions = mutableListOf<Action>(action2, action1)

        val result = machineRouter.findHighestPriorityAction(actions)

        assertNotNull(result)
        assertEquals(action1, result)
    }

    @Test
    fun `findSuitableMachineForAction selects fastest machine`() {
        val action = SowingAction(FieldPlant.WHEAT, 1, 1, createDummyTile(1), 1)
        val usedMachines = emptySet<Int>()

        val result = machineTurnExecutor.findSuitableMachineForAction(action, usedMachines)

        assertNotNull(result)
        assertEquals(machine1, result)
    }

    @Test
    fun `returnToShed handles stuck machine correctly`() {
        val stuckMachine = Machine(
            id = 4, name = "Stuck Tractor", duration = 5, location = 200, lastShedLocation = 101,
            farmId = 1, actions = emptyList(), plants = emptyList(), currentAction = null, isReady = -1
        )
        stuckMachine.currentAction = HarvestAction(FieldPlant.WHEAT, 1, 1, createDummyTile(200))
        farm.machines += stuckMachine

        whenever(mockMapController.findPath(eq(200), any(), any())).thenReturn(false)

        val success = machineTurnExecutor.returnToShed(stuckMachine, 0, "")

        assertEquals(false, success)
        assertEquals(3, farm.machines.size)
        assertEquals(1, farm.stuckMachines.size)
        assertEquals(stuckMachine, farm.stuckMachines.first())
    }

    @Test
    fun `two actions cannot be done on the same tile in one tick`() {
        val machine3 = Machine(
            id = 3, name = "Tractor C", duration = 6, location = 101, lastShedLocation = 101, farmId = 1,
            actions = listOf("IRRIGATING"), plants = listOf(FieldPlant.WHEAT.toString()),
            currentAction = null, isReady = 0
        )
        farm.machines += machine3
        val action1 = SowingAction(FieldPlant.WHEAT, 2, 2, createDummyTile(1), 10)
        val action2 = IrrigatingAction(FieldPlant.WHEAT, 2, 2, createFieldTileWithPlant(1, FieldPlant.WHEAT, 100))
        val actions = mutableListOf<Action>(action1, action2)
        val context = TurnContext(
            schedule = mutableMapOf(2 to actions.toMutableList()),
            usedMachines = mutableSetOf(),
            plansExecuted = mutableSetOf(),
            usedTiles = mutableSetOf()
        )
        machineRouter.performActions(context, actions)
        assertEquals(1, context.usedTiles.size)
        assertEquals(1, context.usedMachines.size)
        machineRouter.performMachineLevelActions(context, actions, machine21)
        assertEquals(1, context.usedTiles.size)
        assertEquals(1, context.usedMachines.size)
    }

    @Test
    fun `no suitable machine for action`() {
        // dis test do be checking if action that no can do
        // being yeeted out da actions list
        // but not da schedule
        val action = SowingAction(FieldPlant.WHEAT, 1, 1, createDummyTile(1), 1)
        val usedMachines = setOf(1, 2) // Both machines are used
        val context = TurnContext(
            schedule = mutableMapOf(1 to mutableListOf(action)),
            usedMachines = usedMachines.toMutableSet(),
            plansExecuted = mutableSetOf(),
            usedTiles = mutableSetOf()
        )
        val actions = mutableListOf<Action>(action)
        val result = machineTurnExecutor.findSuitableMachineForAction(action, usedMachines)
        assertEquals(null, result)

        machineRouter.performActions(context, actions)
        assertEquals(0, context.usedTiles.size)
        assertEquals(2, context.usedMachines.size)
        assertEquals(0, actions.size)
        assertEquals(1, context.schedule[1]?.size)
    }

    @Test
    fun `stats are being collected correctly and duration check works`() {
        val plant = FieldPlant.WHEAT
        val plant2 = FieldPlant.WHEAT
        val plant3 = FieldPlant.WHEAT
        val plant4 = FieldPlant.OAT
        val fieldTile = createFieldTileWithPlant(1, plant, 2000)
        val filedTile2 = createFieldTileWithPlant(2, plant, 2000)
        val filedTile3 = createFieldTileWithPlant(3, plant, 2000)
        val filedTile4 = createFieldTileWithPlant(4, plant, 2000)
        val action = HarvestAction(plant, 1, 1, fieldTile)
        val action2 = HarvestAction(plant2, 1, 1, filedTile2)
        val action3 = HarvestAction(plant3, 1, 1, filedTile3)
        val action4 = HarvestAction(plant4, 1, 1, filedTile4)
        // only 2 actions can be done in one tick because duration is 5 and max is 14
        // so 3rd action should not be done
        val stats = Stats(mutableMapOf(), mutableMapOf())
        val schedule = mutableMapOf(1 to mutableListOf<Action>(action, action2, action3, action4))
        val context = TurnContext(
            schedule = schedule,
            usedMachines = mutableSetOf(),
            plansExecuted = mutableSetOf(),
            usedTiles = mutableSetOf(),
            stats = stats
        )
        val actions = mutableListOf<Action>(action, action2, action3, action4)
        whenever(mockMapController.getNeighbourTiles(1, 2)).thenReturn(listOf(filedTile2, filedTile3, filedTile4))
        whenever(mockMapController.getNeighbourTiles(2, 2)).thenReturn(listOf(fieldTile, filedTile3, filedTile4))
        machineRouter.performMachineLevelActions(context, actions, machine2)

        // no machines can harvest OAT
        assertEquals(null, machineTurnExecutor.findSuitableMachineForAction(action4, context.usedMachines))
        // assert action 1 and 2 are not in schedule anymore
        // and 3 and 4 are still there
        assertEquals(2, actions.size)
        assertEquals(true, schedule.getValue(1).contains(action3))
        assertEquals(true, schedule.getValue(1).contains(action4))
        assertEquals(false, schedule.getValue(1).contains(action))
        assertEquals(false, schedule.getValue(1).contains(action2))
        assertEquals(2, context.usedTiles.size)
        assertEquals(1, context.usedMachines.size)

        // we collected the harvest
        assertEquals(0, fieldTile.harvestEstimate)
        assertEquals(0, filedTile2.harvestEstimate)
        // check if stats were updated
        assertEquals(3000000, stats.getPlantStats(plant.toString()))
        assertEquals(3000000, stats.getFarmStats(farm.id))
    }

    @Test
    fun `Check if the new irrigation logic works`() {
        val tile1 = createFieldTileWithPlant(1, FieldPlant.WHEAT, 100)
        val tile2 = createFieldTileWithPlant(2, FieldPlant.OAT, 100)
        val tile5 = createFieldTileWithPlant(5, FieldPlant.PUMPKIN, 100)
        val tile3 = createPlantationTile(3, PlantationPlant.APPLE, 100)
        val tile4 = createPlantationTile(4, PlantationPlant.CHERRY, 100)
        val tiles = listOf(tile1, tile2, tile3, tile4, tile5)
        whenever(mockMapController.getTile(eq(1))).thenReturn(tile1)
        whenever(mockMapController.getTile(eq(2))).thenReturn(tile2)
        whenever(mockMapController.getTile(eq(3))).thenReturn(tile3)
        whenever(mockMapController.getTile(eq(4))).thenReturn(tile4)
        whenever(mockMapController.getTile(eq(5))).thenReturn(tile5)
        whenever(mockMapController.getNeighbourTiles(eq(1), eq(2))).thenReturn(listOf(tile2, tile3))
        whenever(mockMapController.getNeighbourTiles(eq(2), eq(2))).thenReturn(listOf(tile3, tile4, tile1))
        whenever(mockMapController.getNeighbourTiles(eq(3), eq(2))).thenReturn(tiles.filter { it.id != 3 })
        whenever(mockMapController.getNeighbourTiles(eq(4), eq(2))).thenReturn(listOf(tile5, tile3, tile2))
        whenever(mockMapController.getNeighbourTiles(eq(5), eq(2))).thenReturn(listOf(tile3, tile4))
        whenever(mockMapController.findPath(any(), any(), any())).thenReturn(true)
        val irrigationMachine = Machine(
            id = 3, name = "Irrigation System", duration = 1, location = 0, lastShedLocation = 0, farmId = 1,
            actions = listOf("IRRIGATING"),
            plants = listOf(
                FieldPlant.WHEAT.toString(),
                FieldPlant.OAT.toString(),
                FieldPlant.PUMPKIN.toString(),
                PlantationPlant.APPLE.toString(),
                PlantationPlant.CHERRY.toString()
            ),
            currentAction = null, isReady = 0
        )
        Tick.currentTick = 1
        farm.machines = listOf(irrigationMachine)
        val action1 = IrrigatingAction(FieldPlant.WHEAT, 1, 1, tile1)
        val action2 = IrrigatingAction(FieldPlant.OAT, 1, 1, tile2)
        val action3 = IrrigatingAction(FieldPlant.PUMPKIN, 1, 1, tile5)
        val action4 = IrrigatingAction(PlantationPlant.APPLE, 1, 1, tile3)
        val action5 = IrrigatingAction(PlantationPlant.CHERRY, 1, 1, tile4)
        val actions = mutableListOf<Action>(action1, action2, action3, action4, action5)
        val schedule = mutableMapOf(1 to actions.toMutableList())
        machineRouter.executeOthers(schedule, Stats(mutableMapOf(), mutableMapOf()))
        for (tile in tiles) {
            assertTrue(tile.soilMoisture > 100, "Tile ${tile.id} was not irrigated")
        }
    }

    @Test
    fun `Check if the new irrigation logic works for plantations`() {
        val tile1 = createPlantationTile(1, PlantationPlant.APPLE, 100)
        val tile2 = createPlantationTile(2, PlantationPlant.APPLE, 100)
        val tile5 = createPlantationTile(5, PlantationPlant.APPLE, 100)
        val tile3 = createFieldTileWithPlant(3, FieldPlant.OAT, 100)
        val tile4 = createFieldTileWithPlant(4, FieldPlant.OAT, 100)
        val tiles = listOf(tile1, tile2, tile3, tile4, tile5)
        whenever(mockMapController.getTile(eq(1))).thenReturn(tile1)
        whenever(mockMapController.getTile(eq(2))).thenReturn(tile2)
        whenever(mockMapController.getTile(eq(3))).thenReturn(tile3)
        whenever(mockMapController.getTile(eq(4))).thenReturn(tile4)
        whenever(mockMapController.getTile(eq(5))).thenReturn(tile5)
        whenever(mockMapController.getNeighbourTiles(eq(0), eq(2))).thenReturn(listOf(tile1, tile2))
        whenever(mockMapController.getNeighbourTiles(eq(1), eq(2))).thenReturn(listOf(tile2, tile3))
        whenever(mockMapController.getNeighbourTiles(eq(2), eq(2))).thenReturn(listOf(tile3, tile4, tile1))
        whenever(mockMapController.getNeighbourTiles(eq(3), eq(2))).thenReturn(tiles.filter { it.id != 3 })
        whenever(mockMapController.getNeighbourTiles(eq(4), eq(2))).thenReturn(listOf(tile5, tile3))
        whenever(mockMapController.getNeighbourTiles(eq(5), eq(2))).thenReturn(listOf(tile3, tile4))
        whenever(mockMapController.findPath(any(), any(), any())).thenReturn(true)
        val irrigationMachine = Machine(
            id = 3, name = "Irrigator 5000", duration = 1, location = 0, lastShedLocation = 0, farmId = 1,
            actions = listOf("IRRIGATING"),
            plants = listOf(
                FieldPlant.OAT.toString(),
                PlantationPlant.APPLE.toString(),
            ),
            currentAction = null, isReady = 0
        )
        Tick.currentTick = 1
        farm.machines = listOf(irrigationMachine)
        val action1 = IrrigatingAction(PlantationPlant.APPLE, 1, 1, tile1)
        val action2 = IrrigatingAction(PlantationPlant.APPLE, 1, 1, tile2)
        val action3 = IrrigatingAction(PlantationPlant.APPLE, 1, 1, tile5)
        val action4 = IrrigatingAction(FieldPlant.OAT, 1, 1, tile3)
        val action5 = IrrigatingAction(FieldPlant.OAT, 1, 1, tile4)
        val actions = mutableListOf<Action>(action1, action2, action3, action4, action5)
        val schedule = mutableMapOf(1 to actions.toMutableList())
        machineRouter.executeOthers(schedule, Stats(mutableMapOf(), mutableMapOf()))
        for (tile in listOf(tile3, tile4, tile5)) {
            assertTrue(tile.soilMoisture > 100, "Tile ${tile.id} was not irrigated")
        }
    }
}
