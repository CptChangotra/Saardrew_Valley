package de.unisaarland.cs.se.selab.systemtest.selab25.basictests.one001and1sowingtests

import de.unisaarland.cs.se.selab.systemtest.selab25.utils.ExampleSystemTestExtension

const val TEXT = """[INFO] Simulation Info: Tick 0 started at tick 10 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: 0.
[IMPORTANT] Farm Action: Machine 1 performs SOWING on tile 11 for 3 days.
[IMPORTANT] Farm Sowing: Machine 1 has sowed POTATO according to sowing plan 0.
[IMPORTANT] Farm Action: Machine 1 performs SOWING on tile 12 for 3 days.
[IMPORTANT] Farm Sowing: Machine 1 has sowed POTATO according to sowing plan 0.
[IMPORTANT] Farm Action: Machine 1 performs SOWING on tile 13 for 3 days.
[IMPORTANT] Farm Sowing: Machine 1 has sowed POTATO according to sowing plan 0.
[IMPORTANT] Farm Machine: Machine 1 is finished and returns to the shed at 0.
[IMPORTANT] Farm: Farm 0 finished its actions.
[INFO] Harvest Estimate: Harvest estimate on tile 11 changed to 900000 g of POTATO.
[INFO] Harvest Estimate: Harvest estimate on tile 12 changed to 900000 g of POTATO.
[INFO] Harvest Estimate: Harvest estimate on tile 13 changed to 900000 g of POTATO.
[INFO] Simulation Info: Tick 1 started at tick 11 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 0 finished its actions.
[INFO] Harvest Estimate: Harvest estimate on tile 11 changed to 810000 g of POTATO.
[INFO] Harvest Estimate: Harvest estimate on tile 12 changed to 810000 g of POTATO.
[INFO] Harvest Estimate: Harvest estimate on tile 13 changed to 810000 g of POTATO.
[INFO] Simulation Info: Tick 2 started at tick 12 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm Action: Machine 0 performs WEEDING on tile 11 for 10 days.
[IMPORTANT] Farm Machine: Machine 0 is finished and returns to the shed at 0.
[IMPORTANT] Farm: Farm 0 finished its actions.
[INFO] Harvest Estimate: Harvest estimate on tile 11 changed to 729000 g of POTATO.
[DEBUG] Harvest Estimate: Required actions on tile 12 were not performed: WEEDING.
[INFO] Harvest Estimate: Harvest estimate on tile 12 changed to 656100 g of POTATO.
[DEBUG] Harvest Estimate: Required actions on tile 13 were not performed: WEEDING.
[INFO] Harvest Estimate: Harvest estimate on tile 13 changed to 656100 g of POTATO.
[INFO] Simulation Info: Tick 3 started at tick 13 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 0 finished its actions.
[INFO] Harvest Estimate: Harvest estimate on tile 11 changed to 656100 g of POTATO.
[INFO] Harvest Estimate: Harvest estimate on tile 12 changed to 590490 g of POTATO.
[INFO] Harvest Estimate: Harvest estimate on tile 13 changed to 590490 g of POTATO.
[INFO] Simulation Info: Tick 4 started at tick 14 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm Action: Machine 0 performs WEEDING on tile 11 for 10 days.
[IMPORTANT] Farm Machine: Machine 0 is finished and returns to the shed at 0.
[IMPORTANT] Farm: Farm 0 finished its actions.
[INFO] Harvest Estimate: Harvest estimate on tile 11 changed to 590490 g of POTATO.
[DEBUG] Harvest Estimate: Required actions on tile 12 were not performed: WEEDING.
[INFO] Harvest Estimate: Harvest estimate on tile 12 changed to 478296 g of POTATO.
[DEBUG] Harvest Estimate: Required actions on tile 13 were not performed: WEEDING.
[INFO] Harvest Estimate: Harvest estimate on tile 13 changed to 478296 g of POTATO.
[INFO] Simulation Info: Tick 5 started at tick 15 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 0 finished its actions.
[INFO] Simulation Info: Tick 6 started at tick 16 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm Action: Machine 0 performs WEEDING on tile 11 for 10 days.
[IMPORTANT] Farm Machine: Machine 0 is finished and returns to the shed at 0.
[IMPORTANT] Farm: Farm 0 finished its actions.
[DEBUG] Harvest Estimate: Required actions on tile 12 were not performed: WEEDING.
[INFO] Harvest Estimate: Harvest estimate on tile 12 changed to 430466 g of POTATO.
[DEBUG] Harvest Estimate: Required actions on tile 13 were not performed: WEEDING.
[INFO] Harvest Estimate: Harvest estimate on tile 13 changed to 430466 g of POTATO.
[INFO] Simulation Info: Tick 7 started at tick 17 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm Action: Machine 0 performs HARVESTING on tile 11 for 10 days.
[IMPORTANT] Farm Harvest: Machine 0 has collected 590490 g of POTATO harvest.
[IMPORTANT] Farm Machine: Machine 0 is finished and returns to the shed at 0.
[IMPORTANT] Farm Machine: Machine 0 unloads 590490 g of POTATO harvest in the shed.
[IMPORTANT] Farm: Farm 0 finished its actions.
[INFO] Simulation Info: Tick 8 started at tick 18 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm Action: Machine 0 performs HARVESTING on tile 12 for 10 days.
[IMPORTANT] Farm Harvest: Machine 0 has collected 430466 g of POTATO harvest.
[IMPORTANT] Farm Machine: Machine 0 is finished and returns to the shed at 0.
[IMPORTANT] Farm Machine: Machine 0 unloads 430466 g of POTATO harvest in the shed.
[IMPORTANT] Farm: Farm 0 finished its actions.
[INFO] Simulation Info: Tick 9 started at tick 19 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: 1.
[IMPORTANT] Farm Action: Machine 1 performs SOWING on tile 10 for 3 days.
[IMPORTANT] Farm Sowing: Machine 1 has sowed WHEAT according to sowing plan 1.
[IMPORTANT] Farm Machine: Machine 1 is finished and returns to the shed at 0.
[IMPORTANT] Farm Action: Machine 0 performs HARVESTING on tile 13 for 10 days.
[IMPORTANT] Farm Harvest: Machine 0 has collected 430466 g of POTATO harvest.
[IMPORTANT] Farm Machine: Machine 0 is finished and returns to the shed at 0.
[IMPORTANT] Farm Machine: Machine 0 unloads 430466 g of POTATO harvest in the shed.
[IMPORTANT] Farm: Farm 0 finished its actions.
[INFO] Simulation Info: Tick 10 started at tick 20 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 0 finished its actions.
[INFO] Simulation Info: Tick 11 started at tick 21 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 0 finished its actions.
[IMPORTANT] Simulation Info: Simulation ended at tick 12.
[IMPORTANT] Simulation Info: Simulation statistics are calculated.
[IMPORTANT] Simulation Statistics: Farm 0 collected 1451422 g of harvest.
[IMPORTANT] Simulation Statistics: Total amount of POTATO harvested: 1451422 g.
[IMPORTANT] Simulation Statistics: Total amount of WHEAT harvested: 0 g.
[IMPORTANT] Simulation Statistics: Total amount of OAT harvested: 0 g.
[IMPORTANT] Simulation Statistics: Total amount of PUMPKIN harvested: 0 g.
[IMPORTANT] Simulation Statistics: Total amount of APPLE harvested: 0 g.
[IMPORTANT] Simulation Statistics: Total amount of GRAPE harvested: 0 g.
[IMPORTANT] Simulation Statistics: Total amount of ALMOND harvested: 0 g.
[IMPORTANT] Simulation Statistics: Total amount of CHERRY harvested: 0 g.
[IMPORTANT] Simulation Statistics: Total harvest estimate still in fields and plantations: 1500000 g."""

const val TEXT_1 = """[INFO] Simulation Info: Tick 0 started at tick 10 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: 0.
[IMPORTANT] Farm Action: Machine 1 performs SOWING on tile 11 for 3 days.
[IMPORTANT] Farm Sowing: Machine 1 has sowed POTATO according to sowing plan 0.
[IMPORTANT] Farm Action: Machine 1 performs SOWING on tile 12 for 3 days.
[IMPORTANT] Farm Sowing: Machine 1 has sowed POTATO according to sowing plan 0.
[IMPORTANT] Farm Action: Machine 1 performs SOWING on tile 13 for 3 days.
[IMPORTANT] Farm Sowing: Machine 1 has sowed POTATO according to sowing plan 0.
[IMPORTANT] Farm Machine: Machine 1 is finished and returns to the shed at 0.
[IMPORTANT] Farm: Farm 0 finished its actions."""
const val TEXT_2 = """[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 0 finished its actions."""
const val TEXT_3 = """
    [INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm Action: Machine 0 performs WEEDING on tile 11 for 10 days.
[IMPORTANT] Farm Machine: Machine 0 is finished and returns to the shed at 0.
[IMPORTANT] Farm: Farm 0 finished its actions."""
const val TEXT_5 = """[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm Action: Machine 0 performs WEEDING on tile 11 for 10 days.
[IMPORTANT] Farm Machine: Machine 0 is finished and returns to the shed at 0.
[IMPORTANT] Farm: Farm 0 finished its actions."""
const val TEXT_6 = """[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 0 finished its actions."""
const val TEXT_7 = """[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm Action: Machine 0 performs WEEDING on tile 11 for 10 days.
[IMPORTANT] Farm Machine: Machine 0 is finished and returns to the shed at 0.
[IMPORTANT] Farm: Farm 0 finished its actions."""
const val TEXT_8 = """[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm Action: Machine 0 performs HARVESTING on tile 11 for 10 days.
[IMPORTANT] Farm Harvest: Machine 0 has collected 590490 g of POTATO harvest.
[IMPORTANT] Farm Machine: Machine 0 is finished and returns to the shed at 0.
[IMPORTANT] Farm Machine: Machine 0 unloads 590490 g of POTATO harvest in the shed.
[IMPORTANT] Farm: Farm 0 finished its actions."""
const val TEXT_9 = """[INFO] Simulation Info: Tick 8 started at tick 18 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm Action: Machine 0 performs HARVESTING on tile 12 for 10 days.
[IMPORTANT] Farm Harvest: Machine 0 has collected 430466 g of POTATO harvest.
[IMPORTANT] Farm Machine: Machine 0 is finished and returns to the shed at 0.
[IMPORTANT] Farm Machine: Machine 0 unloads 430466 g of POTATO harvest in the shed.
[IMPORTANT] Farm: Farm 0 finished its actions.
[INFO] Simulation Info: Tick 9 started at tick 19 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: 1.
[IMPORTANT] Farm Action: Machine 1 performs SOWING on tile 10 for 3 days.
[IMPORTANT] Farm Sowing: Machine 1 has sowed WHEAT according to sowing plan 1.
[IMPORTANT] Farm Machine: Machine 1 is finished and returns to the shed at 0.
[IMPORTANT] Farm Action: Machine 0 performs HARVESTING on tile 13 for 10 days.
[IMPORTANT] Farm Harvest: Machine 0 has collected 430466 g of POTATO harvest.
[IMPORTANT] Farm Machine: Machine 0 is finished and returns to the shed at 0.
[IMPORTANT] Farm Machine: Machine 0 unloads 430466 g of POTATO harvest in the shed.
[IMPORTANT] Farm: Farm 0 finished its actions.
[INFO] Simulation Info: Tick 10 started at tick 20 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 0 finished its actions.
[INFO] Simulation Info: Tick 11 started at tick 21 within the year.
[INFO] Soil Moisture: The soil moisture is below threshold in 0 FIELD and 0 PLANTATION tiles.
[IMPORTANT] Farm: Farm 0 starts its actions.
[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: .
[IMPORTANT] Farm: Farm 0 finished its actions.
[IMPORTANT] Simulation Info: Simulation ended at tick 12.
[IMPORTANT] Simulation Info: Simulation statistics are calculated.
[IMPORTANT] Simulation Statistics: Farm 0 collected 1451422 g of harvest.
[IMPORTANT] Simulation Statistics: Total amount of POTATO harvested: 1451422 g.
[IMPORTANT] Simulation Statistics: Total amount of WHEAT harvested: 0 g.
[IMPORTANT] Simulation Statistics: Total amount of OAT harvested: 0 g.
[IMPORTANT] Simulation Statistics: Total amount of PUMPKIN harvested: 0 g.
[IMPORTANT] Simulation Statistics: Total amount of APPLE harvested: 0 g.
[IMPORTANT] Simulation Statistics: Total amount of GRAPE harvested: 0 g.
[IMPORTANT] Simulation Statistics: Total amount of ALMOND harvested: 0 g.
[IMPORTANT] Simulation Statistics: Total amount of CHERRY harvested: 0 g.
[IMPORTANT] Simulation Statistics: Total harvest estimate still in fields and plantations: 1500000 g."""

/**
 * lick my shiny metal ass
 */
open class YetAnotherSowingPlanOG : ExampleSystemTestExtension() {
    override val name = "Sowing Big but sadly no workey workey :'("
    override val description = ""

    override val farms = "sowingTests/AnoterSowingTest/farms.json"
    override val scenario = "sowingTests/AnoterSowingTest/scenario.json"
    override val map = "sowingTests/AnoterSowingTest/map.json"

    override val logLevel = "DEBUG"
    override val maxTicks = 12
    override val startYearTick = 10

    override suspend fun run() {
        skipUntilString(
            "[INFO] Simulation Info: Simulation started at tick 10 within the year."
        )
        for (line in TEXT.lines()) {
            assertNextLine(line)
        }
    }
}

/**
 * lick my shiny metal ass
 */
open class YetAnotherSowingPlan : ExampleSystemTestExtension() {
    override val name = "Sowing again"
    override val description = ""

    override val farms = "sowingTests/AnoterSowingTest/farms.json"
    override val scenario = "sowingTests/AnoterSowingTest/scenario.json"
    override val map = "sowingTests/AnoterSowingTest/map.json"

    override val logLevel = "DEBUG"
    override val maxTicks = 12
    override val startYearTick = 10

    override suspend fun run() {
        skipUntilString(
            "[INFO] Simulation Info: Simulation started at tick 10 within the year."
        )
        for (line in TEXT_1.lines()) {
            assertNextLine(line)
        }
    }
}

/**
 * goo goo ga ga
 */
open class YetAnotherSowingPlanTick1 : YetAnotherSowingPlan() {
    override val name = "Sowing again Tick 1"

    override suspend fun run() {
        super.run()
        skipUntilString(
            "[INFO] Simulation Info: Tick 1 started at tick 11 within the year."
        )
        for (line in TEXT_2.lines()) {
            assertNextLine(line)
        }
    }
}

/**
 * goo goo ga ga
 */
open class YetAnotherSowingPlanTick2 : YetAnotherSowingPlanTick1() {
    override val name = "Sowing again Tick 2"

    override suspend fun run() {
        super.run()
        skipUntilString(
            "[INFO] Simulation Info: Tick 2 started at tick 12 within the year."
        )
        for (line in TEXT_3.lines()) {
            assertNextLine(line)
        }
    }
}

/**
 * goo goo ga ga
 */
open class YetAnotherSowingPlanTick3 : YetAnotherSowingPlanTick2() {
    override val name = "Sowing again Tick 3"

    override suspend fun run() {
        super.run()
        skipUntilString(
            "[INFO] Simulation Info: Tick 3 started at tick 13 within the year."
        )
        for (line in TEXT_2.lines()) {
            assertNextLine(line)
        }
    }
}

/**
 * goo goo ga ga
 */
open class YetAnotherSowingPlanTick4 : YetAnotherSowingPlanTick3() {
    override val name = "Sowing again Tick 4"

    override suspend fun run() {
        super.run()
        skipUntilString(
            "[INFO] Simulation Info: Tick 4 started at tick 14 within the year."
        )
        for (line in TEXT_5.lines()) {
            assertNextLine(line)
        }
    }
}

/**
 * goo goo ga ga
 */
open class YetAnotherSowingPlanTick5 : YetAnotherSowingPlanTick4() {
    override val name = "Sowing again Tick 5"

    override suspend fun run() {
        super.run()
        skipUntilString(
            "[INFO] Simulation Info: Tick 5 started at tick 15 within the year."
        )
        for (line in TEXT_6.lines()) {
            assertNextLine(line)
        }
    }
}

/**
 * goo goo ga ga
 */
open class YetAnotherSowingPlanTick6 : YetAnotherSowingPlanTick5() {
    override val name = "Sowing again Tick 6"

    override suspend fun run() {
        super.run()
        skipUntilString(
            "[INFO] Simulation Info: Tick 6 started at tick 16 within the year."
        )
        for (line in TEXT_7.lines()) {
            assertNextLine(line)
        }
    }
}

/**
 * goo goo ga ga
 */
open class YetAnotherSowingPlanTick7 : YetAnotherSowingPlanTick6() {
    override val name = "Sowing again Tick 7"

    override suspend fun run() {
        super.run()
        skipUntilString(
            "[INFO] Simulation Info: Tick 7 started at tick 17 within the year."
        )
        for (line in TEXT_8.lines()) {
            assertNextLine(line)
        }
    }
}

/**
 * goo goo ga ga
 */
open class YetAnotherSowingPlanTick8 : YetAnotherSowingPlanTick7() {
    override val name = "Sowing again Tick 8"

    override suspend fun run() {
        super.run()
        skipUntilString(
            "[INFO] Simulation Info: Tick 8 started at tick 18 within the year."
        )
        for (line in TEXT_9.lines()) {
            assertNextLine(line)
        }
    }
}
