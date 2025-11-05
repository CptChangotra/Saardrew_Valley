package de.unisaarland.cs.se.selab.systemtest.selab25.basictests

import de.unisaarland.cs.se.selab.systemtest.selab25.utils.ExampleSystemTestExtension

private const val LOG = """[INFO] Initialization Info: bigMap.json successfully parsed and validated.
[INFO] Initialization Info: bigFarm.json successfully parsed and validated.
[INFO] Initialization Info: bigScenario.json successfully parsed and validated.
[INFO] Simulation Info: Simulation started at tick 17 within the year.

[INFO] Simulation Info: Tick 0 started at tick 17 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 1 PLANTATION tiles.
[INFO] Cloud Movement: Cloud 0 with 30 L water moved from tile 9 to tile 10.
[INFO] Cloud Dissipation: Cloud 0 got stuck on tile 10.
[IMPORTANT] Cloud Rain: Cloud 1 on tile 87 rained down 70 L water.
[INFO] Cloud Movement: Cloud 1 with 72318 L water moved from tile 87 to tile 79.
[DEBUG] Cloud Movement: On tile 87, the amount of sunlight is 123.
[IMPORTANT] Cloud Rain: Cloud 1 on tile 79 rained down 72318 L water.
[INFO] Cloud Dissipation: Cloud 1 dissipates on tile 79.
[IMPORTANT] Cloud Rain: Cloud 2 on tile 32 rained down 5050 L water.
[INFO] Cloud Dissipation: Cloud 2 dissipates on tile 32.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm Action: Machine 0 performs HARVESTING on tile 11 for 2 days.
[IMPORTANT] Farm Harvest: Machine 0 has collected 1700000 g of APPLE harvest.
[IMPORTANT] Farm Machine: Machine 0 is finished and returns to the shed at 20.
[IMPORTANT] Farm Machine: Machine 0 unloads 1700000 g of APPLE harvest in the shed.
[IMPORTANT] Farm Action: Machine 3 performs HARVESTING on tile 69 for 4 days.
[IMPORTANT] Farm Harvest: Machine 3 has collected 800000 g of ALMOND harvest.
[IMPORTANT] Farm Machine: Machine 3 is finished and returns to the shed at 20.
[IMPORTANT] Farm Machine: Machine 3 unloads 800000 g of ALMOND harvest in the shed.
[IMPORTANT] Farm: Farm 0 finished its actions.
[DEBUG] Harvest Estimate: Required actions on tile 57 were not performed: HARVESTING.
[INFO] Harvest Estimate: Harvest estimate on tile 57 changed to 1140000 g of GRAPE.
[DEBUG] Harvest Estimate: Required actions on tile 86 were not performed: HARVESTING.
[INFO] Harvest Estimate: Harvest estimate on tile 86 changed to 1140000 g of GRAPE.

[INFO] Simulation Info: Tick 1 started at tick 18 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 1 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: 3.
[IMPORTANT] Farm: Farm 0 finished its actions.
[DEBUG] Harvest Estimate: Required actions on tile 57 were not performed: HARVESTING.
[INFO] Harvest Estimate: Harvest estimate on tile 57 changed to 1083000 g of GRAPE.
[DEBUG] Harvest Estimate: Required actions on tile 86 were not performed: HARVESTING.
[INFO] Harvest Estimate: Harvest estimate on tile 86 changed to 1083000 g of GRAPE.

[INFO] Simulation Info: Tick 2 started at tick 19 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 2 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: 3.
[IMPORTANT] Farm Action: Machine 2 performs IRRIGATING on tile 56 for 4 days.
[IMPORTANT] Farm Machine: Machine 2 is finished and returns to the shed at 20.
[IMPORTANT] Farm: Farm 0 finished its actions.
[DEBUG] Harvest Estimate: Required actions on tile 57 were not performed: HARVESTING.
[INFO] Harvest Estimate: Harvest estimate on tile 57 changed to 1028850 g of GRAPE.
[DEBUG] Harvest Estimate: Required actions on tile 86 were not performed: HARVESTING.
[INFO] Harvest Estimate: Harvest estimate on tile 86 changed to 1028850 g of GRAPE.

[INFO] Simulation Info: Tick 3 started at tick 20 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 1 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: 1,3.
[IMPORTANT] Farm: Farm 0 finished its actions.
[IMPORTANT] Incident: Incident 701 of type CLOUD_CREATION happened and affected tiles 27,34,35,41,42,43,48,49,50,51,55,56,57,59,62,63,64,65,66,67,69,70,71,72,73,74,77,78,79,80,81,82,86,87,88,89,93,94,95,96,101,102,103,109,110.
[DEBUG] Harvest Estimate: Required actions on tile 57 were not performed: HARVESTING.
[INFO] Harvest Estimate: Harvest estimate on tile 57 changed to 0 g of GRAPE.
[DEBUG] Harvest Estimate: Required actions on tile 86 were not performed: HARVESTING.
[INFO] Harvest Estimate: Harvest estimate on tile 86 changed to 0 g of GRAPE.

[INFO] Simulation Info: Tick 4 started at tick 21 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 1 PLANTATION tiles.
[IMPORTANT] Cloud Rain: Cloud 3 on tile 27 rained down 5050 L water.
[INFO] Cloud Dissipation: Cloud 3 dissipates on tile 27.
[IMPORTANT] Cloud Rain: Cloud 4 on tile 34 rained down 5050 L water.
[INFO] Cloud Dissipation: Cloud 4 dissipates on tile 34.
[IMPORTANT] Cloud Rain: Cloud 5 on tile 35 rained down 5050 L water.
[INFO] Cloud Dissipation: Cloud 5 dissipates on tile 35.
[IMPORTANT] Cloud Rain: Cloud 6 on tile 41 rained down 5050 L water.
[INFO] Cloud Dissipation: Cloud 6 dissipates on tile 41.
[IMPORTANT] Cloud Rain: Cloud 7 on tile 42 rained down 350 L water.
[INFO] Cloud Movement: Cloud 7 with 4700 L water moved from tile 42 to tile 43.
[DEBUG] Cloud Movement: On tile 42, the amount of sunlight is 95.
[IMPORTANT] Cloud Union: Clouds 8 and 7 united to cloud 48 with 9750 L water and duration 3 on tile 43.
[IMPORTANT] Cloud Rain: Cloud 9 on tile 48 rained down 5050 L water.
[INFO] Cloud Dissipation: Cloud 9 dissipates on tile 48.
[IMPORTANT] Cloud Rain: Cloud 10 on tile 49 rained down 5050 L water.
[INFO] Cloud Dissipation: Cloud 10 dissipates on tile 49.
[IMPORTANT] Cloud Rain: Cloud 11 on tile 50 rained down 5050 L water.
[INFO] Cloud Dissipation: Cloud 11 dissipates on tile 50.
[IMPORTANT] Cloud Rain: Cloud 12 on tile 51 rained down 5050 L water.
[INFO] Cloud Dissipation: Cloud 12 dissipates on tile 51.
[IMPORTANT] Cloud Rain: Cloud 13 on tile 55 rained down 5050 L water.
[INFO] Cloud Dissipation: Cloud 13 dissipates on tile 55.
[IMPORTANT] Cloud Rain: Cloud 14 on tile 56 rained down 200 L water.
[IMPORTANT] Cloud Rain: Cloud 15 on tile 57 rained down 500 L water.
[INFO] Cloud Movement: Cloud 15 with 4550 L water moved from tile 57 to tile 72.
[DEBUG] Cloud Movement: On tile 57, the amount of sunlight is 95.
[IMPORTANT] Cloud Union: Clouds 26 and 15 united to cloud 49 with 9600 L water and duration 3 on tile 72.
[IMPORTANT] Cloud Rain: Cloud 16 on tile 59 rained down 5050 L water.
[INFO] Cloud Dissipation: Cloud 16 dissipates on tile 59.
[IMPORTANT] Cloud Rain: Cloud 17 on tile 62 rained down 5050 L water.
[INFO] Cloud Dissipation: Cloud 17 dissipates on tile 62.
[IMPORTANT] Cloud Rain: Cloud 18 on tile 63 rained down 5050 L water.
[INFO] Cloud Dissipation: Cloud 18 dissipates on tile 63.
[IMPORTANT] Cloud Rain: Cloud 19 on tile 64 rained down 5050 L water.
[INFO] Cloud Dissipation: Cloud 19 dissipates on tile 64.
[IMPORTANT] Cloud Rain: Cloud 20 on tile 65 rained down 5050 L water.
[INFO] Cloud Dissipation: Cloud 20 dissipates on tile 65.
[IMPORTANT] Cloud Rain: Cloud 21 on tile 66 rained down 5050 L water.
[INFO] Cloud Dissipation: Cloud 21 dissipates on tile 66.
[IMPORTANT] Cloud Rain: Cloud 22 on tile 67 rained down 5050 L water.
[INFO] Cloud Dissipation: Cloud 22 dissipates on tile 67.
[IMPORTANT] Cloud Rain: Cloud 23 on tile 69 rained down 100 L water.
[IMPORTANT] Cloud Rain: Cloud 24 on tile 70 rained down 350 L water.
[INFO] Cloud Movement: Cloud 24 with 4700 L water moved from tile 70 to tile 63.
[DEBUG] Cloud Movement: On tile 70, the amount of sunlight is 95.
[IMPORTANT] Cloud Rain: Cloud 25 on tile 71 rained down 5050 L water.
[INFO] Cloud Dissipation: Cloud 25 dissipates on tile 71.
[IMPORTANT] Cloud Rain: Cloud 27 on tile 73 rained down 5050 L water.
[INFO] Cloud Dissipation: Cloud 27 dissipates on tile 73.
[IMPORTANT] Cloud Rain: Cloud 28 on tile 74 rained down 5050 L water.
[INFO] Cloud Dissipation: Cloud 28 dissipates on tile 74.
[IMPORTANT] Cloud Rain: Cloud 29 on tile 77 rained down 5050 L water.
[INFO] Cloud Dissipation: Cloud 29 dissipates on tile 77.
[IMPORTANT] Cloud Rain: Cloud 30 on tile 78 rained down 5050 L water.
[INFO] Cloud Dissipation: Cloud 30 dissipates on tile 78.
[IMPORTANT] Cloud Rain: Cloud 31 on tile 79 rained down 5050 L water.
[INFO] Cloud Dissipation: Cloud 31 dissipates on tile 79.
[IMPORTANT] Cloud Rain: Cloud 32 on tile 80 rained down 5050 L water.
[INFO] Cloud Dissipation: Cloud 32 dissipates on tile 80.
[IMPORTANT] Cloud Rain: Cloud 33 on tile 81 rained down 5050 L water.
[INFO] Cloud Dissipation: Cloud 33 dissipates on tile 81.
[IMPORTANT] Cloud Rain: Cloud 34 on tile 82 rained down 5050 L water.
[INFO] Cloud Dissipation: Cloud 34 dissipates on tile 82.
[IMPORTANT] Cloud Rain: Cloud 35 on tile 86 rained down 500 L water.
[IMPORTANT] Cloud Rain: Cloud 36 on tile 87 rained down 280 L water.
[INFO] Cloud Movement: Cloud 36 with 4770 L water moved from tile 87 to tile 79.
[DEBUG] Cloud Movement: On tile 87, the amount of sunlight is 95.
[IMPORTANT] Cloud Rain: Cloud 37 on tile 88 rained down 5050 L water.
[INFO] Cloud Dissipation: Cloud 37 dissipates on tile 88.
[IMPORTANT] Cloud Rain: Cloud 38 on tile 89 rained down 5050 L water.
[INFO] Cloud Dissipation: Cloud 38 dissipates on tile 89.
[IMPORTANT] Cloud Rain: Cloud 39 on tile 93 rained down 5050 L water.
[INFO] Cloud Dissipation: Cloud 39 dissipates on tile 93.
[IMPORTANT] Cloud Rain: Cloud 40 on tile 94 rained down 5050 L water.
[INFO] Cloud Dissipation: Cloud 40 dissipates on tile 94.
[IMPORTANT] Cloud Rain: Cloud 41 on tile 95 rained down 5050 L water.
[INFO] Cloud Dissipation: Cloud 41 dissipates on tile 95.
[IMPORTANT] Cloud Rain: Cloud 42 on tile 96 rained down 5050 L water.
[INFO] Cloud Dissipation: Cloud 42 dissipates on tile 96.
[IMPORTANT] Cloud Rain: Cloud 43 on tile 101 rained down 5050 L water.
[INFO] Cloud Dissipation: Cloud 43 dissipates on tile 101.
[IMPORTANT] Cloud Rain: Cloud 44 on tile 102 rained down 5050 L water.
[INFO] Cloud Dissipation: Cloud 44 dissipates on tile 102.
[IMPORTANT] Cloud Rain: Cloud 45 on tile 103 rained down 5050 L water.
[INFO] Cloud Dissipation: Cloud 45 dissipates on tile 103.
[IMPORTANT] Cloud Rain: Cloud 46 on tile 109 rained down 5050 L water.
[INFO] Cloud Dissipation: Cloud 46 dissipates on tile 109.
[IMPORTANT] Cloud Rain: Cloud 47 on tile 110 rained down 5050 L water.
[INFO] Cloud Dissipation: Cloud 47 dissipates on tile 110.
[IMPORTANT] Cloud Rain: Cloud 48 on tile 43 rained down 200 L water.
[IMPORTANT] Cloud Rain: Cloud 49 on tile 72 rained down 9600 L water.
[INFO] Cloud Dissipation: Cloud 49 dissipates on tile 72.
[DEBUG] Cloud Position: Cloud 48 is on tile 43, where the amount of sunlight is 48.
[DEBUG] Cloud Position: Cloud 14 is on tile 56, where the amount of sunlight is 48.
[DEBUG] Cloud Position: Cloud 23 is on tile 69, where the amount of sunlight is 48.
[DEBUG] Cloud Position: Cloud 35 is on tile 86, where the amount of sunlight is 48.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: 1,3,8.
[IMPORTANT] Farm Action: Machine 0 performs CUTTING on tile 11 for 2 days.
[IMPORTANT] Farm Machine: Machine 0 is finished and returns to the shed at 20.
[IMPORTANT] Farm Action: Machine 3 performs CUTTING on tile 69 for 4 days.
[IMPORTANT] Farm Machine: Machine 3 is finished and returns to the shed at 20.
[IMPORTANT] Farm: Farm 0 finished its actions.
[INFO] Harvest Estimate: Harvest estimate on tile 11 changed to 1530000 g of APPLE.
[INFO] Harvest Estimate: Harvest estimate on tile 56 changed to 1200000 g of CHERRY.
[INFO] Harvest Estimate: Harvest estimate on tile 57 changed to 1200000 g of GRAPE.
[DEBUG] Harvest Estimate: Required actions on tile 69 were not performed: IRRIGATING.
[INFO] Harvest Estimate: Harvest estimate on tile 69 changed to 799850 g of ALMOND.
[INFO] Harvest Estimate: Harvest estimate on tile 86 changed to 1200000 g of GRAPE.

[INFO] Simulation Info: Tick 5 started at tick 22 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 1 PLANTATION tiles.
[IMPORTANT] Cloud Rain: Cloud 48 on tile 43 rained down 70 L water.
[DEBUG] Cloud Position: Cloud 48 is on tile 43, where the amount of sunlight is 48.
[DEBUG] Cloud Position: Cloud 14 is on tile 56, where the amount of sunlight is 48.
[DEBUG] Cloud Position: Cloud 23 is on tile 69, where the amount of sunlight is 48.
[DEBUG] Cloud Position: Cloud 35 is on tile 86, where the amount of sunlight is 48.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: 1,3,8.
[IMPORTANT] Farm: Farm 0 finished its actions.
[INFO] Harvest Estimate: Harvest estimate on tile 11 changed to 1377000 g of APPLE.
[DEBUG] Harvest Estimate: Required actions on tile 69 were not performed: IRRIGATING.
[INFO] Harvest Estimate: Harvest estimate on tile 69 changed to 0 g of ALMOND.

[INFO] Simulation Info: Tick 6 started at tick 23 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 1 PLANTATION tiles.
[INFO] Cloud Dissipation: Cloud 14 dissipates on tile 56.
[INFO] Cloud Dissipation: Cloud 23 dissipates on tile 69.
[INFO] Cloud Dissipation: Cloud 24 dissipates on tile 63.
[INFO] Cloud Dissipation: Cloud 35 dissipates on tile 86.
[INFO] Cloud Dissipation: Cloud 36 dissipates on tile 79.
[IMPORTANT] Cloud Rain: Cloud 48 on tile 43 rained down 70 L water.
[INFO] Cloud Dissipation: Cloud 48 dissipates on tile 43.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: 1,3,8.
[IMPORTANT] Farm: Farm 0 finished its actions.
[INFO] Harvest Estimate: Harvest estimate on tile 11 changed to 1239300 g of APPLE.

[INFO] Simulation Info: Tick 7 started at tick 24 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 2 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: 1,2,3,8.
[IMPORTANT] Farm Action: Machine 2 performs IRRIGATING on tile 56 for 4 days.
[IMPORTANT] Farm Machine: Machine 2 is finished and returns to the shed at 20.
[IMPORTANT] Farm: Farm 0 finished its actions.
[INFO] Harvest Estimate: Harvest estimate on tile 11 changed to 1115370 g of APPLE.

[INFO] Simulation Info: Tick 8 started at tick 1 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 1 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: 1,2,3,8.
[IMPORTANT] Farm: Farm 0 finished its actions.
[INFO] Harvest Estimate: Harvest estimate on tile 11 changed to 1003833 g of APPLE.

[INFO] Simulation Info: Tick 9 started at tick 2 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 1 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: 1,2,3,8.
[IMPORTANT] Farm: Farm 0 finished its actions.
[INFO] Harvest Estimate: Harvest estimate on tile 11 changed to 903449 g of APPLE.

[INFO] Simulation Info: Tick 10 started at tick 3 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 2 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: 1,2,3,8.
[IMPORTANT] Farm Action: Machine 2 performs IRRIGATING on tile 56 for 4 days.
[IMPORTANT] Farm Machine: Machine 2 is finished and returns to the shed at 20.
[IMPORTANT] Farm: Farm 0 finished its actions.
[IMPORTANT] Incident: Incident 1 of type ANIMAL_ATTACK happened and affected tiles 11,39,42,56,69.
[IMPORTANT] Incident: Incident 500 of type BROKEN_MACHINE happened and affected tiles 20.
[INFO] Harvest Estimate: Harvest estimate on tile 11 changed to 658613 g of APPLE.
[INFO] Harvest Estimate: Harvest estimate on tile 56 changed to 1080000 g of CHERRY.

[INFO] Simulation Info: Tick 11 started at tick 4 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 2 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: 1,2,3,8.
[IMPORTANT] Farm Action: Machine 1 performs IRRIGATING on tile 57 for 4 days.
[IMPORTANT] Farm Machine: Machine 1 is finished and returns to the shed at 20.
[IMPORTANT] Farm: Farm 0 finished its actions.
[IMPORTANT] Incident: Incident 100 of type CITY_EXPANSION happened and affected tiles 9.
[INFO] Harvest Estimate: Harvest estimate on tile 11 changed to 533475 g of APPLE.
[DEBUG] Harvest Estimate: Required actions on tile 56 were not performed: CUTTING.
[INFO] Harvest Estimate: Harvest estimate on tile 56 changed to 540000 g of CHERRY.

[INFO] Simulation Info: Tick 12 started at tick 5 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 1 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: 1,2,3,8.
[IMPORTANT] Farm: Farm 0 finished its actions.
[IMPORTANT] Incident: Incident 700 of type CLOUD_CREATION happened and affected tiles 1,2,3,4,8,11,12,16,17,18,19,24,25,26,32,33,40.
[INFO] Harvest Estimate: Harvest estimate on tile 11 changed to 388902 g of APPLE.

[INFO] Simulation Info: Tick 13 started at tick 6 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 2 PLANTATION tiles.
[INFO] Cloud Movement: Cloud 62 with 4000 L water moved from tile 25 to tile 32.
[IMPORTANT] Cloud Union: Clouds 64 and 62 united to cloud 67 with 8000 L water and duration 2 on tile 32.
[INFO] Cloud Movement: Cloud 63 with 4000 L water moved from tile 26 to tile 41.
[INFO] Cloud Movement: Cloud 63 with 4000 L water moved from tile 41 to tile 26.
[INFO] Cloud Movement: Cloud 63 with 4000 L water moved from tile 26 to tile 41.
[INFO] Cloud Movement: Cloud 63 with 4000 L water moved from tile 41 to tile 26.
[INFO] Cloud Movement: Cloud 63 with 4000 L water moved from tile 26 to tile 41.
[INFO] Cloud Movement: Cloud 63 with 4000 L water moved from tile 41 to tile 26.
[INFO] Cloud Movement: Cloud 63 with 4000 L water moved from tile 26 to tile 41.
[INFO] Cloud Movement: Cloud 63 with 4000 L water moved from tile 41 to tile 26.
[INFO] Cloud Movement: Cloud 63 with 4000 L water moved from tile 26 to tile 41.
[INFO] Cloud Movement: Cloud 63 with 4000 L water moved from tile 41 to tile 26.
[IMPORTANT] Cloud Rain: Cloud 67 on tile 32 rained down 8000 L water.
[INFO] Cloud Dissipation: Cloud 67 dissipates on tile 32.
[DEBUG] Cloud Position: Cloud 55 is on tile 11, where the amount of sunlight is 76.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: 1,2,3,8.
[IMPORTANT] Farm Action: Machine 2 performs SOWING on tile 42 for 4 days.
[IMPORTANT] Farm Sowing: Machine 2 has sowed OAT according to sowing plan 3.
[IMPORTANT] Farm Machine: Machine 2 is finished and returns to the shed at 20.
[IMPORTANT] Farm: Farm 0 finished its actions.
[INFO] Harvest Estimate: Harvest estimate on tile 11 changed to 350011 g of APPLE.
[INFO] Harvest Estimate: Harvest estimate on tile 42 changed to 1080000 g of OAT.

[INFO] Simulation Info: Tick 14 started at tick 7 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 2 PLANTATION tiles.
[INFO] Cloud Dissipation: Cloud 50 dissipates on tile 1.
[INFO] Cloud Dissipation: Cloud 51 dissipates on tile 2.
[INFO] Cloud Dissipation: Cloud 52 dissipates on tile 3.
[INFO] Cloud Dissipation: Cloud 53 dissipates on tile 4.
[INFO] Cloud Dissipation: Cloud 54 dissipates on tile 8.
[INFO] Cloud Dissipation: Cloud 55 dissipates on tile 11.
[INFO] Cloud Dissipation: Cloud 56 dissipates on tile 12.
[INFO] Cloud Dissipation: Cloud 57 dissipates on tile 16.
[INFO] Cloud Dissipation: Cloud 58 dissipates on tile 17.
[INFO] Cloud Dissipation: Cloud 59 dissipates on tile 18.
[INFO] Cloud Dissipation: Cloud 60 dissipates on tile 19.
[INFO] Cloud Dissipation: Cloud 61 dissipates on tile 24.
[INFO] Cloud Movement: Cloud 63 with 4000 L water moved from tile 26 to tile 41.
[INFO] Cloud Movement: Cloud 63 with 4000 L water moved from tile 41 to tile 26.
[INFO] Cloud Movement: Cloud 63 with 4000 L water moved from tile 26 to tile 41.
[INFO] Cloud Movement: Cloud 63 with 4000 L water moved from tile 41 to tile 26.
[INFO] Cloud Movement: Cloud 63 with 4000 L water moved from tile 26 to tile 41.
[INFO] Cloud Movement: Cloud 63 with 4000 L water moved from tile 41 to tile 26.
[INFO] Cloud Movement: Cloud 63 with 4000 L water moved from tile 26 to tile 41.
[INFO] Cloud Movement: Cloud 63 with 4000 L water moved from tile 41 to tile 26.
[INFO] Cloud Movement: Cloud 63 with 4000 L water moved from tile 26 to tile 41.
[INFO] Cloud Movement: Cloud 63 with 4000 L water moved from tile 41 to tile 26.
[INFO] Cloud Dissipation: Cloud 63 dissipates on tile 26.
[INFO] Cloud Dissipation: Cloud 65 dissipates on tile 33.
[INFO] Cloud Dissipation: Cloud 66 dissipates on tile 40.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: 1,2,8.
[IMPORTANT] Farm Action: Machine 1 performs SOWING on tile 43 for 4 days.
[IMPORTANT] Farm Sowing: Machine 1 has sowed POTATO according to sowing plan 8.
[IMPORTANT] Farm Machine: Machine 1 is finished and returns to the shed at 20.
[IMPORTANT] Farm Action: Machine 2 performs WEEDING on tile 42 for 4 days.
[IMPORTANT] Farm Machine: Machine 2 is finished and returns to the shed at 20.
[IMPORTANT] Farm: Farm 0 finished its actions.
[INFO] Harvest Estimate: Harvest estimate on tile 11 changed to 255157 g of APPLE.
[INFO] Harvest Estimate: Harvest estimate on tile 42 changed to 874800 g of OAT.
[DEBUG] Harvest Estimate: Required actions on tile 43 were not performed: IRRIGATING.
[INFO] Harvest Estimate: Harvest estimate on tile 43 changed to 0 g of POTATO.
[DEBUG] Harvest Estimate: Required actions on tile 56 were not performed: IRRIGATING.
[INFO] Harvest Estimate: Harvest estimate on tile 56 changed to 0 g of CHERRY.
[DEBUG] Harvest Estimate: Required actions on tile 57 were not performed: MOWING.
[INFO] Harvest Estimate: Harvest estimate on tile 57 changed to 1080000 g of GRAPE.
[DEBUG] Harvest Estimate: Required actions on tile 86 were not performed: MOWING.
[INFO] Harvest Estimate: Harvest estimate on tile 86 changed to 1080000 g of GRAPE.

[INFO] Simulation Info: Tick 15 started at tick 8 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 2 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: 1,2.
[IMPORTANT] Farm Action: Machine 2 performs WEEDING on tile 42 for 4 days.
[IMPORTANT] Farm Machine: Machine 2 is finished and returns to the shed at 20.
[IMPORTANT] Farm: Farm 0 finished its actions.
[INFO] Harvest Estimate: Harvest estimate on tile 11 changed to 186008 g of APPLE.
[INFO] Harvest Estimate: Harvest estimate on tile 42 changed to 708588 g of OAT.

[INFO] Simulation Info: Tick 16 started at tick 9 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 2 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: 1,2.
[IMPORTANT] Farm Action: Machine 2 performs WEEDING on tile 42 for 4 days.
[IMPORTANT] Farm Machine: Machine 2 is finished and returns to the shed at 20.
[IMPORTANT] Farm: Farm 0 finished its actions.
[INFO] Harvest Estimate: Harvest estimate on tile 11 changed to 122039 g of APPLE.
[INFO] Harvest Estimate: Harvest estimate on tile 42 changed to 516560 g of OAT.

[INFO] Simulation Info: Tick 17 started at tick 10 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 2 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: 1,2.
[IMPORTANT] Farm Action: Machine 3 performs SOWING on tile 39 for 4 days.
[IMPORTANT] Farm Sowing: Machine 3 has sowed PUMPKIN according to sowing plan 1.
[IMPORTANT] Farm Machine: Machine 3 is finished and returns to the shed at 20.
[IMPORTANT] Farm Action: Machine 2 performs IRRIGATING on tile 56 for 4 days.
[IMPORTANT] Farm Machine: Machine 2 is finished and returns to the shed at 20.
[IMPORTANT] Farm: Farm 0 finished its actions.
[IMPORTANT] Incident: Incident 702 of type CLOUD_CREATION happened and affected tiles 43,50,51,57,59,65,66,73.
[INFO] Harvest Estimate: Harvest estimate on tile 11 changed to 80068 g of APPLE.
[INFO] Harvest Estimate: Harvest estimate on tile 39 changed to 450000 g of PUMPKIN.
[INFO] Harvest Estimate: Harvest estimate on tile 42 changed to 376571 g of OAT.

[INFO] Simulation Info: Tick 18 started at tick 11 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 2 PLANTATION tiles.
[IMPORTANT] Cloud Rain: Cloud 68 on tile 43 rained down 200 L water.
[INFO] Cloud Dissipation: Cloud 68 dissipates on tile 43.
[IMPORTANT] Cloud Rain: Cloud 69 on tile 50 rained down 10000000 L water.
[INFO] Cloud Dissipation: Cloud 69 dissipates on tile 50.
[IMPORTANT] Cloud Rain: Cloud 70 on tile 51 rained down 10000000 L water.
[INFO] Cloud Dissipation: Cloud 70 dissipates on tile 51.
[IMPORTANT] Cloud Rain: Cloud 71 on tile 57 rained down 700 L water.
[INFO] Cloud Movement: Cloud 71 with 9999300 L water moved from tile 57 to tile 72.
[DEBUG] Cloud Movement: On tile 57, the amount of sunlight is 165.
[IMPORTANT] Cloud Rain: Cloud 71 on tile 72 rained down 9999300 L water.
[INFO] Cloud Dissipation: Cloud 71 dissipates on tile 72.
[IMPORTANT] Cloud Rain: Cloud 72 on tile 59 rained down 10000000 L water.
[INFO] Cloud Dissipation: Cloud 72 dissipates on tile 59.
[IMPORTANT] Cloud Rain: Cloud 73 on tile 65 rained down 10000000 L water.
[INFO] Cloud Dissipation: Cloud 73 dissipates on tile 65.
[IMPORTANT] Cloud Rain: Cloud 74 on tile 66 rained down 10000000 L water.
[INFO] Cloud Dissipation: Cloud 74 dissipates on tile 66.
[IMPORTANT] Cloud Rain: Cloud 75 on tile 73 rained down 10000000 L water.
[INFO] Cloud Dissipation: Cloud 75 dissipates on tile 73.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: 2.
[IMPORTANT] Farm Action: Machine 3 performs MOWING on tile 11 for 4 days.
[IMPORTANT] Farm Machine: Machine 3 is finished and returns to the shed at 20.
[IMPORTANT] Farm: Farm 0 finished its actions.
[INFO] Harvest Estimate: Harvest estimate on tile 11 changed to 52531 g of APPLE.
[INFO] Harvest Estimate: Harvest estimate on tile 39 changed to 405000 g of PUMPKIN.
[INFO] Harvest Estimate: Harvest estimate on tile 42 changed to 274518 g of OAT.

[INFO] Simulation Info: Tick 19 started at tick 12 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 1 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: 2.
[IMPORTANT] Farm Action: Machine 3 performs WEEDING on tile 39 for 4 days.
[IMPORTANT] Farm Machine: Machine 3 is finished and returns to the shed at 20.
[IMPORTANT] Farm: Farm 0 finished its actions.
[INFO] Harvest Estimate: Harvest estimate on tile 11 changed to 34464 g of APPLE.
[INFO] Harvest Estimate: Harvest estimate on tile 39 changed to 364500 g of PUMPKIN.
[INFO] Harvest Estimate: Harvest estimate on tile 42 changed to 200123 g of OAT.

[INFO] Simulation Info: Tick 20 started at tick 13 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 2 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: 2.
[IMPORTANT] Farm Action: Machine 2 performs HARVESTING on tile 42 for 4 days.
[IMPORTANT] Farm Harvest: Machine 2 has collected 200123 g of OAT harvest.
[IMPORTANT] Farm Machine: Machine 2 is finished and returns to the shed at 20.
[IMPORTANT] Farm Machine: Machine 2 unloads 200123 g of OAT harvest in the shed.
[IMPORTANT] Farm Action: Machine 1 performs MOWING on tile 57 for 4 days.
[IMPORTANT] Farm Machine: Machine 1 is finished and returns to the shed at 20.
[IMPORTANT] Farm: Farm 0 finished its actions.
[INFO] Harvest Estimate: Harvest estimate on tile 11 changed to 22610 g of APPLE.
[INFO] Harvest Estimate: Harvest estimate on tile 39 changed to 328050 g of PUMPKIN.
[DEBUG] Harvest Estimate: Required actions on tile 86 were not performed: MOWING.
[INFO] Harvest Estimate: Harvest estimate on tile 86 changed to 972000 g of GRAPE.

[INFO] Simulation Info: Tick 21 started at tick 14 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 2 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: 2.
[IMPORTANT] Farm Action: Machine 1 performs CUTTING on tile 57 for 4 days.
[IMPORTANT] Farm Machine: Machine 1 is finished and returns to the shed at 20.
[IMPORTANT] Farm Action: Machine 2 performs IRRIGATING on tile 56 for 4 days.
[IMPORTANT] Farm Machine: Machine 2 is finished and returns to the shed at 20.
[IMPORTANT] Farm Action: Machine 3 performs WEEDING on tile 39 for 4 days.
[IMPORTANT] Farm Machine: Machine 3 is finished and returns to the shed at 20.
[IMPORTANT] Farm: Farm 0 finished its actions.
[INFO] Harvest Estimate: Harvest estimate on tile 11 changed to 14833 g of APPLE.
[INFO] Harvest Estimate: Harvest estimate on tile 39 changed to 295245 g of PUMPKIN.

[INFO] Simulation Info: Tick 22 started at tick 15 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 1 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: 2.
[IMPORTANT] Farm Action: Machine 1 performs CUTTING on tile 86 for 4 days.
[IMPORTANT] Farm Machine: Machine 1 is finished and returns to the shed at 20.
[IMPORTANT] Farm: Farm 0 finished its actions.
[IMPORTANT] Incident: Incident 0 of type DROUGHT happened and affected tiles 42,56.
[IMPORTANT] Incident: Incident 501 of type BROKEN_MACHINE happened and affected tiles 20.
[INFO] Harvest Estimate: Harvest estimate on tile 11 changed to 9730 g of APPLE.
[INFO] Harvest Estimate: Harvest estimate on tile 39 changed to 265720 g of PUMPKIN.

[INFO] Simulation Info: Tick 23 started at tick 16 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 1 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: 2.
[IMPORTANT] Farm Action: Machine 3 performs WEEDING on tile 39 for 4 days.
[IMPORTANT] Farm Machine: Machine 3 is finished and returns to the shed at 20.
[IMPORTANT] Farm: Farm 0 finished its actions.
[INFO] Harvest Estimate: Harvest estimate on tile 11 changed to 6382 g of APPLE.
[INFO] Harvest Estimate: Harvest estimate on tile 39 changed to 239148 g of PUMPKIN.
[IMPORTANT] Simulation Info: Simulation ended at tick 24.
[IMPORTANT] Simulation Info: Simulation statistics are calculated.
[IMPORTANT] Simulation Statistics: Farm 0 collected 2700123 g of harvest.
[IMPORTANT] Simulation Statistics: Total amount of POTATO harvested: 0 g.
[IMPORTANT] Simulation Statistics: Total amount of WHEAT harvested: 0 g.
[IMPORTANT] Simulation Statistics: Total amount of OAT harvested: 200123 g.
[IMPORTANT] Simulation Statistics: Total amount of PUMPKIN harvested: 0 g.
[IMPORTANT] Simulation Statistics: Total amount of APPLE harvested: 1700000 g.
[IMPORTANT] Simulation Statistics: Total amount of GRAPE harvested: 0 g.
[IMPORTANT] Simulation Statistics: Total amount of ALMOND harvested: 800000 g.
[IMPORTANT] Simulation Statistics: Total amount of CHERRY harvested: 0 g.
[IMPORTANT] Simulation Statistics: Total harvest estimate still in fields and plantations: 2297530 g.
"""

private const val MAP = "example/bigMap.json"
private const val FARMS = "example/bigFarm.json"
private const val SCENARIO = "example/bigScenario.json"
private const val LEVEL = "DEBUG"

/**
 * O_o
 */
class FullSimulation2(private val tick: Int) : ExampleSystemTestExtension() {
    override val name = "Full Simulation(2) ${if (tick == 0) "init" else (tick - 1).toString()}"
    override val description = "Full sim test for tick ${if (tick == 0) "init" else (tick - 1).toString()}"

    override val map = MAP
    override val farms = FARMS
    override val scenario = SCENARIO

    override val logLevel = LEVEL
    override val maxTicks = 24
    override val startYearTick = 17

    override suspend fun run() {
        val ticks = LOG.split(Regex("\\n\\s*\\n"))
        val lines = ticks[tick].split(Regex("\\n"))
        skipUntilString(lines.first())
        for (line in lines.drop(1)) {
            assertNextLine(line)
        }
    }
}

/**
 * O_o
 */
class FullSimulation14Clouds : ExampleSystemTestExtension() {
    override val name = "FullSimulation4 Clouds"
    override val description = "FullSimulation4 Clouds"

    override val map = MAP
    override val farms = FARMS
    override val scenario = SCENARIO

    override val logLevel = LEVEL
    override val maxTicks = 24
    override val startYearTick = 17

    override suspend fun run() {
        val lines = """[INFO] Simulation Info: Tick 14 started at tick 7 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 2 PLANTATION tiles.
[INFO] Cloud Dissipation: Cloud 50 dissipates on tile 1.
[INFO] Cloud Dissipation: Cloud 51 dissipates on tile 2.
[INFO] Cloud Dissipation: Cloud 52 dissipates on tile 3.
[INFO] Cloud Dissipation: Cloud 53 dissipates on tile 4.
[INFO] Cloud Dissipation: Cloud 54 dissipates on tile 8.
[INFO] Cloud Dissipation: Cloud 55 dissipates on tile 11.
[INFO] Cloud Dissipation: Cloud 56 dissipates on tile 12.
[INFO] Cloud Dissipation: Cloud 57 dissipates on tile 16.
[INFO] Cloud Dissipation: Cloud 58 dissipates on tile 17.
[INFO] Cloud Dissipation: Cloud 59 dissipates on tile 18.
[INFO] Cloud Dissipation: Cloud 60 dissipates on tile 19.
[INFO] Cloud Dissipation: Cloud 61 dissipates on tile 24.
[INFO] Cloud Movement: Cloud 63 with 4000 L water moved from tile 26 to tile 41.
[INFO] Cloud Movement: Cloud 63 with 4000 L water moved from tile 41 to tile 26.
[INFO] Cloud Movement: Cloud 63 with 4000 L water moved from tile 26 to tile 41.
[INFO] Cloud Movement: Cloud 63 with 4000 L water moved from tile 41 to tile 26.
[INFO] Cloud Movement: Cloud 63 with 4000 L water moved from tile 26 to tile 41.
[INFO] Cloud Movement: Cloud 63 with 4000 L water moved from tile 41 to tile 26.
[INFO] Cloud Movement: Cloud 63 with 4000 L water moved from tile 26 to tile 41.
[INFO] Cloud Movement: Cloud 63 with 4000 L water moved from tile 41 to tile 26.
[INFO] Cloud Movement: Cloud 63 with 4000 L water moved from tile 26 to tile 41.
[INFO] Cloud Movement: Cloud 63 with 4000 L water moved from tile 41 to tile 26.
[INFO] Cloud Dissipation: Cloud 63 dissipates on tile 26.
[INFO] Cloud Dissipation: Cloud 65 dissipates on tile 33.
[INFO] Cloud Dissipation: Cloud 66 dissipates on tile 40.
        """.trimIndent().lines()

        skipUntilString(lines.first())
        for (line in lines.drop(1)) {
            assertNextLine(line)
        }
    }
}

/**
 * O_o
 */
class FullSimulation14Farm0 : ExampleSystemTestExtension() {
    override val name = "FullSimulation14 Farm0"
    override val description = "FullSimulation14 Farm0"

    override val map = MAP
    override val farms = FARMS
    override val scenario = SCENARIO

    override val logLevel = LEVEL
    override val maxTicks = 24
    override val startYearTick = 17

    override suspend fun run() {
        skipUntilString("[INFO] Simulation Info: Tick 14 started at tick 7 within the year.")
        val lines = """[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: 1,2,8.
[IMPORTANT] Farm Action: Machine 1 performs SOWING on tile 43 for 4 days.
[IMPORTANT] Farm Sowing: Machine 1 has sowed POTATO according to sowing plan 8.
[IMPORTANT] Farm Machine: Machine 1 is finished and returns to the shed at 20.
[IMPORTANT] Farm Action: Machine 2 performs IRRIGATING on tile 56 for 4 days.
[IMPORTANT] Farm Machine: Machine 2 is finished and returns to the shed at 20.
[IMPORTANT] Farm: Farm 0 finished its actions.
        """.trimIndent().lines()

        skipUntilString(lines.first())
        for (line in lines.drop(1)) {
            assertNextLine(line)
        }
    }
}

/**
 * O_o
 */
class FullSimulation14End : ExampleSystemTestExtension() {
    override val name = "FullSimulation14 End"
    override val description = "FullSimulation14 End"

    override val map = MAP
    override val farms = FARMS
    override val scenario = SCENARIO

    override val logLevel = LEVEL
    override val maxTicks = 24
    override val startYearTick = 17

    override suspend fun run() {
        skipUntilString("[INFO] Simulation Info: Tick 14 started at tick 7 within the year.")
        val lines = """[INFO] Harvest Estimate: Harvest estimate on tile 11 changed to 255157 g of APPLE.
[DEBUG] Harvest Estimate: Required actions on tile 42 were not performed: WEEDING.
[INFO] Harvest Estimate: Harvest estimate on tile 42 changed to 787320 g of OAT.
[DEBUG] Harvest Estimate: Required actions on tile 43 were not performed: IRRIGATING.
[INFO] Harvest Estimate: Harvest estimate on tile 43 changed to 0 g of POTATO.
[DEBUG] Harvest Estimate: Required actions on tile 57 were not performed: MOWING.
[INFO] Harvest Estimate: Harvest estimate on tile 57 changed to 1080000 g of GRAPE.
[DEBUG] Harvest Estimate: Required actions on tile 86 were not performed: MOWING.
[INFO] Harvest Estimate: Harvest estimate on tile 86 changed to 1080000 g of GRAPE.
        """.trimIndent().lines()

        skipUntilString(lines.first())
        for (line in lines.drop(1)) {
            assertNextLine(line)
        }
    }
}
