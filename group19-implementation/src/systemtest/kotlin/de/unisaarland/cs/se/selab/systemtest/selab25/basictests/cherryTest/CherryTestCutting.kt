package de.unisaarland.cs.se.selab.systemtest.selab25.basictests.cherryTest

import de.unisaarland.cs.se.selab.systemtest.selab25.utils.ExampleSystemTestExtension

private const val FARM_START_STR = "[IMPORTANT] Farm: Farm 0 starts its actions."
private const val FARM_FINISHED_ACTIONS = "[IMPORTANT] Farm: Farm 0 finished its actions."
private const val LOW_SOIL_MOISTURE_0_0 = "[INFO] Soil Moisture: The soil moisture is below threshold" +
    " in 0 FIELD and 0 PLANTATION tiles."
private const val SOWING_PLAN_0 = "[DEBUG] Farm: Farm 0 has the following active " +
    "sowing plans it intends to pursue in this tick: ."
private const val MACHINE_BACK = "[IMPORTANT] Farm Machine: Machine 0 is finished " +
    "and returns to the shed at 0."

/**
 * test
 */
class CherryTestCutting : ExampleSystemTestExtension() {
    override val name = "CherryTest Cutting"
    override val description = "Tests a full cycle of cherry with cutting action"

    override val farms = "cherry_test/cherry_test_farm.json"
    override val scenario = "cherry_test/cherry_test_scenario.json"
    override val map = "cherry_test/cherry_test_map.json"

    override val logLevel = "DEBUG"
    override val maxTicks = 9
    override val startYearTick = 21

    override suspend fun run() {
        skipUntilString("[INFO] Simulation Info: Tick 0 started at tick 21 within the year.")
        assertNextLine(LOW_SOIL_MOISTURE_0_0)
        assertNextLine(FARM_START_STR)
        assertNextLine(SOWING_PLAN_0)
        assertNextLine("[IMPORTANT] Farm Action: Machine 0 performs CUTTING on tile 1 for 10 days.")
        assertNextLine(MACHINE_BACK)
        assertNextLine(FARM_FINISHED_ACTIONS)
        skipUntilString("[INFO] Simulation Info: Tick 1 started at tick 22 within the year.")
        assertNextLine(LOW_SOIL_MOISTURE_0_0)
        assertNextLine(FARM_START_STR)
        assertNextLine(SOWING_PLAN_0)
        assertNextLine("[IMPORTANT] Farm Action: Machine 0 performs CUTTING on tile 2 for 10 days.")
        assertNextLine(MACHINE_BACK)
        assertNextLine(FARM_FINISHED_ACTIONS)
        assertNextLine("[INFO] Simulation Info: Tick 2 started at tick 23 within the year.")
        skipUntilString("[INFO] Simulation Info: Tick 6 started at tick 3 within the year.")
        assertNextLine(LOW_SOIL_MOISTURE_0_0)
        assertNextLine(FARM_START_STR)
        assertNextLine(SOWING_PLAN_0)
        assertNextLine("[IMPORTANT] Farm Action: Machine 0 performs CUTTING on tile 3 for 10 days.")
        assertNextLine(MACHINE_BACK)
        assertNextLine(FARM_FINISHED_ACTIONS)
        skipUntilString("[INFO] Simulation Info: Tick 7 started at tick 4 within the year.")
        assertNextLine(LOW_SOIL_MOISTURE_0_0)
        assertNextLine(FARM_START_STR)
        assertNextLine(SOWING_PLAN_0)
        assertNextLine("[IMPORTANT] Farm Action: Machine 0 performs CUTTING on tile 4 for 10 days.")
        assertNextLine(MACHINE_BACK)
        assertNextLine(FARM_FINISHED_ACTIONS)
    }
}
