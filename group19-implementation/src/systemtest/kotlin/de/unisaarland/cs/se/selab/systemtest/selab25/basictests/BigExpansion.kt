package de.unisaarland.cs.se.selab.systemtest.selab25.basictests

import de.unisaarland.cs.se.selab.systemtest.selab25.utils.ExampleSystemTestExtension

private const val LOG = """[INFO] Initialization Info: expansionMap.json successfully parsed and validated.
[INFO] Initialization Info: expansionFarms.json successfully parsed and validated.
[INFO] Initialization Info: expansionScenario.json successfully parsed and validated.
[INFO] Simulation Info: Simulation started at tick 13 within the year.

[INFO] Simulation Info: Tick 0 started at tick 13 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 1 PLANTATION tiles.
[INFO] Cloud Movement: Cloud 0 with 30 L water moved from tile 9 to tile 10.
[INFO] Cloud Dissipation: Cloud 0 got stuck on tile 10.
[IMPORTANT] Cloud Rain: Cloud 1 on tile 87 rained down 70 L water.
[INFO] Cloud Movement: Cloud 1 with 18318 L water moved from tile 87 to tile 79.
[DEBUG] Cloud Movement: On tile 87, the amount of sunlight is 165.
[IMPORTANT] Cloud Rain: Cloud 1 on tile 79 rained down 18318 L water.
[INFO] Cloud Dissipation: Cloud 1 dissipates on tile 79.
[IMPORTANT] Cloud Rain: Cloud 2 on tile 32 rained down 5050 L water.
[INFO] Cloud Dissipation: Cloud 2 dissipates on tile 32.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: 1,2,3,8.
[IMPORTANT] Farm Action: Machine 0 performs SOWING on tile 39 for 1 days.
[IMPORTANT] Farm Sowing: Machine 0 has sowed PUMPKIN according to sowing plan 1.
[IMPORTANT] Farm Machine: Machine 0 is finished and returns to the shed at 20.
[IMPORTANT] Farm Action: Machine 3 performs SOWING on tile 43 for 1 days.
[IMPORTANT] Farm Sowing: Machine 3 has sowed PUMPKIN according to sowing plan 1.
[IMPORTANT] Farm Machine: Machine 3 is finished and returns to the shed at 20.
[IMPORTANT] Farm Action: Machine 2 performs HARVESTING on tile 56 for 1 days.
[IMPORTANT] Farm Harvest: Machine 2 has collected 1200000 g of CHERRY harvest.
[IMPORTANT] Farm Machine: Machine 2 is finished and returns to the shed at 20.
[IMPORTANT] Farm Machine: Machine 2 unloads 1200000 g of CHERRY harvest in the shed.
[IMPORTANT] Farm Action: Machine 1 performs MOWING on tile 57 for 1 days.
[IMPORTANT] Farm Machine: Machine 1 is finished and returns to the shed at 20.
[IMPORTANT] Farm: Farm 0 finished its actions.
[IMPORTANT] Incident: Incident 1 of type CITY_EXPANSION happened and affected tiles 17.
[INFO] Harvest Estimate: Harvest estimate on tile 11 changed to 1115370 g of APPLE.
[INFO] Harvest Estimate: Harvest estimate on tile 39 changed to 360000 g of PUMPKIN.
[DEBUG] Harvest Estimate: Required actions on tile 43 were not performed: IRRIGATING.
[INFO] Harvest Estimate: Harvest estimate on tile 43 changed to 359800 g of PUMPKIN.
[DEBUG] Harvest Estimate: Required actions on tile 69 were not performed: IRRIGATING.
[INFO] Harvest Estimate: Harvest estimate on tile 69 changed to 0 g of ALMOND.
[DEBUG] Harvest Estimate: Required actions on tile 86 were not performed: MOWING.
[INFO] Harvest Estimate: Harvest estimate on tile 86 changed to 1080000 g of GRAPE.

[INFO] Simulation Info: Tick 1 started at tick 14 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 1 FIELD and 1 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: 2,3,8.
[IMPORTANT] Farm Action: Machine 1 performs CUTTING on tile 57 for 1 days.
[IMPORTANT] Farm Machine: Machine 1 is finished and returns to the shed at 20.
[IMPORTANT] Farm Action: Machine 0 performs IRRIGATING on tile 43 for 1 days.
[IMPORTANT] Farm Machine: Machine 0 is finished and returns to the shed at 20.
[IMPORTANT] Farm: Farm 0 finished its actions.
[IMPORTANT] Incident: Incident 2 of type CITY_EXPANSION happened and affected tiles 70.
[IMPORTANT] Incident: Incident 3 of type CLOUD_CREATION happened and affected tiles 25,32,33,39,40,41,46,47,48,49,53,54,55,56,57,61,62,63,64,69,71,77,78.
[INFO] Harvest Estimate: Harvest estimate on tile 11 changed to 731793 g of APPLE.
[INFO] Harvest Estimate: Harvest estimate on tile 39 changed to 324000 g of PUMPKIN.
[DEBUG] Harvest Estimate: Required actions on tile 43 were not performed: IRRIGATING.
[INFO] Harvest Estimate: Harvest estimate on tile 43 changed to 323620 g of PUMPKIN.

[INFO] Simulation Info: Tick 2 started at tick 15 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 1 FIELD and 2 PLANTATION tiles.
[INFO] Cloud Movement: Cloud 3 with 4000 L water moved from tile 25 to tile 32.
[IMPORTANT] Cloud Union: Clouds 4 and 3 united to cloud 26 with 8000 L water and duration 2 on tile 32.
[INFO] Cloud Movement: Cloud 6 with 4000 L water moved from tile 39 to tile 32.
[DEBUG] Cloud Movement: On tile 39, the amount of sunlight is 151.
[IMPORTANT] Cloud Union: Clouds 26 and 6 united to cloud 27 with 12000 L water and duration 2 on tile 32.
[INFO] Cloud Movement: Cloud 8 with 4000 L water moved from tile 41 to tile 26.
[INFO] Cloud Movement: Cloud 8 with 4000 L water moved from tile 26 to tile 41.
[INFO] Cloud Movement: Cloud 8 with 4000 L water moved from tile 41 to tile 26.
[INFO] Cloud Movement: Cloud 8 with 4000 L water moved from tile 26 to tile 41.
[INFO] Cloud Movement: Cloud 8 with 4000 L water moved from tile 41 to tile 26.
[INFO] Cloud Movement: Cloud 8 with 4000 L water moved from tile 26 to tile 41.
[INFO] Cloud Movement: Cloud 8 with 4000 L water moved from tile 41 to tile 26.
[INFO] Cloud Movement: Cloud 8 with 4000 L water moved from tile 26 to tile 41.
[INFO] Cloud Movement: Cloud 8 with 4000 L water moved from tile 41 to tile 26.
[INFO] Cloud Movement: Cloud 8 with 4000 L water moved from tile 26 to tile 41.
[INFO] Cloud Movement: Cloud 14 with 4000 L water moved from tile 54 to tile 53.
[IMPORTANT] Cloud Union: Clouds 13 and 14 united to cloud 28 with 8000 L water and duration 1 on tile 53.
[INFO] Cloud Movement: Cloud 15 with 4000 L water moved from tile 55 to tile 63.
[IMPORTANT] Cloud Union: Clouds 20 and 15 united to cloud 29 with 8000 L water and duration 2 on tile 63.
[INFO] Cloud Movement: Cloud 17 with 4000 L water moved from tile 57 to tile 72.
[DEBUG] Cloud Movement: On tile 57, the amount of sunlight is 151.
[INFO] Cloud Movement: Cloud 17 with 4000 L water moved from tile 72 to tile 71.
[IMPORTANT] Cloud Union: Clouds 23 and 17 united to cloud 30 with 8000 L water and duration 2 on tile 71.
[IMPORTANT] Cloud Rain: Cloud 27 on tile 32 rained down 12000 L water.
[INFO] Cloud Dissipation: Cloud 27 dissipates on tile 32.
[IMPORTANT] Cloud Rain: Cloud 28 on tile 53 rained down 8000 L water.
[INFO] Cloud Dissipation: Cloud 28 dissipates on tile 53.
[IMPORTANT] Cloud Rain: Cloud 29 on tile 63 rained down 8000 L water.
[INFO] Cloud Dissipation: Cloud 29 dissipates on tile 63.
[IMPORTANT] Cloud Rain: Cloud 30 on tile 71 rained down 8000 L water.
[INFO] Cloud Dissipation: Cloud 30 dissipates on tile 71.
[DEBUG] Cloud Position: Cloud 16 is on tile 56, where the amount of sunlight is 104.
[DEBUG] Cloud Position: Cloud 22 is on tile 69, where the amount of sunlight is 104.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: 2,3,8.
[IMPORTANT] Farm Action: Machine 1 performs CUTTING on tile 86 for 1 days.
[IMPORTANT] Farm Machine: Machine 1 is finished and returns to the shed at 20.
[IMPORTANT] Farm Action: Machine 0 performs IRRIGATING on tile 43 for 1 days.
[IMPORTANT] Farm Machine: Machine 0 is finished and returns to the shed at 20.
[IMPORTANT] Farm Action: Machine 2 performs IRRIGATING on tile 56 for 1 days.
[IMPORTANT] Farm Machine: Machine 2 is finished and returns to the shed at 20.
[IMPORTANT] Farm Action: Machine 3 performs WEEDING on tile 39 for 1 days.
[IMPORTANT] Farm Machine: Machine 3 is finished and returns to the shed at 20.
[IMPORTANT] Farm: Farm 0 finished its actions.
[IMPORTANT] Incident: Incident 4 of type CITY_EXPANSION happened and affected tiles 24.
[IMPORTANT] Incident: Incident 5 of type CLOUD_CREATION happened and affected tiles 9,16,18,23,25,26,30,31,32,33,34,38,39,40,41,42,45,46,47,48,49,53,54,55,56,57,60,61,62,63,64,68,69,71,76,77,78,84.
[IMPORTANT] Cloud Union: Clouds 5 and 40 united to cloud 41 with 9000 L water and duration 1 on tile 33.
[IMPORTANT] Cloud Union: Clouds 7 and 45 united to cloud 46 with 9000 L water and duration 1 on tile 40.
[IMPORTANT] Cloud Union: Clouds 8 and 47 united to cloud 48 with 9000 L water and duration 1 on tile 41.
[IMPORTANT] Cloud Union: Clouds 9 and 51 united to cloud 52 with 9000 L water and duration 1 on tile 46.
[IMPORTANT] Cloud Union: Clouds 10 and 53 united to cloud 54 with 9000 L water and duration 1 on tile 47.
[IMPORTANT] Cloud Union: Clouds 11 and 55 united to cloud 56 with 9000 L water and duration 1 on tile 48.
[IMPORTANT] Cloud Union: Clouds 12 and 57 united to cloud 58 with 9000 L water and duration 1 on tile 49.
[IMPORTANT] Cloud Union: Clouds 16 and 62 united to cloud 63 with 9000 L water and duration 1 on tile 56.
[IMPORTANT] Cloud Union: Clouds 18 and 66 united to cloud 67 with 9000 L water and duration 1 on tile 61.
[IMPORTANT] Cloud Union: Clouds 19 and 68 united to cloud 69 with 9000 L water and duration 1 on tile 62.
[IMPORTANT] Cloud Union: Clouds 21 and 71 united to cloud 72 with 9000 L water and duration 1 on tile 64.
[IMPORTANT] Cloud Union: Clouds 22 and 74 united to cloud 75 with 9000 L water and duration 1 on tile 69.
[IMPORTANT] Cloud Union: Clouds 24 and 78 united to cloud 79 with 9000 L water and duration 1 on tile 77.
[IMPORTANT] Cloud Union: Clouds 25 and 80 united to cloud 81 with 9000 L water and duration 1 on tile 78.
[INFO] Harvest Estimate: Harvest estimate on tile 11 changed to 480127 g of APPLE.
[INFO] Harvest Estimate: Harvest estimate on tile 39 changed to 291600 g of PUMPKIN.
[DEBUG] Harvest Estimate: Required actions on tile 43 were not performed: WEEDING,IRRIGATING.
[INFO] Harvest Estimate: Harvest estimate on tile 43 changed to 261952 g of PUMPKIN.

[INFO] Simulation Info: Tick 3 started at tick 16 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 1 FIELD and 1 PLANTATION tiles.
[IMPORTANT] Cloud Rain: Cloud 31 on tile 9 rained down 5000 L water.
[INFO] Cloud Dissipation: Cloud 31 dissipates on tile 9.
[IMPORTANT] Cloud Rain: Cloud 32 on tile 16 rained down 5000 L water.
[INFO] Cloud Dissipation: Cloud 32 dissipates on tile 16.
[IMPORTANT] Cloud Rain: Cloud 33 on tile 18 rained down 5000 L water.
[INFO] Cloud Dissipation: Cloud 33 dissipates on tile 18.
[IMPORTANT] Cloud Rain: Cloud 34 on tile 23 rained down 5000 L water.
[INFO] Cloud Dissipation: Cloud 34 dissipates on tile 23.
[IMPORTANT] Cloud Rain: Cloud 35 on tile 25 rained down 5000 L water.
[INFO] Cloud Dissipation: Cloud 35 dissipates on tile 25.
[IMPORTANT] Cloud Rain: Cloud 36 on tile 26 rained down 5000 L water.
[INFO] Cloud Dissipation: Cloud 36 dissipates on tile 26.
[IMPORTANT] Cloud Rain: Cloud 37 on tile 30 rained down 5000 L water.
[INFO] Cloud Dissipation: Cloud 37 dissipates on tile 30.
[IMPORTANT] Cloud Rain: Cloud 38 on tile 31 rained down 5000 L water.
[INFO] Cloud Dissipation: Cloud 38 dissipates on tile 31.
[IMPORTANT] Cloud Rain: Cloud 39 on tile 32 rained down 5000 L water.
[INFO] Cloud Dissipation: Cloud 39 dissipates on tile 32.
[IMPORTANT] Cloud Rain: Cloud 41 on tile 33 rained down 9000 L water.
[INFO] Cloud Dissipation: Cloud 41 dissipates on tile 33.
[IMPORTANT] Cloud Rain: Cloud 42 on tile 34 rained down 5000 L water.
[INFO] Cloud Dissipation: Cloud 42 dissipates on tile 34.
[IMPORTANT] Cloud Rain: Cloud 43 on tile 38 rained down 5000 L water.
[INFO] Cloud Dissipation: Cloud 43 dissipates on tile 38.
[IMPORTANT] Cloud Rain: Cloud 44 on tile 39 rained down 370 L water.
[INFO] Cloud Movement: Cloud 44 with 4630 L water moved from tile 39 to tile 32.
[DEBUG] Cloud Movement: On tile 39, the amount of sunlight is 151.
[IMPORTANT] Cloud Rain: Cloud 46 on tile 40 rained down 9000 L water.
[INFO] Cloud Dissipation: Cloud 46 dissipates on tile 40.
[IMPORTANT] Cloud Rain: Cloud 48 on tile 41 rained down 9000 L water.
[INFO] Cloud Dissipation: Cloud 48 dissipates on tile 41.
[IMPORTANT] Cloud Rain: Cloud 49 on tile 42 rained down 280 L water.
[INFO] Cloud Movement: Cloud 49 with 4720 L water moved from tile 42 to tile 43.
[DEBUG] Cloud Movement: On tile 42, the amount of sunlight is 151.
[IMPORTANT] Cloud Rain: Cloud 50 on tile 45 rained down 5000 L water.
[INFO] Cloud Dissipation: Cloud 50 dissipates on tile 45.
[IMPORTANT] Cloud Rain: Cloud 52 on tile 46 rained down 9000 L water.
[INFO] Cloud Dissipation: Cloud 52 dissipates on tile 46.
[IMPORTANT] Cloud Rain: Cloud 54 on tile 47 rained down 9000 L water.
[INFO] Cloud Dissipation: Cloud 54 dissipates on tile 47.
[IMPORTANT] Cloud Rain: Cloud 56 on tile 48 rained down 9000 L water.
[INFO] Cloud Dissipation: Cloud 56 dissipates on tile 48.
[IMPORTANT] Cloud Rain: Cloud 58 on tile 49 rained down 9000 L water.
[INFO] Cloud Dissipation: Cloud 58 dissipates on tile 49.
[IMPORTANT] Cloud Rain: Cloud 59 on tile 53 rained down 5000 L water.
[INFO] Cloud Dissipation: Cloud 59 dissipates on tile 53.
[IMPORTANT] Cloud Rain: Cloud 60 on tile 54 rained down 5000 L water.
[INFO] Cloud Dissipation: Cloud 60 dissipates on tile 54.
[IMPORTANT] Cloud Rain: Cloud 61 on tile 55 rained down 5000 L water.
[INFO] Cloud Dissipation: Cloud 61 dissipates on tile 55.
[IMPORTANT] Cloud Rain: Cloud 63 on tile 56 rained down 100 L water.
[INFO] Cloud Dissipation: Cloud 63 dissipates on tile 56.
[IMPORTANT] Cloud Rain: Cloud 64 on tile 57 rained down 400 L water.
[INFO] Cloud Movement: Cloud 64 with 4600 L water moved from tile 57 to tile 72.
[DEBUG] Cloud Movement: On tile 57, the amount of sunlight is 151.
[INFO] Cloud Movement: Cloud 64 with 4600 L water moved from tile 72 to tile 71.
[IMPORTANT] Cloud Union: Clouds 76 and 64 united to cloud 83 with 9600 L water and duration 3 on tile 71.
[IMPORTANT] Cloud Rain: Cloud 65 on tile 60 rained down 5000 L water.
[INFO] Cloud Dissipation: Cloud 65 dissipates on tile 60.
[IMPORTANT] Cloud Rain: Cloud 67 on tile 61 rained down 9000 L water.
[INFO] Cloud Dissipation: Cloud 67 dissipates on tile 61.
[IMPORTANT] Cloud Rain: Cloud 69 on tile 62 rained down 9000 L water.
[INFO] Cloud Dissipation: Cloud 69 dissipates on tile 62.
[IMPORTANT] Cloud Rain: Cloud 70 on tile 63 rained down 5000 L water.
[INFO] Cloud Dissipation: Cloud 70 dissipates on tile 63.
[IMPORTANT] Cloud Rain: Cloud 72 on tile 64 rained down 9000 L water.
[INFO] Cloud Dissipation: Cloud 72 dissipates on tile 64.
[IMPORTANT] Cloud Rain: Cloud 73 on tile 68 rained down 5000 L water.
[INFO] Cloud Dissipation: Cloud 73 dissipates on tile 68.
[IMPORTANT] Cloud Rain: Cloud 75 on tile 69 rained down 100 L water.
[INFO] Cloud Dissipation: Cloud 75 dissipates on tile 69.
[IMPORTANT] Cloud Rain: Cloud 77 on tile 76 rained down 5000 L water.
[INFO] Cloud Dissipation: Cloud 77 dissipates on tile 76.
[IMPORTANT] Cloud Rain: Cloud 79 on tile 77 rained down 9000 L water.
[INFO] Cloud Dissipation: Cloud 79 dissipates on tile 77.
[IMPORTANT] Cloud Rain: Cloud 81 on tile 78 rained down 9000 L water.
[INFO] Cloud Dissipation: Cloud 81 dissipates on tile 78.
[IMPORTANT] Cloud Rain: Cloud 82 on tile 84 rained down 280 L water.
[INFO] Cloud Movement: Cloud 82 with 4720 L water moved from tile 84 to tile 85.
[DEBUG] Cloud Movement: On tile 84, the amount of sunlight is 151.
[INFO] Cloud Dissipation: Cloud 82 got stuck on tile 85.
[IMPORTANT] Cloud Rain: Cloud 83 on tile 71 rained down 9600 L water.
[INFO] Cloud Dissipation: Cloud 83 dissipates on tile 71.
[DEBUG] Cloud Position: Cloud 49 is on tile 43, where the amount of sunlight is 104.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: 2,3,8.
[IMPORTANT] Farm Action: Machine 0 performs IRRIGATING on tile 43 for 1 days.
[IMPORTANT] Farm Machine: Machine 0 is finished and returns to the shed at 20.
[IMPORTANT] Farm: Farm 0 finished its actions.
[IMPORTANT] Incident: Incident 6 of type CITY_EXPANSION happened and affected tiles 84.
[INFO] Harvest Estimate: Harvest estimate on tile 11 changed to 315009 g of APPLE.
[INFO] Harvest Estimate: Harvest estimate on tile 39 changed to 262440 g of PUMPKIN.
[DEBUG] Harvest Estimate: Required actions on tile 43 were not performed: IRRIGATING.
[INFO] Harvest Estimate: Harvest estimate on tile 43 changed to 261752 g of PUMPKIN.

[INFO] Simulation Info: Tick 4 started at tick 17 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 1 FIELD and 1 PLANTATION tiles.
[DEBUG] Cloud Position: Cloud 49 is on tile 43, where the amount of sunlight is 76.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: 2,3,8.
[IMPORTANT] Farm Action: Machine 0 performs HARVESTING on tile 11 for 1 days.
[IMPORTANT] Farm Harvest: Machine 0 has collected 315009 g of APPLE harvest.
[IMPORTANT] Farm Machine: Machine 0 is finished and returns to the shed at 20.
[IMPORTANT] Farm Machine: Machine 0 unloads 315009 g of APPLE harvest in the shed.
[IMPORTANT] Farm Action: Machine 3 performs HARVESTING on tile 39 for 1 days.
[IMPORTANT] Farm Harvest: Machine 3 has collected 262440 g of PUMPKIN harvest.
[IMPORTANT] Farm Machine: Machine 3 is finished and returns to the shed at 20.
[IMPORTANT] Farm Machine: Machine 3 unloads 262440 g of PUMPKIN harvest in the shed.
[IMPORTANT] Farm: Farm 0 finished its actions.
[IMPORTANT] Incident: Incident 7 of type CITY_EXPANSION happened and affected tiles 39.
[DEBUG] Harvest Estimate: Required actions on tile 43 were not performed: WEEDING,IRRIGATING.
[INFO] Harvest Estimate: Harvest estimate on tile 43 changed to 235351 g of PUMPKIN.
[DEBUG] Harvest Estimate: Required actions on tile 57 were not performed: HARVESTING.
[INFO] Harvest Estimate: Harvest estimate on tile 57 changed to 1140000 g of GRAPE.
[DEBUG] Harvest Estimate: Required actions on tile 86 were not performed: HARVESTING.
[INFO] Harvest Estimate: Harvest estimate on tile 86 changed to 1026000 g of GRAPE.

[INFO] Simulation Info: Tick 5 started at tick 18 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 1 FIELD and 1 PLANTATION tiles.
[INFO] Cloud Dissipation: Cloud 44 dissipates on tile 32.
[INFO] Cloud Dissipation: Cloud 49 dissipates on tile 43.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: 2,3,8.
[IMPORTANT] Farm Action: Machine 0 performs HARVESTING on tile 43 for 1 days.
[IMPORTANT] Farm Harvest: Machine 0 has collected 235351 g of PUMPKIN harvest.
[IMPORTANT] Farm Machine: Machine 0 is finished and returns to the shed at 20.
[IMPORTANT] Farm Machine: Machine 0 unloads 235351 g of PUMPKIN harvest in the shed.
[IMPORTANT] Farm: Farm 0 finished its actions.
[IMPORTANT] Incident: Incident 8 of type CITY_EXPANSION happened and affected tiles 62.
[DEBUG] Harvest Estimate: Required actions on tile 57 were not performed: HARVESTING.
[INFO] Harvest Estimate: Harvest estimate on tile 57 changed to 1083000 g of GRAPE.
[DEBUG] Harvest Estimate: Required actions on tile 86 were not performed: HARVESTING.
[INFO] Harvest Estimate: Harvest estimate on tile 86 changed to 974700 g of GRAPE.

[INFO] Simulation Info: Tick 6 started at tick 19 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 2 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: 2,3,8.
[IMPORTANT] Farm Action: Machine 3 performs SOWING on tile 42 for 1 days.
[IMPORTANT] Farm Sowing: Machine 3 has sowed WHEAT according to sowing plan 2.
[IMPORTANT] Farm Machine: Machine 3 is finished and returns to the shed at 20.
[IMPORTANT] Farm Action: Machine 2 performs IRRIGATING on tile 56 for 1 days.
[IMPORTANT] Farm Machine: Machine 2 is finished and returns to the shed at 20.
[IMPORTANT] Farm: Farm 0 finished its actions.
[IMPORTANT] Incident: Incident 9 of type CITY_EXPANSION happened and affected tiles 54.
[DEBUG] Harvest Estimate: Required actions on tile 57 were not performed: HARVESTING.
[INFO] Harvest Estimate: Harvest estimate on tile 57 changed to 1028850 g of GRAPE.
[DEBUG] Harvest Estimate: Required actions on tile 86 were not performed: HARVESTING.
[INFO] Harvest Estimate: Harvest estimate on tile 86 changed to 925965 g of GRAPE.
[IMPORTANT] Simulation Info: Simulation ended at tick 7.
[IMPORTANT] Simulation Info: Simulation statistics are calculated.
[IMPORTANT] Simulation Statistics: Farm 0 collected 2012800 g of harvest.
[IMPORTANT] Simulation Statistics: Total amount of POTATO harvested: 0 g.
[IMPORTANT] Simulation Statistics: Total amount of WHEAT harvested: 0 g.
[IMPORTANT] Simulation Statistics: Total amount of OAT harvested: 0 g.
[IMPORTANT] Simulation Statistics: Total amount of PUMPKIN harvested: 497791 g.
[IMPORTANT] Simulation Statistics: Total amount of APPLE harvested: 315009 g.
[IMPORTANT] Simulation Statistics: Total amount of GRAPE harvested: 0 g.
[IMPORTANT] Simulation Statistics: Total amount of ALMOND harvested: 0 g.
[IMPORTANT] Simulation Statistics: Total amount of CHERRY harvested: 1200000 g.
[IMPORTANT] Simulation Statistics: Total harvest estimate still in fields and plantations: 3454815 g.

"""

private const val MAP = "example/expansionMap.json"
private const val FARMS = "example/expansionFarms.json"
private const val SCENARIO = "example/expansionScenario.json"
private const val LEVEL = "DEBUG"

/**
 * O_o
 */
class BigExpansion(private val tick: Int) : ExampleSystemTestExtension() {
    override val name = "Big City Expansion(s) ${if (tick == 0) "init" else (tick - 1).toString()}"
    override val description = "City expansion for tick ${if (tick == 0) "init" else (tick - 1).toString()}"

    override val map = MAP
    override val farms = FARMS
    override val scenario = SCENARIO

    override val logLevel = LEVEL
    override val maxTicks = 7
    override val startYearTick = 13

    override suspend fun run() {
        val ticks = LOG.split(Regex("\\n\\s*\\n"))
        val lines = ticks[tick].split(Regex("\\n"))
        skipUntilString(lines.first())
        for (line in lines.drop(1)) {
            assertNextLine(line)
        }
    }
}

// /**
// * O_o
// */
// class BigExpansion3Clouds : ExampleSystemTestExtension() {
//    override val name = "Big City Expansion(s) 3 Cloud "
//    override val description = "City expansion for tick 3"
//
//    override val map = MAP
//    override val farms = FARMS
//    override val scenario = SCENARIO
//
//    override val logLevel = LEVEL
//    override val maxTicks = 7
//    override val startYearTick = 13
//
//    override suspend fun run() {
//
//    }
// }
