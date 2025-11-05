package de.unisaarland.cs.se.selab.systemtest.selab25.basictests.one001and1sowingtests

import de.unisaarland.cs.se.selab.systemtest.selab25.utils.ExampleSystemTestExtension

const val OUTPUT_1 = """[INFO] Simulation Info: Tick 0 started at tick 16 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: 0.
[IMPORTANT] Farm: Farm 0 finished its actions.
[INFO] Simulation Info: Tick 1 started at tick 17 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: 0.
[IMPORTANT] Farm: Farm 0 finished its actions.
[INFO] Simulation Info: Tick 2 started at tick 18 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: 0.
[IMPORTANT] Farm: Farm 0 finished its actions.
[INFO] Simulation Info: Tick 3 started at tick 19 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: 0.
[IMPORTANT] Farm Action: Machine 0 performs SOWING on tile 1 for 10 days.
[IMPORTANT] Farm Sowing: Machine 0 has sowed WHEAT according to sowing plan 0.
[IMPORTANT] Farm Machine: Machine 0 is finished and returns to the shed at 0.
[IMPORTANT] Farm: Farm 0 finished its actions.
[INFO] Simulation Info: Tick 4 started at tick 20 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 0 finished its actions.
[INFO] Simulation Info: Tick 5 started at tick 21 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 0 finished its actions.
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
[IMPORTANT] Simulation Statistics: Total harvest estimate still in fields and plantations: 1500000 g."""

/**
* Drought -> try sowing -> make sure it can only be done after 4 ticks
*/
class PartialSowingPlanFulfillment : ExampleSystemTestExtension() {
    override val name = "Partial sowing plan fulfillment"
    override val description = "Sawing (mahcine can do only 1 field) -> sowingPlan is dropped"

    override val farms = "sowingTests/partialPlanFulfillment/farms.json"
    override val scenario = "sowingTests/partialPlanFulfillment/scenario.json"
    override val map = "sowingTests/partialPlanFulfillment/map.json"

    override val logLevel = "DEBUG"
    override val maxTicks = 6
    override val startYearTick = 16

    override suspend fun run() {
        skipUntilString(
            "[INFO] Simulation Info: Simulation started at tick 16 within the year."
        )
        for (line in OUTPUT_1.lines()) {
            assertNextLine(line)
        }
    }
}
