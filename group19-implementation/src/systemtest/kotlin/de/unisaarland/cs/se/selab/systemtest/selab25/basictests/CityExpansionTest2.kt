package de.unisaarland.cs.se.selab.systemtest.selab25.basictests

import de.unisaarland.cs.se.selab.systemtest.selab25.utils.ExampleSystemTestExtension

/**
 * Tests if there are multiple forests affecting a plantation in a single attack
 */
class CityExpansionTest2 : ExampleSystemTestExtension() {
    override val name = "CityExpansionFarmOnlyOneField"
    override val description =
        "Tests if allowing a city expansion to remove a field from a farm that has only one field is ok."

    // Paths are relative from the `src/systemtest/resources` directory.
    override val farms = "Cityexpansionfield/farms.json"
    override val scenario = "Cityexpansionfield/scenario.json"
    override val map = "Cityexpansionfield/map.json"

    override val logLevel = "DEBUG"
    override val maxTicks = 3
    override val startYearTick = 1

    override suspend fun run() {
        skipUntilString("[IMPORTANT] Incident: Incident 444 of type CITY_EXPANSION happened and affected tiles 1.")
    }
}
