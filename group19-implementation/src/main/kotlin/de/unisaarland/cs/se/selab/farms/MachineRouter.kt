package de.unisaarland.cs.se.selab.farms

import de.unisaarland.cs.se.selab.map.MapController
import de.unisaarland.cs.se.selab.map.TileType
import de.unisaarland.cs.se.selab.plant.FieldPlant
import de.unisaarland.cs.se.selab.utils.Logger
import de.unisaarland.cs.se.selab.utils.Stats
import de.unisaarland.cs.se.selab.utils.Tick

/**
 * Orchestrates the execution of farm actions by coordinating machines.
 *
 * This class is the main entry point for processing a farm's actions during a tick.
 * It decides which actions to perform (sowing plans vs. other scheduled actions)
 * and delegates the low-level execution details to a [MachineTurnExecutor].
 *
 * @param farm The farm this router belongs to.
 * @param mapController The controller for the game map, used for pathfinding.
 * @param sowingPlans A mutable map holding the sowing actions derived from [SowingPlan]s.
 */
class MachineRouter(
    val farm: Farm,
    val mapController: MapController,
    var sowingPlans: MutableMap<Int, MutableList<Action>>,
) {
    private val actionExecutor = MachineTurnExecutor(farm, mapController)
    val plansExecuted: MutableSet<Int> = mutableSetOf()
    val usedMachines: MutableSet<Int> = mutableSetOf()
    val usedTiles: MutableSet<Int> = mutableSetOf()
    val debug = false

    /**
     * Executes all non-sowing-plan actions scheduled for the current tick.
     *
     * This function processes actions like harvesting, cutting, irrigating, weeding, and mowing.
     * It respects the priority rules defined in the documentation: farm-level actions are
     * prioritized by the lowest tile ID, and machine-level actions are prioritized by the
     * lowest machine ID.
     *
     * @param schedule The global schedule of actions for all farms.
     * @param stats The global placeholder for storing statistics.
     */
    fun executeOthers(schedule: MutableMap<Int, MutableList<Action>>, stats: Stats) {
//        if (debug) { // prints out the schedule for debugging purposes
//            println("\n--- Schedule before executing other actions ---")
//            for (tick in schedule.keys.sorted()) {
//                val actions = schedule[tick]
//                println("Tick $tick: ${actions?.joinToString { action -> action.toString() }}")
//            }
//        }
        val actionsForTick = schedule[Tick.currentTick] ?: return
        val context = TurnContext(schedule, usedMachines, plansExecuted, usedTiles, stats)

        // --- Farm Level Actions ---
        val harvestingActions = actionsForTick.filterHarvesting()
        performActions(context, harvestingActions.filterPlantationPlant().toMutableList())
        performActions(context, harvestingActions.filterFieldPlant().toMutableList())
        performActions(context, actionsForTick.filterCutting().toMutableList())

        // --- Machine Level Actions ---
        // irrigation fields > plantations
        for (machine in getUnusedMachines()) {
            performIrrigationCycle(context, actionsForTick.filterIrrigating().toMutableList(), machine, true)
            performMachineLevelActions(context, actionsForTick.filterWeeding().toMutableList(), machine)
            performIrrigationCycle(context, actionsForTick.filterIrrigating().toMutableList(), machine, false)
            performMachineLevelActions(context, actionsForTick.filterMowing().toMutableList(), machine)
        }
        usedMachines.clear()
        usedTiles.clear()
    }

    /**
     * Performs a full irrigation cycle for a given priority type.
     * It iterates through all unused machines and assigns them a task if a suitable
     * tile of the priority type is found.
     */
    fun performIrrigationCycle(
        context: TurnContext,
        allIrrigationActions: MutableList<Action>,
        machine: Machine,
        fields: Boolean
    ) {
        if (allIrrigationActions.isEmpty() || machine.id in context.usedMachines) return
        // Find the best initial action for this machine, matching the priority type.
        // first try to find the lowest id field
        val possibleActions = allIrrigationActions
            .filter {
                it.tile.id !in context.usedTiles &&
                    actionExecutor.checkIfMachineSuitableForAction(machine, it)
            }
        val currentAction = possibleActions
            .filter { it.tile.category == TileType.FIELD }
            .minByOrNull { it.tile.id }
            ?: if (!fields) {
                possibleActions
                    .filter { it.tile.category == TileType.PLANTATION }
                    .minByOrNull { it.tile.id }
            } else {
                null
            }
        currentAction ?: return
        actionExecutor.performMachineTurn(machine, currentAction, allIrrigationActions, context)
    }

    /**
     * Executes the sowing plans scheduled for the current tick.
     *
     * This function first unpacks any new sowing plans that are due. It then processes the
     * resulting sowing actions, prioritizing them by their start tick and then by plan ID.
     *
     * @param schedule The global schedule of actions for all farms.
     */
    fun executePlans(schedule: MutableMap<Int, MutableList<Action>>) {
//        if (debug) { // prints out the schedule for debugging purposes
//            println("\n--- Schedule before sowing ---")
//            for (tick in schedule.keys.sorted()) {
//                val actions = schedule[tick]
//                println("Tick $tick: ${actions?.joinToString { action -> action.toString() }}")
//            }
//        }
        unpackPlansForCurrentTick()
        val actions = sowingPlans[Tick.currentTick]?.toMutableList() ?: return
        val context = TurnContext(schedule, usedMachines, plansExecuted, usedTiles)
        while (true) {
            val currentAction = findHighestPriorityAction(actions) ?: break
            val currMachine = actionExecutor.findSuitableMachineForAction(currentAction, context.usedMachines)
            if (currMachine != null) {
                actionExecutor.performMachineTurn(currMachine, currentAction, actions, context)
            } else {
                actions.remove(currentAction) // Action is impossible, try the next one.
            }
        }
        removeExecutedPlans()
    }

    /**
     * Performs a list of farm-level actions, prioritizing by the lowest tile ID.
     *
     * For a given list of actions (e.g., harvesting), this function repeatedly finds the
     * action on the lowest tile ID and assigns a suitable machine to execute it.
     *
     * @param context The shared context for the current turn.
     * @param actions The mutable list of actions to perform.
     */
    fun performActions(context: TurnContext, actions: MutableList<Action>) {
        if (actions.isEmpty()) return
        while (true) {
            val currentAction = actions
                .filter { it.tile.id !in context.usedTiles }
                .minByOrNull { it.tile.id } ?: break
            val currMachine = actionExecutor.findSuitableMachineForAction(currentAction, context.usedMachines)

            if (currMachine != null) {
                actionExecutor.performMachineTurn(currMachine, currentAction, actions, context)
            } else {
                actions.remove(currentAction) // Action is impossible, try the next one.
            }
        }
    }

    /**
     * Performs a list of machine-level actions
     * For a given list of actions (e.g., weeding)
     *
     * @param context The shared context for the current turn.
     * @param actionsOfTypeForTick The list of actions to perform.
     */
    fun performMachineLevelActions(context: TurnContext, actionsOfTypeForTick: MutableList<Action>, machine: Machine) {
        if (actionsOfTypeForTick.isEmpty() || machine.id in context.usedMachines) return
        // Find the single best action this specific machine can do from the list.
        val bestActionForThisMachine = actionsOfTypeForTick
            .filter { action ->
                action.tile.id !in context.usedTiles &&
                    actionExecutor.checkIfMachineSuitableForAction(machine, action)
            }
            .minByOrNull { it.tile.id } ?: return

        actionExecutor.performMachineTurn(machine, bestActionForThisMachine, actionsOfTypeForTick, context)
    }

    /**
     * Finds the action with the highest priority from a list. All actions have the same type
     *
     * Priority is determined first by the `startTick` and then by the action's `id`.
     *
     * @param actions The list of actions to search through.
     * @return The highest-priority [Action], or null if the list is empty.
     */
    fun findHighestPriorityAction(actions: List<Action>?): Action? {
        return actions
            ?.filter { it.tile.id !in usedTiles }
            // so we cant sow the same tile twice in one tick, although it cant be sown twice
            ?.minWithOrNull(compareBy({ it.startTick }, { it.id }, { it.tile.id }))
    }

    /**
     * Checks for due [SowingPlan]s and unpacks them into [SowingAction]s.
     *
     * This function iterates through the farm's list of sowing plans. If a plan's start
     * tick is on or before the current tick, its actions are generated and added to the
     * `sowingPlans` map.
     */
    private fun unpackPlansForCurrentTick() {
        val activatedPlans = mutableListOf<Int>()
        for (plan in farm.sowingPlans) {
            if (plan.startTick <= Tick.currentTick) {
                val newActions = unpackActionsForPlan(plan)
                // Merge new actions with any existing actions for that start tick.
                sowingPlans.merge(
                    Tick.currentTick,
                    newActions.toMutableList()
                ) { oldList, newList -> (oldList + newList) as? MutableList<Action>? }
                activatedPlans += plan.id
            }
        }
        Logger.farmActiveSowingPlan(farm.id, activatedPlans)
    }

    /**
     * Generates a list of [SowingAction]s based on a single [SowingPlan].
     *
     * The plan can specify target tiles either by a list of IDs or by a center
     * tile and a radius.
     *
     * @param plan The [SowingPlan] to convert into actions.
     * @return A list of [SowingAction]s.
     */
    private fun unpackActionsForPlan(plan: SowingPlan): List<Action> {
        val tiles = when {
            plan.tileIDs != null -> {
                plan.tileIDs.map {
                    mapController.getTile(it)
                }
            }
            plan.location != null && plan.radius != null -> {
                listOf(mapController.getTile(plan.location)) +
                    mapController.getNeighboursGeometries(plan.location, plan.radius)
            }
            else -> {
                emptyList()
            }
        }.asSequence()
            .filter { it.farm == farm.id } // only consider tiles that belong to this farm
            .filter { it.category == TileType.FIELD } // only consider field tiles
            .filter { it.plant == null } // only consider empty tiles
            .toList() // Convert back to a list only after all filtering is done.
        // only create the sowing action for the tiles with FieldPlant on them
        val actions = tiles.mapNotNull { tile ->
            val fieldPlant: FieldPlant?
            tile.possiblePlants?.let { possiblePlants ->
                fieldPlant = possiblePlants.find { it.toString() == plan.plant.toString() }
                // only create the action if the plant is not null
                fieldPlant?.let {
                    SowingAction(
                        it,
                        plan.startTick,
                        plan.startTick,
                        tile,
                        plan.id
                    )
                }
            }
        }
        // at this point only sowing actions with valid plants are in the list
        return actions
    }

    /**
     * Removes all executed sowing plans from the farm's list of plans.
     *
     * This function filters the farm's `sowingPlans` list, retaining only those
     * plans whose IDs are not present in the `plansExecuted` set.
     */
    private fun removeExecutedPlans() {
        if (plansExecuted.isEmpty()) return
        farm.sowingPlans = farm.sowingPlans.filter { it.id !in plansExecuted }
        sowingPlans.clear()
    }

    /**
     * Retrieves a list of machines that were not used or stuck.
     */
    fun getUnusedMachines(): List<Machine> {
        return farm.machines
            .filter { it.id !in usedMachines && it !in farm.stuckMachines }
            .sortedWith(compareBy({ it.duration }, { it.id }))
    }
}
