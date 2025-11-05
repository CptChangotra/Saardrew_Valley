package de.unisaarland.cs.se.selab.systemtest.selab25.mapParserTests

import de.unisaarland.cs.se.selab.systemtest.selab25.utils.ExampleSystemTestExtension

/**
 * Test ANIMAL_ATTACK: at least one of
 * 28 the impacted tiles is a FOREST at the moment of incident
 */
class AnimalAttackNoForest : ExampleSystemTestExtension() {
    override val name = "AnimalAttackNoForest"
    override val description = "animal attack no forest around"

    // Paths are relative from the `src/systemtest/resources` directory.
    override val farms = "scenarioParser/farmAnimalAttackNoForest.json"
    override val scenario = "scenarioParser/scenarioAnimalAttackNoForest.json"
    override val map = "scenarioParser/mapAnimalAttackNoForest.json"

    override val logLevel = "DEBUG"
    override val maxTicks = 1
    override val startYearTick = 1

    override suspend fun run() {
        assertNextLine("[INFO] Initialization Info: mapAnimalAttackNoForest.json successfully parsed and validated.")
        assertNextLine("[INFO] Initialization Info: farmAnimalAttackNoForest.json successfully parsed and validated.")
        assertNextLine("[IMPORTANT] Initialization Info: scenarioAnimalAttackNoForest.json is invalid.")
    }
}
