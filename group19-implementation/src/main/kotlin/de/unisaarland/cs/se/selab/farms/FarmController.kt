package de.unisaarland.cs.se.selab.farms

import de.unisaarland.cs.se.selab.incidents.PERCENT
import de.unisaarland.cs.se.selab.map.MapController
import de.unisaarland.cs.se.selab.map.Reason
import de.unisaarland.cs.se.selab.map.Tile
import de.unisaarland.cs.se.selab.map.TileType
import de.unisaarland.cs.se.selab.plant.FieldPlant
import de.unisaarland.cs.se.selab.plant.PlantationPlant
import de.unisaarland.cs.se.selab.utils.Logger
import de.unisaarland.cs.se.selab.utils.Stats
import de.unisaarland.cs.se.selab.utils.Tick
import kotlin.math.max

const val REQUIRED_WATER_THRESHOLD = 100
const val MOISTURE_PENALTY = 50
const val ONE = 1
const val ZERO = 0
const val TWO = 2
const val THREE = 3
const val TWENTY_FIVE = 25
const val ERROR = "Plant not found"

// all the penalties represent percentage
const val GRAPE_PENALTY = 95
const val CHERRY_PENALTY = 30
const val ALMOND_PENALTY = 90
const val FIELD_PENALTY = 80
const val CUTTING_PENALTY = 50
const val APPLE_PENALTY = 50
const val ZERO_NINE = 0.9
const val NINETY = 90

/**
 * Controller for managing a single farm's operations
 *
 * @property farm The [Farm] instance this controller manages
 * @property mapController Controller for accessing tiles on the map
 * @property schedule Schedule mapping ticks to planned [Action]s
 * @property router Executes actions on tiles
 */
class FarmController(
    val farm: Farm,
    private val mapController: MapController,
    val schedule: MutableMap<Int, MutableList<Action>>,
    private val router: MachineRouter,
    private val stats: Stats
) {
    /** List of tiles (fields and plantations) managed by this farm */
    private val tiles: MutableList<Tile> = mutableListOf()
    private val actionOrder = listOf(WEEDING, CUTTING, MOWING, IRRIGATING, HARVESTING) // for log
    private val logMap = mutableMapOf<Int, MutableList<String>>()

    /** (Re-)Initializes the [tiles] list from the farm's fields and plantations */
    private fun init() {
        val fields: List<Tile> = farm.fields.map { id -> mapController.getTile(id) }
        val plantations: List<Tile> = farm.plantations.map { id -> mapController.getTile(id) }
        tiles.clear()
        tiles += plantations + fields
    }

    /**
     * Removes a field tile from the farm (used for City Expansion incident).
     * Will also remove all actions associated with this field.
     */
    fun removeFieldFromFarm(tileId: Int) {
        assert(mapController.getTile(tileId).category == TileType.FIELD) { "Tile must be a field" }
        farm.fields = farm.fields.filter { tileId != it }
        removeActionsIf { it == tileId }
        init()
    }

    /**
     * Removes all actions (from all ticks) that respect the [predicate]
     * @param predicate predicate from tile id to boolean
     */
    fun removeActionsIf(predicate: (Int) -> Boolean) {
        schedule.forEach { (_, actions) -> actions.removeIf { predicate(it.tile.id) } }
        val newMap = logMap.filter { (tileId, missedActions) -> !predicate(tileId) }
        logMap.clear()
        logMap += newMap
    }

    /**
     * Updates farm state for the current tick
     *
     * - Creates irrigation actions if needed
     * - Executes actions via the router
     * - Logs start and finish of farm operations
     */
    fun updateOnTick() {
        init() // DONE IN THE END OF THE TICK

        Logger.farmStart(farm.id)
        router.executePlans(schedule)
        createIrrigating()
        router.executeOthers(schedule, stats)
        Logger.farmFinished(farm.id)

        // penalty calculation
        logMap.clear()
        sunlightPenalty()
        updateActions()

        // update machine (must happen before applying incidents)
        farm.machines.forEach { m ->
            m.isReady = m.isReady.let {
                if (it > 0) it - 1 else it
            }
        }
    }

    /**
     * Returns (wOw!!!) farm ID
     */
    fun getFarmId(): Int {
        return farm.id
    }

    /**
     * Updates actions & harvest estimates, logs any changes
     * Additionally decreases counters: machine's isReady, fallowPeriod
     */
    fun updateHarvest(): FarmsLogs {
        init()

        // penalty application
        // order: weeding, cutting, mowing, harvesting
        calculatePenalty()

        val sortedLogMap = logMap.toSortedMap() // sort by tileId
        sortedLogMap.values.forEach { act -> // sort by action order
            act.sortBy { actionOrder.indexOf(it) }
        }

        val logActions = mutableMapOf<Int, List<String>>()
        val logHarvestChanges = mutableMapOf<Int, Pair<String, Int>>()

        tiles.sortedBy { it.id }.forEach { tile ->
            val actions = sortedLogMap[tile.id]
            if (!actions.isNullOrEmpty()) {
//                Logger.harvestEstimateMissed(tile.id, actions)
                logActions[tile.id] = actions
            }
            if (tile.soilMoisture == 0) {
                tile.harvestEstimate = 0
            }
            if (tile.harvestEstimate != tile.startOfTickEstimate) {
                val plant = tile.plant ?: error(ERROR)
//                Logger.harvestEstimateChanged(tile.id, plant.toString(), tile.harvestEstimate)
                logHarvestChanges[tile.id] = plant.toString() to tile.harvestEstimate
                littleHelper(tile)
            }
            if (tile.penalties.any() { it.second == Reason.DROUGHT }) tile.plant = null
            tile.penalties.clear()
        }

        tiles.forEach { tile ->
            tile.fallowPeriod = tile.fallowPeriod?.let { period ->
                if (period > 0) period - 1 else period
            }
        }

        return FarmsLogs(logActions, logHarvestChanges)
    }

    private fun littleHelper(tile: Tile) {
        tile.startOfTickEstimate = tile.harvestEstimate
        if (tile.harvestEstimate == 0) {
            removeActionsIf { it == tile.id }
            if (tile.category == TileType.FIELD) {
                tile.plant = null
                tile.fallowPeriod = FALLOW_PERIOD
            }
        }
    }

    private fun calculatePenalty() {
        tiles.sortedBy { it.id }.forEach { tile ->
            val orderedReasons = listOf(Reason.WEEDING, Reason.CUTTING, Reason.MOWING, Reason.HARVESTING)
            orderedReasons.forEach { reason ->
                val penalty = tile.penalties.firstOrNull() { penalty -> penalty.second == reason }
                if (penalty != null) {
                    tile.harvestEstimate = tile.harvestEstimate * penalty.first / PERCENT
                }
            }
//            if (tile.penalties.contains(Pair(NINETY, Reason.WEEDING))) {
//                tile.harvestEstimate = tile.harvestEstimate * NINETY / PERCENT
//            }
//            if (tile.penalties.contains(Pair(CUTTING_PENALTY, Reason.CUTTING))) {
//                tile.harvestEstimate = tile.harvestEstimate * CUTTING_PENALTY / PERCENT
//            }
//            if (tile.penalties.contains(Pair(NINETY, Reason.MOWING))) {
//                tile.harvestEstimate = tile.harvestEstimate * NINETY / PERCENT
//            }
//            if (tile.penalties.any { it.second == Reason.HARVESTING }) {
//                val penalty = tile.penalties.firstOrNull { it.second == Reason.HARVESTING } ?: return@forEach
//                tile.harvestEstimate = tile.harvestEstimate * penalty.first / PERCENT
//            }
            tile.penalties.filter {
                it.second in listOf(Reason.ANIMAL_ATTACK, Reason.DROUGHT, Reason.BEE_HAPPY)
            }.forEach { penalty ->
                tile.harvestEstimate = tile.harvestEstimate * penalty.first / PERCENT
            }
        }
    }

    /** Updates actions for the farm (removes irrigating, reschedules others if possible) */
    private fun updateActions() {
        val actions = schedule.remove(Tick.currentTick) ?: return
        actions.forEach { action ->
            when (action) {
                is IrrigatingAction -> {
                    irrigatingHelper(action)
                }
                is CuttingAction -> {
                    cuttingHelper(action)
                }
                is HarvestAction -> {
                    harvestHelper(action)
                }
                is MowingAction -> {
                    action.tile.penalties.add(Pair(NINETY, Reason.MOWING))
                    logMap.getOrPut(action.tile.id) { mutableListOf() }.add(MOWING)
                }
                is WeedingAction -> {
                    action.tile.penalties.add(Pair(NINETY, Reason.WEEDING))
                    logMap.getOrPut(action.tile.id) { mutableListOf() }.add(WEEDING)
                }
                else -> {
                    throw IllegalArgumentException("Something is very wrong")
                }
            }
        }
    }

    /**
     * Applies penalty for extra sunlight for each field/plant tile
     */
    private fun sunlightPenalty() {
        tiles.forEach { tile ->
            val plant = tile.plant ?: return@forEach
            val extra = max(tile.amountSunlight - plant.sunlight(), 0)
            val reduction = extra / TWENTY_FIVE
            repeat(reduction) { // reduce by 10% for each 25h
                tile.harvestEstimate = tile.harvestEstimate * NINETY / PERCENT
            }
        }
    }

    /**
     * Applies irrigation logic to a specific plant on a tile: adjusts the harvest estimate based on whether the plant
     * received enough water. If the moisture level reaches 0L, the plant is destroyed and
     * the harvest is lost. If the plant is under-watered, a penalty is applied to
     * the harvest estimate proportional to the missing water amount
     *
     * @param action The IrrigatingAction containing the target plant and tile
     */
    private fun irrigatingHelper(action: IrrigatingAction) {
        // if the moisture reaches 0 L, the harvest is lost
        if (action.tile.startOfTickEstimate == 0) { // only if plantation was dead cuz of soil moisture
            return
        }
        if (action.tile.soilMoisture == 0) {
            if (action.tile.startOfTickEstimate != 0) {
                logMap.getOrPut(action.tile.id) { mutableListOf() }.add(IRRIGATING)
            }
            action.tile.harvestEstimate = 0
            return
        }
        // for each 100L below the required amount of water at the tile, the tile loses 50g harvest
        val moistureRequired = action.plant.moisture() - action.tile.soilMoisture
        if (moistureRequired >= REQUIRED_WATER_THRESHOLD) {
            // only missed irrigating that decreased the harvest is logged
            logMap.getOrPut(action.tile.id) { mutableListOf() }.add(IRRIGATING)
            // apply penalty
            val penalty = moistureRequired / REQUIRED_WATER_THRESHOLD * MOISTURE_PENALTY
            val newEstimate = action.tile.harvestEstimate - penalty
            if (newEstimate > 0) {
                action.tile.harvestEstimate = newEstimate
            } else {
                // if harvest is <= 0, then remove plant
                action.tile.harvestEstimate = 0
//                if (action.tile.category == TileType.FIELD) { // DONE IN UPDATE HARVEST
//                    action.tile.plant = null
//                }
            }
        }
    }

    /**
     * Applies harvesting logic to a specific plant on a tile: handles late or missed harvests by applying
     * penalties to the harvest estimate, potentially destroying the plant if harvest is too late. It also logs
     * relevant events and optionally reschedules the action for the next tick if harvesting can still occur
     *
     * Late harvest penalties differ for FieldPlants and PlantationPlants and
     * depend on how many ticks after the optimal period the harvest occurs.
     *
     * @param action The HarvestAction containing the target plant, tile, and timing
     */

    private fun harvestHelper(
        action: HarvestAction
    ) {
        val needsReschedule: Boolean
        val daysLate = Tick.yTick - action.endTick
        needsReschedule = if (daysLate >= 0) {
            // log harvest missed if we are really late
            logMap.getOrPut(action.tile.id) { mutableListOf() }.add(HARVESTING)
            when (daysLate) {
                ZERO, ONE -> {
                    harvestZeroOne(action)
                }

                TWO, THREE -> {
                    harvestTwoThree(action)
                }

                else -> {
                    error("something went wrong, unexpectedly late harvest action")
                }
            }
        } else {
            true
        }
        if (needsReschedule) {
            schedule.getOrPut(Tick.currentTick + ONE) { mutableListOf() }.add(action)
        }
        // all actions are created on the moment of plantation plant re-setting
        // or for field plant - when sowing is performed
    }

    private fun harvestZeroOne(action: HarvestAction): Boolean {
        val lateDays = Tick.yTick - action.endTick
        return when (lateDays) {
            ZERO -> { // late harvest day zero
                when (action.plant) {
                    PlantationPlant.GRAPE -> {
                        action.tile.penalties.add(Pair(GRAPE_PENALTY, Reason.HARVESTING))
                    }
                    PlantationPlant.CHERRY -> {
                        action.tile.penalties.add(Pair(CHERRY_PENALTY, Reason.HARVESTING))
                    }
                    PlantationPlant.ALMOND -> {
                        action.tile.penalties.add(Pair(ALMOND_PENALTY, Reason.HARVESTING))
                    }
                    PlantationPlant.APPLE -> {
                        action.tile.penalties.add(Pair(APPLE_PENALTY, Reason.HARVESTING))
                    }
                    FieldPlant.WHEAT, FieldPlant.OAT -> {
                        action.tile.penalties.add(Pair(FIELD_PENALTY, Reason.HARVESTING))
                    }
                    else -> { // potato and pumpkin cannot do late harvest => destroy (and don't log) estimate changed
                        action.tile.harvestEstimate = ZERO
                        action.removeActions(schedule)
                        // we log harvest estimate change and for that we need plant
//                        action.tile.startOfTickEstimate = ZERO
//                        action.tile.plant = null
                        return false
                    }
                }
                true // reschedule
            }
            ONE -> { // late harvest day one
                when (action.plant) {
                    PlantationPlant.CHERRY, PlantationPlant.ALMOND, PlantationPlant.APPLE -> {
                        // destroy (and DO log) estimate changed
                        action.tile.harvestEstimate = ZERO
                        action.removeActions(schedule)
                        return false // no reschedule
                    }
                    PlantationPlant.GRAPE -> {
                        action.tile.penalties.add(Pair(GRAPE_PENALTY, Reason.HARVESTING))
                    }
                    FieldPlant.WHEAT, FieldPlant.OAT -> {
                        action.tile.penalties.add(Pair(FIELD_PENALTY, Reason.HARVESTING))
                    }
                    else -> {
                        error("only plantation plant on Plantation")
                    }
                }

                true // reschedule
            }
            else -> {
                error("something went wrong, unexpectedly late harvest action")
            }
        }
    }

    private fun harvestTwoThree(action: HarvestAction): Boolean {
        val lateDays = Tick.yTick - action.endTick
        return when (lateDays) {
            TWO -> { // late harvest day two
                when (action.plant) {
                    FieldPlant.WHEAT, FieldPlant.OAT -> {
                        action.tile.harvestEstimate = ZERO
                        action.removeActions(schedule)
                        // we log harvest estimate change and for that we need plant
//                        action.tile.startOfTickEstimate = ZERO
//                        action.tile.plant = null
                        return false // no reschedule
                    }
                    PlantationPlant.GRAPE -> {
                        action.tile.penalties.add(Pair(GRAPE_PENALTY, Reason.HARVESTING))
                    }
                    else -> {
                        require(false) // something went wrong, no other plants should be here
                    }
                }
                true // reschedule
            }
            THREE -> { // only grape is left
                // we log harvest estimate change and for that we need plant
                action.tile.harvestEstimate = ZERO
                action.removeActions(schedule)
                return false // no reschedule
            }
            else -> {
                require(false) // something went wrong
                return false
            }
        }
    }

    /**
     * Schedule cutting for 1 period only. Example require a CUTTING in the second tick of July or in August,
     * then either cutting (14,14) or (15,16)
     */
    private fun cuttingHelper(
        action: CuttingAction
    ) {
        if (action.endTick == Tick.yTick) { // action not performed in the right period => penalty or new action
            action.plant as PlantationPlant

            if (action.startTick == action.plant.cuttingPeriod[0].first) {
                // second cutting is possible
                val actionC = CuttingAction( // schedule new cutting
                    action.plant,
                    action.plant.cuttingPeriod[1].first,
                    action.plant.cuttingPeriod[1].second,
                    action.tile
                )
                schedule.getOrPut(action.scheduleTick(actionC)) { mutableListOf() }.add(actionC)
            } else { // second cutting also failed => penalty
                action.tile.penalties.add(Pair(CUTTING_PENALTY, Reason.CUTTING))
                logMap.getOrPut(action.tile.id) { mutableListOf() }.add(CUTTING)
            }
        } else { // reschedule action on the next tick
            schedule.getOrPut(Tick.currentTick + ONE) { mutableListOf() }.add(action)
        }
    }

    /**
     * Computes the total remaining harvest estimate for all tiles
     *
     * @return Sum of [Tile.harvestEstimate] for all tiles in this farm
     */
    fun getRemainingHarvestEstimate(): Int {
        init()
        var totalHarvestEstimate = 0
        tiles.forEach { tile ->
            totalHarvestEstimate += tile.harvestEstimate
        }
        return totalHarvestEstimate
    }

    /**
     * Creates irrigation actions for tiles where the soil moisture
     * is below the required moisture level of the planted crop
     *
     * Adds the actions to the [schedule] for the current tick
     */
    private fun createIrrigating() {
        tiles.forEach { tile ->
            val plant = tile.plant // to prevent using !!
            if (plant != null && tile.soilMoisture < plant.moisture()) {
                val irr = IrrigatingAction(plant, Tick.currentTick, Tick.currentTick, tile)
                schedule.getOrPut(Tick.currentTick) { mutableListOf() }.add(irr)
            }
        }
    }

    /**
     * Called by Animal attack to remove mowing from current and next tick from specific tiles
     */
    fun removeMowing(tileIds: List<Int>) {
        tileIds.forEach { tileId ->
            val list = logMap.remove(tileId) ?: return@forEach
            val new = list.filter { it != MOWING }
            logMap[tileId] = new.toMutableList()
        }
        val currentTickActions = schedule.remove(Tick.currentTick) ?: mutableListOf()
        val newCurrentTickActions = currentTickActions.toMutableList()
        val nextTickActions = schedule.remove(Tick.currentTick + ONE) ?: mutableListOf()
        val newNextTickActions = nextTickActions.toMutableList()
        currentTickActions.forEach { action ->
            if (action is MowingAction && tileIds.contains(action.tile.id)) {
                newCurrentTickActions.remove(action)
            }
        }
        nextTickActions.forEach { action ->
            if (action is MowingAction && tileIds.contains(action.tile.id)) {
                newNextTickActions.remove(action)
            }
        }
        schedule[Tick.currentTick] = newCurrentTickActions
        schedule[Tick.currentTick + ONE] = newNextTickActions
    }
}

/**
 * The logs of a farm
 * @param actions map from tile id to the names of the missed actions on that tile
 * @param harvestChanges map from tile id to the pair (plant name, harvest change)
 */
data class FarmsLogs(val actions: Map<Int, List<String>>, val harvestChanges: Map<Int, Pair<String, Int>>) {
    /**
     * Merges two FarmsLogs
     */
    fun merge(other: FarmsLogs): FarmsLogs =
        FarmsLogs(this.actions + other.actions, this.harvestChanges + other.harvestChanges)

    /**
     * Logs the maps in the correct order
     */
    fun log() {
        harvestChanges.entries.sortedBy { it.key }.forEach { (tileId, value) ->
            val actions = actions[tileId]
            if (!actions.isNullOrEmpty()) {
                Logger.harvestEstimateMissed(tileId, actions)
            }
            val (plant, harvest) = value
            Logger.harvestEstimateChanged(tileId, plant, harvest)
        }
    }
}

val emptyFarmsLogs = FarmsLogs(emptyMap(), emptyMap())
