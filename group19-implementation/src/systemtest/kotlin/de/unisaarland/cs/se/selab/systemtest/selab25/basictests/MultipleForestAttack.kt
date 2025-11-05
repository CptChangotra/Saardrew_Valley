package de.unisaarland.cs.se.selab.systemtest.selab25.basictests

import de.unisaarland.cs.se.selab.systemtest.selab25.utils.ExampleSystemTestExtension

/**
 * Tests if there are multiple forests affecting a plantation in a single attack
 */
class MultipleForestAttack : ExampleSystemTestExtension() {
    override val name = "MultipleForestAttack"
    override val description = "Tests if there are multiple forests affecting a plantation in a single attack."

    // Paths are relative from the `src/systemtest/resources` directory.
    override val farms = "animal_attack/multiple_forest_attack_farm.json"
    override val scenario = "animal_attack/multiple_forest_attack_scenario.json"
    override val map = "animal_attack/multiple_forest_attack_map.json"

    override val logLevel = "DEBUG"
    override val maxTicks = 3
    override val startYearTick = 1

    override suspend fun run() {
        skipUntilString("[IMPORTANT] Incident: Incident 333 of type ANIMAL_ATTACK happened and affected tiles 1,4.")
    }
}
