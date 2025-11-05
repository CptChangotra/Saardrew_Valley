package de.unisaarland.cs.se.selab.farms

import de.unisaarland.cs.se.selab.map.Tile
import de.unisaarland.cs.se.selab.plant.Plant
import de.unisaarland.cs.se.selab.utils.Logger

/** Action representing the cutting of a plant on a specific tile.
 *
 * @property plant The plant to be cut.
 * @property startTick The tick when the action starts.
 * @property endTick The tick when the action ends.
 * @property tile The tile where the action takes place.
 */
class CuttingAction(plant: Plant, startTick: Int, endTick: Int, tile: Tile) : Action(
    plant,
    startTick,
    endTick,
    tile,
    DEFAULT_VAL
) {
    override fun execute(schedule: MutableMap<Int, MutableList<Action>>, machine: Machine): Boolean {
        if (tile.harvestEstimate == 0) return false
        Logger.farmAction(machine.id, CUTTING, tile.id, machine.duration)
        return true
    }

    override fun toString(): String = "CUTTING"
}
