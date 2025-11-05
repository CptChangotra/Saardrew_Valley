package de.unisaarland.cs.se.selab.simulation

import de.unisaarland.cs.se.selab.cloud.CloudController
import de.unisaarland.cs.se.selab.farms.FarmController
import de.unisaarland.cs.se.selab.farms.ONE
import de.unisaarland.cs.se.selab.farms.TWENTY_FIVE
import de.unisaarland.cs.se.selab.farms.emptyFarmsLogs
import de.unisaarland.cs.se.selab.incidents.IncidentController
import de.unisaarland.cs.se.selab.map.MapController
import de.unisaarland.cs.se.selab.utils.Logger
import de.unisaarland.cs.se.selab.utils.Stats
import de.unisaarland.cs.se.selab.utils.Tick
import de.unisaarland.cs.se.selab.utils.YearTick

/**
 * Main controller class
 */
class SimulationController(
    val maxTicks: Int,
    val mapController: MapController,
    val cloudController: CloudController,
    val farmController: List<FarmController>, // must be already sorted
    val incidentController: IncidentController,
    val simulationStats: Stats
) {
    /**
     * Main entry point of the simulation
     */
    fun runSimulation() {
        Logger.simulationStart(Tick.yTick)
        while (Tick.currentTick < maxTicks) {
            tick()
        }
        Logger.simulationEnd(Tick.currentTick) // QUESTION: year or curr tick?

        Logger.simulationStatisticsStart()
        printStats()
    }

    /**
     *
     */
    private fun tick() {
        Logger.tickStart(Tick.currentTick, Tick.yTick)
        if (Tick.yTick == YearTick.NOVEMBER_1) {
            mapController.resetPlantationHarvestEstimate(farmController)
        }
        mapController.tick()
        cloudController.updateOnTick()
        farmController.forEach { farmController ->
            farmController.updateOnTick()
        }
        incidentController.updateOnTick()

//        farmController.forEach { farmController ->
//            farmController.updateHarvest()
//        }

        farmController
            .fold(emptyFarmsLogs) { log, farmC -> log.merge(farmC.updateHarvest()) }
            .log()

        Tick.currentTick++
        Tick.yTick++
        if (Tick.yTick == TWENTY_FIVE) {
            Tick.yTick = ONE
        }
    }

    private fun printStats() {
        // stats per farm
        farmController.forEach { farmController ->
            Logger.simulationStatisticsFarm(
                farmController.getFarmId(),
                simulationStats.getFarmStats(farmController.getFarmId())
            )
        }
        // stats per plant
        val plants = listOf("POTATO", "WHEAT", "OAT", "PUMPKIN", "APPLE", "GRAPE", "ALMOND", "CHERRY")
        for (plant in plants) {
            Logger.simulationStatisticsPlant(plant, simulationStats.getPlantStats(plant))
        }
        // remaining stats
        var total = 0
        farmController.forEach { farmController ->
            total += farmController.getRemainingHarvestEstimate()
        }
        Logger.simulationStatisticsRemaining(total)
    }
}
