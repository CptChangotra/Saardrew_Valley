package de.unisaarland.cs.se.selab.farms

import de.unisaarland.cs.se.selab.utils.Stats

/**
 * Holds the shared state and context for a machine's turn execution.
 */
data class TurnContext(
    val schedule: MutableMap<Int, MutableList<Action>>,
    val usedMachines: MutableSet<Int>,
    val plansExecuted: MutableSet<Int>,
    val usedTiles: MutableSet<Int>,
    val stats: Stats? = null
)
