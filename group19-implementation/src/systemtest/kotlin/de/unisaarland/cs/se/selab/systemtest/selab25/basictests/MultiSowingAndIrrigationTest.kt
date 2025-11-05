package de.unisaarland.cs.se.selab.systemtest.selab25.basictests

import de.unisaarland.cs.se.selab.systemtest.selab25.utils.ExampleSystemTestExtension

private const val MACHINE0SHED = "[IMPORTANT] Farm Machine: Machine 0 is finished and returns to the shed at 64."
private const val MACHINE1SHED = "[IMPORTANT] Farm Machine: Machine 1 is finished and returns to the shed at 64."
private const val FINISH = "[IMPORTANT] Farm: Farm 0 finished its actions."
private const val START = "[IMPORTANT] Farm: Farm 0 starts its actions."

/**
 * Testing sowing and irrigating actions.
 */
class MultiSowingAndIrrigationTest : ExampleSystemTestExtension() {
    override val name = "Irrigation simulation"
    override val description = "Tests sowing and irrigating actions"

    override val farms = "example/irrigationFarms.json"
    override val scenario = "example/scenario.json"
    override val map = "example/irrigationMap.json"

    override val logLevel = "DEBUG"
    override val maxTicks = 2
    override val startYearTick = 11

    override suspend fun run() {
        skipUntilString(START)
        assertNextLine(
            "[DEBUG] Farm: Farm 0 has the " +
                "following active sowing plans it intends to pursue in this tick: 55."
        )
        assertNextLine("[IMPORTANT] Farm Action: Machine 0 performs SOWING on tile 54 for 1 days.")
        assertNextLine(
            "[IMPORTANT] Farm Sowing: " +
                "Machine 0 has sowed PUMPKIN according to sowing plan 55."
        )
        assertNextLine("[IMPORTANT] Farm Action: Machine 0 performs SOWING on tile 69 for 1 days.")
        assertNextLine("[IMPORTANT] Farm Sowing: Machine 0 has sowed PUMPKIN according to sowing plan 55.")
        assertNextLine("[IMPORTANT] Farm Action: Machine 0 performs SOWING on tile 84 for 1 days.")
        assertNextLine("[IMPORTANT] Farm Sowing: Machine 0 has sowed PUMPKIN according to sowing plan 55.")
        assertNextLine(MACHINE0SHED)
        assertNextLine(FINISH)
        skipUntilString("[INFO] Simulation Info: Tick 1 started at tick 12 within the year.")
        assertNextLine("[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 4 PLANTATION tiles.")
        assertNextLine(START)
        assertNextLine(
            "[DEBUG] Farm: Farm " +
                "0 has the following active sowing plans it intends to pursue in this tick: ."
        )
        assertNextLine("[IMPORTANT] Farm Action: Machine 1 performs IRRIGATING on tile 40 for 1 days.")
        assertNextLine("[IMPORTANT] Farm Action: Machine 1 performs IRRIGATING on tile 41 for 1 days.")
        assertNextLine("[IMPORTANT] Farm Action: Machine 1 performs IRRIGATING on tile 42 for 1 days.")
        // !!!!!!!!!!!!!! THESE IRRIGATION'S ARE NOT HAPPENING
        assertNextLine("[IMPORTANT] Farm Action: Machine 1 performs IRRIGATING on tile 43 for 1 days.")
        assertNextLine(MACHINE1SHED)
        assertNextLine(FINISH)
    }
}
