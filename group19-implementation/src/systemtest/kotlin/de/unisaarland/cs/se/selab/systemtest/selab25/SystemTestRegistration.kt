package de.unisaarland.cs.se.selab.systemtest.selab25

import de.unisaarland.cs.se.selab.systemtest.selab25.basicesttests.*
import de.unisaarland.cs.se.selab.systemtest.selab25.basictests.*
import de.unisaarland.cs.se.selab.systemtest.selab25.basictests.cherryTest.*
import de.unisaarland.cs.se.selab.systemtest.selab25.basictests.one001and1sowingtests.*
import de.unisaarland.cs.se.selab.systemtest.selab25.mapParserTests.*

/**
 * Used for test registration
 */
object SystemTestRegistration {
    /**
     * Register your tests to run against the reference implementation!
     * This can also be used to debug our system test, or to see if we
     * understood something correctly or not (everything should work
     * the same as their reference implementation)
     */
    fun registerSystemTestsForReferenceImplementation(testSuite: SELab25TestSuite) {
        // testSuite.registerTest(SoilMoisture())
        // clouds
        // testSuite.registerTest(CloudInf())
        // testSuite.registerTest(LoopCloud())
        testSuite.registerTest(CloudOnEdge())

        // incidents
        testSuite.registerTest(BigIncidents()) // better keep to track we didn't fuck up anything
        testSuite.registerTest(BigIncident1())
        testSuite.registerTest(DroughtThenBeeHappy())

        testSuite.registerTest(DroughtTest())

//        testSuite.registerTest(IncidentBeeHappy()) // didn't pass
//        testSuite.registerTest(BeeHappyNotLogged()) // passed
        testSuite.registerTest(IncidentAnimalAttack())

        // machines and actions
        testSuite.registerTest(HarvestLogTest())
        testSuite.registerTest(HarvestLogTest2())
        testSuite.registerTest(SowBigRadius())
//        testSuite.registerTest(CherryTest())
//        testSuite.registerTest(CherryTestCutting())
//        testSuite.registerTest(CherryTestMowing())

//        testSuite.registerTest(AppleCyclePart1()) // maybe leave to verify we didn't break anything
//        testSuite.registerTest(AppleCyclePart2())
//        testSuite.registerTest(AppleCyclePart3())
//        testSuite.registerTest(AppleCyclePart4())
//        testSuite.registerTest(AppleCycleFull())
//        testSuite.registerTest(AppleBusyMachine())
//        testSuite.registerTest(AppleLateHarvest())
//        testSuite.registerTest(HarvestCycleTick0To4())
//        testSuite.registerTest(HarvestCycleTick5To10())
//        testSuite.registerTest(HarvestCycleTick11To15())
//        testSuite.registerTest(HarvestCycleTick16To20())
        testSuite.registerTest(AppleTest2())
//        for (tick in 0..23) testSuite.registerTest(BasicAlmond(tick))

        for (tick in 0..23) testSuite.registerTest(FullSimulation2(tick))
        testSuite.registerTest(FullSimulation14Clouds())
        testSuite.registerTest(FullSimulation14Farm0())
        testSuite.registerTest(FullSimulation14End())

        for (tick in 0..6) testSuite.registerTest(BigExpansion(tick))

//        testSuite.registerTest(FallowPeriodWithEstimate())

//        testSuite.registerTest(BigLad8Farm0())
//        testSuite.registerTest(BigLad8Farm1())

        // validation
//        testSuite.registerTest(SowingPlanWrongFarm())
//        testSuite.registerTest(StealMachine())
//        testSuite.registerTest(ParseManyIncidents())

        // Sowing multitest

        // irrigation
        // testSuite.registerTest(FullIrrigationTestTick0())
        // testSuite.registerTest(FullIrrigationTestTick1())
        // testSuite.registerTest(FullIrrigationTestTick2())
        // testSuite.registerTest(FullIrrigationTestTick4())
        // testSuite.registerTest(FullIrrigationTestTick5())
//        testSuite.registerTest(SowingRadius())

        testSuite.registerTest(HarvestLost())
        testSuite.registerTest(CloudCreationParsingValid())
        testSuite.registerTest(CloudCreationParsingInvalid())
        testSuite.registerTest(CloudCreationParsingInvalid2())
        testSuite.registerTest(ApplesLost())
        testSuite.registerTest(AppleJackUnhappy())

//        testSuite.registerTest(LowMoistureCherry1())
//        testSuite.registerTest(LowMoistureCherry2())

        testSuite.registerTest(MachineMaze())
        testSuite.registerTest(CityExpansionTest2())
    }

    /**
     * Register the tests you want to run against the validation mutants here!
     * The test only check validation, so they log messages will only possibly
     * be incorrect during the parsing/validation.
     * Everything after 'Simulation start' works correctly
     */
    fun registerSystemTestsMutantValidation(testSuite: SELab25TestSuite) {
        testSuite.registerTest(FarmsteadAdjoinOtherFarmField())
        testSuite.registerTest(FarmTileMissingInFarm())
        testSuite.registerTest(PlantInTwoFarm())
        testSuite.registerTest(CityExpansionTest())
        testSuite.registerTest(MoistureDropsToZero2())
        testSuite.registerTest(VillageDestroyMeadow())
        testSuite.registerTest(SowingPlanWrongFarm())
        testSuite.registerTest(StealMachine())
        // testSuite.registerTest(SowingInvalid())
        testSuite.registerTest(SowingPlanNoField())
    }

    /**
     * The same as above, but the log message only (possibly) become incorrect
     * from the 'Simulation start' log onwards
     */
    fun registerSystemTestsMutantSimulation(testSuite: SELab25TestSuite) {
        // NEW TESTS

        // VALID MUTANT FINDERS
        // machines and actions
        testSuite.registerTest(MultipleSowingPlansTest())
        testSuite.registerTest(AppleCyclePart2())
        testSuite.registerTest(AppleCyclePart4())
        testSuite.registerTest(AppleBusyMachine())
        testSuite.registerTest(NewSowTestAsRef1())
        testSuite.registerTest(PartialSowingPlanFulfillment())
        testSuite.registerTest(OverestimateHarvest())
        testSuite.registerTest(SowingRadius())
        testSuite.registerTest(HarvestLost())
        testSuite.registerTest(UnreachableMapSowingTest())

        // clouds
        testSuite.registerTest(CloudMix())
        testSuite.registerTest(CloudSystemTest())

        // incidents
        testSuite.registerTest(IncidentCloudCity())
        testSuite.registerTest(BigIncidents())
        testSuite.registerTest(BigIncident1())
        testSuite.registerTest(IncidentsAndCloudsFromSuperBigScenarioTest())
        testSuite.registerTest(StuckAndBroken())
        testSuite.registerTest(BrokenMachineTest())
        testSuite.registerTest(MoistureIrrigationPart1())
        testSuite.registerTest(MoistureIrrigationPart3())
        testSuite.registerTest(MoistureIrrigationPart4())

        for (tick in 0..25) testSuite.registerTest(SowTestMixPlants(tick))
        for (i in 0..33) testSuite.registerTest(BigLad(i))

        testSuite.registerTest(FullIrrigationTestTick3())
        testSuite.registerTest(FullIrrigationTestTick31())
        testSuite.registerTest(FullIrrigationTestTick32())
        testSuite.registerTest(FullIrrigationTestTick33())
        testSuite.registerTest(FullIrrigationTestTick34())
        testSuite.registerTest(FullIrrigationTestTick35())
        testSuite.registerTest(FullIrrigationTestTick36())
        testSuite.registerTest(FullIrrigationTestTick37())
        testSuite.registerTest(FullIrrigationTestTick3Estimations())

//        testSuite.registerTest(MoistureIrrigationPart1())
//        testSuite.registerTest(MoistureIrrigationPart2())
//        testSuite.registerTest(MoistureIrrigationPart3())
//        testSuite.registerTest(MoistureIrrigationPart4())

//        testSuite.registerTest(HarvestCycleTick0To4())
//        testSuite.registerTest(HarvestCycleTick5To10())
//        testSuite.registerTest(HarvestCycleTick11To15())
        testSuite.registerTest(HarvestCycleTick16To20())

        testSuite.registerTest(MultiSowingAndIrrigationTest())

        // testSuite.registerTest(UnreachableMapSowingTest())

        // for (tick in 0..23) testSuite.registerTest(FullSimulation(tick))
    }
}
