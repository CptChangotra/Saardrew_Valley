package de.unisaarland.cs.se.selab.systemtest.selab25.basictests

import de.unisaarland.cs.se.selab.systemtest.selab25.utils.ExampleSystemTestExtension
import de.unisaarland.cs.se.selab.utils.YearTick

private const val LOG = """
    [INFO] Initialization Info: appleMap.json successfully parsed and validated.
[INFO] Initialization Info: appleFarm.json successfully parsed and validated.
[INFO] Initialization Info: appleScenario.json successfully parsed and validated.
[INFO] Simulation Info: Simulation started at tick 23 within the year.
[INFO] Simulation Info: Tick 0 started at tick 23 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 0 finished its actions.
[INFO] Harvest Estimate: Harvest estimate on tile 22 changed to 1530000 g of APPLE.
[INFO] Harvest Estimate: Harvest estimate on tile 23 changed to 1530000 g of APPLE.
[INFO] Harvest Estimate: Harvest estimate on tile 24 changed to 1530000 g of APPLE.
[INFO] Simulation Info: Tick 1 started at tick 24 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 0 finished its actions.
[IMPORTANT] Incident: Incident 222 of type CLOUD_CREATION happened and affected tiles 22,23,24,31.
[INFO] Harvest Estimate: Harvest estimate on tile 22 changed to 1377000 g of APPLE.
[INFO] Harvest Estimate: Harvest estimate on tile 23 changed to 1377000 g of APPLE.
[INFO] Harvest Estimate: Harvest estimate on tile 24 changed to 1377000 g of APPLE.
[INFO] Simulation Info: Tick 2 started at tick 1 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[DEBUG] Cloud Position: Cloud 0 is on tile 22, where the amount of sunlight is 48.
[DEBUG] Cloud Position: Cloud 1 is on tile 23, where the amount of sunlight is 48.
[DEBUG] Cloud Position: Cloud 2 is on tile 24, where the amount of sunlight is 48.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 0 finished its actions.
[INFO] Simulation Info: Tick 3 started at tick 2 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[DEBUG] Cloud Position: Cloud 0 is on tile 22, where the amount of sunlight is 48.
[DEBUG] Cloud Position: Cloud 1 is on tile 23, where the amount of sunlight is 48.
[DEBUG] Cloud Position: Cloud 2 is on tile 24, where the amount of sunlight is 48.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 0 finished its actions.
[INFO] Simulation Info: Tick 4 started at tick 3 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[DEBUG] Cloud Position: Cloud 0 is on tile 22, where the amount of sunlight is 62.
[DEBUG] Cloud Position: Cloud 1 is on tile 23, where the amount of sunlight is 62.
[DEBUG] Cloud Position: Cloud 2 is on tile 24, where the amount of sunlight is 62.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm Action: Machine 0 performs CUTTING on tile 22 for 4 days.
[IMPORTANT] Farm Action: Machine 0 performs CUTTING on tile 23 for 4 days.
[IMPORTANT] Farm Machine: Machine 0 is finished and returns to the shed at 0.
[IMPORTANT] Farm: Farm 0 finished its actions.
[INFO] Simulation Info: Tick 5 started at tick 4 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[DEBUG] Cloud Position: Cloud 0 is on tile 22, where the amount of sunlight is 62.
[DEBUG] Cloud Position: Cloud 1 is on tile 23, where the amount of sunlight is 62.
[DEBUG] Cloud Position: Cloud 2 is on tile 24, where the amount of sunlight is 62.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 0 finished its actions.
[DEBUG] Harvest Estimate: Required actions on tile 24 were not performed: CUTTING.
[INFO] Harvest Estimate: Harvest estimate on tile 24 changed to 688500 g of APPLE.
[INFO] Simulation Info: Tick 6 started at tick 5 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[DEBUG] Cloud Position: Cloud 0 is on tile 22, where the amount of sunlight is 76.
[DEBUG] Cloud Position: Cloud 1 is on tile 23, where the amount of sunlight is 76.
[DEBUG] Cloud Position: Cloud 2 is on tile 24, where the amount of sunlight is 76.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 0 finished its actions.
[INFO] Harvest Estimate: Harvest estimate on tile 22 changed to 1239300 g of APPLE.
[INFO] Harvest Estimate: Harvest estimate on tile 23 changed to 1239300 g of APPLE.
[INFO] Harvest Estimate: Harvest estimate on tile 24 changed to 619650 g of APPLE.
[INFO] Simulation Info: Tick 7 started at tick 6 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[DEBUG] Cloud Position: Cloud 0 is on tile 22, where the amount of sunlight is 76.
[DEBUG] Cloud Position: Cloud 1 is on tile 23, where the amount of sunlight is 76.
[DEBUG] Cloud Position: Cloud 2 is on tile 24, where the amount of sunlight is 76.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 0 finished its actions.
[INFO] Harvest Estimate: Harvest estimate on tile 22 changed to 1115370 g of APPLE.
[INFO] Harvest Estimate: Harvest estimate on tile 23 changed to 1115370 g of APPLE.
[INFO] Harvest Estimate: Harvest estimate on tile 24 changed to 557685 g of APPLE.
[INFO] Simulation Info: Tick 8 started at tick 7 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[DEBUG] Cloud Position: Cloud 0 is on tile 22, where the amount of sunlight is 90.
[DEBUG] Cloud Position: Cloud 1 is on tile 23, where the amount of sunlight is 90.
[DEBUG] Cloud Position: Cloud 2 is on tile 24, where the amount of sunlight is 90.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 0 finished its actions.
[INFO] Harvest Estimate: Harvest estimate on tile 22 changed to 1003833 g of APPLE.
[INFO] Harvest Estimate: Harvest estimate on tile 23 changed to 1003833 g of APPLE.
[INFO] Harvest Estimate: Harvest estimate on tile 24 changed to 501916 g of APPLE.
[INFO] Simulation Info: Tick 9 started at tick 8 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[DEBUG] Cloud Position: Cloud 0 is on tile 22, where the amount of sunlight is 90.
[DEBUG] Cloud Position: Cloud 1 is on tile 23, where the amount of sunlight is 90.
[DEBUG] Cloud Position: Cloud 2 is on tile 24, where the amount of sunlight is 90.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 0 finished its actions.
[INFO] Harvest Estimate: Harvest estimate on tile 22 changed to 903449 g of APPLE.
[INFO] Harvest Estimate: Harvest estimate on tile 23 changed to 903449 g of APPLE.
[INFO] Harvest Estimate: Harvest estimate on tile 24 changed to 451724 g of APPLE.
[INFO] Simulation Info: Tick 10 started at tick 9 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[DEBUG] Cloud Position: Cloud 0 is on tile 22, where the amount of sunlight is 118.
[DEBUG] Cloud Position: Cloud 1 is on tile 23, where the amount of sunlight is 118.
[DEBUG] Cloud Position: Cloud 2 is on tile 24, where the amount of sunlight is 118.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 0 finished its actions.
[INFO] Harvest Estimate: Harvest estimate on tile 22 changed to 731793 g of APPLE.
[INFO] Harvest Estimate: Harvest estimate on tile 23 changed to 731793 g of APPLE.
[INFO] Harvest Estimate: Harvest estimate on tile 24 changed to 365895 g of APPLE.
[INFO] Simulation Info: Tick 11 started at tick 10 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[DEBUG] Cloud Position: Cloud 0 is on tile 22, where the amount of sunlight is 118.
[DEBUG] Cloud Position: Cloud 1 is on tile 23, where the amount of sunlight is 118.
[DEBUG] Cloud Position: Cloud 2 is on tile 24, where the amount of sunlight is 118.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 0 finished its actions.
[INFO] Harvest Estimate: Harvest estimate on tile 22 changed to 592751 g of APPLE.
[INFO] Harvest Estimate: Harvest estimate on tile 23 changed to 592751 g of APPLE.
[INFO] Harvest Estimate: Harvest estimate on tile 24 changed to 296374 g of APPLE.
[INFO] Simulation Info: Tick 12 started at tick 11 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[DEBUG] Cloud Position: Cloud 0 is on tile 22, where the amount of sunlight is 118.
[DEBUG] Cloud Position: Cloud 1 is on tile 23, where the amount of sunlight is 118.
[DEBUG] Cloud Position: Cloud 2 is on tile 24, where the amount of sunlight is 118.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm Action: Machine 0 performs MOWING on tile 22 for 4 days.
[IMPORTANT] Farm Action: Machine 0 performs MOWING on tile 23 for 4 days.
[IMPORTANT] Farm Machine: Machine 0 is finished and returns to the shed at 0.
[IMPORTANT] Farm: Farm 0 finished its actions.
[INFO] Harvest Estimate: Harvest estimate on tile 22 changed to 480127 g of APPLE.
[INFO] Harvest Estimate: Harvest estimate on tile 23 changed to 480127 g of APPLE.
[DEBUG] Harvest Estimate: Required actions on tile 24 were not performed: MOWING.
[INFO] Harvest Estimate: Harvest estimate on tile 24 changed to 216055 g of APPLE.
[INFO] Simulation Info: Tick 13 started at tick 12 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[DEBUG] Cloud Position: Cloud 0 is on tile 22, where the amount of sunlight is 118.
[DEBUG] Cloud Position: Cloud 1 is on tile 23, where the amount of sunlight is 118.
[DEBUG] Cloud Position: Cloud 2 is on tile 24, where the amount of sunlight is 118.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 0 finished its actions.
[INFO] Harvest Estimate: Harvest estimate on tile 22 changed to 388902 g of APPLE.
[INFO] Harvest Estimate: Harvest estimate on tile 23 changed to 388902 g of APPLE.
[INFO] Harvest Estimate: Harvest estimate on tile 24 changed to 175004 g of APPLE.
[INFO] Simulation Info: Tick 14 started at tick 13 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[DEBUG] Cloud Position: Cloud 0 is on tile 22, where the amount of sunlight is 118.
[DEBUG] Cloud Position: Cloud 1 is on tile 23, where the amount of sunlight is 118.
[DEBUG] Cloud Position: Cloud 2 is on tile 24, where the amount of sunlight is 118.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 0 finished its actions.
[INFO] Harvest Estimate: Harvest estimate on tile 22 changed to 315009 g of APPLE.
[INFO] Harvest Estimate: Harvest estimate on tile 23 changed to 315009 g of APPLE.
[INFO] Harvest Estimate: Harvest estimate on tile 24 changed to 141752 g of APPLE.
[INFO] Simulation Info: Tick 15 started at tick 14 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[DEBUG] Cloud Position: Cloud 0 is on tile 22, where the amount of sunlight is 118.
[DEBUG] Cloud Position: Cloud 1 is on tile 23, where the amount of sunlight is 118.
[DEBUG] Cloud Position: Cloud 2 is on tile 24, where the amount of sunlight is 118.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 0 finished its actions.
[INFO] Harvest Estimate: Harvest estimate on tile 22 changed to 255157 g of APPLE.
[INFO] Harvest Estimate: Harvest estimate on tile 23 changed to 255157 g of APPLE.
[INFO] Harvest Estimate: Harvest estimate on tile 24 changed to 114818 g of APPLE.
[INFO] Simulation Info: Tick 16 started at tick 15 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[DEBUG] Cloud Position: Cloud 0 is on tile 22, where the amount of sunlight is 104.
[DEBUG] Cloud Position: Cloud 1 is on tile 23, where the amount of sunlight is 104.
[DEBUG] Cloud Position: Cloud 2 is on tile 24, where the amount of sunlight is 104.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 0 finished its actions.
[INFO] Harvest Estimate: Harvest estimate on tile 22 changed to 206676 g of APPLE.
[INFO] Harvest Estimate: Harvest estimate on tile 23 changed to 206676 g of APPLE.
[INFO] Harvest Estimate: Harvest estimate on tile 24 changed to 93002 g of APPLE.
[INFO] Simulation Info: Tick 17 started at tick 16 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[DEBUG] Cloud Position: Cloud 0 is on tile 22, where the amount of sunlight is 104.
[DEBUG] Cloud Position: Cloud 1 is on tile 23, where the amount of sunlight is 104.
[DEBUG] Cloud Position: Cloud 2 is on tile 24, where the amount of sunlight is 104.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 0 finished its actions.
[INFO] Harvest Estimate: Harvest estimate on tile 22 changed to 167407 g of APPLE.
[INFO] Harvest Estimate: Harvest estimate on tile 23 changed to 167407 g of APPLE.
[INFO] Harvest Estimate: Harvest estimate on tile 24 changed to 75330 g of APPLE.
[INFO] Simulation Info: Tick 18 started at tick 17 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[DEBUG] Cloud Position: Cloud 0 is on tile 22, where the amount of sunlight is 76.
[DEBUG] Cloud Position: Cloud 1 is on tile 23, where the amount of sunlight is 76.
[DEBUG] Cloud Position: Cloud 2 is on tile 24, where the amount of sunlight is 76.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm Action: Machine 2 performs HARVESTING on tile 22 for 4 days.
[IMPORTANT] Farm Harvest: Machine 2 has collected 167407 g of APPLE harvest.
[IMPORTANT] Farm Action: Machine 2 performs HARVESTING on tile 23 for 4 days.
[IMPORTANT] Farm Harvest: Machine 2 has collected 167407 g of APPLE harvest.
[IMPORTANT] Farm Machine: Machine 2 is finished and returns to the shed at 0.
[IMPORTANT] Farm Machine: Machine 2 unloads 334814 g of APPLE harvest in the shed.
[IMPORTANT] Farm: Farm 0 finished its actions.
[DEBUG] Harvest Estimate: Required actions on tile 24 were not performed: MOWING.
[INFO] Harvest Estimate: Harvest estimate on tile 24 changed to 61017 g of APPLE.
[INFO] Simulation Info: Tick 19 started at tick 18 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[DEBUG] Cloud Position: Cloud 0 is on tile 22, where the amount of sunlight is 76.
[DEBUG] Cloud Position: Cloud 1 is on tile 23, where the amount of sunlight is 76.
[DEBUG] Cloud Position: Cloud 2 is on tile 24, where the amount of sunlight is 76.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 0 finished its actions.
[INFO] Harvest Estimate: Harvest estimate on tile 24 changed to 54915 g of APPLE.
[INFO] Simulation Info: Tick 20 started at tick 19 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[DEBUG] Cloud Position: Cloud 0 is on tile 22, where the amount of sunlight is 62.
[DEBUG] Cloud Position: Cloud 1 is on tile 23, where the amount of sunlight is 62.
[DEBUG] Cloud Position: Cloud 2 is on tile 24, where the amount of sunlight is 62.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 0 finished its actions.
[DEBUG] Harvest Estimate: Required actions on tile 24 were not performed: HARVESTING.
[INFO] Harvest Estimate: Harvest estimate on tile 24 changed to 27457 g of APPLE.
[INFO] Simulation Info: Tick 21 started at tick 20 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[DEBUG] Cloud Position: Cloud 0 is on tile 22, where the amount of sunlight is 62.
[DEBUG] Cloud Position: Cloud 1 is on tile 23, where the amount of sunlight is 62.
[DEBUG] Cloud Position: Cloud 2 is on tile 24, where the amount of sunlight is 62.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 0 finished its actions.
[DEBUG] Harvest Estimate: Required actions on tile 24 were not performed: HARVESTING.
[INFO] Harvest Estimate: Harvest estimate on tile 24 changed to 0 g of APPLE.
[INFO] Simulation Info: Tick 22 started at tick 21 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[DEBUG] Cloud Position: Cloud 0 is on tile 22, where the amount of sunlight is 48.
[DEBUG] Cloud Position: Cloud 1 is on tile 23, where the amount of sunlight is 48.
[DEBUG] Cloud Position: Cloud 2 is on tile 24, where the amount of sunlight is 48.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm Action: Machine 0 performs CUTTING on tile 22 for 4 days.
[IMPORTANT] Farm Action: Machine 0 performs CUTTING on tile 23 for 4 days.
[IMPORTANT] Farm Machine: Machine 0 is finished and returns to the shed at 0.
[IMPORTANT] Farm: Farm 0 finished its actions.
[INFO] Harvest Estimate: Harvest estimate on tile 22 changed to 1700000 g of APPLE.
[INFO] Harvest Estimate: Harvest estimate on tile 23 changed to 1700000 g of APPLE.
[INFO] Harvest Estimate: Harvest estimate on tile 24 changed to 1700000 g of APPLE.
[IMPORTANT] Simulation Info: Simulation ended at tick 23.
[IMPORTANT] Simulation Info: Simulation statistics are calculated.
[IMPORTANT] Simulation Statistics: Farm 0 collected 334814 g of harvest.
[IMPORTANT] Simulation Statistics: Total amount of POTATO harvested: 0 g.
[IMPORTANT] Simulation Statistics: Total amount of WHEAT harvested: 0 g.
[IMPORTANT] Simulation Statistics: Total amount of OAT harvested: 0 g.
[IMPORTANT] Simulation Statistics: Total amount of PUMPKIN harvested: 0 g.
[IMPORTANT] Simulation Statistics: Total amount of APPLE harvested: 334814 g.
[IMPORTANT] Simulation Statistics: Total amount of GRAPE harvested: 0 g.
[IMPORTANT] Simulation Statistics: Total amount of ALMOND harvested: 0 g.
[IMPORTANT] Simulation Statistics: Total amount of CHERRY harvested: 0 g.
[IMPORTANT] Simulation Statistics: Total harvest estimate still in fields and plantations: 5100000 g."""

/**
 * full sim of the test AppleCycle
 */
class AppleCycleFull : ExampleSystemTestExtension() {
    override val name = "AppleLife"
    override val description = "Tests a cycle for Apple"

    override val map = "basicApple/appleMap.json"
    override val farms = "basicApple/appleFarm.json"
    override val scenario = "basicApple/appleScenario.json"

    override val logLevel = "DEBUG"

    override val maxTicks = 23
    override val startYearTick = YearTick.DECEMBER_1

    override suspend fun run() {
        val logLines = LOG.trim().lines()
        val startIdx = logLines.indexOf("[INFO] Simulation Info: Simulation started at tick 23 within the year.")
        require(startIdx != -1) { "Start line not found in LOG." }
        skipUntilString("[INFO] Initialization Info: appleScenario.json successfully parsed and validated.")
        for (i in startIdx until logLines.size) {
            assertNextLine(logLines[i])
        }
    }
}
