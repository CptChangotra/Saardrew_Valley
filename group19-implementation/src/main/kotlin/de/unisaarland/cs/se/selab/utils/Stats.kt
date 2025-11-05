package de.unisaarland.cs.se.selab.utils

/**
 * Stores simulation-wide statistics for final reporting
 * @param statsPerFarm map from farm id to total harvest for farm
 * @param statsPerPlant map from plant name to total harvest for plant
 */
class Stats(var statsPerFarm: Map<Int, Int>, var statsPerPlant: Map<String, Int>) {
    /**
     * Getter for plant stats
     */
    fun getPlantStats(plant: String): Int = statsPerPlant[plant] ?: 0

    /**
     * Getter for farm stats
     */
    fun getFarmStats(farmId: Int): Int = statsPerFarm[farmId] ?: 0

    /**
     * Setter for plant stats
     */
    fun setPlantStats(plant: String, value: Int) {
        statsPerPlant += plant to (statsPerPlant[plant] ?: 0) + value
    }

    /**
     * Setter for farm stats
     */
    fun setFarmStats(farm: Int, value: Int) {
        statsPerFarm += farm to (statsPerFarm[farm] ?: 0) + value
    }
}
