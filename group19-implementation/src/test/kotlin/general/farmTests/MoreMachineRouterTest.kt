package general.farmTests
import de.unisaarland.cs.se.selab.farms.Action
import de.unisaarland.cs.se.selab.farms.Farm
import de.unisaarland.cs.se.selab.farms.IrrigatingAction
import de.unisaarland.cs.se.selab.farms.Machine
import de.unisaarland.cs.se.selab.farms.MachineRouter
import de.unisaarland.cs.se.selab.farms.MachineTurnExecutor
import de.unisaarland.cs.se.selab.farms.SowingAction
import de.unisaarland.cs.se.selab.map.MapController
import de.unisaarland.cs.se.selab.map.Tile
import de.unisaarland.cs.se.selab.map.TileType
import de.unisaarland.cs.se.selab.plant.FieldPlant
import de.unisaarland.cs.se.selab.plant.Plant
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.any
import org.mockito.kotlin.doAnswer
import org.mockito.kotlin.eq
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNull
import kotlin.test.assertSame
import kotlin.test.assertTrue

class MoreMachineRouterTest {

    private fun sowingAction(
        id: Int,
        startTick: Int,
        tileId: Int
    ): Action {
        val tile = mock(Tile::class.java)
        whenever(tile.id).thenReturn(tileId)
        whenever(tile.category).thenReturn(TileType.FIELD)
        val plant = FieldPlant.POTATO
        return SowingAction(plant, startTick, startTick, tile, id)
    }

    private fun irrigatingAction(
        startTick: Int,
        tileId: Int,
        plantString: String = "POTATO"
    ): Action {
        val tile = mock(Tile::class.java)
        whenever(tile.id).thenReturn(tileId)
        whenever(tile.category).thenReturn(TileType.FIELD)

        val plant = mock<Plant>()
        whenever(plant.toString()).thenReturn(plantString)
        return IrrigatingAction(plant, startTick, startTick, tile)
    }

    private fun machineMock(
        id: Int,
        duration: Int,
        location: Int,
        actions: List<String>,
        plants: List<String>,
        isReady: Int = 0,
        farmId: Int = 0,
        lastShed: Int = location
    ): Machine {
        val m = mock(Machine::class.java)
        whenever(m.id).thenReturn(id)
        whenever(m.duration).thenReturn(duration)
        whenever(m.location).thenReturn(location)
        whenever(m.actions).thenReturn(actions)
        whenever(m.plants).thenReturn(plants)
        whenever(m.isReady).thenReturn(isReady)
        whenever(m.farmId).thenReturn(farmId)
        whenever(m.lastShedLocation).thenReturn(lastShed)

        doAnswer { inv ->
            val value = inv.getArgument<Int>(0)
            whenever(m.location).thenReturn(value)
            null
        }.whenever(m).location = any<Int>()

        doAnswer { inv ->
            val value = inv.getArgument<Int>(0)
            whenever(m.lastShedLocation).thenReturn(value)
            null
        }.whenever(m).lastShedLocation = any<Int>()

        doAnswer { inv ->
            val value = inv.getArgument<Int>(0)
            whenever(m.isReady).thenReturn(value)
            null
        }.whenever(m).isReady = any<Int>()

        return m
    }

    private fun farmMock(
        machines: List<Machine> = emptyList(),
        sheds: List<Int> = emptyList(),
        stuck: MutableList<Machine> = mutableListOf()
    ): Farm {
        val f = mock(Farm::class.java)
        whenever(f.machines).thenReturn(machines)
        whenever(f.sheds).thenReturn(sheds)
        whenever(f.stuckMachines).thenReturn(stuck)
        doAnswer { inv ->
            val newList = inv.getArgument<MutableList<Machine>>(0)
            stuck.clear()
            stuck.addAll(newList)
            null
        }.whenever(f).stuckMachines = any<MutableList<Machine>>()

        return f
    }

    @Test
    fun machineRouter_findHighestPriorityAction_orders_by_startTick_then_id_then_tileId_and_skips_usedTiles() {
        val mapC = mock(MapController::class.java)
        val farm = farmMock()
        val router = MachineRouter(farm, mapC, mutableMapOf())

        val a1 = sowingAction(id = 5, startTick = 10, tileId = 30)
        val a2 = sowingAction(id = 3, startTick = 10, tileId = 20)
        val a3 = sowingAction(id = 4, startTick = 9, tileId = 40)
        val a4 = sowingAction(id = 99, startTick = 9, tileId = 15)

        router.usedTiles.add(15)

        val picked = router.findHighestPriorityAction(listOf(a1, a2, a3, a4))
        assertSame(a3, picked)
    }

    @Test
    fun machineTurnExecutor_checkIfMachineSuitableForAction_requires_capabilities_and_path() {
        val mapC = mock(MapController::class.java)

        val act = irrigatingAction(startTick = 0, tileId = 500, plantString = "POTATO")

        val m = machineMock(
            id = 1,
            duration = 1,
            location = 100,
            actions = listOf("IRRIGATING"),
            plants = listOf("POTATO"),
            isReady = 0
        )

        val farm = farmMock(machines = listOf(m))
        val exec = MachineTurnExecutor(farm, mapC)

        whenever(mapC.findPath(eq(100), eq(500), eq(false))).thenReturn(true)
        assertTrue(exec.checkIfMachineSuitableForAction(m, act))

        whenever(mapC.findPath(eq(100), eq(500), eq(false))).thenReturn(false)
        assertFalse(exec.checkIfMachineSuitableForAction(m, act))

        whenever(mapC.findPath(eq(100), eq(500), eq(false))).thenReturn(true)
        whenever(m.actions).thenReturn(listOf("MOWING"))
        assertFalse(exec.checkIfMachineSuitableForAction(m, act))
    }

    @Test
    fun machineTurnExecutor_findSuitableMachineForAction_picks_by_duration_then_id_and_respects_filters() {
        val mapC = mock(MapController::class.java)
        val act = irrigatingAction(startTick = 0, tileId = 900, plantString = "POTATO")

        val mSlow = machineMock(
            id = 10,
            duration = 3,
            location = 1,
            actions = listOf("IRRIGATING"),
            plants = listOf("POTATO")
        )
        val mFast1 = machineMock(
            id = 11,
            duration = 1,
            location = 2,
            actions = listOf("IRRIGATING"),
            plants = listOf("POTATO")
        )
        val mFast2 = machineMock(
            id = 9,
            duration = 1,
            location = 3,
            actions = listOf("IRRIGATING"),
            plants = listOf("POTATO")
        )

        whenever(mapC.findPath(any<Int>(), eq(900), eq(false))).thenReturn(true)

        val farm = farmMock(machines = listOf(mSlow, mFast1, mFast2))
        val exec = MachineTurnExecutor(farm, mapC)

        val chosen = exec.findSuitableMachineForAction(act, usedMachines = emptySet())
        assertSame(mFast2, chosen)

        val chosen2 = exec.findSuitableMachineForAction(act, usedMachines = setOf(9))
        assertSame(mFast1, chosen2)

        whenever(mapC.findPath(any<Int>(), any<Int>(), any<Boolean>())).thenReturn(false)
        assertNull(exec.findSuitableMachineForAction(act, usedMachines = emptySet()))
    }

    @Test
    fun machineTurnExecutor_returnToShed_uses_last_shed_when_reachable_and_logs_unload_when_harvested() {
        val mapC = mock(MapController::class.java)

        val m = machineMock(
            id = 1,
            duration = 1,
            location = 100,
            actions = emptyList(),
            plants = emptyList(),
            lastShed = 7
        )

        val farm = farmMock(machines = listOf(m), sheds = listOf(7, 8), stuck = mutableListOf())
        val exec = MachineTurnExecutor(farm, mapC)

        whenever(mapC.findPath(100, 7, true)).thenReturn(true)
        val ok = exec.returnToShed(m, harvest = 123, plant = "POTATO")

        assertTrue(ok)
        assertEquals(7, m.location)
        assertEquals(7, m.lastShedLocation)
        assertTrue(farm.stuckMachines.isEmpty(), "Machine should not be marked stuck on successful return")
    }

    @Test
    fun machineTurnExecutor_returnToShed_tries_other_sheds_if_last_fails() {
        val mapC = mock(MapController::class.java)

        val m = machineMock(
            id = 2,
            duration = 1,
            location = 50,
            actions = emptyList(),
            plants = emptyList(),
            lastShed = 10
        )

        val farm = farmMock(machines = listOf(m), sheds = listOf(10, 20, 30), stuck = mutableListOf())
        val exec = MachineTurnExecutor(farm, mapC)

        whenever(mapC.findPath(50, 10, false)).thenReturn(false)
        whenever(mapC.findPath(50, 20, false)).thenReturn(true)

        val ok = exec.returnToShed(m, harvest = 0, plant = "")
        assertTrue(ok)
        assertEquals(20, m.location)
        assertEquals(20, m.lastShedLocation)
        assertTrue(farm.stuckMachines.isEmpty())
    }

    @Test
    fun machineTurnExecutor_returnToShed_marks_machine_stuck_when_no_shed_reachable() {
        val mapC = mock(MapController::class.java)

        val m = machineMock(
            id = 3,
            duration = 1,
            location = 77,
            actions = emptyList(),
            plants = emptyList(),
            lastShed = 5
        )

        val stuck = mutableListOf<Machine>()
        val farm = farmMock(machines = listOf(m), sheds = listOf(5, 6), stuck = stuck)
        val exec = MachineTurnExecutor(farm, mapC)

        whenever(mapC.findPath(eq(77), eq(5), eq(false))).thenReturn(false)
        whenever(mapC.findPath(eq(77), eq(6), eq(false))).thenReturn(false)

        val ok = exec.returnToShed(m, harvest = 0, plant = "")
        assertFalse(ok)
        assertTrue(stuck.contains(m), "Machine should be added to farm.stuckMachines on failure")
        assertEquals(-1, m.isReady, "Machine should be marked broken (isReady = -1)")
    }
}
