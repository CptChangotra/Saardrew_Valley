package de.unisaarland.cs.se.selab.systemtest.selab25.basictests

import de.unisaarland.cs.se.selab.systemtest.selab25.utils.ExampleSystemTestExtension

private const val MACHINE0SHED = "[IMPORTANT] Farm Machine: Machine 0 is finished and returns to the shed at 3."
private const val FINISH = "[IMPORTANT] Farm: Farm 0 finished its actions."
private const val START = "[IMPORTANT] Farm: Farm 0 starts its actions."
private const val PLAN = "[DEBUG] Farm: Farm 0 has the following active sowing plans " +
    "it intends to pursue in this tick: ."

/**
 * Testing a basic simulation run with sowing and weeding actions.
 */
class UnreachableMapSowingTest : ExampleSystemTestExtension() {
    override val name = "Unreachable sowing"
    override val description = "Tests sowing on the unreachable tile"

    override val farms = "example/unreachableFarms.json"
    override val scenario = "example/scenario.json"
    override val map = "example/unreachableMap.json"

    override val logLevel = "DEBUG"
    override val maxTicks = 5
    override val startYearTick = 11

    override suspend fun run() {
        skipUntilString("[IMPORTANT] Farm Action: Machine 0 performs SOWING on tile 5 for 4 days.")
        assertNextLine("[IMPORTANT] Farm Sowing: Machine 0 has sowed PUMPKIN according to sowing plan 1.")
        assertNextLine(MACHINE0SHED)
        assertNextLine(FINISH)
        assertNextLine("[INFO] Harvest Estimate: Harvest estimate on tile 5 changed to 450000 g of PUMPKIN.")

        skipUntilString(START)
        assertNextLine(PLAN)
        assertNextLine(FINISH)
        assertNextLine("[INFO] Harvest Estimate: Harvest estimate on tile 5 changed to 405000 g of PUMPKIN.")

        skipUntilString(
            "[DEBUG] Farm: Farm 0 has the following active sowing " +
                "plans it intends to pursue in this tick: ."
        )
        assertNextLine("[IMPORTANT] Farm Action: Machine 0 performs WEEDING on tile 5 for 4 days.")
        assertNextLine(MACHINE0SHED)
        assertNextLine(FINISH)
        assertNextLine("[INFO] Harvest Estimate: Harvest estimate on tile 5 changed to 364500 g of PUMPKIN.")

        assertNextLine("[INFO] Simulation Info: Tick 3 started at tick 14 within the year.")
        assertNextLine("[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.")
        assertNextLine(START)
        assertNextLine(
            "[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: ."
        )
        assertNextLine(FINISH)
        assertNextLine("[INFO] Harvest Estimate: Harvest estimate on tile 5 changed to 328050 g of PUMPKIN.")

        assertNextLine("[INFO] Simulation Info: Tick 4 started at tick 15 within the year.")
        assertNextLine("[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.")
        assertNextLine(START)
        assertNextLine(
            "[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: ."
        )
        assertNextLine("[IMPORTANT] Farm Action: Machine 0 performs WEEDING on tile 5 for 4 days.")
        assertNextLine(MACHINE0SHED)
        assertNextLine(FINISH)
        assertNextLine("[INFO] Harvest Estimate: Harvest estimate on tile 5 changed to 295245 g of PUMPKIN.")
    }
}
