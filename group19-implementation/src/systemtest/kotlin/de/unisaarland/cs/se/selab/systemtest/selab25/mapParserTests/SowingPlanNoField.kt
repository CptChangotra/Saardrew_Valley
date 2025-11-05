package de.unisaarland.cs.se.selab.systemtest.selab25.mapParserTests

import de.unisaarland.cs.se.selab.systemtest.selab25.utils.ExampleSystemTestExtension

/**
 * test the tiles affected by sowing plan have to have at least one field
 */
class SowingPlanNoField : ExampleSystemTestExtension() {
    override val name = "SowingPlanNoField"
    override val description = "Sowing plan doesn't contain any field tile"

    // Paths are relative from the `src/systemtest/resources` directory.
    override val map = "SowingPlanNoField/mapSowingPlanNoField.json"
    override val farms = "SowingPlanNoField/farmSowingPlanNoField.json"
    override val scenario = "example/scenario.json"

    override val logLevel = "DEBUG"
    override val maxTicks = 1
    override val startYearTick = 1

    override suspend fun run() {
        assertNextLine("[INFO] Initialization Info: mapSowingPlanNoField.json successfully parsed and validated.")
        assertNextLine("[IMPORTANT] Initialization Info: farmSowingPlanNoField.json is invalid.")
        // assertNextLine("[INFO] Initialization Info: farmTileMissingInFarm.json successfully parsed and validated.")
    }
}
