package de.unisaarland.cs.se.selab.systemtest.selab25.basictests

import de.unisaarland.cs.se.selab.systemtest.selab25.utils.ExampleSystemTestExtension

/**
 * test
 */
class TestDroughtCloudsInterplay : ExampleSystemTestExtension() {
    override val name = "Drought and soil moisture"
    override val description = "soil moisture and drought"

    override val map = "DroughtCloudInterplay/map.json"
    override val farms = "DroughtCloudInterplay/farms.json"
    override val scenario = "DroughtCloudInterplay/scenario.json"

    override val logLevel = "DEBUG"
    override val maxTicks = 3
    override val startYearTick = 5
    override suspend fun run() {
        skipUntilString("[IMPORTANT] Incident: Incident 0 of type CLOUD_CREATION happened and affected tiles 1.")
        assertNextLine("[IMPORTANT] Incident: Incident 1 of type DROUGHT happened and affected tiles 1.")
        assertNextLine("[INFO] Harvest Estimate: Harvest estimate on tile 1 changed to 0 g of CHERRY.")
        skipUntilString("[IMPORTANT] Cloud Rain: Cloud 0 on tile 1 rained down 5000 L water.")
        assertNextLine("[INFO] Cloud Dissipation: Cloud 0 dissipates on tile 1.")
        skipUntilString("[IMPORTANT] Incident: Incident 2 of type DROUGHT happened and affected tiles 1.")
        assertNextLine("[IMPORTANT] Incident: Incident 3 of type CLOUD_CREATION happened and affected tiles 1.")
        skipUntilString("[IMPORTANT] Cloud Rain: Cloud 1 on tile 1 rained down 5000 L water")
        assertNextLine("[DEBUG] Cloud Position: Cloud 1 is on tile 1, where the amount of sunlight is 90.")
    }
}
