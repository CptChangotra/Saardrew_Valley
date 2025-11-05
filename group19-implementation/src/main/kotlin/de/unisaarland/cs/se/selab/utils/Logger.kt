package de.unisaarland.cs.se.selab.utils

import java.io.PrintWriter

/**
 * outputs a log message at different levels of details
 */
object Logger {
    var logLevel: LogLevel = LogLevel.DEBUG
    var writer: PrintWriter = PrintWriter(System.out)

    /**
     * a log message that parsing and validating each configuration file was successful
     * @param filename the name of the file as a string
     */
    fun fileValid(filename: String) {
        when (logLevel) {
            LogLevel.INFO, LogLevel.DEBUG -> {
                writer.println(
                    "[INFO] Initialization Info: $filename successfully parsed and " +
                        "validated."
                )
                writer.flush()
            }
            else -> {}
        }
    }

    /**
     * a log message printing that the configuration file is invalid
     * @param filename the name of the file as a string
     */
    fun fileInvalid(filename: String) {
        writer.println("[IMPORTANT] Initialization Info: $filename is invalid.")
        writer.flush()
    }

    /**
     * a log message indicating the start of the simulation
     * @param tick the year tick
     */
    fun simulationStart(tick: Int) {
        when (logLevel) {
            LogLevel.INFO, LogLevel.DEBUG -> {
                writer.println(
                    "[INFO] Simulation Info: Simulation started at tick $tick within the " +
                        "year."
                )
                writer.flush()
            }
            else -> {}
        }
    }

    /**
     * a log message that the simulation ended
     * @param tick the current tick
     */
    fun simulationEnd(tick: Int) {
        writer.println("[IMPORTANT] Simulation Info: Simulation ended at tick $tick.")
        writer.flush()
    }

    /**
     * a log message that the tick started
     * @param tick the current tick
     * @param yearTick the year tick
     */
    fun tickStart(tick: Int, yearTick: Int) {
        when (logLevel) {
            LogLevel.INFO, LogLevel.DEBUG -> {
                writer.println(
                    "[INFO] Simulation Info: Tick $tick started at tick $yearTick within the " +
                        "year."
                )
                writer.flush()
            }
            else -> {}
        }
    }

    /**
     * log message containing the amount of field and plantation tiles
     * that are planted and where the moisture is
     * below the threshold the plants require
     * @param amountField number of field tiles below threshold
     * @param amountPlantation number of plantation tiles below threshold
     */
    fun soilMoisture(amountField: Int, amountPlantation: Int) {
        when (logLevel) {
            LogLevel.INFO, LogLevel.DEBUG -> {
                writer.println(
                    "[INFO] Soil Moisture: The soil moisture is below threshold in " +
                        "$amountField FIELD and $amountPlantation PLANTATION tiles."
                )
                writer.flush()
            }
            else -> {}
        }
    }

    /**
     * a log message describing the raining process
     * @param cloudID the id of the cloud
     * @param tileID the id of the tile where the cloud rains
     * @param amount the amount of water that rains down
     */
    fun cloudRain(cloudID: Int, tileID: Int, amount: Int) {
        writer.println(
            "[IMPORTANT] Cloud Rain: Cloud $cloudID on tile $tileID rained down " +
                "$amount L water."
        )
        writer.flush()
    }

    /**
     * a log message describing cloud movement
     * @param cloudID the id of the cloud
     * @param amount the amount of water of the cloud
     * @param startTileID the id of the tile the cloud moved from
     * @param endTileID the id of the tile the cloud moved to
     */
    fun cloudMovement(cloudID: Int, amount: Int, startTileID: Int, endTileID: Int) {
        when (logLevel) {
            LogLevel.INFO, LogLevel.DEBUG -> {
                writer.println(
                    "[INFO] Cloud Movement: Cloud $cloudID with $amount L water moved " +
                        "from tile $startTileID to tile $endTileID."
                )
                writer.flush()
            }
            else -> {}
        }
    }

    /**
     * a log message printing the current amount of sunlight for the tile the cloud moved from in this tick
     * @param startTileID the tile id
     * @param amountSunlight the amount of sunlight
     */
    fun cloudMovementSunlight(startTileID: Int, amountSunlight: Int) {
        when (logLevel) {
            LogLevel.DEBUG -> {
                writer.println(
                    "[DEBUG] Cloud Movement: On tile $startTileID, the amount of sunlight is " +
                        "$amountSunlight."
                )
                writer.flush()
            }
            else -> {}
        }
    }

    /**
     * a log message describing the cloud merging process
     * @param cloudIDFrom the id of the first original clouds
     * @param cloudIDTo the id of the second original clouds
     * @param cloudIDNew the id of the new cloud
     * @param amount the amount of water of the new cloud
     * @param duration the duration of the new cloud
     * @param tileID the id of the tile where the union occurred
     */
    fun cloudUnion(
        cloudIDFrom: Int,
        cloudIDTo: Int,
        cloudIDNew: Int,
        amount: Int,
        duration: Int,
        tileID: Int
    ) {
        writer.println(
            "[IMPORTANT] Cloud Union: Clouds $cloudIDFrom and " +
                "$cloudIDTo united to cloud $cloudIDNew with $amount L water " +
                "and duration $duration on tile $tileID."
        )
        writer.flush()
    }

    /**
     * a log message printing that a cloud got stuck at a village
     * @param cloudID the id of the stuck cloud
     * @param tileID the tile id of the village
     */
    fun cloudStuck(cloudID: Int, tileID: Int) {
        when (logLevel) {
            LogLevel.INFO, LogLevel.DEBUG -> {
                writer.println(
                    "[INFO] Cloud Dissipation: Cloud $cloudID got stuck on tile $tileID."
                )
                writer.flush()
            }
            else -> {}
        }
    }

    /**
     * a log message printing that a cloud dissipated
     * @param cloudID the id of the cloud
     * @param tileID the id of the tile where the cloud dissipated
     */
    fun cloudDissipation(cloudID: Int, tileID: Int) {
        when (logLevel) {
            LogLevel.INFO, LogLevel.DEBUG -> {
                writer.println(
                    "[INFO] Cloud Dissipation: Cloud $cloudID dissipates on tile $tileID."
                )
                writer.flush()
            }
            else -> {}
        }
    }

    /**
     * a log message printing that a cloud located on the tile affects the sunlight
     * @param cloudID the id of the cloud
     * @param tileID the id of the tile
     * @param amountSunlight the amount of sunlight of the tile
     */
    fun cloudPosition(cloudID: Int, tileID: Int, amountSunlight: Int) {
        when (logLevel) {
            LogLevel.DEBUG -> {
                writer.println(
                    "[DEBUG] Cloud Position: Cloud $cloudID is on tile $tileID, where the " +
                        "amount of sunlight is $amountSunlight."
                )
                writer.flush()
            }
            else -> {}
        }
    }

    /**
     * a log message printing that the farms now start moving their machines and performing actions
     * @param farmID the id of the farm
     */
    fun farmStart(farmID: Int) {
        writer.println("[IMPORTANT] Farm: Farm $farmID starts its actions.")
        writer.flush()
    }

    /**
     * a log message that the farm plans to sow according to
     *  those plans if possible. This message is also sent if there are no active sowing plans.
     *  @param farmID the id of the farm
     *  @param sowingPlanIDs the ids of the active sowing plans
     */
    fun farmActiveSowingPlan(farmID: Int, sowingPlanIDs: List<Int>) {
        when (logLevel) {
            LogLevel.DEBUG -> {
                writer.println(
                    "[DEBUG] Farm: Farm $farmID has the following active sowing plans it " +
                        "intends to pursue in this tick: ${idListToString(sowingPlanIDs)}."
                )
                writer.flush()
            }
            else -> {}
        }
    }

    /**
     * a log message that a machine performs an action
     * @param machineID the id of the machine
     * @param actionType the type of action
     * @param tileID the id of tile the machine moves to
     * @param duration the duration this action takes
     */
    fun farmAction(machineID: Int, actionType: String, tileID: Int, duration: Int) {
        writer.println(
            "[IMPORTANT] Farm Action: Machine $machineID performs $actionType on " +
                "tile $tileID for $duration days."
        )
        writer.flush()
    }

    /**
     * a log message that the machine sowed
     * @param machineID the id of the machine
     * @param plant the plant sowed
     * @param sowingPlanID the id of the sowing plan according to which sowing was preformed
     */
    fun farmSowing(machineID: Int, plant: String, sowingPlanID: Int) {
        writer.println(
            "[IMPORTANT] Farm Sowing: Machine $machineID has sowed $plant according " +
                "to sowing plan $sowingPlanID."
        )
        writer.flush()
    }

    /**
     * the message that the machine has collected harvest
     * @param machineID the id of the machine
     * @param plant the harvested plant
     * @param amount the amount of harvest collected
     */
    fun farmHarvest(machineID: Int, plant: String, amount: Int) {
        writer.println(
            "[IMPORTANT] Farm Harvest: Machine $machineID has collected $amount g of " +
                "$plant harvest."
        )
        writer.flush()
    }

    /**
     * a log message that the machine returns.
     * @param machineID the id of the machine
     * @param tileID the id of the tile the machine returns
     */
    fun farmMachineReturn(machineID: Int, tileID: Int) {
        writer.println(
            "[IMPORTANT] Farm Machine: Machine $machineID is finished and returns to " +
                "the shed at $tileID."
        )
        writer.flush()
    }

    /**
     * a log message that the machine failed to return
     * @param machineID the id of the machine
     */
    fun farmMachineReturnFailed(machineID: Int) {
        writer.println(
            "[IMPORTANT] Farm Machine: Machine $machineID is finished but failed to " +
                "return."
        )
        writer.flush()
    }

    /**
     * a log the message that the machine unloads harvest
     * @param machineID the id of the machine
     * @param plant the harvested plant
     * @param amount the amount of harvest
     */
    fun farmMachineUnload(machineID: Int, plant: String, amount: Int) {
        writer.println(
            "[IMPORTANT] Farm Machine: Machine $machineID unloads $amount g of " +
                "$plant harvest in the shed."
        )
        writer.flush()
    }

    /**
     * a log message that the farm finished its actions
     * @param farmID the id of the farm
     */
    fun farmFinished(farmID: Int) {
        writer.println("[IMPORTANT] Farm: Farm $farmID finished its actions.")
        writer.flush()
    }

    /**
     * a log message that the incident happened
     * @param incidentID the id of the incident
     * @param incidentType type of the incident
     * @param affectedTiles the tiles that were affected by the incident
     */
    fun incident(incidentID: Int, incidentType: String, affectedTiles: List<Int>) {
        writer.println(
            "[IMPORTANT] Incident: Incident $incidentID of type $incidentType " +
                "happened and affected tiles ${idListToString(affectedTiles)}."
        )
        writer.flush()
    }

    /**
     * a log message that the estimated harvest changed
     * @param tileID the id of the tile
     * @param actions a list of actions that were missed
     */
    fun harvestEstimateMissed(tileID: Int, actions: List<String>) {
        when (logLevel) {
            LogLevel.DEBUG -> {
                val order = listOf("WEEDING", "CUTTING", "MOWING", "IRRIGATING", "HARVESTING")
                val sorted = actions.sortedBy { order.indexOf(it) }

                writer.println(
                    "[DEBUG] Harvest Estimate: Required actions on tile $tileID were not performed: " +
                        "${sorted.joinToString(",")}."
                )
                writer.flush()
            }
            else -> {}
        }
    }

    /**
     * a log message that the estimated harvest changed
     * @param tileID the id of the tile
     * @param plant the plant growing in the tile
     * @param amount the new harvest estimate
     */
    fun harvestEstimateChanged(tileID: Int, plant: String, amount: Int) {
        when (logLevel) {
            LogLevel.INFO, LogLevel.DEBUG -> {
                writer.println(
                    "[INFO] Harvest Estimate: Harvest estimate on tile $tileID changed to " +
                        "$amount g of $plant."
                )
                writer.flush()
            }
            else -> {}
        }
    }

    /**
     * a log message that the simulation statistics are calculated
     */
    fun simulationStatisticsStart() {
        writer.println("[IMPORTANT] Simulation Info: Simulation statistics are calculated.")
        writer.flush()
    }

    /**
     * a log message that the farm collected this amount of harvest
     * @param farmID the id of the farm
     * @param amount the amount of harvest collected by the farm across plants
     */
    fun simulationStatisticsFarm(farmID: Int, amount: Int) {
        writer.println("[IMPORTANT] Simulation Statistics: Farm $farmID collected $amount g of harvest.")
        writer.flush()
    }

    /**
     * a log message for the plant and the amount of harvest collected for this plant
     * @param plant the harvested plant
     * @param amount the amount of the harvest
     */
    fun simulationStatisticsPlant(plant: String, amount: Int) {
        writer.println("[IMPORTANT] Simulation Statistics: Total amount of $plant harvested: $amount g.")
        writer.flush()
    }

    /**
     * a log message with the current estimate of all harvests remaining in the field and plantation tiles
     */
    fun simulationStatisticsRemaining(amount: Int) {
        writer.println(
            "[IMPORTANT] Simulation Statistics: Total harvest estimate still in " +
                "fields and plantations: $amount g."
        )
        writer.flush()
    }

    /**
     * sorts the tile id list and remove whitespace in between
     */
    private fun idListToString(list: List<Int>): String =
        list.distinct().sorted().map { it.toString() }.joinToString(",")
}

/**
 * describes the log levels to log information at different levels of detail
 */
enum class LogLevel {
    DEBUG,
    INFO,
    IMPORTANT
}
