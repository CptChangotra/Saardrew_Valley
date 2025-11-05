package de.unisaarland.cs.se.selab.systemtest.selab25.basictests.one001and1sowingtests

import de.unisaarland.cs.se.selab.systemtest.selab25.utils.ExampleSystemTestExtension

const val OUTPUT_2 = """"""

/**
* Drought -> try sowing -> make sure it can only be done after 4 ticks
*/
class SowingAfterDrought : ExampleSystemTestExtension() {
    override val name = "ImUnderTheWaterPleaseHelpMe"
    override val description = "Drought -> try sowing -> make sure it can only be done after 4 ticks"

    override val farms = "sowingTests/SowingAfterDrought/farms.json"
    override val scenario = "sowingTests/SowingAfterDrought/scenario.json"
    override val map = "sowingTests/SowingAfterDrought/map.json"

    override val logLevel = "DEBUG"
    override val maxTicks = 5
    override val startYearTick = 21

    override suspend fun run() {
        skipUntilString(
            "[INFO] Simulation Info: Simulation started at tick 19 within the year."
        )
        // for (line in OUTPUT_2.lines()) {
        //    assertNextLine(line)
        // }
    }
}
