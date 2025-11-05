package de.unisaarland.cs.se.selab.systemtest.selab25.basictests

import de.unisaarland.cs.se.selab.systemtest.selab25.utils.ExampleSystemTestExtension

private const val FARM_FINISHED = "[IMPORTANT] Farm: Farm 0 finished its actions."

/**
 * Tests clouds
 */
class CloudOnEdge : ExampleSystemTestExtension() {
    override val name = "Cloud on Edge"
    override val description = "Cloud Movement -> Merge -> Cloud creation"

    override val farms = "CloudOnEdge/CloudOnEdge_farm.json"
    override val scenario = "CloudOnEdge/CloudOnEdge_scenario.json"
    override val map = "CloudOnEdge/CloudOnEdge_map.json"

    override val logLevel = "DEBUG"
    override val maxTicks = 6
    override val startYearTick = 7 // APRIL1

    override suspend fun run() {
        skipUntilString(FARM_FINISHED)
        assertNextLine(
            "[IMPORTANT] Incident: Incident 1 of type CLOUD_CREATION happened" +
                " and affected tiles 1,2,3,4,5,7,8,11,12,13,14,15,16,17."
        )
        assertNextLine(
            "[IMPORTANT] Cloud Union: Clouds 2 and 16 united to cloud 17 with 6000 L water and duration 2 on tile 7."
        )
        skipUntilString(FARM_FINISHED)
        assertNextLine("[IMPORTANT] Incident: Incident 2 of type CLOUD_CREATION happened and affected tiles 18.")
    }
}
