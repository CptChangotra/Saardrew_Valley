package de.unisaarland.cs.se.selab.farms

/**
 * Represents a machine used in farming operations.
 *
 * @property id Unique identifier for the machine.
 * @property name Name of the machine.
 * @property actions List of action types (by simple class name) that the machine can perform.
 * @property plant List of plant types (by simple class name) that the machine can handle.
 * @property duration Number of days required to perform an action with this machine.
 * @property lastShedLocation The tile ID of the last shed location where the machine was stationed.
 * @property location The current tile ID where the machine is located.
 * @property currentAction The action currently being performed by the machine, if any.
 * @property farmId The ID of the farm to which this machine belongs.
 * @property isReady Indicator of whether the machine is ready for use (1 for ready, 0 for not ready).
 */
data class Machine(
    val id: Int,
    val name: String,
    val actions: List<String>, // simpleName of Action subclasses
    val plants: List<String>, // simpleName of Plant subclasses
    val duration: Int,
    var lastShedLocation: Int,
    var location: Int,
    var currentAction: Action?,
    val farmId: Int,
    var isReady: Int,
)
