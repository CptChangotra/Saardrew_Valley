package de.unisaarland.cs.se.selab.systemtest.selab25.mapParserTests

import de.unisaarland.cs.se.selab.systemtest.selab25.utils.ExampleSystemTestExtension

/**
 * Test machine in other farm's shed
 */
class MachineInOtherFarm : ExampleSystemTestExtension() {
    override val name = "MachineInOtherFarm"
    override val description = "Test machine in other farm's shed"

    // Paths are relative from the `src/systemtest/resources` directory.
    override val farms = "invalidFarmJson/farmMachineInOtherFarm.json"
    override val scenario = "example/scenario.json"
    override val map = "invalidFarmJson/mapFarmTileMissingInFarm.json"

    override val logLevel = "DEBUG"
    override val maxTicks = 1
    override val startYearTick = 1

    override suspend fun run() {
        assertNextLine("[INFO] Initialization Info: mapFarmTileMissingInFarm.json successfully parsed and validated.")
        assertNextLine("[IMPORTANT] Initialization Info: farmMachineInOtherFarm.json is invalid.")
    }
}
