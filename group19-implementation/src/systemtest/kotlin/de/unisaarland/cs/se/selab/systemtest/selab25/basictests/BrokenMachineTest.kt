package de.unisaarland.cs.se.selab.systemtest.selab25.basictests

import de.unisaarland.cs.se.selab.systemtest.selab25.utils.ExampleSystemTestExtension

/**
 * Testing broken machine incident, harvest estimates, and harvesting with shed return.
 */
class BrokenMachineTest : ExampleSystemTestExtension() {
    override val name = "Broken Machine Test"
    override val description = "Tests BROKEN_MACHINE for 2 ticks and then performs harvesting"

    override val farms = "example/shedReturnFarms.json"
    override val scenario = "example/brokenMachineScenario.json"
    override val map = "example/shedReturnMap.json"

    override val logLevel = "DEBUG"
    override val maxTicks = 4
    override val startYearTick = 11

    override suspend fun run() {
        // tick 0: mowing, broken machine, and harvest estimate
        skipUntilString("[IMPORTANT] Farm Action: Machine 0 performs MOWING on tile 3 for 4 days.")
        assertNextLine("[IMPORTANT] Farm Machine: Machine 0 is finished and returns to the shed at 0.")
        assertNextLine("[IMPORTANT] Farm: Farm 0 finished its actions.")
        assertNextLine("[IMPORTANT] Incident: Incident 1 of type BROKEN_MACHINE happened and affected tiles 0.")
        assertNextLine("[INFO] Harvest Estimate: Harvest estimate on tile 3 changed to 1080000 g of CHERRY.")

        // tick 1: no actions, only harvest estimate change
        skipUntilString("[INFO] Harvest Estimate: Harvest estimate on tile 3 changed to 972000 g of CHERRY.")

        // tick 2: no actions, only harvest estimate change
        skipUntilString("[INFO] Harvest Estimate: Harvest estimate on tile 3 changed to 874800 g of CHERRY.")

        // tick 3: machine is back to life, harvesting and unloading
        skipUntilString("[IMPORTANT] Farm Action: Machine 0 performs HARVESTING on tile 3 for 4 days.")
        assertNextLine("[IMPORTANT] Farm Harvest: Machine 0 has collected 874800 g of CHERRY harvest.")
        assertNextLine("[IMPORTANT] Farm Machine: Machine 0 is finished and returns to the shed at 4.")
        assertNextLine("[IMPORTANT] Farm Machine: Machine 0 unloads 874800 g of CHERRY harvest in the shed.")
        assertNextLine("[IMPORTANT] Farm: Farm 0 finished its actions.")

        // statistics
        skipUntilString("[IMPORTANT] Simulation Info: Simulation ended at tick 4.")
        assertNextLine("[IMPORTANT] Simulation Info: Simulation statistics are calculated.")
        assertNextLine("[IMPORTANT] Simulation Statistics: Farm 0 collected 874800 g of harvest.")
        assertNextLine("[IMPORTANT] Simulation Statistics: Total amount of POTATO harvested: 0 g.")
        assertNextLine("[IMPORTANT] Simulation Statistics: Total amount of WHEAT harvested: 0 g.")
        assertNextLine("[IMPORTANT] Simulation Statistics: Total amount of OAT harvested: 0 g.")
        assertNextLine("[IMPORTANT] Simulation Statistics: Total amount of PUMPKIN harvested: 0 g.")
        assertNextLine("[IMPORTANT] Simulation Statistics: Total amount of APPLE harvested: 0 g.")
        assertNextLine("[IMPORTANT] Simulation Statistics: Total amount of GRAPE harvested: 0 g.")
        assertNextLine("[IMPORTANT] Simulation Statistics: Total amount of ALMOND harvested: 0 g.")
        assertNextLine("[IMPORTANT] Simulation Statistics: Total amount of CHERRY harvested: 874800 g.")
        assertNextLine(
            "[IMPORTANT] Simulation Statistics: Total " +
                "harvest estimate still in fields and plantations: 0 g."
        )
    }
}
