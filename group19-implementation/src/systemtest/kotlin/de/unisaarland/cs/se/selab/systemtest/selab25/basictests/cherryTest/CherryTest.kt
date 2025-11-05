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
class CherryTest : ExampleSystemTestExtension() {
    override val name = "CherryTest harvest lost"
    override val description = "Tests a full cycle of cherry with different lost of Harvest"

    override val farms = "cherry_test/cherry_test_farm.json"
    override val scenario = "cherry_test/cherry_test_scenario.json"
    override val map = "cherry_test/cherry_test_map.json"

    override val logLevel = "DEBUG"
    override val maxTicks = 19
    override val startYearTick = 21

    override suspend fun run() {
        skipUntilString("[INFO] Simulation Info: Tick 16 started at tick 13 within the year.")
        assertNextLine(LOW_SOIL_MOISTURE_0_0)
        assertNextLine(FARM_START_STR)
        assertNextLine(SOWING_PLAN_0)
        assertNextLine("[IMPORTANT] Farm Action: Machine 0 performs HARVESTING on tile 1 for 10 days.")
        assertNextLine("[IMPORTANT] Farm Harvest: Machine 0 has collected 787320 g of CHERRY harvest.")
        assertNextLine(MACHINE_BACK)
        assertNextLine("[IMPORTANT] Farm Machine: Machine 0 unloads 787320 g of CHERRY harvest in the shed.")
        assertNextLine(FARM_FINISHED_ACTIONS)
        assertNextLine("[INFO] Harvest Estimate: Harvest estimate on tile 2 changed to 708588 g of CHERRY.")
        assertNextLine("[INFO] Harvest Estimate: Harvest estimate on tile 3 changed to 708588 g of CHERRY.")
        assertNextLine("[INFO] Harvest Estimate: Harvest estimate on tile 4 changed to 637729 g of CHERRY.")
        assertNextLine("[INFO] Simulation Info: Tick 17 started at tick 14 within the year.")
        assertNextLine(LOW_SOIL_MOISTURE_0_0)
        assertNextLine(FARM_START_STR)
        assertNextLine(SOWING_PLAN_0)
        assertNextLine("[IMPORTANT] Farm Action: Machine 0 performs HARVESTING on tile 2 for 10 days.")
        assertNextLine("[IMPORTANT] Farm Harvest: Machine 0 has collected 708588 g of CHERRY harvest.")
        assertNextLine(MACHINE_BACK)
        assertNextLine("[IMPORTANT] Farm Machine: Machine 0 unloads 708588 g of CHERRY harvest in the shed.")
        assertNextLine(FARM_FINISHED_ACTIONS)
        assertNextLine("[DEBUG] Harvest Estimate: Required actions on tile 3 were not performed: HARVESTING.")
        assertNextLine("[INFO] Harvest Estimate: Harvest estimate on tile 3 changed to 191318 g of CHERRY.")
        assertNextLine("[DEBUG] Harvest Estimate: Required actions on tile 4 were not performed: HARVESTING.")
        assertNextLine("[INFO] Harvest Estimate: Harvest estimate on tile 4 changed to 172186 g of CHERRY.")
        assertNextLine("[INFO] Simulation Info: Tick 18 started at tick 15 within the year.")
        assertNextLine(LOW_SOIL_MOISTURE_0_0)
        assertNextLine(FARM_START_STR)
        assertNextLine(SOWING_PLAN_0)
        assertNextLine("[IMPORTANT] Farm Action: Machine 0 performs HARVESTING on tile 3 for 10 days.")
        assertNextLine("[IMPORTANT] Farm Harvest: Machine 0 has collected 191318 g of CHERRY harvest.")
        assertNextLine(MACHINE_BACK)
        assertNextLine("[IMPORTANT] Farm Machine: Machine 0 unloads 191318 g of CHERRY harvest in the shed.")
        assertNextLine(FARM_FINISHED_ACTIONS)
        assertNextLine("[DEBUG] Harvest Estimate: Required actions on tile 4 were not performed: HARVESTING.")
        assertNextLine("[INFO] Harvest Estimate: Harvest estimate on tile 4 changed to 0 g of CHERRY.")
        skipUntilString("[IMPORTANT] Simulation Info: Simulation statistics are calculated.")
        assertNextLine("[IMPORTANT] Simulation Statistics: Farm 0 collected 1687226 g of harvest.")
        skipUntilString("[IMPORTANT] Simulation Statistics: Total amount of ALMOND harvested: 0 g.")
        assertNextLine("[IMPORTANT] Simulation Statistics: Total amount of CHERRY harvested: 1687226 g.")
        assertNextLine(
            "[IMPORTANT] Simulation Statistics: Total harvest estimate " +
                "still in fields and plantations: 0 g."
        )
    }
}
