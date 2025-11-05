package de.unisaarland.cs.se.selab.systemtest.selab25.mapParserTests

import de.unisaarland.cs.se.selab.systemtest.selab25.utils.ExampleSystemTestExtension

/**
 * tile id 1 is in field list but should belong to plantation
 */
class FarmWrongFieldList : ExampleSystemTestExtension() {
    override val name = "FarmWrongFieldList"
    override val description = "Tests tile is missing in plantation list"

    // Paths are relative from the `src/systemtest/resources` directory.
    override val farms = "invalidFarmJson/farmWrongFieldList.json"
    override val scenario = "example/scenario.json"
    override val map = "example/map.json"

    override val logLevel = "DEBUG"
    override val maxTicks = 1
    override val startYearTick = 1

    override suspend fun run() {
        assertNextLine("[INFO] Initialization Info: map.json successfully parsed and validated.")
        assertNextLine("[IMPORTANT] Initialization Info: farmWrongFieldList.json is invalid.")
    }
}
