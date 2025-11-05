package de.unisaarland.cs.se.selab.systemtest.selab25.basictests

import de.unisaarland.cs.se.selab.systemtest.selab25.utils.ExampleSystemTestExtension

private const val FARM_FINISH = "[IMPORTANT] Farm: Farm 0 finished its actions."

/**
 * We lost it man!
 */
class HarvestLost : ExampleSystemTestExtension() {
    override val name = "Wheat on edge"
    override val description = "Wheat is all lost"

    override val farms = "HarvestLost/HarvestLost_farm.json"
    override val scenario = "HarvestLost/HarvestLost_scenario.json"
    override val map = "HarvestLost/HarvestLost_map.json"

    override val logLevel = "DEBUG"
    override val maxTicks = 21
    override val startYearTick = 20

    override suspend fun run() {
        skipUntilString(FARM_FINISH) // t0
        skipUntilString(FARM_FINISH) // t1
        skipUntilString(FARM_FINISH) // t2
        assertNextLine("[INFO] Harvest Estimate: Harvest estimate on tile 1 changed to 960000 g of WHEAT.")
        skipUntilString(FARM_FINISH) // t3
        skipUntilString(FARM_FINISH) // t4
        assertNextLine("[IMPORTANT] Incident: Incident 1 of type BROKEN_MACHINE happened and affected tiles 12.")
        skipUntilString(FARM_FINISH) // t5
        assertNextLine("[DEBUG] Harvest Estimate: Required actions on tile 1 were not performed: WEEDING.")
        assertNextLine("[INFO] Harvest Estimate: Harvest estimate on tile 1 changed to 864000 g of WHEAT.")
        skipUntilString(FARM_FINISH) // t6
        skipUntilString(FARM_FINISH) // t7
        skipUntilString(FARM_FINISH) // t8
        skipUntilString(FARM_FINISH) // t9
        skipUntilString(FARM_FINISH) // t10
        assertNextLine("[IMPORTANT] Incident: Incident 2 of type BROKEN_MACHINE happened and affected tiles 12.")
        skipUntilString(FARM_FINISH) // t11
        assertNextLine("[DEBUG] Harvest Estimate: Required actions on tile 1 were not performed: WEEDING.")
        assertNextLine("[INFO] Harvest Estimate: Harvest estimate on tile 1 changed to 777600 g of WHEAT.")
        skipUntilString(FARM_FINISH) // t12
        skipUntilString(FARM_FINISH) // t13
        skipUntilString(FARM_FINISH) // t14
        skipUntilString(FARM_FINISH) // t15
        skipUntilString(FARM_FINISH) // t16
        skipUntilString(FARM_FINISH) // t17
        skipUntilString(FARM_FINISH) // t18
        skipUntilString(FARM_FINISH) // t19
        assertNextLine("[DEBUG] Harvest Estimate: Required actions on tile 1 were not performed: HARVESTING.")
        assertNextLine("[INFO] Harvest Estimate: Harvest estimate on tile 1 changed to 0 g of WHEAT.")
        skipUntilString(FARM_FINISH) // t20
        skipUntilString("[IMPORTANT] Simulation Info: Simulation statistics are calculated.")
        assertNextLine("[IMPORTANT] Simulation Statistics: Farm 0 collected 0 g of harvest.")
        assertNextLine("[IMPORTANT] Simulation Statistics: Total amount of POTATO harvested: 0 g.")
        assertNextLine("[IMPORTANT] Simulation Statistics: Total amount of WHEAT harvested: 0 g.")
    }
}
