package de.unisaarland.cs.se.selab.systemtest.selab25.mapParserTests

import de.unisaarland.cs.se.selab.systemtest.selab25.utils.ExampleSystemTestExtension

/**
 * test tile type Village should not adjoin Forest
 */
class VillageAdjoinForest : ExampleSystemTestExtension() {
    override val name = "MapParser test Village adjoin Forest"
    override val description = "A tile with type VILLAGE should not adjoin a tile with type FOREST."

    override val farms = "example/farms.json"
    override val scenario = "example/scenario.json"
    override val map = "invalidMapJSON/mapVillageAdjoinForest.json"

    override val logLevel = "DEBUG"
    override val maxTicks = 1
    override val startYearTick = 1

    override suspend fun run() {
        assertNextLine("[IMPORTANT] Initialization Info: mapVillageAdjoinForest.json is invalid.")
    }
}
