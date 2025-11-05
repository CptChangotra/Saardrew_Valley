package de.unisaarland.cs.se.selab.farms

import de.unisaarland.cs.se.selab.map.Tile
import de.unisaarland.cs.se.selab.map.TileType
import de.unisaarland.cs.se.selab.plant.Plant
import de.unisaarland.cs.se.selab.plant.PlantationPlant
import de.unisaarland.cs.se.selab.utils.Logger
import de.unisaarland.cs.se.selab.utils.Tick

const val YEAR = 24
const val FALLOW_PERIOD = 5

/**
 * Represents a harvesting action for a specific plant on a given tile.
 *
 * This action is responsible for managing the harvesting process, including
 * scheduling subsequent actions like cutting and mowing for plantation plants.
 *
 * @param plant The plant to be harvested.
 * @param startTick The tick when the action starts.
 * @param endTick The tick when the action ends.
 * @param tile The tile where the action takes place.
 */
class HarvestAction(
    plant: Plant,
    startTick: Int,
    endTick: Int,
    tile: Tile
) : Action(
    plant,
    startTick,
    endTick,
    tile,
    DEFAULT_VAL
) {
    override fun execute(schedule: MutableMap<Int, MutableList<Action>>, machine: Machine): Boolean {
        if (tile.plant != null || tile.harvestEstimate != 0) {
            Logger.farmAction(machine.id, this.toString(), tile.id, machine.duration)
            Logger.farmHarvest(machine.id, plant.toString(), tile.startOfTickEstimate)
            removeActions(schedule)
            tile.harvestEstimate = ZERO
            tile.startOfTickEstimate = ZERO
            if (tile.category == TileType.FIELD) {
                tile.plant = null
                tile.fallowPeriod = FALLOW_PERIOD
            }
            // all actions are created on the moment of plantation plant re-setting
            // or for field plant - when sowing is performed
            return true
        }
        return false
    }

    /**
     * Schedules new actions (cutting and mowing) for plantation plants
     */
    fun plantationHarvest(schedule: MutableMap<Int, MutableList<Action>>) {
        plant as PlantationPlant
        // create only first cutting action
        val actionC = CuttingAction(plant, plant.cuttingPeriod[0].first, plant.cuttingPeriod[0].second, tile)
        var tick = scheduleTick(actionC)
        if (schedule[tick] == null || schedule[tick]?.any { it is CuttingAction && it.tile == tile } == false) {
            schedule.getOrPut(tick) { mutableListOf() }.add(actionC)
        }

        // create mowing actions
        var actionM = MowingAction(plant, plant.mowingPeriod.first, plant.mowingPeriod.first, tile)
        tick = scheduleTick(actionM)
        if (schedule[tick] == null || schedule[tick]?.any { it is MowingAction && it.tile == tile } == false) {
            schedule.getOrPut(tick) { mutableListOf() }.add(actionM)
        }
        if (plant != PlantationPlant.CHERRY) { // cherry requires mowing only once
            actionM = MowingAction(plant, plant.mowingPeriod.second, plant.mowingPeriod.second, tile)
            tick = scheduleTick(actionM)
            if (schedule[tick] == null || schedule[tick]?.any { it is MowingAction && it.tile == tile } == false) {
                schedule.getOrPut(tick) { mutableListOf() }.add(actionM)
            }
        }
    }

    /**
     * Iterates over the map with actions and removes any that are planned for this tile
     */
    fun removeActions(schedule: MutableMap<Int, MutableList<Action>>) {
        var addIrrigation = false
        if (schedule[Tick.currentTick] != null &&
            schedule[Tick.currentTick]?.any { it is IrrigatingAction && it.tile == tile } == true
        ) {
            addIrrigation = true
        }
        schedule.forEach { (tick, actions) ->
            val filtered = actions.filter { it.tile.id != this.tile.id }
            schedule[tick] = filtered.toMutableList()
        }
        if (addIrrigation && plant is PlantationPlant) {
            val irr = IrrigatingAction(plant, Tick.currentTick, Tick.currentTick, tile)
            schedule.getOrPut(Tick.currentTick) { mutableListOf() }.add(irr)
        }
    }

    /**
     * Harvest is always scheduled for next tick
     */
    fun scheduleHarvestTick(): Int {
        val planned = startTick
        val yearTick = Tick.yTick
        val simTick = Tick.currentTick
        return planned + simTick + (YEAR - yearTick)
    }

    override fun toString(): String = "HARVESTING"
}
