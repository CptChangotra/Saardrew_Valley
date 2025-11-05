package de.unisaarland.cs.se.selab.systemtest.selab25.mapParserTests

import de.unisaarland.cs.se.selab.systemtest.selab25.utils.ExampleSystemTestExtension

/**
 * Tests city expansion ... should be parsed
 * */
class CityExpansionTest : ExampleSystemTestExtension() {
    override val name = "CityExpansionTest"
    override val description = "Tests if city expansion"

    override val map = "parseCityExpansion/mapCityExpansion.json"
    override val farms = "parseCityExpansion/farmCityExpansion.json"
    override val scenario = "parseCityExpansion/scenarioCityExpansion.json"

    override val logLevel = "DEBUG"
    override val maxTicks = 1
    override val startYearTick = 1

    override suspend fun run() {
        assertNextLine("[INFO] Initialization Info: mapCityExpansion.json successfully parsed and validated.")
        assertNextLine("[INFO] Initialization Info: farmCityExpansion.json successfully parsed and validated.")
        assertNextLine("[INFO] Initialization Info: scenarioCityExpansion.json successfully parsed and validated.")
    }
}
