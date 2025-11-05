package de.unisaarland.cs.se.selab.systemtest.selab25.basictests

import de.unisaarland.cs.se.selab.systemtest.selab25.utils.ExampleSystemTestExtension

/**
 *
 */
class SowingPlanTestSimple : ExampleSystemTestExtension() {
    override val name = "SimpleSowingPlan"
    override val description = "Tests a simple slow plan."

    override val farms = "sowingPlan/farms.json"
    override val scenario = "example/scenario.json"
    override val map = "sowingPlan/map.json"

    override val logLevel = "DEBUG"
    override val maxTicks = 1
    override val startYearTick = 19

    override suspend fun run() {
        skipUntilString("[IMPORTANT] Farm: Farm 0 starts its actions")
        assertNextLine(
            "[DEBUG] Farm: Farm 0 has the following active sowing plans " +
                "it intends to pursue in this tick: 0."
        )
        assertNextLine(
            "[IMPORTANT] Farm Action: Machine 0 performs SOWING on " +
                "tile 2 for 4 days."
        )
        assertNextLine(
            "[IMPORTANT] Farm Sowing: Machine 0 has sowed WHEAT according " +
                "to sowing plan 0."
        )
        assertNextLine(
            "[IMPORTANT] Farm Action: Machine 0 performs SOWING on " +
                "tile 4 for 4 days."
        )
        assertNextLine("[IMPORTANT] Farm Sowing: Machine 0 has sowed WHEAT according to sowing plan 0.")
        assertNextLine("[IMPORTANT] Farm Machine: Machine 0 is finished and returns to the shed at 5.")
        assertNextLine("[IMPORTANT] Farm: Farm 0 finished its actions.")
    }
}
