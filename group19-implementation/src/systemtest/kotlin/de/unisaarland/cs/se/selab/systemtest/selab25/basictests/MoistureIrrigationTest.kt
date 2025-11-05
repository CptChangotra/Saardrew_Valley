package de.unisaarland.cs.se.selab.systemtest.selab25.basictests

import de.unisaarland.cs.se.selab.systemtest.selab25.utils.ExampleSystemTestExtension

private const val IRRIGATION9 =
    "[DEBUG] Harvest Estimate: Required actions on tile 9 were not performed: IRRIGATING."

/**
 * Base config for all MoistureIrrigation parts
 */
abstract class MoistureIrrigationAbstract : ExampleSystemTestExtension() {
    override val farms = "example/MoistureFarms.json"
    override val scenario = "example/MoistureScenario.json"
    override val map = "example/MoistureMap.json"

    override val logLevel = "DEBUG"
    override val maxTicks = 6
    override val startYearTick = 1
}

/**
 * Part 1 – First irrigation actions and harvest estimate update
 */
class MoistureIrrigationPart1 : MoistureIrrigationAbstract() {
    override val name = "Moisture Irrigation – Initial irrigation"
    override val description = "Tests initial irrigation actions and first harvest estimate update."

    override suspend fun run() {
        skipUntilString("[IMPORTANT] Farm Action: Machine 0 performs IRRIGATING on tile 8 for 14 days.")
        assertNextLine("[IMPORTANT] Farm Machine: Machine 0 is finished and returns to the shed at 0.")
        assertNextLine("[IMPORTANT] Farm Action: Machine 1 performs IRRIGATING on tile 9 for 14 days.")
        assertNextLine("[IMPORTANT] Farm Machine: Machine 1 is finished and returns to the shed at 0.")
        assertNextLine("[IMPORTANT] Farm: Farm 0 finished its actions.")
        assertNextLine(IRRIGATION9)
        assertNextLine("[INFO] Harvest Estimate: Harvest estimate on tile 9 changed to 799950 g of ALMOND.")
    }
}

/**
 * Part 2 – Cloud creation affecting tile 8
 */
class MoistureIrrigationPart2 : MoistureIrrigationAbstract() {
    override val name = "Moisture Irrigation – Cloud creation"
    override val description = "Tests cloud creation and subsequent harvest estimate changes."

    override suspend fun run() {
        skipUntilString("[IMPORTANT] Incident: Incident 4 of type CLOUD_CREATION happened and affected tiles 8.")
        assertNextLine(IRRIGATION9)
        assertNextLine("[INFO] Harvest Estimate: Harvest estimate on tile 9 changed to 799900 g of ALMOND.")
    }
}

/**
 * Part 3 – Cloud rain and second irrigation actions
 */
class MoistureIrrigationPart3 : MoistureIrrigationAbstract() {
    override val name = "Moisture Irrigation – Cloud rain and irrigation"
    override val description = "Tests cloud rain and another round of irrigation actions."

    override suspend fun run() {
        skipUntilString("[IMPORTANT] Cloud Rain: Cloud 0 on tile 8 rained down 100 L water.")
        assertNextLine("[DEBUG] Cloud Position: Cloud 0 is on tile 8, where the amount of sunlight is 62.")
        skipUntilString("[IMPORTANT] Farm Action: Machine 0 performs IRRIGATING on tile 9 for 14 days.")
        assertNextLine("[IMPORTANT] Farm Machine: Machine 0 is finished and returns to the shed at 0.")
        assertNextLine("[IMPORTANT] Farm: Farm 0 finished its actions.")
        assertNextLine(IRRIGATION9)
        assertNextLine("[INFO] Harvest Estimate: Harvest estimate on tile 9 changed to 799850 g of ALMOND.")
    }
}

/**
 * Part 4 – Drought incident and final harvest estimate drop to 0
 */
class MoistureIrrigationPart4 : MoistureIrrigationAbstract() {
    override val name = "Moisture Irrigation – Drought"
    override val description = "Tests drought incident and final harvest estimate reset to 0."

    override suspend fun run() {
        skipUntilString("[IMPORTANT] Incident: Incident 1 of type DROUGHT happened and affected tiles 8,9.")
        assertNextLine("[INFO] Harvest Estimate: Harvest estimate on tile 8 changed to 0 g of ALMOND.")
        assertNextLine("[INFO] Harvest Estimate: Harvest estimate on tile 9 changed to 0 g of ALMOND.")
    }
}
