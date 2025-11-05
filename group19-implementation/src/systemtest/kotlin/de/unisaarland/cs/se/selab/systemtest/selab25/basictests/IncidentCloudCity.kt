package de.unisaarland.cs.se.selab.systemtest.selab25.basictests

import de.unisaarland.cs.se.selab.systemtest.selab25.utils.ExampleSystemTestExtension

/**
 * Testing of cloud movement/merging/raining/dissipation
 */
class IncidentCloudCity : ExampleSystemTestExtension() {
    override val name = "Incident Cloud City"
    override val description = "Tests cloud creation overlaps with city exp and again cloud creation."

    override val farms = "example/farmsSeq3.json"
    override val scenario = "example/scenarioIncExp.json"
    override val map = "example/mapIncExp.json"

    override val logLevel = "DEBUG"
    override val maxTicks = 1
    override val startYearTick = 1

    override suspend fun run() { // not completed
        skipUntilString(
            "[IMPORTANT] Incident: Incident 1 of type CLOUD_CREATION " +
                "happened and affected tiles 8,207,214,215."
        )
        assertNextLine(
            "[IMPORTANT] Cloud Union: Clouds 2 and 5 united to cloud 6 with 5000 L water and duration 9 on tile 214."
        )
        assertNextLine(
            "[IMPORTANT] Cloud Union: Clouds 1 and 7 united to cloud 8 with 5000 L water and duration 9 on tile 215."
        )
        assertNextLine(
            "[IMPORTANT] Incident: Incident 22 of type CITY_EXPANSION " +
                "happened and affected tiles 215."
        )
        assertNextLine("[INFO] Cloud Dissipation: Cloud 8 got stuck on tile 215.")
        assertNextLine(
            "[IMPORTANT] Incident: Incident 33 of type CLOUD_CREATION " +
                "happened and affected tiles 2,202."
        )
    }
}
