package de.unisaarland.cs.se.selab.systemtest.selab25.basictests

import de.unisaarland.cs.se.selab.systemtest.selab25.utils.ExampleSystemTestExtension

/**
 * Testing harvest estimate changes and incidents
 */
class BigIncident1 : ExampleSystemTestExtension() {
    override val name = "Animal, bee and drought harvest"
    override val description = "Tests incidents."

    override val farms = "example/bigIncidentFarms.json"
    override val scenario = "example/bigIncidentScenario.json"
    override val map = "example/bigIncidentMap.json"

    override val logLevel = "DEBUG"
    override val maxTicks = 4
    override val startYearTick = 7

    override suspend fun run() {
        // animal attack
        skipUntilString(
            "[IMPORTANT] Incident: Incident 1 of type ANIMAL_ATTACK happened and affected tiles 40,41,54."
        )
        assertNextLine("[INFO] Harvest Estimate: Harvest estimate on tile 40 changed to 1115370 g of APPLE.")
        assertNextLine("[INFO] Harvest Estimate: Harvest estimate on tile 41 changed to 1115370 g of APPLE.")
        assertNextLine("[INFO] Harvest Estimate: Harvest estimate on tile 42 changed to 1239300 g of APPLE.")

        // bee happy
        skipUntilString("[IMPORTANT] Incident: Incident 2 of type BEE_HAPPY happened and affected tiles 40,41.")
        assertNextLine("[INFO] Harvest Estimate: Harvest estimate on tile 40 changed to 894414 g of APPLE.")
        assertNextLine("[INFO] Harvest Estimate: Harvest estimate on tile 41 changed to 894414 g of APPLE.")
        assertNextLine("[INFO] Harvest Estimate: Harvest estimate on tile 42 changed to 903449 g of APPLE.")

        // drought
        skipUntilString("[IMPORTANT] Incident: Incident 3 of type DROUGHT happened and affected tiles 40,54.")
        assertNextLine("[INFO] Harvest Estimate: Harvest estimate on tile 40 changed to 0 g of APPLE.")
        assertNextLine("[INFO] Harvest Estimate: Harvest estimate on tile 41 changed to 385013 g of APPLE.")
        assertNextLine("[INFO] Harvest Estimate: Harvest estimate on tile 42 changed to 388902 g of APPLE.")
        assertNextLine("[INFO] Harvest Estimate: Harvest estimate on tile 54 changed to 0 g of PUMPKIN.")
        assertNextLine("[INFO] Harvest Estimate: Harvest estimate on tile 69 changed to 450000 g of PUMPKIN.")
        assertNextLine("[INFO] Harvest Estimate: Harvest estimate on tile 84 changed to 450000 g of PUMPKIN.")
    }
}
