package general.farmTests

import de.unisaarland.cs.se.selab.farms.Action
import de.unisaarland.cs.se.selab.farms.Farm
import de.unisaarland.cs.se.selab.farms.FarmController
import de.unisaarland.cs.se.selab.farms.Machine
import de.unisaarland.cs.se.selab.farms.MachineRouter
import de.unisaarland.cs.se.selab.farms.MachineTurnExecutor
import de.unisaarland.cs.se.selab.farms.SowingPlan
import de.unisaarland.cs.se.selab.farms.WeedingAction
import de.unisaarland.cs.se.selab.map.Coordinate
import de.unisaarland.cs.se.selab.map.MapController
import de.unisaarland.cs.se.selab.map.Tile
import de.unisaarland.cs.se.selab.map.TileType
import de.unisaarland.cs.se.selab.plant.FieldPlant
import de.unisaarland.cs.se.selab.plant.PlantationPlant
import de.unisaarland.cs.se.selab.utils.Stats
import de.unisaarland.cs.se.selab.utils.Tick
import de.unisaarland.cs.se.selab.utils.YearTick
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.eq
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import kotlin.test.assertNotEquals

class FarmControllerTests {
    private lateinit var mockMapController: MapController
    private lateinit var farm: Farm
    private lateinit var farmController: FarmController
    private lateinit var machineRouter: MachineRouter
    private lateinit var mockMachineRouter: MachineRouter
    private lateinit var machineTurnExecutor: MachineTurnExecutor
    private val stats: Stats = Stats(mutableMapOf(), mutableMapOf())
    private lateinit var tile1: Tile
    private lateinit var tile2: Tile
    private lateinit var tile3: Tile
    private lateinit var tile4: Tile
    private lateinit var tile11: Tile
    private lateinit var plant3: FieldPlant
    private lateinit var sowingPlanTileIDS: SowingPlan
    private lateinit var sowingPlanRadius: SowingPlan

    private val machine1 = Machine(
        id = 1, name = "Blue Twong", duration = 4, location = 11, lastShedLocation = 11, farmId = 1,
        actions = listOf("SOWING", "IRRIGATING"),
        plants = listOf(FieldPlant.WHEAT.toString(), FieldPlant.OAT.toString()),
        currentAction = null, isReady = 0
    )
    private val machine2 = Machine(
        id = 2, name = "Red Twong", duration = 5, location = 11, lastShedLocation = 11, farmId = 1,
        actions = listOf("HARVESTING", "WEEDING"),
        plants = listOf(FieldPlant.OAT.toString(), FieldPlant.WHEAT.toString()),
        currentAction = null, isReady = 0
    )
    private val machine3 = Machine(
        id = 3, name = "Green Twong", duration = 5, location = 11, lastShedLocation = 11, farmId = 1,
        actions = listOf("IRRIGATING", "WEEDING"),
        plants = listOf(FieldPlant.OAT.toString(), FieldPlant.WHEAT.toString()),
        currentAction = null, isReady = 0
    )

    @BeforeEach
    fun setUp() {
        mockMapController = mock()
        farm = Farm(
            id = 1, name = "Clarksons Farm", farmsteads = listOf(11),
            sheds = listOf(11), fields = listOf(1, 2, 3), plantations = emptyList(),
            machines = listOf(machine1, machine2, machine3),
            stuckMachines = emptyList(), sowingPlans = emptyList()
        )
        //  remove this line if we mock machineRouter
        machineRouter = MachineRouter(farm, mockMapController, mutableMapOf())
        mockMachineRouter = mock()
        whenever(mockMachineRouter.farm).thenReturn(farm)
        whenever(mockMachineRouter.mapController).thenReturn(mockMapController)
        whenever(mockMachineRouter.sowingPlans).thenReturn(mutableMapOf())

        tile1 = Tile(
            id = 1, coordinate = Coordinate(0, 0), category = TileType.FIELD, farm = 1,
            airflow = false, direction = null, maxCapacity = 1000, plant = null,
            possiblePlants = listOf(FieldPlant.WHEAT), harvestEstimate = 0, startOfTickEstimate = 0,
            shed = false, fallowPeriod = 0, soilMoisture = 100, amountSunlight = 100,
            neighbours = emptyMap(), penalties = mutableListOf()
        )
        tile2 = Tile(
            id = 2, coordinate = Coordinate(2, 0), category = TileType.FIELD, farm = 1,
            airflow = true, direction = 7, maxCapacity = 1000, plant = null,
            possiblePlants = listOf(FieldPlant.OAT), harvestEstimate = 0, startOfTickEstimate = 0,
            shed = false, fallowPeriod = 0, soilMoisture = 100, amountSunlight = 100,
            neighbours = emptyMap(), penalties = mutableListOf()
        )

        plant3 = FieldPlant.WHEAT
        tile3 = Tile(
            id = 3, coordinate = Coordinate(0, 2), category = TileType.FIELD, farm = 1,
            airflow = true, direction = 4, maxCapacity = 1000, plant = null,
            possiblePlants = listOf(FieldPlant.WHEAT), harvestEstimate = 0, startOfTickEstimate = 0,
            shed = false, fallowPeriod = 1, soilMoisture = 1000, amountSunlight = 100,
            neighbours = emptyMap(), penalties = mutableListOf()
        )
        tile4 = Tile(
            id = 21, coordinate = Coordinate(2, 2), category = TileType.PLANTATION, farm = 1,
            airflow = false, direction = null, maxCapacity = 50, plant = PlantationPlant.APPLE,
            possiblePlants = null, harvestEstimate = 0, startOfTickEstimate = 0,
            shed = false, fallowPeriod = 0, soilMoisture = 500, amountSunlight = 100,
            neighbours = emptyMap(), penalties = mutableListOf()
        )
        tile11 = Tile(
            id = 11, coordinate = Coordinate(1, 1), category = TileType.FARMSTEAD, farm = 1,
            airflow = false, direction = null, maxCapacity = null, plant = null,
            possiblePlants = null, harvestEstimate = 0, startOfTickEstimate = 0,
            shed = true, fallowPeriod = null, soilMoisture = 0, amountSunlight = 0,
            neighbours = emptyMap(), penalties = mutableListOf()
        )

        sowingPlanTileIDS = SowingPlan(
            id = 1, plant = FieldPlant.WHEAT, tileIDs = listOf(tile3.id, tile2.id, tile1.id),
            startTick = 1, radius = null, location = null
        )

        sowingPlanRadius = SowingPlan(
            id = 2, plant = FieldPlant.OAT, tileIDs = null,
            startTick = 1, radius = 1, location = tile1.id
        )
    }

    @Test
    fun testFarmControllerInitialization() {
        val schedule = mutableMapOf<Int, MutableList<Action>>()
        farmController = FarmController(
            farm,
            mockMapController,
            schedule = schedule,
            router = machineRouter,
            stats = stats
        )
        assertNotNull(farmController)
        assertEquals(farm, farmController.farm)
        assertEquals(farm.id, farmController.getFarmId())
    }

    @Test
    fun `irrigation is created`() {
        whenever(mockMapController.getTile(eq(1))).thenReturn(tile1)
        whenever(mockMapController.getTile(eq(3))).thenReturn(tile2)
        whenever(mockMapController.getTile(eq(2))).thenReturn(tile3)

        Tick.currentTick = 1
        val schedule = mutableMapOf<Int, MutableList<Action>>()
        assertTrue(schedule.isEmpty())
        farmController = FarmController(
            farm,
            mockMapController,
            schedule = schedule,
            router = mockMachineRouter,
            stats = stats
        )
        machineTurnExecutor = MachineTurnExecutor(farm, mockMapController)
        farmController.updateOnTick()
        val actions = schedule[Tick.currentTick]
        // assertNotNull(actions)
        //  val irrigatedTileIds = actions?.map { it.tile.id }
        //  assertTrue(irrigatedTileIds?.containsAll(listOf(1, 2, 3)) ?: return error("tile id unknown"))
        //  assertFalse(irrigatedTileIds?.contains(4) ?: return error("tile id unknown"))
    }

    @Test
    fun testPerformTurn() {
        val testWeedingAction = WeedingAction(
            plant = plant3,
            startTick = 1,
            endTick = 1,
            tile = tile3
        )
        val schedule = mutableMapOf<Int, MutableList<Action>>(1 to mutableListOf(testWeedingAction))
        farmController = FarmController(
            farm, mockMapController,
            schedule = schedule,
            router = machineRouter,
            stats = stats
        )
        farm.sowingPlans = listOf(sowingPlanTileIDS, sowingPlanRadius)
        machineTurnExecutor = MachineTurnExecutor(farm, mockMapController)

        Tick.currentTick = 1
        Tick.yTick = plant3.sowingPeriod.first

        //  Mocking map controller behavior
        whenever(mockMapController.findPath(any(), any(), any())).thenReturn(true)
        whenever(mockMapController.getTile(eq(1))).thenReturn(tile1)
        whenever(mockMapController.getTile(eq(3))).thenReturn(tile2)
        whenever(mockMapController.getTile(eq(2))).thenReturn(tile3)
        whenever(mockMapController.getNeighbourTiles(1, 2)).thenReturn(listOf(tile2, tile3))
        whenever(mockMapController.getNeighbourTiles(2, 2)).thenReturn(listOf(tile1, tile3))
        whenever(mockMapController.getNeighbourTiles(3, 2)).thenReturn(listOf(tile1, tile2))
        whenever(mockMapController.getNeighbourTiles(1, 1)).thenReturn(listOf(tile2, tile3))

        //  Perform the turn
        farmController.updateOnTick()
        farmController.updateHarvest()

        assertEquals(1, farm.sowingPlans.size)
        Tick.currentTick = 2
        Tick.yTick = YearTick.MARCH_2

        whenever(mockMapController.findPath(any(), any(), any())).thenReturn(true)
        whenever(mockMapController.getTile(eq(1))).thenReturn(tile1)
        whenever(mockMapController.getTile(eq(3))).thenReturn(tile2)
        whenever(mockMapController.getTile(eq(2))).thenReturn(tile3)
        whenever(mockMapController.getNeighbourTiles(1, 2)).thenReturn(listOf(tile2, tile3))
        whenever(mockMapController.getNeighbourTiles(2, 2)).thenReturn(listOf(tile1, tile3))
        whenever(mockMapController.getNeighbourTiles(3, 2)).thenReturn(listOf(tile1, tile2))
        whenever(mockMapController.getNeighbourTiles(1, 1)).thenReturn(listOf(tile2, tile3))

        farmController.updateOnTick()
        farmController.updateHarvest()

        //  assert(schedule.isEmpty())
    }

    @Test
    fun `Funny ahh test`() {
        //  Machine performs an action and then fails to return to the shed
        //  Incident happens on the tile where the machine is located
        //  The machine location should not change and the machine should be marked as broken
        tile3 = Tile(
            id = 3, coordinate = Coordinate(0, 2), category = TileType.FIELD, farm = 1,
            airflow = true, direction = 4, maxCapacity = 1000, plant = plant3,
            possiblePlants = listOf(plant3), harvestEstimate = 10000, startOfTickEstimate = 10000,
            shed = false, fallowPeriod = 0, soilMoisture = 1000, amountSunlight = 100,
            neighbours = emptyMap(), penalties = mutableListOf()
        )
        val testWeedingAction = WeedingAction(
            plant = plant3,
            startTick = 1,
            endTick = 1,
            tile = tile3
        )
        farm = Farm(
            id = 1, name = "Clarksons Farm", farmsteads = listOf(11),
            sheds = listOf(11), fields = listOf(1, 2, 3), plantations = emptyList(),
            machines = listOf(machine1, machine2, machine3), stuckMachines = emptyList(), sowingPlans = emptyList()
        )
        machineRouter = MachineRouter(
            farm, mockMapController, mutableMapOf()
        )
        val schedule = mutableMapOf<Int, MutableList<Action>>(1 to mutableListOf(testWeedingAction))
        farmController = FarmController(
            farm, mockMapController,
            schedule = schedule,
            router = machineRouter,
            stats = stats
        )
        machineTurnExecutor = MachineTurnExecutor(farm, mockMapController)
        Tick.currentTick = 1
        Tick.yTick = 1
        whenever(mockMapController.findPath(any(), any(), any())).thenReturn(true)
        whenever(mockMapController.findPath(any(), eq(11), any())).thenReturn(false)
        whenever(mockMapController.getTile(eq(1))).thenReturn(tile1)
        whenever(mockMapController.getTile(eq(3))).thenReturn(tile2)
        whenever(mockMapController.getTile(eq(2))).thenReturn(tile3)
        whenever(mockMapController.getNeighbourTiles(1, 2)).thenReturn(listOf(tile2, tile3))
        whenever(mockMapController.getNeighbourTiles(2, 2)).thenReturn(listOf(tile1, tile3))
        whenever(mockMapController.getNeighbourTiles(3, 2)).thenReturn(listOf(tile1, tile2))
        whenever(mockMapController.getNeighbourTiles(1, 1)).thenReturn(listOf(tile2, tile3))
        farmController.updateOnTick()

        assertNotEquals(machine2.location, machine2.lastShedLocation)
        assertEquals(-1, machine2.isReady)
        assert(machine2 in farm.stuckMachines)

        val machineBreakDownIncident = de.unisaarland.cs.se.selab.incidents.BrokenMachine(
            id = 1,
            tick = 1,
            mapController = mockMapController,
            machineId = machine2.id,
            duration = 3,
            machines = farm.machines
        )
        machineBreakDownIncident.apply()
        assertEquals(-1, machine2.isReady)
        assertEquals(machine2.location, tile3.id)
    }

    @Test
    fun `Late sowing harvest reduction test`() {
        whenever(mockMapController.getTile(eq(1))).thenReturn(tile1)
        whenever(mockMapController.getTile(eq(3))).thenReturn(tile3)
        whenever(mockMapController.getTile(eq(2))).thenReturn(tile2)
        whenever(mockMapController.findPath(any(), any(), any())).thenReturn(true)
        machine2.isReady = 0
        machine2.location = 11
        machine2.lastShedLocation = 11
        val sowingPlan1 = SowingPlan(
            id = 1,
            plant = FieldPlant.WHEAT,
            tileIDs = listOf(tile3.id),
            startTick = 1,
            radius = null,
            location = null
        )
        farm.sowingPlans = listOf(sowingPlan1)
        val schedule = mutableMapOf<Int, MutableList<Action>>()
        val machineRouter = MachineRouter(farm, mockMapController, mutableMapOf())
        farmController = FarmController(
            farm,
            mockMapController,
            schedule = schedule,
            router = machineRouter,
            stats = stats
        )
        Tick.currentTick = 1
        Tick.yTick = FieldPlant.WHEAT.sowingPeriod.second
        machineTurnExecutor = MachineTurnExecutor(farm, mockMapController)
        farmController.updateOnTick()
        farmController.updateHarvest()
        Tick.currentTick = 2
        Tick.yTick = FieldPlant.WHEAT.sowingPeriod.second + 2
        farmController.updateOnTick()
        farmController.updateHarvest()
        assertNotNull(tile3.plant)
        assertEquals(960000, tile3.harvestEstimate)
    }
}
