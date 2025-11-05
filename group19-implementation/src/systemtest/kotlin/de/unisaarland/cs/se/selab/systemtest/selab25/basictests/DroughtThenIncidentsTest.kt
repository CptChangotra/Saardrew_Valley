package de.unisaarland.cs.se.selab.systemtest.selab25.basictests

import de.unisaarland.cs.se.selab.systemtest.selab25.utils.ExampleSystemTestExtension
import de.unisaarland.cs.se.selab.utils.YearTick

/**
 * yo
 */
open class DroughtThenIncidentsTest : ExampleSystemTestExtension() {
    override val name = "DroughtThenIncidentsTest"
    override val description = "test logging for incidents after drought"

    override val map = "basic_farm/map.json"
    override val farms = "basic_farm/farms.json"
    override val scenario = "basic_farm/incidentScenario.json"

    override val logLevel = "DEBUG"
    override val maxTicks = 2
    override val startYearTick = YearTick.DECEMBER_1

    override suspend fun run() {
        skipUntilString("[IMPORTANT] Incident: Incident 0 of type DROUGHT happened and affected tiles 10,11.")
        assertNextLine("[IMPORTANT] Incident: Incident 1 of type ANIMAL_ATTACK happened and affected tiles 10,11.")
    }
}

/**
 * yo?
 */
class DroughtThenIncidentsWithBeeHappyTest : DroughtThenIncidentsTest() {
    override val name = "DroughtThenIncidentsWithBeeHappyTest"
    override val description = "test logging for incidents after drought, with bee happy reporting"

    override suspend fun run() {
        super.run()
        assertNextLine("[IMPORTANT] Incident: Incident 2 of type BEE_HAPPY happened and affected tiles 10,11.")
    }
}

/**
 * yo yo?
 */
class DroughtThenIncidentsWithoutBeeHappyTest : DroughtThenIncidentsTest() {
    override val name = "DroughtThenIncidentsWithoutBeeHappyTest"
    override val description = "test logging for incidents after drought, without bee happy reporting"

    override suspend fun run() {
        super.run()
        assertNextLine("[IMPORTANT] Incident: Incident 2 of type BEE_HAPPY happened and affected tiles .")
    }
}
