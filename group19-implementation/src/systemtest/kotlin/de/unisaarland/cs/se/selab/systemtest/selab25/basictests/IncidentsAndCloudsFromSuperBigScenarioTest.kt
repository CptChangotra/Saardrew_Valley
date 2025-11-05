package de.unisaarland.cs.se.selab.systemtest.selab25.basictests

import de.unisaarland.cs.se.selab.systemtest.selab25.utils.ExampleSystemTestExtension

/**
 * Testing only incidents and cloud logs from the super big incident scenario.
 */
class IncidentsAndCloudsFromSuperBigScenarioTest : ExampleSystemTestExtension() {
    override val name = "Incidents And Clouds Super Big Scenario"
    override val description = "Tests only the incident and cloud logs."

    override val farms = "example/bigIncidentFarms.json"
    override val scenario = "example/superBigIncidentScenario.json"
    override val map = "example/bigIncidentMap.json"

    override val logLevel = "DEBUG"
    override val maxTicks = 1
    override val startYearTick = 13

    override suspend fun run() {
        skipUntilString("[IMPORTANT] Incident: Incident 1 of type BROKEN_MACHINE happened and affected tiles 64.")
        assertNextLine(
            "[IMPORTANT] Incident: Incident 2 of type CLOUD_CREATION " +
                "happened and affected tiles 4,5,11,12,13,18,19,20,21" +
                ",25,26,27,28,29,32,33,34,35,36,39,40,41,42,43,46,47,48,49" +
                ",50,52,53,54,55,56,57,59,61,62,63,64,66,67,69,70,71,73,74,77" +
                ",78,80,81,82,85,87,89,94,95,96,101,102,103,109,110."
        )
        assertNextLine("[IMPORTANT] Incident: Incident 3 of type CITY_EXPANSION happened and affected tiles 95.")
        assertNextLine("[INFO] Cloud Dissipation: Cloud 56 got stuck on tile 95.")
        assertNextLine("[IMPORTANT] Incident: Incident 4 of type ANIMAL_ATTACK happened and affected tiles 41.")
        assertNextLine("[IMPORTANT] Incident: Incident 5 of type DROUGHT happened and affected tiles 41.")
    }
}
