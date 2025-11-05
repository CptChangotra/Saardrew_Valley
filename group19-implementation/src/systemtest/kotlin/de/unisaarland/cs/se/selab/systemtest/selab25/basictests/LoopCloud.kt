package de.unisaarland.cs.se.selab.systemtest.selab25.basictests

import de.unisaarland.cs.se.selab.systemtest.selab25.utils.ExampleSystemTestExtension

/**
 * Testing of cloud movement/merging/raining/dissipation
 */
class LoopCloud : ExampleSystemTestExtension() {
    override val name = "LoopCloudTest"
    override val description = "Tests merging and then moving for only 10 tiles (for 2 ticks)"

    override val farms = "example/farmsSeq3.json"
    override val scenario = "example/scenarioLoop.json"
    override val map = "example/mapLoop.json"

    override val logLevel = "DEBUG"
    override val maxTicks = 2
    override val startYearTick = 1

    private val move207 = "[INFO] Cloud Movement: Cloud 3 with 2000 L water moved from tile 207 to tile 215."
    private val move215 = "[INFO] Cloud Movement: Cloud 3 with 2000 L water moved from tile 215 to tile 208."
    private val move208 = "[INFO] Cloud Movement: Cloud 3 with 2000 L water moved from tile 208 to tile 8."
    private val move8 = "[INFO] Cloud Movement: Cloud 3 with 2000 L water moved from tile 8 to tile 207."

    override suspend fun run() {
        skipUntilString(
            "[INFO] Cloud Movement: Cloud 1 with 1000 L water moved from tile 208 to tile 8."
        )
        assertNextLine("[INFO] Cloud Movement: Cloud 1 with 1000 L water moved from tile 8 to tile 207.")
        assertNextLine(
            "[IMPORTANT] Cloud Union: Clouds 2 and 1 united to cloud 3 with 2000 L water and duration 2 on tile 207."
        )
        run2078()
        run2078()
        assertNextLine(move207)
        assertNextLine(move215)

        skipUntilString(move208)
        assertNextLine(move8)
        run2078()
        run2078()
        assertNextLine("[INFO] Cloud Dissipation: Cloud 3 dissipates on tile 207.")
    }

    private suspend fun run2078() {
        assertNextLine(move207)
        assertNextLine(move215)
        assertNextLine(move208)
        assertNextLine(move8)
    }
}
