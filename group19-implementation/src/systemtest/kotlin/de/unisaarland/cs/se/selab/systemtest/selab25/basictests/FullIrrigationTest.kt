package de.unisaarland.cs.se.selab.systemtest.selab25.basictests

import de.unisaarland.cs.se.selab.systemtest.selab25.utils.ExampleSystemTestExtension

const val OUTPUT_TICK_0 = """[INFO] Simulation Info: Tick 0 started at tick 19 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 2 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: 0.
[IMPORTANT] Farm Action: Machine 0 performs SOWING on tile 1 for 1 days.
[IMPORTANT] Farm Sowing: Machine 0 has sowed WHEAT according to sowing plan 0.
[IMPORTANT] Farm Action: Machine 0 performs SOWING on tile 2 for 1 days.
[IMPORTANT] Farm Sowing: Machine 0 has sowed WHEAT according to sowing plan 0.
[IMPORTANT] Farm Action: Machine 0 performs SOWING on tile 4 for 1 days.
[IMPORTANT] Farm Sowing: Machine 0 has sowed WHEAT according to sowing plan 0.
[IMPORTANT] Farm Machine: Machine 0 is finished and returns to the shed at 0.
[IMPORTANT] Farm Action: Machine 1 performs IRRIGATING on tile 3 for 1 days.
[IMPORTANT] Farm Action: Machine 1 performs IRRIGATING on tile 5 for 1 days.
[IMPORTANT] Farm Machine: Machine 1 is finished and returns to the shed at 0.
[IMPORTANT] Farm: Farm 0 finished its actions.
[DEBUG] Harvest Estimate: Required actions on tile 1 were not performed: IRRIGATING.
[INFO] Harvest Estimate: Harvest estimate on tile 1 changed to 1499800 g of WHEAT.
[DEBUG] Harvest Estimate: Required actions on tile 2 were not performed: IRRIGATING.
[INFO] Harvest Estimate: Harvest estimate on tile 2 changed to 1499800 g of WHEAT.
[DEBUG] Harvest Estimate: Required actions on tile 3 were not performed: HARVESTING.
[INFO] Harvest Estimate: Harvest estimate on tile 3 changed to 688500 g of APPLE.
[DEBUG] Harvest Estimate: Required actions on tile 4 were not performed: IRRIGATING.
[INFO] Harvest Estimate: Harvest estimate on tile 4 changed to 1499800 g of WHEAT."""

const val OUTPUT_TICK_1 = """[INFO] Simulation Info: Tick 1 started at tick 20 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 3 FIELD and 2 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm Action: Machine 1 performs IRRIGATING on tile 1 for 1 days.
[IMPORTANT] Farm Action: Machine 1 performs IRRIGATING on tile 2 for 1 days.
[IMPORTANT] Farm Action: Machine 1 performs IRRIGATING on tile 4 for 1 days.
[IMPORTANT] Farm Action: Machine 1 performs IRRIGATING on tile 3 for 1 days.
[IMPORTANT] Farm Action: Machine 1 performs IRRIGATING on tile 5 for 1 days.
[IMPORTANT] Farm Machine: Machine 1 is finished and returns to the shed at 0.
[IMPORTANT] Farm: Farm 0 finished its actions.
[DEBUG] Harvest Estimate: Required actions on tile 1 were not performed: IRRIGATING.
[INFO] Harvest Estimate: Harvest estimate on tile 1 changed to 1499650 g of WHEAT.
[DEBUG] Harvest Estimate: Required actions on tile 2 were not performed: IRRIGATING.
[INFO] Harvest Estimate: Harvest estimate on tile 2 changed to 1499650 g of WHEAT.
[DEBUG] Harvest Estimate: Required actions on tile 3 were not performed: HARVESTING.
[INFO] Harvest Estimate: Harvest estimate on tile 3 changed to 0 g of APPLE.
[DEBUG] Harvest Estimate: Required actions on tile 4 were not performed: IRRIGATING.
[INFO] Harvest Estimate: Harvest estimate on tile 4 changed to 1499650 g of WHEAT."""

const val OUTPUT_TICK_2 = """[INFO] Simulation Info: Tick 2 started at tick 21 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 3 FIELD and 2 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm Action: Machine 1 performs IRRIGATING on tile 1 for 1 days.
[IMPORTANT] Farm Action: Machine 1 performs IRRIGATING on tile 2 for 1 days.
[IMPORTANT] Farm Action: Machine 1 performs IRRIGATING on tile 4 for 1 days.
[IMPORTANT] Farm Action: Machine 1 performs IRRIGATING on tile 3 for 1 days.
[IMPORTANT] Farm Action: Machine 1 performs IRRIGATING on tile 5 for 1 days.
[IMPORTANT] Farm Machine: Machine 1 is finished and returns to the shed at 0.
[IMPORTANT] Farm: Farm 0 finished its actions.
[DEBUG] Harvest Estimate: Required actions on tile 1 were not performed: IRRIGATING.
[INFO] Harvest Estimate: Harvest estimate on tile 1 changed to 1499500 g of WHEAT.
[DEBUG] Harvest Estimate: Required actions on tile 2 were not performed: IRRIGATING.
[INFO] Harvest Estimate: Harvest estimate on tile 2 changed to 1499500 g of WHEAT.
[INFO] Harvest Estimate: Harvest estimate on tile 3 changed to 1530000 g of APPLE.
[DEBUG] Harvest Estimate: Required actions on tile 4 were not performed: IRRIGATING.
[INFO] Harvest Estimate: Harvest estimate on tile 4 changed to 1499500 g of WHEAT."""

const val OUTPUT_TICK_3 = """[INFO] Soil Moisture: The soil moisture is below threshold in 3 FIELD and 2 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm Action: Machine 1 performs IRRIGATING on tile 1 for 1 days.
[IMPORTANT] Farm Action: Machine 1 performs IRRIGATING on tile 2 for 1 days.
[IMPORTANT] Farm Action: Machine 1 performs IRRIGATING on tile 4 for 1 days.
[IMPORTANT] Farm Action: Machine 1 performs IRRIGATING on tile 3 for 1 days.
[IMPORTANT] Farm Action: Machine 1 performs IRRIGATING on tile 5 for 1 days.
[IMPORTANT] Farm Machine: Machine 1 is finished and returns to the shed at 0.
[IMPORTANT] Farm: Farm 0 finished its actions."""

const val OUTPUT_TICK_3_ESTIMATIONS = """[DEBUG] Harvest Estimate: Required actions on tile 1 were not performed: WEEDING,IRRIGATING.
[INFO] Harvest Estimate: Harvest estimate on tile 1 changed to 1349415 g of WHEAT.
[DEBUG] Harvest Estimate: Required actions on tile 2 were not performed: WEEDING,IRRIGATING.
[INFO] Harvest Estimate: Harvest estimate on tile 2 changed to 1349415 g of WHEAT.
[INFO] Harvest Estimate: Harvest estimate on tile 3 changed to 1377000 g of APPLE.
[DEBUG] Harvest Estimate: Required actions on tile 4 were not performed: WEEDING,IRRIGATING.
[INFO] Harvest Estimate: Harvest estimate on tile 4 changed to 1349415 g of WHEAT."""

const val OUTPUT_TICK_4 = """[INFO] Simulation Info: Tick 4 started at tick 23 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 3 FIELD and 2 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm Action: Machine 1 performs IRRIGATING on tile 1 for 1 days.
[IMPORTANT] Farm Action: Machine 1 performs IRRIGATING on tile 2 for 1 days.
[IMPORTANT] Farm Action: Machine 1 performs IRRIGATING on tile 4 for 1 days.
[IMPORTANT] Farm Action: Machine 1 performs IRRIGATING on tile 3 for 1 days.
[IMPORTANT] Farm Action: Machine 1 performs IRRIGATING on tile 5 for 1 days.
[IMPORTANT] Farm Machine: Machine 1 is finished and returns to the shed at 0.
[IMPORTANT] Farm: Farm 0 finished its actions.
[DEBUG] Harvest Estimate: Required actions on tile 1 were not performed: IRRIGATING.
[INFO] Harvest Estimate: Harvest estimate on tile 1 changed to 1349265 g of WHEAT.
[DEBUG] Harvest Estimate: Required actions on tile 2 were not performed: IRRIGATING.
[INFO] Harvest Estimate: Harvest estimate on tile 2 changed to 1349265 g of WHEAT.
[INFO] Harvest Estimate: Harvest estimate on tile 3 changed to 1239300 g of APPLE.
[DEBUG] Harvest Estimate: Required actions on tile 4 were not performed: IRRIGATING.
[INFO] Harvest Estimate: Harvest estimate on tile 4 changed to 1349265 g of WHEAT."""

const val OUTPUT_TICK_5 = """[IMPORTANT] Simulation Info: Simulation ended at tick 5.
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
[IMPORTANT] Simulation Statistics: Total harvest estimate still in fields and plantations: 6487095 g."""

/**
 * Fortinati or babagee
 */
open class FullIrrigationTestTick0 : ExampleSystemTestExtension() {
    override val name = "IrrigationTest"
    override val description = "Tests the irrigation logic with different plants on fields and plantations."

    // Paths are relative from the `src/systemtest/resources` directory.
    override val farms = "new_irrigation_test/farms.json"
    override val scenario = "example/scenario.json"
    override val map = "new_irrigation_test/map.json"

    override val logLevel = "DEBUG"
    override val maxTicks = 5
    override val startYearTick = 19

    override suspend fun run() {
        skipUntilString("[INFO] Simulation Info: Simulation started at tick 19 within the year.")
        for (line in OUTPUT_TICK_0.lines()) {
            assertNextLine(line)
        }
    }
}

/**
 * Fortinati or babagee
 */
open class FullIrrigationTestTick1 : FullIrrigationTestTick0() {
    override val name = "IrrigationTest_tick1"
    override suspend fun run() {
        super.run()
        for (line in OUTPUT_TICK_1.lines()) {
            assertNextLine(line)
        }
    }
}

/**
 * Fortinati or babagee
 */
open class FullIrrigationTestTick2 : FullIrrigationTestTick1() {
    override val name = "IrrigationTest_tick2"
    override suspend fun run() {
        super.run()
        for (line in OUTPUT_TICK_2.lines()) {
            assertNextLine(line)
        }
    }
}

/**
 * Fortinati or babagee
 */
open class FullIrrigationTestTick3 : FullIrrigationTestTick2() {
    override val name = "IrrigationTest_tick3"
    override suspend fun run() {
        super.run()
        skipUntilString("[INFO] Simulation Info: Tick 3 started at tick 22 within the year.")
        assertNextLine(
            "[INFO] Soil Moisture: The soil moisture is below threshold in 3 FIELD and 2 PLANTATION tiles."
        )
        assertNextLine(
            "[IMPORTANT] Farm: Farm 0 starts its actions."
        )
    }
}

/**
 * Fortinati or babagee
 */
open class FullIrrigationTestTick31 : FullIrrigationTestTick3() {
    override val name = "IrrigationTest_tick3_1"
    override suspend fun run() {
        super.run()
        assertNextLine(
            "[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: ."
        )
    }
}

/**
 * Fortinati or babagee
 */
open class FullIrrigationTestTick32 : FullIrrigationTestTick31() {
    override val name = "IrrigationTest_tick3_2"
    override suspend fun run() {
        super.run()
        assertNextLine(
            "[IMPORTANT] Farm Action: Machine 1 performs IRRIGATING on tile 1 for 1 days."
        )
    }
}

/**
 * Fortinati or babagee
 */
open class FullIrrigationTestTick33 : FullIrrigationTestTick32() {
    override val name = "IrrigationTest_tick3_3"
    override suspend fun run() {
        super.run()
        assertNextLine(
            "[IMPORTANT] Farm Action: Machine 1 performs IRRIGATING on tile 2 for 1 days."
        )
    }
}

/**
 * Fortinati or babagee
 */
open class FullIrrigationTestTick34 : FullIrrigationTestTick33() {
    override val name = "IrrigationTest_tick3_4"
    override suspend fun run() {
        super.run()
        assertNextLine(
            "[IMPORTANT] Farm Action: Machine 1 performs IRRIGATING on tile 4 for 1 days."
        )
    }
}

/**
 * Fortinati or babagee
 */
open class FullIrrigationTestTick35 : FullIrrigationTestTick34() {
    override val name = "IrrigationTest_tick3_5"
    override suspend fun run() {
        super.run()
        assertNextLine(
            "[IMPORTANT] Farm Action: Machine 1 performs IRRIGATING on tile 3 for 1 days."
        )
    }
}

/**
 * Fortinati or babagee
 */
open class FullIrrigationTestTick36 : FullIrrigationTestTick35() {
    override val name = "IrrigationTest_tick3_6"
    override suspend fun run() {
        super.run()
        assertNextLine(
            "[IMPORTANT] Farm Action: Machine 1 performs IRRIGATING on tile 5 for 1 days."
        )
    }
}

/**
 * Fortinati or babagee
 */
open class FullIrrigationTestTick37 : FullIrrigationTestTick36() {
    override val name = "IrrigationTest_tick3_7"
    override suspend fun run() {
        super.run()
        assertNextLine(
            "[IMPORTANT] Farm Machine: Machine 1 is finished and returns to the shed at 0."
        )
        assertNextLine(
            "[IMPORTANT] Farm: Farm 0 finished its actions."
        )
    }
}

/**
 * Fortinati or babagee
 */
open class FullIrrigationTestTick3Estimations : FullIrrigationTestTick37() {
    override val name = "IrrigationTest_tick3_estimations"
    override suspend fun run() {
        super.run()
        for (line in OUTPUT_TICK_3_ESTIMATIONS.lines()) {
            assertNextLine(line)
        }
    }
}

/**
 * Fortinati or babagee
 */
open class FullIrrigationTestTick4 : FullIrrigationTestTick3Estimations() {
    override val name = "IrrigationTest_tick4"
    override suspend fun run() {
        super.run()
        for (line in OUTPUT_TICK_4.lines()) {
            assertNextLine(line)
        }
    }
}

/**
 * Fortinati or babagee
 */
open class FullIrrigationTestTick5 : FullIrrigationTestTick4() {
    override val name = "IrrigationTest_tick5"
    override suspend fun run() {
        super.run()
        for (line in OUTPUT_TICK_5.lines()) {
            assertNextLine(line)
        }
    }
}
