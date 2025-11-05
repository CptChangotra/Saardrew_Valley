package de.unisaarland.cs.se.selab.farms

import de.unisaarland.cs.se.selab.map.Tile
import de.unisaarland.cs.se.selab.plant.Plant
import de.unisaarland.cs.se.selab.utils.Logger

/**
 * Represents a weeding action to be performed on a specific tile.
 *
 * @param plant The plant associated with the weeding action.
 * @param startTick The tick when the action is scheduled to start.
 * @param endTick The tick when the action is scheduled to end.
 * @param tile The tile where the weeding action will take place.
 */
class WeedingAction(plant: Plant, startTick: Int, endTick: Int, tile: Tile) : Action(
    plant,
    startTick,
    endTick,
    tile,
    DEFAULT_VAL
) {
    override fun execute(schedule: MutableMap<Int, MutableList<Action>>, machine: Machine): Boolean {
        if (tile.harvestEstimate == 0) return false
        Logger.farmAction(machine.id, WEEDING, tile.id, machine.duration)
        return true
    }

    override fun toString(): String = "WEEDING"
}
