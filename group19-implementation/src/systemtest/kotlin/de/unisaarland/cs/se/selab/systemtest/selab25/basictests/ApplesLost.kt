package de.unisaarland.cs.se.selab.systemtest.selab25.basictests

import de.unisaarland.cs.se.selab.systemtest.selab25.utils.ExampleSystemTestExtension

private const val LOG = """
    [INFO] Simulation Info: Simulation started at tick 16 within the year.
[INFO] Simulation Info: Tick 0 started at tick 16 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[INFO] Cloud Dissipation: Cloud 1 got stuck on tile 1.
[IMPORTANT] Cloud Rain: Cloud 2 on tile 2 rained down 100 L water.
[INFO] Cloud Dissipation: Cloud 3 got stuck on tile 4.
[INFO] Cloud Dissipation: Cloud 4 got stuck on tile 5.
[IMPORTANT] Cloud Rain: Cloud 5 on tile 10 rained down 8000 L water.
[INFO] Cloud Dissipation: Cloud 5 dissipates on tile 10.
[DEBUG] Cloud Position: Cloud 2 is on tile 2, where the amount of sunlight is 104.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 0 finished its actions.
[IMPORTANT] Incident: Incident 1 of type BROKEN_MACHINE happened and affected tiles 10.
[INFO] Harvest Estimate: Harvest estimate on tile 2 changed to 1377000 g of APPLE.
[INFO] Harvest Estimate: Harvest estimate on tile 3 changed to 1115370 g of APPLE.
[INFO] Simulation Info: Tick 1 started at tick 17 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Cloud Rain: Cloud 2 on tile 2 rained down 100 L water.
[DEBUG] Cloud Position: Cloud 2 is on tile 2, where the amount of sunlight is 76.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 0 finished its actions.
[DEBUG] Harvest Estimate: Required actions on tile 2 were not performed: MOWING.
[INFO] Harvest Estimate: Harvest estimate on tile 2 changed to 1115370 g of APPLE.
[DEBUG] Harvest Estimate: Required actions on tile 3 were not performed: MOWING.
[INFO] Harvest Estimate: Harvest estimate on tile 3 changed to 731793 g of APPLE.
[INFO] Simulation Info: Tick 2 started at tick 18 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Cloud Rain: Cloud 2 on tile 2 rained down 100 L water.
[DEBUG] Cloud Position: Cloud 2 is on tile 2, where the amount of sunlight is 76.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 0 finished its actions.
[INFO] Harvest Estimate: Harvest estimate on tile 2 changed to 1003833 g of APPLE.
[INFO] Harvest Estimate: Harvest estimate on tile 3 changed to 533475 g of APPLE.
[INFO] Simulation Info: Tick 3 started at tick 19 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Cloud Rain: Cloud 2 on tile 2 rained down 100 L water.
[DEBUG] Cloud Position: Cloud 2 is on tile 2, where the amount of sunlight is 62.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 0 finished its actions.
[DEBUG] Harvest Estimate: Required actions on tile 2 were not performed: HARVESTING.
[INFO] Harvest Estimate: Harvest estimate on tile 2 changed to 501916 g of APPLE.
[DEBUG] Harvest Estimate: Required actions on tile 3 were not performed: HARVESTING.
[INFO] Harvest Estimate: Harvest estimate on tile 3 changed to 216057 g of APPLE.
[INFO] Simulation Info: Tick 4 started at tick 20 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Cloud Rain: Cloud 2 on tile 2 rained down 100 L water.
[DEBUG] Cloud Position: Cloud 2 is on tile 2, where the amount of sunlight is 62.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 0 finished its actions.
[DEBUG] Harvest Estimate: Required actions on tile 2 were not performed: HARVESTING.
[INFO] Harvest Estimate: Harvest estimate on tile 2 changed to 0 g of APPLE.
[DEBUG] Harvest Estimate: Required actions on tile 3 were not performed: HARVESTING.
[INFO] Harvest Estimate: Harvest estimate on tile 3 changed to 0 g of APPLE.
[INFO] Simulation Info: Tick 5 started at tick 21 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Cloud Rain: Cloud 2 on tile 2 rained down 100 L water.
[DEBUG] Cloud Position: Cloud 2 is on tile 2, where the amount of sunlight is 48.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 0 finished its actions.
[INFO] Harvest Estimate: Harvest estimate on tile 2 changed to 1700000 g of APPLE.
[INFO] Harvest Estimate: Harvest estimate on tile 3 changed to 1530000 g of APPLE.
[IMPORTANT] Simulation Info: Simulation ended at tick 6.
[IMPORTANT] Simulation Info: Simulation statistics are calculated.
[IMPORTANT] Simulation Statistics: Farm 0 collected 0 g of harvest.
[IMPORTANT] Simulation Statistics: Total amount of POTATO harvested: 0 g.
[IMPORTANT] Simulation Statistics: Total amount of WHEAT harvested: 0 g.
[IMPORTANT] Simulation Statistics: Total amount of OAT harvested: 0 g.
[IMPORTANT] Simulation Statistics: Total amount of PUMPKIN harvested: 0 g.
[IMPORTANT] Simulation Statistics: Total amount of APPLE harvested: 0 g.
[IMPORTANT] Simulation Statistics: Total amount of GRAPE harvested: 0 g.
[IMPORTANT] Simulation Statistics: Total amount of ALMOND harvested: 0 g.
[IMPORTANT] Simulation Statistics: Total amount of CHERRY harvested: 0 g.
[IMPORTANT] Simulation Statistics: Total harvest estimate still in fields and plantations: 3230000 g.
"""

/**
 * Test for apples' reset and harvest loss with a broken machine.
 */
class ApplesLost : ExampleSystemTestExtension() {
    override val name = "Apples Lost"
    override val description = "Test for apples' reset and harvest loss with a broken machine."

    override val map = "ApplesLost/ApplesLost_map.json"
    override val farms = "ApplesLost/ApplesLost_farm.json"
    override val scenario = "ApplesLost/ApplesLost_scenario.json"

    override val logLevel = "DEBUG"
    override val maxTicks = 6
    override val startYearTick = 16

    override suspend fun run() {
        val logLines = LOG.trim().lines()
        val startIdx = logLines.indexOf("[INFO] Simulation Info: Simulation started at tick 16 within the year.")
        require(startIdx != -1) { "Start line not found in LOG." }
        skipUntilString("[INFO] Initialization Info: ApplesLost_scenario.json successfully parsed and validated.")
        for (i in startIdx until logLines.size) {
            assertNextLine(logLines[i])
        }
    }
}
