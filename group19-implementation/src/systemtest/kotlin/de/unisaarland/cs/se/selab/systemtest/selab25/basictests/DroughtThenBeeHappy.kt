package de.unisaarland.cs.se.selab.systemtest.selab25.basictests

import de.unisaarland.cs.se.selab.systemtest.selab25.utils.ExampleSystemTestExtension

private const val LOG = """
[INFO] Simulation Info: Simulation started at tick 7 within the year.
[INFO] Simulation Info: Tick 0 started at tick 7 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: 0.
[IMPORTANT] Farm Action: Machine 0 performs SOWING on tile 4 for 3 days.
[IMPORTANT] Farm Sowing: Machine 0 has sowed POTATO according to sowing plan 0.
[IMPORTANT] Farm Machine: Machine 0 is finished and returns to the shed at 12.
[IMPORTANT] Farm: Farm 0 finished its actions.
[IMPORTANT] Incident: Incident 1 of type DROUGHT happened and affected tiles 2.
[IMPORTANT] Incident: Incident 2 of type DROUGHT happened and affected tiles 1.
[INFO] Harvest Estimate: Harvest estimate on tile 2 changed to 0 g of APPLE.
[INFO] Simulation Info: Tick 1 started at tick 8 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 0 finished its actions.
[INFO] Simulation Info: Tick 2 started at tick 9 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm Action: Machine 0 performs WEEDING on tile 4 for 3 days.
[IMPORTANT] Farm Machine: Machine 0 is finished and returns to the shed at 12.
[IMPORTANT] Farm: Farm 0 finished its actions.
[INFO] Harvest Estimate: Harvest estimate on tile 4 changed to 900000 g of POTATO.
[INFO] Simulation Info: Tick 3 started at tick 10 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: 1.
[IMPORTANT] Farm Action: Machine 1 performs SOWING on tile 5 for 3 days.
[IMPORTANT] Farm Sowing: Machine 1 has sowed PUMPKIN according to sowing plan 1.
[IMPORTANT] Farm Machine: Machine 1 is finished and returns to the shed at 14.
[IMPORTANT] Farm: Farm 0 finished its actions.
[IMPORTANT] Incident: Incident 3 of type CITY_EXPANSION happened and affected tiles 10.
[INFO] Harvest Estimate: Harvest estimate on tile 4 changed to 810000 g of POTATO.
[INFO] Harvest Estimate: Harvest estimate on tile 5 changed to 450000 g of PUMPKIN.
[INFO] Simulation Info: Tick 4 started at tick 11 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm Action: Machine 0 performs WEEDING on tile 4 for 3 days.
[IMPORTANT] Farm Machine: Machine 0 is finished and returns to the shed at 12.
[IMPORTANT] Farm: Farm 0 finished its actions.
[INFO] Harvest Estimate: Harvest estimate on tile 4 changed to 729000 g of POTATO.
[INFO] Harvest Estimate: Harvest estimate on tile 5 changed to 405000 g of PUMPKIN.
[INFO] Simulation Info: Tick 5 started at tick 12 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm Action: Machine 1 performs WEEDING on tile 5 for 3 days.
[IMPORTANT] Farm Machine: Machine 1 is finished and returns to the shed at 14.
[IMPORTANT] Farm: Farm 0 finished its actions.
[IMPORTANT] Incident: Incident 4 of type BEE_HAPPY happened and affected tiles .
[INFO] Harvest Estimate: Harvest estimate on tile 4 changed to 656100 g of POTATO.
[INFO] Harvest Estimate: Harvest estimate on tile 5 changed to 364500 g of PUMPKIN.
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
[IMPORTANT] Simulation Statistics: Total harvest estimate still in fields and plantations: 1020600 g.
"""

/**
 * A System Test to test Drought and Bee Happy
 */
class DroughtThenBeeHappy : ExampleSystemTestExtension() {
    override val name = "Drought then Bee Happy"
    override val description = "Potato Sowing -> Drought -> Bee Happy -> City Expansion"

    override val farms = "DroughtThenBeeHappy/IncidentMixFarm.json"
    override val scenario = "DroughtThenBeeHappy/IncidentMixScenario.json"
    override val map = "DroughtThenBeeHappy/smallerMap.json"

    override val logLevel = "DEBUG"
    override val maxTicks = 6
    override val startYearTick = 7 // APRIL1

    override suspend fun run() {
        val logLines = LOG.trim().lines()
        val startIdx = logLines.indexOf("[INFO] Simulation Info: Simulation started at tick 7 within the year.")
        require(startIdx != -1) { "Start line not found in LOG." }
        skipUntilString("[INFO] Initialization Info: IncidentMixScenario.json successfully parsed and validated.")
        for (i in startIdx until logLines.size) {
            assertNextLine(logLines[i])
        }
    }
}
