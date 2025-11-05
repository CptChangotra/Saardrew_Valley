package de.unisaarland.cs.se.selab.farms

import de.unisaarland.cs.se.selab.map.Tile
import de.unisaarland.cs.se.selab.plant.Plant
import de.unisaarland.cs.se.selab.utils.Tick

const val WEEDING = "WEEDING"
const val CUTTING = "CUTTING"
const val MOWING = "MOWING"
const val IRRIGATING = "IRRIGATING"
const val HARVESTING = "HARVESTING"

/**
 * Represents an action that can be performed by a machine on a farm.
 *
 * Each action has an associated plant, start and end ticks, the tile it is performed on,
 * and a unique identifier. The action can be executed by a machine, which may modify
 * the farm's state and the global schedule of actions.
 *
 * @property plant The plant associated with the action.
 * @property startTick The tick when the action starts.
 * @property endTick The tick when the action ends.
 * @property tile The tile where the action is performed.
 * @property id A unique identifier for the action. Should be zero if not part of a plan.
 */
sealed class Action(
    val plant: Plant,
    val startTick: Int,
    val endTick: Int,
    val tile: Tile,
    val id: Int // zero should be given on creation if not part
) {
    /** Default value for uninitialized properties. */
    companion object {
        var DEFAULT_VAL = -1
    }

    /**
     * Executes the action using the provided machine.
     *
     * This method modifies the given schedule and may change the state of the machine
     * and the farm. It returns true if the action was successfully executed, false otherwise.
     *
     * @param schedule The global schedule of actions for all farms.
     * @param machine The machine performing the action.
     * @return True if the action was executed successfully, false otherwise.
     */
    abstract fun execute(
        schedule: MutableMap<Int, MutableList<Action>>,
        machine: Machine
    ): Boolean

    /**
     * Calculates the scheduled tick according to which the action must be stored in the schedule
     */
    fun scheduleTick(action: Action): Int {
        val planned = action.startTick
        val yearTick = Tick.yTick
        val simTick = Tick.currentTick
        val offset = if (planned < yearTick) {
            YEAR + planned - yearTick
        } else {
            planned - yearTick
        }
        return simTick + offset
    }
}
