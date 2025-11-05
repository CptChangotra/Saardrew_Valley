package de.unisaarland.cs.se.selab.farms

import de.unisaarland.cs.se.selab.incidents.PERCENT
import de.unisaarland.cs.se.selab.map.Tile
import de.unisaarland.cs.se.selab.plant.FieldPlant
import de.unisaarland.cs.se.selab.plant.Plant
import de.unisaarland.cs.se.selab.utils.Tick

const val LATE_SOWING = 80
const val NINE = 9

/**
 * Represents a sowing action to be performed by a machine on a farm.
 *
 * @param plant The plant to be sown.
 * @param startTick The tick when the action is scheduled to start.
 * @param endTick The tick when the action is scheduled to end.
 * @param tile The tile where the action is to be performed.
 * @param id The unique identifier for the action, typically linked to a sowing plan.
 */
class SowingAction(
    plant: Plant,
    startTick: Int,
    endTick: Int,
    tile: Tile,
    id: Int
) : Action(plant, startTick, endTick, tile, id) {
    override fun execute(schedule: MutableMap<Int, MutableList<Action>>, machine: Machine): Boolean {
        // NOTE: not need, you already convert later
        // plant is supposed to be a FieldPlant
//        assert(plant is FieldPlant)

        if (!sowingPossible()) {
            return false
        }

        tile.plant = plant
        tile.startOfTickEstimate = plant.harvestEstimate()
        setHarvestEstimate()
        plant.lastSowingTick = Tick.currentTick

        // create harvesting actions
        plant as FieldPlant
        val actionH = HarvestAction(plant, plant.harvestingPeriod.first, plant.harvestingPeriod.second, tile)
        schedule.getOrPut(scheduleTick(actionH)) { mutableListOf() }.add(actionH)

        // create weeding actions
        createWeeding(schedule, actionH)
        return true // sowing successfully performed
    }

    /**
     * Returns true if sowing can be performed at the current tick (including late sowing)
     */
    private fun sowingPossible(): Boolean {
        plant as FieldPlant
        var flag = true // sowing is possible
        // current tick is either too early or too late (including late sowing)
        if (Tick.yTick < plant.sowingPeriod.first || Tick.yTick > plant.sowingPeriod.second + TWO) {
            flag = false
        } else if (tile.fallowPeriod != ZERO) {
            flag = false
        } else if (tile.possiblePlants?.contains(plant) == false) {
            flag = false
        } else if (tile.plant != null) {
            flag = false
        }
        return flag
    }

    /**
     * Sets the correct harvest estimate for possibly late sowing
     */
    private fun setHarvestEstimate() {
        val daysLate = Tick.yTick - (this.plant as FieldPlant).sowingPeriod.second
        when (daysLate) {
            ONE -> {
                tile.harvestEstimate = plant.harvestEstimate() * LATE_SOWING / PERCENT
            }
            TWO -> {
                tile.harvestEstimate = plant.harvestEstimate() * LATE_SOWING / PERCENT * LATE_SOWING / PERCENT
            }
            else -> { // if zero or negative => harvest is not reduced
                tile.harvestEstimate = plant.harvestEstimate()
            }
        }
        tile.startOfTickEstimate = plant.harvestEstimate()
//        tile.startOfTickEstimate = tile.harvestEstimate // LATE SOWING MUST BE LOGGED AS CHANGED
    }

    private fun createWeeding(schedule: MutableMap<Int, MutableList<Action>>, action: HarvestAction) {
        when (plant) {
            FieldPlant.OAT -> { // require WEEDING during each of the first three ticks after SOWING
                var actionW = WeedingAction(plant, Tick.yTick + ONE, Tick.yTick + ONE, tile)
                schedule.getOrPut(scheduleTick(actionW)) { mutableListOf() }.add(actionW)
                actionW = WeedingAction(plant, Tick.yTick + TWO, Tick.yTick + TWO, tile)
                schedule.getOrPut(scheduleTick(actionW)) { mutableListOf() }.add(actionW)
                actionW = WeedingAction(plant, Tick.yTick + THREE, Tick.yTick + THREE, tile)
                schedule.getOrPut(scheduleTick(actionW)) { mutableListOf() }.add(actionW)
            }
            FieldPlant.WHEAT -> { // requires WEEDING three and nine ticks after SOWING
                var actionW = WeedingAction(plant, Tick.yTick + THREE, Tick.yTick + THREE, tile)
                schedule.getOrPut(scheduleTick(actionW)) { mutableListOf() }.add(actionW)
                actionW = WeedingAction(plant, Tick.yTick + NINE, Tick.yTick + NINE, tile)
                schedule.getOrPut(scheduleTick(actionW)) { mutableListOf() }.add(actionW)
            }
            FieldPlant.POTATO, FieldPlant.PUMPKIN -> {
                plant as FieldPlant
                for (weedingTick in (Tick.currentTick + TWO)..(scheduleTick(action) + THREE) step 2) {
                    val weedingAction = WeedingAction(
                        plant = plant,
                        startTick = weedingTick, // does not matter what we store here as we never read this field
                        endTick = weedingTick,
                        tile = tile
                    )
                    // weeding tick is already "scheduled" tick
                    schedule.getOrPut(weedingTick) { mutableListOf() }.add(weedingAction)
                }
            }
            else -> {
                require(false) // something is very wrong
            }
        }
    }

    override fun toString(): String = "SOWING"
}
