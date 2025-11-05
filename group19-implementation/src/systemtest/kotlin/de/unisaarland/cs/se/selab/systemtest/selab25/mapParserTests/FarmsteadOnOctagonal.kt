package de.unisaarland.cs.se.selab.systemtest.selab25.mapParserTests

import de.unisaarland.cs.se.selab.systemtest.selab25.utils.ExampleSystemTestExtension

/**
 * tile type FARMSTEAD is on an octagonal tile
 */
class FarmsteadOnOctagonal : ExampleSystemTestExtension() {
    override val name = "MapParser test Farmstead on Octagonal tile"
    override val description = "Test farmstead on octagonal tile"

    override val farms = "example/farms.json"
    override val scenario = "example/scenario.json"
    override val map = "invalidMapJSON/mapFarmsteadOnOctagonal.json"

    override val logLevel = "DEBUG"
    override val maxTicks = 1
    override val startYearTick = 1

    override suspend fun run() {
        assertNextLine("[IMPORTANT] Initialization Info: mapFarmsteadOnOctagonal.json is invalid.")
    }
}
