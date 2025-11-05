package de.unisaarland.cs.se.selab.systemtest.selab25.basictests

import de.unisaarland.cs.se.selab.systemtest.selab25.utils.ExampleSystemTestExtension

const val SOWING_PLAN_0 = "[DEBUG] Farm: Farm 0 has the following active sowing plans it " +
    "intends to pursue in this tick: 444."
const val SOWING_PLAN_1 = "[DEBUG] Farm: Farm 1 has the following active sowing plans it " +
    "intends to pursue in this tick: 445."

/**
 * Test how sowing plans with radius 3 and two farms
 */
class SowingRadius : ExampleSystemTestExtension() {
    override val name = "SowingRadius3"
    override val description = "Test SowingPlans with radius 3 and two farms"

    override val farms = "SowingRadius/farmSowingRadius.json"
    override val map = "SowingRadius/mapSowingRadius.json"
    override val scenario = "example/scenario.json"

    override val logLevel = "DEBUG"
    override val maxTicks = 10
    override val startYearTick = 18

    private suspend fun assertSowing(id: Int, planId: Int, machine: Int, duration: Int) {
        assertNextLine(
            "[IMPORTANT] Farm Action: Machine $machine performs SOWING on " +
                "tile $id for $duration days."
        )
        assertNextLine(
            "[IMPORTANT] Farm Sowing: Machine $machine has sowed WHEAT according " +
                "to sowing plan $planId."
        )
    }

    override suspend fun run() {
        skipUntilString(SOWING_PLAN_0)
        // machine with the lowest duration and lowest ID and the field with the lowest ID in radius
        assertSowing(1, 444, 80, 4)
        assertSowing(4, 444, 80, 4)
        assertSowing(5, 444, 80, 4)
        assertNextLine("[IMPORTANT] Farm Machine: Machine 80 is finished and returns to the shed at 3.")
        // machine 1 starts sowing
        assertSowing(9, 444, 81, 7)
        assertSowing(15, 444, 81, 7)
        assertNextLine("[IMPORTANT] Farm Machine: Machine 81 is finished and returns to the shed at 3.")

        skipUntilString(SOWING_PLAN_1)
        // machine 2 and sow tiles: 11, 13, 18, 19, 20, 21
        assertSowing(11, 445, 82, 2)
        assertSowing(13, 445, 82, 2)
        assertSowing(18, 445, 82, 2)
        assertSowing(16, 445, 82, 2)
        assertSowing(17, 445, 82, 2)
        assertSowing(19, 445, 82, 2)
        assertSowing(20, 445, 82, 2)
        assertNextLine("[IMPORTANT] Farm Machine: Machine 82 is finished and returns to the shed at 14.")

        // machine 3 sow 21
        assertSowing(21, 445, 83, 6)
        assertNextLine("[IMPORTANT] Farm Machine: Machine 83 is finished and returns to the shed at 14.")

        skipUntilString("[INFO] Simulation Info: Tick 9 started at tick 3 within the year.")
        skipUntilString("[IMPORTANT] Farm: Farm 0 starts its actions.")
        assertNextLine(
            "[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: ."
        )
    }
}
