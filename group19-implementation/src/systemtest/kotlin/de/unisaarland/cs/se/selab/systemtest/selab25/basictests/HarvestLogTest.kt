package de.unisaarland.cs.se.selab.systemtest.selab25.basictests

import de.unisaarland.cs.se.selab.systemtest.selab25.utils.ExampleSystemTestExtension

/**
 * Testing order of logging
 */
class HarvestLogTest : ExampleSystemTestExtension() {
    override val name = "Harvest Log 1"
    override val description = "Tests logging of harvest changed because of missed actions 1"

    override val map = "example/simulationMap.json"
    override val farms = "example/oneMachineFarm.json"
    override val scenario = "example/scenario.json"

    override val logLevel = "DEBUG"
    override val maxTicks = 4
    override val startYearTick = 1

    override suspend fun run() {
        skipUntilString("[DEBUG] Harvest Estimate: Required actions on tile 40 were not performed: CUTTING.")
        assertNextLine("[INFO] Harvest Estimate: Harvest estimate on tile 40 changed to 600000 g of CHERRY.")
        assertNextLine("[DEBUG] Harvest Estimate: Required actions on tile 41 were not performed: CUTTING.")
        assertNextLine("[INFO] Harvest Estimate: Harvest estimate on tile 41 changed to 451724 g of APPLE.")
    }
}
