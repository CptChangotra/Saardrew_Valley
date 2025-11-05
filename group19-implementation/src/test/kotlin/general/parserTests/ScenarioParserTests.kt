package general.parserTests
import de.unisaarland.cs.se.selab.cloud.CloudController
import de.unisaarland.cs.se.selab.farms.Farm
import de.unisaarland.cs.se.selab.farms.FarmController
import de.unisaarland.cs.se.selab.farms.Machine
import de.unisaarland.cs.se.selab.farms.SowingPlan
import de.unisaarland.cs.se.selab.map.Coordinate
import de.unisaarland.cs.se.selab.map.MapController
import de.unisaarland.cs.se.selab.map.Tile
import de.unisaarland.cs.se.selab.map.TileType
import de.unisaarland.cs.se.selab.parser.MapParser
import de.unisaarland.cs.se.selab.parser.ScenarioParser
import de.unisaarland.cs.se.selab.plant.FieldPlant
import org.junit.jupiter.api.BeforeEach
import org.mockito.Mockito
import org.mockito.kotlin.whenever
import kotlin.test.Test
import kotlin.test.assertNotNull

object TestStrings2 {
    const val EXAMPLE_STRING_MAP = """{
  "tiles": [
    {
      "id": 0,
      "coordinates": { "x": 1, "y": 1 },
      "category": "FARMSTEAD",
      "farm": 1,
      "shed": true,
      "airflow": false
    },
    {
      "id": 1,
      "coordinates": { "x": 2, "y": 2 },
      "category": "FIELD",
      "farm": 1,
      "capacity": 1000,
      "possiblePlants": ["WHEAT", "OAT"],
      "airflow": false
    },
    {
      "id": 2,
      "coordinates": { "x": 6, "y": 2 },
      "category": "FIELD",
      "farm": 1,
      "capacity": 1000,
      "possiblePlants": ["WHEAT", "OAT"],
      "airflow": false
    },
    {
      "id": 3,
      "coordinates": { "x": 4, "y": 2 },
      "category": "VILLAGE"
    }
  ]
}"""
    const val MAP_2 = """{
  "tiles": [
    {
      "id": 0,
      "coordinates": { "x": 1, "y": 1 },
      "category": "FARMSTEAD",
      "farm": 1,
      "shed": true,
      "airflow": false
    },
    {
      "id": 1,
      "coordinates": { "x": 2, "y": 2 },
      "category": "FIELD",
      "farm": 1,
      "capacity": 1000,
      "possiblePlants": ["WHEAT", "OAT"],
      "airflow": false
    },
    {
      "id": 2,
      "coordinates": { "x": 6, "y": 2 },
      "category": "FIELD",
      "farm": 1,
      "capacity": 1000,
      "possiblePlants": ["WHEAT", "OAT"],
      "airflow": false
    },
    {
      "id": 4,
      "coordinates": { "x": 2, "y": 0 },
      "category": "FOREST",
      "airflow": false
    }
  ]
}"""
    const val SCENARIO = """{
  "clouds": [
  ],
  "incidents": [
    {
      "id": 444,
      "tick": 0,
      "type": "CITY_EXPANSION",
      "location": 1
    }
  ]
}"""
    const val SCENARIO_2 = """{
  "clouds": [
  ],
  "incidents": [
    {
      "id": 333,
      "type": "ANIMAL_ATTACK",
      "tick": 3,
      "location": 1,
      "radius": 2
    }
  ]
}"""
}

class ScenarioParserTests {
    private lateinit var tile1: Tile
    private lateinit var tile2: Tile
    private lateinit var tile3: Tile
    private lateinit var tile4: Tile
    private lateinit var tile5: Tile
    val mockMapController: MapController = Mockito.mock(MapController::class.java)
    val mockFarmController: FarmController = Mockito.mock(FarmController::class.java)
    val mockCloudController: CloudController = Mockito.mock(CloudController::class.java)
    val farm = Farm(
        1,
        "Farm",
        listOf(1),
        listOf(1),
        listOf(1),
        emptyList(),
        listOf(
            Machine(
                1,
                "cool",
                listOf("HARVESTING"), // simpleName of Action subclasses
                listOf("CHERRY"), // simpleName of Plant subclasses
                2,
                0,
                0,
                null,
                1,
                0,
            )
        ),
        emptyList(),
        emptyList()
    )

    @BeforeEach
    fun setup() {
        tile1 = Tile(
            id = 0, coordinate = Coordinate(1, 1), category = TileType.FARMSTEAD, farm = 0,
            airflow = false, direction = null, maxCapacity = null, plant = null,
            possiblePlants = null, harvestEstimate = 0, startOfTickEstimate = 0,
            shed = true, fallowPeriod = null, soilMoisture = 0, amountSunlight = 0,
            neighbours = emptyMap(), penalties = mutableListOf()
        )
        tile2 = Tile(
            id = 1, coordinate = Coordinate(2, 0), category = TileType.FIELD, farm = 0,
            airflow = false, direction = null, maxCapacity = 1000, plant = null,
            possiblePlants = listOf(FieldPlant.OAT, FieldPlant.WHEAT), harvestEstimate = 0, startOfTickEstimate = 0,
            shed = false, fallowPeriod = 0, soilMoisture = 100, amountSunlight = 100,
            neighbours = emptyMap(), penalties = mutableListOf()
        )
        tile3 = Tile(
            id = 2, coordinate = Coordinate(6, 0), category = TileType.FIELD, farm = 0,
            airflow = false, direction = null, maxCapacity = 1000, plant = null,
            possiblePlants = listOf(FieldPlant.OAT, FieldPlant.WHEAT), harvestEstimate = 0, startOfTickEstimate = 0,
            shed = false, fallowPeriod = 0, soilMoisture = 100, amountSunlight = 100,
            neighbours = emptyMap(), penalties = mutableListOf()
        )
        tile4 = Tile(
            id = 3, coordinate = Coordinate(4, 2), category = TileType.VILLAGE, farm = null,
            airflow = false, direction = null, maxCapacity = null, plant = null,
            possiblePlants = null, harvestEstimate = 0, startOfTickEstimate = 0,
            shed = false, fallowPeriod = null, soilMoisture = 0, amountSunlight = 0,
            neighbours = emptyMap(), penalties = mutableListOf()
        )
        tile5 = Tile(
            id = 4, coordinate = Coordinate(2, 0), category = TileType.FOREST, farm = null,
            airflow = false, direction = null, maxCapacity = null, plant = null,
            possiblePlants = null, harvestEstimate = 0, startOfTickEstimate = 0,
            shed = false, fallowPeriod = null, soilMoisture = 0, amountSunlight = 0,
            neighbours = emptyMap(), penalties = mutableListOf()
        )
    }

    @Test
    fun incidentTest() {
        val parser = MapParser(config = TestStrings2.EXAMPLE_STRING_MAP)
        val sMapRes = parser.createMap()
        val sMap = sMapRes.getOrThrow()
        whenever(mockMapController.simulationMap).thenReturn(sMap)
        whenever(mockFarmController.farm).thenReturn(farm)
        whenever(mockMapController.getTile(0)).thenReturn(tile1)
        whenever(mockMapController.getTile(1)).thenReturn(tile2)
        whenever(mockMapController.getTile(2)).thenReturn(tile3)
        whenever(mockMapController.getTile(3)).thenReturn(tile4)
        whenever(mockMapController.getNeighboursGeometries(1, 1)).thenReturn(listOf(tile1, tile4))
        val sParser = ScenarioParser(TestStrings2.SCENARIO)
        val sIncidents = sParser.createIncidents(mockMapController, listOf(mockFarmController), mockCloudController)
        val incidents = sIncidents.getOrThrow()
        assertNotNull(incidents)
    }

    @Test
    fun incidentTest2() {
        val parser = MapParser(config = TestStrings2.MAP_2)
        val sMapRes = parser.createMap()
        val sMap = sMapRes.getOrThrow()
        whenever(mockMapController.simulationMap).thenReturn(sMap)
        whenever(mockFarmController.farm).thenReturn(farm)
        whenever(mockMapController.getTile(0)).thenReturn(tile1)
        whenever(mockMapController.getTile(1)).thenReturn(tile2)
        whenever(mockMapController.getTile(2)).thenReturn(tile3)
        whenever(mockMapController.getTile(3)).thenReturn(tile4)
        whenever(mockMapController.getNeighboursGeometries(1, 2)).thenReturn(listOf(tile5))
        val sParser = ScenarioParser(TestStrings2.SCENARIO_2)
        val sIncidents = sParser.createIncidents(mockMapController, listOf(mockFarmController), mockCloudController)
        val incidents = sIncidents.getOrThrow()
        assertNotNull(incidents)
    }

    @Test
    fun clouds_success() {
        val parser = MapParser(config = TestStrings2.EXAMPLE_STRING_MAP)
        val sMap = parser.createMap().getOrThrow()

        val sp = ScenarioParser(
            """
        {
          "clouds": [
            {"id": 10, "location": 1, "duration": 5, "amount": 100},
            {"id": 11, "location": 2, "duration": -1, "amount": 50}
          ],
          "incidents": []
        }
            """.trimIndent()
        )

        val res = sp.createClouds(sMap)
        kotlin.test.assertTrue(res.isSuccess)
    }

    @Test
    fun clouds_invalid_location_should_fail() {
        val parser = MapParser(config = TestStrings2.EXAMPLE_STRING_MAP)
        val sMap = parser.createMap().getOrThrow()

        val sp = ScenarioParser(
            """{"clouds":[{"id": 10, "location": 999, "duration": 5, "amount": 100}], "incidents":[]}"""
        )

        val res = sp.createClouds(sMap)
        kotlin.test.assertTrue(res.isFailure)
    }

    @Test
    fun clouds_duplicate_ids_or_bad_amount_should_fail() {
        val parser = MapParser(config = TestStrings2.EXAMPLE_STRING_MAP)
        val sMap = parser.createMap().getOrThrow()

        val sp = ScenarioParser(
            """
        {
          "clouds":[
            {"id": 10, "location": 1, "duration": 5, "amount": 100},
            {"id": 10, "location": 2, "duration": 5, "amount": -1}
          ],
          "incidents":[]
        }
            """.trimIndent()
        )

        val res = sp.createClouds(sMap)
        kotlin.test.assertTrue(res.isFailure)
    }

    @Test
    fun incident_broken_machine_success() {
        val parser = MapParser(config = TestStrings2.EXAMPLE_STRING_MAP)
        val sMap = parser.createMap().getOrThrow()

        val mockMapController: MapController = Mockito.mock(MapController::class.java)
        val mockFarmController: FarmController = Mockito.mock(FarmController::class.java)
        val mockCloudController: CloudController = Mockito.mock(CloudController::class.java)
        whenever(mockMapController.simulationMap).thenReturn(sMap)

        val machine = Machine(1, "m", emptyList(), emptyList(), 0, 0, 0, null, 0, 0)
        val farm =
            Farm(
                1, "Farm", emptyList(), emptyList(), emptyList(), emptyList(),
                listOf(
                    machine
                ),
                emptyList(), emptyList()
            )
        whenever(mockFarmController.farm).thenReturn(farm)

        val sp = ScenarioParser(
            """
        {
          "clouds": [],
          "incidents": [
            {"id": 1, "type": "BROKEN_MACHINE", "tick": 0, "duration": 3, "machineId": 1}
          ]
        }
            """.trimIndent()
        )
        val res = sp.createIncidents(mockMapController, listOf(mockFarmController), mockCloudController)
        kotlin.test.assertTrue(res.isSuccess)
    }

    @Test
    fun incident_broken_machine_invalid_should_fail() {
        val parser = MapParser(config = TestStrings2.EXAMPLE_STRING_MAP)
        val sMap = parser.createMap().getOrThrow()

        val mockMapController: MapController = Mockito.mock(MapController::class.java)
        val mockFarmController: FarmController = Mockito.mock(FarmController::class.java)
        val mockCloudController: CloudController = Mockito.mock(CloudController::class.java)
        whenever(mockMapController.simulationMap).thenReturn(sMap)

        // farm without machine 999 or duration invalid
        val farm =
            Farm(1, "Farm", emptyList(), emptyList(), emptyList(), emptyList(), emptyList(), emptyList(), emptyList())
        whenever(mockFarmController.farm).thenReturn(farm)

        val sp = ScenarioParser(
            """
        {
          "clouds": [],
          "incidents": [
            {"id": 1, "type": "BROKEN_MACHINE", "tick": 0, "duration": 0, "machineId": 999}
          ]
        }
            """.trimIndent()
        )
        val res = sp.createIncidents(mockMapController, listOf(mockFarmController), mockCloudController)
        kotlin.test.assertTrue(res.isFailure)
    }

    @Test
    fun incident_bee_happy_no_meadow_should_fail() {
        // MAP 1 has no MEADOW; fail even if effect > 0
        val parser = MapParser(config = TestStrings2.EXAMPLE_STRING_MAP)
        val sMap = parser.createMap().getOrThrow()

        val mockMapController: MapController = Mockito.mock(MapController::class.java)
        val mockFarmController: FarmController = Mockito.mock(FarmController::class.java)
        val mockCloudController: CloudController = Mockito.mock(CloudController::class.java)
        whenever(mockMapController.simulationMap).thenReturn(sMap)

        // Return only VILLAGE as neighbor for (loc=1, radius=1)
        val village = sMap.idToTile[3] ?: return
        whenever(mockMapController.getNeighboursGeometries(1, 1)).thenReturn(listOf(village))

        val sp = ScenarioParser(
            """{"clouds": [], "incidents": [
            {"id": 1, "type": "BEE_HAPPY", "tick": 0, "location": 1, "radius": 1, "effect": 5}
        ]}"""
        )
        val res = sp.createIncidents(mockMapController, listOf(mockFarmController), mockCloudController)
        kotlin.test.assertTrue(res.isFailure)
    }

    @Test
    fun incident_bee_happy_invalid_effect_should_fail() {
        val parser = MapParser(config = TestStrings2.EXAMPLE_STRING_MAP)
        val sMap = parser.createMap().getOrThrow()

        val mockMapController: MapController = Mockito.mock(MapController::class.java)
        val mockFarmController: FarmController = Mockito.mock(FarmController::class.java)
        val mockCloudController: CloudController = Mockito.mock(CloudController::class.java)
        whenever(mockMapController.simulationMap).thenReturn(sMap)

        val village = sMap.idToTile[3] ?: return
        whenever(mockMapController.getNeighboursGeometries(1, 1)).thenReturn(listOf(village))

        val sp = ScenarioParser(
            """{"clouds": [], "incidents": [
            {"id": 1, "type": "BEE_HAPPY", "tick": 0, "location": 1, "radius": 1, "effect": 0}
        ]}"""
        )
        val res = sp.createIncidents(mockMapController, listOf(mockFarmController), mockCloudController)
        kotlin.test.assertTrue(res.isFailure)
    }

    @Test
    fun incident_drought_no_fields_should_fail() {
        val parser = MapParser(config = TestStrings2.EXAMPLE_STRING_MAP)
        val sMap = parser.createMap().getOrThrow()

        val mockMapController: MapController = Mockito.mock(MapController::class.java)
        val mockFarmController: FarmController = Mockito.mock(FarmController::class.java)
        val mockCloudController: CloudController = Mockito.mock(CloudController::class.java)
        whenever(mockMapController.simulationMap).thenReturn(sMap)

        val village = sMap.idToTile[3] ?: return
        whenever(mockMapController.getNeighboursGeometries(3, 1)).thenReturn(listOf(village))

        val sp = ScenarioParser(
            """{"clouds": [], "incidents": [
            {"id": 1, "type": "DROUGHT", "tick": 0, "location": 3, "radius": 1}
        ]}"""
        )
        val res = sp.createIncidents(mockMapController, listOf(mockFarmController), mockCloudController)
        kotlin.test.assertTrue(res.isFailure)
    }

    @Test
    fun incident_city_expansion_invalid_target_should_fail() {
        val parser = MapParser(config = TestStrings2.EXAMPLE_STRING_MAP)
        val sMap = parser.createMap().getOrThrow()

        val mockMapController: MapController = Mockito.mock(MapController::class.java)
        val mockFarmController: FarmController = Mockito.mock(FarmController::class.java)
        val mockCloudController: CloudController = Mockito.mock(CloudController::class.java)
        whenever(mockMapController.simulationMap).thenReturn(sMap)

        // target is VILLAGE (id=3) -> invalid (must be FIELD or ROAD)
        val village = sMap.idToTile[3] ?: return
        whenever(mockMapController.getNeighboursGeometries(3, 1)).thenReturn(listOf(village))

        val sp = ScenarioParser(
            """{"clouds": [], "incidents": [
            {"id": 1, "type": "CITY_EXPANSION", "tick": 0, "location": 3}
        ]}"""
        )
        val res = sp.createIncidents(mockMapController, listOf(mockFarmController), mockCloudController)
        kotlin.test.assertTrue(res.isFailure)
    }

    @Test
    fun incident_animal_attack_no_forest_should_fail() {
        val parser = MapParser(config = TestStrings2.EXAMPLE_STRING_MAP)
        val sMap = parser.createMap().getOrThrow()

        val mockMapController: MapController = Mockito.mock(MapController::class.java)
        val mockFarmController: FarmController = Mockito.mock(FarmController::class.java)
        val mockCloudController: CloudController = Mockito.mock(CloudController::class.java)
        whenever(mockMapController.simulationMap).thenReturn(sMap)

        // Only VILLAGE neighbor -> no FOREST
        val village = sMap.idToTile[3] ?: return
        whenever(mockMapController.getNeighboursGeometries(1, 1)).thenReturn(listOf(village))

        val sp = ScenarioParser(
            """{"clouds": [], "incidents": [
            {"id": 1, "type": "ANIMAL_ATTACK", "tick": 0, "location": 1, "radius": 1}
        ]}"""
        )
        val res = sp.createIncidents(mockMapController, listOf(mockFarmController), mockCloudController)
        kotlin.test.assertTrue(res.isFailure)
    }

    @Test
    fun incident_overlapping_cloud_creations_same_tick_should_fail() {
        val parser = MapParser(config = TestStrings2.EXAMPLE_STRING_MAP)
        val sMap = parser.createMap().getOrThrow()

        val mockMapController: MapController = Mockito.mock(MapController::class.java)
        val mockFarmController: FarmController = Mockito.mock(FarmController::class.java)
        val mockCloudController: CloudController = Mockito.mock(CloudController::class.java)
        whenever(mockMapController.simulationMap).thenReturn(sMap)

        // Force overlap on FIELD id=1 for both cloud creations at tick=0
        val field = sMap.idToTile[1] ?: return
        whenever(mockMapController.getNeighboursGeometries(1, 1)).thenReturn(listOf(field))
        whenever(mockMapController.getNeighboursGeometries(2, 1)).thenReturn(listOf(field))

        val sp = ScenarioParser(
            """{"clouds": [], "incidents": [
          {"id": 1, "type": "CLOUD_CREATION", "tick": 0, "location": 1, "radius": 1, "duration": 5, "amount": 10},
          {"id": 2, "type": "CLOUD_CREATION", "tick": 0, "location": 2, "radius": 1, "duration": 5, "amount": 10}
        ]}"""
        )
        val res = sp.createIncidents(mockMapController, listOf(mockFarmController), mockCloudController)
        kotlin.test.assertTrue(res.isFailure)
    }

    @Test
    fun incidents_duplicate_ids_and_negative_tick_should_fail() {
        val parser = MapParser(config = TestStrings2.EXAMPLE_STRING_MAP)
        val sMap = parser.createMap().getOrThrow()

        val mockMapController: MapController = Mockito.mock(MapController::class.java)
        val mockFarmController: FarmController = Mockito.mock(FarmController::class.java)
        val mockCloudController: CloudController = Mockito.mock(CloudController::class.java)
        whenever(mockMapController.simulationMap).thenReturn(sMap)

        val spDup = ScenarioParser(
            """{"clouds": [], "incidents": [
          {"id": 1, "type": "DROUGHT", "tick": 0, "location": 1, "radius": 1},
          {"id": 1, "type": "BEE_HAPPY", "tick": 1, "location": 2, "radius": 1, "effect": 5}
        ]}"""
        )
        val spNeg = ScenarioParser(
            """{"clouds": [], "incidents": [
          {"id": 2, "type": "DROUGHT", "tick": -1, "location": 1, "radius": 1}
        ]}"""
        )

        val resDup = spDup.createIncidents(mockMapController, listOf(mockFarmController), mockCloudController)
        val resNeg = spNeg.createIncidents(mockMapController, listOf(mockFarmController), mockCloudController)
        kotlin.test.assertTrue(resDup.isFailure)
        kotlin.test.assertTrue(resNeg.isFailure)
    }

    @Test
    fun incidents_malformed_json_should_fail() {
        val parser = MapParser(config = TestStrings2.EXAMPLE_STRING_MAP)
        val sMap = parser.createMap().getOrThrow()

        // createIncidents uses MapController; but malformed JSON will fail before validation
        val sp = ScenarioParser("""{"clouds": [], "incidents": [ {"id":1,"type":"DROUGHT"} """)
        val mockMapController: MapController = Mockito.mock(MapController::class.java)
        val mockFarmController: List<FarmController> = emptyList()
        val mockCloudController: CloudController = Mockito.mock(CloudController::class.java)
        whenever(mockMapController.simulationMap).thenReturn(sMap)

        val res = sp.createIncidents(mockMapController, mockFarmController, mockCloudController)
        kotlin.test.assertTrue(res.isFailure)
    }

    @Test
    fun cloud_overlap_same_tick_only_shared_village_should_pass() {
        // Map 1: FIELD 1, FIELD 2, VILLAGE 3
        val parser = MapParser(config = TestStrings2.EXAMPLE_STRING_MAP)
        val sMap = parser.createMap().getOrThrow()

        val mapC: MapController = Mockito.mock(MapController::class.java)
        val farmC: List<FarmController> = emptyList()
        val cloudC: CloudController = Mockito.mock(CloudController::class.java)

        whenever(mapC.simulationMap).thenReturn(sMap)
        // Delegate getTile to sMap for any id
        whenever(mapC.getTile(org.mockito.kotlin.any())).thenAnswer { inv ->
            val id = inv.getArgument<Int>(0)
            requireNotNull(sMap.idToTile[id]) { "Tile $id not found in test map" }
        }
        val field1 = sMap.idToTile[1] ?: return
        val field2 = sMap.idToTile[2] ?: return
        val village = sMap.idToTile[3] ?: return

        // Same tick (tick 0), neighborhoods share only VILLAGE (filtered out),
        // disjoint non-village tiles => no overlap for updateCloudAffectedTiles
        whenever(mapC.getNeighboursGeometries(1, 1)).thenReturn(listOf(field1, village))
        whenever(mapC.getNeighboursGeometries(2, 1)).thenReturn(listOf(field2, village))

        val sp = ScenarioParser(
            """
        {"clouds": [], "incidents": [
          {"id": 1, "type": "CLOUD_CREATION", "tick": 0, "location": 1, "radius": 1, "duration": 3, "amount": 10},
          {"id": 2, "type": "CLOUD_CREATION", "tick": 0, "location": 2, "radius": 1, "duration": 3, "amount": 10}
        ]}
            """.trimIndent()
        )
        val res = sp.createIncidents(mapC, farmC, cloudC)
        kotlin.test.assertTrue(res.isSuccess)
    }

    @Test
    fun cloud_overlap_different_ticks_same_tiles_should_pass() {
        val parser = MapParser(config = TestStrings2.EXAMPLE_STRING_MAP)
        val sMap = parser.createMap().getOrThrow()

        val mapC: MapController = Mockito.mock(MapController::class.java)
        val farmC: List<FarmController> = emptyList()
        val cloudC: CloudController = Mockito.mock(CloudController::class.java)

        whenever(mapC.simulationMap).thenReturn(sMap)
        whenever(mapC.getTile(org.mockito.kotlin.any())).thenAnswer { inv ->
            val id = inv.getArgument<Int>(0)
            requireNotNull(sMap.idToTile[id]) { "Tile $id not found in test map" }
        }
        val field1 = sMap.idToTile[1] ?: return

        // Overlap on FIELD 1 but incidents at ticks 0 and 1; per-tick set resets → allowed
        whenever(mapC.getNeighboursGeometries(1, 1)).thenReturn(listOf(field1))
        // Second call is for the second incident as well (same neighbors)
        whenever(mapC.getNeighboursGeometries(1, 1)).thenReturn(listOf(field1))

        val sp = ScenarioParser(
            """
        {"clouds": [], "incidents": [
          {"id": 1, "type": "CLOUD_CREATION", "tick": 0, "location": 1, "radius": 1, "duration": 3, "amount": 10},
          {"id": 2, "type": "CLOUD_CREATION", "tick": 1, "location": 1, "radius": 1, "duration": 3, "amount": 10}
        ]}
            """.trimIndent()
        )
        val res = sp.createIncidents(mapC, farmC, cloudC)
        kotlin.test.assertTrue(res.isSuccess)
    }

    @Test
    fun sowing_plans_valid_with_tileIDs_should_pass_new_farm() {
        // Map with FIELD 1 owned by farm 1 and supports WHEAT
        val parser = MapParser(config = TestStrings2.EXAMPLE_STRING_MAP)
        val sMap = parser.createMap().getOrThrow()

        val mapC: MapController = Mockito.mock(MapController::class.java)
        val cloudC: CloudController = Mockito.mock(CloudController::class.java)
        val farmC: FarmController = Mockito.mock(FarmController::class.java)

        whenever(mapC.simulationMap).thenReturn(sMap)
        // ScenarioParser checks possiblePlants via getTile, delegate to sMap
        whenever(mapC.getTile(org.mockito.kotlin.any())).thenAnswer { inv ->
            val id = inv.getArgument<Int>(0)
            requireNotNull(sMap.idToTile[id]) { "Tile $id not found in test map" }
        }
        val validPlan = listOf(
            SowingPlan(
                id = 10,
                startTick = 0,
                plant = FieldPlant.WHEAT,
                tileIDs = listOf(1), // FIELD 1, farm 1, supports WHEAT
                location = null,
                radius = null
            )
        )
        val perTestFarm = Farm(
            1,
            "Farm",
            listOf(1),
            listOf(1),
            listOf(1),
            emptyList(),
            listOf(
                Machine(
                    1,
                    "cool",
                    listOf("HARVESTING"), // simpleName of Action subclasses
                    listOf("CHERRY"), // simpleName of Plant subclasses
                    2,
                    0,
                    0,
                    null,
                    1,
                    0,
                )
            ),
            emptyList(),
            validPlan
        )
        whenever(farmC.farm).thenReturn(perTestFarm)

        // Benign incident just to run validation
        val field1 = sMap.idToTile[1] ?: return
        whenever(mapC.getNeighboursGeometries(1, 0)).thenReturn(listOf(field1))

        val sp = ScenarioParser(
            """
        {"clouds": [], "incidents": [
          {"id": 1, "type": "CLOUD_CREATION", "tick": 0, "location": 1, "radius": 0, "duration": 2, "amount": 5}
        ]}
            """.trimIndent()
        )
        val res = sp.createIncidents(mapC, listOf(farmC), cloudC)
        kotlin.test.assertTrue(res.isSuccess)
    }

    @Test
    fun sowing_plans_invalid_should_fail_new_farm() {
        val parser = MapParser(config = TestStrings2.EXAMPLE_STRING_MAP)
        val sMap = parser.createMap().getOrThrow()

        val mapC: MapController = Mockito.mock(MapController::class.java)
        val cloudC: CloudController = Mockito.mock(CloudController::class.java)
        val farmC: FarmController = Mockito.mock(FarmController::class.java)

        whenever(mapC.simulationMap).thenReturn(sMap)
        whenever(mapC.getTile(org.mockito.kotlin.any())).thenAnswer { inv ->
            val id = inv.getArgument<Int>(0)
            requireNotNull(sMap.idToTile[id]) { "Tile $id not found in test map" }
        }
        // Invalid: target VILLAGE (id=3), so not a FIELD
        val invalidPlan = listOf(
            SowingPlan(
                id = 11,
                startTick = 0,
                plant = de.unisaarland.cs.se.selab.plant.FieldPlant.WHEAT,
                tileIDs = listOf(3), // VILLAGE
                location = null,
                radius = null
            )
        )
        val perTestBadFarm = Farm(
            1,
            "Farm",
            listOf(1),
            listOf(1),
            listOf(1),
            emptyList(),
            listOf(
                Machine(
                    1,
                    "cool",
                    listOf("HARVESTING"), // simpleName of Action subclasses
                    listOf("CHERRY"), // simpleName of Plant subclasses
                    2,
                    0,
                    0,
                    null,
                    1,
                    0,
                )
            ),
            emptyList(),
            invalidPlan
        )
        whenever(farmC.farm).thenReturn(perTestBadFarm)

        val field1 = sMap.idToTile[1] ?: return
        whenever(mapC.getNeighboursGeometries(1, 0)).thenReturn(listOf(field1))

        val sp = ScenarioParser(
            """
        {"clouds": [], "incidents": [
          {"id": 1, "type": "CLOUD_CREATION", "tick": 0, "location": 1, "radius": 0, "duration": 2, "amount": 5}
        ]}
            """.trimIndent()
        )
        val res = sp.createIncidents(mapC, listOf(farmC), cloudC)
        kotlin.test.assertTrue(res.isFailure)
    }
}
