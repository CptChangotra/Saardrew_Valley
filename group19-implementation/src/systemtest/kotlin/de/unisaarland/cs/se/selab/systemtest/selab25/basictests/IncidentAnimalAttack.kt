package de.unisaarland.cs.se.selab.systemtest.selab25.basictests

import de.unisaarland.cs.se.selab.systemtest.selab25.utils.ExampleSystemTestExtension
import de.unisaarland.cs.se.selab.utils.YearTick

/**
 * test Incident Bee Happy
 */
class IncidentAnimalAttack : ExampleSystemTestExtension() {
    override val name = "IncidentAnimalAttack"
    override val description = "test animal attack happens between mowing and harvesting"

    override val farms = "AnimalAttack2/farm.json"
    override val scenario = "AnimalAttack2/scenario.json"
    override val map = "AnimalAttack2/map.json"

    override val logLevel = "DEBUG"
    override val maxTicks = 4
    override val startYearTick = YearTick.AUGUST_1

    override suspend fun run() {
        skipUntilString("[IMPORTANT] Incident: Incident 332 of type DROUGHT happened and affected tiles 1.")
        assertNextLine("[INFO] Harvest Estimate: Harvest estimate on tile 1 changed to 0 g of APPLE.")
        skipUntilString("[IMPORTANT] Incident: Incident 333 of type ANIMAL_ATTACK happened and affected tiles 1,3.")
        assertNextLine("[IMPORTANT] Incident: Incident 334 of type ANIMAL_ATTACK happened and affected tiles 1,3.")
    }
}
