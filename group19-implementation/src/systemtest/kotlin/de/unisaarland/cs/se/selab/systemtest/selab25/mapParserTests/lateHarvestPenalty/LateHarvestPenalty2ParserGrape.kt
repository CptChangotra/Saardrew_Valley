package de.unisaarland.cs.se.selab.systemtest.selab25.mapParserTests.lateHarvestPenalty

import de.unisaarland.cs.se.selab.systemtest.selab25.utils.ExampleSystemTestExtension

/**
 * test
 */
class LateHarvestPenalty2ParserGrape : ExampleSystemTestExtension() {
    override val name = "lateHarvestPenaltyParserAlmond"
    override val description = "Tests if the second late harvest penalty for Grape is parsed"

    override val farms = "lateHarvestPenaltyParser/farms.json"
    override val scenario = "lateHarvestPenaltyParser/scenario.json"
    override val map = "lateHarvestPenaltyParser/mapGrape.json"

    override val logLevel = "DEBUG"
    override val maxTicks = 1
    override val startYearTick = 19
    override suspend fun run() {
        skipUntilString("[IMPORTANT] Simulation Info: Simulation statistics are calculated.")
        assertNextLine("[IMPORTANT] Simulation Statistics: Farm 0 collected 1083000 g of harvest.")
    }
}
