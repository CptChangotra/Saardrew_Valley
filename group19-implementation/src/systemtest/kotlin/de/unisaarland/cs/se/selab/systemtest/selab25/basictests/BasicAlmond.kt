package de.unisaarland.cs.se.selab.systemtest.selab25.basictests

import de.unisaarland.cs.se.selab.systemtest.selab25.utils.ExampleSystemTestExtension
import de.unisaarland.cs.se.selab.utils.YearTick
private const val LOG = """[INFO] Initialization Info: map.json successfully parsed and validated.
[INFO] Initialization Info: farm.json successfully parsed and validated.
[INFO] Initialization Info: scenario.json successfully parsed and validated.
[INFO] Simulation Info: Simulation started at tick 21 within the year.

[INFO] Simulation Info: Tick 0 started at tick 21 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm Action: Machine 0 performs CUTTING on tile 22 for 4 days.
[IMPORTANT] Farm Action: Machine 0 performs CUTTING on tile 23 for 4 days.
[IMPORTANT] Farm Machine: Machine 0 is finished and returns to the shed at 0.
[IMPORTANT] Farm: Farm 0 finished its actions.
[INFO] Harvest Estimate: Harvest estimate on tile 22 changed to 800000 g of ALMOND.
[INFO] Harvest Estimate: Harvest estimate on tile 23 changed to 800000 g of ALMOND.
[INFO] Harvest Estimate: Harvest estimate on tile 24 changed to 800000 g of ALMOND.

[INFO] Simulation Info: Tick 1 started at tick 22 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 0 finished its actions.

[INFO] Simulation Info: Tick 2 started at tick 23 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 0 finished its actions.

[INFO] Simulation Info: Tick 3 started at tick 24 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 0 finished its actions.

[INFO] Simulation Info: Tick 4 started at tick 1 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 0 finished its actions.

[INFO] Simulation Info: Tick 5 started at tick 2 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 0 finished its actions.

[INFO] Simulation Info: Tick 6 started at tick 3 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 0 finished its actions.

[INFO] Simulation Info: Tick 7 started at tick 4 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 0 finished its actions.
[DEBUG] Harvest Estimate: Required actions on tile 24 were not performed: CUTTING.
[INFO] Harvest Estimate: Harvest estimate on tile 24 changed to 400000 g of ALMOND.

[INFO] Simulation Info: Tick 8 started at tick 5 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 0 finished its actions.

[INFO] Simulation Info: Tick 9 started at tick 6 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 0 finished its actions.

[INFO] Simulation Info: Tick 10 started at tick 7 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 0 finished its actions.

[INFO] Simulation Info: Tick 11 started at tick 8 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 0 finished its actions.

[INFO] Simulation Info: Tick 12 started at tick 9 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 0 finished its actions.
[INFO] Harvest Estimate: Harvest estimate on tile 22 changed to 720000 g of ALMOND.
[INFO] Harvest Estimate: Harvest estimate on tile 23 changed to 720000 g of ALMOND.
[INFO] Harvest Estimate: Harvest estimate on tile 24 changed to 360000 g of ALMOND.

[INFO] Simulation Info: Tick 13 started at tick 10 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 0 finished its actions.
[INFO] Harvest Estimate: Harvest estimate on tile 22 changed to 648000 g of ALMOND.
[INFO] Harvest Estimate: Harvest estimate on tile 23 changed to 648000 g of ALMOND.
[INFO] Harvest Estimate: Harvest estimate on tile 24 changed to 324000 g of ALMOND.

[INFO] Simulation Info: Tick 14 started at tick 11 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm Action: Machine 0 performs MOWING on tile 22 for 4 days.
[IMPORTANT] Farm Action: Machine 0 performs MOWING on tile 23 for 4 days.
[IMPORTANT] Farm Machine: Machine 0 is finished and returns to the shed at 0.
[IMPORTANT] Farm: Farm 0 finished its actions.
[INFO] Harvest Estimate: Harvest estimate on tile 22 changed to 583200 g of ALMOND.
[INFO] Harvest Estimate: Harvest estimate on tile 23 changed to 583200 g of ALMOND.
[DEBUG] Harvest Estimate: Required actions on tile 24 were not performed: MOWING.
[INFO] Harvest Estimate: Harvest estimate on tile 24 changed to 262440 g of ALMOND.

[INFO] Simulation Info: Tick 15 started at tick 12 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 0 finished its actions.
[INFO] Harvest Estimate: Harvest estimate on tile 22 changed to 524880 g of ALMOND.
[INFO] Harvest Estimate: Harvest estimate on tile 23 changed to 524880 g of ALMOND.
[INFO] Harvest Estimate: Harvest estimate on tile 24 changed to 236196 g of ALMOND.

[INFO] Simulation Info: Tick 16 started at tick 13 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 0 finished its actions.
[INFO] Harvest Estimate: Harvest estimate on tile 22 changed to 472392 g of ALMOND.
[INFO] Harvest Estimate: Harvest estimate on tile 23 changed to 472392 g of ALMOND.
[INFO] Harvest Estimate: Harvest estimate on tile 24 changed to 212576 g of ALMOND.

[INFO] Simulation Info: Tick 17 started at tick 14 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 0 finished its actions.
[INFO] Harvest Estimate: Harvest estimate on tile 22 changed to 425152 g of ALMOND.
[INFO] Harvest Estimate: Harvest estimate on tile 23 changed to 425152 g of ALMOND.
[INFO] Harvest Estimate: Harvest estimate on tile 24 changed to 191318 g of ALMOND.
[INFO] Simulation Info: Tick 18 started at tick 15 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 0 finished its actions.

[INFO] Simulation Info: Tick 19 started at tick 16 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm Action: Machine 2 performs HARVESTING on tile 22 for 10 days.
[IMPORTANT] Farm Harvest: Machine 2 has collected 425152 g of ALMOND harvest.
[IMPORTANT] Farm Machine: Machine 2 is finished and returns to the shed at 0.
[IMPORTANT] Farm Machine: Machine 2 unloads 425152 g of ALMOND harvest in the shed.
[IMPORTANT] Farm: Farm 0 finished its actions.

[INFO] Simulation Info: Tick 20 started at tick 17 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm Action: Machine 2 performs HARVESTING on tile 23 for 10 days.
[IMPORTANT] Farm Harvest: Machine 2 has collected 425152 g of ALMOND harvest.
[IMPORTANT] Farm Machine: Machine 2 is finished and returns to the shed at 0.
[IMPORTANT] Farm Machine: Machine 2 unloads 425152 g of ALMOND harvest in the shed.
[IMPORTANT] Farm: Farm 0 finished its actions.
[DEBUG] Harvest Estimate: Required actions on tile 24 were not performed: MOWING.
[INFO] Harvest Estimate: Harvest estimate on tile 24 changed to 172186 g of ALMOND.

[INFO] Simulation Info: Tick 21 started at tick 18 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 0 finished its actions.

[INFO] Simulation Info: Tick 22 started at tick 19 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 0 finished its actions.
[DEBUG] Harvest Estimate: Required actions on tile 24 were not performed: HARVESTING,HARVESTING.
[INFO] Harvest Estimate: Harvest estimate on tile 24 changed to 154967 g of ALMOND.

[INFO] Simulation Info: Tick 23 started at tick 20 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 0 finished its actions.
[DEBUG] Harvest Estimate: Required actions on tile 24 were not performed: HARVESTING,HARVESTING.
[INFO] Harvest Estimate: Harvest estimate on tile 24 changed to 0 g of ALMOND.

[IMPORTANT] Simulation Info: Simulation ended at tick 24.
[IMPORTANT] Simulation Info: Simulation statistics are calculated.
[IMPORTANT] Simulation Statistics: Farm 0 collected 850304 g of harvest.
[IMPORTANT] Simulation Statistics: Total amount of POTATO harvested: 0 g.
[IMPORTANT] Simulation Statistics: Total amount of WHEAT harvested: 0 g.
[IMPORTANT] Simulation Statistics: Total amount of OAT harvested: 0 g.
[IMPORTANT] Simulation Statistics: Total amount of PUMPKIN harvested: 0 g.
[IMPORTANT] Simulation Statistics: Total amount of APPLE harvested: 0 g.
[IMPORTANT] Simulation Statistics: Total amount of GRAPE harvested: 0 g.
[IMPORTANT] Simulation Statistics: Total amount of ALMOND harvested: 850304 g.
[IMPORTANT] Simulation Statistics: Total amount of CHERRY harvested: 0 g.
[IMPORTANT] Simulation Statistics: Total harvest estimate still in fields and plantations: 0 g."""
private const val NEWL = "\\n"

/**
 * test almond full simulation with late harvest
 */
class BasicAlmond(private val chunk: Int) : ExampleSystemTestExtension() {
    override val name = "almondFarm ${if (chunk == 0) "init" else (chunk - 1).toString()}"
    override val description = "AlmondFarm test for tick ${if (chunk == 0) "init" else (chunk - 1).toString()}"

    override val farms = "AlmondFarm/farm.json"
    override val scenario = "example/scenario.json"
    override val map = "AlmondFarm/map.json"

    override val logLevel = "DEBUG"
    override val maxTicks = 24
    override val startYearTick = YearTick.NOVEMBER_1

    override suspend fun run() {
        val chunks = LOG.split(Regex("\\n\\s*\\n"))
        val lines = chunks[chunk].split(Regex(NEWL))
        skipUntilString(lines.first())
        for (line in lines.drop(1)) {
            assertNextLine(line)
        }
    }
}
