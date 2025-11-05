package de.unisaarland.cs.se.selab.incidents

import de.unisaarland.cs.se.selab.cloud.Cloud
import de.unisaarland.cs.se.selab.cloud.CloudController
import de.unisaarland.cs.se.selab.map.MapController
import de.unisaarland.cs.se.selab.map.TileType
import de.unisaarland.cs.se.selab.utils.Logger

/**
 * Represents a cloud creation.
 */
class CloudCreation(
    id: Int,
    tick: Int,
    mapController: MapController,
    val location: Int,
    val radius: Int,
    val duration: Int,
    val amount: Int,
    val cloudController: CloudController
) : Incident(
    id,
    tick,
    mapController
) {
    override fun apply() {
        val tiles = mapController.getNeighboursGeometries(location, radius)
            .filter { it.category != TileType.VILLAGE }
            .map { it.id }
            .sorted()

        // the cloud creation incident must be logged before the clouds themselves log their creation
        Logger.incident(id, "CLOUD_CREATION", tiles)

        tiles.forEach { tileId ->
            // dummy id, will be set by the [cloudController]
            val cloud = Cloud(-1, duration, tileId, amount, Cloud.MAX_MOVES)
            cloudController.createCloud(cloud)
        }
    }
}
