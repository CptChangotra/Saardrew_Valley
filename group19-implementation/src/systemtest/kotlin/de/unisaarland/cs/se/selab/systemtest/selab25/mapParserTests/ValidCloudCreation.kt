package de.unisaarland.cs.se.selab.systemtest.selab25.mapParserTests

import de.unisaarland.cs.se.selab.systemtest.selab25.utils.ExampleSystemTestExtension

/**
 * test incident json
 */
class ValidCloudCreation : ExampleSystemTestExtension() {
    override val name = "scenarioValidCloudCreation"
    override val description = "cloud creations overlap on the tile 31 that is already transformed to village"

    override val farms = "scenarioParser/farmValidCloudCreation.json"
    override val scenario = "scenarioParser/validCloudCreation.json"
    override val map = "scenarioParser/mapValidCloudCreation.json"

    override val logLevel = "DEBUG"
    override val maxTicks = 3
    override val startYearTick = 1

    override suspend fun run() {
        assertNextLine("[INFO] Initialization Info: mapValidCloudCreation.json successfully parsed and validated.")
        assertNextLine("[INFO] Initialization Info: farmValidCloudCreation.json successfully parsed and validated.")
        assertNextLine("[INFO] Initialization Info: validCloudCreation.json successfully parsed and validated.")

//        skipUntilString("[IMPORTANT] Incident: Incident 11 of type CITY_EXPANSION happened and affected tiles 31.")
//        assertNextLine("[IMPORTANT] Incident: Incident 11 of type CITY_EXPANSION happened and affected tiles 31.")
//        skipUntilString(
//            "[IMPORTANT] Incident: Incident 12 of type CLOUD_CREATION happened " +
//                "and affected tiles 0,1,2,3."
//        )
//        assertNextLine(
//            "[IMPORTANT] Incident: Incident 12 of type CLOUD_CREATION happened" +
//                " and affected tiles 0,1,2,3."
//        )
//        skipUntilString(
//            "[IMPORTANT] Incident: Incident 13 of type CLOUD_CREATION happened " +
//                "and affected tiles 4."
//        )
//        assertNextLine("[IMPORTANT] Incident: Incident 13 of type CLOUD_CREATION happened and affected tiles 4.")
    }
}
