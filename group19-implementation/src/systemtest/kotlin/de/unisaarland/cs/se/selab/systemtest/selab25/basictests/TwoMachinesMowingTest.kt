package de.unisaarland.cs.se.selab.systemtest.selab25.basictests

import de.unisaarland.cs.se.selab.systemtest.selab25.utils.ExampleSystemTestExtension

/**
 * Testing multiple machines mowing and resulting harvest estimates/statistics.
 */
class TwoMachinesMowingTest : ExampleSystemTestExtension() {
    override val name = "Two Machines Mowing"
    override val description = "Tests mowing actions by two machines and harvest estimate updates."

    override val farms = "example/twoMachinesFarm.json"
    override val scenario = "example/scenario.json"
    override val map = "example/twoMachinesMap.json"

    override val logLevel = "DEBUG"
    override val maxTicks = 1
    override val startYearTick = 13

    override suspend fun run() {
        skipUntilString("[IMPORTANT] Farm Action: Machine 0 performs MOWING on tile 40 for 5 days.")
        assertNextLine("[IMPORTANT] Farm Action: Machine 0 performs MOWING on tile 41 for 5 days.")
        assertNextLine("[IMPORTANT] Farm Machine: Machine 0 is finished and returns to the shed at 64.")
        assertNextLine("[IMPORTANT] Farm Action: Machine 1 performs MOWING on tile 42 for 10 days.")
        assertNextLine("[IMPORTANT] Farm Machine: Machine 1 is finished and returns to the shed at 64.")
        assertNextLine("[IMPORTANT] Farm: Farm 0 finished its actions.")

        assertNextLine("[DEBUG] Harvest Estimate: Required actions on tile 43 were not performed: MOWING.")
        assertNextLine("[INFO] Harvest Estimate: Harvest estimate on tile 43 changed to 1080000 g of GRAPE.")

        skipUntilString(
            "[IMPORTANT] Simulation Statistics: " +
                "Total harvest estimate still in fields and plantations: 4680000 g."
        )
    }
}
