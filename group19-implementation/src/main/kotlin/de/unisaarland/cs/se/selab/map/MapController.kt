package de.unisaarland.cs.se.selab.map

import de.unisaarland.cs.se.selab.farms.FarmController
import de.unisaarland.cs.se.selab.farms.HarvestAction
import de.unisaarland.cs.se.selab.parser.UNREACHABLE
import de.unisaarland.cs.se.selab.plant.PlantationPlant
import de.unisaarland.cs.se.selab.utils.Logger
import de.unisaarland.cs.se.selab.utils.Tick
import de.unisaarland.cs.se.selab.utils.YearTick
import java.util.PriorityQueue
import kotlin.collections.List
import kotlin.math.absoluteValue
import kotlin.math.max

const val REDUCE_MOISTURE_HAS_PLANT = 100
const val REDUCE_MOISTURE_NO_PLANT = 70

const val SUNLIGHT_JANUARY = 98
const val SUNLIGHT_FEBRUARY = 112
const val SUNLIGHT_MARCH = 126
const val SUNLIGHT_APRIL = 140
const val SUNLIGHT_MAY = 168
const val SUNLIGHT_JUNE = 168
const val SUNLIGHT_JULY = 168
const val SUNLIGHT_AUGUST = 154
const val SUNLIGHT_SEPTEMBER = 126
const val SUNLIGHT_OCTOBER = 112
const val SUNLIGHT_NOVEMBER = 98
const val SUNLIGHT_DECEMBER = 84

const val TILE_NOT_FOUND = "A Tile with the given id is not found"

/**
 * Map Controller responsible for controlling and manipulating the map.
 * @param simulationMap Map the tile id to their tile object.
 * @param farmControllers List of all farm controllers
 */
class MapController(val simulationMap: SimulationMap) {

    /**
     *  Iterates over all tiles, reduce moisture, sun level and logs the FIELD/PLANTATION that are below Threshold
     */
    fun tick() {
        var fieldMoistureBelowThreshold = 0
        var plantationMoistureBelowThreshold = 0
        for (tile in simulationMap.idToTile.values) {
            reduceMoisture(tile)
            setSunLevel(tile)
            fieldMoistureBelowThreshold += checkThresholdField(tile)
            plantationMoistureBelowThreshold += checkThresholdPlantation(tile)
        }
        Logger.soilMoisture(fieldMoistureBelowThreshold, plantationMoistureBelowThreshold)
    }

    /**
     *  In the start of each tick, reduces the moisture by 100 L if there are plants on that tile,
     *  otherwise by 70 L during the environment phase.
     *  @param tile The tile we reduce moisture on.
     */
    private fun reduceMoisture(tile: Tile) {
        if (tile.category == TileType.FIELD || tile.category == TileType.PLANTATION) {
            if (tile.plant != null) {
                tile.soilMoisture = max(tile.soilMoisture - REDUCE_MOISTURE_HAS_PLANT, 0) // reduced by 100
            } else {
                tile.soilMoisture = max(tile.soilMoisture - REDUCE_MOISTURE_NO_PLANT, 0) // reduced by 70
            }
        } else { // should not be reached because only FIELD and PLANTATION have soil moisture
            tile.soilMoisture = max(tile.soilMoisture - REDUCE_MOISTURE_NO_PLANT, 0)
        }
    }

    /**
     * For Logger.soilMoisture, checks whether a field tile is below the threshold for soil moisture.
     * @param tile The FIELD tile meant to be checked on.
     * @return Returns 1 if its moisture is below the plant required moisture, otherwise 0
     */
    private fun checkThresholdField(tile: Tile): Int {
        return when (tile.category) {
            TileType.FIELD -> {
                tile.plant?.let {
                    if (tile.soilMoisture < it.moisture()) 1 else 0
                } ?: 0 // if plant is null
            }
            else -> { 0 }
        }
    }

    /**
     * For Logger.soilMoisture, checks whether a plantation tile is below the threshold for soil moisture
     * @param tile The PLANTATION tile meant to be checked on.
     * @return Returns 1 if its moisture is below the plant required moisture, otherwise 0
     */
    private fun checkThresholdPlantation(tile: Tile): Int {
        return when (tile.category) {
            TileType.PLANTATION -> {
                tile.plant?.let {
                    if (tile.soilMoisture < it.moisture()) 1 else 0
                } ?: 0 // if plant is null
            }
            else -> { 0 }
        }
    }

    /**
     * For each tick during its environment phse, set its sun level according to the
     * tick of the year.
     * @param tile Set the sunLevel for this tile depends on yearTick
     */
    private fun setSunLevel(tile: Tile) {
        tile.amountSunlight = when (Tick.yTick) {
            YearTick.JANUARY_1, YearTick.JANUARY_2 -> SUNLIGHT_JANUARY
            YearTick.FEBRUARY_1, YearTick.FEBRUARY_2 -> SUNLIGHT_FEBRUARY
            YearTick.MARCH_1, YearTick.MARCH_2 -> SUNLIGHT_MARCH
            YearTick.APRIL_1, YearTick.APRIL_2 -> SUNLIGHT_APRIL
            YearTick.MAY_1, YearTick.MAY_2 -> SUNLIGHT_MAY
            YearTick.JUNE_1, YearTick.JUNE_2 -> SUNLIGHT_JUNE
            YearTick.JULY_1, YearTick.JULY_2 -> SUNLIGHT_JULY
            YearTick.AUGUST_1, YearTick.AUGUST_2 -> SUNLIGHT_AUGUST
            YearTick.SEPTEMBER_1, YearTick.SEPTEMBER_2 -> SUNLIGHT_SEPTEMBER
            YearTick.OCTOBER_1, YearTick.OCTOBER_2 -> SUNLIGHT_OCTOBER
            YearTick.NOVEMBER_1, YearTick.NOVEMBER_2 -> SUNLIGHT_NOVEMBER
            YearTick.DECEMBER_1, YearTick.DECEMBER_2 -> SUNLIGHT_DECEMBER
            else -> error("Unknown year tick")
        }
    }

    /**
     * Get the tile from the given id.
     * @param id The id of a Tile.
     * @return Returns the Tile object.
     */
    fun getTile(id: Int): Tile {
        return simulationMap.idToTile[id] ?: error(TILE_NOT_FOUND)
    }

    /**
     * For cloud movement, check to which tile the cloud should move to.
     * @param id The tile id of where the cloud located.
     * @return Returns the tile where the cloud should move to.
     *         Returns null if the tile doesn't have airflow.
     */
    fun getNeighbourTile(id: Int): Tile? {
        val tile = simulationMap.idToTile[id] ?: error(TILE_NOT_FOUND)
        if (!tile.airflow) return null
        val neighbourId = tile.direction?.let { tile.neighbours[it] } ?: return null
        return simulationMap.idToTile[neighbourId]
    }

    /**
     * All tiles withing a given radius from a specified centre tile
     * @param id The id of the center tile.
     * @param radius The maximum distance (in tile steps) from the center tile;
     *           must be >= 1, otherwise an empty list is returned
     * @return Returns a list of tiles within the given radius including the center.
     */
    fun getNeighbourTilesAndCentre(id: Int, radius: Int): List<Tile> = getNeighbourTiles(id, radius) + getTile(id)

    /**
     * Using BFS to collect the tiles within a given radius from a specified center tile based on
     * their distance of tiles on the map. This method is used for machine to find where to execute
     * the action.
     * @param id The id of the center tile.
     * @param radius The maximum distance (in tile steps) from the center tile;
     *           must be >= 1, otherwise an empty list is returned
     * @return Returns a list of tiles within the given radius excluding the center.
     */
    fun getNeighbourTiles(id: Int, radius: Int): List<Tile> {
        if (radius < 1) return emptyList()
        val center = simulationMap.idToTile[id] ?: error(TILE_NOT_FOUND)
        val visited = mutableSetOf(center.id)
        val neighbours = mutableListOf<Tile>()
        val queue: ArrayDeque<Pair<Tile, Int>> = ArrayDeque() // <Tile, Int>: tile, distance from the center

        queue.add(center to 0)

        while (queue.isNotEmpty()) {
            val (current, dist) = queue.removeFirst()
            if (dist == 0) {
                // center tile
            } else if (dist <= radius) {
                neighbours.add(current)
            }

            if (dist >= radius) continue

            // Iterate over all neighbors' id from current. If not found, continue
            current.neighbours.values.forEach { neighbourId ->
                val neighbour = simulationMap.idToTile[neighbourId] ?: return@forEach
                if (visited.add(neighbour.id)) {
                    queue.add(neighbour to dist + 1)
                }
            }
        }

        return neighbours
    }

    /**
     * A calculation of tiles affected by incidents based on the distance between their coordinates.
     * Square and octagonal tiles are distinguished into two cases.
     * @param id
     * @param radius A radius defining the area around id
     * @return Return a list of tiles within the radius with respect to their coordinate
     *          distance including the center tile.
     */
    fun getNeighboursGeometries(id: Int, radius: Int): List<Tile> {
        val center = simulationMap.idToTile[id] ?: error(TILE_NOT_FOUND)
        val isSquare = center.coordinate.x.mod(2) == 1
        val result = simulationMap.idToTile.values.filter { tile ->
            val dx = (tile.coordinate.x - center.coordinate.x).absoluteValue
            val dy = (tile.coordinate.y - center.coordinate.y).absoluteValue
            dx + dy <= radius * 2
        }

        return if (!isSquare) {
            result
        } else {
            result.filter {
                !((it.coordinate.x - center.coordinate.x).absoluteValue == 2 * radius).xor(
                    (it.coordinate.y - center.coordinate.y).absoluteValue == 2 * radius
                )
            }
        }
    }

    /**
     * Machines cannot traverse FOREST, can traverse FARMSTEAD, FIELD and PLANTATION tiles
     * owned by its farm and any MEADOW or ROAD tile.
     * When isMachineFull == true, machines cannot traverse FOREST and VILLAGE.
     * @param t1 The tile id of the starting tile
     * @param t2 The tile id of the target tile
     * @param isMachineFull whether the machine contains harvest
     * @return Returns if there exist a path from tile t1 to t2 for machines.
     */
    fun findPath(t1: Int, t2: Int, isMachineFull: Boolean): Boolean {
        val visited = mutableSetOf<Int>()
        val toVisit: ArrayDeque<Int> = ArrayDeque()

        toVisit.add(t1)
        visited.add(t1)

        while (toVisit.isNotEmpty()) {
            val current = toVisit.removeFirst()

            if (current == t2) return true // if the target tile is reached, a path is found

            for (neighbour in getTile(current).neighbours.values) { // continue checking its neighbor
                if (!visited.contains(neighbour) && canTraverse(t1, neighbour, isMachineFull)) {
                    visited.add(neighbour)
                    toVisit.add(neighbour)
                }
            }
        }
        return false // no path found
    }

    /**
     * For findPath. Checking if the tile can be traversed by the machine.
     * @param startTileId We need check if startTile and currTile belong to the same farm.
     * @param currTileId Checking whether this tile can be traversed.
     * @param isMachineFull whether the machine contains harvest.
     * @return Returns whether this tile can be traversed.
     */
    private fun canTraverse(startTileId: Int, currTileId: Int, isMachineFull: Boolean): Boolean {
        val startTile = simulationMap.idToTile[startTileId] ?: error(TILE_NOT_FOUND)
        val currTile = simulationMap.idToTile[currTileId] ?: error(TILE_NOT_FOUND)

        return when (currTile.category) {
            TileType.MEADOW, TileType.ROAD -> true
            TileType.FARMSTEAD, TileType.FIELD, TileType.PLANTATION -> startTile.farm == currTile.farm
            TileType.FOREST -> false
            TileType.VILLAGE -> !isMachineFull
        }
    }

    /**
     * Machines cannot traverse FOREST, can traverse FARMSTEAD, FIELD and PLANTATION tiles
     * owned by its farm and any MEADOW or ROAD tile.
     * When isMachineFull == true, machines cannot traverse FOREST and VILLAGE.
     * @param t1 The tile id of the starting tile
     * @param t2 The tile id of the target tile
     * @param isMachineFull whether the machine contains harvest
     * @return Returns if there exist a path from tile t1 to t2 for machines.
     */
    fun findPathAStar(t1: Int, t2: Int, isMachineFull: Boolean): Boolean {
        val goalNode = getTile(t2)
        val openSet = PriorityQueue<Pair<Int, Int>>(compareBy { it.second })
        val cameFrom = mutableMapOf<Int, Int>()
        val gScore = mutableMapOf<Int, Int>().withDefault { Int.MAX_VALUE }
        val fScore = mutableMapOf<Int, Int>().withDefault { Int.MAX_VALUE }

        gScore[t1] = 0
        fScore[t1] = calculateHeuristic(getTile(t1), goalNode)
        openSet.add(t1 to fScore.getValue(t1))

        while (openSet.isNotEmpty()) {
            val currentId = openSet.poll().first
            if (currentId == t2) return true

            getTile(currentId).neighbours.values.forEach { neighbourId ->
                processNeighbour(
                    currentId, neighbourId, t1, isMachineFull, gScore,
                    fScore, cameFrom, openSet, goalNode
                )
            }
        }
        return false
    }

    /**
     * Process each neighbour of the current node in the A* algorithm.
     */
    private fun processNeighbour(
        currentId: Int,
        neighbourId: Int,
        startTileId: Int,
        isMachineFull: Boolean,
        gScore: MutableMap<Int, Int>,
        fScore: MutableMap<Int, Int>,
        cameFrom: MutableMap<Int, Int>,
        openSet: PriorityQueue<Pair<Int, Int>>,
        goalNode: Tile
    ) {
        if (!canTraverse(startTileId, neighbourId, isMachineFull)) return

        val tentativeGScore = gScore.getValue(currentId) + 1
        if (tentativeGScore < gScore.getValue(neighbourId)) {
            cameFrom[neighbourId] = currentId
            gScore[neighbourId] = tentativeGScore
            val neighbourTile = getTile(neighbourId)
            fScore[neighbourId] = tentativeGScore + calculateHeuristic(neighbourTile, goalNode)
            if (openSet.none { it.first == neighbourId }) {
                openSet.add(neighbourId to fScore.getValue(neighbourId))
            }
        }
    }

    /**
     * Calculate the Manhattan distance between two tiles.
     */
    private fun calculateHeuristic(tile: Tile, goal: Tile): Int {
        val dx = (tile.coordinate.x - goal.coordinate.x).absoluteValue
        val dy = (tile.coordinate.y - goal.coordinate.y).absoluteValue
        return dx + dy
    }

    /**
     * Returns a list of tiles having the same type according to the given tile id
     */
    // fun getNextNearestTileOfType(id: Int): List<Tile> = TODO()
    /**
     * Iterates over all the tiles and resets the harvest estimate of plantation tiles
     */
    fun resetPlantationHarvestEstimate(farmControllers: List<FarmController>) {
        for (tile in simulationMap.idToTile.values) {
            when (tile.category) {
                TileType.PLANTATION -> {
                    resetPlantation(tile, farmControllers)
                }
                else -> {}
            }
        }
    }

    /**
     * For a given tile, checks whether it's a plantation tile and resets the harvest estimate
     * @param tile the tile that needs reset
     */
    private fun resetPlantation(tile: Tile, farmControllers: List<FarmController>) {
        val plant = tile.plant as? PlantationPlant ?: return
        tile.harvestEstimate = plant.harvestEstimate()
        tile.startOfTickEstimate = -1

        // create all actions
        val farm = farmControllers.find { it.getFarmId() == tile.farm } ?: error(UNREACHABLE)
        // create and schedule harvesting
        val actionH = HarvestAction(
            plant,
            plant.harvestingPeriod.first,
            plant.harvestingPeriod.second,
            tile
        )
        farm.schedule.getOrPut(actionH.scheduleHarvestTick()) { mutableListOf() }.add(actionH)
        // create and schedule cutting and mowing
        actionH.plantationHarvest(farm.schedule)
    }
}

/**
 * Stores all tiles in a map from their id to a tile object
 */
data class SimulationMap(val idToTile: Map<Int, Tile>)
