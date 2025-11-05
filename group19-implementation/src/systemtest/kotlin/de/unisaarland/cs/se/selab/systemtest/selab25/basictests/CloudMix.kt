package de.unisaarland.cs.se.selab.systemtest.selab25.basictests

import de.unisaarland.cs.se.selab.systemtest.selab25.utils.ExampleSystemTestExtension

/**
 * Testing of cloud movement/merging/raining/dissipation
 */
class CloudMix : ExampleSystemTestExtension() {
    override val name = "MixCloudTest"
    override val description = "Tests different conditions for raining and standing."

    override val farms = "example/farmsSeq3.json"
    override val scenario = "example/scenarioMix.json"
    override val map = "example/mapMix.json"

    override val logLevel = "DEBUG"
    override val maxTicks = 1
    override val startYearTick = 1

    override suspend fun run() {
        skipUntilString(
            "[INFO] Cloud Dissipation: Cloud 1 dissipates on tile 120."
        )
        assertNextLine("[INFO] Cloud Movement: Cloud 2 with 3000 L water moved from tile 150 to tile 120.")
        assertNextLine("[DEBUG] Cloud Movement: On tile 150, the amount of sunlight is 95.")

        // cloud3 doesn't do anything

        assertNextLine("[IMPORTANT] Cloud Rain: Cloud 4 on tile 18 rained down 100 L water.")
        assertNextLine("[INFO] Cloud Movement: Cloud 4 with 4950 L water moved from tile 18 to tile 17.")
        assertNextLine("[DEBUG] Cloud Movement: On tile 18, the amount of sunlight is 95.")
        assertNextLine("[INFO] Cloud Movement: Cloud 4 with 4950 L water moved from tile 17 to tile 150.")
        assertNextLine("[DEBUG] Cloud Movement: On tile 17, the amount of sunlight is 95.")
        assertNextLine("[INFO] Cloud Movement: Cloud 4 with 4950 L water moved from tile 150 to tile 120.")
        assertNextLine("[DEBUG] Cloud Movement: On tile 150, the amount of sunlight is 92.")
        assertNextLine(
            "[IMPORTANT] Cloud Union: Clouds 2 and 4 united to cloud 6 with 7950 L water and duration 3 on tile 120."
        )
        assertNextLine("[IMPORTANT] Cloud Rain: Cloud 6 on tile 120 rained down 7950 L water.")
        assertNextLine("[INFO] Cloud Dissipation: Cloud 6 dissipates on tile 120.")

        assertNextLine("[DEBUG] Cloud Position: Cloud 5 is on tile 10, where the amount of sunlight is 48.")
        assertNextLine("[DEBUG] Cloud Position: Cloud 3 is on tile 12, where the amount of sunlight is 48.")
    }
}
