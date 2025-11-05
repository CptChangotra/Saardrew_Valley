package de.unisaarland.cs.se.selab.systemtest.selab25.basictests

import de.unisaarland.cs.se.selab.systemtest.selab25.utils.ExampleSystemTestExtension

/**
 * Testing of cloud movement/merging/raining/dissipation
 */
class MergeCloudSystemTest : ExampleSystemTestExtension() {
    override val name = "MergeCloudTest"
    override val description = "Tests double merging (and a bit of dissipation)."

    // Paths are relative from the `src/systemtest/resources` directory.
    override val farms = "example/farmsSeq3.json"
    override val scenario = "example/scenarioMerge.json"
    override val map = "example/mapSeq3.json"

    override val logLevel = "DEBUG"
    override val maxTicks = 1
    override val startYearTick = 1

    override suspend fun run() {
        skipUntilString("[INFO] Cloud Movement: Cloud 1 with 3000 L water moved from tile 23 to tile 22.")
        assertNextLine(
            "[IMPORTANT] Cloud Union: Clouds 2 and 1 united to cloud 20 with 4000 L water and duration 4 on tile 22."
        )
        assertNextLine("[INFO] Cloud Dissipation: Cloud 15 dissipates on tile 6.")
        assertNextLine("[INFO] Cloud Movement: Cloud 19 with 3000 L water moved from tile 225 to tile 22.")
        assertNextLine(
            "[IMPORTANT] Cloud Union: Clouds 20 and 19 united to cloud 21 with 7000 L water and duration 4 on tile 22."
        )
        assertNextLine("[IMPORTANT] Cloud Rain: Cloud 21 on tile 22 rained down 7000 L water.")
        assertNextLine("[INFO] Cloud Dissipation: Cloud 21 dissipates on tile 22.")
    }
}
