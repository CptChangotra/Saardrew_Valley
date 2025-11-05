package de.unisaarland.cs.se.selab.farms

import de.unisaarland.cs.se.selab.map.Tile
import de.unisaarland.cs.se.selab.plant.Plant
import de.unisaarland.cs.se.selab.utils.Logger

/**
 * Represents a mowing action to be performed by a machine on a specific tile.
 *
 * @property plant The plant associated with the mowing action.
 * @property startTick The tick when the action starts.
 * @property endTick The tick when the action ends.
 * @property tile The tile where the action is performed.
 * @property id Unique identifier for the action.
 */
class MowingAction(plant: Plant, startTick: Int, endTick: Int, tile: Tile) : Action(
    plant,
    startTick,
    endTick,
    tile,
    DEFAULT_VAL
) {
    override fun execute(schedule: MutableMap<Int, MutableList<Action>>, machine: Machine): Boolean {
        if (tile.harvestEstimate == 0) return false
        Logger.farmAction(machine.id, MOWING, tile.id, machine.duration)
        return true
    }

    override fun toString(): String = "MOWING"
}
