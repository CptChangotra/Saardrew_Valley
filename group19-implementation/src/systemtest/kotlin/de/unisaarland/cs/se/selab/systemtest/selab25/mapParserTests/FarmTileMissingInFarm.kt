package de.unisaarland.cs.se.selab.systemtest.selab25.mapParserTests

import de.unisaarland.cs.se.selab.systemtest.selab25.utils.ExampleSystemTestExtension

/**
 * tile in map json is missing in farm json
 */
class FarmTileMissingInFarm : ExampleSystemTestExtension() {
    override val name = "FarmTileMissingInFarm"
    override val description = "Tests Field tile in map is not in farm"

    // Paths are relative from the `src/systemtest/resources` directory.
    override val farms = "invalidFarmJson/farmTileMissingInFarm.json"
    override val scenario = "example/scenario.json"
    override val map = "invalidFarmJson/mapFarmTileMissingInFarm.json"

    override val logLevel = "DEBUG"
    override val maxTicks = 1
    override val startYearTick = 1

    override suspend fun run() {
        assertNextLine("[INFO] Initialization Info: mapFarmTileMissingInFarm.json successfully parsed and validated.")
        assertNextLine("[IMPORTANT] Initialization Info: farmTileMissingInFarm.json is invalid.")
        // assertNextLine("[INFO] Initialization Info: farmTileMissingInFarm.json successfully parsed and validated.")
    }
}
