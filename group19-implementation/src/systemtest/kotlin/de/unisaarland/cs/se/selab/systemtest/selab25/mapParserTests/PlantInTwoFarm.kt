package de.unisaarland.cs.se.selab.systemtest.selab25.mapParserTests

import de.unisaarland.cs.se.selab.systemtest.selab25.utils.ExampleSystemTestExtension

/**
 * Test plantation or field belong to wrong farm
 */
class PlantInTwoFarm : ExampleSystemTestExtension() {
    override val name = "PlantInTwoFarm"
    override val description = "Test plantation or field belong to wrong farm"

    // Paths are relative from the `src/systemtest/resources` directory.
    override val farms = "invalidFarmJson/farmPlantInTwoFarm.json"
    override val scenario = "example/scenario.json"
    override val map = "invalidFarmJson/mapPlantInTwoFarm.json"

    override val logLevel = "DEBUG"
    override val maxTicks = 1
    override val startYearTick = 1

    override suspend fun run() {
        assertNextLine("[INFO] Initialization Info: mapPlantInTwoFarm.json successfully parsed and validated.")
        assertNextLine("[IMPORTANT] Initialization Info: farmPlantInTwoFarm.json is invalid.")
    }
}
