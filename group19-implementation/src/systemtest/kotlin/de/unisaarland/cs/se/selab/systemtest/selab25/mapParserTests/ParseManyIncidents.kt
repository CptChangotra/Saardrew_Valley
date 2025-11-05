package de.unisaarland.cs.se.selab.systemtest.selab25.mapParserTests

import de.unisaarland.cs.se.selab.systemtest.selab25.utils.ExampleSystemTestExtension

/**
 * Test scenario parser
 */
class ParseManyIncidents : ExampleSystemTestExtension() {
    override val name = "ScenarioParser Many Incidents"
    override val description = "Test scenario parser with many incidents"

    // Paths are relative from the `src/systemtest/resources` directory.
    override val map = "ParseIncidents/map.json"
    override val farms = "ParseIncidents/farm.json"
    override val scenario = "ParseIncidents/scenario.json"

    override val logLevel = "DEBUG"
    override val maxTicks = 1
    override val startYearTick = 1

    override suspend fun run() {
        assertNextLine("[INFO] Initialization Info: map.json successfully parsed and validated.")
        assertNextLine("[INFO] Initialization Info: farm.json successfully parsed and validated.")
        assertNextLine("[IMPORTANT] Initialization Info: scenario.json is invalid.")
    }
}
