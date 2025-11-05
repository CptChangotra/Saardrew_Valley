package de.unisaarland.cs.se.selab.systemtest.selab25.basictests

import de.unisaarland.cs.se.selab.systemtest.selab25.utils.ExampleSystemTestExtension

/**
 * Testing harvesting where the machine fails to return to the shed.
 */
class HarvestStuckMachineTest : ExampleSystemTestExtension() {
    override val name = "Harvest Stuck Machine"
    override val description = "Tests harvesting with machine stuck (failed to return) and final statistics."

    override val farms = "example/stuckFarms.json"
    override val scenario = "example/scenario.json"
    override val map = "example/stuckMap.json"

    override val logLevel = "DEBUG"
    override val maxTicks = 1
    override val startYearTick = 13

    override suspend fun run() {
        skipUntilString("[IMPORTANT] Farm Action: Machine 0 performs HARVESTING on tile 3 for 4 days.")
        assertNextLine("[IMPORTANT] Farm Harvest: Machine 0 has collected 1200000 g of CHERRY harvest.")
        assertNextLine("[IMPORTANT] Farm Machine: Machine 0 is finished but failed to return.")
        assertNextLine("[IMPORTANT] Farm: Farm 0 finished its actions.")

        // Simulation ended
        skipUntilString("[IMPORTANT] Simulation Info: Simulation ended at tick 1.")
        assertNextLine("[IMPORTANT] Simulation Info: Simulation statistics are calculated.")
        assertNextLine("[IMPORTANT] Simulation Statistics: Farm 0 collected 1200000 g of harvest.")
        assertNextLine("[IMPORTANT] Simulation Statistics: Total amount of POTATO harvested: 0 g.")
        assertNextLine("[IMPORTANT] Simulation Statistics: Total amount of WHEAT harvested: 0 g.")
        assertNextLine("[IMPORTANT] Simulation Statistics: Total amount of OAT harvested: 0 g.")
        assertNextLine("[IMPORTANT] Simulation Statistics: Total amount of PUMPKIN harvested: 0 g.")
        assertNextLine("[IMPORTANT] Simulation Statistics: Total amount of APPLE harvested: 0 g.")
        assertNextLine("[IMPORTANT] Simulation Statistics: Total amount of GRAPE harvested: 0 g.")
        assertNextLine("[IMPORTANT] Simulation Statistics: Total amount of ALMOND harvested: 0 g.")
        assertNextLine("[IMPORTANT] Simulation Statistics: Total amount of CHERRY harvested: 1200000 g.")
        assertNextLine(
            "[IMPORTANT] Simulation Statistics: Total harvest estimate still " +
                "in fields and plantations: 0 g."
        )
    }
}
