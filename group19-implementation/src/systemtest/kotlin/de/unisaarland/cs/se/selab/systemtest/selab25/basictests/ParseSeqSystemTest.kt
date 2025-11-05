package de.unisaarland.cs.se.selab.systemtest.selab25.basictests

import de.unisaarland.cs.se.selab.systemtest.selab25.utils.ExampleSystemTestExtension

/**
 * System test for parse sequence diagram
 */
class ParseSeqSystemTest : ExampleSystemTestExtension() {
    override val name = "ParseSeqDiagram"
    override val description = "Test parsing sequence"

    override val map = "sequence_diagrams_configs/parser_map.json"
    override val farms = "sequence_diagrams_configs/parser_farms.json"
    override val scenario = "sequence_diagrams_configs/parser_scenario.json"

    override val logLevel = "DEBUG"
    override val maxTicks = 0
    override val startYearTick = 10

    override suspend fun run() {
        assertNextLine("[INFO] Initialization Info: parser_map.json successfully parsed and validated.")
        assertNextLine("[INFO] Initialization Info: parser_farms.json successfully parsed and validated.")
        assertNextLine("[INFO] Initialization Info: parser_scenario.json successfully parsed and validated.")
        assertNextLine("[INFO] Simulation Info: Simulation started at tick $startYearTick within the year.")
        assertNextLine("[IMPORTANT] Simulation Info: Simulation ended at tick 0.")
        assertNextLine("[IMPORTANT] Simulation Info: Simulation statistics are calculated.")
        assertNextLine("[IMPORTANT] Simulation Statistics: Farm 0 collected 0 g of harvest.")
        listOf("POTATO", "WHEAT", "OAT", "PUMPKIN", "APPLE", "GRAPE", "ALMOND", "CHERRY").forEach { plant ->
            assertNextLine("[IMPORTANT] Simulation Statistics: Total amount of $plant harvested: 0 g.")
        }
        assertNextLine(
            "[IMPORTANT] Simulation Statistics: Total harvest estimate still in fields and plantations: 0 g."
        )
    }
}
