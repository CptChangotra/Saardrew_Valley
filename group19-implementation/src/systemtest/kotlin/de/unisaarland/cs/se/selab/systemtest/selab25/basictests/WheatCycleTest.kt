package de.unisaarland.cs.se.selab.systemtest.selab25.basictests

import de.unisaarland.cs.se.selab.systemtest.selab25.utils.ExampleSystemTestExtension
import de.unisaarland.cs.se.selab.utils.YearTick

private const val FARM_START_STR = "[IMPORTANT] Farm: Farm 0 starts its actions."
private const val MACHINE_1_IRRIGATION = "[IMPORTANT] Farm Action: Machine 1 performs IRRIGATING on tile 11 for 4 days."
private const val MACHINE_0_RETURNS = "[IMPORTANT] Farm Machine: Machine 0 is finished and returns to the shed at 0."
private const val MACHINE_1_RETURNS = "[IMPORTANT] Farm Machine: Machine 1 is finished and returns to the shed at 0."
private const val FARM_SOWING_PLANS = "[DEBUG] Farm: Farm 0 has the following active sowing plans " +
    "it intends to pursue in this tick: ."
private const val FARM_SOWING_PLANS_ACTIVE = "[DEBUG] Farm: Farm 0 has the following active sowing plans " +
    "it intends to pursue in this tick: 0."
private const val FARM_FINISHED_ACTIONS = "[IMPORTANT] Farm: Farm 0 finished its actions."
private const val SIMULATION_STARTED_3 = "[INFO] Simulation Info: Tick 3 started at tick 23 within the year."
private const val MACHINE_0_WEEDING_10 = "[IMPORTANT] Farm Action: Machine 0 performs WEEDING on tile 10 for 4 days."
private const val MACHINE_0_WEEDING_11 = "[IMPORTANT] Farm Action: Machine 0 performs WEEDING on tile 11 for 4 days."
private const val MACHINE_0_SOWING_10 = "[IMPORTANT] Farm Action: Machine 0 performs SOWING on tile 10 for 4 days."
private const val MACHINE_0_SOWING_11 = "[IMPORTANT] Farm Action: Machine 0 performs SOWING on tile 11 for 4 days."
private const val MACHINE_0_SOWED = "[IMPORTANT] Farm Sowing: Machine 0 has sowed WHEAT according to sowing plan 0."
private const val WEEDING_NOT_PERFORMED = "[DEBUG] Harvest Estimate: Required actions " +
    "on tile 11 were not performed: WEEDING."

// private const val HARVEST_10_UPDATE = "[INFO] Harvest Estimate: Harvest estimate " +
//    "on tile 10 changed to 1350000 g of WHEAT."
private const val HARVEST_11_UPDATE = "[INFO] Harvest Estimate: Harvest estimate " +
    "on tile 11 changed to 1350000 g of WHEAT."

/**
 * Tests a full cycle for a Wheat
 */
abstract class WheatCycleTestAbstract : ExampleSystemTestExtension() {
    override val description = "Tests a full cycle for a Wheat"

    override val map = "basic_farm/map.json"
    override val farms = "basic_farm/farms.json"
    override val scenario = "example/scenario.json"

    override val logLevel = "DEBUG"
    override val maxTicks = 10
    override val startYearTick = YearTick.OCTOBER_2
}

/**
 * Tests a full cycle for a Wheat
 */
class WheatCycleTest : WheatCycleTestAbstract() {
    override val name = "Wheat Test 0"

    override suspend fun run() {
        skipUntilString(FARM_START_STR)
        assertNextLine(FARM_SOWING_PLANS_ACTIVE)
    }
}

/**
 * Tests a full cycle for a Wheat
 */
class WheatCycleTestPart3 : WheatCycleTestAbstract() {
    override val name = "Wheat Test 3"

    override suspend fun run() {
        skipUntilString(FARM_START_STR)
        assertNextLine(FARM_SOWING_PLANS_ACTIVE)
        assertNextLine(MACHINE_0_SOWING_10)
        assertNextLine(MACHINE_0_SOWED)
        assertNextLine(MACHINE_0_SOWING_11)
        assertNextLine(MACHINE_0_SOWED)
        assertNextLine(MACHINE_0_RETURNS)
        assertNextLine(FARM_FINISHED_ACTIONS)

        skipUntilString(FARM_START_STR)
        assertNextLine(FARM_SOWING_PLANS)
        assertNextLine(MACHINE_1_IRRIGATION)
        assertNextLine(MACHINE_1_RETURNS)

        skipUntilString(SIMULATION_STARTED_3)
        skipUntilString(FARM_START_STR)
        assertNextLine(FARM_SOWING_PLANS)
        assertNextLine(MACHINE_1_IRRIGATION)
        assertNextLine(MACHINE_1_RETURNS)
        assertNextLine(MACHINE_0_WEEDING_10)
        assertNextLine(MACHINE_0_RETURNS)
        assertNextLine(FARM_FINISHED_ACTIONS)

        assertNextLine(WEEDING_NOT_PERFORMED)
        assertNextLine(HARVEST_11_UPDATE)
    }
}

/**
 * Tests a full cycle for a Wheat
 */
class WheatCycleTestPart3Second : WheatCycleTestAbstract() {
    override val name = "Wheat Test 3 Second"

    override suspend fun run() {
        skipUntilString(FARM_START_STR)
        assertNextLine(FARM_SOWING_PLANS_ACTIVE)
        assertNextLine(MACHINE_0_SOWING_10)
        assertNextLine(MACHINE_0_SOWED)
        assertNextLine(MACHINE_0_SOWING_11)
        assertNextLine(MACHINE_0_SOWED)
        assertNextLine(MACHINE_0_RETURNS)
        assertNextLine(FARM_FINISHED_ACTIONS)

        skipUntilString(FARM_START_STR)
        assertNextLine(FARM_SOWING_PLANS)
        assertNextLine(MACHINE_1_IRRIGATION)
        assertNextLine(MACHINE_1_RETURNS)

        skipUntilString(SIMULATION_STARTED_3)
        skipUntilString(FARM_START_STR)
        assertNextLine(FARM_SOWING_PLANS)
        assertNextLine(MACHINE_0_WEEDING_10) // <-- works until here
        assertNextLine(MACHINE_0_WEEDING_11)
        assertNextLine(MACHINE_0_RETURNS)
//        assertNextLine(FARM_FINISHED_ACTIONS)
//
//        assertNextLine(HARVEST_10_UPDATE)
//        assertNextLine(HARVEST_11_UPDATE)
    }
}

/**
 * Tests a full cycle for a Wheat
 */
class WheatCycleTestPart4Second : WheatCycleTestAbstract() {
    override val name = "Wheat Test 4 Second"

    override suspend fun run() {
        skipUntilString(FARM_START_STR)
        assertNextLine(FARM_SOWING_PLANS_ACTIVE)
        assertNextLine(MACHINE_0_SOWING_10)
        assertNextLine(MACHINE_0_SOWED)
        assertNextLine(MACHINE_0_SOWING_11)
        assertNextLine(MACHINE_0_SOWED)
        assertNextLine(MACHINE_0_RETURNS)
        assertNextLine(FARM_FINISHED_ACTIONS)

        skipUntilString(FARM_START_STR)
        assertNextLine(FARM_SOWING_PLANS)
        assertNextLine(MACHINE_1_IRRIGATION)
        assertNextLine(MACHINE_1_RETURNS)

        skipUntilString(SIMULATION_STARTED_3)
        skipUntilString(FARM_START_STR)
        assertNextLine(FARM_SOWING_PLANS)
        assertNextLine(MACHINE_0_WEEDING_10) // <-- works until here
        assertNextLine(MACHINE_0_WEEDING_11)
        assertNextLine(MACHINE_0_RETURNS)
        assertNextLine(FARM_FINISHED_ACTIONS)

//        assertNextLine(HARVEST_10_UPDATE)
//        assertNextLine(HARVEST_11_UPDATE)
    }
}

/**
 * Tests a full cycle for a Wheat
 */
class WheatCycleTestPart4 : WheatCycleTestAbstract() {
    override val name = "Wheat Test 4"

    override suspend fun run() {
        skipUntilString(FARM_START_STR)
        assertNextLine(FARM_SOWING_PLANS_ACTIVE)
        assertNextLine(MACHINE_0_SOWING_10)
        assertNextLine(MACHINE_0_SOWED)
        assertNextLine(MACHINE_0_SOWING_11)
        assertNextLine(MACHINE_0_SOWED)
        assertNextLine(MACHINE_0_RETURNS)
        assertNextLine(FARM_FINISHED_ACTIONS)

        skipUntilString(FARM_START_STR)
        assertNextLine(FARM_SOWING_PLANS)
        assertNextLine(MACHINE_1_IRRIGATION)
        assertNextLine(MACHINE_1_RETURNS)

        skipUntilString(SIMULATION_STARTED_3)
        skipUntilString(FARM_START_STR)
        assertNextLine(FARM_SOWING_PLANS)
        assertNextLine(MACHINE_1_IRRIGATION)
        assertNextLine(MACHINE_1_RETURNS)
        assertNextLine(MACHINE_0_WEEDING_10)
        assertNextLine(MACHINE_0_RETURNS)
        assertNextLine(FARM_FINISHED_ACTIONS)

        assertNextLine(WEEDING_NOT_PERFORMED)
        assertNextLine(HARVEST_11_UPDATE)

        skipUntilString("[INFO] Simulation Info: Tick 9 started at tick 5 within the year.")
        skipUntilString(FARM_START_STR)
        assertNextLine(FARM_SOWING_PLANS)
        assertNextLine(MACHINE_1_IRRIGATION)
        assertNextLine(MACHINE_1_RETURNS)
        assertNextLine(MACHINE_0_WEEDING_10)
        assertNextLine(MACHINE_0_RETURNS)
        assertNextLine(FARM_FINISHED_ACTIONS)
    }
}

/**
 * Tests a full cycle for a Wheat
 */
class WheatCycleTestPart5 : WheatCycleTestAbstract() {
    override val name = "Wheat Test 5"

    override suspend fun run() {
        skipUntilString(FARM_START_STR)
        assertNextLine(FARM_SOWING_PLANS_ACTIVE)
        assertNextLine(MACHINE_0_SOWING_10)
        assertNextLine(MACHINE_0_SOWED)
        assertNextLine(MACHINE_0_SOWING_11)
        assertNextLine(MACHINE_0_SOWED)
        assertNextLine(MACHINE_0_RETURNS)
        assertNextLine(FARM_FINISHED_ACTIONS)

        skipUntilString(FARM_START_STR)
        assertNextLine(FARM_SOWING_PLANS)
        assertNextLine(MACHINE_1_IRRIGATION)
        assertNextLine(MACHINE_1_RETURNS)

        skipUntilString(SIMULATION_STARTED_3)
        skipUntilString(FARM_START_STR)
        assertNextLine(FARM_SOWING_PLANS)
        assertNextLine(MACHINE_1_IRRIGATION)
        assertNextLine(MACHINE_1_RETURNS)
        assertNextLine(MACHINE_0_WEEDING_10)
        assertNextLine(MACHINE_0_RETURNS)
        assertNextLine(FARM_FINISHED_ACTIONS)

        assertNextLine(WEEDING_NOT_PERFORMED)
        assertNextLine(HARVEST_11_UPDATE)

        skipUntilString("[INFO] Simulation Info: Tick 9 started at tick 5 within the year.")
        skipUntilString(FARM_START_STR)
        assertNextLine(FARM_SOWING_PLANS)
        assertNextLine(MACHINE_1_IRRIGATION)
        assertNextLine(MACHINE_1_RETURNS)
        assertNextLine(MACHINE_0_WEEDING_10)
        assertNextLine(MACHINE_0_RETURNS)
        assertNextLine(FARM_FINISHED_ACTIONS)

        assertNextLine(WEEDING_NOT_PERFORMED)
        assertNextLine("[INFO] Harvest Estimate: Harvest estimate on tile 10 changed to 1350000 g of WHEAT.")
        assertNextLine("[INFO] Harvest Estimate: Harvest estimate on tile 11 changed to 1093500 g of WHEAT.")
    }
}
