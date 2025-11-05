package de.unisaarland.cs.se.selab.farms

import de.unisaarland.cs.se.selab.map.Tile
import de.unisaarland.cs.se.selab.plant.Plant
import de.unisaarland.cs.se.selab.utils.Logger
import de.unisaarland.cs.se.selab.utils.Tick

/**
 * Represents an action where a machine irrigates a tile to its maximum soil moisture capacity.
 *
 * @param plant The plant associated with the action (not directly used in irrigation).
 * @param startTick The tick when the action starts.
 * @param endTick The tick when the action ends.
 * @param tile The tile to be irrigated.
 */
class IrrigatingAction(plant: Plant, startTick: Int, endTick: Int, tile: Tile) : Action(
    plant,
    startTick,
    endTick,
    tile,
    DEFAULT_VAL
) {
    override fun execute(schedule: MutableMap<Int, MutableList<Action>>, machine: Machine): Boolean {
        Logger.farmAction(machine.id, IRRIGATING, tile.id, machine.duration)
        tile.soilMoisture = tile.maxCapacity ?: error("plantation and field always have soil capacity")
        val plant = tile.plant ?: error("plant is null")
        if (tile.soilMoisture < plant.moisture() && tile.startOfTickEstimate != 0) {
            val new = IrrigatingAction(plant, startTick, endTick, tile)
            schedule.getOrPut(Tick.currentTick) { mutableListOf() }.add(new)
        }
        return true
    }

    override fun toString(): String = "IRRIGATING"
}
