package de.unisaarland.cs.se.selab.systemtest.selab25.basictests

import de.unisaarland.cs.se.selab.systemtest.selab25.utils.ExampleSystemTestExtension
import de.unisaarland.cs.se.selab.utils.YearTick

private const val FARM_START_STR = "[IMPORTANT] Farm: Farm 0 starts its actions."
private const val FARM_SOWING_PLAN = "[DEBUG] Farm: Farm 0 has the following active sowing plans " +
    "it intends to pursue in this tick: ."
private const val SOIL_MOISTURE = "[INFO] Soil Moisture: The soil moisture is below threshold in " +
    "0 FIELD and 0 PLANTATION tiles."
private const val MACHINE_2_HARVEST_22 = "[IMPORTANT] Farm Action: Machine 2 performs HARVESTING " +
    "on tile 22 for 12 days."
private const val MACHINE_2_RETURN = "[IMPORTANT] Farm Machine: Machine 2 is finished and returns to the shed at 0."
private const val FARM_FINISHED_ACTIONS = "[IMPORTANT] Farm: Farm 0 finished its actions."

/**
 * Tests apple late harvest
 */
class AppleLateHarvest : ExampleSystemTestExtension() {
    override val name = "AppeLateHarvest"
    override val description = "Tests simulation start from late harvest period"

    override val map = "basicApple/appleMap.json"
    override val farms = "basicApple/appleBusyMachine.json"
    override val scenario = "example/scenario.json"

    override val logLevel = "DEBUG"
    override val maxTicks = 1
    override val startYearTick = YearTick.OCTOBER_2

    override suspend fun run() {
        // Oct2, harvest delayed by 1 tick on tile 22, no harvest on 23,24
        skipUntilString("[INFO] Simulation Info: Tick 0 started at tick 20 within the year.")
        assertNextLine(SOIL_MOISTURE)
        skipUntilString(FARM_START_STR)
        assertNextLine(FARM_SOWING_PLAN)
        assertNextLine(MACHINE_2_HARVEST_22)
        assertNextLine("[IMPORTANT] Farm Harvest: Machine 2 has collected 850000 g of APPLE harvest.")
        assertNextLine(MACHINE_2_RETURN)
        assertNextLine("[IMPORTANT] Farm Machine: Machine 2 unloads 850000 g of APPLE harvest in the shed.")
        assertNextLine(FARM_FINISHED_ACTIONS)
        assertNextLine("[DEBUG] Harvest Estimate: Required actions on tile 23 were not performed: HARVESTING.")
        assertNextLine("[INFO] Harvest Estimate: Harvest estimate on tile 23 changed to 0 g of APPLE.")
        assertNextLine("[DEBUG] Harvest Estimate: Required actions on tile 24 were not performed: HARVESTING.")
        assertNextLine("[INFO] Harvest Estimate: Harvest estimate on tile 24 changed to 0 g of APPLE.")
        // !! above two lines didn't exist

        skipUntilString("[IMPORTANT] Simulation Info: Simulation statistics are calculated.")
        assertNextLine("[IMPORTANT] Simulation Statistics: Farm 0 collected 850000 g of harvest.")
    }
}
