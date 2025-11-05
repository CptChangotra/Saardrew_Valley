package de.unisaarland.cs.se.selab.systemtest.selab25.basicesttests

import de.unisaarland.cs.se.selab.systemtest.selab25.utils.ExampleSystemTestExtension
import de.unisaarland.cs.se.selab.utils.YearTick

/**
 * me is tired
 */
open class FallowPeriodTest : ExampleSystemTestExtension() {
    override val name = "FallowPeriod"
    override val description = "Test fallow period for wheat"

    override val map = "basic_farm/fallowMap.json"
    override val farms = "basic_farm/fallowFarms.json"
    override val scenario = "basic_farm/scenarioFallow.json"

    override val logLevel = "DEBUG"
    override val maxTicks = 25
    override val startYearTick = YearTick.OCTOBER_2

    override suspend fun run() {
        skipUntilString("[INFO] Simulation Info: Tick 17 started at tick 13 within the year.")
        skipUntilString("[IMPORTANT] Farm: Farm 0 starts its actions.")
        assertNextLine(
            "[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: 1."
        )
    }
}

/**
 * hmmmm
 */
open class FallowPeriodWithEstimate : FallowPeriodTest() {
    override val name = "FallowPeriodWithEstimate"
    override val description = "Test fallow period with harvest estimate"

    override suspend fun run() {
        super.run()
        skipUntilString("[IMPORTANT] Farm Machine: Machine 1 unloads 984150 g of WHEAT harvest in the shed.")
        assertNextLine("[IMPORTANT] Farm: Farm 0 finished its actions.")
        assertNextLine("[DEBUG] Harvest Estimate: Required actions on tile 13 were not performed: HARVESTING.")
        // WORKS UNTIL HERE

        assertNextLine("[DEBUG] Harvest Estimate: Required actions on tile 14 were not performed: HARVESTING.")

//        skipUntilString("[INFO] Simulation Info: Tick 18 started at tick 14 within the year.")
//        skipLines(11)
//        assertNextLine("[IMPORTANT] Farm: Farm 0 starts its actions.")
//        assertNextLine(
//            "[DEBUG] Farm: Farm 0 has the following active sowing plans it intends to pursue in this tick: 1."
//        )
    }
}

/**
 * hmmmmmmmm
 */
open class FallowPeriodWithEstimateExtended : FallowPeriodWithEstimate() {
    override val name = "FallowPeriodWithEstimateExtended"
    override val description = "Test fallow period with harvest estimate for longer"

    override suspend fun run() {
        super.run()
        assertNextLine("[IMPORTANT] Farm Action: Machine 1 performs HARVESTING on tile 13 for 12 days.")
        assertNextLine("[IMPORTANT] Farm Harvest: Machine 1 has collected 708588 g of WHEAT harvest.")
        skipLines(2)
        assertNextLine("[IMPORTANT] Farm: Farm 0 finished its actions.")
        assertNextLine("[DEBUG] Harvest Estimate: Required actions on tile 14 were not performed: HARVESTING.")

        skipUntilString("[INFO] Simulation Info: Tick 23 started at tick 19 within the year.")
        skipLines(13)
    }
}

/**
 * hmmmmmmmmmmmmmm
 */
class FallowPeriodWithEstimateExtraExtended : FallowPeriodWithEstimateExtended() {
    override val name = "FallowPeriodWithEstimateExtraExtended"
    override val description = "Test fallow period with harvest estimate for extra longer"

    override suspend fun run() {
        super.run()
        for (i in 10..13) {
            assertNextLine("[IMPORTANT] Farm Action: Machine 0 performs SOWING on tile $i for 1 days.")
            assertNextLine("[IMPORTANT] Farm Sowing: Machine 0 has sowed WHEAT according to sowing plan 1.")
        }
        assertNextLine("[IMPORTANT] Farm Machine: Machine 0 is finished and returns to the shed at 0.")

        skipUntilString("[INFO] Simulation Info: Tick 24 started at tick 20 within the year.")
        skipLines(13)
        assertNextLine("[IMPORTANT] Farm Action: Machine 0 performs SOWING on tile 14 for 1 days.")
        assertNextLine("[IMPORTANT] Farm Sowing: Machine 0 has sowed WHEAT according to sowing plan 2.")
        assertNextLine("[IMPORTANT] Farm Machine: Machine 0 is finished and returns to the shed at 0.")
    }
}
