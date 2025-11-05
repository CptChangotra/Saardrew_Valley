package de.unisaarland.cs.se.selab.systemtest.selab25.basictests

import de.unisaarland.cs.se.selab.systemtest.selab25.utils.ExampleSystemTestExtension
import de.unisaarland.cs.se.selab.utils.YearTick

/**
 * Tests two sowing plans
 */
class MultipleSowingPlansTest : ExampleSystemTestExtension() {
    override val name = "MultipleSowingPlansTest"
    override val description = "Tests two sowing plans"

    override val map = "multiple_sowing_plans/map.json"
    override val farms = "multiple_sowing_plans/farms.json"
    override val scenario = "example/scenario.json"

    override val logLevel = "DEBUG"
    override val maxTicks = 4
    override val startYearTick = YearTick.OCTOBER_2

    override suspend fun run() {
        val soilMoistureStr =
            "[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles."
        val startFarmActionsStr = "[IMPORTANT] Farm: Farm 0 starts its actions."
        val endFarmActionsStr = "[IMPORTANT] Farm: Farm 0 finished its actions."
        val machineReturns = "[IMPORTANT] Farm Machine: Machine 0 is finished and returns to the shed at 0."

        skipUntilString("[INFO] Simulation Info: Tick 0 started at tick 20 within the year.")
        assertNextLine(soilMoistureStr)
        assertNextLine(startFarmActionsStr)
        assertNextLine(
            "[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: 0,1,2,3."
        )
        assertNextLine("[IMPORTANT] Farm Action: Machine 0 performs SOWING on tile 1 for 6 days.")
        assertNextLine("[IMPORTANT] Farm Sowing: Machine 0 has sowed WHEAT according to sowing plan 0.")
        assertNextLine("[IMPORTANT] Farm Action: Machine 0 performs SOWING on tile 2 for 6 days.")
        assertNextLine("[IMPORTANT] Farm Sowing: Machine 0 has sowed WHEAT according to sowing plan 0.")
        assertNextLine(machineReturns)
        assertNextLine(endFarmActionsStr)

        skipUntilString("[INFO] Simulation Info: Tick 1 started at tick 21 within the year.")
        assertNextLine(soilMoistureStr)
        assertNextLine(startFarmActionsStr)
        assertNextLine(
            "[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: 1,2,3."
        )
        assertNextLine("[IMPORTANT] Farm Action: Machine 0 performs SOWING on tile 3 for 6 days.")
        assertNextLine("[IMPORTANT] Farm Sowing: Machine 0 has sowed WHEAT according to sowing plan 1.")
        assertNextLine(machineReturns)
        assertNextLine(endFarmActionsStr)

        skipUntilString("[INFO] Simulation Info: Tick 2 started at tick 22 within the year.")
        assertNextLine(soilMoistureStr)
        assertNextLine(startFarmActionsStr)
        assertNextLine(
            "[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: 2,3."
        )
        assertNextLine("[IMPORTANT] Farm Action: Machine 0 performs SOWING on tile 6 for 6 days.")
        assertNextLine("[IMPORTANT] Farm Sowing: Machine 0 has sowed WHEAT according to sowing plan 2.")
        assertNextLine(machineReturns)
        assertNextLine(endFarmActionsStr)

        skipUntilString("[INFO] Simulation Info: Tick 3 started at tick 23 within the year.")
        assertNextLine(soilMoistureStr)
        assertNextLine(startFarmActionsStr)
        assertNextLine(
            "[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: 3."
        )
        assertNextLine(endFarmActionsStr)
    }
}
