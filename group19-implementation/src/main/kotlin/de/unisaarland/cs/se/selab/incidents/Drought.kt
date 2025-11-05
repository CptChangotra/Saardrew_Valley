package de.unisaarland.cs.se.selab.incidents

import de.unisaarland.cs.se.selab.farms.FALLOW_PERIOD
import de.unisaarland.cs.se.selab.farms.FarmController
import de.unisaarland.cs.se.selab.map.MapController
import de.unisaarland.cs.se.selab.map.Reason
import de.unisaarland.cs.se.selab.map.TileType
import de.unisaarland.cs.se.selab.utils.Logger

const val RESET_FOR_FALLOW_PERIOD = 4

/**
 * Represents a drought.
 */
class Drought(
    id: Int,
    tick: Int,
    mapController: MapController,
    val location: Int,
    val radius: Int,
    private val farmControllers: List<FarmController>
) : Incident(
    id,
    tick,
    mapController
) {
    override fun apply() {
        val tiles = mapController.getNeighboursGeometries(location, radius)
        val affectedFieldTiles = tiles.filter { it.category == TileType.FIELD }
        val affectedPlantationTiles = tiles.filter { it.category == TileType.PLANTATION }
        affectedFieldTiles.forEach { tile ->
            tile.harvestEstimate = 0
            if (tile.plant != null) { tile.fallowPeriod = FALLOW_PERIOD }
            tile.soilMoisture = 0
            tile.penalties.clear()
            tile.penalties.add(Pair(0, Reason.DROUGHT))
        }
        affectedPlantationTiles.forEach { tile ->
            tile.harvestEstimate = 0
            tile.soilMoisture = 0
            tile.penalties.clear()
            tile.penalties.add(Pair(0, Reason.DROUGHT))
        }
        val affectedTileId = affectedPlantationTiles.map { it.id } + affectedFieldTiles.map { it.id }
        this.farmControllers.forEach { farmC -> farmC.removeActionsIf { it in affectedTileId } }
        Logger.incident(id, "DROUGHT", affectedTileId)
    }
}
