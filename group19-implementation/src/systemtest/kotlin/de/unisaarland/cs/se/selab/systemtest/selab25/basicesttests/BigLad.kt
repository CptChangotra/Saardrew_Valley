package de.unisaarland.cs.se.selab.systemtest.selab25.basicesttests

import de.unisaarland.cs.se.selab.systemtest.selab25.utils.ExampleSystemTestExtension
import de.unisaarland.cs.se.selab.utils.YearTick

private const val LOG = """[INFO] Initialization Info: map.json successfully parsed and validated.
[INFO] Initialization Info: farms.json successfully parsed and validated.
[INFO] Initialization Info: scenario.json successfully parsed and validated.
[INFO] Simulation Info: Simulation started at tick 1 within the year.

[INFO] Simulation Info: Tick 0 started at tick 1 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Cloud Rain: Cloud 0 on tile 102 rained down 70 L water.
[IMPORTANT] Cloud Rain: Cloud 1 on tile 1 rained down 5050 L water.
[INFO] Cloud Dissipation: Cloud 1 dissipates on tile 1.
[IMPORTANT] Cloud Rain: Cloud 2 on tile 201 rained down 70 L water.
[INFO] Cloud Movement: Cloud 2 with 4980 L water moved from tile 201 to tile 104.
[DEBUG] Cloud Movement: On tile 201, the amount of sunlight is 95.
[DEBUG] Cloud Position: Cloud 0 is on tile 102, where the amount of sunlight is 48.
[DEBUG] Cloud Position: Cloud 2 is on tile 104, where the amount of sunlight is 48.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 0 finished its actions.
[IMPORTANT] Farm: Farm 1 starts its actions.
[DEBUG] Farm: Farm 1 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 1 finished its actions.

[INFO] Simulation Info: Tick 1 started at tick 2 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Cloud Rain: Cloud 0 on tile 102 rained down 70 L water.
[DEBUG] Cloud Position: Cloud 0 is on tile 102, where the amount of sunlight is 48.
[DEBUG] Cloud Position: Cloud 2 is on tile 104, where the amount of sunlight is 48.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 0 finished its actions.
[IMPORTANT] Farm: Farm 1 starts its actions.
[DEBUG] Farm: Farm 1 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 1 finished its actions.

[INFO] Simulation Info: Tick 2 started at tick 3 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Cloud Rain: Cloud 0 on tile 102 rained down 70 L water.
[DEBUG] Cloud Position: Cloud 0 is on tile 102, where the amount of sunlight is 62.
[DEBUG] Cloud Position: Cloud 2 is on tile 104, where the amount of sunlight is 62.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 0 finished its actions.
[IMPORTANT] Farm: Farm 1 starts its actions.
[DEBUG] Farm: Farm 1 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 1 finished its actions.

[INFO] Simulation Info: Tick 3 started at tick 4 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Cloud Rain: Cloud 0 on tile 102 rained down 70 L water.
[INFO] Cloud Dissipation: Cloud 2 dissipates on tile 104.
[DEBUG] Cloud Position: Cloud 0 is on tile 102, where the amount of sunlight is 62.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 0 finished its actions.
[IMPORTANT] Farm: Farm 1 starts its actions.
[DEBUG] Farm: Farm 1 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 1 finished its actions.
[DEBUG] Harvest Estimate: Required actions on tile 104 were not performed: CUTTING.
[INFO] Harvest Estimate: Harvest estimate on tile 104 changed to 600000 g of CHERRY.

[INFO] Simulation Info: Tick 4 started at tick 5 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Cloud Rain: Cloud 0 on tile 102 rained down 70 L water.
[DEBUG] Cloud Position: Cloud 0 is on tile 102, where the amount of sunlight is 76.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 0 finished its actions.
[IMPORTANT] Farm: Farm 1 starts its actions.
[DEBUG] Farm: Farm 1 has the following active sowing plans it intends to pursue in this tick: 3.
[IMPORTANT] Farm: Farm 1 finished its actions.

[INFO] Simulation Info: Tick 5 started at tick 6 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Cloud Rain: Cloud 0 on tile 102 rained down 70 L water.
[DEBUG] Cloud Position: Cloud 0 is on tile 102, where the amount of sunlight is 76.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 0 finished its actions.
[IMPORTANT] Farm: Farm 1 starts its actions.
[DEBUG] Farm: Farm 1 has the following active sowing plans it intends to pursue in this tick: 3.
[IMPORTANT] Farm: Farm 1 finished its actions.
[IMPORTANT] Incident: Incident 500 of type BROKEN_MACHINE happened and affected tiles 1.
[IMPORTANT] Incident: Incident 700 of type CLOUD_CREATION happened and affected tiles 0,1,11,12,13,100,101,102,103,104,200,201.
[IMPORTANT] Cloud Union: Clouds 0 and 10 united to cloud 11 with 10580 L water and duration 2 on tile 102.

[INFO] Simulation Info: Tick 6 started at tick 7 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 1 PLANTATION tiles.
[INFO] Cloud Movement: Cloud 4 with 1000 L water moved from tile 1 to tile 200.
[IMPORTANT] Cloud Union: Clouds 14 and 4 united to cloud 16 with 2000 L water and duration 2 on tile 200.
[INFO] Cloud Movement: Cloud 8 with 1000 L water moved from tile 100 to tile 101.
[DEBUG] Cloud Movement: On tile 100, the amount of sunlight is 137.
[IMPORTANT] Cloud Union: Clouds 9 and 8 united to cloud 17 with 2000 L water and duration 2 on tile 101.
[IMPORTANT] Cloud Rain: Cloud 11 on tile 102 rained down 70 L water.
[INFO] Cloud Movement: Cloud 12 with 1000 L water moved from tile 103 to tile 100.
[DEBUG] Cloud Movement: On tile 103, the amount of sunlight is 137.
[INFO] Cloud Movement: Cloud 12 with 1000 L water moved from tile 100 to tile 101.
[DEBUG] Cloud Movement: On tile 100, the amount of sunlight is 134.
[IMPORTANT] Cloud Union: Clouds 17 and 12 united to cloud 18 with 3000 L water and duration 2 on tile 101.
[INFO] Cloud Movement: Cloud 15 with 1000 L water moved from tile 201 to tile 104.
[DEBUG] Cloud Movement: On tile 201, the amount of sunlight is 137.
[IMPORTANT] Cloud Union: Clouds 13 and 15 united to cloud 19 with 2000 L water and duration 1 on tile 104.
[INFO] Cloud Movement: Cloud 16 with 2000 L water moved from tile 200 to tile 103.
[DEBUG] Cloud Movement: On tile 200, the amount of sunlight is 137.
[INFO] Cloud Movement: Cloud 16 with 2000 L water moved from tile 103 to tile 100.
[DEBUG] Cloud Movement: On tile 103, the amount of sunlight is 134.
[INFO] Cloud Movement: Cloud 16 with 2000 L water moved from tile 100 to tile 101.
[DEBUG] Cloud Movement: On tile 100, the amount of sunlight is 131.
[IMPORTANT] Cloud Union: Clouds 18 and 16 united to cloud 20 with 5000 L water and duration 2 on tile 101.
[INFO] Cloud Dissipation: Cloud 19 dissipates on tile 104.
[IMPORTANT] Cloud Rain: Cloud 20 on tile 101 rained down 490 L water.
[INFO] Cloud Movement: Cloud 20 with 4510 L water moved from tile 101 to tile 10.
[DEBUG] Cloud Movement: On tile 101, the amount of sunlight is 137.
[INFO] Cloud Dissipation: Cloud 20 got stuck on tile 10.
[DEBUG] Cloud Position: Cloud 11 is on tile 102, where the amount of sunlight is 90.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm Action: Machine 1 performs MOWING on tile 103 for 4 days.
[IMPORTANT] Farm Machine: Machine 1 is finished and returns to the shed at 0.
[IMPORTANT] Farm: Farm 0 finished its actions.
[IMPORTANT] Farm: Farm 1 starts its actions.
[DEBUG] Farm: Farm 1 has the following active sowing plans it intends to pursue in this tick: 3.
[IMPORTANT] Farm Action: Machine 11 performs SOWING on tile 201 for 10 days.
[IMPORTANT] Farm Sowing: Machine 11 has sowed POTATO according to sowing plan 3.
[IMPORTANT] Farm Machine: Machine 11 is finished and returns to the shed at 1.
[IMPORTANT] Farm: Farm 1 finished its actions.
[DEBUG] Harvest Estimate: Required actions on tile 201 were not performed: IRRIGATING.
[INFO] Harvest Estimate: Harvest estimate on tile 201 changed to 999900 g of POTATO.

[INFO] Simulation Info: Tick 7 started at tick 8 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 1 FIELD and 2 PLANTATION tiles.
[INFO] Cloud Dissipation: Cloud 3 dissipates on tile 0.
[INFO] Cloud Dissipation: Cloud 5 dissipates on tile 11.
[INFO] Cloud Dissipation: Cloud 6 dissipates on tile 12.
[INFO] Cloud Dissipation: Cloud 7 dissipates on tile 13.
[IMPORTANT] Cloud Rain: Cloud 11 on tile 102 rained down 70 L water.
[INFO] Cloud Dissipation: Cloud 11 dissipates on tile 102.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: 0.
[IMPORTANT] Farm Action: Machine 0 performs SOWING on tile 101 for 8 days.
[IMPORTANT] Farm Sowing: Machine 0 has sowed OAT according to sowing plan 0.
[IMPORTANT] Farm Machine: Machine 0 is finished and returns to the shed at 0.
[IMPORTANT] Farm Action: Machine 4 performs IRRIGATING on tile 103 for 4 days.
[IMPORTANT] Farm Machine: Machine 4 is finished and returns to the shed at 0.
[IMPORTANT] Farm: Farm 0 finished its actions.
[IMPORTANT] Farm: Farm 1 starts its actions.
[DEBUG] Farm: Farm 1 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 1 finished its actions.
[IMPORTANT] Incident: Incident 23 of type BEE_HAPPY happened and affected tiles 104.
[IMPORTANT] Incident: Incident 24 of type BEE_HAPPY happened and affected tiles 104.
[INFO] Harvest Estimate: Harvest estimate on tile 101 changed to 622080 g of OAT.
[DEBUG] Harvest Estimate: Required actions on tile 104 were not performed: IRRIGATING.
[INFO] Harvest Estimate: Harvest estimate on tile 104 changed to 0 g of CHERRY.
[DEBUG] Harvest Estimate: Required actions on tile 201 were not performed: IRRIGATING.
[INFO] Harvest Estimate: Harvest estimate on tile 201 changed to 999750 g of POTATO.

[INFO] Simulation Info: Tick 8 started at tick 9 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 1 FIELD and 1 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm Action: Machine 1 performs WEEDING on tile 101 for 4 days.
[IMPORTANT] Farm Machine: Machine 1 is finished and returns to the shed at 0.
[IMPORTANT] Farm: Farm 0 finished its actions.
[IMPORTANT] Farm: Farm 1 starts its actions.
[DEBUG] Farm: Farm 1 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm Action: Machine 13 performs WEEDING on tile 201 for 10 days.
[IMPORTANT] Farm Machine: Machine 13 is finished and returns to the shed at 1.
[IMPORTANT] Farm: Farm 1 finished its actions.
[IMPORTANT] Incident: Incident 21 of type BEE_HAPPY happened and affected tiles .
[IMPORTANT] Incident: Incident 22 of type BEE_HAPPY happened and affected tiles .
[INFO] Harvest Estimate: Harvest estimate on tile 101 changed to 453495 g of OAT.
[DEBUG] Harvest Estimate: Required actions on tile 201 were not performed: IRRIGATING.
[INFO] Harvest Estimate: Harvest estimate on tile 201 changed to 899575 g of POTATO.

[INFO] Simulation Info: Tick 9 started at tick 10 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 1 FIELD and 1 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm Action: Machine 1 performs WEEDING on tile 101 for 4 days.
[IMPORTANT] Farm Machine: Machine 1 is finished and returns to the shed at 0.
[IMPORTANT] Farm: Farm 0 finished its actions.
[IMPORTANT] Farm: Farm 1 starts its actions.
[DEBUG] Farm: Farm 1 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm Action: Machine 14 performs IRRIGATING on tile 201 for 10 days.
[IMPORTANT] Farm Machine: Machine 14 is finished and returns to the shed at 1.
[IMPORTANT] Farm: Farm 1 finished its actions.
[INFO] Harvest Estimate: Harvest estimate on tile 101 changed to 330597 g of OAT.
[INFO] Harvest Estimate: Harvest estimate on tile 201 changed to 809617 g of POTATO.

[INFO] Simulation Info: Tick 10 started at tick 11 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 1 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm Action: Machine 1 performs WEEDING on tile 101 for 4 days.
[IMPORTANT] Farm Machine: Machine 1 is finished and returns to the shed at 0.
[IMPORTANT] Farm: Farm 0 finished its actions.
[IMPORTANT] Farm: Farm 1 starts its actions.
[DEBUG] Farm: Farm 1 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm Action: Machine 13 performs WEEDING on tile 201 for 10 days.
[IMPORTANT] Farm Machine: Machine 13 is finished and returns to the shed at 1.
[IMPORTANT] Farm: Farm 1 finished its actions.
[IMPORTANT] Incident: Incident 1 of type ANIMAL_ATTACK happened and affected tiles 103,104,200,201.
[INFO] Harvest Estimate: Harvest estimate on tile 101 changed to 241004 g of OAT.
[INFO] Harvest Estimate: Harvest estimate on tile 103 changed to 600000 g of GRAPE.
[INFO] Harvest Estimate: Harvest estimate on tile 201 changed to 364327 g of POTATO.

[INFO] Simulation Info: Tick 11 started at tick 12 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 1 FIELD and 1 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm Action: Machine 4 performs IRRIGATING on tile 101 for 4 days.
[IMPORTANT] Farm Machine: Machine 4 is finished and returns to the shed at 0.
[IMPORTANT] Farm: Farm 0 finished its actions.
[IMPORTANT] Farm: Farm 1 starts its actions.
[DEBUG] Farm: Farm 1 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 1 finished its actions.
[IMPORTANT] Incident: Incident 100 of type CITY_EXPANSION happened and affected tiles 102.
[INFO] Harvest Estimate: Harvest estimate on tile 101 changed to 175690 g of OAT.
[INFO] Harvest Estimate: Harvest estimate on tile 201 changed to 327894 g of POTATO.

[INFO] Simulation Info: Tick 12 started at tick 13 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 1 FIELD and 1 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm Action: Machine 2 performs HARVESTING on tile 101 for 10 days.
[IMPORTANT] Farm Harvest: Machine 2 has collected 175690 g of OAT harvest.
[IMPORTANT] Farm Machine: Machine 2 is finished and returns to the shed at 0.
[IMPORTANT] Farm Machine: Machine 2 unloads 175690 g of OAT harvest in the shed.
[IMPORTANT] Farm Action: Machine 1 performs MOWING on tile 103 for 4 days.
[IMPORTANT] Farm Machine: Machine 1 is finished and returns to the shed at 0.
[IMPORTANT] Farm: Farm 0 finished its actions.
[IMPORTANT] Farm: Farm 1 starts its actions.
[DEBUG] Farm: Farm 1 has the following active sowing plans it intends to pursue in this tick: 2.
[IMPORTANT] Farm Action: Machine 10 performs SOWING on tile 200 for 10 days.
[IMPORTANT] Farm Sowing: Machine 10 has sowed PUMPKIN according to sowing plan 2.
[IMPORTANT] Farm Machine: Machine 10 is finished and returns to the shed at 1.
[IMPORTANT] Farm Action: Machine 13 performs WEEDING on tile 201 for 10 days.
[IMPORTANT] Farm Machine: Machine 13 is finished and returns to the shed at 1.
[IMPORTANT] Farm: Farm 1 finished its actions.
[DEBUG] Harvest Estimate: Required actions on tile 200 were not performed: IRRIGATING.
[INFO] Harvest Estimate: Harvest estimate on tile 200 changed to 0 g of PUMPKIN.
[DEBUG] Harvest Estimate: Required actions on tile 201 were not performed: IRRIGATING.
[INFO] Harvest Estimate: Harvest estimate on tile 201 changed to 295054 g of POTATO.

[INFO] Simulation Info: Tick 13 started at tick 14 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 1 FIELD and 1 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 0 finished its actions.
[IMPORTANT] Farm: Farm 1 starts its actions.
[DEBUG] Farm: Farm 1 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm Action: Machine 14 performs IRRIGATING on tile 201 for 10 days.
[IMPORTANT] Farm Machine: Machine 14 is finished and returns to the shed at 1.
[IMPORTANT] Farm: Farm 1 finished its actions.
[INFO] Harvest Estimate: Harvest estimate on tile 201 changed to 265548 g of POTATO.

[INFO] Simulation Info: Tick 14 started at tick 15 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 1 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 0 finished its actions.
[IMPORTANT] Farm: Farm 1 starts its actions.
[DEBUG] Farm: Farm 1 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm Action: Machine 13 performs WEEDING on tile 201 for 10 days.
[IMPORTANT] Farm Machine: Machine 13 is finished and returns to the shed at 1.
[IMPORTANT] Farm: Farm 1 finished its actions.
[IMPORTANT] Incident: Incident 701 of type CLOUD_CREATION happened and affected tiles 0,1,11,12,13,100,101,103,104,200,201.

[INFO] Simulation Info: Tick 15 started at tick 16 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 2 PLANTATION tiles.
[IMPORTANT] Cloud Rain: Cloud 21 on tile 0 rained down 5050 L water.
[INFO] Cloud Dissipation: Cloud 21 dissipates on tile 0.
[IMPORTANT] Cloud Rain: Cloud 22 on tile 1 rained down 5050 L water.
[INFO] Cloud Dissipation: Cloud 22 dissipates on tile 1.
[IMPORTANT] Cloud Rain: Cloud 23 on tile 11 rained down 5050 L water.
[INFO] Cloud Dissipation: Cloud 23 dissipates on tile 11.
[IMPORTANT] Cloud Rain: Cloud 24 on tile 12 rained down 5050 L water.
[INFO] Cloud Dissipation: Cloud 24 dissipates on tile 12.
[IMPORTANT] Cloud Rain: Cloud 25 on tile 13 rained down 5050 L water.
[INFO] Cloud Dissipation: Cloud 25 dissipates on tile 13.
[IMPORTANT] Cloud Rain: Cloud 26 on tile 100 rained down 700 L water.
[INFO] Cloud Movement: Cloud 26 with 4350 L water moved from tile 100 to tile 101.
[DEBUG] Cloud Movement: On tile 100, the amount of sunlight is 151.
[IMPORTANT] Cloud Union: Clouds 27 and 26 united to cloud 32 with 9400 L water and duration 1 on tile 101.
[IMPORTANT] Cloud Rain: Cloud 28 on tile 103 rained down 800 L water.
[INFO] Cloud Movement: Cloud 28 with 4250 L water moved from tile 103 to tile 100.
[DEBUG] Cloud Movement: On tile 103, the amount of sunlight is 151.
[INFO] Cloud Movement: Cloud 28 with 4250 L water moved from tile 100 to tile 101.
[DEBUG] Cloud Movement: On tile 100, the amount of sunlight is 148.
[IMPORTANT] Cloud Union: Clouds 32 and 28 united to cloud 33 with 13650 L water and duration 1 on tile 101.
[IMPORTANT] Cloud Rain: Cloud 29 on tile 104 rained down 800 L water.
[INFO] Cloud Dissipation: Cloud 29 dissipates on tile 104.
[IMPORTANT] Cloud Rain: Cloud 30 on tile 200 rained down 900 L water.
[INFO] Cloud Movement: Cloud 30 with 4150 L water moved from tile 200 to tile 103.
[DEBUG] Cloud Movement: On tile 200, the amount of sunlight is 151.
[INFO] Cloud Movement: Cloud 30 with 4150 L water moved from tile 103 to tile 100.
[DEBUG] Cloud Movement: On tile 103, the amount of sunlight is 148.
[INFO] Cloud Movement: Cloud 30 with 4150 L water moved from tile 100 to tile 101.
[DEBUG] Cloud Movement: On tile 100, the amount of sunlight is 145.
[IMPORTANT] Cloud Union: Clouds 33 and 30 united to cloud 34 with 17800 L water and duration 1 on tile 101.
[IMPORTANT] Cloud Rain: Cloud 31 on tile 201 rained down 200 L water.
[INFO] Cloud Movement: Cloud 31 with 4850 L water moved from tile 201 to tile 104.
[DEBUG] Cloud Movement: On tile 201, the amount of sunlight is 151.
[INFO] Cloud Dissipation: Cloud 31 dissipates on tile 104.
[IMPORTANT] Cloud Rain: Cloud 34 on tile 101 rained down 310 L water.
[INFO] Cloud Movement: Cloud 34 with 17490 L water moved from tile 101 to tile 10.
[DEBUG] Cloud Movement: On tile 101, the amount of sunlight is 151.
[INFO] Cloud Dissipation: Cloud 34 got stuck on tile 10.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 0 finished its actions.
[IMPORTANT] Farm: Farm 1 starts its actions.
[DEBUG] Farm: Farm 1 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 1 finished its actions.
[IMPORTANT] Incident: Incident 20 of type BEE_HAPPY happened and affected tiles .
[DEBUG] Harvest Estimate: Required actions on tile 103 were not performed: CUTTING.
[INFO] Harvest Estimate: Harvest estimate on tile 103 changed to 300000 g of GRAPE.

[INFO] Simulation Info: Tick 16 started at tick 17 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm Action: Machine 3 performs HARVESTING on tile 103 for 10 days.
[IMPORTANT] Farm Harvest: Machine 3 has collected 300000 g of GRAPE harvest.
[IMPORTANT] Farm Machine: Machine 3 is finished and returns to the shed at 0.
[IMPORTANT] Farm Machine: Machine 3 unloads 300000 g of GRAPE harvest in the shed.
[IMPORTANT] Farm: Farm 0 finished its actions.
[IMPORTANT] Farm: Farm 1 starts its actions.
[DEBUG] Farm: Farm 1 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm Action: Machine 12 performs HARVESTING on tile 201 for 10 days.
[IMPORTANT] Farm Harvest: Machine 12 has collected 265548 g of POTATO harvest.
[IMPORTANT] Farm Machine: Machine 12 is finished and returns to the shed at 1.
[IMPORTANT] Farm Machine: Machine 12 unloads 265548 g of POTATO harvest in the shed.
[IMPORTANT] Farm: Farm 1 finished its actions.

[INFO] Simulation Info: Tick 17 started at tick 18 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 0 finished its actions.
[IMPORTANT] Farm: Farm 1 starts its actions.
[DEBUG] Farm: Farm 1 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 1 finished its actions.

[INFO] Simulation Info: Tick 18 started at tick 19 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 0 finished its actions.
[IMPORTANT] Farm: Farm 1 starts its actions.
[DEBUG] Farm: Farm 1 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 1 finished its actions.

[INFO] Simulation Info: Tick 19 started at tick 20 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: 1.
[IMPORTANT] Farm Action: Machine 0 performs SOWING on tile 100 for 8 days.
[IMPORTANT] Farm Sowing: Machine 0 has sowed WHEAT according to sowing plan 1.
[IMPORTANT] Farm Machine: Machine 0 is finished and returns to the shed at 0.
[IMPORTANT] Farm: Farm 0 finished its actions.
[IMPORTANT] Farm: Farm 1 starts its actions.
[DEBUG] Farm: Farm 1 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 1 finished its actions.

[INFO] Simulation Info: Tick 20 started at tick 21 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 1 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm Action: Machine 4 performs IRRIGATING on tile 100 for 4 days.
[IMPORTANT] Farm Machine: Machine 4 is finished and returns to the shed at 0.
[IMPORTANT] Farm: Farm 0 finished its actions.
[IMPORTANT] Farm: Farm 1 starts its actions.
[DEBUG] Farm: Farm 1 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 1 finished its actions.
[INFO] Harvest Estimate: Harvest estimate on tile 103 changed to 1200000 g of GRAPE.
[INFO] Harvest Estimate: Harvest estimate on tile 104 changed to 1200000 g of CHERRY.

[INFO] Simulation Info: Tick 21 started at tick 22 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 0 finished its actions.
[IMPORTANT] Farm: Farm 1 starts its actions.
[DEBUG] Farm: Farm 1 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 1 finished its actions.

[INFO] Simulation Info: Tick 22 started at tick 23 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 1 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm Action: Machine 1 performs WEEDING on tile 100 for 4 days.
[IMPORTANT] Farm Machine: Machine 1 is finished and returns to the shed at 0.
[IMPORTANT] Farm: Farm 0 finished its actions.
[IMPORTANT] Farm: Farm 1 starts its actions.
[DEBUG] Farm: Farm 1 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 1 finished its actions.
[IMPORTANT] Incident: Incident 0 of type DROUGHT happened and affected tiles 103.
[IMPORTANT] Incident: Incident 501 of type BROKEN_MACHINE happened and affected tiles 0.
[IMPORTANT] Incident: Incident 702 of type CLOUD_CREATION happened and affected tiles 0,1,11,12,13,100,101,103,104,200,201.
[INFO] Harvest Estimate: Harvest estimate on tile 103 changed to 0 g of GRAPE.

[INFO] Simulation Info: Tick 23 started at tick 24 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 1 FIELD and 1 PLANTATION tiles.
[IMPORTANT] Cloud Rain: Cloud 35 on tile 0 rained down 5050 L water.
[INFO] Cloud Dissipation: Cloud 35 dissipates on tile 0.
[IMPORTANT] Cloud Rain: Cloud 36 on tile 1 rained down 5050 L water.
[INFO] Cloud Dissipation: Cloud 36 dissipates on tile 1.
[IMPORTANT] Cloud Rain: Cloud 37 on tile 11 rained down 5050 L water.
[INFO] Cloud Dissipation: Cloud 37 dissipates on tile 11.
[IMPORTANT] Cloud Rain: Cloud 38 on tile 12 rained down 5050 L water.
[INFO] Cloud Dissipation: Cloud 38 dissipates on tile 12.
[IMPORTANT] Cloud Rain: Cloud 39 on tile 13 rained down 5050 L water.
[INFO] Cloud Dissipation: Cloud 39 dissipates on tile 13.
[IMPORTANT] Cloud Rain: Cloud 40 on tile 100 rained down 300 L water.
[INFO] Cloud Movement: Cloud 40 with 4750 L water moved from tile 100 to tile 101.
[DEBUG] Cloud Movement: On tile 100, the amount of sunlight is 81.
[IMPORTANT] Cloud Union: Clouds 41 and 40 united to cloud 46 with 9800 L water and duration 1 on tile 101.
[IMPORTANT] Cloud Rain: Cloud 42 on tile 103 rained down 1000 L water.
[INFO] Cloud Movement: Cloud 42 with 4050 L water moved from tile 103 to tile 100.
[DEBUG] Cloud Movement: On tile 103, the amount of sunlight is 81.
[INFO] Cloud Movement: Cloud 42 with 4050 L water moved from tile 100 to tile 101.
[DEBUG] Cloud Movement: On tile 100, the amount of sunlight is 78.
[IMPORTANT] Cloud Union: Clouds 46 and 42 united to cloud 47 with 13850 L water and duration 1 on tile 101.
[IMPORTANT] Cloud Rain: Cloud 43 on tile 104 rained down 800 L water.
[INFO] Cloud Dissipation: Cloud 43 dissipates on tile 104.
[IMPORTANT] Cloud Rain: Cloud 44 on tile 200 rained down 560 L water.
[INFO] Cloud Movement: Cloud 44 with 4490 L water moved from tile 200 to tile 103.
[DEBUG] Cloud Movement: On tile 200, the amount of sunlight is 81.
[INFO] Cloud Movement: Cloud 44 with 4490 L water moved from tile 103 to tile 100.
[DEBUG] Cloud Movement: On tile 103, the amount of sunlight is 78.
[INFO] Cloud Movement: Cloud 44 with 4490 L water moved from tile 100 to tile 101.
[DEBUG] Cloud Movement: On tile 100, the amount of sunlight is 75.
[IMPORTANT] Cloud Union: Clouds 47 and 44 united to cloud 48 with 18340 L water and duration 1 on tile 101.
[IMPORTANT] Cloud Rain: Cloud 45 on tile 201 rained down 590 L water.
[INFO] Cloud Movement: Cloud 45 with 4460 L water moved from tile 201 to tile 104.
[DEBUG] Cloud Movement: On tile 201, the amount of sunlight is 81.
[INFO] Cloud Dissipation: Cloud 45 dissipates on tile 104.
[IMPORTANT] Cloud Rain: Cloud 48 on tile 101 rained down 560 L water.
[INFO] Cloud Movement: Cloud 48 with 17780 L water moved from tile 101 to tile 10.
[DEBUG] Cloud Movement: On tile 101, the amount of sunlight is 81.
[INFO] Cloud Dissipation: Cloud 48 got stuck on tile 10.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 0 finished its actions.
[IMPORTANT] Farm: Farm 1 starts its actions.
[DEBUG] Farm: Farm 1 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 1 finished its actions.

[INFO] Simulation Info: Tick 24 started at tick 1 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 0 finished its actions.
[IMPORTANT] Farm: Farm 1 starts its actions.
[DEBUG] Farm: Farm 1 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 1 finished its actions.

[INFO] Simulation Info: Tick 25 started at tick 2 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 0 finished its actions.
[IMPORTANT] Farm: Farm 1 starts its actions.
[DEBUG] Farm: Farm 1 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 1 finished its actions.

[INFO] Simulation Info: Tick 26 started at tick 3 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 1 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm Action: Machine 4 performs IRRIGATING on tile 100 for 4 days.
[IMPORTANT] Farm Machine: Machine 4 is finished and returns to the shed at 0.
[IMPORTANT] Farm: Farm 0 finished its actions.
[IMPORTANT] Farm: Farm 1 starts its actions.
[DEBUG] Farm: Farm 1 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 1 finished its actions.

[INFO] Simulation Info: Tick 27 started at tick 4 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 0 finished its actions.
[IMPORTANT] Farm: Farm 1 starts its actions.
[DEBUG] Farm: Farm 1 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 1 finished its actions.
[DEBUG] Harvest Estimate: Required actions on tile 104 were not performed: CUTTING.
[INFO] Harvest Estimate: Harvest estimate on tile 104 changed to 600000 g of CHERRY.

[INFO] Simulation Info: Tick 28 started at tick 5 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm Action: Machine 1 performs WEEDING on tile 100 for 4 days.
[IMPORTANT] Farm Machine: Machine 1 is finished and returns to the shed at 0.
[IMPORTANT] Farm: Farm 0 finished its actions.
[IMPORTANT] Farm: Farm 1 starts its actions.
[DEBUG] Farm: Farm 1 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 1 finished its actions.
[INFO] Harvest Estimate: Harvest estimate on tile 100 changed to 1350000 g of WHEAT.

[INFO] Simulation Info: Tick 29 started at tick 6 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 1 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm Action: Machine 4 performs IRRIGATING on tile 100 for 4 days.
[IMPORTANT] Farm Machine: Machine 4 is finished and returns to the shed at 0.
[IMPORTANT] Farm: Farm 0 finished its actions.
[IMPORTANT] Farm: Farm 1 starts its actions.
[DEBUG] Farm: Farm 1 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 1 finished its actions.
[INFO] Harvest Estimate: Harvest estimate on tile 100 changed to 1215000 g of WHEAT.

[IMPORTANT] Simulation Info: Simulation ended at tick 30.
[IMPORTANT] Simulation Info: Simulation statistics are calculated.
[IMPORTANT] Simulation Statistics: Farm 0 collected 475690 g of harvest.
[IMPORTANT] Simulation Statistics: Farm 1 collected 265548 g of harvest.

[IMPORTANT] Simulation Statistics: Total amount of POTATO harvested: 265548 g.
[IMPORTANT] Simulation Statistics: Total amount of WHEAT harvested: 0 g.
[IMPORTANT] Simulation Statistics: Total amount of OAT harvested: 175690 g.
[IMPORTANT] Simulation Statistics: Total amount of PUMPKIN harvested: 0 g.
[IMPORTANT] Simulation Statistics: Total amount of APPLE harvested: 0 g.
[IMPORTANT] Simulation Statistics: Total amount of GRAPE harvested: 300000 g.
[IMPORTANT] Simulation Statistics: Total amount of ALMOND harvested: 0 g.
[IMPORTANT] Simulation Statistics: Total amount of CHERRY harvested: 0 g.

[IMPORTANT] Simulation Statistics: Total harvest estimate still in fields and plantations: 1815000 g."""

private const val MAP = "bigger_farm/map.json"
private const val FARMS = "bigger_farm/farms.json"
private const val SCENARIO = "bigger_farm/scenario.json"
private const val LEVEL = "DEBUG"
private const val NEWL = "\\n"

/**
 * wow
 */
class BigLad(private val chunk: Int) : ExampleSystemTestExtension() {
    override val name = "BigLad ${if (chunk == 0) "init" else (chunk - 1).toString()}"
    override val description = "Big Lad test for tick ${if (chunk == 0) "init" else (chunk - 1).toString()}"

    override val map = MAP
    override val farms = FARMS
    override val scenario = SCENARIO

    override val logLevel = LEVEL
    override val maxTicks = 30
    override val startYearTick = YearTick.JANUARY_1

    override suspend fun run() {
        val chunks = LOG.split(Regex("\\n\\s*\\n"))
        val lines = chunks[chunk].split(Regex(NEWL))
        skipUntilString(lines.first())
        for (line in lines.drop(1)) {
            assertNextLine(line)
        }
    }
}

/**
 * wow
 */
open class BigLad8Farm0 : ExampleSystemTestExtension() {
    override val name = "BigLad8Farm0"
    override val description = "BigLad8Farm0 test"

    override val map = MAP
    override val farms = FARMS
    override val scenario = SCENARIO

    override val logLevel = LEVEL
    override val maxTicks = 30
    override val startYearTick = YearTick.JANUARY_1

    override suspend fun run() {
        val lines = """[INFO] Simulation Info: Tick 8 started at tick 9 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 1 FIELD and 1 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm Action: Machine 1 performs WEEDING on tile 101 for 4 days.
[IMPORTANT] Farm Machine: Machine 1 is finished and returns to the shed at 0.
[IMPORTANT] Farm: Farm 0 finished its actions.
        """.trimIndent().lines()
        skipUntilString(lines.first())
        for (line in lines.drop(1)) {
            assertNextLine(line)
        }
    }
}

/**
 * wow
 */
open class BigLad8Farm1 : BigLad8Farm0() {
    override val name = "BigLad8Farm1"
    override val description = "BigLad8Farm1 test"

    override val map = MAP
    override val farms = FARMS
    override val scenario = SCENARIO

    override val logLevel = LEVEL
    override val maxTicks = 30
    override val startYearTick = YearTick.JANUARY_1

    override suspend fun run() {
        super.run()
        val lines = """[IMPORTANT] Farm: Farm 1 starts its actions.
[DEBUG] Farm: Farm 1 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm Action: Machine 14 performs IRRIGATING on tile 201 for 10 days.
[IMPORTANT] Farm Machine: Machine 14 is finished and returns to the shed at 1.
[IMPORTANT] Farm: Farm 1 finished its actions.
        """.trimIndent().lines()
        skipUntilString(lines.first())
        for (line in lines.drop(1)) {
            assertNextLine(line)
        }
    }
}
