package de.unisaarland.cs.se.selab.systemtest.selab25.basictests

import de.unisaarland.cs.se.selab.systemtest.selab25.utils.ExampleSystemTestExtension
import de.unisaarland.cs.se.selab.utils.YearTick

private const val CLOUD_CREATION = "[IMPORTANT] Incident: Incident 222 of type CLOUD_CREATION " +
    "happened and affected tiles 22,23,24,31."
private const val FARM_START_STR = "[IMPORTANT] Farm: Farm 0 starts its actions."
private const val FARM_SOWING_PLAN = "[DEBUG] Farm: Farm 0 has the following active sowing plans " +
    "it intends to pursue in this tick: ."
private const val SOIL_MOISTURE = "[INFO] Soil Moisture: The soil moisture is below threshold in " +
    "0 FIELD and 0 PLANTATION tiles."
private const val MACHINE_0_CUT_22 = "[IMPORTANT] Farm Action: Machine 0 performs CUTTING on " +
    "tile 22 for 4 days."
private const val MACHINE_0_CUT_23 = "[IMPORTANT] Farm Action: Machine 0 performs CUTTING on " +
    "tile 23 for 4 days."
private const val MACHINE_0_MOW_22 = "[IMPORTANT] Farm Action: Machine 0 performs MOWING on " +
    "tile 22 for 4 days."
private const val MACHINE_0_MOW_23 = "[IMPORTANT] Farm Action: Machine 0 performs MOWING on " +
    "tile 23 for 4 days."
private const val MACHINE_0_RETURN = "[IMPORTANT] Farm Machine: Machine 0 is finished and returns to the shed at 0."
private const val MACHINE_2_HARVEST_22 = "[IMPORTANT] Farm Action: Machine 2 performs HARVESTING on tile 22 for 4 days."
private const val MACHINE_2_HARVEST_23 = "[IMPORTANT] Farm Action: Machine 2 performs HARVESTING on tile 23 for 4 days."
private const val MACHINE_2_RETURN = "[IMPORTANT] Farm Machine: Machine 2 is finished and returns to the shed at 0."
private const val FARM_FINISHED_ACTIONS = "[IMPORTANT] Farm: Farm 0 finished its actions."

/**
 * Tests a cycle of Apple
 */
abstract class AppleCycleAbstract : ExampleSystemTestExtension() {
    // override val name = "AppleLife"
    override val description = "Tests a cycle for Apple"

    override val map = "basicApple/appleMap.json"
    override val farms = "basicApple/appleFarm.json"
    override val scenario = "basicApple/appleScenario.json"

    override val logLevel = "DEBUG"

    override val maxTicks = 23
    override val startYearTick = YearTick.DECEMBER_1
}

/**
 * Tests a cycle of Apple
 */
class AppleCyclePart1 : AppleCycleAbstract() {
    override val name = "Apple Test until cutting"
    override suspend fun run() {
        // sunlightPenalty applies in Dec1 and Dec2
        skipUntilString(CLOUD_CREATION)
        assertNextLine("[INFO] Harvest Estimate: Harvest estimate on tile 22 changed to 1377000 g of APPLE.")
        // Feb1 - Feb2, cutting
        skipUntilString("[INFO] Simulation Info: Tick 4 started at tick 3 within the year.")
        assertNextLine(SOIL_MOISTURE)
        assertNextLine("[DEBUG] Cloud Position: Cloud 0 is on tile 22, where the amount of sunlight is 62.")
        assertNextLine("[DEBUG] Cloud Position: Cloud 1 is on tile 23, where the amount of sunlight is 62.")
        assertNextLine("[DEBUG] Cloud Position: Cloud 2 is on tile 24, where the amount of sunlight is 62.")
        assertNextLine(FARM_START_STR)
        assertNextLine(FARM_SOWING_PLAN)
        assertNextLine(MACHINE_0_CUT_22)
        assertNextLine(MACHINE_0_CUT_23)
        assertNextLine(MACHINE_0_RETURN)
        assertNextLine(FARM_FINISHED_ACTIONS)

        skipUntilString("[INFO] Simulation Info: Tick 5 started at tick 4 within the year.")
        assertNextLine(SOIL_MOISTURE)
        skipUntilString(FARM_START_STR)
        assertNextLine(FARM_SOWING_PLAN)
        assertNextLine(FARM_FINISHED_ACTIONS)
        assertNextLine("[DEBUG] Harvest Estimate: Required actions on tile 24 were not performed: CUTTING.")
        assertNextLine("[INFO] Harvest Estimate: Harvest estimate on tile 24 changed to 688500 g of APPLE.")
    }
}

/**
 * Tests a cycle of Apple
 */
class AppleCyclePart2 : AppleCycleAbstract() {
    override val name = "Apple Test mowing"
    override suspend fun run() {
        // June1 mowing
        skipUntilString("[INFO] Simulation Info: Tick 12 started at tick 11 within the year.")
        assertNextLine(SOIL_MOISTURE)
        assertNextLine("[DEBUG] Cloud Position: Cloud 0 is on tile 22, where the amount of sunlight is 118.")
        assertNextLine("[DEBUG] Cloud Position: Cloud 1 is on tile 23, where the amount of sunlight is 118.")
        assertNextLine("[DEBUG] Cloud Position: Cloud 2 is on tile 24, where the amount of sunlight is 118.")
        assertNextLine(FARM_START_STR)
        assertNextLine(FARM_SOWING_PLAN)
        assertNextLine(MACHINE_0_MOW_22)
        assertNextLine(MACHINE_0_MOW_23)
        assertNextLine(MACHINE_0_RETURN)
        assertNextLine(FARM_FINISHED_ACTIONS)
        assertNextLine("[INFO] Harvest Estimate: Harvest estimate on tile 22 changed to 480127 g of APPLE.")
        assertNextLine("[INFO] Harvest Estimate: Harvest estimate on tile 23 changed to 480127 g of APPLE.")
        assertNextLine("[DEBUG] Harvest Estimate: Required actions on tile 24 were not performed: MOWING.")
    }
}

/**
 * Tests a cycle of Apple
 */
class AppleCyclePart3 : AppleCycleAbstract() {
    override val name = "Apple Test harvesting"
    override suspend fun run() {
        // Sep1
        skipUntilString("[INFO] Simulation Info: Tick 18 started at tick 17 within the year.")
        assertNextLine(SOIL_MOISTURE)
        skipUntilString(FARM_START_STR)
        assertNextLine(FARM_SOWING_PLAN)
        assertNextLine(MACHINE_2_HARVEST_22)
        assertNextLine("[IMPORTANT] Farm Harvest: Machine 2 has collected 167407 g of APPLE harvest.")
        assertNextLine(MACHINE_2_HARVEST_23)
        assertNextLine("[IMPORTANT] Farm Harvest: Machine 2 has collected 167407 g of APPLE harvest.")
        assertNextLine(MACHINE_2_RETURN)
        assertNextLine("[IMPORTANT] Farm Machine: Machine 2 unloads 334814 g of APPLE harvest in the shed.")
        assertNextLine(FARM_FINISHED_ACTIONS)
        assertNextLine("[DEBUG] Harvest Estimate: Required actions on tile 24 were not performed: MOWING.")

        // Sep2 this part no problem
        // assertNextLine("[INFO] Simulation Info: Tick 19 started at tick 18 within the year.")
        // assertNextLine(SOIL_MOISTURE)
        skipUntilString(FARM_START_STR)
        assertNextLine(FARM_SOWING_PLAN)
        assertNextLine(FARM_FINISHED_ACTIONS)

        // Oct1 this part no problem
        // assertNextLine("[INFO] Simulation Info: Tick 20 started at tick 19 within the year.")
        // assertNextLine(SOIL_MOISTURE)
        skipUntilString(FARM_START_STR)
        assertNextLine(FARM_SOWING_PLAN)
        assertNextLine(FARM_FINISHED_ACTIONS)
        assertNextLine("[DEBUG] Harvest Estimate: Required actions on tile 24 were not performed: HARVESTING.")

        // Oct2 (tick 21, yearTick 20)
        skipUntilString("[DEBUG] Harvest Estimate: Required actions on tile 24 were not performed: HARVESTING.")
        assertNextLine("[INFO] Harvest Estimate: Harvest estimate on tile 24 changed to 0 g of APPLE.")
    }
}

/**
 * Tests a cycle of Apple
 */
class AppleCyclePart4 : AppleCycleAbstract() {
    override val name = "Apple Test new cutting"
    override suspend fun run() {
        // Nov1, new cutting
        skipUntilString("[INFO] Simulation Info: Tick 22 started at tick 21 within the year.")
        assertNextLine(SOIL_MOISTURE)
        skipUntilString(MACHINE_0_CUT_22)
        assertNextLine(MACHINE_0_CUT_23)
        assertNextLine(MACHINE_0_RETURN)
        assertNextLine(FARM_FINISHED_ACTIONS)
    }
}
