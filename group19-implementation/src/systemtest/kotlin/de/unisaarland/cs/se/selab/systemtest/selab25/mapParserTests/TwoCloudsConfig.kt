package de.unisaarland.cs.se.selab.systemtest.selab25.mapParserTests

import de.unisaarland.cs.se.selab.systemtest.selab25.utils.ExampleSystemTestExtension

/**
 * Test not more than one cloud will be created at any tile for the
 * initial cloud config
 */
class TwoCloudsConfig : ExampleSystemTestExtension() {
    override val name = "TwoCloudsConfig"
    override val description = "two clouds in the same tile in the initial configuration"

    // Paths are relative from the `src/systemtest/resources` directory.
    override val farms = "example/farms.json"
    override val scenario = "scenarioParser/scenarioTwoCloudsConfig.json"
    override val map = "example/map.json"

    override val logLevel = "DEBUG"
    override val maxTicks = 1
    override val startYearTick = 1

    override suspend fun run() {
        assertNextLine("[INFO] Initialization Info: map.json successfully parsed and validated.")
        assertNextLine("[INFO] Initialization Info: farms.json successfully parsed and validated.")
        assertNextLine("[IMPORTANT] Initialization Info: scenarioTwoCloudsConfig.json is invalid.")
    }
}
