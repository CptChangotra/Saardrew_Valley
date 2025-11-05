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
 * irrigating not performed
 */
abstract class SoilMoisture : ExampleSystemTestExtension() {
    // override val name = "low moisture and drought"
    override val description = "tests low moisture and drought"

    override val farms = "soilMoisture/farms.json"
    override val scenario = "soilMoisture/scenario.json"
    override val map = "soilMoisture/map.json"

    override val logLevel = "DEBUG"
    override val maxTicks = 7
    override val startYearTick = YearTick.NOVEMBER_1

    // tick 0 340 - 100 = 240,
    // tick 1 240 - 100 = 140,
    // tick 2 140 - 100 = 40,
    // tick 3 , moisture -> 0, harvest -> 0 g
}

/**
 * irrigating not performed
 */
class LowSoilMoisture1 : SoilMoisture() {
    override val name = "lowSoilMoisture1"
    override suspend fun run() {
        skipUntilString("[INFO] Simulation Info: Tick 2 started at tick 23 within the year.")
        assertNextLine(LOW_MOISTURE_0_1)
        assertNextLine(FARM_START_STR)
        assertNextLine(FARM_0_SOWING_PLAN)
        assertNextLine(FARM_FINISH)
        assertNextLine("[INFO] Harvest Estimate: Harvest estimate on tile 1 changed to 1239300 g of APPLE.")
    }
}

/**
 * irrigating not performed
 */
class LowSoilMoisture2 : SoilMoisture() {
    override val name = "lowSoilMoisture2"
    override suspend fun run() {
        skipUntilString("[INFO] Simulation Info: Tick 3 started at tick 24 within the year.")
        assertNextLine(LOW_MOISTURE_0_1)
        assertNextLine(FARM_START_STR)
        assertNextLine(FARM_0_SOWING_PLAN)
        assertNextLine(FARM_FINISH)
        assertNextLine("[DEBUG] Harvest Estimate: Required actions on tile 1 were not performed: IRRIGATING.")
        assertNextLine("[INFO] Harvest Estimate: Harvest estimate on tile 1 changed to 0 g of APPLE.")
    }
}

/**
 * irrigating is created
 */
class LowSoilMoisture3 : SoilMoisture() {
    override val name = "lowSoilMoisture3"
    override suspend fun run() {
//        skipUntilString("[INFO] Simulation Info: Tick 4 started at tick 1 within the year.")
//        assertNextLine(LOW_MOISTURE_0_1)
//        assertNextLine(FARM_START_STR)
//        assertNextLine(FARM_0_SOWING_PLAN)
//        assertNextLine(FARM_FINISH)
//        assertNextLine("[INFO] Simulation Info: Tick 5 started at tick 2 within the year.")
        skipUntilString("[INFO] Simulation Info: Tick 4 started at tick 1 within the year.")
        assertNextLine(LOW_MOISTURE_0_1)
        assertNextLine(FARM_START_STR)
        assertNextLine(FARM_0_SOWING_PLAN)
        assertNextLine(FARM_FINISH)
        assertNextLine("[DEBUG] Harvest Estimate: Required actions on tile 1 were not performed: IRRIGATING.")
        assertNextLine("[INFO] Simulation Info: Tick 5 started at tick 2 within the year.")
    }
}

/**
 * irrigating is not created
 */
class LowSoilMoisture4 : SoilMoisture() {
    override val name = "lowSoilMoisture4"
    override suspend fun run() {
        skipUntilString("[INFO] Simulation Info: Tick 5 started at tick 2 within the year.")
        assertNextLine(LOW_MOISTURE_0_1)
        assertNextLine(FARM_START_STR)
        assertNextLine(FARM_0_SOWING_PLAN)
        assertNextLine(FARM_FINISH)
        // assertNextLine("[DEBUG] Harvest Estimate: Required actions on tile 1 were not performed: IRRIGATING.")
        skipUntilString("[INFO] Simulation Info: Tick 6 started at tick 3 within the year.")
    }
}
