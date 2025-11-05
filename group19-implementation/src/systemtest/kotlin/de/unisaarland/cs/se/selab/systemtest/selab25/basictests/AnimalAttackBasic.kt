package de.unisaarland.cs.se.selab.systemtest.selab25.basictests

import de.unisaarland.cs.se.selab.systemtest.selab25.utils.ExampleSystemTestExtension

private const val LOG = """
[INFO] Simulation Info: Simulation started at tick 9 within the year.
[INFO] Simulation Info: Tick 0 started at tick 9 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[INFO] Cloud Movement: Cloud 2 with 1000 L water moved from tile 2 to tile 10.
[DEBUG] Cloud Movement: On tile 2, the amount of sunlight is 165.
[INFO] Cloud Movement: Cloud 3 with 1000 L water moved from tile 3 to tile 6.
[DEBUG] Cloud Movement: On tile 3, the amount of sunlight is 165.
[DEBUG] Cloud Position: Cloud 1 is on tile 0, where the amount of sunlight is 118.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: 0.
[IMPORTANT] Farm Action: Machine 1 performs SOWING on tile 4 for 2 days.
[IMPORTANT] Farm Sowing: Machine 1 has sowed POTATO according to sowing plan 0.
[IMPORTANT] Farm Machine: Machine 1 is finished and returns to the shed at 16.
[IMPORTANT] Farm: Farm 0 finished its actions.
[IMPORTANT] Incident: Incident 1 of type ANIMAL_ATTACK happened and affected tiles 2,4,8,9.
[INFO] Harvest Estimate: Harvest estimate on tile 2 changed to 1003833 g of APPLE.
[INFO] Harvest Estimate: Harvest estimate on tile 3 changed to 1080000 g of CHERRY.
[INFO] Harvest Estimate: Harvest estimate on tile 4 changed to 450000 g of POTATO.
[INFO] Harvest Estimate: Harvest estimate on tile 9 changed to 648000 g of ALMOND.
[INFO] Harvest Estimate: Harvest estimate on tile 11 changed to 720000 g of ALMOND.
[INFO] Simulation Info: Tick 1 started at tick 10 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[INFO] Cloud Dissipation: Cloud 1 dissipates on tile 0.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 0 finished its actions.
[IMPORTANT] Incident: Incident 2 of type BEE_HAPPY happened and affected tiles .
[INFO] Harvest Estimate: Harvest estimate on tile 2 changed to 658613 g of APPLE.
[INFO] Harvest Estimate: Harvest estimate on tile 3 changed to 972000 g of CHERRY.
[INFO] Harvest Estimate: Harvest estimate on tile 4 changed to 405000 g of POTATO.
[INFO] Harvest Estimate: Harvest estimate on tile 9 changed to 583200 g of ALMOND.
[INFO] Harvest Estimate: Harvest estimate on tile 11 changed to 648000 g of ALMOND.
[INFO] Simulation Info: Tick 2 started at tick 11 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[INFO] Cloud Dissipation: Cloud 3 dissipates on tile 6.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm Action: Machine 1 performs WEEDING on tile 4 for 2 days.
[IMPORTANT] Farm Machine: Machine 1 is finished and returns to the shed at 16.
[IMPORTANT] Farm: Farm 0 finished its actions.
[DEBUG] Harvest Estimate: Required actions on tile 2 were not performed: MOWING.
[INFO] Harvest Estimate: Harvest estimate on tile 2 changed to 388902 g of APPLE.
[DEBUG] Harvest Estimate: Required actions on tile 3 were not performed: MOWING.
[INFO] Harvest Estimate: Harvest estimate on tile 3 changed to 787320 g of CHERRY.
[INFO] Harvest Estimate: Harvest estimate on tile 4 changed to 364500 g of POTATO.
[DEBUG] Harvest Estimate: Required actions on tile 9 were not performed: MOWING.
[INFO] Harvest Estimate: Harvest estimate on tile 9 changed to 472392 g of ALMOND.
[DEBUG] Harvest Estimate: Required actions on tile 11 were not performed: MOWING.
[INFO] Harvest Estimate: Harvest estimate on tile 11 changed to 524880 g of ALMOND.
[IMPORTANT] Simulation Info: Simulation ended at tick 3.
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
[IMPORTANT] Simulation Statistics: Total harvest estimate still in fields and plantations: 3737994 g.
"""

/**
 * System Test for Animal Attack
 */
class AnimalAttackBasic : ExampleSystemTestExtension() {
    override val name = "Animal Attack Basic"
    override val description = "Test for Animal Attack"

    override val map = "DroughtThenBeeHappy/IncidentMix_AnimalAttackMap.json"
    override val farms = "DroughtThenBeeHappy/IncidentMix_AnimalAttackFarm.json"
    override val scenario = "DroughtThenBeeHappy/IncidentMix_AnimalAttackScenario.json"

    override val logLevel = "DEBUG"
    override val maxTicks = 3
    override val startYearTick = 9

    override suspend fun run() {
        val logLines = LOG.trim().lines()
        val startIdx = logLines.indexOf("[INFO] Simulation Info: Simulation started at tick 9 within the year.")
        require(startIdx != -1) { "Start line not found in LOG." }
        skipUntilString(
            "[INFO] Initialization Info: IncidentMix_AnimalAttackScenario.json successfully parsed and validated."
        )
        for (i in startIdx until logLines.size) {
            assertNextLine(logLines[i])
        }
    }
}
