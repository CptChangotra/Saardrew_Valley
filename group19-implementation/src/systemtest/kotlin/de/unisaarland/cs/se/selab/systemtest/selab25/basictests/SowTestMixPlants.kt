package de.unisaarland.cs.se.selab.systemtest.selab25.basictests

import de.unisaarland.cs.se.selab.systemtest.selab25.utils.ExampleSystemTestExtension
private const val LOG = """[INFO] Initialization Info: mapAllField.json successfully parsed and validated.
[INFO] Initialization Info: farmsAllField.json successfully parsed and validated.
[INFO] Initialization Info: scenarioAllField.json successfully parsed and validated.
[INFO] Simulation Info: Simulation started at tick 7 within the year.

[INFO] Simulation Info: Tick 0 started at tick 7 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 0 finished its actions.
[IMPORTANT] Farm: Farm 1 starts its actions.
[DEBUG] Farm: Farm 1 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 1 finished its actions.
[IMPORTANT] Farm: Farm 2 starts its actions.
[DEBUG] Farm: Farm 2 has the following active sowing plans it intends to pursue in this tick: 301,302,303,304.
[IMPORTANT] Farm Action: Machine 301 performs SOWING on tile 19 for 10 days.
[IMPORTANT] Farm Sowing: Machine 301 has sowed OAT according to sowing plan 301.
[IMPORTANT] Farm Machine: Machine 301 is finished and returns to the shed at 25.
[IMPORTANT] Farm: Farm 2 finished its actions.
[INFO] Harvest Estimate: Harvest estimate on tile 19 changed to 777600 g of OAT.

[INFO] Simulation Info: Tick 1 started at tick 8 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 0 finished its actions.
[IMPORTANT] Farm: Farm 1 starts its actions.
[DEBUG] Farm: Farm 1 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 1 finished its actions.
[IMPORTANT] Farm: Farm 2 starts its actions.
[DEBUG] Farm: Farm 2 has the following active sowing plans it intends to pursue in this tick: 302,303,304.
[IMPORTANT] Farm Action: Machine 301 performs SOWING on tile 20 for 10 days.
[IMPORTANT] Farm Sowing: Machine 301 has sowed OAT according to sowing plan 302.
[IMPORTANT] Farm Machine: Machine 301 is finished and returns to the shed at 25.
[IMPORTANT] Farm Action: Machine 303 performs WEEDING on tile 19 for 4 days.
[IMPORTANT] Farm Machine: Machine 303 is finished and returns to the shed at 25.
[IMPORTANT] Farm: Farm 2 finished its actions.
[INFO] Harvest Estimate: Harvest estimate on tile 19 changed to 629856 g of OAT.
[INFO] Harvest Estimate: Harvest estimate on tile 20 changed to 622080 g of OAT.

[INFO] Simulation Info: Tick 2 started at tick 9 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 0 finished its actions.
[IMPORTANT] Farm: Farm 1 starts its actions.
[DEBUG] Farm: Farm 1 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 1 finished its actions.
[IMPORTANT] Farm: Farm 2 starts its actions.
[DEBUG] Farm: Farm 2 has the following active sowing plans it intends to pursue in this tick: 303,304.
[IMPORTANT] Farm Action: Machine 301 performs SOWING on tile 21 for 10 days.
[IMPORTANT] Farm Sowing: Machine 301 has sowed POTATO according to sowing plan 304.
[IMPORTANT] Farm Machine: Machine 301 is finished and returns to the shed at 25.
[IMPORTANT] Farm Action: Machine 303 performs WEEDING on tile 19 for 4 days.
[IMPORTANT] Farm Action: Machine 303 performs WEEDING on tile 20 for 4 days.
[IMPORTANT] Farm Machine: Machine 303 is finished and returns to the shed at 25.
[IMPORTANT] Farm: Farm 2 finished its actions.
[INFO] Harvest Estimate: Harvest estimate on tile 19 changed to 459164 g of OAT.
[INFO] Harvest Estimate: Harvest estimate on tile 20 changed to 453495 g of OAT.
[INFO] Harvest Estimate: Harvest estimate on tile 21 changed to 900000 g of POTATO.

[INFO] Simulation Info: Tick 3 started at tick 10 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 0 finished its actions.
[IMPORTANT] Farm: Farm 1 starts its actions.
[DEBUG] Farm: Farm 1 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 1 finished its actions.
[IMPORTANT] Farm: Farm 2 starts its actions.
[DEBUG] Farm: Farm 2 has the following active sowing plans it intends to pursue in this tick: 303.
[IMPORTANT] Farm Action: Machine 303 performs WEEDING on tile 19 for 4 days.
[IMPORTANT] Farm Action: Machine 303 performs WEEDING on tile 20 for 4 days.
[IMPORTANT] Farm Machine: Machine 303 is finished and returns to the shed at 25.
[IMPORTANT] Farm: Farm 2 finished its actions.
[INFO] Harvest Estimate: Harvest estimate on tile 19 changed to 334729 g of OAT.
[INFO] Harvest Estimate: Harvest estimate on tile 20 changed to 330597 g of OAT.
[INFO] Harvest Estimate: Harvest estimate on tile 21 changed to 810000 g of POTATO.

[INFO] Simulation Info: Tick 4 started at tick 11 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 0 finished its actions.
[IMPORTANT] Farm: Farm 1 starts its actions.
[DEBUG] Farm: Farm 1 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 1 finished its actions.
[IMPORTANT] Farm: Farm 2 starts its actions.
[DEBUG] Farm: Farm 2 has the following active sowing plans it intends to pursue in this tick: 303,305.
[IMPORTANT] Farm Action: Machine 301 performs SOWING on tile 22 for 10 days.
[IMPORTANT] Farm Sowing: Machine 301 has sowed POTATO according to sowing plan 305.
[IMPORTANT] Farm Machine: Machine 301 is finished and returns to the shed at 25.
[IMPORTANT] Farm Action: Machine 303 performs WEEDING on tile 20 for 4 days.
[IMPORTANT] Farm Action: Machine 303 performs WEEDING on tile 21 for 4 days.
[IMPORTANT] Farm Machine: Machine 303 is finished and returns to the shed at 25.
[IMPORTANT] Farm: Farm 2 finished its actions.
[INFO] Harvest Estimate: Harvest estimate on tile 19 changed to 244017 g of OAT.
[INFO] Harvest Estimate: Harvest estimate on tile 20 changed to 241004 g of OAT.
[INFO] Harvest Estimate: Harvest estimate on tile 21 changed to 729000 g of POTATO.
[INFO] Harvest Estimate: Harvest estimate on tile 22 changed to 720000 g of POTATO.

[INFO] Simulation Info: Tick 5 started at tick 12 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 1 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 0 finished its actions.
[IMPORTANT] Farm: Farm 1 starts its actions.
[DEBUG] Farm: Farm 1 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 1 finished its actions.
[IMPORTANT] Farm: Farm 2 starts its actions.
[DEBUG] Farm: Farm 2 has the following active sowing plans it intends to pursue in this tick: 303,306.
[IMPORTANT] Farm Action: Machine 301 performs SOWING on tile 23 for 10 days.
[IMPORTANT] Farm Sowing: Machine 301 has sowed POTATO according to sowing plan 306.
[IMPORTANT] Farm Machine: Machine 301 is finished and returns to the shed at 25.
[IMPORTANT] Farm Action: Machine 303 performs IRRIGATING on tile 21 for 4 days.
[IMPORTANT] Farm Machine: Machine 303 is finished and returns to the shed at 25.
[IMPORTANT] Farm: Farm 2 finished its actions.
[INFO] Harvest Estimate: Harvest estimate on tile 19 changed to 177887 g of OAT.
[INFO] Harvest Estimate: Harvest estimate on tile 20 changed to 175690 g of OAT.
[INFO] Harvest Estimate: Harvest estimate on tile 21 changed to 656100 g of POTATO.
[INFO] Harvest Estimate: Harvest estimate on tile 22 changed to 648000 g of POTATO.
[INFO] Harvest Estimate: Harvest estimate on tile 23 changed to 576000 g of POTATO.

[INFO] Simulation Info: Tick 6 started at tick 13 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 2 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 0 finished its actions.
[IMPORTANT] Farm: Farm 1 starts its actions.
[DEBUG] Farm: Farm 1 has the following active sowing plans it intends to pursue in this tick: 201.
[IMPORTANT] Farm Action: Machine 201 performs SOWING on tile 18 for 4 days.
[IMPORTANT] Farm Sowing: Machine 201 has sowed PUMPKIN according to sowing plan 201.
[IMPORTANT] Farm Machine: Machine 201 is finished and returns to the shed at 9.
[IMPORTANT] Farm: Farm 1 finished its actions.
[IMPORTANT] Farm: Farm 2 starts its actions.
[DEBUG] Farm: Farm 2 has the following active sowing plans it intends to pursue in this tick: 303.
[IMPORTANT] Farm Action: Machine 304 performs HARVESTING on tile 19 for 10 days.
[IMPORTANT] Farm Harvest: Machine 304 has collected 177887 g of OAT harvest.
[IMPORTANT] Farm Machine: Machine 304 is finished and returns to the shed at 25.
[IMPORTANT] Farm Machine: Machine 304 unloads 177887 g of OAT harvest in the shed.
[IMPORTANT] Farm Action: Machine 303 performs IRRIGATING on tile 22 for 4 days.
[IMPORTANT] Farm Action: Machine 303 performs IRRIGATING on tile 23 for 4 days.
[IMPORTANT] Farm Machine: Machine 303 is finished and returns to the shed at 25.
[IMPORTANT] Farm: Farm 2 finished its actions.
[INFO] Harvest Estimate: Harvest estimate on tile 18 changed to 360000 g of PUMPKIN.
[INFO] Harvest Estimate: Harvest estimate on tile 20 changed to 128077 g of OAT.
[DEBUG] Harvest Estimate: Required actions on tile 21 were not performed: WEEDING.
[INFO] Harvest Estimate: Harvest estimate on tile 21 changed to 531441 g of POTATO.
[DEBUG] Harvest Estimate: Required actions on tile 22 were not performed: WEEDING.
[INFO] Harvest Estimate: Harvest estimate on tile 22 changed to 524880 g of POTATO.
[INFO] Harvest Estimate: Harvest estimate on tile 23 changed to 518400 g of POTATO.

[INFO] Simulation Info: Tick 7 started at tick 14 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 2 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 0 finished its actions.
[IMPORTANT] Farm: Farm 1 starts its actions.
[DEBUG] Farm: Farm 1 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm Action: Machine 203 performs IRRIGATING on tile 18 for 4 days.
[IMPORTANT] Farm Machine: Machine 203 is finished and returns to the shed at 9.
[IMPORTANT] Farm: Farm 1 finished its actions.
[IMPORTANT] Farm: Farm 2 starts its actions.
[DEBUG] Farm: Farm 2 has the following active sowing plans it intends to pursue in this tick: 303,307,308.
[IMPORTANT] Farm Action: Machine 301 performs SOWING on tile 24 for 10 days.
[IMPORTANT] Farm Sowing: Machine 301 has sowed PUMPKIN according to sowing plan 307.
[IMPORTANT] Farm Machine: Machine 301 is finished and returns to the shed at 25.
[IMPORTANT] Farm Action: Machine 304 performs HARVESTING on tile 20 for 10 days.
[IMPORTANT] Farm Harvest: Machine 304 has collected 128077 g of OAT harvest.
[IMPORTANT] Farm Machine: Machine 304 is finished and returns to the shed at 25.
[IMPORTANT] Farm Machine: Machine 304 unloads 128077 g of OAT harvest in the shed.
[IMPORTANT] Farm Action: Machine 303 performs WEEDING on tile 23 for 4 days.
[IMPORTANT] Farm Machine: Machine 303 is finished and returns to the shed at 25.
[IMPORTANT] Farm: Farm 2 finished its actions.
[INFO] Harvest Estimate: Harvest estimate on tile 18 changed to 324000 g of PUMPKIN.
[INFO] Harvest Estimate: Harvest estimate on tile 21 changed to 478296 g of POTATO.
[INFO] Harvest Estimate: Harvest estimate on tile 22 changed to 472392 g of POTATO.
[INFO] Harvest Estimate: Harvest estimate on tile 23 changed to 466560 g of POTATO.
[DEBUG] Harvest Estimate: Required actions on tile 24 were not performed: IRRIGATING.
[INFO] Harvest Estimate: Harvest estimate on tile 24 changed to 287850 g of PUMPKIN.

[INFO] Simulation Info: Tick 8 started at tick 15 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 1 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 0 finished its actions.
[IMPORTANT] Farm: Farm 1 starts its actions.
[DEBUG] Farm: Farm 1 has the following active sowing plans it intends to pursue in this tick: 202.
[IMPORTANT] Farm Action: Machine 203 performs WEEDING on tile 18 for 4 days.
[IMPORTANT] Farm Machine: Machine 203 is finished and returns to the shed at 9.
[IMPORTANT] Farm: Farm 1 finished its actions.
[IMPORTANT] Farm: Farm 2 starts its actions.
[DEBUG] Farm: Farm 2 has the following active sowing plans it intends to pursue in this tick: 303,308,309.
[IMPORTANT] Farm Action: Machine 303 performs WEEDING on tile 21 for 4 days.
[IMPORTANT] Farm Action: Machine 303 performs WEEDING on tile 22 for 4 days.
[IMPORTANT] Farm Machine: Machine 303 is finished and returns to the shed at 25.
[IMPORTANT] Farm: Farm 2 finished its actions.
[INFO] Harvest Estimate: Harvest estimate on tile 18 changed to 291600 g of PUMPKIN.
[DEBUG] Harvest Estimate: Required actions on tile 24 were not performed: IRRIGATING.
[INFO] Harvest Estimate: Harvest estimate on tile 24 changed to 258865 g of PUMPKIN.

[INFO] Simulation Info: Tick 9 started at tick 16 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 1 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 0 finished its actions.
[IMPORTANT] Farm: Farm 1 starts its actions.
[DEBUG] Farm: Farm 1 has the following active sowing plans it intends to pursue in this tick: 202.
[IMPORTANT] Farm: Farm 1 finished its actions.
[IMPORTANT] Farm: Farm 2 starts its actions.
[DEBUG] Farm: Farm 2 has the following active sowing plans it intends to pursue in this tick: 303,308,309.
[IMPORTANT] Farm Action: Machine 303 performs WEEDING on tile 23 for 4 days.
[IMPORTANT] Farm Machine: Machine 303 is finished and returns to the shed at 25.
[IMPORTANT] Farm: Farm 2 finished its actions.
[INFO] Harvest Estimate: Harvest estimate on tile 18 changed to 262440 g of PUMPKIN.
[DEBUG] Harvest Estimate: Required actions on tile 24 were not performed: WEEDING,IRRIGATING.
[INFO] Harvest Estimate: Harvest estimate on tile 24 changed to 209455 g of PUMPKIN.

[INFO] Simulation Info: Tick 10 started at tick 17 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 1 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 0 finished its actions.
[IMPORTANT] Farm: Farm 1 starts its actions.
[DEBUG] Farm: Farm 1 has the following active sowing plans it intends to pursue in this tick: 202.
[IMPORTANT] Farm Action: Machine 202 performs HARVESTING on tile 18 for 4 days.
[IMPORTANT] Farm Harvest: Machine 202 has collected 262440 g of PUMPKIN harvest.
[IMPORTANT] Farm Machine: Machine 202 is finished and returns to the shed at 9.
[IMPORTANT] Farm Machine: Machine 202 unloads 262440 g of PUMPKIN harvest in the shed.
[IMPORTANT] Farm: Farm 1 finished its actions.
[IMPORTANT] Farm: Farm 2 starts its actions.
[DEBUG] Farm: Farm 2 has the following active sowing plans it intends to pursue in this tick: 303,308,309,310.
[IMPORTANT] Farm Action: Machine 304 performs HARVESTING on tile 21 for 10 days.
[IMPORTANT] Farm Harvest: Machine 304 has collected 478296 g of POTATO harvest.
[IMPORTANT] Farm Machine: Machine 304 is finished and returns to the shed at 25.
[IMPORTANT] Farm Machine: Machine 304 unloads 478296 g of POTATO harvest in the shed.
[IMPORTANT] Farm Action: Machine 303 performs WEEDING on tile 22 for 4 days.
[IMPORTANT] Farm Machine: Machine 303 is finished and returns to the shed at 25.
[IMPORTANT] Farm: Farm 2 finished its actions.
[DEBUG] Harvest Estimate: Required actions on tile 24 were not performed: IRRIGATING.
[INFO] Harvest Estimate: Harvest estimate on tile 24 changed to 0 g of PUMPKIN.

[INFO] Simulation Info: Tick 11 started at tick 18 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 0 finished its actions.
[IMPORTANT] Farm: Farm 1 starts its actions.
[DEBUG] Farm: Farm 1 has the following active sowing plans it intends to pursue in this tick: 202.
[IMPORTANT] Farm: Farm 1 finished its actions.
[IMPORTANT] Farm: Farm 2 starts its actions.
[DEBUG] Farm: Farm 2 has the following active sowing plans it intends to pursue in this tick: 303,308,309,310.
[IMPORTANT] Farm Action: Machine 304 performs HARVESTING on tile 22 for 10 days.
[IMPORTANT] Farm Harvest: Machine 304 has collected 472392 g of POTATO harvest.
[IMPORTANT] Farm Machine: Machine 304 is finished and returns to the shed at 25.
[IMPORTANT] Farm Machine: Machine 304 unloads 472392 g of POTATO harvest in the shed.
[IMPORTANT] Farm Action: Machine 303 performs WEEDING on tile 23 for 4 days.
[IMPORTANT] Farm Machine: Machine 303 is finished and returns to the shed at 25.
[IMPORTANT] Farm: Farm 2 finished its actions.

[INFO] Simulation Info: Tick 12 started at tick 19 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 1 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 0 finished its actions.
[IMPORTANT] Farm: Farm 1 starts its actions.
[DEBUG] Farm: Farm 1 has the following active sowing plans it intends to pursue in this tick: 202.
[IMPORTANT] Farm: Farm 1 finished its actions.
[IMPORTANT] Farm: Farm 2 starts its actions.
[DEBUG] Farm: Farm 2 has the following active sowing plans it intends to pursue in this tick: 303,308,309,310.
[IMPORTANT] Farm Action: Machine 301 performs SOWING on tile 19 for 10 days.
[IMPORTANT] Farm Sowing: Machine 301 has sowed WHEAT according to sowing plan 308.
[IMPORTANT] Farm Machine: Machine 301 is finished and returns to the shed at 25.
[IMPORTANT] Farm Action: Machine 304 performs HARVESTING on tile 23 for 10 days.
[IMPORTANT] Farm Harvest: Machine 304 has collected 466560 g of POTATO harvest.
[IMPORTANT] Farm Machine: Machine 304 is finished and returns to the shed at 25.
[IMPORTANT] Farm Machine: Machine 304 unloads 466560 g of POTATO harvest in the shed.
[IMPORTANT] Farm: Farm 2 finished its actions.
[DEBUG] Harvest Estimate: Required actions on tile 19 were not performed: IRRIGATING.
[INFO] Harvest Estimate: Harvest estimate on tile 19 changed to 0 g of WHEAT.

[INFO] Simulation Info: Tick 13 started at tick 20 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: 101.
[IMPORTANT] Farm: Farm 0 finished its actions.
[IMPORTANT] Farm: Farm 1 starts its actions.
[DEBUG] Farm: Farm 1 has the following active sowing plans it intends to pursue in this tick: 202,203.
[IMPORTANT] Farm Action: Machine 201 performs SOWING on tile 13 for 4 days.
[IMPORTANT] Farm Sowing: Machine 201 has sowed WHEAT according to sowing plan 203.
[IMPORTANT] Farm Machine: Machine 201 is finished and returns to the shed at 9.
[IMPORTANT] Farm: Farm 1 finished its actions.
[IMPORTANT] Farm: Farm 2 starts its actions.
[DEBUG] Farm: Farm 2 has the following active sowing plans it intends to pursue in this tick: 303,309,310.
[IMPORTANT] Farm Action: Machine 301 performs SOWING on tile 20 for 10 days.
[IMPORTANT] Farm Sowing: Machine 301 has sowed WHEAT according to sowing plan 309.
[IMPORTANT] Farm Machine: Machine 301 is finished and returns to the shed at 25.
[IMPORTANT] Farm: Farm 2 finished its actions.
[IMPORTANT] Incident: Incident 0 of type DROUGHT happened and affected tiles 13.
[INFO] Harvest Estimate: Harvest estimate on tile 13 changed to 0 g of WHEAT.
[DEBUG] Harvest Estimate: Required actions on tile 20 were not performed: IRRIGATING.
[INFO] Harvest Estimate: Harvest estimate on tile 20 changed to 0 g of WHEAT.

[INFO] Simulation Info: Tick 14 started at tick 21 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: 101.
[IMPORTANT] Farm: Farm 0 finished its actions.
[IMPORTANT] Farm: Farm 1 starts its actions.
[DEBUG] Farm: Farm 1 has the following active sowing plans it intends to pursue in this tick: 202,204.
[IMPORTANT] Farm: Farm 1 finished its actions.
[IMPORTANT] Farm: Farm 2 starts its actions.
[DEBUG] Farm: Farm 2 has the following active sowing plans it intends to pursue in this tick: 303,310.
[IMPORTANT] Farm: Farm 2 finished its actions.

[INFO] Simulation Info: Tick 15 started at tick 22 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: 101,102.
[IMPORTANT] Farm Action: Machine 101 performs SOWING on tile 11 for 4 days.
[IMPORTANT] Farm Sowing: Machine 101 has sowed WHEAT according to sowing plan 102.
[IMPORTANT] Farm Machine: Machine 101 is finished and returns to the shed at 1.
[IMPORTANT] Farm: Farm 0 finished its actions.
[IMPORTANT] Farm: Farm 1 starts its actions.
[DEBUG] Farm: Farm 1 has the following active sowing plans it intends to pursue in this tick: 202,204.
[IMPORTANT] Farm: Farm 1 finished its actions.
[IMPORTANT] Farm: Farm 2 starts its actions.
[DEBUG] Farm: Farm 2 has the following active sowing plans it intends to pursue in this tick: 303,310.
[IMPORTANT] Farm Action: Machine 301 performs SOWING on tile 24 for 10 days.
[IMPORTANT] Farm Sowing: Machine 301 has sowed WHEAT according to sowing plan 310.
[IMPORTANT] Farm Machine: Machine 301 is finished and returns to the shed at 25.
[IMPORTANT] Farm: Farm 2 finished its actions.
[DEBUG] Harvest Estimate: Required actions on tile 11 were not performed: IRRIGATING.
[INFO] Harvest Estimate: Harvest estimate on tile 11 changed to 0 g of WHEAT.
[DEBUG] Harvest Estimate: Required actions on tile 24 were not performed: IRRIGATING.
[INFO] Harvest Estimate: Harvest estimate on tile 24 changed to 0 g of WHEAT.

[INFO] Simulation Info: Tick 16 started at tick 23 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: 101,103.
[IMPORTANT] Farm: Farm 0 finished its actions.
[IMPORTANT] Farm: Farm 1 starts its actions.
[DEBUG] Farm: Farm 1 has the following active sowing plans it intends to pursue in this tick: 202,204.
[IMPORTANT] Farm: Farm 1 finished its actions.
[IMPORTANT] Farm: Farm 2 starts its actions.
[DEBUG] Farm: Farm 2 has the following active sowing plans it intends to pursue in this tick: 303.
[IMPORTANT] Farm: Farm 2 finished its actions.

[INFO] Simulation Info: Tick 17 started at tick 24 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: 101,103.
[IMPORTANT] Farm: Farm 0 finished its actions.
[IMPORTANT] Farm: Farm 1 starts its actions.
[DEBUG] Farm: Farm 1 has the following active sowing plans it intends to pursue in this tick: 202,204.
[IMPORTANT] Farm: Farm 1 finished its actions.
[IMPORTANT] Farm: Farm 2 starts its actions.
[DEBUG] Farm: Farm 2 has the following active sowing plans it intends to pursue in this tick: 303.
[IMPORTANT] Farm: Farm 2 finished its actions.

[INFO] Simulation Info: Tick 18 started at tick 1 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: 101,103.
[IMPORTANT] Farm: Farm 0 finished its actions.
[IMPORTANT] Farm: Farm 1 starts its actions.
[DEBUG] Farm: Farm 1 has the following active sowing plans it intends to pursue in this tick: 202,204.
[IMPORTANT] Farm: Farm 1 finished its actions.
[IMPORTANT] Farm: Farm 2 starts its actions.
[DEBUG] Farm: Farm 2 has the following active sowing plans it intends to pursue in this tick: 303.
[IMPORTANT] Farm: Farm 2 finished its actions.

[INFO] Simulation Info: Tick 19 started at tick 2 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: 101,103.
[IMPORTANT] Farm: Farm 0 finished its actions.
[IMPORTANT] Farm: Farm 1 starts its actions.
[DEBUG] Farm: Farm 1 has the following active sowing plans it intends to pursue in this tick: 202,204.
[IMPORTANT] Farm: Farm 1 finished its actions.
[IMPORTANT] Farm: Farm 2 starts its actions.
[DEBUG] Farm: Farm 2 has the following active sowing plans it intends to pursue in this tick: 303.
[IMPORTANT] Farm: Farm 2 finished its actions.

[INFO] Simulation Info: Tick 20 started at tick 3 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: 101,103.
[IMPORTANT] Farm: Farm 0 finished its actions.
[IMPORTANT] Farm: Farm 1 starts its actions.
[DEBUG] Farm: Farm 1 has the following active sowing plans it intends to pursue in this tick: 202,204.
[IMPORTANT] Farm: Farm 1 finished its actions.
[IMPORTANT] Farm: Farm 2 starts its actions.
[DEBUG] Farm: Farm 2 has the following active sowing plans it intends to pursue in this tick: 303.
[IMPORTANT] Farm: Farm 2 finished its actions.

[INFO] Simulation Info: Tick 21 started at tick 4 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: 101,103.
[IMPORTANT] Farm: Farm 0 finished its actions.
[IMPORTANT] Farm: Farm 1 starts its actions.
[DEBUG] Farm: Farm 1 has the following active sowing plans it intends to pursue in this tick: 202,204.
[IMPORTANT] Farm: Farm 1 finished its actions.
[IMPORTANT] Farm: Farm 2 starts its actions.
[DEBUG] Farm: Farm 2 has the following active sowing plans it intends to pursue in this tick: 303.
[IMPORTANT] Farm: Farm 2 finished its actions.

[INFO] Simulation Info: Tick 22 started at tick 5 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: 101,103.
[IMPORTANT] Farm: Farm 0 finished its actions.
[IMPORTANT] Farm: Farm 1 starts its actions.
[DEBUG] Farm: Farm 1 has the following active sowing plans it intends to pursue in this tick: 202,204.
[IMPORTANT] Farm: Farm 1 finished its actions.
[IMPORTANT] Farm: Farm 2 starts its actions.
[DEBUG] Farm: Farm 2 has the following active sowing plans it intends to pursue in this tick: 303.
[IMPORTANT] Farm: Farm 2 finished its actions.

[INFO] Simulation Info: Tick 23 started at tick 6 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: 101,103.
[IMPORTANT] Farm: Farm 0 finished its actions.
[IMPORTANT] Farm: Farm 1 starts its actions.
[DEBUG] Farm: Farm 1 has the following active sowing plans it intends to pursue in this tick: 202,204.
[IMPORTANT] Farm: Farm 1 finished its actions.
[IMPORTANT] Farm: Farm 2 starts its actions.
[DEBUG] Farm: Farm 2 has the following active sowing plans it intends to pursue in this tick: 303.
[IMPORTANT] Farm Action: Machine 301 performs SOWING on tile 19 for 10 days.
[IMPORTANT] Farm Sowing: Machine 301 has sowed OAT according to sowing plan 303.
[IMPORTANT] Farm Machine: Machine 301 is finished and returns to the shed at 25.
[IMPORTANT] Farm: Farm 2 finished its actions.
[DEBUG] Harvest Estimate: Required actions on tile 19 were not performed: IRRIGATING.
[INFO] Harvest Estimate: Harvest estimate on tile 19 changed to 0 g of OAT.

[IMPORTANT] Simulation Info: Simulation ended at tick 24.
[IMPORTANT] Simulation Info: Simulation statistics are calculated.
[IMPORTANT] Simulation Statistics: Farm 0 collected 0 g of harvest.
[IMPORTANT] Simulation Statistics: Farm 1 collected 262440 g of harvest.
[IMPORTANT] Simulation Statistics: Farm 2 collected 1723212 g of harvest.
[IMPORTANT] Simulation Statistics: Total amount of POTATO harvested: 1417248 g.
[IMPORTANT] Simulation Statistics: Total amount of WHEAT harvested: 0 g.
[IMPORTANT] Simulation Statistics: Total amount of OAT harvested: 305964 g.
[IMPORTANT] Simulation Statistics: Total amount of PUMPKIN harvested: 262440 g.
[IMPORTANT] Simulation Statistics: Total amount of APPLE harvested: 0 g.
[IMPORTANT] Simulation Statistics: Total amount of GRAPE harvested: 0 g.
[IMPORTANT] Simulation Statistics: Total amount of ALMOND harvested: 0 g.
[IMPORTANT] Simulation Statistics: Total amount of CHERRY harvested: 0 g.
[IMPORTANT] Simulation Statistics: Total harvest estimate still in fields and plantations: 0 g."""
private const val NEWL = "\\n"

/**
 * test
 */
class SowTestMixPlants(private val chunk: Int) : ExampleSystemTestExtension() {
    override val name = "SowTestMixPlants ${if (chunk == 0) "init" else (chunk - 1).toString()}"
    override val description = "SowTestMixPlants test for tick ${if (chunk == 0) "init" else (chunk - 1).toString()}"

    override val farms = "sowingPlan/farmsAllField.json"
    override val scenario = "sowingPlan/scenarioAllField.json"
    override val map = "sowingPlan/mapAllField.json"

    override val logLevel = "DEBUG"
    override val maxTicks = 24
    override val startYearTick = 7

    override suspend fun run() {
        val chunks = LOG.split(Regex("\\n\\s*\\n"))
        val lines = chunks[chunk].split(Regex(NEWL))
        skipUntilString(lines.first())
        for (line in lines.drop(1)) {
            assertNextLine(line)
        }
    }
}
