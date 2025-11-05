package de.unisaarland.cs.se.selab.systemtest.selab25.basictests

import de.unisaarland.cs.se.selab.systemtest.selab25.utils.ExampleSystemTestExtension

/**
 * Testing only harvest estimate changes from the super big incident scenario.
 */
class HarvestEstimatesFromSuperBigScenarioTest : ExampleSystemTestExtension() {
    override val name = "Harvest Estimates Super Big Scenario"
    override val description = "Tests only the harvest estimate change logs."

    override val farms = "example/bigIncidentFarms.json"
    override val scenario = "example/superBigIncidentScenario.json"
    override val map = "example/bigIncidentMap.json"

    override val logLevel = "DEBUG"
    override val maxTicks = 1
    override val startYearTick = 13

    override suspend fun run() {
        skipUntilString("[INFO] Harvest Estimate: Harvest estimate on tile 40 changed to 1115370 g of APPLE.")
        assertNextLine("[INFO] Harvest Estimate: Harvest estimate on tile 41 changed to 0 g of APPLE.")
        assertNextLine("[INFO] Harvest Estimate: Harvest estimate on tile 42 changed to 1115370 g of APPLE.")
        assertNextLine("[INFO] Harvest Estimate: Harvest estimate on tile 54 changed to 360000 g of PUMPKIN.")
        assertNextLine("[INFO] Harvest Estimate: Harvest estimate on tile 69 changed to 360000 g of PUMPKIN.")
        assertNextLine("[INFO] Harvest Estimate: Harvest estimate on tile 84 changed to 360000 g of PUMPKIN.")
    }
}
