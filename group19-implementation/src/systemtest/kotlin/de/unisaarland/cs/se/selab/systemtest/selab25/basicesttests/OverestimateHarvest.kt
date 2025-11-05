package de.unisaarland.cs.se.selab.systemtest.selab25.basicesttests

import de.unisaarland.cs.se.selab.systemtest.selab25.utils.ExampleSystemTestExtension

private const val LOG = """[INFO] Initialization Info: onePlantationMap.json successfully parsed and validated.
[INFO] Initialization Info: onePlantationFarm.json successfully parsed and validated.
[INFO] Initialization Info: onePlantationScenario.json successfully parsed and validated.
[INFO] Simulation Info: Simulation started at tick 8 within the year.
[INFO] Simulation Info: Tick 0 started at tick 8 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Cloud Rain: Cloud 0 on tile 10 rained down 100 L water.
[DEBUG] Cloud Position: Cloud 0 is on tile 10, where the amount of sunlight is 90.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 0 finished its actions.
[IMPORTANT] Incident: Incident 12 of type BEE_HAPPY happened and affected tiles 10.
[INFO] Harvest Estimate: Harvest estimate on tile 10 changed to 9180000 g of APPLE.
[INFO] Simulation Info: Tick 1 started at tick 9 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Cloud Rain: Cloud 0 on tile 10 rained down 100 L water.
[DEBUG] Cloud Position: Cloud 0 is on tile 10, where the amount of sunlight is 118.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 0 finished its actions.
[INFO] Harvest Estimate: Harvest estimate on tile 10 changed to 7435800 g of APPLE.
[INFO] Simulation Info: Tick 2 started at tick 10 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Cloud Rain: Cloud 0 on tile 10 rained down 100 L water.
[DEBUG] Cloud Position: Cloud 0 is on tile 10, where the amount of sunlight is 118.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 0 finished its actions.
[INFO] Harvest Estimate: Harvest estimate on tile 10 changed to 6022998 g of APPLE.
[INFO] Simulation Info: Tick 3 started at tick 11 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Cloud Rain: Cloud 0 on tile 10 rained down 100 L water.
[DEBUG] Cloud Position: Cloud 0 is on tile 10, where the amount of sunlight is 118.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm Action: Machine 0 performs MOWING on tile 10 for 4 days.
[IMPORTANT] Farm Machine: Machine 0 is finished and returns to the shed at 0.
[IMPORTANT] Farm: Farm 0 finished its actions.
[INFO] Harvest Estimate: Harvest estimate on tile 10 changed to 4878628 g of APPLE.
[INFO] Simulation Info: Tick 4 started at tick 12 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Cloud Rain: Cloud 0 on tile 10 rained down 100 L water.
[DEBUG] Cloud Position: Cloud 0 is on tile 10, where the amount of sunlight is 118.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 0 finished its actions.
[INFO] Harvest Estimate: Harvest estimate on tile 10 changed to 3951688 g of APPLE.
[INFO] Simulation Info: Tick 5 started at tick 13 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Cloud Rain: Cloud 0 on tile 10 rained down 100 L water.
[DEBUG] Cloud Position: Cloud 0 is on tile 10, where the amount of sunlight is 118.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 0 finished its actions.
[INFO] Harvest Estimate: Harvest estimate on tile 10 changed to 3200867 g of APPLE.
[INFO] Simulation Info: Tick 6 started at tick 14 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Cloud Rain: Cloud 0 on tile 10 rained down 100 L water.
[DEBUG] Cloud Position: Cloud 0 is on tile 10, where the amount of sunlight is 118.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 0 finished its actions.
[INFO] Harvest Estimate: Harvest estimate on tile 10 changed to 2592702 g of APPLE.
[INFO] Simulation Info: Tick 7 started at tick 15 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Cloud Rain: Cloud 0 on tile 10 rained down 100 L water.
[DEBUG] Cloud Position: Cloud 0 is on tile 10, where the amount of sunlight is 104.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 0 finished its actions.
[INFO] Harvest Estimate: Harvest estimate on tile 10 changed to 2100087 g of APPLE.
[INFO] Simulation Info: Tick 8 started at tick 16 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Cloud Rain: Cloud 0 on tile 10 rained down 100 L water.
[DEBUG] Cloud Position: Cloud 0 is on tile 10, where the amount of sunlight is 104.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 0 finished its actions.
[INFO] Harvest Estimate: Harvest estimate on tile 10 changed to 1701070 g of APPLE.
[INFO] Simulation Info: Tick 9 started at tick 17 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Cloud Rain: Cloud 0 on tile 10 rained down 100 L water.
[DEBUG] Cloud Position: Cloud 0 is on tile 10, where the amount of sunlight is 76.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm Action: Machine 0 performs HARVESTING on tile 10 for 4 days.
[IMPORTANT] Farm Harvest: Machine 0 has collected 1701070 g of APPLE harvest.
[IMPORTANT] Farm Machine: Machine 0 is finished and returns to the shed at 0.
[IMPORTANT] Farm Machine: Machine 0 unloads 1701070 g of APPLE harvest in the shed.
[IMPORTANT] Farm: Farm 0 finished its actions.
[IMPORTANT] Simulation Info: Simulation ended at tick 10.
[IMPORTANT] Simulation Info: Simulation statistics are calculated.
[IMPORTANT] Simulation Statistics: Farm 0 collected 1701070 g of harvest.
[IMPORTANT] Simulation Statistics: Total amount of POTATO harvested: 0 g.
[IMPORTANT] Simulation Statistics: Total amount of WHEAT harvested: 0 g.
[IMPORTANT] Simulation Statistics: Total amount of OAT harvested: 0 g.
[IMPORTANT] Simulation Statistics: Total amount of PUMPKIN harvested: 0 g.
[IMPORTANT] Simulation Statistics: Total amount of APPLE harvested: 1701070 g.
[IMPORTANT] Simulation Statistics: Total amount of GRAPE harvested: 0 g.
[IMPORTANT] Simulation Statistics: Total amount of ALMOND harvested: 0 g.
[IMPORTANT] Simulation Statistics: Total amount of CHERRY harvested: 0 g.
[IMPORTANT] Simulation Statistics: Total harvest estimate still in fields and plantations: 0 g."""

/**
 * aaaaaaa b
 */
class OverestimateHarvest : ExampleSystemTestExtension() {
    override val name = "OverestimateHarvest"
    override val description = "bee happy do be crazy"

    override val map = "basic_farm/onePlantationMap.json"
    override val farms = "basic_farm/onePlantationFarm.json"
    override val scenario = "basic_farm/onePlantationScenario.json"

    override val startYearTick = 8
    override val maxTicks = 10
    override val logLevel = "DEBUG"

    override suspend fun run() {
        val lines = LOG.split(Regex("\\n"))
        for (line in lines) {
            assertNextLine(line)
        }
    }
}
