package de.unisaarland.cs.se.selab.systemtest.selab25.basictests

import de.unisaarland.cs.se.selab.systemtest.selab25.utils.ExampleSystemTestExtension

private const val LOG = """
[INFO] Simulation Info: Simulation started at tick 14 within the year.
[INFO] Simulation Info: Tick 0 started at tick 14 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[INFO] Cloud Movement: Cloud 2 with 1000 L water moved from tile 2 to tile 10.
[DEBUG] Cloud Movement: On tile 2, the amount of sunlight is 165.
[INFO] Cloud Dissipation: Cloud 2 got stuck on tile 10.
[INFO] Cloud Movement: Cloud 3 with 1000 L water moved from tile 3 to tile 6.
[DEBUG] Cloud Movement: On tile 3, the amount of sunlight is 165.
[INFO] Cloud Dissipation: Cloud 3 got stuck on tile 6.
[DEBUG] Cloud Position: Cloud 1 is on tile 0, where the amount of sunlight is 118.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: 0.
[IMPORTANT] Farm: Farm 0 finished its actions.
[IMPORTANT] Incident: Incident 1 of type CITY_EXPANSION happened and affected tiles 2.
[DEBUG] Harvest Estimate: Required actions on tile 3 were not performed: HARVESTING.
[INFO] Harvest Estimate: Harvest estimate on tile 3 changed to 324000 g of CHERRY.
[INFO] Harvest Estimate: Harvest estimate on tile 9 changed to 720000 g of ALMOND.
[INFO] Harvest Estimate: Harvest estimate on tile 11 changed to 720000 g of ALMOND.
[INFO] Simulation Info: Tick 1 started at tick 15 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[INFO] Cloud Dissipation: Cloud 1 dissipates on tile 0.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: 0.
[IMPORTANT] Farm: Farm 0 finished its actions.
[IMPORTANT] Incident: Incident 2 of type CITY_EXPANSION happened and affected tiles 5.
[DEBUG] Harvest Estimate: Required actions on tile 3 were not performed: HARVESTING.
[INFO] Harvest Estimate: Harvest estimate on tile 3 changed to 0 g of CHERRY.
[INFO] Simulation Info: Tick 2 started at tick 16 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: 0.
[IMPORTANT] Farm: Farm 0 finished its actions.
[IMPORTANT] Incident: Incident 3 of type CITY_EXPANSION happened and affected tiles 8.
[INFO] Simulation Info: Tick 3 started at tick 17 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: 0.
[IMPORTANT] Farm: Farm 0 finished its actions.
[IMPORTANT] Incident: Incident 4 of type CITY_EXPANSION happened and affected tiles 7.
[DEBUG] Harvest Estimate: Required actions on tile 9 were not performed: MOWING.
[INFO] Harvest Estimate: Harvest estimate on tile 9 changed to 648000 g of ALMOND.
[DEBUG] Harvest Estimate: Required actions on tile 11 were not performed: MOWING.
[INFO] Harvest Estimate: Harvest estimate on tile 11 changed to 648000 g of ALMOND.
[INFO] Simulation Info: Tick 4 started at tick 18 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: 0.
[IMPORTANT] Farm: Farm 0 finished its actions.
[IMPORTANT] Incident: Incident 5 of type CLOUD_CREATION happened and affected tiles 4,9,13,14,16,17.
[INFO] Simulation Info: Tick 5 started at tick 19 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[DEBUG] Cloud Position: Cloud 4 is on tile 4, where the amount of sunlight is 62.
[DEBUG] Cloud Position: Cloud 5 is on tile 9, where the amount of sunlight is 62.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: 0.
[IMPORTANT] Farm: Farm 0 finished its actions.
[DEBUG] Harvest Estimate: Required actions on tile 9 were not performed: HARVESTING.
[INFO] Harvest Estimate: Harvest estimate on tile 9 changed to 583200 g of ALMOND.
[DEBUG] Harvest Estimate: Required actions on tile 11 were not performed: HARVESTING.
[INFO] Harvest Estimate: Harvest estimate on tile 11 changed to 583200 g of ALMOND.
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
[IMPORTANT] Simulation Statistics: Total harvest estimate still in fields and plantations: 1166400 g.
"""

/**
 * System test to check for consecutive city expansion with adjoining expanded tiles
 */
class CrazyCityExpansion : ExampleSystemTestExtension() {
    override val name = "Crazy City Expansion"
    override val description = "System test to check for consecutive city expansion with adjoining expanded tiles"

    override val map = "CrazyCityExpansion/CrazyCityExpansion_Map.json"
    override val farms = "CrazyCityExpansion/CrazyCityExpansion_Farm.json"
    override val scenario = "CrazyCityExpansion/CrazyCityExpansion_Scenario.json"

    override val logLevel = "DEBUG"
    override val maxTicks = 6
    override val startYearTick = 14

    override suspend fun run() {
        val logLines = LOG.trim().lines()
        val startIdx = logLines.indexOf("[INFO] Simulation Info: Simulation started at tick 14 within the year.")
        require(startIdx != -1) { "Start line not found in LOG." }
        skipUntilString(
            "[INFO] Initialization Info: CrazyCityExpansion_Scenario.json successfully parsed and validated."
        )
        for (i in startIdx until logLines.size) {
            assertNextLine(logLines[i])
        }
    }
}
