package general.parserTests

import de.unisaarland.cs.se.selab.parser.Parser
import de.unisaarland.cs.se.selab.utils.LogLevel
import de.unisaarland.cs.se.selab.utils.Tick
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNotNull

class ParserTests {

    @Test
    fun build_creates_all_controllers_with_injected_configs() {
        val args = arrayOf(
            "--map", "test_map.json",
            "--farms", "test_farms.json",
            "--scenario", "test_scenario.json",
            "--start_year_tick", "5",
            "--max_ticks", "8",
            "--log_level", "DEBUG",
            "--out", "stdout"
        )

        val parser = Parser(args)

        val sim = parser.build()

        // Assertions
        assertNotNull(sim)

        // MapController created; map loaded
        val map = sim.mapController.simulationMap
        assertEquals(setOf(0, 1, 2, 3), map.idToTile.keys.toSet())

        // FarmControllers created and sorted
        assertEquals(1, sim.farmController.size)
        assertEquals(listOf(1), sim.farmController.map { it.farm.id })

        // CloudController created from scenario
        val clouds = sim.cloudController.clouds
        assertEquals(1, clouds.size)
        val cloud = clouds.single()
        assertEquals(10, cloud.id)
        assertEquals(1, cloud.location)
        assertEquals(5, cloud.duration)
        assertEquals(100, cloud.amount)

        // IncidentController created (empty incidents list here)
        assertEquals(0, sim.incidentController.incidents.size)

        // Logger level applied
        assertEquals(LogLevel.DEBUG, de.unisaarland.cs.se.selab.utils.Logger.logLevel)
    }

    @Test
    fun build_applies_start_tick_and_log_level() {
        val args = arrayOf(
            "--map", "test_map.json",
            "--farms", "test_farms.json",
            "--scenario", "test_scenario.json",
            "--start_year_tick", "5",
            "--max_ticks", "8",
            "--log_level", "IMPORTANT",
            "--out", "stdout"
        )

        val parser = Parser(args = args)

        val sim = parser.build()

        assertEquals(8, sim.maxTicks)
        assertEquals(5, Tick.yTick)
        assertEquals(0, Tick.currentTick)
        assertEquals(LogLevel.IMPORTANT, de.unisaarland.cs.se.selab.utils.Logger.logLevel)
    }

    @Test
    fun parseCLI_with_help_flag_throws_HelpMessage() {
        val args = arrayOf("--help")

        val parser = Parser(args)

        assertFailsWith<Parser.HelpMessage> {
            parser.build()
        }
    }

    @Test
    fun build_creates_sowing_plans_correctly() {
        val args = arrayOf(
            "--map", "test_map.json",
            "--farms", "test_farms.json",
            "--scenario", "test_scenario.json",
            "--start_year_tick", "1",
            "--max_ticks", "10",
            "--log_level", "DEBUG",
            "--out", "stdout"
        )

        val parser = Parser(args = args)

        val sim = parser.build()

        val farmController = sim.farmController.first()
        val farm = farmController.farm

        val sowingPlan = farm.sowingPlans.first()
        assertEquals(0, sowingPlan.id)
        assertEquals(0, sowingPlan.startTick)
        assertEquals("WHEAT", sowingPlan.plant.name)
        assertEquals(1, sowingPlan.location)
        assertEquals(2, sowingPlan.radius)
    }

    @Test
    fun check_plantation_harvest_init() {
        val args = arrayOf(
            "--map", "test_map.json",
            "--farms", "test_farms.json",
            "--scenario", "test_scenario.json",
            "--start_year_tick", "20",
            "--max_ticks", "10",
            "--log_level", "DEBUG",
            "--out", "stdout"
        )

        val parser = Parser(args = args)

        val sim = parser.build()

        assertEquals(850000, sim.mapController.simulationMap.idToTile[2]?.harvestEstimate ?: return)
    }
}
