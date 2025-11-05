package de.unisaarland.cs.se.selab.systemtest.selab25.mapParserTests

import de.unisaarland.cs.se.selab.systemtest.selab25.utils.ExampleSystemTestExtension

/**
 * machine is in other farm's shed
 */
class StealMachine : ExampleSystemTestExtension() {
    override val name = "StealMachine"
    override val description = "Test machine is in other farm's shed"

    // Paths are relative from the `src/systemtest/resources` directory.
    override val farms = "invalidFarmJson/farmStealMachine.json"
    override val scenario = "example/scenario.json"
    override val map = "invalidFarmJson/mapStealMachine.json"

    override val logLevel = "DEBUG"
    override val maxTicks = 1
    override val startYearTick = 1

    override suspend fun run() {
        assertNextLine("[INFO] Initialization Info: mapStealMachine.json successfully parsed and validated.")
        assertNextLine("[IMPORTANT] Initialization Info: farmStealMachine.json is invalid.")
    }
}
