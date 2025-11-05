package de.unisaarland.cs.se.selab.incidents

import de.unisaarland.cs.se.selab.farms.Machine
import de.unisaarland.cs.se.selab.map.MapController
import de.unisaarland.cs.se.selab.utils.Logger
import kotlin.math.max

/**
 * Represents a machine broken event.
 */
public final class BrokenMachine(
    id: Int,
    tick: Int,
    mapController: MapController,
    val machineId: Int,
    val duration: Int,
    val machines: List<Machine>
) : Incident(
    id,
    tick,
    mapController
) {
    override fun apply() {
        val machine = this.machines.firstOrNull { it.id == this.machineId } ?: error("machine should not be null")
        machine.isReady =
            if (machine.isReady == -1 || this.duration == -1) -1 else max(duration, machine.isReady)
        Logger.incident(id, "BROKEN_MACHINE", listOf(machine.location))
    }
}
