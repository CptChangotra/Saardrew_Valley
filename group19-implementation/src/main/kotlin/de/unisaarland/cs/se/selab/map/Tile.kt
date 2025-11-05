package de.unisaarland.cs.se.selab.map

import de.unisaarland.cs.se.selab.plant.FieldPlant
import de.unisaarland.cs.se.selab.plant.Plant
import kotlinx.serialization.Serializable

/**
 * Tile
 */
data class Tile(
    val id: Int,
    val coordinate: Coordinate,
    var category: TileType,
    var farm: Int?,
    var airflow: Boolean,
    var direction: Int?,
    var maxCapacity: Int?,
    var plant: Plant?,
    var possiblePlants: List<FieldPlant>?,
    var harvestEstimate: Int,
    var startOfTickEstimate: Int,
    var shed: Boolean,
    var fallowPeriod: Int?,
    var soilMoisture: Int,
    var amountSunlight: Int,
    var neighbours: Map<Int, Int>, // from degree to tile id
    var penalties: MutableList<Pair<Int, Reason>>
)

/**
 * contains a coordinate with x and y
 */
@Serializable
data class Coordinate(val x: Int, val y: Int)

/**
 * Add offset (delta) to coordinate
 */
operator fun Coordinate.plus(delta: Pair<Int, Int>): Coordinate {
    return Coordinate(this.x + delta.first, this.y + delta.second)
}

/**
 * enumeration of all tile types: FARMSTEAD,
 *     FOREST,
 *     FIELD,
 *     MEADOW,
 *     PLANTATION,
 *     ROAD,
 *     VILLAGE
 */
@Serializable
enum class TileType {
    FARMSTEAD,
    FOREST,
    FIELD,
    MEADOW,
    PLANTATION,
    ROAD,
    VILLAGE
}

/**
 * Reasons for applying penalties
 */
@Serializable
enum class Reason {
    MOWING,
    CUTTING,
    WEEDING,
    HARVESTING,
    DROUGHT,
    BEE_HAPPY,
    ANIMAL_ATTACK
}
