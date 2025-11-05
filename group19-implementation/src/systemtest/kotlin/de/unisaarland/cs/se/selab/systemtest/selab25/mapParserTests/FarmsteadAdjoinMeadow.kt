package de.unisaarland.cs.se.selab.systemtest.selab25.mapParserTests

import de.unisaarland.cs.se.selab.systemtest.selab25.utils.ExampleSystemTestExtension

/**
 * tile type FARMSTEAD is adjoining a tile with type MEADOW
 */
class FarmsteadAdjoinMeadow : ExampleSystemTestExtension() {
    override val name = "MapParser test Farmstead adjoint Meadow"
    override val description = "A tile with type FARMSTEAD should not adjoin a tile with type MEADOW."

    override val farms = "example/farms.json"
    override val scenario = "example/scenario.json"
    override val map = "invalidMapJSON/mapFarmsteadAdjoinMeadow.json"

    override val logLevel = "DEBUG"
    override val maxTicks = 1
    override val startYearTick = 1

    override suspend fun run() {
        assertNextLine("[IMPORTANT] Initialization Info: mapFarmsteadAdjoinMeadow.json is invalid.")
    }
}
