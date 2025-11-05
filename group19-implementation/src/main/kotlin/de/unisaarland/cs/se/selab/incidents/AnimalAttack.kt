package de.unisaarland.cs.se.selab.incidents
import de.unisaarland.cs.se.selab.farms.FarmController
import de.unisaarland.cs.se.selab.map.MapController
import de.unisaarland.cs.se.selab.map.Reason
import de.unisaarland.cs.se.selab.map.TileType
import de.unisaarland.cs.se.selab.plant.PlantationPlant
import de.unisaarland.cs.se.selab.utils.Logger

const val NEIGHBORS_RADIUS = 1
const val APPLE_CHERRY_REDUCTION = 90 // also almond btw
const val HALF = 50

/**
 * Represents an animal attack event.
 *
 * This class models information related to attacks by animals.
 */
class AnimalAttack(
    id: Int,
    tick: Int,
    mapController: MapController,
    val location: Int,
    val radius: Int,
    val farmController: List<FarmController>
) : Incident(id, tick, mapController) {
    override fun apply() {
        val tileIds = mapController.getNeighboursGeometries(location, radius)
        val forestTiles = tileIds.filter { it.category == TileType.FOREST }
        if (forestTiles.isEmpty()) return

        val forestNeighbors = forestTiles.flatMap { mapController.getNeighboursGeometries(it.id, NEIGHBORS_RADIUS) }
            .filter { it.category == TileType.PLANTATION || it.category == TileType.FIELD }
            .distinctBy { it.id }
        val processedIds = mutableListOf<Int>()
        val toRemoveMowing = mutableListOf<Int>()
        forestNeighbors.map {
            if (it.category == TileType.FIELD) {
                processedIds.add(it.id)
                it.penalties.add(Pair(HALF, Reason.ANIMAL_ATTACK))
            } else if (it.category == TileType.PLANTATION) {
                processedIds.add(it.id)
                if (it.plant?.let { plant -> plant == PlantationPlant.GRAPE } == true) {
                    it.penalties.add(Pair(HALF, Reason.ANIMAL_ATTACK))
                } else {
                    it.penalties.add(Pair(APPLE_CHERRY_REDUCTION, Reason.ANIMAL_ATTACK))
                    it.penalties.removeAll { penalty -> penalty.second == Reason.MOWING }
                    toRemoveMowing.add(it.id)
                }
            }
        }
        farmController.map { it.removeMowing(toRemoveMowing) }
        // farmController.forEach { it.removeMowing(toRemoveMowing) }
        Logger.incident(id, "ANIMAL_ATTACK", processedIds)
    }
}
