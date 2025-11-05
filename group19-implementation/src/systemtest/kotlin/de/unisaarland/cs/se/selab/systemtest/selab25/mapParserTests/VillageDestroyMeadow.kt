package de.unisaarland.cs.se.selab.systemtest.selab25.mapParserTests

import de.unisaarland.cs.se.selab.systemtest.selab25.utils.ExampleSystemTestExtension

/**
 *
 */
class VillageDestroyMeadow : ExampleSystemTestExtension() {
    override val name = "Scenario parser test Village expand over meadow"
    override val description = "city expansion not possible over meadows"

    override val farms = "example/farms.json"
    override val scenario = "invalidMapJSON/scenarioVillagedestroysMeadow.json"
    override val map = "invalidMapJSON/mapVillageDestroyMeadow.json"

    override val logLevel = "DEBUG"
    override val maxTicks = 2
    override val startYearTick = 1

    override suspend fun run() {
        assertNextLine("[INFO] Initialization Info: mapVillageDestroyMeadow.json successfully parsed and validated.")
        assertNextLine("[INFO] Initialization Info: farms.json successfully parsed and validated.")
        assertNextLine("[IMPORTANT] Initialization Info: scenarioVillagedestroysMeadow.json is invalid.")
    }
}
