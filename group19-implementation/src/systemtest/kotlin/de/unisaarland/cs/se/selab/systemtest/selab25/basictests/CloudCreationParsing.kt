package de.unisaarland.cs.se.selab.systemtest.selab25.basictests

import de.unisaarland.cs.se.selab.systemtest.selab25.utils.ExampleSystemTestExtension
import de.unisaarland.cs.se.selab.utils.YearTick

private const val MAP = "CloudCreationParsing/CloudCreationParsing_map.json"
private const val FARM = "CloudCreationParsing/CloudCreationParsing_farm.json"
private const val FARM_PARSED = "[INFO] Initialization Info: CloudCreationParsing_farm.json" +
    " successfully parsed and validated."

/**
 * Tests the parsing for Cloud creation
 */
abstract class CloudCreationParsing : ExampleSystemTestExtension() {
    override val description = "Cloud creation parsing tests"

    override val logLevel = "DEBUG"
    override val maxTicks = 4
    override val startYearTick = YearTick.JULY_1
}

/**
 * Tests Cloud Creation parsing
 */
class CloudCreationParsingValid : CloudCreationParsing() {
    override val name = "Cloud Creation is valid"
    override val description = "Cloud creation parsing tests -> valid"

    override val map = MAP
    override val farms = FARM
    override val scenario = "CloudCreationParsing/CloudCreationParsing_scenarioValid.json"

    override suspend fun run() {
        skipUntilString("[IMPORTANT] Farm: Farm 0 finished its actions.")
        assertNextLine("[IMPORTANT] Incident: Incident 1 of type CLOUD_CREATION happened and affected tiles 2.")
    }
}

/**
 * Tests Cloud Creation parsing
 */
class CloudCreationParsingInvalid : CloudCreationParsing() {
    override val name = "Cloud Creation is invalid"
    override val description = "Cloud creation parsing tests -> invalid"

    override val map = MAP
    override val farms = FARM
    override val scenario = "CloudCreationParsing/CloudCreationParsing_scenarioInvalid.json"

    override suspend fun run() {
        skipUntilString(FARM_PARSED)

        assertNextLine("[IMPORTANT] Initialization Info: CloudCreationParsing_scenarioInvalid.json is invalid.")
    }
}

/**
 * Tests Cloud Creation parsing
 */
class CloudCreationParsingInvalid2 : CloudCreationParsing() {
    override val name = "Cloud Creation is invalid 2"
    override val description = "Cloud creation parsing tests -> invalid2"

    override val map = MAP
    override val farms = FARM
    override val scenario = "CloudCreationParsing/CloudCreationParsing_scenarioInvalid2.json"

    override suspend fun run() {
        skipUntilString(FARM_PARSED)

        assertNextLine(
            "[IMPORTANT] Initialization Info: CloudCreationParsing_scenarioInvalid2.json is invalid."
        )
    }
}
