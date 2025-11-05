package de.unisaarland.cs.se.selab.incidents

import de.unisaarland.cs.se.selab.map.MapController
import de.unisaarland.cs.se.selab.map.Reason
import de.unisaarland.cs.se.selab.map.Tile
import de.unisaarland.cs.se.selab.map.TileType
import de.unisaarland.cs.se.selab.plant.FieldPlant
import de.unisaarland.cs.se.selab.plant.PlantationPlant
import de.unisaarland.cs.se.selab.utils.Logger
import de.unisaarland.cs.se.selab.utils.Tick

const val NEIGHBORS_RADIUS_M = 2
const val PERCENT = 100
const val OFFSET1_FOR_PUMPKIN = 2
const val OFFSET2_FOR_PUMPKIN = 4
const val OFFSET_FOR_POTATO = 4 // TODO assuming after three ticks means 3 full ticks

/**
 * Represents a bee pollination event.
 */
public final class BeeHappy(
    id: Int,
    tick: Int,
    mapController: MapController,
    val location: Int,
    val radius: Int,
    val effect: Int,
    val isFirst: Boolean
) : Incident(id, tick, mapController) {
    override fun apply() {
        val tiles = mapController.getNeighboursGeometries(location, radius)
        val meadowTiles = tiles.filter { it.category == TileType.MEADOW }

        if (meadowTiles.isEmpty()) return

        val meadowNeighbors: List<Tile> = meadowTiles.flatMap {
            mapController.getNeighboursGeometries(it.id, NEIGHBORS_RADIUS_M)
        }
            .distinctBy { it.id }
            .filter { it.plant != null }
            .filter { it.startOfTickEstimate != 0 }

        val processedIds = mutableListOf<Int>()

        meadowNeighbors
            .filter { it.category == TileType.PLANTATION && it.plant != PlantationPlant.GRAPE }
            .forEach { tile ->
                val plant = (tile.plant ?: return@forEach) as PlantationPlant
                if (
                    Tick.yTick in plant.bloomingPeriod.first..plant.bloomingPeriod.second
                ) {
                    tile.penalties.add(this.effect + PERCENT to Reason.BEE_HAPPY)
                    processedIds.add(tile.id)
                }
            }

        meadowNeighbors
            .filter { it.plant == FieldPlant.POTATO }
            .forEach { tile ->
                val plant = (tile.plant ?: return@forEach) as FieldPlant
                // 3 ticks after SOWING and the blooming period lasts for 1 tick.
                if (plant.lastSowingTick + OFFSET_FOR_POTATO == Tick.currentTick) {
                    processedIds.add(tile.id)
                }
            }

        meadowNeighbors
            .filter { it.plant == FieldPlant.PUMPKIN }
            .forEach { tile ->
                val plant = (tile.plant ?: return@forEach) as FieldPlant
                // TODO assuming after two ticks means 2 full ticks
                if (Tick.currentTick > plant.lastSowingTick + OFFSET1_FOR_PUMPKIN &&
                    Tick.currentTick <= plant.lastSowingTick + OFFSET2_FOR_PUMPKIN
                ) {
                    tile.penalties.add(this.effect + PERCENT to Reason.BEE_HAPPY)
                    processedIds.add(tile.id)
                }
            }
        Logger.incident(id, "BEE_HAPPY", processedIds)
    }
}
