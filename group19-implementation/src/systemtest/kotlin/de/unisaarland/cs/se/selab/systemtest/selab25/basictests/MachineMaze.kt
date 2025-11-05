package de.unisaarland.cs.se.selab.systemtest.selab25.basictests

import de.unisaarland.cs.se.selab.systemtest.selab25.utils.ExampleSystemTestExtension

/**
 *
 */
class MachineMaze : ExampleSystemTestExtension() {
    override val name = "MachineMaze"
    override val description = "tests different scenario for machine movement and harvest,mainly connected with village"

    override val farms = "machineMaze/farms.json"
    override val scenario = "machineMaze/scenario.json"
    override val map = "machineMaze/map.json"

    override val logLevel = "DEBUG"
    override val maxTicks = 4
    override val startYearTick = 14

    override suspend fun run() {
        skipUntilString("[INFO] Simulation Info: Tick 3 started at tick 17 within the year.")
        skipUntilString("[IMPORTANT] Farm Action: Machine 0 performs HARVESTING on tile 1 for 4 days.")
        assertNextLine("[IMPORTANT] Farm Harvest: Machine 0 has collected 1200000 g of GRAPE harvest.")
        assertNextLine("[IMPORTANT] Farm Action: Machine 0 performs HARVESTING on tile 3 for 4 days.")
        assertNextLine("[IMPORTANT] Farm Harvest: Machine 0 has collected 1200000 g of GRAPE harvest.")
        assertNextLine("[IMPORTANT] Farm Machine: Machine 0 is finished and returns to the shed at 10.")
        assertNextLine("[IMPORTANT] Farm Machine: Machine 0 unloads 2400000 g of GRAPE harvest in the shed.")
        assertNextLine("[IMPORTANT] Farm Action: Machine 1 performs HARVESTING on tile 2 for 10 days.")
        assertNextLine("[IMPORTANT] Farm Harvest: Machine 1 has collected 1200000 g of GRAPE harvest.")
        assertNextLine("[IMPORTANT] Farm Machine: Machine 1 is finished but failed to return.")
        assertNextLine("[IMPORTANT] Farm Action: Machine 2 performs HARVESTING on tile 5 for 10 days.")
        assertNextLine("[IMPORTANT] Farm Harvest: Machine 2 has collected 1200000 g of GRAPE harvest.")
        assertNextLine("[IMPORTANT] Farm Machine: Machine 2 is finished and returns to the shed at 11.")
        assertNextLine("[IMPORTANT] Farm Machine: Machine 2 unloads 1200000 g of GRAPE harvest in the shed.")
        assertNextLine("[IMPORTANT] Farm Action: Machine 3 performs HARVESTING on tile 4 for 10 days.")
    }
}
