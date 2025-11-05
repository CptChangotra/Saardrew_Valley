package de.unisaarland.cs.se.selab.systemtest.selab25.basictests

import de.unisaarland.cs.se.selab.systemtest.selab25.utils.ExampleSystemTestExtension

/**
 * Testing harvesting, shed return, and unloading of harvest with shedBackMap.
 */
class HarvestShedBackTest : ExampleSystemTestExtension() {
    override val name = "Harvest Shed Back"
    override val description = "Tests harvesting and shed return."

    override val farms = "example/shedReturnFarms.json"
    override val scenario = "example/scenario.json"
    override val map = "example/shedBackMap.json"

    override val logLevel = "DEBUG"
    override val maxTicks = 1
    override val startYearTick = 13

    override suspend fun run() {
        skipUntilString("[IMPORTANT] Farm Action: Machine 0 performs HARVESTING on tile 3 for 4 days.")
        assertNextLine("[IMPORTANT] Farm Harvest: Machine 0 has collected 1200000 g of CHERRY harvest.")
        assertNextLine("[IMPORTANT] Farm Machine: Machine 0 is finished and returns to the shed at 0.")
        assertNextLine("[IMPORTANT] Farm Machine: Machine 0 unloads 1200000 g of CHERRY harvest in the shed.")
        assertNextLine("[IMPORTANT] Farm: Farm 0 finished its actions.")
    }
}
