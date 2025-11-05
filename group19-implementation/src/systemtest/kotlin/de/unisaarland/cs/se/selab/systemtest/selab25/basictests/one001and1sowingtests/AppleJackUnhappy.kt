package de.unisaarland.cs.se.selab.systemtest.selab25.basictests.one001and1sowingtests

import de.unisaarland.cs.se.selab.systemtest.selab25.utils.ExampleSystemTestExtension

/**
 * GRRRRRRRR
 */
class AppleJackUnhappy : ExampleSystemTestExtension() {
    override val name = "AppleJack is unhappy"
    override val description = "DEATH TO APPLES!1!!"

    override val farms = "AppleJackUnhappy/farm.json"
    override val scenario = "AppleJackUnhappy/scenario.json"
    override val map = "AppleJackUnhappy/map.json"

    override val logLevel = "DEBUG"
    override val maxTicks = 3
    override val startYearTick = 20

    override suspend fun run() {
        skipUntilString("[INFO] Simulation Info: Simulation started at tick 20 within the year.")
        skipUntilString("[IMPORTANT] Farm: Farm 0 finished its actions.")
        assertNextLine(
            "[DEBUG] Harvest Estimate: Required actions on tile 2 were not performed: HARVESTING."
        )
        assertNextLine(
            "[INFO] Harvest Estimate: Harvest estimate on tile 2 changed to 0 g of APPLE."
        )
    }
}
