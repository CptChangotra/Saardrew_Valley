package de.unisaarland.cs.se.selab.systemtest.selab25.basictests

import de.unisaarland.cs.se.selab.systemtest.selab25.utils.ExampleSystemTestExtension

private const val LOG = """[INFO] Initialization Info: fullSimMap.json successfully parsed and validated.
[INFO] Initialization Info: fullSimFarms.json successfully parsed and validated.
[INFO] Initialization Info: fullSimScenario.json successfully parsed and validated.
[INFO] Simulation Info: Simulation started at tick 20 within the year.

[INFO] Simulation Info: Tick 0 started at tick 20 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: 53.
[IMPORTANT] Farm Action: Machine 0 performs HARVESTING on tile 40 for 4 days.
[IMPORTANT] Farm Harvest: Machine 0 has collected 850000 g of APPLE harvest.
[IMPORTANT] Farm Machine: Machine 0 is finished and returns to the shed at 64.
[IMPORTANT] Farm Machine: Machine 0 unloads 850000 g of APPLE harvest in the shed.
[IMPORTANT] Farm: Farm 0 finished its actions.
[DEBUG] Harvest Estimate: Required actions on tile 42 were not performed: HARVESTING.
[INFO] Harvest Estimate: Harvest estimate on tile 42 changed to 0 g of GRAPE.
[DEBUG] Harvest Estimate: Required actions on tile 43 were not performed: HARVESTING.
[INFO] Harvest Estimate: Harvest estimate on tile 43 changed to 0 g of ALMOND.

[INFO] Simulation Info: Tick 1 started at tick 21 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: 53.
[IMPORTANT] Farm Action: Machine 0 performs CUTTING on tile 40 for 4 days.
[IMPORTANT] Farm Machine: Machine 0 is finished and returns to the shed at 64.
[IMPORTANT] Farm Action: Machine 1 performs CUTTING on tile 41 for 4 days.
[IMPORTANT] Farm Machine: Machine 1 is finished and returns to the shed at 64.
[IMPORTANT] Farm: Farm 0 finished its actions.
[INFO] Harvest Estimate: Harvest estimate on tile 40 changed to 1530000 g of APPLE.

[INFO] Simulation Info: Tick 2 started at tick 22 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 1 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: 12,53.
[IMPORTANT] Farm: Farm 0 finished its actions.
[INFO] Harvest Estimate: Harvest estimate on tile 40 changed to 1377000 g of APPLE.
[DEBUG] Harvest Estimate: Required actions on tile 43 were not performed: IRRIGATING.
[INFO] Harvest Estimate: Harvest estimate on tile 43 changed to 799950 g of ALMOND.

[INFO] Simulation Info: Tick 3 started at tick 23 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 2 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: 12,53,55.
[IMPORTANT] Farm Action: Machine 0 performs IRRIGATING on tile 42 for 4 days.
[IMPORTANT] Farm Machine: Machine 0 is finished and returns to the shed at 64.
[IMPORTANT] Farm: Farm 0 finished its actions.
[IMPORTANT] Incident: Incident 2 of type BEE_HAPPY happened and affected tiles .
[IMPORTANT] Incident: Incident 9 of type CLOUD_CREATION happened and affected tiles 0,1,2,3,8,9,10,11,15,16,17,18,19,23,25,26,27,30,31,32,33,34,35,38,39,40,41,42,43,45,46,47,48,49,50,53,54,55,56,57,60,61,62,63,64,68,69,70,71,75,76,77,78,83,84,85,91,92,99.
[INFO] Harvest Estimate: Harvest estimate on tile 40 changed to 1239300 g of APPLE.
[DEBUG] Harvest Estimate: Required actions on tile 43 were not performed: IRRIGATING.
[INFO] Harvest Estimate: Harvest estimate on tile 43 changed to 799850 g of ALMOND.

[INFO] Simulation Info: Tick 4 started at tick 24 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 2 PLANTATION tiles.
[INFO] Cloud Movement: Cloud 10 with 4000 L water moved from tile 17 to tile 25.
[IMPORTANT] Cloud Union: Clouds 14 and 10 united to cloud 59 with 8000 L water and duration 4 on tile 25.
[INFO] Cloud Movement: Cloud 15 with 4000 L water moved from tile 26 to tile 33.
[IMPORTANT] Cloud Union: Clouds 20 and 15 united to cloud 60 with 8000 L water and duration 4 on tile 33.
[INFO] Cloud Movement: Cloud 23 with 4000 L water moved from tile 38 to tile 31.
[IMPORTANT] Cloud Union: Clouds 18 and 23 united to cloud 61 with 8000 L water and duration 3 on tile 31.
[INFO] Cloud Movement: Cloud 24 with 4000 L water moved from tile 39 to tile 24.
[INFO] Cloud Dissipation: Cloud 24 got stuck on tile 24.
[INFO] Cloud Movement: Cloud 25 with 4000 L water moved from tile 40 to tile 48.
[DEBUG] Cloud Movement: On tile 40, the amount of sunlight is 81.
[IMPORTANT] Cloud Union: Clouds 32 and 25 united to cloud 62 with 8000 L water and duration 4 on tile 48.
[INFO] Cloud Movement: Cloud 26 with 4000 L water moved from tile 41 to tile 33.
[DEBUG] Cloud Movement: On tile 41, the amount of sunlight is 81.
[IMPORTANT] Cloud Union: Clouds 60 and 26 united to cloud 63 with 12000 L water and duration 4 on tile 33.
[INFO] Cloud Movement: Cloud 36 with 4000 L water moved from tile 54 to tile 53.
[DEBUG] Cloud Movement: On tile 54, the amount of sunlight is 81.
[IMPORTANT] Cloud Union: Clouds 35 and 36 united to cloud 64 with 8000 L water and duration 3 on tile 53.
[INFO] Cloud Movement: Cloud 37 with 4000 L water moved from tile 55 to tile 62.
[IMPORTANT] Cloud Union: Clouds 42 and 37 united to cloud 65 with 8000 L water and duration 4 on tile 62.
[INFO] Cloud Movement: Cloud 38 with 4000 L water moved from tile 56 to tile 48.
[IMPORTANT] Cloud Union: Clouds 62 and 38 united to cloud 66 with 12000 L water and duration 4 on tile 48.
[INFO] Cloud Movement: Cloud 46 with 4000 L water moved from tile 69 to tile 84.
[DEBUG] Cloud Movement: On tile 69, the amount of sunlight is 81.
[IMPORTANT] Cloud Union: Clouds 54 and 46 united to cloud 67 with 8000 L water and duration 4 on tile 84.
[IMPORTANT] Cloud Rain: Cloud 59 on tile 25 rained down 8000 L water.
[INFO] Cloud Dissipation: Cloud 59 dissipates on tile 25.
[IMPORTANT] Cloud Rain: Cloud 61 on tile 31 rained down 8000 L water.
[INFO] Cloud Dissipation: Cloud 61 dissipates on tile 31.
[IMPORTANT] Cloud Rain: Cloud 63 on tile 33 rained down 12000 L water.
[INFO] Cloud Dissipation: Cloud 63 dissipates on tile 33.
[IMPORTANT] Cloud Rain: Cloud 64 on tile 53 rained down 8000 L water.
[INFO] Cloud Dissipation: Cloud 64 dissipates on tile 53.
[IMPORTANT] Cloud Rain: Cloud 65 on tile 62 rained down 8000 L water.
[INFO] Cloud Dissipation: Cloud 65 dissipates on tile 62.
[IMPORTANT] Cloud Rain: Cloud 66 on tile 48 rained down 12000 L water.
[INFO] Cloud Dissipation: Cloud 66 dissipates on tile 48.
[IMPORTANT] Cloud Rain: Cloud 67 on tile 84 rained down 350 L water.
[DEBUG] Cloud Position: Cloud 27 is on tile 42, where the amount of sunlight is 34.
[DEBUG] Cloud Position: Cloud 28 is on tile 43, where the amount of sunlight is 34.
[DEBUG] Cloud Position: Cloud 67 is on tile 84, where the amount of sunlight is 34.
[DEBUG] Cloud Position: Cloud 58 is on tile 99, where the amount of sunlight is 34.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: 12,53,55.
[IMPORTANT] Farm Action: Machine 1 performs IRRIGATING on tile 41 for 4 days.
[IMPORTANT] Farm Machine: Machine 1 is finished and returns to the shed at 64.
[IMPORTANT] Farm: Farm 0 finished its actions.
[IMPORTANT] Incident: Incident 19 of type CITY_EXPANSION happened and affected tiles 17.
[INFO] Harvest Estimate: Harvest estimate on tile 40 changed to 1115370 g of APPLE.
[DEBUG] Harvest Estimate: Required actions on tile 43 were not performed: IRRIGATING.
[INFO] Harvest Estimate: Harvest estimate on tile 43 changed to 799700 g of ALMOND.

[INFO] Simulation Info: Tick 5 started at tick 1 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 2 PLANTATION tiles.
[IMPORTANT] Cloud Rain: Cloud 67 on tile 84 rained down 70 L water.
[DEBUG] Cloud Position: Cloud 27 is on tile 42, where the amount of sunlight is 48.
[DEBUG] Cloud Position: Cloud 28 is on tile 43, where the amount of sunlight is 48.
[DEBUG] Cloud Position: Cloud 67 is on tile 84, where the amount of sunlight is 48.
[DEBUG] Cloud Position: Cloud 58 is on tile 99, where the amount of sunlight is 48.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: 12,53,55.
[IMPORTANT] Farm Action: Machine 0 performs IRRIGATING on tile 40 for 4 days.
[IMPORTANT] Farm Machine: Machine 0 is finished and returns to the shed at 64.
[IMPORTANT] Farm: Farm 0 finished its actions.
[IMPORTANT] Incident: Incident 17 of type BROKEN_MACHINE happened and affected tiles 64.
[INFO] Harvest Estimate: Harvest estimate on tile 40 changed to 1003833 g of APPLE.
[DEBUG] Harvest Estimate: Required actions on tile 43 were not performed: IRRIGATING.
[INFO] Harvest Estimate: Harvest estimate on tile 43 changed to 0 g of ALMOND.

[INFO] Simulation Info: Tick 6 started at tick 2 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 1 PLANTATION tiles.
[IMPORTANT] Cloud Rain: Cloud 67 on tile 84 rained down 70 L water.
[DEBUG] Cloud Position: Cloud 27 is on tile 42, where the amount of sunlight is 48.
[DEBUG] Cloud Position: Cloud 28 is on tile 43, where the amount of sunlight is 48.
[DEBUG] Cloud Position: Cloud 67 is on tile 84, where the amount of sunlight is 48.
[DEBUG] Cloud Position: Cloud 58 is on tile 99, where the amount of sunlight is 48.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: 12,53,55.
[IMPORTANT] Farm: Farm 0 finished its actions.
[INFO] Harvest Estimate: Harvest estimate on tile 40 changed to 903449 g of APPLE.

[INFO] Simulation Info: Tick 7 started at tick 3 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 2 PLANTATION tiles.
[INFO] Cloud Dissipation: Cloud 0 dissipates on tile 0.
[INFO] Cloud Dissipation: Cloud 1 dissipates on tile 1.
[INFO] Cloud Dissipation: Cloud 2 dissipates on tile 2.
[INFO] Cloud Dissipation: Cloud 3 dissipates on tile 3.
[INFO] Cloud Dissipation: Cloud 4 dissipates on tile 8.
[INFO] Cloud Dissipation: Cloud 5 dissipates on tile 9.
[INFO] Cloud Dissipation: Cloud 6 dissipates on tile 10.
[INFO] Cloud Dissipation: Cloud 7 dissipates on tile 11.
[INFO] Cloud Dissipation: Cloud 8 dissipates on tile 15.
[INFO] Cloud Dissipation: Cloud 9 dissipates on tile 16.
[INFO] Cloud Dissipation: Cloud 11 dissipates on tile 18.
[INFO] Cloud Dissipation: Cloud 12 dissipates on tile 19.
[INFO] Cloud Dissipation: Cloud 13 dissipates on tile 23.
[INFO] Cloud Dissipation: Cloud 16 dissipates on tile 27.
[INFO] Cloud Dissipation: Cloud 17 dissipates on tile 30.
[INFO] Cloud Dissipation: Cloud 19 dissipates on tile 32.
[INFO] Cloud Dissipation: Cloud 21 dissipates on tile 34.
[INFO] Cloud Dissipation: Cloud 22 dissipates on tile 35.
[INFO] Cloud Dissipation: Cloud 27 dissipates on tile 42.
[INFO] Cloud Dissipation: Cloud 28 dissipates on tile 43.
[INFO] Cloud Dissipation: Cloud 29 dissipates on tile 45.
[INFO] Cloud Dissipation: Cloud 30 dissipates on tile 46.
[INFO] Cloud Dissipation: Cloud 31 dissipates on tile 47.
[INFO] Cloud Dissipation: Cloud 33 dissipates on tile 49.
[INFO] Cloud Dissipation: Cloud 34 dissipates on tile 50.
[INFO] Cloud Dissipation: Cloud 39 dissipates on tile 57.
[INFO] Cloud Dissipation: Cloud 40 dissipates on tile 60.
[INFO] Cloud Dissipation: Cloud 41 dissipates on tile 61.
[INFO] Cloud Dissipation: Cloud 43 dissipates on tile 63.
[INFO] Cloud Dissipation: Cloud 44 dissipates on tile 64.
[INFO] Cloud Dissipation: Cloud 45 dissipates on tile 68.
[INFO] Cloud Dissipation: Cloud 47 dissipates on tile 70.
[INFO] Cloud Dissipation: Cloud 48 dissipates on tile 71.
[INFO] Cloud Dissipation: Cloud 49 dissipates on tile 75.
[INFO] Cloud Dissipation: Cloud 50 dissipates on tile 76.
[INFO] Cloud Dissipation: Cloud 51 dissipates on tile 77.
[INFO] Cloud Dissipation: Cloud 52 dissipates on tile 78.
[INFO] Cloud Dissipation: Cloud 53 dissipates on tile 83.
[INFO] Cloud Dissipation: Cloud 55 dissipates on tile 85.
[INFO] Cloud Dissipation: Cloud 56 dissipates on tile 91.
[INFO] Cloud Dissipation: Cloud 57 dissipates on tile 92.
[INFO] Cloud Dissipation: Cloud 58 dissipates on tile 99.
[IMPORTANT] Cloud Rain: Cloud 67 on tile 84 rained down 70 L water.
[INFO] Cloud Dissipation: Cloud 67 dissipates on tile 84.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: 12,53,55,99.
[IMPORTANT] Farm: Farm 0 finished its actions.
[IMPORTANT] Incident: Incident 1 of type ANIMAL_ATTACK happened and affected tiles 40,41,54.
[INFO] Harvest Estimate: Harvest estimate on tile 40 changed to 658613 g of APPLE.
[INFO] Harvest Estimate: Harvest estimate on tile 41 changed to 1080000 g of CHERRY.

[INFO] Simulation Info: Tick 8 started at tick 4 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 2 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: 12,53,55,99.
[IMPORTANT] Farm: Farm 0 finished its actions.
[INFO] Harvest Estimate: Harvest estimate on tile 40 changed to 533475 g of APPLE.
[DEBUG] Harvest Estimate: Required actions on tile 42 were not performed: IRRIGATING.
[INFO] Harvest Estimate: Harvest estimate on tile 42 changed to 1199950 g of GRAPE.

[INFO] Simulation Info: Tick 9 started at tick 5 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 3 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: 12,53,55,99.
[IMPORTANT] Farm Action: Machine 1 performs IRRIGATING on tile 41 for 4 days.
[IMPORTANT] Farm Machine: Machine 1 is finished and returns to the shed at 64.
[IMPORTANT] Farm: Farm 0 finished its actions.
[IMPORTANT] Incident: Incident 3 of type DROUGHT happened and affected tiles 41,42,43.
[INFO] Harvest Estimate: Harvest estimate on tile 40 changed to 388902 g of APPLE.
[INFO] Harvest Estimate: Harvest estimate on tile 41 changed to 0 g of CHERRY.
[INFO] Harvest Estimate: Harvest estimate on tile 42 changed to 0 g of GRAPE.

[INFO] Simulation Info: Tick 10 started at tick 6 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: 12,53,55,99.
[IMPORTANT] Farm Action: Machine 1 performs SOWING on tile 99 for 4 days.
[IMPORTANT] Farm Sowing: Machine 1 has sowed OAT according to sowing plan 12.
[IMPORTANT] Farm Machine: Machine 1 is finished and returns to the shed at 64.
[IMPORTANT] Farm: Farm 0 finished its actions.
[INFO] Harvest Estimate: Harvest estimate on tile 40 changed to 283508 g of APPLE.
[DEBUG] Harvest Estimate: Required actions on tile 99 were not performed: IRRIGATING.
[INFO] Harvest Estimate: Harvest estimate on tile 99 changed to 1079900 g of OAT.

[INFO] Simulation Info: Tick 11 started at tick 7 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 1 FIELD and 1 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: 53,55,99.
[IMPORTANT] Farm Action: Machine 2 performs SOWING on tile 84 for 4 days.
[IMPORTANT] Farm Sowing: Machine 2 has sowed POTATO according to sowing plan 53.
[IMPORTANT] Farm Machine: Machine 2 is finished and returns to the shed at 64.
[IMPORTANT] Farm Action: Machine 1 performs IRRIGATING on tile 99 for 4 days.
[IMPORTANT] Farm Machine: Machine 1 is finished and returns to the shed at 64.
[IMPORTANT] Farm: Farm 0 finished its actions.
[DEBUG] Harvest Estimate: Required actions on tile 40 were not performed: IRRIGATING.
[INFO] Harvest Estimate: Harvest estimate on tile 40 changed to 0 g of APPLE.
[DEBUG] Harvest Estimate: Required actions on tile 99 were not performed: WEEDING.
[INFO] Harvest Estimate: Harvest estimate on tile 99 changed to 787247 g of OAT.

[INFO] Simulation Info: Tick 12 started at tick 8 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 1 FIELD and 1 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: 6,55,99.
[IMPORTANT] Farm Action: Machine 0 performs IRRIGATING on tile 40 for 4 days.
[IMPORTANT] Farm Machine: Machine 0 is finished and returns to the shed at 64.
[IMPORTANT] Farm Action: Machine 1 performs WEEDING on tile 99 for 4 days.
[IMPORTANT] Farm Machine: Machine 1 is finished and returns to the shed at 64.
[IMPORTANT] Farm Action: Machine 2 performs IRRIGATING on tile 84 for 4 days.
[IMPORTANT] Farm Machine: Machine 2 is finished and returns to the shed at 64.
[IMPORTANT] Farm: Farm 0 finished its actions.
[INFO] Harvest Estimate: Harvest estimate on tile 99 changed to 637669 g of OAT.

[INFO] Simulation Info: Tick 13 started at tick 9 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: 6,55,99.
[IMPORTANT] Farm Action: Machine 1 performs WEEDING on tile 99 for 4 days.
[IMPORTANT] Farm Machine: Machine 1 is finished and returns to the shed at 64.
[IMPORTANT] Farm Action: Machine 2 performs WEEDING on tile 84 for 4 days.
[IMPORTANT] Farm Machine: Machine 2 is finished and returns to the shed at 64.
[IMPORTANT] Farm: Farm 0 finished its actions.
[INFO] Harvest Estimate: Harvest estimate on tile 84 changed to 900000 g of POTATO.
[INFO] Harvest Estimate: Harvest estimate on tile 99 changed to 464859 g of OAT.

[INFO] Simulation Info: Tick 14 started at tick 10 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: 6,55,99.
[IMPORTANT] Farm Action: Machine 0 performs SOWING on tile 54 for 4 days.
[IMPORTANT] Farm Sowing: Machine 0 has sowed PUMPKIN according to sowing plan 55.
[IMPORTANT] Farm Machine: Machine 0 is finished and returns to the shed at 64.
[IMPORTANT] Farm: Farm 0 finished its actions.
[DEBUG] Harvest Estimate: Required actions on tile 54 were not performed: IRRIGATING.
[INFO] Harvest Estimate: Harvest estimate on tile 54 changed to 0 g of PUMPKIN.
[INFO] Harvest Estimate: Harvest estimate on tile 84 changed to 810000 g of POTATO.
[INFO] Harvest Estimate: Harvest estimate on tile 99 changed to 338881 g of OAT.

[INFO] Simulation Info: Tick 15 started at tick 11 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: 6,99.
[IMPORTANT] Farm Action: Machine 2 performs WEEDING on tile 84 for 4 days.
[IMPORTANT] Farm Machine: Machine 2 is finished and returns to the shed at 64.
[IMPORTANT] Farm: Farm 0 finished its actions.
[INFO] Harvest Estimate: Harvest estimate on tile 84 changed to 729000 g of POTATO.
[INFO] Harvest Estimate: Harvest estimate on tile 99 changed to 247042 g of OAT.

[INFO] Simulation Info: Tick 16 started at tick 12 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 1 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: 6,99.
[IMPORTANT] Farm Action: Machine 2 performs IRRIGATING on tile 84 for 4 days.
[IMPORTANT] Farm Machine: Machine 2 is finished and returns to the shed at 64.
[IMPORTANT] Farm: Farm 0 finished its actions.
[INFO] Harvest Estimate: Harvest estimate on tile 84 changed to 656100 g of POTATO.
[INFO] Harvest Estimate: Harvest estimate on tile 99 changed to 180092 g of OAT.

[INFO] Simulation Info: Tick 17 started at tick 13 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 1 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: 6,99.
[IMPORTANT] Farm Action: Machine 1 performs HARVESTING on tile 99 for 4 days.
[IMPORTANT] Farm Harvest: Machine 1 has collected 180092 g of OAT harvest.
[IMPORTANT] Farm Machine: Machine 1 is finished and returns to the shed at 64.
[IMPORTANT] Farm Machine: Machine 1 unloads 180092 g of OAT harvest in the shed.
[IMPORTANT] Farm Action: Machine 2 performs WEEDING on tile 84 for 4 days.
[IMPORTANT] Farm Machine: Machine 2 is finished and returns to the shed at 64.
[IMPORTANT] Farm: Farm 0 finished its actions.
[INFO] Harvest Estimate: Harvest estimate on tile 84 changed to 590490 g of POTATO.

[INFO] Simulation Info: Tick 18 started at tick 14 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 1 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: 6,99.
[IMPORTANT] Farm Action: Machine 0 performs IRRIGATING on tile 40 for 4 days.
[IMPORTANT] Farm Machine: Machine 0 is finished and returns to the shed at 64.
[IMPORTANT] Farm: Farm 0 finished its actions.
[INFO] Harvest Estimate: Harvest estimate on tile 84 changed to 531441 g of POTATO.

[INFO] Simulation Info: Tick 19 started at tick 15 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: 6,99.
[IMPORTANT] Farm Action: Machine 2 performs WEEDING on tile 84 for 4 days.
[IMPORTANT] Farm Machine: Machine 2 is finished and returns to the shed at 64.
[IMPORTANT] Farm: Farm 0 finished its actions.

[INFO] Simulation Info: Tick 20 started at tick 16 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 1 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: 6,99.
[IMPORTANT] Farm Action: Machine 2 performs IRRIGATING on tile 84 for 4 days.
[IMPORTANT] Farm Machine: Machine 2 is finished and returns to the shed at 64.
[IMPORTANT] Farm: Farm 0 finished its actions.

[INFO] Simulation Info: Tick 21 started at tick 17 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: 6,99.
[IMPORTANT] Farm Action: Machine 2 performs HARVESTING on tile 84 for 4 days.
[IMPORTANT] Farm Harvest: Machine 2 has collected 531441 g of POTATO harvest.
[IMPORTANT] Farm Machine: Machine 2 is finished and returns to the shed at 64.
[IMPORTANT] Farm Machine: Machine 2 unloads 531441 g of POTATO harvest in the shed.
[IMPORTANT] Farm: Farm 0 finished its actions.

[INFO] Simulation Info: Tick 22 started at tick 18 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: 6,99.
[IMPORTANT] Farm: Farm 0 finished its actions.

[INFO] Simulation Info: Tick 23 started at tick 19 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: 6,99.
[IMPORTANT] Farm Action: Machine 2 performs SOWING on tile 69 for 4 days.
[IMPORTANT] Farm Sowing: Machine 2 has sowed WHEAT according to sowing plan 99.
[IMPORTANT] Farm Machine: Machine 2 is finished and returns to the shed at 64.
[IMPORTANT] Farm: Farm 0 finished its actions.
[DEBUG] Harvest Estimate: Required actions on tile 69 were not performed: IRRIGATING.
[INFO] Harvest Estimate: Harvest estimate on tile 69 changed to 0 g of WHEAT.
[IMPORTANT] Simulation Info: Simulation ended at tick 24.
[IMPORTANT] Simulation Info: Simulation statistics are calculated.
[IMPORTANT] Simulation Statistics: Farm 0 collected 1561533 g of harvest.
[IMPORTANT] Simulation Statistics: Total amount of POTATO harvested: 531441 g.
[IMPORTANT] Simulation Statistics: Total amount of WHEAT harvested: 0 g.
[IMPORTANT] Simulation Statistics: Total amount of OAT harvested: 180092 g.
[IMPORTANT] Simulation Statistics: Total amount of PUMPKIN harvested: 0 g.
[IMPORTANT] Simulation Statistics: Total amount of APPLE harvested: 850000 g.
[IMPORTANT] Simulation Statistics: Total amount of GRAPE harvested: 0 g.
[IMPORTANT] Simulation Statistics: Total amount of ALMOND harvested: 0 g.
[IMPORTANT] Simulation Statistics: Total amount of CHERRY harvested: 0 g.
[IMPORTANT] Simulation Statistics: Total harvest estimate still in fields and plantations: 0 g."""

private const val MAP = "example/fullSimMap.json"
private const val FARMS = "example/fullSimFarms.json"
private const val SCENARIO = "example/fullSimScenario.json"
private const val LEVEL = "DEBUG"

/**
 * O_o
 */
class FullSimulation(private val tick: Int) : ExampleSystemTestExtension() {
    override val name = "Full Simulation(1) ${if (tick == 0) "init" else (tick - 1).toString()}"
    override val description = "Big Sim test for tick ${if (tick == 0) "init" else (tick - 1).toString()}"

    override val map = MAP
    override val farms = FARMS
    override val scenario = SCENARIO

    override val logLevel = LEVEL
    override val maxTicks = 24
    override val startYearTick = 20

    override suspend fun run() {
        val ticks = LOG.split(Regex("\\n\\s*\\n"))
        val lines = ticks[tick].split(Regex("\\n"))
        skipUntilString(lines.first())
        for (line in lines.drop(1)) {
            assertNextLine(line)
        }
    }
}
