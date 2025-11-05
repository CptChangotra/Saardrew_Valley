package de.unisaarland.cs.se.selab.farms

/**
 * Represents a farm with its associated entities and plans.
 *
 * @property id The unique identifier of the farm.
 * @property name The name of the farm.
 * @property farmsteads A list of IDs representing the farmsteads associated with the farm.
 * @property sheds A list of IDs representing the farmsteads WITH SHEDS associated with the farm.
 * @property fields A list of IDs representing the fields associated with the farm.
 * @property plantations A list of IDs representing the plantations associated with the farm.
 * @property machines A list of machines owned by the farm.
 * @property stuckMachines A list of machines that are currently stuck and cannot perform actions.
 * @property sowingPlans A list of sowing plans scheduled for the farm.
 */
data class Farm(
    val id: Int,
    val name: String,
    val farmsteads: List<Int>,
    val sheds: List<Int>,
    var fields: List<Int>,
    val plantations: List<Int>,
    var machines: List<Machine>,
    var stuckMachines: List<Machine>,
    var sowingPlans: List<SowingPlan>
)
