package de.unisaarland.cs.se.selab.systemtest.selab25.basictests

import de.unisaarland.cs.se.selab.systemtest.selab25.utils.ExampleSystemTestExtension
import de.unisaarland.cs.se.selab.utils.YearTick

/**
 * Test BEE_HAPPY is logged only the first one
 */
class BeeHappyLogFirst : ExampleSystemTestExtension() {
    override val name = "BeeHappyLogFirst"
    override val description = "Tests bee happy logging"

    override val farms = "BeeHappySimple/farmBeeHappy.json"
    override val scenario = "BeeHappySimple/scenarioBeeHappy.json"
    override val map = "BeeHappySimple/mapBeeHappy.json"

    override val logLevel = "DEBUG"
    override val maxTicks = 2
    override val startYearTick = YearTick.APRIL_2

    override suspend fun run() {
        // bee happy
        skipUntilString("[IMPORTANT] Incident: Incident 20 of type BEE_HAPPY happened and affected tiles 1.")
        assertNextLine("[INFO] Harvest Estimate: Harvest estimate on tile 1 changed to 1555200 g of CHERRY.")
    }
}
