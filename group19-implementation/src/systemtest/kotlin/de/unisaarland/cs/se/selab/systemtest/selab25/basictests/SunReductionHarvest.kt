package de.unisaarland.cs.se.selab.systemtest.selab25.basictests

import de.unisaarland.cs.se.selab.systemtest.selab25.utils.ExampleSystemTestExtension

/**
 * Testing only harvest estimate changes
 */
class SunReductionHarvest : ExampleSystemTestExtension() {
    override val name = "Harvest Estimates and Sun"
    override val description = "Tests harvest estimate changes."

    override val farms = "example/bigIncidentFarms.json"
    override val scenario = "example/scenario.json"
    override val map = "example/bigIncidentMap.json"

    override val logLevel = "DEBUG"
    override val maxTicks = 3
    override val startYearTick = 1

    override suspend fun run() {
        // tick 0
        skipUntilString("[INFO] Harvest Estimate: Harvest estimate on tile 40 changed to 1530000 g of APPLE.")
        assertNextLine("[INFO] Harvest Estimate: Harvest estimate on tile 41 changed to 1530000 g of APPLE.")
        assertNextLine("[INFO] Harvest Estimate: Harvest estimate on tile 42 changed to 1530000 g of APPLE.")

        // tick 1
        skipUntilString("[INFO] Harvest Estimate: Harvest estimate on tile 40 changed to 1377000 g of APPLE.")
        assertNextLine("[INFO] Harvest Estimate: Harvest estimate on tile 41 changed to 1377000 g of APPLE.")
        assertNextLine("[INFO] Harvest Estimate: Harvest estimate on tile 42 changed to 1377000 g of APPLE.")

        // tick 2
        skipUntilString("[INFO] Harvest Estimate: Harvest estimate on tile 40 changed to 1115370 g of APPLE.")
        assertNextLine("[INFO] Harvest Estimate: Harvest estimate on tile 41 changed to 1115370 g of APPLE.")
        assertNextLine("[INFO] Harvest Estimate: Harvest estimate on tile 42 changed to 1115370 g of APPLE.")
    }
}
