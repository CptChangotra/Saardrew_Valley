package de.unisaarland.cs.se.selab.systemtest.selab25.basictests

import de.unisaarland.cs.se.selab.systemtest.selab25.utils.ExampleSystemTestExtension
import de.unisaarland.cs.se.selab.utils.YearTick

private const val FARM_START_STR = "[IMPORTANT] Farm: Farm 0 starts its actions."
private const val FARM_SOWING_PLAN = "[DEBUG] Farm: Farm 0 has the following active sowing plans " +
    "it intends to pursue in this tick: ."
private const val SOIL_MOISTURE = "[INFO] Soil Moisture: The soil moisture is below threshold in " +
    "0 FIELD and 0 PLANTATION tiles."
private const val MACHINE_0_CUT_22 = "[IMPORTANT] Farm Action: Machine 0 performs CUTTING on " +
    "tile 22 for 4 days."
private const val MACHINE_0_CUT_23 = "[IMPORTANT] Farm Action: Machine 0 performs CUTTING on " +
    "tile 23 for 4 days."

// private const val MACHINE_0_MOW_22 = "[IMPORTANT] Farm Action: Machine 0 performs MOWING on " +
//    "tile 22 for 4 days."
private const val MACHINE_0_MOW_23 = "[IMPORTANT] Farm Action: Machine 0 performs MOWING on " +
    "tile 23 for 4 days."
private const val MACHINE_0_MOW_25 = "[IMPORTANT] Farm Action: Machine 0 performs MOWING on " +
    "tile 25 for 4 days."
private const val MACHINE_0_RETURN = "[IMPORTANT] Farm Machine: Machine 0 is finished and returns to the shed at 0."
private const val MACHINE_2_HARVEST_22 = "[IMPORTANT] Farm Action: Machine 2 performs HARVESTING on tile 22 for 4 days."
private const val MACHINE_2_HARVEST_23 = "[IMPORTANT] Farm Action: Machine 2 performs HARVESTING on tile 23 for 4 days."
private const val MACHINE_2_RETURN = "[IMPORTANT] Farm Machine: Machine 2 is finished and returns to the shed at 0."
private const val FARM_FINISHED_ACTIONS = "[IMPORTANT] Farm: Farm 0 finished its actions."

/**
 * Tests Apple with Bee Happy and Drought
 */
abstract class AppleIncidentsAbstract : ExampleSystemTestExtension() {
    // override val name = "AppleLife"
    override val description = "Tests Apple with Incidents"

    override val map = "basicApple/mapAppleIncident.json"
    override val farms = "basicApple/farmAppleIncident.json"
    override val scenario = "basicApple/scenarioAppleIncident.json"

    override val logLevel = "DEBUG"

    override val maxTicks = 22
    override val startYearTick = YearTick.MARCH_1
}

/**
 * Tests a cycle of Apple
 */
class AppleIncidentsPart1 : AppleIncidentsAbstract() {
    override val name = "Apple Incidents Part1"
    override suspend fun run() {
        // tick 0 (March_1), cloud creation
        // tick 1 (March_2), drought on tile 24
        skipUntilString("[INFO] Simulation Info: Tick 1 started at tick 6 within the year.")
        assertNextLine(SOIL_MOISTURE)
        skipUntilString(FARM_START_STR)
        assertNextLine(FARM_SOWING_PLAN)
        assertNextLine(FARM_FINISHED_ACTIONS)
        assertNextLine("[IMPORTANT] Incident: Incident 333 of type DROUGHT happened and affected tiles 24.")
        skipUntilString("[INFO] Harvest Estimate: Harvest estimate on tile 24 changed to 0 g of APPLE.")
        assertNextLine("[INFO] Harvest Estimate: Harvest estimate on tile 25 changed to 1115370 g of APPLE.")
        // tick 2 (April_1),
        // tick 3 (April_2), Bee happy affects 23, 26 (no tile 24)
        skipUntilString("[INFO] Simulation Info: Tick 3 started at tick 8 within the year.")
        assertNextLine(SOIL_MOISTURE)
        skipUntilString(FARM_START_STR)
        assertNextLine(FARM_SOWING_PLAN)
        assertNextLine(FARM_FINISHED_ACTIONS)
        assertNextLine("[IMPORTANT] Incident: Incident 222 of type BEE_HAPPY happened and affected tiles 23,26.")
        // tick 4 (May_1)
        // tick 5 (May_2), BEE_HAPPY shouldn't affect anything
        skipUntilString("[INFO] Simulation Info: Tick 5 started at tick 10 within the year.")
        assertNextLine(SOIL_MOISTURE)
        skipUntilString(FARM_START_STR)
        assertNextLine(FARM_SOWING_PLAN)
        assertNextLine(FARM_FINISHED_ACTIONS)
        assertNextLine("[IMPORTANT] Incident: Incident 225 of type BEE_HAPPY happened and affected tiles .")
    }
}

/**
 * Tests a cycle of Apple
 */
class AppleIncidentsPart2 : AppleIncidentsAbstract() {
    override val name = "Apple Incidents Part2"
    override suspend fun run() {
        // tick 6 (June_1)
        skipUntilString("[INFO] Simulation Info: Tick 6 started at tick 11 within the year.")
        assertNextLine(SOIL_MOISTURE)
        skipUntilString(FARM_START_STR)
        assertNextLine(FARM_SOWING_PLAN)
        // assertNextLine(MACHINE_0_MOW_22)
        // assertNextLine(MACHINE_0_MOW_23)
        skipUntilString(MACHINE_0_MOW_23)
        assertNextLine(MACHINE_0_MOW_25)
        assertNextLine(MACHINE_0_RETURN)
        assertNextLine(FARM_FINISHED_ACTIONS)
        skipUntilString("[DEBUG] Harvest Estimate: Required actions on tile 26 were not performed: MOWING.")
        assertNextLine("[INFO] Harvest Estimate: Harvest estimate on tile 26 changed to 864232 g of APPLE.")
        // tick 8 (July_1) drought on tile 25
        skipUntilString("[INFO] Simulation Info: Tick 8 started at tick 13 within the year.")
        assertNextLine(SOIL_MOISTURE)
        skipUntilString(FARM_START_STR)
        assertNextLine(FARM_SOWING_PLAN)
        assertNextLine(FARM_FINISHED_ACTIONS)
        assertNextLine("[IMPORTANT] Incident: Incident 334 of type DROUGHT happened and affected tiles 25.")
        skipUntilString("[INFO] Harvest Estimate: Harvest estimate on tile 25 changed to 0 g of APPLE.")
        assertNextLine("[INFO] Harvest Estimate: Harvest estimate on tile 26 changed to 567021 g of APPLE.")
        // tick 0 (July_2) drought on tile 26
        skipUntilString("[INFO] Simulation Info: Tick 9 started at tick 14 within the year.")
        assertNextLine(SOIL_MOISTURE)
        skipUntilString(FARM_START_STR)
        assertNextLine(FARM_SOWING_PLAN)
        assertNextLine(FARM_FINISHED_ACTIONS)
        assertNextLine("[IMPORTANT] Incident: Incident 335 of type DROUGHT happened and affected tiles 26.")
        // skipUntilString("[INFO] Harvest Estimate: Harvest estimate on tile 26 changed to 0 g of APPLE.")
    }
}

/**
 * Tests a cycle of Apple
 */
class AppleIncidentsPart3 : AppleIncidentsAbstract() {
    override val name = "Apple Incidents Part3"
    override suspend fun run() {
        // Sep1
        skipUntilString("[INFO] Simulation Info: Tick 12 started at tick 17 within the year.")
        assertNextLine(SOIL_MOISTURE)
        skipUntilString(FARM_START_STR)
        assertNextLine(FARM_SOWING_PLAN)
        assertNextLine(MACHINE_2_HARVEST_22)
        assertNextLine("[IMPORTANT] Farm Harvest: Machine 2 has collected 167407 g of APPLE harvest.")
        assertNextLine(MACHINE_2_HARVEST_23)
        assertNextLine("[IMPORTANT] Farm Harvest: Machine 2 has collected 334818 g of APPLE harvest.")
        assertNextLine(MACHINE_2_RETURN)
        assertNextLine("[IMPORTANT] Farm Machine: Machine 2 unloads 502225 g of APPLE harvest in the shed.")
        assertNextLine(FARM_FINISHED_ACTIONS)
        // Nov1
        skipUntilString("[INFO] Simulation Info: Tick 16 started at tick 21 within the year.")
        assertNextLine(SOIL_MOISTURE)
        skipUntilString(FARM_START_STR)
        assertNextLine(FARM_SOWING_PLAN)
        assertNextLine(MACHINE_0_CUT_22)
        assertNextLine(MACHINE_0_CUT_23)
        assertNextLine(MACHINE_0_RETURN)
        assertNextLine(FARM_FINISHED_ACTIONS)
        assertNextLine("[INFO] Harvest Estimate: Harvest estimate on tile 22 changed to 1700000 g of APPLE.")
    }
}
