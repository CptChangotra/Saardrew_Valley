package general.farmTests
import de.unisaarland.cs.se.selab.farms.Action
import de.unisaarland.cs.se.selab.farms.HarvestAction
import de.unisaarland.cs.se.selab.farms.Machine
import de.unisaarland.cs.se.selab.farms.SowingAction
import de.unisaarland.cs.se.selab.farms.WeedingAction
import de.unisaarland.cs.se.selab.map.Tile
import de.unisaarland.cs.se.selab.map.TileType
import de.unisaarland.cs.se.selab.plant.FieldPlant
import de.unisaarland.cs.se.selab.utils.Tick
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever

class TestSowingActions {

    private fun sowableFieldTileFor(plant: FieldPlant): Tile {
        val tile = mock(Tile::class.java)
        whenever(tile.id).thenReturn(10_001)
        whenever(tile.category).thenReturn(TileType.FIELD)
        whenever(tile.fallowPeriod).thenReturn(0)
        whenever(tile.plant).thenReturn(null)
        // IMPORTANT: your Tile.possiblePlants is a List<FieldPlant>
        whenever(tile.possiblePlants).thenReturn(listOf(plant))
        return tile
    }

    private inline fun <reified T : Action> collectScheduled(
        schedule: MutableMap<Int, MutableList<Action>>,
        tile: Tile
    ): Map<Int, List<T>> =
        schedule
            .mapValues { (_, list) -> list.filterIsInstance<T>().filter { it.tile === tile } }
            .filterValues { it.isNotEmpty() }

    private fun findHarvestTick(schedule: MutableMap<Int, MutableList<Action>>, tile: Tile): Int {
        val harvestByTick = collectScheduled<HarvestAction>(schedule, tile)
        return harvestByTick.keys.single() // SowingAction schedules exactly one HarvestAction
    }

    @Test
    fun createWeeding_for_OAT_schedules_three_consecutive_ticks_after_sowing() {
        val plant = FieldPlant.OAT
        val tile = sowableFieldTileFor(plant)

        val sowStart = plant.sowingPeriod.first
        Tick.yTick = sowStart
        Tick.currentTick = sowStart

        val schedule = mutableMapOf<Int, MutableList<Action>>()
        val sow = SowingAction(plant, sowStart, sowStart, tile, id = 1)
        val machine = mock(Machine::class.java)

        assertTrue(sow.execute(schedule, machine), "OAT sowing should be possible")

        val weedingTicks = collectScheduled<WeedingAction>(schedule, tile).keys.toSet()
        assertEquals(setOf(sowStart + 1, sowStart + 2, sowStart + 3), weedingTicks)
    }

    @Test
    fun createWeeding_for_WHEAT_schedules_at_yPlus3_and_yPlus9() {
        val plant = FieldPlant.WHEAT
        val tile = sowableFieldTileFor(plant)

        val sowStart = plant.sowingPeriod.first
        Tick.yTick = sowStart
        Tick.currentTick = sowStart

        val schedule = mutableMapOf<Int, MutableList<Action>>()
        val sow = SowingAction(plant, sowStart, sowStart, tile, id = 2)
        val machine = mock(Machine::class.java)

        assertTrue(sow.execute(schedule, machine), "WHEAT sowing should be possible")

        val weedingTicks = collectScheduled<WeedingAction>(schedule, tile).keys.toSet()
        assertEquals(setOf(sowStart + 3, sowStart + 9), weedingTicks)
    }

    @Test
    fun createWeeding_for_POTATO_schedules_every_two_ticks_from_currentTickPlus2_until_harvestPlus3() {
        val plant = FieldPlant.POTATO
        val tile = sowableFieldTileFor(plant)

        val sowStart = plant.sowingPeriod.first
        Tick.yTick = sowStart
        Tick.currentTick = sowStart

        val schedule = mutableMapOf<Int, MutableList<Action>>()
        val sow = SowingAction(plant, sowStart, sowStart, tile, id = 3)
        val machine = mock(Machine::class.java)

        assertTrue(sow.execute(schedule, machine), "POTATO sowing should be possible")

        val firstWeedingExpected = Tick.currentTick + 2
        val harvestTick = findHarvestTick(schedule, tile)
        val lastWeedingExpected = harvestTick + 3

        val weedingTicks = collectScheduled<WeedingAction>(schedule, tile).keys.sorted()

        assertEquals(firstWeedingExpected, weedingTicks.first(), "first weeding tick should be currentTick + 2")
        val last = weedingTicks.last()
        assertTrue(
            last == lastWeedingExpected || last == lastWeedingExpected - 1,
            "last weeding tick should be ≤ harvestTick + 3 and match the step-2 parity"
        )
        weedingTicks.zipWithNext().forEach { (a, b) ->
            assertEquals(2, b - a, "weeding ticks for POTATO must be spaced by 2")
        }
    }

    @Test
    fun createWeeding_for_PUMPKIN_schedules_every_two_ticks_from_currentTickPlus2_until_harvestPlus3() {
        val plant = FieldPlant.PUMPKIN
        val tile = sowableFieldTileFor(plant)

        val sowStart = plant.sowingPeriod.first
        Tick.yTick = sowStart
        Tick.currentTick = sowStart

        val schedule = mutableMapOf<Int, MutableList<Action>>()
        val sow = SowingAction(plant, sowStart, sowStart, tile, id = 4)
        val machine = mock(Machine::class.java)

        assertTrue(sow.execute(schedule, machine), "PUMPKIN sowing should be possible")

        val firstWeedingExpected = Tick.currentTick + 2
        val harvestTick = findHarvestTick(schedule, tile)
        val lastWeedingExpected = harvestTick + 3

        val weedingTicks = collectScheduled<WeedingAction>(schedule, tile).keys.sorted()

        assertEquals(firstWeedingExpected, weedingTicks.first(), "first weeding tick should be currentTick + 2")
        assertEquals(lastWeedingExpected, weedingTicks.last(), "last weeding tick should be harvestTick + 3")
        weedingTicks.zipWithNext().forEach { (a, b) ->
            assertEquals(2, b - a, "weeding ticks for PUMPKIN must be spaced by 2")
        }
    }
}
