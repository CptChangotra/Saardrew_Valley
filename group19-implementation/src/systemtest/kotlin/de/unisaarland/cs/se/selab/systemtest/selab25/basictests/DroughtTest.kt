package de.unisaarland.cs.se.selab.systemtest.selab25.basictests

import de.unisaarland.cs.se.selab.systemtest.selab25.utils.ExampleSystemTestExtension
import de.unisaarland.cs.se.selab.utils.YearTick

private const val FARM_START_STR = "[IMPORTANT] Farm: Farm 0 starts its actions."
private const val FARM_SOWING_PLAN = "[DEBUG] Farm: Farm 0 has the following active sowing plans " +
    "it intends to pursue in this tick: ."
private const val SOIL_MOISTURE_0_0 = "[INFO] Soil Moisture: The soil moisture is below threshold in " +
    "0 FIELD and 0 PLANTATION tiles."
private const val SOIL_MOISTURE_0_1 = "[INFO] Soil Moisture: The soil moisture is below threshold in " +
    "0 FIELD and 1 PLANTATION tiles."
private const val FARM_FINISHED_ACTIONS = "[IMPORTANT] Farm: Farm 0 finished its actions."

/**
 * Tests a cycle of Apple
 */
class DroughtTest : ExampleSystemTestExtension() {
    override val name = "Drought and soil moisture"
    override val description = "soil moisture and drought"

    override val map = "droughtMoisture/map.json"
    override val farms = "droughtMoisture/farm.json"
    override val scenario = "droughtMoisture/scenario.json"

    override val logLevel = "DEBUG"
    override val maxTicks = 4
    override val startYearTick = YearTick.MARCH_1

    override suspend fun run() {
        // drought on tile 25
        skipUntilString("[INFO] Simulation Info: Tick 1 started at tick 6 within the year.")
        assertNextLine(SOIL_MOISTURE_0_0)
        skipUntilString(FARM_START_STR)
        assertNextLine(FARM_SOWING_PLAN)
        assertNextLine(FARM_FINISHED_ACTIONS)
        assertNextLine("[IMPORTANT] Incident: Incident 334 of type DROUGHT happened and affected tiles 25.")
        skipUntilString("[INFO] Harvest Estimate: Harvest estimate on tile 25 changed to 0 g of APPLE.")
        assertNextLine("[INFO] Harvest Estimate: Harvest estimate on tile 26 changed to 903449 g of APPLE.")

        skipUntilString("[INFO] Simulation Info: Tick 2 started at tick 7 within the year.")
        assertNextLine(SOIL_MOISTURE_0_1)

        skipUntilString("[INFO] Simulation Info: Tick 3 started at tick 8 within the year.")
        assertNextLine(SOIL_MOISTURE_0_1)
        skipUntilString(FARM_START_STR)
        assertNextLine(FARM_SOWING_PLAN)
        assertNextLine(FARM_FINISHED_ACTIONS)
        assertNextLine("[IMPORTANT] Incident: Incident 335 of type DROUGHT happened and affected tiles 26.")
    }
}
