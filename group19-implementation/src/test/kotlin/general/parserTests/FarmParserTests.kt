package general.parserTests

import de.unisaarland.cs.se.selab.map.MapController
import de.unisaarland.cs.se.selab.map.SimulationMap
import de.unisaarland.cs.se.selab.map.Tile
import de.unisaarland.cs.se.selab.map.TileType
import de.unisaarland.cs.se.selab.parser.FarmParser
import de.unisaarland.cs.se.selab.plant.FieldPlant
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever
import kotlin.math.abs

class FarmParserTests {

    private fun tileMock(
        id: Int,
        category: TileType,
        farmId: Int? = null,
        shed: Boolean = false,
        neighbours: Map<Int, Int> = emptyMap(),
        possiblePlants: List<FieldPlant>? = null
    ): Tile {
        val t = Mockito.mock(Tile::class.java)
        whenever(t.id).thenReturn(id)
        whenever(t.category).thenReturn(category)
        whenever(t.farm).thenReturn(farmId)
        whenever(t.shed).thenReturn(shed)
        whenever(t.neighbours).thenReturn(neighbours)
        whenever(t.possiblePlants).thenReturn(possiblePlants)
        return t
    }

    private fun stubMapController(idToTile: Map<Int, Tile>): MapController {
        val mapC = Mockito.mock(MapController::class.java)
        val simMap = Mockito.mock(SimulationMap::class.java)

        whenever(simMap.idToTile).thenReturn(idToTile)
        whenever(mapC.simulationMap).thenReturn(simMap)

        // mapC.getTile(id)
        idToTile.forEach { (id, t) ->
            whenever(mapC.getTile(id)).thenReturn(t)
        }

        // mapC.getNeighbourTilesAndCentre(center, radius)
        whenever(mapC.getNeighbourTilesAndCentre(any(), any())).thenAnswer { inv ->
            val center = inv.getArgument<Int>(0)
            val radius = inv.getArgument<Int>(1)
            idToTile.filter { (tid, _) -> abs(tid - center) <= radius }.values.toList()
        }

        return mapC
    }

    private fun jsonMachine(
        id: Int,
        name: String,
        actions: List<String>,
        plants: List<String>,
        duration: Int,
        location: Int
    ) = """
        { "id": $id, "name": "$name",
          "actions": [${actions.joinToString(",") { "\"$it\"" }}],
          "plants": [${plants.joinToString(",") { "\"$it\"" }}],
          "duration": $duration,
          "location": $location
        }
    """.trimIndent()

    private fun jsonPlanFields(id: Int, tick: Int, plant: String, fields: List<Int>) =
        """{ "id": $id, "tick": $tick, "plant": "$plant", "fields": [${fields.joinToString(",")}] }"""

    private fun jsonPlanRadius(id: Int, tick: Int, plant: String, location: Int, radius: Int) =
        """{ "id": $id, "tick": $tick, "plant": "$plant", "location": $location, "radius": $radius }"""

    private fun wrapFarm(
        id: Int,
        name: String,
        farmsteads: List<Int>,
        fields: List<Int>,
        plantations: List<Int>,
        machines: List<String>,
        plans: List<String>
    ) = """
        {
          "id": $id,
          "name": "$name",
          "farmsteads": [${farmsteads.joinToString(",")}],
          "fields": [${fields.joinToString(",")}],
          "plantations": [${plantations.joinToString(",")}],
          "machines": [${machines.joinToString(",")}],
          "sowingPlans": [${plans.joinToString(",")}]
        }
    """.trimIndent()

    private fun wrapAll(vararg farms: String) =
        """{ "farms": [ ${farms.joinToString(",")} ] }"""

    @Test
    fun happy_path_single_farm_valid() {
        val tShed = tileMock(1, TileType.FARMSTEAD, farmId = 0, shed = true)
        val tField = tileMock(10, TileType.FIELD, farmId = 0, possiblePlants = listOf(FieldPlant.POTATO))
        val tPlant = tileMock(20, TileType.PLANTATION, farmId = 0)
        val mapC = stubMapController(mapOf(1 to tShed, 10 to tField, 20 to tPlant))

        val m0 = jsonMachine(
            5,
            "SowerHarvester",
            actions = listOf("SOWING", "HARVESTING"),
            plants = listOf("POTATO", "WHEAT"),
            duration = 3,
            location = 1
        )
        val p0 = jsonPlanFields(7, 0, "POTATO", listOf(10))
        val farm = wrapFarm(0, "Alpha", listOf(1), listOf(10), listOf(20), listOf(m0), listOf(p0))

        val parser = FarmParser(wrapAll(farm))
        val res = parser.createFarms(mapC)
        assertTrue(res.isSuccess)
        val farms = res.getOrThrow()
        assertEquals(1, farms.size)
        assertEquals("Alpha", farms[0].name)
        assertEquals(listOf(10), farms[0].fields)
        assertEquals(listOf(20), farms[0].plantations)
        assertEquals(1, farms[0].machines.size)
        assertEquals(1, farms[0].sowingPlans.size)
    }

    @Test
    fun reject_unknown_key_strict_json() {
        val mapC = stubMapController(emptyMap())
        val badFarm = """
            {
              "id": 0, "name": "Alpha",
              "farmsteads": [], "fields": [], "plantations": [],
              "machines": [], "sowingPlans": [],
              "extraKey": "boom"
            }
        """.trimIndent()

        val res = FarmParser(wrapAll(badFarm)).createFarms(mapC)
        assertTrue(res.isFailure)
    }

    @Test
    fun reject_empty_farms_array() {
        val mapC = stubMapController(emptyMap())
        val res = FarmParser("""{ "farms": [] }""").createFarms(mapC)
        assertTrue(res.isFailure)
    }

    @Test
    fun reject_duplicate_farm_ids_and_names() {
        val shed0 = tileMock(1, TileType.FARMSTEAD, farmId = 0, shed = true)
        val shed1 = tileMock(2, TileType.FARMSTEAD, farmId = 0, shed = true)
        val mapC = stubMapController(mapOf(1 to shed0, 2 to shed1))

        val f0 = wrapFarm(
            0,
            "Dup",
            farmsteads = listOf(1),
            fields = emptyList(),
            plantations = emptyList(),
            machines = listOf(jsonMachine(1, "M", listOf("IRRIGATING"), listOf("POTATO"), 1, 1)),
            plans = emptyList()
        )
        val f1 = wrapFarm(
            0,
            "Dup",
            farmsteads = listOf(2),
            fields = emptyList(),
            plantations = emptyList(),
            machines = listOf(jsonMachine(2, "N", listOf("IRRIGATING"), listOf("POTATO"), 1, 2)),
            plans = emptyList()
        )

        assertTrue(FarmParser(wrapAll(f0, f1)).createFarms(mapC).isFailure)
    }

    @Test
    fun reject_wrong_category_in_lists() {
        val shed = tileMock(1, TileType.FARMSTEAD, farmId = 0, shed = true)
        val notAField = tileMock(10, TileType.PLANTATION, farmId = 0)
        val notAPlantation = tileMock(20, TileType.FIELD, farmId = 0)
        val mapC = stubMapController(mapOf(1 to shed, 10 to notAField, 20 to notAPlantation))

        val f = wrapFarm(
            0,
            "Mixup",
            listOf(1),
            listOf(10),
            listOf(20),
            machines = listOf(jsonMachine(5, "M", listOf("IRRIGATING"), listOf("POTATO"), 1, 1)),
            plans = emptyList()
        )

        assertTrue(FarmParser(wrapAll(f)).createFarms(mapC).isFailure)
    }

    @Test
    fun reject_map_owns_field_but_farm_does_not_list_it() {
        val shed = tileMock(1, TileType.FARMSTEAD, farmId = 0, shed = true)
        val fieldMissing = tileMock(10, TileType.FIELD, farmId = 0)
        val mapC = stubMapController(mapOf(1 to shed, 10 to fieldMissing))

        val f = wrapFarm(
            0,
            "MissingField",
            farmsteads = listOf(1),
            fields = emptyList(),
            plantations = emptyList(),
            machines = listOf(jsonMachine(9, "M", listOf("IRRIGATING"), listOf("POTATO"), 1, 1)),
            plans = emptyList()
        )

        assertTrue(FarmParser(wrapAll(f)).createFarms(mapC).isFailure)
    }

    @Test
    fun reject_farmstead_neighbour_belongs_to_other_farm() {
        val shed = tileMock(
            1,
            TileType.FARMSTEAD,
            farmId = 0,
            shed = true,
            neighbours = mapOf(0 to 10)
        )
        val foreignField = tileMock(10, TileType.FIELD, farmId = 1)
        val mapC = stubMapController(mapOf(1 to shed, 10 to foreignField))

        val f = wrapFarm(
            0,
            "NeighbourMismatch",
            farmsteads = listOf(1),
            fields = emptyList(),
            plantations = emptyList(),
            machines = listOf(jsonMachine(1, "M", listOf("IRRIGATING"), listOf("POTATO"), 1, 1)),
            plans = emptyList()
        )
        assertTrue(FarmParser(wrapAll(f)).createFarms(mapC).isFailure)
    }

    @Test
    fun reject_missing_machine_or_farmstead_or_land() {
        val shed = tileMock(1, TileType.FARMSTEAD, farmId = 0, shed = true)
        val mapC = stubMapController(mapOf(1 to shed))

        val fNoMachine = wrapFarm(0, "NoMachine", listOf(1), emptyList(), emptyList(), emptyList(), emptyList())
        assertTrue(FarmParser(wrapAll(fNoMachine)).createFarms(mapC).isFailure)

        val fNoFarmstead = wrapFarm(
            0,
            "NoFarmstead",
            emptyList(),
            emptyList(),
            emptyList(),
            machines = listOf(jsonMachine(1, "M", listOf("IRRIGATING"), listOf("POTATO"), 1, 1)),
            plans = emptyList()
        )
        assertTrue(FarmParser(wrapAll(fNoFarmstead)).createFarms(mapC).isFailure)

        val fNoLand = wrapFarm(
            0,
            "NoLand",
            listOf(1),
            emptyList(),
            emptyList(),
            machines = listOf(jsonMachine(1, "M", listOf("IRRIGATING"), listOf("POTATO"), 1, 1)),
            plans = emptyList()
        )
        assertTrue(FarmParser(wrapAll(fNoLand)).createFarms(mapC).isFailure)
    }

    @Test
    fun machines_reject_invalid_duration_not_shed_or_foreign_shed() {
        val ownShed = tileMock(1, TileType.FARMSTEAD, farmId = 0, shed = true)
        val notAShed = tileMock(2, TileType.FARMSTEAD, farmId = 0, shed = false)
        val foreignShed = tileMock(3, TileType.FARMSTEAD, farmId = 1, shed = true)
        val mapC = stubMapController(mapOf(1 to ownShed, 2 to notAShed, 3 to foreignShed))

        val badDur = wrapFarm(
            0,
            "A",
            listOf(1),
            emptyList(),
            emptyList(),
            machines = listOf(jsonMachine(1, "BadDur", listOf("IRRIGATING"), listOf("POTATO"), 0, 1)),
            plans = emptyList()
        )
        assertTrue(FarmParser(wrapAll(badDur)).createFarms(mapC).isFailure)

        val notShedFarm = wrapFarm(
            0,
            "B",
            listOf(1, 2),
            emptyList(),
            emptyList(),
            machines = listOf(jsonMachine(2, "NotShed", listOf("IRRIGATING"), listOf("POTATO"), 1, 2)),
            plans = emptyList()
        )
        assertTrue(FarmParser(wrapAll(notShedFarm)).createFarms(mapC).isFailure)

        val foreign = wrapFarm(
            0,
            "C",
            listOf(1, 3),
            emptyList(),
            emptyList(),
            machines = listOf(jsonMachine(3, "Foreign", listOf("IRRIGATING"), listOf("POTATO"), 1, 3)),
            plans = emptyList()
        )
        assertTrue(FarmParser(wrapAll(foreign)).createFarms(mapC).isFailure)
    }

    @Test
    fun plans_reject_both_or_neither_fields_and_radius() {
        val shed = tileMock(1, TileType.FARMSTEAD, farmId = 0, shed = true)
        val field = tileMock(10, TileType.FIELD, farmId = 0, possiblePlants = listOf(FieldPlant.POTATO))
        val mapC = stubMapController(mapOf(1 to shed, 10 to field))

        val badBoth = """{ "id": 1, "tick": 0, "plant": "POTATO", "fields": [10], "location": 1, "radius": 0 }"""
        val badNeither = """{ "id": 2, "tick": 0, "plant": "POTATO" }"""

        val fBoth = wrapFarm(
            0,
            "Alpha",
            listOf(1),
            listOf(10),
            emptyList(),
            machines = listOf(jsonMachine(9, "Sower", listOf("SOWING"), listOf("POTATO"), 1, 1)),
            plans = listOf(badBoth)
        )
        val fNeither = wrapFarm(
            0,
            "Beta",
            listOf(1),
            listOf(10),
            emptyList(),
            machines = listOf(jsonMachine(10, "Sower", listOf("SOWING"), listOf("POTATO"), 1, 1)),
            plans = listOf(badNeither)
        )

        assertTrue(FarmParser(wrapAll(fBoth)).createFarms(mapC).isFailure)
        assertTrue(FarmParser(wrapAll(fNeither)).createFarms(mapC).isFailure)
    }

    @Test
    fun plans_location_exists_and_radius_non_negative() {
        val shed = tileMock(1, TileType.FARMSTEAD, farmId = 0, shed = true)
        val mapC = stubMapController(mapOf(1 to shed))

        val badLocation = jsonPlanRadius(1, 0, "POTATO", location = 99, radius = 0)
        val badRadius = jsonPlanRadius(2, 0, "POTATO", location = 1, radius = -1)

        val f1 = wrapFarm(
            0,
            "Alpha",
            listOf(1),
            emptyList(),
            emptyList(),
            machines = listOf(jsonMachine(1, "S", listOf("SOWING"), listOf("POTATO"), 1, 1)),
            plans = listOf(badLocation)
        )
        val f2 = wrapFarm(
            0,
            "Beta",
            listOf(1),
            emptyList(),
            emptyList(),
            machines = listOf(jsonMachine(2, "S", listOf("SOWING"), listOf("POTATO"), 1, 1)),
            plans = listOf(badRadius)
        )

        assertTrue(FarmParser(wrapAll(f1)).createFarms(mapC).isFailure)
        assertTrue(FarmParser(wrapAll(f2)).createFarms(mapC).isFailure)
    }

    @Test
    fun plans_must_have_at_least_one_own_field_candidate_and_allow_plant() {
        val shed = tileMock(1, TileType.FARMSTEAD, farmId = 0, shed = true)
        val ownWrong = tileMock(10, TileType.FIELD, farmId = 0, possiblePlants = listOf(FieldPlant.WHEAT))
        val foreignRight = tileMock(11, TileType.FIELD, farmId = 1, possiblePlants = listOf(FieldPlant.POTATO))
        val ownRight = tileMock(12, TileType.FIELD, farmId = 0, possiblePlants = listOf(FieldPlant.POTATO))
        val mapC = stubMapController(mapOf(1 to shed, 10 to ownWrong, 11 to foreignRight, 12 to ownRight))
        val mapCSuccess = stubMapController(mapOf(1 to shed, 10 to ownWrong, 12 to ownRight))

        val pFail = jsonPlanFields(1, 0, "POTATO", listOf(10, 11))
        val fFail = wrapFarm(
            0,
            "Alpha",
            farmsteads = listOf(1),
            fields = listOf(10, 12),
            plantations = emptyList(),
            machines = listOf(jsonMachine(1, "S", listOf("SOWING"), listOf("POTATO"), 1, 1)),
            plans = listOf(pFail)
        )
        assertTrue(FarmParser(wrapAll(fFail)).createFarms(mapC).isFailure)

        val pOk = jsonPlanFields(2, 0, "POTATO", listOf(10, 12))
        val fOk = wrapFarm(
            0,
            "Beta",
            farmsteads = listOf(1),
            fields = listOf(10, 12),
            plantations = emptyList(),
            machines = listOf(jsonMachine(2, "S", listOf("SOWING"), listOf("POTATO"), 1, 1)),
            plans = listOf(pOk)
        )
        assertTrue(FarmParser(wrapAll(fOk)).createFarms(mapCSuccess).isSuccess)

        val pRad = jsonPlanRadius(3, 0, "POTATO", location = 1, radius = 100)
        val fRad = wrapFarm(
            0,
            "Gamma",
            farmsteads = listOf(1),
            fields = listOf(10, 12),
            plantations = emptyList(),
            machines = listOf(jsonMachine(3, "S", listOf("SOWING"), listOf("POTATO"), 1, 1)),
            plans = listOf(pRad)
        )
        assertTrue(FarmParser(wrapAll(fRad)).createFarms(mapCSuccess).isSuccess)
    }

    @Test
    fun plans_require_at_least_one_sowing_capable_machine() {
        val shed = tileMock(1, TileType.FARMSTEAD, farmId = 0, shed = true)
        val field = tileMock(10, TileType.FIELD, farmId = 0, possiblePlants = listOf(FieldPlant.POTATO))
        val mapC = stubMapController(mapOf(1 to shed, 10 to field))

        val plan = jsonPlanFields(1, 0, "POTATO", listOf(10))

        val fFail = wrapFarm(
            0,
            "Alpha",
            listOf(1),
            listOf(10),
            emptyList(),
            machines = listOf(jsonMachine(1, "Water", listOf("IRRIGATING"), listOf("POTATO"), 1, 1)),
            plans = listOf(plan)
        )
        assertTrue(FarmParser(wrapAll(fFail)).createFarms(mapC).isFailure)

        val fOk = wrapFarm(
            0,
            "Beta",
            listOf(1),
            listOf(10),
            emptyList(),
            machines = listOf(jsonMachine(2, "Sower", listOf("SOWING"), listOf("POTATO"), 1, 1)),
            plans = listOf(plan)
        )
        assertTrue(FarmParser(wrapAll(fOk)).createFarms(mapC).isSuccess)
    }

    @Test
    fun reject_nonexistent_tile_ids_are_referenced() {
        val shed = tileMock(1, TileType.FARMSTEAD, farmId = 0, shed = true)
        val field = tileMock(10, TileType.FIELD, farmId = 0, possiblePlants = listOf(FieldPlant.POTATO))
        val mapC = stubMapController(mapOf(1 to shed, 10 to field))

        val fBad = wrapFarm(
            0,
            "Alpha",
            farmsteads = listOf(1),
            fields = listOf(999),
            plantations = emptyList(),
            machines = listOf(jsonMachine(2, "Sower", listOf("SOWING"), listOf("POTATO"), 1, 1)),
            plans = listOf(jsonPlanFields(1, 0, "POTATO", listOf(10)))
        )
        assertTrue(FarmParser(wrapAll(fBad)).createFarms(mapC).isFailure)
    }

    @Test
    fun machine_ids_and_names_unique_globally() {
        val shedA = tileMock(1, TileType.FARMSTEAD, farmId = 0, shed = true)
        val shedB = tileMock(2, TileType.FARMSTEAD, farmId = 1, shed = true)
        val mapC = stubMapController(mapOf(1 to shedA, 2 to shedB))

        val mDupId = jsonMachine(1, "X", listOf("IRRIGATING"), listOf("POTATO"), 1, 1)
        val mDupName = jsonMachine(2, "X", listOf("IRRIGATING"), listOf("POTATO"), 1, 2)

        val f0 = wrapFarm(0, "A", listOf(1), emptyList(), emptyList(), listOf(mDupId), emptyList())
        val f1 = wrapFarm(1, "B", listOf(2), emptyList(), emptyList(), listOf(mDupName), emptyList())

        assertTrue(FarmParser(wrapAll(f0, f1)).createFarms(mapC).isFailure)
    }
}
