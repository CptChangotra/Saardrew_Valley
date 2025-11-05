package de.unisaarland.cs.se.selab.farms

import de.unisaarland.cs.se.selab.plant.FieldPlant
import de.unisaarland.cs.se.selab.plant.PlantationPlant

/**
 * Filters the list to find only HarvestingActions.
 * @return A new list containing only HarvestingAction objects.
 */
fun List<Action>.filterHarvesting(): List<Action> {
    return this.filterIsInstance<HarvestAction>()
}

/**
 * Filters the list to find only WeedingActions.
 * @return A new list containing only WeedingAction objects.
 */
fun List<Action>.filterWeeding(): List<Action> {
    return this.filterIsInstance<WeedingAction>()
}

/**
 * Filters the list to find only IrrigatingActions.
 * @return A new list containing only WeedingAction objects.
 */
fun List<Action>.filterIrrigating(): List<Action> {
    return this.filterIsInstance<IrrigatingAction>()
}

/**
 * Filters the list to find only MowingActions.
 * @return A new list containing only WeedingAction objects.
 */
fun List<Action>.filterMowing(): List<Action> {
    return this.filterIsInstance<MowingAction>()
}

/**
 * Filters the list to find only CuttingActions.
 * @return A new list containing only WeedingAction objects.
 */
fun List<Action>.filterCutting(): List<Action> {
    return this.filterIsInstance<CuttingAction>()
}

/**
 * Filters the list to find only actions with PlantationPlants.
 * @return A new list containing only actions with PlantationPlants.
 */
fun List<Action>.filterPlantationPlant(): List<Action> {
    return this.filter { it.plant is PlantationPlant }
}

/**
 * Filters the list to find only actions with FieldPlants.
 * @return A new list containing only actions with FieldPlants.
 */
fun List<Action>.filterFieldPlant(): List<Action> {
    return this.filter { it.plant is FieldPlant }
}
