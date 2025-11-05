package de.unisaarland.cs.se.selab.systemtest.selab25.basictests

import de.unisaarland.cs.se.selab.systemtest.selab25.utils.ExampleSystemTestExtension

/**
 *
 */
class IrrigatingEdgeCase : ExampleSystemTestExtension() {
    override val name = "FieldPlantationIrrigatingEdgeCase"
    override val description = "Tests switching back and forth between Fields and Plantation"

    override val farms = "Irrigating_Test/farms.json"
    override val scenario = "Irrigating_Test/scenario.json"
    override val map = "Irrigating_Test/map.json"

    override val logLevel = "DEBUG"
    override val maxTicks = 7
    override val startYearTick = 14

    override suspend fun run() {
        skipUntilString("[INFO] Simulation Info: Tick 6 started at tick 20 within the year.")
        assertNextLine("[INFO] Soil Moisture: The soil moisture is below threshold in 4 FIELD and 2 PLANTATION tiles.")
        assertNextLine("[IMPORTANT] Farm: Farm 0 starts its actions.")
        assertNextLine(
            "[DEBUG] Farm: Farm 0 has the following active " +
                "sowing plans it intends to pursue in this tick: ."
        )
        assertNextLine("[IMPORTANT] Farm Action: Machine 0 performs IRRIGATING on tile 1 for 2 days.")
        assertNextLine("[IMPORTANT] Farm Action: Machine 0 performs IRRIGATING on tile 2 for 2 days.")
        assertNextLine("[IMPORTANT] Farm Action: Machine 0 performs IRRIGATING on tile 3 for 2 days.")
        assertNextLine("[IMPORTANT] Farm Action: Machine 0 performs IRRIGATING on tile 5 for 2 days.")
        assertNextLine("[IMPORTANT] Farm Action: Machine 0 performs IRRIGATING on tile 4 for 2 days.")
    }
}
