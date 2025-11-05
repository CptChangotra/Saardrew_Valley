package de.unisaarland.cs.se.selab.systemtest.selab25.basictests.cherryTest

import de.unisaarland.cs.se.selab.systemtest.selab25.utils.ExampleSystemTestExtension
import de.unisaarland.cs.se.selab.utils.YearTick

private const val FARM_START_STR = "[IMPORTANT] Farm: Farm 0 starts its actions."
private const val FARM_0_SOWING_PLAN = "[DEBUG] Farm: Farm 0 has the following active " +
    "sowing plans it intends to pursue in this tick: ."
private const val FARM_FINISH = "[IMPORTANT] Farm: Farm 0 finished its actions."
private const val LOW_MOISTURE_0_1 = "[INFO] Soil Moisture: The soil moisture is below " +
    "threshold in 0 FIELD and 1 PLANTATION tiles."

/**
 *
 */
abstract class MoistureCherry : ExampleSystemTestExtension() {
    override val description = "tests low moisture and drought"

    override val farms = "MoistureCherry/farm.json"
    override val scenario = "MoistureCherry/scenario.json"
    override val map = "MoistureCherry/map.json"

    override val logLevel = "DEBUG"
    override val maxTicks = 7
    override val startYearTick = YearTick.JULY_1
}

/**
 * test irrigation with cherry low moisture after harvest
 */
class LowMoistureCherry1 : MoistureCherry() {
    override val name = "lowMoistureCherry1"
    override suspend fun run() {
        // cherry requires 150 L, optimal harvest 1200,000
        // tick 0: 240-100 = 140
        // tick 1: 140-100 = 40, apply lowMoisture penalty, lose 50 g harvest
        // tick 2: 40 -> 0,
        skipUntilString("[INFO] Simulation Info: Tick 0 started at tick 13 within the year.")
        assertNextLine(LOW_MOISTURE_0_1)
        assertNextLine(FARM_START_STR)
        assertNextLine(FARM_0_SOWING_PLAN)
        assertNextLine("[IMPORTANT] Farm Action: Machine 0 performs HARVESTING on tile 1 for 4 days.")
        assertNextLine("[IMPORTANT] Farm Harvest: Machine 0 has collected 1200000 g of CHERRY harvest.")
        assertNextLine("[IMPORTANT] Farm Machine: Machine 0 is finished and returns to the shed at 0.")
        assertNextLine("[IMPORTANT] Farm Machine: Machine 0 unloads 1200000 g of CHERRY harvest in the shed.")
        assertNextLine(FARM_FINISH)
    }
}

/**
 * test irrigation with cherry low moisture
 */
class LowMoistureCherry2 : MoistureCherry() {
    override val name = "lowMoistureCherry2"
    override suspend fun run() {
        // should irrigating be created in this tick?
        skipUntilString("[INFO] Simulation Info: Tick 1 started at tick 14 within the year.")
        assertNextLine(LOW_MOISTURE_0_1)
        assertNextLine(FARM_START_STR)
        assertNextLine(FARM_0_SOWING_PLAN)
        assertNextLine(FARM_FINISH)
        assertNextLine("[DEBUG] Harvest Estimate: Required actions on tile 1 were not performed: IRRIGATING.")
        assertNextLine("[INFO] Simulation Info: Tick 2 started at tick 15 within the year.")
    }
}
