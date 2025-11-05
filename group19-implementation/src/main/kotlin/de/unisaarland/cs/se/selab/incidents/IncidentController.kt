package de.unisaarland.cs.se.selab.incidents

import de.unisaarland.cs.se.selab.utils.Tick

/**
 * Controller for incidents.
 */
class IncidentController(val incidents: List<Incident>) {
    /**
     * Calls all incidents for the current tick
     */
    fun updateOnTick() {
        incidents
            .filter { it.tick == Tick.currentTick }
            .sortedBy { it.id }
            .forEach { it.apply() }
    }
}
