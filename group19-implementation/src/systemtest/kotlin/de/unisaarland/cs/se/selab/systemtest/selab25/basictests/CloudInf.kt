package de.unisaarland.cs.se.selab.systemtest.selab25.basictests

import de.unisaarland.cs.se.selab.systemtest.selab25.utils.ExampleSystemTestExtension

/**
 * Testing of cloud movement/merging/raining/dissipation
 */
class CloudInf : ExampleSystemTestExtension() {
    override val name = "InfiniteCloud"
    override val description = "Tests moving of a cloud with infinite duration"

    override val farms = "example/farmsSeq3.json"
    override val scenario = "example/scenarioInfCloud.json"
    override val map = "example/mapInfCloud.json"

    override val logLevel = "DEBUG"
    override val maxTicks = 20
    override val startYearTick = 1
    private val move1 = "[INFO] Cloud Movement: Cloud 666 with 1000 L water moved from tile 201 to tile 202."
    private val move2 = "[INFO] Cloud Movement: Cloud 666 with 1000 L water moved from tile 202 to tile 201."

    override suspend fun run() {
        skipUntilString("[INFO] Cloud Dissipation: Cloud 333 got stuck on tile 16.")
        repeat(20) {
            skipUntilString(move1)
            assertNextLine(move2)

            repeat(4) {
                printTwo()
            }
        }
    }

    private suspend fun printTwo() {
        assertNextLine(move1)
        assertNextLine(move2)
    }
}
