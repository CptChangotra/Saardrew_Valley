package de.unisaarland.cs.se.selab.systemtest.selab25.basictests

import de.unisaarland.cs.se.selab.systemtest.selab25.utils.ExampleSystemTestExtension

/**
 * Testing the irrigation order
 */
const val LOGS = """[INFO] Simulation Info: Tick 0 started at tick 7 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: 0.
[IMPORTANT] Farm Action: Machine 0 performs SOWING on tile 2 for 1 days.
[IMPORTANT] Farm Sowing: Machine 0 has sowed POTATO according to sowing plan 0.
[IMPORTANT] Farm Action: Machine 0 performs SOWING on tile 4 for 1 days.
[IMPORTANT] Farm Sowing: Machine 0 has sowed POTATO according to sowing plan 0.
[IMPORTANT] Farm Machine: Machine 0 is finished and returns to the shed at 0.
[IMPORTANT] Farm: Farm 0 finished its actions.
[INFO] Harvest Estimate: Harvest estimate on tile 3 changed to 1239300 g of APPLE.
[INFO] Simulation Info: Tick 1 started at tick 8 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 0 finished its actions.
[INFO] Harvest Estimate: Harvest estimate on tile 3 changed to 903449 g of APPLE.
[INFO] Simulation Info: Tick 2 started at tick 9 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 0 finished its actions.
[DEBUG] Harvest Estimate: Required actions on tile 2 were not performed: WEEDING.
[INFO] Harvest Estimate: Harvest estimate on tile 2 changed to 810000 g of POTATO.
[INFO] Harvest Estimate: Harvest estimate on tile 3 changed to 592751 g of APPLE.
[DEBUG] Harvest Estimate: Required actions on tile 4 were not performed: WEEDING.
[INFO] Harvest Estimate: Harvest estimate on tile 4 changed to 810000 g of POTATO.
[INFO] Harvest Estimate: Harvest estimate on tile 5 changed to 1080000 g of CHERRY.
[INFO] Simulation Info: Tick 3 started at tick 10 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 0 finished its actions.
[INFO] Harvest Estimate: Harvest estimate on tile 2 changed to 729000 g of POTATO.
[INFO] Harvest Estimate: Harvest estimate on tile 3 changed to 388902 g of APPLE.
[INFO] Harvest Estimate: Harvest estimate on tile 4 changed to 729000 g of POTATO.
[INFO] Harvest Estimate: Harvest estimate on tile 5 changed to 972000 g of CHERRY.
[INFO] Simulation Info: Tick 4 started at tick 11 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 0 finished its actions.
[DEBUG] Harvest Estimate: Required actions on tile 2 were not performed: WEEDING.
[INFO] Harvest Estimate: Harvest estimate on tile 2 changed to 590490 g of POTATO.
[DEBUG] Harvest Estimate: Required actions on tile 3 were not performed: MOWING.
[INFO] Harvest Estimate: Harvest estimate on tile 3 changed to 229641 g of APPLE.
[DEBUG] Harvest Estimate: Required actions on tile 4 were not performed: WEEDING.
[INFO] Harvest Estimate: Harvest estimate on tile 4 changed to 590490 g of POTATO.
[DEBUG] Harvest Estimate: Required actions on tile 5 were not performed: MOWING.
[INFO] Harvest Estimate: Harvest estimate on tile 5 changed to 787320 g of CHERRY.
[INFO] Simulation Info: Tick 5 started at tick 12 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 0 finished its actions.
[INFO] Harvest Estimate: Harvest estimate on tile 2 changed to 531441 g of POTATO.
[INFO] Harvest Estimate: Harvest estimate on tile 3 changed to 150666 g of APPLE.
[INFO] Harvest Estimate: Harvest estimate on tile 4 changed to 531441 g of POTATO.
[INFO] Harvest Estimate: Harvest estimate on tile 5 changed to 708588 g of CHERRY.
[INFO] Simulation Info: Tick 6 started at tick 13 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 0 finished its actions.
[DEBUG] Harvest Estimate: Required actions on tile 2 were not performed: WEEDING.
[INFO] Harvest Estimate: Harvest estimate on tile 2 changed to 430466 g of POTATO.
[INFO] Harvest Estimate: Harvest estimate on tile 3 changed to 98851 g of APPLE.
[DEBUG] Harvest Estimate: Required actions on tile 4 were not performed: WEEDING.
[INFO] Harvest Estimate: Harvest estimate on tile 4 changed to 430466 g of POTATO.
[INFO] Harvest Estimate: Harvest estimate on tile 5 changed to 637729 g of CHERRY.
[INFO] Simulation Info: Tick 7 started at tick 14 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 0 finished its actions.
[INFO] Harvest Estimate: Harvest estimate on tile 2 changed to 387419 g of POTATO.
[INFO] Harvest Estimate: Harvest estimate on tile 3 changed to 64854 g of APPLE.
[INFO] Harvest Estimate: Harvest estimate on tile 4 changed to 387419 g of POTATO.
[DEBUG] Harvest Estimate: Required actions on tile 5 were not performed: HARVESTING.
[INFO] Harvest Estimate: Harvest estimate on tile 5 changed to 172186 g of CHERRY.
[INFO] Simulation Info: Tick 8 started at tick 15 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 0 finished its actions.
[DEBUG] Harvest Estimate: Required actions on tile 2 were not performed: WEEDING.
[INFO] Harvest Estimate: Harvest estimate on tile 2 changed to 348677 g of POTATO.
[INFO] Harvest Estimate: Harvest estimate on tile 3 changed to 42549 g of APPLE.
[DEBUG] Harvest Estimate: Required actions on tile 4 were not performed: WEEDING.
[INFO] Harvest Estimate: Harvest estimate on tile 4 changed to 348677 g of POTATO.
[DEBUG] Harvest Estimate: Required actions on tile 5 were not performed: HARVESTING.
[INFO] Harvest Estimate: Harvest estimate on tile 5 changed to 0 g of CHERRY.
[INFO] Simulation Info: Tick 9 started at tick 16 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 0 finished its actions.
[INFO] Harvest Estimate: Harvest estimate on tile 3 changed to 27915 g of APPLE.
[INFO] Simulation Info: Tick 10 started at tick 17 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm Action: Machine 0 performs HARVESTING on tile 2 for 1 days.
[IMPORTANT] Farm Harvest: Machine 0 has collected 348677 g of POTATO harvest.
[IMPORTANT] Farm Action: Machine 0 performs HARVESTING on tile 4 for 1 days.
[IMPORTANT] Farm Harvest: Machine 0 has collected 348677 g of POTATO harvest.
[IMPORTANT] Farm Machine: Machine 0 is finished and returns to the shed at 0.
[IMPORTANT] Farm Machine: Machine 0 unloads 697354 g of POTATO harvest in the shed.
[IMPORTANT] Farm: Farm 0 finished its actions.
[DEBUG] Harvest Estimate: Required actions on tile 3 were not performed: MOWING.
[INFO] Harvest Estimate: Harvest estimate on tile 3 changed to 18314 g of APPLE.
[INFO] Simulation Info: Tick 11 started at tick 18 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 0 finished its actions.
[INFO] Harvest Estimate: Harvest estimate on tile 3 changed to 13349 g of APPLE.
[INFO] Simulation Info: Tick 12 started at tick 19 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: 1,2.
[IMPORTANT] Farm Action: Machine 0 performs SOWING on tile 1 for 1 days.
[IMPORTANT] Farm Sowing: Machine 0 has sowed WHEAT according to sowing plan 1.
[IMPORTANT] Farm Machine: Machine 0 is finished and returns to the shed at 0.
[IMPORTANT] Farm: Farm 0 finished its actions.
[DEBUG] Harvest Estimate: Required actions on tile 3 were not performed: HARVESTING.
[INFO] Harvest Estimate: Harvest estimate on tile 3 changed to 5406 g of APPLE.
[INFO] Simulation Info: Tick 13 started at tick 20 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: 2.
[IMPORTANT] Farm: Farm 0 finished its actions.
[DEBUG] Harvest Estimate: Required actions on tile 3 were not performed: HARVESTING.
[INFO] Harvest Estimate: Harvest estimate on tile 3 changed to 0 g of APPLE.
[INFO] Simulation Info: Tick 14 started at tick 21 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: 2.
[IMPORTANT] Farm: Farm 0 finished its actions.
[INFO] Harvest Estimate: Harvest estimate on tile 3 changed to 1530000 g of APPLE.
[INFO] Harvest Estimate: Harvest estimate on tile 5 changed to 1200000 g of CHERRY.
[INFO] Simulation Info: Tick 15 started at tick 22 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: 2.
[IMPORTANT] Farm Action: Machine 0 performs SOWING on tile 2 for 1 days.
[IMPORTANT] Farm Sowing: Machine 0 has sowed WHEAT according to sowing plan 2.
[IMPORTANT] Farm Machine: Machine 0 is finished and returns to the shed at 0.
[IMPORTANT] Farm: Farm 0 finished its actions.
[DEBUG] Harvest Estimate: Required actions on tile 1 were not performed: WEEDING.
[INFO] Harvest Estimate: Harvest estimate on tile 1 changed to 1350000 g of WHEAT.
[INFO] Harvest Estimate: Harvest estimate on tile 2 changed to 960000 g of WHEAT.
[INFO] Harvest Estimate: Harvest estimate on tile 3 changed to 1377000 g of APPLE.
[IMPORTANT] Simulation Info: Simulation ended at tick 16.
[IMPORTANT] Simulation Info: Simulation statistics are calculated.
[IMPORTANT] Simulation Statistics: Farm 0 collected 697354 g of harvest.
[IMPORTANT] Simulation Statistics: Total amount of POTATO harvested: 697354 g.
[IMPORTANT] Simulation Statistics: Total amount of WHEAT harvested: 0 g.
[IMPORTANT] Simulation Statistics: Total amount of OAT harvested: 0 g.
[IMPORTANT] Simulation Statistics: Total amount of PUMPKIN harvested: 0 g.
[IMPORTANT] Simulation Statistics: Total amount of APPLE harvested: 0 g.
[IMPORTANT] Simulation Statistics: Total amount of GRAPE harvested: 0 g.
[IMPORTANT] Simulation Statistics: Total amount of ALMOND harvested: 0 g.
[IMPORTANT] Simulation Statistics: Total amount of CHERRY harvested: 0 g.
[IMPORTANT] Simulation Statistics: Total harvest estimate still in fields and plantations: 4887000 g."""

/**
 * Fuck the docs
 */
open class NewSowTestAsRef1 : ExampleSystemTestExtension() {
    override val name = "SowingTest part 1"
    override val description = "Tests the sowing logic"

    // Paths are relative from the `src/systemtest/resources` directory.
    override val farms = "sowTestAsRef/farms.json"
    override val scenario = "example/scenario.json"
    override val map = "sowTestAsRef/map.json"

    override val logLevel = "DEBUG"
    override val maxTicks = 16
    override val startYearTick = 7

    override suspend fun run() {
        skipUntilString(
            "[INFO] Simulation Info: Simulation started at tick 7 within the year."
        )
        for (line in LOGS.lines()) {
            assertNextLine(line)
        }
    }
}
