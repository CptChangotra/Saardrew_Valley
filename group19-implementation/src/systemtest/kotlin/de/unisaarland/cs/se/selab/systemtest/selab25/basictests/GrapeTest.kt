package de.unisaarland.cs.se.selab.systemtest.selab25.basictests

import de.unisaarland.cs.se.selab.systemtest.selab25.utils.ExampleSystemTestExtension
import de.unisaarland.cs.se.selab.utils.YearTick

private const val FARM_START_STR = "[IMPORTANT] Farm: Farm 0 starts its actions."
private const val MACHINE_1_HARVESTING = "[IMPORTANT] Farm Action: Machine 1 performs HARVESTING on tile 1 for 2 days."
private const val MACHINE_0_RETURNS = "[IMPORTANT] Farm Machine: Machine 0 is finished and returns to the shed at 0."
private const val MACHINE_1_RETURNS = "[IMPORTANT] Farm Machine: Machine 1 is finished and returns to the shed at 0."
private const val FARM_SOWING_PLANS = "[DEBUG] Farm: Farm 0 has the following active sowing plans " +
    "it intends to pursue in this tick: ."
private const val FARM_FINISHED_ACTIONS = "[IMPORTANT] Farm: Farm 0 finished its actions."
private const val SIMULATION_STARTED_18 = "[INFO] Simulation Info: Tick 18 started at tick 7 within the year."
private const val SIMULATION_STARTED_4 = "[INFO] Simulation Info: Tick 4 started at tick 17 within the year."
private const val SIMULATION_STARTED_24 = "[INFO] Simulation Info: Tick 24 started at tick 13 within the year."
private const val SIMULATION_STARTED_28 = "[INFO] Simulation Info: Tick 28 started at tick 17 within the year."
private const val MACHINE_0_CUTTING = "[IMPORTANT] Farm Action: Machine 0 performs CUTTING on tile 1 for 4 days."
private const val MACHINE_0_MOWING = "[IMPORTANT] Farm Action: Machine 0 performs MOWING on tile 1 for 4 days."
private const val MACHINE_1_MOWING = "[IMPORTANT] Farm Action: Machine 1 performs MOWING on tile 1 for 2 days."
private const val MACHINE_1_HARVESTED = "[IMPORTANT] Farm Harvest: Machine 1 has collected 1200000 g of " +
    "GRAPE harvest."
private const val HARVEST_UNLOAD = "[IMPORTANT] Farm Machine: Machine 1 unloads 1200000 g of " +
    "GRAPE harvest in the shed."

/**
* abstract class to make grape tests
**/
abstract class GrapeTestAbstract : ExampleSystemTestExtension() {
    override val description = "Tests grapes"

    override val map = "grape_test/grape_test_map.json"
    override val farms = "grape_test/grape_test_farm.json"
    override val scenario = "grape_test/grape_test_scenario.json"

    override val logLevel = "DEBUG"
    override val maxTicks = 30
    override val startYearTick = YearTick.JULY_1
}

/**
 * abstract class to make grape tests
 **/
class GrapeTest10 : GrapeTestAbstract() {
    override val name = "Grape Test10"

    override suspend fun run() {
        skipUntilString(FARM_START_STR)
        assertNextLine(FARM_SOWING_PLANS)
    }
}

/**
 * class for the grape system test
 */
class GrapeTest9 : GrapeTestAbstract() {
    override val name = "Grape Test9"

    override suspend fun run() {
        skipUntilString(FARM_START_STR)
        assertNextLine(FARM_SOWING_PLANS)
        assertNextLine(MACHINE_1_MOWING)
        assertNextLine(MACHINE_1_RETURNS)
        assertNextLine(FARM_FINISHED_ACTIONS)
    }
}

/**
 * class for the grape system test
 */
class GrapeTest2 : GrapeTestAbstract() {
    override val name = "Grape Test2"

    override suspend fun run() {
        skipUntilString(FARM_START_STR)
        assertNextLine(FARM_SOWING_PLANS)
        assertNextLine(MACHINE_0_MOWING)
        assertNextLine(MACHINE_0_RETURNS)
        assertNextLine(FARM_FINISHED_ACTIONS)
    }
}

/**
 * class for the grape system test
 */
class GrapeTest3 : GrapeTestAbstract() {
    override val name = "Grape Test3"

    override suspend fun run() {
        skipUntilString(FARM_START_STR)
        assertNextLine(FARM_SOWING_PLANS)
        assertNextLine(MACHINE_0_MOWING)
        assertNextLine(MACHINE_0_RETURNS)
        assertNextLine(FARM_FINISHED_ACTIONS)

        skipUntilString(FARM_START_STR)
        assertNextLine(FARM_SOWING_PLANS)
        assertNextLine(MACHINE_0_CUTTING)
        assertNextLine(MACHINE_0_RETURNS)
    }
}

/**
 * class for the grape system test
 */
class GrapeTest4 : GrapeTestAbstract() {
    override val name = "Grape Test4"

    override suspend fun run() {
        skipUntilString(FARM_START_STR)
        assertNextLine(FARM_SOWING_PLANS)
        assertNextLine(MACHINE_0_MOWING)
        assertNextLine(MACHINE_0_RETURNS)
        assertNextLine(FARM_FINISHED_ACTIONS)

        skipUntilString(FARM_START_STR)
        assertNextLine(FARM_SOWING_PLANS)
        assertNextLine(MACHINE_0_CUTTING)
        assertNextLine(MACHINE_0_RETURNS)

        skipUntilString(SIMULATION_STARTED_4)
        skipLines(1)
        assertNextLine(FARM_START_STR)
        assertNextLine(FARM_SOWING_PLANS)
        assertNextLine(MACHINE_1_HARVESTING)
        assertNextLine(MACHINE_1_HARVESTED)
        assertNextLine(MACHINE_1_RETURNS)
        assertNextLine(HARVEST_UNLOAD)
        assertNextLine(FARM_FINISHED_ACTIONS)
    }
}

/**
 * class for the grape system test
 */
class GrapeTest5 : GrapeTestAbstract() {
    override val name = "Grape Test5"

    override suspend fun run() {
        skipUntilString(FARM_START_STR)
        assertNextLine(FARM_SOWING_PLANS)
        assertNextLine(MACHINE_0_MOWING)
        assertNextLine(MACHINE_0_RETURNS)
        assertNextLine(FARM_FINISHED_ACTIONS)

        skipUntilString(FARM_START_STR)
        assertNextLine(FARM_SOWING_PLANS)
        assertNextLine(MACHINE_0_CUTTING)
        assertNextLine(MACHINE_0_RETURNS)

        skipUntilString(SIMULATION_STARTED_4)
        skipLines(1)
        assertNextLine(FARM_START_STR)
        assertNextLine(FARM_SOWING_PLANS)
        assertNextLine(MACHINE_1_HARVESTING)
        assertNextLine(MACHINE_1_HARVESTED)
        assertNextLine(MACHINE_1_RETURNS)
        assertNextLine(HARVEST_UNLOAD)
        assertNextLine(FARM_FINISHED_ACTIONS)

        skipUntilString(SIMULATION_STARTED_24)
        skipUntilString(FARM_START_STR)
        assertNextLine(FARM_SOWING_PLANS)
        assertNextLine(MACHINE_0_MOWING)
        assertNextLine(MACHINE_0_RETURNS)
        assertNextLine(FARM_FINISHED_ACTIONS)
    }
}

/**
 * class for the grape system test
 */
class GrapeTest6 : GrapeTestAbstract() {
    override val name = "Grape Test6"

    override suspend fun run() {
        skipUntilString(FARM_START_STR)
        assertNextLine(FARM_SOWING_PLANS)
        assertNextLine(MACHINE_0_MOWING)
        assertNextLine(MACHINE_0_RETURNS)
        assertNextLine(FARM_FINISHED_ACTIONS)

        skipUntilString(FARM_START_STR)
        assertNextLine(FARM_SOWING_PLANS)
        assertNextLine(MACHINE_0_CUTTING)
        assertNextLine(MACHINE_0_RETURNS)

        skipUntilString(SIMULATION_STARTED_4)
        skipLines(1)
        assertNextLine(FARM_START_STR)
        assertNextLine(FARM_SOWING_PLANS)
        assertNextLine(MACHINE_1_HARVESTING)
        assertNextLine(MACHINE_1_HARVESTED)
        assertNextLine(MACHINE_1_RETURNS)
        assertNextLine(HARVEST_UNLOAD)
        assertNextLine(FARM_FINISHED_ACTIONS)

        skipUntilString(SIMULATION_STARTED_24)
        skipUntilString(FARM_START_STR)
        assertNextLine(FARM_SOWING_PLANS)
        assertNextLine(MACHINE_0_MOWING)
        assertNextLine(MACHINE_0_RETURNS)
        assertNextLine(FARM_FINISHED_ACTIONS)

        skipUntilString(FARM_START_STR)
        assertNextLine(FARM_SOWING_PLANS)
        assertNextLine(MACHINE_0_CUTTING)
        assertNextLine(MACHINE_0_RETURNS)
    }
}

/**
 * class for the grape system test
 */
class GrapeTest : GrapeTestAbstract() {
    override val name = "Grape Test"

    override suspend fun run() {
        skipUntilString(FARM_START_STR)
        assertNextLine(FARM_SOWING_PLANS)
        assertNextLine(MACHINE_1_MOWING)
        assertNextLine(MACHINE_1_RETURNS)
        assertNextLine(FARM_FINISHED_ACTIONS)

        skipUntilString(FARM_START_STR)
        assertNextLine(FARM_SOWING_PLANS)
        assertNextLine(MACHINE_0_CUTTING)
        assertNextLine(MACHINE_0_RETURNS)

        skipUntilString(SIMULATION_STARTED_4)
        skipLines(1)
        assertNextLine(FARM_START_STR)
        assertNextLine(FARM_SOWING_PLANS)
        assertNextLine(MACHINE_1_HARVESTING)
        assertNextLine(MACHINE_1_HARVESTED)
        assertNextLine(MACHINE_1_RETURNS)
        assertNextLine(HARVEST_UNLOAD)
        assertNextLine(FARM_FINISHED_ACTIONS)

        skipUntilString(SIMULATION_STARTED_18)
        skipUntilString(FARM_START_STR)
        assertNextLine(FARM_SOWING_PLANS)
        assertNextLine(MACHINE_1_MOWING)
        assertNextLine(MACHINE_1_RETURNS)
        assertNextLine(FARM_FINISHED_ACTIONS)

        skipUntilString(SIMULATION_STARTED_24)
        skipUntilString(FARM_START_STR)
        assertNextLine(FARM_SOWING_PLANS)
        assertNextLine(MACHINE_1_MOWING)
        assertNextLine(MACHINE_1_RETURNS)
        assertNextLine(FARM_FINISHED_ACTIONS)

        skipUntilString(FARM_START_STR)
        assertNextLine(FARM_SOWING_PLANS)
        assertNextLine(MACHINE_0_CUTTING)
        assertNextLine(MACHINE_0_RETURNS)

        skipUntilString(SIMULATION_STARTED_28)
        skipLines(1)
        assertNextLine(FARM_START_STR)
        assertNextLine(FARM_SOWING_PLANS)
        assertNextLine(MACHINE_1_HARVESTING)
        assertNextLine(MACHINE_1_HARVESTED)
        assertNextLine(MACHINE_1_RETURNS)
        assertNextLine(HARVEST_UNLOAD)
        assertNextLine(FARM_FINISHED_ACTIONS)
    }
}
