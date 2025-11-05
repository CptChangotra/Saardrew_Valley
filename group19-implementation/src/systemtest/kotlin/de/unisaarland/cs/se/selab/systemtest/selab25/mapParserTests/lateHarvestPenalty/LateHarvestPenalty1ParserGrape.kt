package de.unisaarland.cs.se.selab.systemtest.selab25.mapParserTests.lateHarvestPenalty

import de.unisaarland.cs.se.selab.systemtest.selab25.utils.ExampleSystemTestExtension

/**
 * test
 */
class LateHarvestPenalty1ParserGrape : ExampleSystemTestExtension() {
    override val name = "LateHarvestPenalty1ParserGrape"
    override val description = "Tests if the first late harvest penalty for Grape is parsed"

    override val farms = "lateHarvestPenaltyParser/farms.json"
    override val scenario = "lateHarvestPenaltyParser/scenario.json"
    override val map = "lateHarvestPenaltyParser/mapGrape.json"

    override val logLevel = "DEBUG"
    override val maxTicks = 1
    override val startYearTick = 18
    override suspend fun run() {
        skipUntilString("[IMPORTANT] Simulation Info: Simulation statistics are calculated.")
        assertNextLine("[IMPORTANT] Simulation Statistics: Farm 0 collected 1140000 g of harvest.")
    }
}
