package de.unisaarland.cs.se.selab.systemtest.selab25.basictests

import de.unisaarland.cs.se.selab.systemtest.selab25.utils.ExampleSystemTestExtension

private const val MACHINE1SHED = "[IMPORTANT] Farm Machine: Machine 1 is finished and returns to the shed at 64."
private const val MACHINE0SHED = "[IMPORTANT] Farm Machine: Machine 0 is finished and returns to the shed at 64."
private const val WEEDING54 = "[DEBUG] Harvest Estimate: Required actions on tile 54 were not performed: WEEDING."
private const val SOIL = "[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles."
private const val FARM_START = "[IMPORTANT] Farm: Farm 0 starts its actions."
private const val FARM_FINISH = "[IMPORTANT] Farm: Farm 0 finished its actions."
private const val PLAN1 = "[DEBUG] Farm: Farm 0 has the following active sowing " +
    "plans it intends to pursue in this tick: 1."

/**
 * Base class for harvest cycle tests from the log
 */
abstract class SimulationTests : ExampleSystemTestExtension() {
    override val description = "System test based on simulation log"
    override val map = "example/simulationMap.json"
    override val farms = "example/simulationFarms.json"
    override val scenario = "example/scenario.json"
    override val logLevel = "DEBUG"
    override val maxTicks = 25
    override val startYearTick = 1
}

/**
 * Tick 0
 */
class HarvestCycleTick0To4 : SimulationTests() {
    override val name = "HarvestCycle Tick 0-4"
    override suspend fun run() {
        skipUntilString("[INFO] Simulation Info: Simulation started at tick 1 within the year.")
        assertNextLine("[INFO] Simulation Info: Tick 0 started at tick 1 within the year.")
        // SOIL MOISTURE IS LEFT ONLY HERE
        assertNextLine(SOIL)
        assertNextLine(FARM_START)
        assertNextLine(
            "[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: ."
        )
        assertNextLine(FARM_FINISH)
        assertNextLine("[INFO] Harvest Estimate: Harvest estimate on tile 41 changed to 1530000 g of APPLE.")
        assertNextLine("[INFO] Simulation Info: Tick 1 started at tick 2 within the year.")
        assertNextLine(SOIL)
        assertNextLine(FARM_START)
        assertNextLine(
            "[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: ."
        )
        assertNextLine(FARM_FINISH)
        assertNextLine("[INFO] Harvest Estimate: Harvest estimate on tile 41 changed to 1377000 g of APPLE.")
        assertNextLine("[INFO] Simulation Info: Tick 2 started at tick 3 within the year.")
        assertNextLine(SOIL)
        assertNextLine(FARM_START)
        assertNextLine(
            PLAN1
        )
        assertNextLine("[IMPORTANT] Farm Action: Machine 0 performs CUTTING on tile 40 for 5 days.")
        assertNextLine("[IMPORTANT] Farm Action: Machine 0 performs CUTTING on tile 41 for 5 days.")
        assertNextLine(MACHINE0SHED)
        assertNextLine(FARM_FINISH)
        assertNextLine("[INFO] Harvest Estimate: Harvest estimate on tile 41 changed to 1115370 g of APPLE.")
        assertNextLine("[INFO] Simulation Info: Tick 3 started at tick 4 within the year.")
        assertNextLine(SOIL)
        assertNextLine(FARM_START)
        assertNextLine(
            PLAN1
        )
        assertNextLine(FARM_FINISH)
        assertNextLine("[INFO] Harvest Estimate: Harvest estimate on tile 41 changed to 903449 g of APPLE.")
        assertNextLine("[INFO] Simulation Info: Tick 4 started at tick 5 within the year.")
        assertNextLine(SOIL)
        assertNextLine(FARM_START)
        assertNextLine(
            PLAN1
        )
        assertNextLine(FARM_FINISH)
        assertNextLine("[INFO] Harvest Estimate: Harvest estimate on tile 41 changed to 658613 g of APPLE.")
    }
}

/**
 * Tick 1
 */
class HarvestCycleTick5To10 : SimulationTests() {
    override val name = "HarvestCycle Tick 5-10"
    override suspend fun run() {
        skipUntilString("[INFO] Simulation Info: Tick 5 started at tick 6 within the year.")
        skipUntilString("[INFO] Harvest Estimate: Harvest estimate on tile 41 changed to 480127 g of APPLE.")

        skipUntilString("[INFO] Simulation Info: Tick 6 started at tick 7 within the year.")
        skipUntilString("[INFO] Harvest Estimate: Harvest estimate on tile 41 changed to 350011 g of APPLE.")
        assertNextLine("[DEBUG] Harvest Estimate: Required actions on tile 42 were not performed: MOWING.")
        assertNextLine("[INFO] Harvest Estimate: Harvest estimate on tile 42 changed to 1080000 g of GRAPE.")

        assertNextLine("[INFO] Simulation Info: Tick 7 started at tick 8 within the year.")
        skipUntilString("[INFO] Harvest Estimate: Harvest estimate on tile 41 changed to 255157 g of APPLE.")

        assertNextLine("[INFO] Simulation Info: Tick 8 started at tick 9 within the year.")
        skipUntilString("[INFO] Harvest Estimate: Harvest estimate on tile 40 changed to 1080000 g of CHERRY.")
        assertNextLine("[INFO] Harvest Estimate: Harvest estimate on tile 41 changed to 167407 g of APPLE.")

        assertNextLine("[INFO] Simulation Info: Tick 9 started at tick 10 within the year.")
        skipUntilString("[IMPORTANT] Farm Action: Machine 1 performs SOWING on tile 54 for 5 days.")
        assertNextLine("[IMPORTANT] Farm Sowing: Machine 1 has sowed PUMPKIN according to sowing plan 1.")
        assertNextLine(MACHINE1SHED)
        assertNextLine(FARM_FINISH)
        assertNextLine("[INFO] Harvest Estimate: Harvest estimate on tile 40 changed to 972000 g of CHERRY.")
        assertNextLine("[INFO] Harvest Estimate: Harvest estimate on tile 41 changed to 109835 g of APPLE.")
        assertNextLine("[INFO] Harvest Estimate: Harvest estimate on tile 54 changed to 450000 g of PUMPKIN.")

        assertNextLine("[INFO] Simulation Info: Tick 10 started at tick 11 within the year.")
        skipUntilString("[DEBUG] Harvest Estimate: Required actions on tile 40 were not performed: MOWING.")
        assertNextLine("[INFO] Harvest Estimate: Harvest estimate on tile 40 changed to 787320 g of CHERRY.")
        assertNextLine("[DEBUG] Harvest Estimate: Required actions on tile 41 were not performed: MOWING.")
        assertNextLine("[INFO] Harvest Estimate: Harvest estimate on tile 41 changed to 64854 g of APPLE.")
        assertNextLine("[INFO] Harvest Estimate: Harvest estimate on tile 54 changed to 405000 g of PUMPKIN.")
    }
}

/**
 * Tick 2
 */
class HarvestCycleTick11To15 : SimulationTests() {
    override val name = "HarvestCycle Tick 11-15"
    override suspend fun run() {
        skipUntilString("[INFO] Simulation Info: Tick 11 started at tick 12 within the year.")
        skipUntilString("[IMPORTANT] Farm Action: Machine 1 performs SOWING on tile 69 for 5 days.")
        assertNextLine("[IMPORTANT] Farm Sowing: Machine 1 has sowed POTATO according to sowing plan 2.")
        assertNextLine(MACHINE1SHED)
        assertNextLine(FARM_FINISH)
        assertNextLine("[INFO] Harvest Estimate: Harvest estimate on tile 40 changed to 708588 g of CHERRY.")
        assertNextLine("[INFO] Harvest Estimate: Harvest estimate on tile 41 changed to 42549 g of APPLE.")
        assertNextLine(WEEDING54)
        assertNextLine("[INFO] Harvest Estimate: Harvest estimate on tile 54 changed to 328050 g of PUMPKIN.")
        assertNextLine("[INFO] Harvest Estimate: Harvest estimate on tile 69 changed to 576000 g of POTATO.")

        assertNextLine("[INFO] Simulation Info: Tick 12 started at tick 13 within the year.")
        skipUntilString("[IMPORTANT] Farm Action: Machine 0 performs HARVESTING on tile 40 for 5 days.")
        assertNextLine("[IMPORTANT] Farm Harvest: Machine 0 has collected 708588 g of CHERRY harvest.")
        assertNextLine(MACHINE0SHED)
        assertNextLine("[IMPORTANT] Farm Machine: Machine 0 unloads 708588 g of CHERRY harvest in the shed.")
        assertNextLine(FARM_FINISH)
        assertNextLine("[INFO] Harvest Estimate: Harvest estimate on tile 41 changed to 27915 g of APPLE.")
        assertNextLine("[DEBUG] Harvest Estimate: Required actions on tile 42 were not performed: MOWING.")
        assertNextLine("[INFO] Harvest Estimate: Harvest estimate on tile 42 changed to 972000 g of GRAPE.")
        assertNextLine("[INFO] Harvest Estimate: Harvest estimate on tile 54 changed to 295245 g of PUMPKIN.")
        assertNextLine("[INFO] Harvest Estimate: Harvest estimate on tile 69 changed to 518400 g of POTATO.")

        assertNextLine("[INFO] Simulation Info: Tick 13 started at tick 14 within the year.")
        skipUntilString("[IMPORTANT] Farm Action: Machine 0 performs CUTTING on tile 42 for 5 days.")
        assertNextLine(MACHINE0SHED)
        assertNextLine(FARM_FINISH)
        assertNextLine("[INFO] Harvest Estimate: Harvest estimate on tile 41 changed to 18314 g of APPLE.")
        assertNextLine(WEEDING54)
        assertNextLine("[INFO] Harvest Estimate: Harvest estimate on tile 54 changed to 239148 g of PUMPKIN.")
        assertNextLine("[DEBUG] Harvest Estimate: Required actions on tile 69 were not performed: WEEDING.")
        assertNextLine("[INFO] Harvest Estimate: Harvest estimate on tile 69 changed to 419904 g of POTATO.")

        assertNextLine("[INFO] Simulation Info: Tick 14 started at tick 15 within the year.")
        skipUntilString("[INFO] Harvest Estimate: Harvest estimate on tile 41 changed to 12014 g of APPLE.")
        assertNextLine("[INFO] Harvest Estimate: Harvest estimate on tile 54 changed to 215233 g of PUMPKIN.")

        assertNextLine("[INFO] Simulation Info: Tick 15 started at tick 16 within the year.")
        skipUntilString("[INFO] Harvest Estimate: Harvest estimate on tile 41 changed to 7881 g of APPLE.")
        assertNextLine(WEEDING54)
        assertNextLine("[INFO] Harvest Estimate: Harvest estimate on tile 54 changed to 174338 g of PUMPKIN.")
        assertNextLine("[DEBUG] Harvest Estimate: Required actions on tile 69 were not performed: WEEDING.")
        assertNextLine("[INFO] Harvest Estimate: Harvest estimate on tile 69 changed to 377913 g of POTATO.")
    }
}

/**
 * Tick 3
 */
class HarvestCycleTick16To20 : SimulationTests() {
    override val name = "HarvestCycle Tick 16-20"
    override suspend fun run() {
        skipUntilString("[INFO] Simulation Info: Tick 16 started at tick 17 within the year.")
        skipUntilString("[IMPORTANT] Farm Action: Machine 0 performs HARVESTING on tile 41 for 5 days.")
        assertNextLine("[IMPORTANT] Farm Harvest: Machine 0 has collected 7881 g of APPLE harvest.")
        assertNextLine(MACHINE0SHED)
        assertNextLine("[IMPORTANT] Farm Machine: Machine 0 unloads 7881 g of APPLE harvest in the shed.")
        assertNextLine("[IMPORTANT] Farm Action: Machine 1 performs HARVESTING on tile 54 for 5 days.")
        assertNextLine("[IMPORTANT] Farm Harvest: Machine 1 has collected 174338 g of PUMPKIN harvest.")
        assertNextLine(MACHINE1SHED)
        assertNextLine("[IMPORTANT] Farm Machine: Machine 1 unloads 174338 g of PUMPKIN harvest in the shed.")
        assertNextLine(FARM_FINISH)
        assertNextLine("[DEBUG] Harvest Estimate: Required actions on tile 42 were not performed: HARVESTING.")
        assertNextLine("[INFO] Harvest Estimate: Harvest estimate on tile 42 changed to 923400 g of GRAPE.")

        assertNextLine("[INFO] Simulation Info: Tick 17 started at tick 18 within the year.")
        skipUntilString("[IMPORTANT] Farm Action: Machine 0 performs HARVESTING on tile 42 for 5 days.")
        assertNextLine("[IMPORTANT] Farm Harvest: Machine 0 has collected 923400 g of GRAPE harvest.")
        assertNextLine(MACHINE0SHED)
        assertNextLine("[IMPORTANT] Farm Machine: Machine 0 unloads 923400 g of GRAPE harvest in the shed.")
        assertNextLine("[IMPORTANT] Farm Action: Machine 1 performs HARVESTING on tile 69 for 5 days.")
        assertNextLine("[IMPORTANT] Farm Harvest: Machine 1 has collected 377913 g of POTATO harvest.")
        assertNextLine(MACHINE1SHED)
        assertNextLine("[IMPORTANT] Farm Machine: Machine 1 unloads 377913 g of POTATO harvest in the shed.")
        assertNextLine(FARM_FINISH)

        skipUntilString("[INFO] Simulation Info: Tick 20 started at tick 21 within the year.")
        skipUntilString(
            "[DEBUG] Farm: Farm 0 has the following active sowing " +
                "plans it intends to pursue in this tick: 3."
        )
        assertNextLine("[IMPORTANT] Farm Action: Machine 1 performs SOWING on tile 84 for 5 days.")
        assertNextLine("[IMPORTANT] Farm Sowing: Machine 1 has sowed WHEAT according to sowing plan 3.")
        assertNextLine(MACHINE1SHED)
        assertNextLine("[IMPORTANT] Farm Action: Machine 0 performs CUTTING on tile 40 for 5 days.")
        assertNextLine("[IMPORTANT] Farm Action: Machine 0 performs CUTTING on tile 41 for 5 days.")
        assertNextLine(MACHINE0SHED)
        assertNextLine(FARM_FINISH)
        assertNextLine("[INFO] Harvest Estimate: Harvest estimate on tile 40 changed to 1200000 g of CHERRY.")
        assertNextLine("[INFO] Harvest Estimate: Harvest estimate on tile 41 changed to 1530000 g of APPLE.")
        assertNextLine("[INFO] Harvest Estimate: Harvest estimate on tile 42 changed to 1200000 g of GRAPE.")
        assertNextLine("[INFO] Harvest Estimate: Harvest estimate on tile 84 changed to 1200000 g of WHEAT.")
    }
}
