package de.unisaarland.cs.se.selab.systemtest.selab25.basictests

import de.unisaarland.cs.se.selab.systemtest.selab25.utils.ExampleSystemTestExtension
import de.unisaarland.cs.se.selab.utils.YearTick

private const val FARM_START_STR = "[IMPORTANT] Farm: Farm 0 starts its actions."
private const val SIMULATION_2 = "[INFO] Simulation Info: Tick 2 started at tick 3 within the year."
private const val SOWING_PLAN =
    "[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: ."
private const val CUTTING_0 = "[IMPORTANT] Farm Action: Machine 0 performs CUTTING on tile 1 for 4 days."

/**
 * abstract class to make grape tests
 **/
abstract class MoreApple : ExampleSystemTestExtension() {
    override val description = "Tests apple"

    override val map = "grape_test/grape_test_map.json"
    override val farms = "grape_test/grape_test_farm.json"
    override val scenario = "grape_test/new_scenario.json"

    override val logLevel = "DEBUG"
    override val maxTicks = 20
    override val startYearTick = YearTick.JANUARY_1
}

/**
 * class for the grape system test
 */
class AppleTest2 : MoreApple() {
    override val name = "Apple"

    override suspend fun run() {
        skipUntilString(SIMULATION_2)
        skipUntilString(FARM_START_STR)
        assertNextLine(SOWING_PLAN)
        assertNextLine(CUTTING_0)
    }
}
