package de.unisaarland.cs.se.selab.systemtest.selab25.basictests

import de.unisaarland.cs.se.selab.systemtest.selab25.utils.ExampleSystemTestExtension
import de.unisaarland.cs.se.selab.utils.YearTick

/**
 * test Incident Bee Happy
 */
class IncidentBeeHappy : ExampleSystemTestExtension() {
    override val name = "BeeHappyPotato"
    override val description = "test bee happy incident on potato"

    override val farms = "BeeHappy/beeHappyFarm.json"
    override val scenario = "BeeHappy/beeHappyScenario.json"
    override val map = "BeeHappy/beeHappyMap.json"

    override val logLevel = "DEBUG"
    override val maxTicks = 4
    override val startYearTick = YearTick.APRIL_1

    override suspend fun run() {
        skipUntilString("[INFO] Simulation Info: Tick 3 started at tick 10 within the year.")
        assertNextLine("[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.")
        assertNextLine("[IMPORTANT] Farm: Farm 0 starts its actions.")
        assertNextLine(
            "[DEBUG] Farm: Farm 0 has the following active sowing plans it intends " +
                "to pursue in this tick: ."
        )
        assertNextLine("[IMPORTANT] Farm: Farm 0 finished its actions.")
        assertNextLine("[IMPORTANT] Incident: Incident 12 of type BEE_HAPPY happened and affected tiles 3.")
    }
}
