package de.unisaarland.cs.se.selab.incidents
import de.unisaarland.cs.se.selab.cloud.CloudController
import de.unisaarland.cs.se.selab.farms.FarmController
import de.unisaarland.cs.se.selab.map.MapController
import de.unisaarland.cs.se.selab.map.TileType
import de.unisaarland.cs.se.selab.utils.Logger

/**
 * Represents a city expansion.
 */
class CityExpand(
    id: Int,
    tick: Int,
    mapController: MapController,
    val location: Int,
    val cloudController: CloudController,
    val farmController: List<FarmController>
) : Incident(
    id,
    tick,
    mapController
) {
    override fun apply() {
        val affectedTile = mapController.getTile(this.location)
        if (affectedTile.category == TileType.FIELD) {
            this.farmController.map { farmC ->
                farmC.removeFieldFromFarm(affectedTile.id)
            }
        }
        affectedTile.category = TileType.VILLAGE
        affectedTile.airflow = false
        affectedTile.direction = null
        affectedTile.farm = null
        affectedTile.maxCapacity = null
        affectedTile.plant = null
        affectedTile.possiblePlants = null
        affectedTile.harvestEstimate = 0
        affectedTile.startOfTickEstimate = 0
        affectedTile.shed = false
        affectedTile.soilMoisture = 0
        affectedTile.fallowPeriod = null
        affectedTile.amountSunlight = 0
        Logger.incident(id, "CITY_EXPANSION", listOf(this.location))
        val possibleCloud = cloudController.cloudMap.remove(affectedTile.id)
        if (possibleCloud != null) {
            cloudController.clouds.remove(possibleCloud)
            Logger.cloudStuck(possibleCloud.id, affectedTile.id)
        }
    }
}
