package de.unisaarland.cs.se.selab.systemtest.selab25.mapParserTests

import de.unisaarland.cs.se.selab.systemtest.selab25.utils.ExampleSystemTestExtension

/**
 * tile in map json is missing in farm json
 */
class SowingPlanWrongFarm : ExampleSystemTestExtension() {
    override val name = "SowingPlanWrongFarm"
    override val description = "Sowing plan wrong farm"

    // Paths are relative from the `src/systemtest/resources` directory.
    override val map = "invalidFarmJson/mapSowingPlanWrongFarm.json"
    override val farms = "invalidFarmJson/farmSowingPlanWrongFarm.json"
    override val scenario = "example/scenario.json"

    override val logLevel = "DEBUG"
    override val maxTicks = 1
    override val startYearTick = 1

    override suspend fun run() {
        assertNextLine("[INFO] Initialization Info: mapSowingPlanWrongFarm.json successfully parsed and validated.")
        assertNextLine("[IMPORTANT] Initialization Info: farmSowingPlanWrongFarm.json is invalid.")
        // assertNextLine("[INFO] Initialization Info: farmTileMissingInFarm.json successfully parsed and validated.")
    }
}
