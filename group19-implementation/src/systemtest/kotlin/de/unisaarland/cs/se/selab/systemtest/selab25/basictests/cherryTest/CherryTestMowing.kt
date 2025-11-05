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
class CherryTestMowing : ExampleSystemTestExtension() {
    override val name = "CherryTest mowing"
    override val description = "Tests a full cycle of cherry with mowing action"

    override val farms = "cherry_test/cherry_test_farm.json"
    override val scenario = "cherry_test/cherry_test_scenario.json"
    override val map = "cherry_test/cherry_test_map.json"

    override val logLevel = "DEBUG"
    override val maxTicks = 15
    override val startYearTick = 21

    override suspend fun run() {
        skipUntilString("[INFO] Simulation Info: Tick 12 started at tick 9 within the year.")
        assertNextLine(LOW_SOIL_MOISTURE_0_0)
        assertNextLine(FARM_START_STR)
        assertNextLine(SOWING_PLAN_0)
        assertNextLine(FARM_FINISHED_ACTIONS)
        assertNextLine("[INFO] Harvest Estimate: Harvest estimate on tile 1 changed to 1080000 g of CHERRY.")
        assertNextLine("[INFO] Harvest Estimate: Harvest estimate on tile 2 changed to 1080000 g of CHERRY.")
        assertNextLine("[INFO] Harvest Estimate: Harvest estimate on tile 3 changed to 1080000 g of CHERRY.")
        assertNextLine("[INFO] Harvest Estimate: Harvest estimate on tile 4 changed to 1080000 g of CHERRY.")
        skipUntilString("[INFO] Simulation Info: Tick 14 started at tick 11 within the year.")
        assertNextLine(LOW_SOIL_MOISTURE_0_0)
        assertNextLine(FARM_START_STR)
        assertNextLine(SOWING_PLAN_0)
        assertNextLine("[IMPORTANT] Farm Action: Machine 1 performs MOWING on tile 1 for 7 days.")
        assertNextLine("[IMPORTANT] Farm Action: Machine 1 performs MOWING on tile 2 for 7 days.")
        assertNextLine("[IMPORTANT] Farm Machine: Machine 1 is finished and returns to the shed at 0.")
        assertNextLine("[IMPORTANT] Farm Action: Machine 0 performs MOWING on tile 3 for 10 days.")
        assertNextLine(MACHINE_BACK)
        assertNextLine(FARM_FINISHED_ACTIONS)
        assertNextLine("[INFO] Harvest Estimate: Harvest estimate on tile 1 changed to 874800 g of CHERRY.")
        assertNextLine("[INFO] Harvest Estimate: Harvest estimate on tile 2 changed to 874800 g of CHERRY.")
        assertNextLine("[INFO] Harvest Estimate: Harvest estimate on tile 3 changed to 874800 g of CHERRY.")
        assertNextLine("[DEBUG] Harvest Estimate: Required actions on tile 4 were not performed: MOWING.")
        assertNextLine("[INFO] Harvest Estimate: Harvest estimate on tile 4 changed to 787320 g of CHERRY.")
    }
}
