package de.unisaarland.cs.se.selab.systemtest.selab25.basictests.one001and1sowingtests

import de.unisaarland.cs.se.selab.systemtest.selab25.utils.ExampleSystemTestExtension

const val OUTPUT_3 = """[INFO] Simulation Info: Tick 0 started at tick 7 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: 0.
[IMPORTANT] Farm Action: Machine 0 performs SOWING on tile 1 for 5 days.
[IMPORTANT] Farm Sowing: Machine 0 has sowed POTATO according to sowing plan 0.
[IMPORTANT] Farm Machine: Machine 0 is finished and returns to the shed at 0.
[IMPORTANT] Farm: Farm 0 finished its actions.
[DEBUG] Harvest Estimate: Required actions on tile 1 were not performed: IRRIGATING.
[INFO] Harvest Estimate: Harvest estimate on tile 1 changed to 999850 g of POTATO.
[INFO] Simulation Info: Tick 1 started at tick 8 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 1 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 0 finished its actions.
[DEBUG] Harvest Estimate: Required actions on tile 1 were not performed: IRRIGATING.
[INFO] Harvest Estimate: Harvest estimate on tile 1 changed to 999650 g of POTATO.
[INFO] Simulation Info: Tick 2 started at tick 9 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 1 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 0 finished its actions.
[DEBUG] Harvest Estimate: Required actions on tile 1 were not performed: WEEDING,IRRIGATING.
[INFO] Harvest Estimate: Harvest estimate on tile 1 changed to 0 g of POTATO.
[INFO] Simulation Info: Tick 3 started at tick 10 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: 1.
[IMPORTANT] Farm: Farm 0 finished its actions.
[INFO] Simulation Info: Tick 4 started at tick 11 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: 1.
[IMPORTANT] Farm: Farm 0 finished its actions.
[INFO] Simulation Info: Tick 5 started at tick 12 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: 1.
[IMPORTANT] Farm: Farm 0 finished its actions.
[INFO] Simulation Info: Tick 6 started at tick 13 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: 1.
[IMPORTANT] Farm: Farm 0 finished its actions.
[INFO] Simulation Info: Tick 7 started at tick 14 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: 1.
[IMPORTANT] Farm Action: Machine 0 performs SOWING on tile 1 for 5 days.
[IMPORTANT] Farm Sowing: Machine 0 has sowed PUMPKIN according to sowing plan 1.
[IMPORTANT] Farm Machine: Machine 0 is finished and returns to the shed at 0.
[IMPORTANT] Farm: Farm 0 finished its actions.
[INFO] Harvest Estimate: Harvest estimate on tile 1 changed to 0 g of PUMPKIN.
[IMPORTANT] Simulation Info: Simulation ended at tick 8.
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
[IMPORTANT] Simulation Statistics: Total harvest estimate still in fields and plantations: 0 g."""

const val OUTPUT_3_ALT = """[INFO] Simulation Info: Tick 0 started at tick 7 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: 0.
[IMPORTANT] Farm Action: Machine 0 performs SOWING on tile 1 for 5 days.
[IMPORTANT] Farm Sowing: Machine 0 has sowed POTATO according to sowing plan 0.
[IMPORTANT] Farm Machine: Machine 0 is finished and returns to the shed at 0.
[IMPORTANT] Farm: Farm 0 finished its actions.
[DEBUG] Harvest Estimate: Required actions on tile 1 were not performed: IRRIGATING.
[INFO] Harvest Estimate: Harvest estimate on tile 1 changed to 999850 g of POTATO.
[INFO] Simulation Info: Tick 1 started at tick 8 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 1 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 0 finished its actions.
[DEBUG] Harvest Estimate: Required actions on tile 1 were not performed: IRRIGATING.
[INFO] Harvest Estimate: Harvest estimate on tile 1 changed to 999650 g of POTATO.
[INFO] Simulation Info: Tick 2 started at tick 9 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 1 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 0 finished its actions.
[DEBUG] Harvest Estimate: Required actions on tile 1 were not performed: WEEDING,IRRIGATING.
[INFO] Harvest Estimate: Harvest estimate on tile 1 changed to 0 g of POTATO.
[INFO] Simulation Info: Tick 3 started at tick 10 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: 1.
[IMPORTANT] Farm: Farm 0 finished its actions.
[INFO] Simulation Info: Tick 4 started at tick 11 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: 1.
[IMPORTANT] Farm: Farm 0 finished its actions.
[INFO] Simulation Info: Tick 5 started at tick 12 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: 1.
[IMPORTANT] Farm: Farm 0 finished its actions.
[INFO] Simulation Info: Tick 6 started at tick 13 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: 1.
[IMPORTANT] Farm: Farm 0 finished its actions.
[INFO] Simulation Info: Tick 7 started at tick 14 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: 1.
[IMPORTANT] Farm Action: Machine 0 performs SOWING on tile 1 for 5 days.
[IMPORTANT] Farm Sowing: Machine 0 has sowed PUMPKIN according to sowing plan 1.
[IMPORTANT] Farm Machine: Machine 0 is finished and returns to the shed at 0.
[IMPORTANT] Farm: Farm 0 finished its actions.
[DEBUG] Harvest Estimate: Required actions on tile 1 were not performed: IRRIGATING.
[INFO] Harvest Estimate: Harvest estimate on tile 1 changed to 0 g of PUMPKIN.
[IMPORTANT] Simulation Info: Simulation ended at tick 8.
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
[IMPORTANT] Simulation Statistics: Total harvest estimate still in fields and plantations: 0 g."""

/**
 * Fortnite balls
 */
class MoistureDropsToZero : ExampleSystemTestExtension() {
    override val name = "ItsVeryDryInBelarus"
    override val description = "Sowing potato -> moisture drops to 0 -> try sowing again -> only possible after 4 ticks"

    override val farms = "sowingTests/MoistureDropsToZero/farms.json"
    override val scenario = "sowingTests/MoistureDropsToZero/scenario.json"
    override val map = "sowingTests/MoistureDropsToZero/map.json"

    override val logLevel = "DEBUG"
    override val maxTicks = 8
    override val startYearTick = 7

    override suspend fun run() {
        skipUntilString(
            "[INFO] Simulation Info: Simulation started at tick 7 within the year."
        )
        for (line in OUTPUT_3.lines()) {
            assertNextLine(line)
        }
    }
}

/**
 * Fortnite balls
 */
class MoistureDropsToZero2 : ExampleSystemTestExtension() {
    override val name = "ItsVeryDryInBelarus_alternative"
    override val description = "Same, but logs plant being sown and dying in the same tick"

    override val farms = "sowingTests/MoistureDropsToZero/farms.json"
    override val scenario = "sowingTests/MoistureDropsToZero/scenario.json"
    override val map = "sowingTests/MoistureDropsToZero/map.json"

    override val logLevel = "DEBUG"
    override val maxTicks = 8
    override val startYearTick = 7

    override suspend fun run() {
        skipUntilString(
            "[INFO] Simulation Info: Simulation started at tick 7 within the year."
        )
        for (line in OUTPUT_3_ALT.lines()) {
            assertNextLine(line)
        }
    }
}
