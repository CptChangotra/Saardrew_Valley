package general.incidentsTests

import de.unisaarland.cs.se.selab.incidents.AnimalAttack
import de.unisaarland.cs.se.selab.map.Coordinate
import de.unisaarland.cs.se.selab.map.MapController
import de.unisaarland.cs.se.selab.map.Reason
import de.unisaarland.cs.se.selab.map.SimulationMap
import de.unisaarland.cs.se.selab.map.Tile
import de.unisaarland.cs.se.selab.map.TileType
import de.unisaarland.cs.se.selab.plant.FieldPlant
import de.unisaarland.cs.se.selab.plant.PlantationPlant
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class AnimalAttackTests {

    private fun createTile(
        id: Int,
        x: Int,
        y: Int,
        type: TileType,
        plant: de.unisaarland.cs.se.selab.plant.Plant? = null,
        harvestEstimate: Int = 1000
    ): Tile {
        return Tile(
            id = id,
            coordinate = Coordinate(x, y),
            category = type,
            farm = null,
            airflow = false,
            direction = null,
            maxCapacity = null,
            plant = plant,
            possiblePlants = null,
            harvestEstimate = harvestEstimate,
            startOfTickEstimate = if (plant != null) 1 else 0,
            shed = false,
            fallowPeriod = null,
            soilMoisture = 500,
            amountSunlight = 150,
            neighbours = emptyMap(),
            penalties = mutableListOf()
        )
    }

    @Test
    fun `forest adjacent to field halves harvest`() {
        val forest = createTile(0, 0, 0, TileType.FOREST)
        val field = createTile(1, 1, 0, TileType.FIELD, FieldPlant.WHEAT, 1000)

        val tiles = mapOf(
            forest.id to forest,
            field.id to field
        )
        val simulationMap = SimulationMap(tiles)
        val mapController = MapController(simulationMap)

        val incident = AnimalAttack(1, 1, mapController, forest.id, 1, emptyList())
        incident.apply()

        // Check if ANIMAL_ATTACK penalty was added (50% = half)
        val animalAttackPenalties = field.penalties.filter { it.second == Reason.ANIMAL_ATTACK }
        assertEquals(1, animalAttackPenalties.size, "Expected exactly one ANIMAL_ATTACK penalty")
        assertEquals(50, animalAttackPenalties.first().first, "Expected penalty value to be 50 (half)")
    }

    @Test
    fun `forest adjacent to grape plantation halves harvest`() {
        val forest = createTile(0, 0, 0, TileType.FOREST)
        val grape = createTile(1, 1, 0, TileType.PLANTATION, PlantationPlant.GRAPE, 1000)

        val tiles = mapOf(
            forest.id to forest,
            grape.id to grape
        )
        val simulationMap = SimulationMap(tiles)
        val mapController = MapController(simulationMap)

        val incident = AnimalAttack(1, 1, mapController, forest.id, 1, emptyList())
        incident.apply()

        // Check if ANIMAL_ATTACK penalty was added (50% = half)
        val animalAttackPenalties = grape.penalties.filter { it.second == Reason.ANIMAL_ATTACK }
        assertEquals(1, animalAttackPenalties.size, "Expected exactly one ANIMAL_ATTACK penalty")
        assertEquals(50, animalAttackPenalties.first().first, "Expected penalty value to be 50 (half)")
    }

    @Test
    fun `forest adjacent to apple plantation reduces by 10 percent and removes mowing`() {
        val forest = createTile(0, 0, 0, TileType.FOREST)
        val apple = createTile(1, 1, 0, TileType.PLANTATION, PlantationPlant.APPLE, 1000)

        // Add a mowing penalty that should be removed
        apple.penalties.add(Pair(80, Reason.MOWING))

        val tiles = mapOf(
            forest.id to forest,
            apple.id to apple
        )
        val simulationMap = SimulationMap(tiles)
        val mapController = MapController(simulationMap)

        val incident = AnimalAttack(1, 1, mapController, forest.id, 1, emptyList())
        incident.apply()

        // Check if ANIMAL_ATTACK penalty was added (90% = 10% reduction)
        val animalAttackPenalties = apple.penalties.filter { it.second == Reason.ANIMAL_ATTACK }
        assertEquals(1, animalAttackPenalties.size, "Expected exactly one ANIMAL_ATTACK penalty")
        assertEquals(90, animalAttackPenalties.first().first, "Expected penalty value to be 90 (10% reduction)")

        // Check if mowing penalty was removed
        val mowingPenalties = apple.penalties.filter { it.second == Reason.MOWING }
        assertEquals(0, mowingPenalties.size, "Expected mowing penalties to be removed")
    }

    @Test
    fun `forest adjacent to almond plantation reduces by 10 percent and removes mowing`() {
        val forest = createTile(0, 0, 0, TileType.FOREST)
        val almond = createTile(1, 1, 0, TileType.PLANTATION, PlantationPlant.ALMOND, 1000)

        // Add a mowing penalty that should be removed
        almond.penalties.add(Pair(80, Reason.MOWING))

        val tiles = mapOf(
            forest.id to forest,
            almond.id to almond
        )
        val simulationMap = SimulationMap(tiles)
        val mapController = MapController(simulationMap)

        val incident = AnimalAttack(1, 1, mapController, forest.id, 1, emptyList())
        incident.apply()

        // Check if ANIMAL_ATTACK penalty was added (90% = 10% reduction)
        val animalAttackPenalties = almond.penalties.filter { it.second == Reason.ANIMAL_ATTACK }
        assertEquals(1, animalAttackPenalties.size, "Expected exactly one ANIMAL_ATTACK penalty")
        assertEquals(90, animalAttackPenalties.first().first, "Expected penalty value to be 90 (10% reduction)")

        // Check if mowing penalty was removed
        val mowingPenalties = almond.penalties.filter { it.second == Reason.MOWING }
        assertEquals(0, mowingPenalties.size, "Expected mowing penalties to be removed")
    }

    @Test
    fun `forest adjacent to cherry plantation reduces by 10 percent and removes mowing`() {
        val forest = createTile(0, 0, 0, TileType.FOREST)
        val cherry = createTile(1, 1, 0, TileType.PLANTATION, PlantationPlant.CHERRY, 1000)

        // Add a mowing penalty that should be removed
        cherry.penalties.add(Pair(80, Reason.MOWING))

        val tiles = mapOf(
            forest.id to forest,
            cherry.id to cherry
        )
        val simulationMap = SimulationMap(tiles)
        val mapController = MapController(simulationMap)

        val incident = AnimalAttack(1, 1, mapController, forest.id, 1, emptyList())
        incident.apply()

        // Check if ANIMAL_ATTACK penalty was added (90% = 10% reduction)
        val animalAttackPenalties = cherry.penalties.filter { it.second == Reason.ANIMAL_ATTACK }
        assertEquals(1, animalAttackPenalties.size, "Expected exactly one ANIMAL_ATTACK penalty")
        assertEquals(90, animalAttackPenalties.first().first, "Expected penalty value to be 90 (10% reduction)")

        // Check if mowing penalty was removed
        val mowingPenalties = cherry.penalties.filter { it.second == Reason.MOWING }
        assertEquals(0, mowingPenalties.size, "Expected mowing penalties to be removed")
    }

    @Test
    fun `multiple forests adjacent to single field only halves once`() {
        val forest1 = createTile(0, 0, 0, TileType.FOREST)
        val forest2 = createTile(1, 2, 0, TileType.FOREST)
        val field = createTile(2, 1, 0, TileType.FIELD, FieldPlant.WHEAT, 1000)

        val tiles = mapOf(
            forest1.id to forest1,
            forest2.id to forest2,
            field.id to field
        )
        val simulationMap = SimulationMap(tiles)
        val mapController = MapController(simulationMap)

        // Use a large radius to include both forests
        val incident = AnimalAttack(1, 1, mapController, field.id, 3, emptyList())
        incident.apply()

        // Check if ANIMAL_ATTACK penalty was added only once despite multiple forests
        val animalAttackPenalties = field.penalties.filter { it.second == Reason.ANIMAL_ATTACK }
        assertEquals(
            1,
            animalAttackPenalties.size,
            "Expected exactly one ANIMAL_ATTACK penalty, even with multiple forests"
        )
        assertEquals(50, animalAttackPenalties.first().first, "Expected penalty value to be 50 (half)")
    }

    @Test
    fun `zero harvest field remains at zero`() {
        val forest = createTile(0, 0, 0, TileType.FOREST)
        val field = createTile(1, 1, 0, TileType.FIELD, FieldPlant.WHEAT, 0) // Zero harvest

        val tiles = mapOf(
            forest.id to forest,
            field.id to field
        )
        val simulationMap = SimulationMap(tiles)
        val mapController = MapController(simulationMap)

        val incident = AnimalAttack(1, 1, mapController, forest.id, 1, emptyList())
        incident.apply()

        // Check if ANIMAL_ATTACK penalty was added (even for zero harvest)
        val animalAttackPenalties = field.penalties.filter { it.second == Reason.ANIMAL_ATTACK }
        assertEquals(1, animalAttackPenalties.size, "Expected ANIMAL_ATTACK penalty to be added even for zero harvest")
        assertEquals(50, animalAttackPenalties.first().first, "Expected penalty value to be 50 (half)")
        assertEquals(0, field.harvestEstimate, "Expected harvest to remain at 0")
    }

    @Test
    fun `no damageable neighbors means no changes`() {
        val forest = createTile(0, 0, 0, TileType.FOREST)
        val road = createTile(1, 1, 0, TileType.ROAD)
        val village = createTile(2, 0, 1, TileType.VILLAGE)

        val tiles = mapOf(
            forest.id to forest,
            road.id to road,
            village.id to village
        )
        val simulationMap = SimulationMap(tiles)
        val mapController = MapController(simulationMap)

        val incident = AnimalAttack(1, 1, mapController, forest.id, 1, emptyList())
        incident.apply()

        // Check that no penalties were added to any tiles
        assertTrue(road.penalties.isEmpty(), "Road should not have any penalties")
        assertTrue(village.penalties.isEmpty(), "Village should not have any penalties")
        assertTrue(forest.penalties.isEmpty(), "Forest should not have any penalties")
    }
}
