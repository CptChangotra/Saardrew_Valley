package de.unisaarland.cs.se.selab.systemtest.selab25.basictests

import de.unisaarland.cs.se.selab.systemtest.selab25.utils.ExampleSystemTestExtension

/**
 * Testing machine output and broken machine incident.
 */
class StuckAndBroken : ExampleSystemTestExtension() {
    override val name = "Machine And Incident"
    override val description = "Tests machine getting stuck and being broken."

    override val farms = "example/stuckForestFarms.json"
    override val scenario = "example/stuckForestScenario.json"
    override val map = "example/stuckForestMap.json"

    override val logLevel = "DEBUG"
    override val maxTicks = 1
    override val startYearTick = 16

    override suspend fun run() {
        skipUntilString("[IMPORTANT] Farm Action: Machine 0 performs HARVESTING on tile 8 for 14 days.")
        assertNextLine("[IMPORTANT] Farm Harvest: Machine 0 has collected 800000 g of ALMOND harvest.")
        assertNextLine("[IMPORTANT] Farm Machine: Machine 0 is finished but failed to return.")
        assertNextLine("[IMPORTANT] Farm: Farm 0 finished its actions.")

        skipUntilString("[IMPORTANT] Incident: Incident 1 of type BROKEN_MACHINE happened and affected tiles 8.")
    }
}
