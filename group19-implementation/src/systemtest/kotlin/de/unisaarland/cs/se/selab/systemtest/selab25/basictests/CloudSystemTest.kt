package de.unisaarland.cs.se.selab.systemtest.selab25.basictests

import de.unisaarland.cs.se.selab.systemtest.selab25.utils.ExampleSystemTestExtension

/**
 * Testing of cloud movement/merging/raining/dissipation
 */
class CloudSystemTest : ExampleSystemTestExtension() {
    override val name = "BigCloudTest"
    override val description = "Tests movement/merging/raining/dissipation of clouds in 1 tick."

    // Paths are relative from the `src/systemtest/resources` directory.
    override val farms = "example/farmsSeq3.json"
    override val scenario = "example/scenarioSeq3.json"
    override val map = "example/mapSeq3.json"

    override val logLevel = "DEBUG"
    override val maxTicks = 1
    override val startYearTick = 1

    override suspend fun run() {
        skipUntilString(
            "[INFO] Cloud Movement: Cloud 1 with 4000 L water moved from tile 23 to tile 22."
        )
        assertNextLine("[INFO] Cloud Movement: Cloud 1 with 4000 L water moved from tile 22 to tile 21.")
        assertNextLine("[INFO] Cloud Movement: Cloud 1 with 4000 L water moved from tile 21 to tile 20.")
        assertNextLine("[INFO] Cloud Movement: Cloud 1 with 4000 L water moved from tile 20 to tile 18.")
        assertNextLine(
            "[IMPORTANT] Cloud Union: Clouds 2 and 1 united to cloud 50 with 6000 L water and duration 4 on tile 18."
        )

        assertNextLine("[INFO] Cloud Movement: Cloud 6 with 1550 L water moved from tile 8 to tile 6.")

        assertNextLine("[IMPORTANT] Cloud Rain: Cloud 12 on tile 10 rained down 70 L water.")
        assertNextLine("[INFO] Cloud Movement: Cloud 12 with 7930 L water moved from tile 10 to tile 208.")
        assertNextLine("[DEBUG] Cloud Movement: On tile 10, the amount of sunlight is 95.")
        assertNextLine("[IMPORTANT] Cloud Rain: Cloud 12 on tile 208 rained down 7930 L water.")
        assertNextLine("[INFO] Cloud Dissipation: Cloud 12 dissipates on tile 208.")

        // here cloud 49 does nothing

        assertNextLine("[IMPORTANT] Cloud Rain: Cloud 50 on tile 18 rained down 100 L water.")
        assertNextLine("[INFO] Cloud Movement: Cloud 50 with 5900 L water moved from tile 18 to tile 17.")
        assertNextLine("[DEBUG] Cloud Movement: On tile 18, the amount of sunlight is 95.")
        assertNextLine("[IMPORTANT] Cloud Rain: Cloud 50 on tile 17 rained down 100 L water.")
        assertNextLine("[INFO] Cloud Movement: Cloud 50 with 5800 L water moved from tile 17 to tile 150.")
        assertNextLine("[DEBUG] Cloud Movement: On tile 17, the amount of sunlight is 95.")
        assertNextLine("[IMPORTANT] Cloud Rain: Cloud 50 on tile 150 rained down 100 L water.")
        assertNextLine("[INFO] Cloud Movement: Cloud 50 with 5700 L water moved from tile 150 to tile 16.")
        assertNextLine("[DEBUG] Cloud Movement: On tile 150, the amount of sunlight is 95.")
        assertNextLine("[INFO] Cloud Dissipation: Cloud 50 got stuck on tile 16.")

        assertNextLine("[DEBUG] Cloud Position: Cloud 49 is on tile 12, where the amount of sunlight is 48.")
    }
}
