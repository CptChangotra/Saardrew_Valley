package de.unisaarland.cs.se.selab.systemtest.selab25.basictests.one001and1sowingtests

import de.unisaarland.cs.se.selab.systemtest.selab25.utils.ExampleSystemTestExtension

/**
 * GRRRRRRRR
 */
class NotVerySowingTestOG : ExampleSystemTestExtension() {
    override val name = "NotVerySowingTestOG (CHERRY)"
    override val description = "No harvest estimate log on reset"

    override val farms = "sowingTests/notASowingTest/farm.json"
    override val scenario = "sowingTests/notASowingTest/scenario.json"
    override val map = "sowingTests/notASowingTest/map.json"

    override val logLevel = "DEBUG"
    override val maxTicks = 3
    override val startYearTick = 20

    override suspend fun run() {
        skipUntilString("[INFO] Simulation Info: Tick 1 started at tick 21 within the year.")
        skipUntilString("[IMPORTANT] Farm: Farm 0 finished its actions.")
        assertNextLine(
            "[INFO] Simulation Info: Tick 2 started at tick 22 within the year."
        ) // no harvest estimate reset log
    }
}

/**
 * GRRRRRRRR
 */
class NotVerySowingTestALT : ExampleSystemTestExtension() {
    override val name = "NotVerySowingTestALT (CHERRY)"
    override val description = "Yes a harvest estimate log on reset"

    override val farms = "sowingTests/notASowingTest/farm.json"
    override val scenario = "sowingTests/notASowingTest/scenario.json"
    override val map = "sowingTests/notASowingTest/map.json"

    override val logLevel = "DEBUG"
    override val maxTicks = 3
    override val startYearTick = 20

    override suspend fun run() {
        skipUntilString("[INFO] Simulation Info: Tick 1 started at tick 21 within the year.")
        skipUntilString("[IMPORTANT] Farm: Farm 0 finished its actions.")
        assertNextLine(
            "[INFO] Harvest Estimate: Harvest estimate on tile 2 changed to 1200000 g of CHERRY."
        )
        assertNextLine(
            "[INFO] Simulation Info: Tick 2 started at tick 22 within the year."
        ) // no harvest estimate reset log
    }
}
