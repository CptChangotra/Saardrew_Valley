package de.unisaarland.cs.se.selab.systemtest.selab25.mapParserTests

import de.unisaarland.cs.se.selab.systemtest.selab25.utils.ExampleSystemTestExtension

/**
 *
 */
class FarmsteadAdjoinOtherFarmField : ExampleSystemTestExtension() {
    override val name = "FarmParser test Farmstead adjoin Field from other Farm"
    override val description = "Farmstead cannot adjoin other farm's Field or Plantation"

    override val map = "invalidMapJSON/mapFarmsteadAdjoinOtherFarmField.json"
    override val farms = "invalidMapJSON/farmFarmsteadAdjoinOtherFarmField.json"
    override val scenario = "example/scenario.json"

    override val logLevel = "DEBUG"
    override val maxTicks = 1
    override val startYearTick = 1

    override suspend fun run() {
//        assertNextLine(
//            "[INFO] Initialization Info: mapFarmsteadAdjoinOtherFarmField.json successfully parsed and validated."
//        )
//        assertNextLine("[IMPORTANT] Initialization Info: farmFarmsteadAdjoinOtherFarmField.json is invalid.")
        assertNextLine("[IMPORTANT] Initialization Info: mapFarmsteadAdjoinOtherFarmField.json is invalid.")
    }
}
