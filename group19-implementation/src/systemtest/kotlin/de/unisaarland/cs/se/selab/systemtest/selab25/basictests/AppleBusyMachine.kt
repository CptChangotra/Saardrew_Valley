package de.unisaarland.cs.se.selab.systemtest.selab25.basictests

import de.unisaarland.cs.se.selab.systemtest.selab25.utils.ExampleSystemTestExtension
import de.unisaarland.cs.se.selab.utils.YearTick

private const val CLOUD_CREATION = "[IMPORTANT] Incident: Incident 222 of type CLOUD_CREATION " +
    "happened and affected tiles 22,23,24,31."
private const val FARM_START_STR = "[IMPORTANT] Farm: Farm 0 starts its actions."
private const val FARM_SOWING_PLAN = "[DEBUG] Farm: Farm 0 has the following active sowing plans " +
    "it intends to pursue in this tick: ."
private const val SOIL_MOISTURE = "[INFO] Soil Moisture: The soil moisture is below threshold in " +
    "0 FIELD and 0 PLANTATION tiles."
private const val MACHINE_0_MOW_22 = "[IMPORTANT] Farm Action: Machine 0 performs MOWING on " +
    "tile 22 for 4 days."
private const val MACHINE_0_MOW_23 = "[IMPORTANT] Farm Action: Machine 0 performs MOWING on " +
    "tile 23 for 4 days."
private const val MACHINE_0_RETURN = "[IMPORTANT] Farm Machine: Machine 0 is finished and returns to the shed at 0."
private const val MACHINE_2_HARVEST_22 = "[IMPORTANT] Farm Action: Machine 2 performs HARVESTING on " +
    "tile 22 for 12 days."
private const val MACHINE_2_HARVEST_23 = "[IMPORTANT] Farm Action: Machine 2 performs HARVESTING on " +
    "tile 23 for 12 days."
private const val MACHINE_2_RETURN = "[IMPORTANT] Farm Machine: Machine 2 is finished and returns to the shed at 0."
private const val MACHINE_2_UNLOAD_22 = "[IMPORTANT] Farm Machine: Machine 2 unloads 167407 g of APPLE " +
    "harvest in the shed."
private const val MACHINE_2_UNLOAD_23 = "[IMPORTANT] Farm Machine: Machine 2 unloads 150666 g of APPLE " +
    "harvest in the shed."
private const val FARM_FINISHED_ACTIONS = "[IMPORTANT] Farm: Farm 0 finished its actions."

/**
 * Tests a cycle of Apple
 */
class AppleBusyMachine : ExampleSystemTestExtension() {
    override val name = "AppleBusyMachine"
    override val description = "Tests Apple with machine busy harvesting"

    override val map = "basicApple/appleMap.json"
    override val farms = "basicApple/appleBusyMachine.json"
    override val scenario = "basicApple/appleScenario.json"

    override val logLevel = "DEBUG"
    override val maxTicks = 21
    override val startYearTick = YearTick.DECEMBER_1

    override suspend fun run() {
        // Apply sunlightPenalty in Dec1 and Dec2
        skipUntilString(CLOUD_CREATION)
        assertNextLine("[INFO] Harvest Estimate: Harvest estimate on tile 22 changed to 1377000 g of APPLE.")
        // Feb1 - Feb2, cutting, missed on tile 24
        skipUntilString("[INFO] Simulation Info: Tick 5 started at tick 4 within the year.")
        assertNextLine(SOIL_MOISTURE)
        skipUntilString(FARM_START_STR)
        assertNextLine(FARM_SOWING_PLAN)
        assertNextLine(FARM_FINISHED_ACTIONS)
        assertNextLine("[DEBUG] Harvest Estimate: Required actions on tile 24 were not performed: CUTTING.")
        assertNextLine("[INFO] Harvest Estimate: Harvest estimate on tile 24 changed to 688500 g of APPLE.")

        // June1 (YearTick 12) mowing (tile 24 not reachable)
        skipUntilString("[INFO] Simulation Info: Tick 12 started at tick 11 within the year.")
        assertNextLine(SOIL_MOISTURE)
        skipUntilString(FARM_START_STR)
        assertNextLine(FARM_SOWING_PLAN)
        assertNextLine(MACHINE_0_MOW_22)
        assertNextLine(MACHINE_0_MOW_23)
        assertNextLine(MACHINE_0_RETURN)
        assertNextLine(FARM_FINISHED_ACTIONS)
        assertNextLine("[INFO] Harvest Estimate: Harvest estimate on tile 22 changed to 480127 g of APPLE.")
        assertNextLine("[INFO] Harvest Estimate: Harvest estimate on tile 23 changed to 480127 g of APPLE.")
        assertNextLine("[DEBUG] Harvest Estimate: Required actions on tile 24 were not performed: MOWING.")
        assertNextLine("[INFO] Harvest Estimate: Harvest estimate on tile 24 changed to 216055 g of APPLE.")
        // !! above line doesn't exist, correctly applying the mowing missed penalty

        // Sep1 (tick 18, yearTick 17): harvest on 22, mow on 23
        // harvestDuration = 12, so machine 2 can't harvest tile 23 at this tick
        skipUntilString("[INFO] Simulation Info: Tick 18 started at tick 17 within the year.")
        assertNextLine(SOIL_MOISTURE)
        skipUntilString(FARM_START_STR)
        assertNextLine(FARM_SOWING_PLAN)
        assertNextLine(MACHINE_2_HARVEST_22)
        assertNextLine("[IMPORTANT] Farm Harvest: Machine 2 has collected 167407 g of APPLE harvest.")
        assertNextLine(MACHINE_2_RETURN)
        assertNextLine(MACHINE_2_UNLOAD_22)
        assertNextLine(MACHINE_0_MOW_23)
        assertNextLine(MACHINE_0_RETURN)
        assertNextLine(FARM_FINISHED_ACTIONS)
        assertNextLine("[INFO] Harvest Estimate: Harvest estimate on tile 23 changed to 150666 g of APPLE.")
        assertNextLine("[DEBUG] Harvest Estimate: Required actions on tile 24 were not performed: MOWING.")
        assertNextLine("[INFO] Harvest Estimate: Harvest estimate on tile 24 changed to 61017 g of APPLE.")
        // !! above line doesn't exist, correctly applying the mowing missed penalty

        // Sep2 (tick 19, yearTick 18): harvest on 23
        skipUntilString(FARM_START_STR)
        assertNextLine(FARM_SOWING_PLAN)
        assertNextLine(MACHINE_2_HARVEST_23)
        assertNextLine("[IMPORTANT] Farm Harvest: Machine 2 has collected 150666 g of APPLE harvest.")
        assertNextLine(MACHINE_2_RETURN)
        // skipUntilString(MACHINE_2_RETURN)
        assertNextLine(MACHINE_2_UNLOAD_23)
        assertNextLine(FARM_FINISHED_ACTIONS)

        // Oct1 (tick 20, yearTick 19):
        // harvest on 24 is not performed (not reachable), reduced half
        skipUntilString(FARM_START_STR)
        assertNextLine(FARM_SOWING_PLAN)
        assertNextLine(FARM_FINISHED_ACTIONS)
        assertNextLine("[DEBUG] Harvest Estimate: Required actions on tile 24 were not performed: HARVESTING.")

//        // stats
//        skipUntilString("[IMPORTANT] Simulation Info: Simulation statistics are calculated.")
//        assertNextLine("[IMPORTANT] Simulation Statistics: Farm 0 collected 318073 g of harvest.")
    }
}
