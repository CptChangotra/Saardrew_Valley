// File: MachineTurnExecutor.kt
package de.unisaarland.cs.se.selab.farms

import de.unisaarland.cs.se.selab.map.MapController
import de.unisaarland.cs.se.selab.map.TileType
import de.unisaarland.cs.se.selab.utils.Logger
import de.unisaarland.cs.se.selab.utils.Tick

/**
 * Executes the turn for machines on a farm, managing action execution and machine movement.
 *
 * @property farm The farm where the machines operate.
 * @property mapController The map controller for pathfinding and tile management.
 */
class MachineTurnExecutor(
    private val farm: Farm,
    private val mapController: MapController
) {
    /**
     * Number of days available for actions in a single turn tick.
     */
    companion object {
        const val DAYS_IN_TICK = 14
    }

    /**
     * Performs the turn for a single machine, now with a context object.
     */
    fun performMachineTurn(
        currMachine: Machine,
        initialAction: Action,
        actions: MutableList<Action>,
        context: TurnContext
    ) {
        var currentAction: Action? = initialAction
        var dayCounter = 0
        var harvest = 0
        var harvestStats = 0
        var plant = ""

        while (dayCounter + currMachine.duration <= DAYS_IN_TICK && currentAction != null) {
            // machine unload stats for logging
            if (currentAction is HarvestAction) {
                harvestStats = currentAction.tile.harvestEstimate
                harvest += harvestStats
                plant = currentAction.plant.toString()
            }
            if (executeAction(currMachine, currentAction, context.schedule)) {
                // Action executed successfully
                currMachine.location = currentAction.tile.id
                dayCounter += currMachine.duration
                actions.remove(currentAction)
                context.plansExecuted.add(currentAction.id)
                context.usedMachines.add(currMachine.id)
                context.usedTiles.add(currentAction.tile.id)
                context.schedule.getOrPut(Tick.currentTick) { mutableListOf() }.remove(currentAction)
                // add the harvest to the stats
                if (harvestStats > 0) {
                    context.stats?.setPlantStats(currentAction.plant.toString(), harvestStats)
                    context.stats?.setFarmStats(currMachine.farmId, harvestStats)
                    harvestStats = 0
                }
                // try to find another action
                currentAction = findNextActionOfType(actions, currentAction, context, currMachine)
            } else {
                // Action could not be executed (i.e. if sowing is performed on the wrong tick)
                actions.remove(currentAction)
                currentAction = findNextActionOfType(actions, currentAction, context, currMachine)
            }
        }
        if (dayCounter > 0) {
            returnToShed(currMachine, harvest, plant)
        }
    }

    /**
     * Finds a suitable machine for the given action that is not already used and not stuck.
     *
     * @param action The action to be performed.
     * @param usedMachines The set of machine IDs that are already used in this turn.
     * @return A suitable machine if found, null otherwise.
     */
    fun findSuitableMachineForAction(
        action: Action,
        usedMachines: Set<Int>
    ): Machine? {
        val suitableMachines = farm.machines.filter {
            it.id !in usedMachines &&
                it !in farm.stuckMachines &&
                it.isReady == 0 &&
                canPerformAction(it, action) != null &&
                mapController.findPath(it.location, action.tile.id, false)
        }
        return suitableMachines.sortedWith(
            compareBy({ it.duration }, { it.id })
        ).firstOrNull()
    }

    /**
     * Checks if a machine is able and suitable for performing the given action.
     *
     * @param machine The machine to check.
     * @param action The action to be performed.
     * @return True if the machine is suitable, false otherwise.
     */
    fun checkIfMachineSuitableForAction(
        machine: Machine,
        action: Action
    ): Boolean {
        return canPerformAction(machine, action) != null &&
            mapController.findPath(machine.location, action.tile.id, false)
    }

    /**
     * Finds the next action of the same type and plant that is reachable within 2 tiles.
     *
     * @param actions The list of available actions.
     * @param act The current action to match type and plant.
     * @return The next matching action if found, null otherwise.
     */
    fun findNextActionOfType(
        actions: List<Action>,
        act: Action,
        context: TurnContext,
        machine: Machine
    ): Action? {
        return when (act) {
            is SowingAction -> findNextActionSamePlant(actions, act, context, machine)
            is HarvestAction -> findNextActionSamePlant(actions, act, context, machine, true)
            is WeedingAction -> findNextActionSameType(actions, act, context, machine)
            is CuttingAction -> findNextActionSameType(actions, act, context, machine)
            is MowingAction -> findNextActionSameType(actions, act, context, machine)
            is IrrigatingAction -> findNextActionIrrigation(actions, act, context, machine)
        }
    }

    /**
     * Finds the next action of the same type and plant that is reachable within 2 tiles.
     *
     * @param actions The list of available actions.
     * @param act The current action to match type and plant.
     * @return The next matching action if found, null otherwise.
     */
    private fun findNextActionSamePlant(
        actions: List<Action>,
        act: Action,
        context: TurnContext,
        machine: Machine,
        isMachineFull: Boolean = false
    ): Action? {
        val reachableTiles = mapController.getNeighbourTiles(act.tile.id, 2).map { it.id }
        val nextActions = actions.filter {
            it::class == act::class &&
                it.plant.toString() == act.plant.toString() &&
                it.id == act.id &&
                it.tile.id !in context.usedTiles &&
                it.tile.id in reachableTiles && // this will work if kotlin has lazy eval
                mapController.findPath(machine.location, it.tile.id, isMachineFull)
        }
        if (nextActions.isEmpty()) return null

        return nextActions.minByOrNull { it.tile.id }
    }

    /**
     * Finds the next action of the same type that is reachable within 2 tiles.
     *
     * @param actions The list of available actions.
     * @param act The current action to match type.
     * @return The next matching action if found, null otherwise.
     */
    private fun findNextActionSameType(
        actions: List<Action>,
        act: Action,
        context: TurnContext,
        machine: Machine
    ): Action? {
        val reachableTiles = mapController.getNeighbourTiles(act.tile.id, 2).map { it.id }
        val nextActions = actions.filter {
            it::class == act::class &&
                it.tile.id !in context.usedTiles &&
                canPerformAction(machine, it) != null &&
                it.tile.id in reachableTiles && // this will work if kotlin has lazy eval
                mapController.findPath(machine.location, it.tile.id, false)
        }
        if (nextActions.isEmpty()) return null

        return nextActions.minByOrNull { it.tile.id }
    }

    /**
     * Finds the next irrigation action.
     */
    private fun findNextActionIrrigation(
        actions: List<Action>,
        act: Action,
        context: TurnContext,
        machine: Machine
    ): Action? {
        val reachableTiles = mapController.getNeighbourTiles(act.tile.id, 2).map { it.id }
        val irrActions = actions.filter {
            it is IrrigatingAction &&
                it.tile.id !in context.usedTiles &&
                canPerformAction(machine, it) != null &&
                it.tile.id in reachableTiles &&
                mapController.findPath(machine.location, it.tile.id, false)
        }

        val fieldActions = irrActions.filter { it.tile.category == TileType.FIELD }
        return if (fieldActions.isNotEmpty()) {
            fieldActions.minByOrNull { it.tile.id }
        } else {
            irrActions.minByOrNull { it.tile.id }
        }
    }

    /**
     * Checks if the machine can perform the given action based on its capabilities and readiness.
     */
    private fun canPerformAction(machine: Machine, action: Action): Int? {
        return if (machine.actions.contains(action.toString()) &&
            machine.plants.contains(action.plant.toString()) &&
            machine.isReady == 0
        ) {
            machine.duration
        } else {
            null
        }
    }

    /**
     * Executes the given action for the specified machine and updates the schedule.
     *
     * @param machine The machine performing the action.
     * @param action The action to be executed.
     * @param schedule The global schedule of actions for all farms.
     * @return True if the action was executed successfully, false otherwise.
     */
    private fun executeAction(
        machine: Machine,
        action: Action,
        schedule: MutableMap<Int, MutableList<Action>>
    ): Boolean {
        machine.currentAction = action
        if (!action.execute(schedule, machine)) {
            return false
        }
        machine.location = action.tile.id
        if (action.id != -1) {
            Logger.farmAction(machine.id, action.toString(), action.tile.id, machine.duration)
            Logger.farmSowing(
                machine.id,
                action.plant.toString(),
                sowingPlanID = action.id
            )
        }
        return true
    }

    /**
     * Returns the machine to its last used shed and logs the unloading action.
     *
     * @param machine The machine to return to the shed.
     * @param harvest The amount of harvest to unload (0 if [machine] did not perform harvest).
     * @param plant The type of plant being unloaded (irrelevant [machine] did not perform harvest).
     * @return True if the machine successfully returned to the shed, false otherwise.
     */
    fun returnToShed(machine: Machine, harvest: Int, plant: String): Boolean {
        val isMachineFull = harvest > 0

        // Try last used shed first
        if (tryReturningToShed(machine, machine.lastShedLocation, isMachineFull, harvest, plant)) {
            return true
        }

        // If that fails, try other sheds
        for (shed in farm.sheds.sortedBy { it }) {
            if (shed != machine.lastShedLocation &&
                tryReturningToShed(machine, shed, isMachineFull, harvest, plant)
            ) {
                return true
            }
        }

        // If no shed is reachable
        farm.stuckMachines += machine
        machine.isReady = -1 // mark as broken
        Logger.farmMachineReturnFailed(machine.id)
        return false
    }

    /**
     * Tries to return a machine to a specific shed.
     */
    private fun tryReturningToShed(
        machine: Machine,
        shedLocation: Int,
        isMachineFull: Boolean,
        harvest: Int,
        plant: String
    ): Boolean {
        if (mapController.findPath(machine.location, shedLocation, isMachineFull)) {
            machine.location = shedLocation
            machine.lastShedLocation = shedLocation
            Logger.farmMachineReturn(machine.id, machine.location)
            if (isMachineFull) {
                Logger.farmMachineUnload(machine.id, plant, harvest)
            }
            return true
        }
        return false
    }
}
