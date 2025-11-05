package de.unisaarland.cs.se.selab.systemtest.selab25.basictests

import de.unisaarland.cs.se.selab.systemtest.selab25.utils.ExampleSystemTestExtension
import de.unisaarland.cs.se.selab.utils.YearTick

/**
 * Testing of cloud movement/merging/raining/dissipation
 */
class SowingTest : ExampleSystemTestExtension() {
    override val name = "ABitMoreComplexSowingTest"
    override val description = "Tests multiple tick sowing of various plants."

    // Paths are relative from the `src/systemtest/resources` directory.
    override val farms = "supermegafortniteballstest/farms.json"
    override val scenario = "example/scenario.json"
    override val map = "supermegafortniteballstest/map1.json"

    override val logLevel = "DEBUG"
    override val maxTicks = 40
    override val startYearTick = YearTick.OCTOBER_1

    override suspend fun run() {
        skipUntilString(
            "[INFO] Simulation Info: Simulation started at tick 1 within the year."
        )
    }
}
