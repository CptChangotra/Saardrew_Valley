package de.unisaarland.cs.se.selab.systemtest.selab25.basictests

import de.unisaarland.cs.se.selab.systemtest.selab25.utils.ExampleSystemTestExtension

/**
 * test
 */
class SowBigRadius : ExampleSystemTestExtension() {
    override val name = "sowBigRadius"
    override val description = "sows a plan with a very big radius"

    override val farms = "sowingPlanBigRadius/farms.json"
    override val scenario = "sowingPlanBigRadius/scenario.json"
    override val map = "sowingPlanBigRadius/map.json"

    override val logLevel = "DEBUG"
    override val maxTicks = 1
    override val startYearTick = 19

    override suspend fun run() {
        skipUntilString(
            "[DEBUG] Farm: Farm 0 has the following active sowing" +
                " plans it intends to pursue in this tick: 0."
        )
        assertNextLine("[IMPORTANT] Farm Action: Machine 0 performs SOWING on tile 1 for 1 days.")
        assertNextLine("Farm Sowing: Machine 0 has sowed WHEAT according to sowing plan 0.")
        assertNextLine("[IMPORTANT] Farm Machine: Machine 0 is finished and returns to the shed at 0.")
    }
}
