package de.unisaarland.cs.se.selab.systemtest.selab25.basictests

import de.unisaarland.cs.se.selab.systemtest.selab25.utils.ExampleSystemTestExtension

/**
 * Tests initialization with a valid map but invalid farm file.
 */
class SowingInvalid : ExampleSystemTestExtension() {
    override val name = "Initialization Invalid sowing plan"
    override val description = "Tests invalid sowing plan"

    override val farms = "example/sowingFarms.json"
    override val scenario = "example/scenario.json"
    override val map = "example/sowingMap.json"

    override val logLevel = "DEBUG"
    override val maxTicks = 1
    override val startYearTick = 1

    override suspend fun run() {
        assertNextLine("[INFO] Initialization Info: sowingMap.json successfully parsed and validated.")
        assertNextLine("[IMPORTANT] Initialization Info: sowingFarms.json is invalid.")
    }
}
