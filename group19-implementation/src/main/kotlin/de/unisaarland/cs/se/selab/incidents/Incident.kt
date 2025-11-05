package de.unisaarland.cs.se.selab.incidents

import de.unisaarland.cs.se.selab.map.MapController

/**
 * absteact classs for incidents.
 */
sealed class Incident(val id: Int, val tick: Int, val mapController: MapController) {
    /**
     * function to apply incidents.
     */
    abstract fun apply(): Unit
}
