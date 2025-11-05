package general.mapTests

import de.unisaarland.cs.se.selab.map.MapController
import de.unisaarland.cs.se.selab.map.SimulationMap
import de.unisaarland.cs.se.selab.parser.MapParser
import de.unisaarland.cs.se.selab.utils.Tick
import de.unisaarland.cs.se.selab.utils.YearTick
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

object TestString {
    const val TILEMAP = """
    {
        "tiles": [
            {
                "id": 1,
                "coordinates": { "x": 0, "y": 2 },
                "category": "PLANTATION",
                "farm": 0,
                "capacity": 1000,
                "plant": "APPLE", 
                "airflow": false
            },
            {
                "id": 2,
                "coordinates": { "x": 2, "y": 2 },
                "category": "PLANTATION",
                "farm": 0,
                "capacity": 70,
                "plant": "CHERRY", 
                "airflow": false
            },
            {
                "id": 3,
                "coordinates": { "x": 4, "y": 2 },
                "category": "FIELD",
                "farm": 0,
                "capacity": 500,
                "possiblePlants": ["PUMPKIN", "WHEAT"],
                "airflow": false
            }
        ]
    }
    """
}
class MapControllerTests {

    lateinit var mapController: MapController

    @BeforeEach
    fun setup() {
        // set up the map
        val mp = MapParser(TestString.TILEMAP)
        val simulationMap: SimulationMap = mp.createMap().getOrElse { error("Unreachable") }

        mapController = MapController(simulationMap)
        mapController.tick() // run environment phase
    }

    @Test
    fun testPlantationReduceMoisture() {
        val t1 = mapController.getTile(1)
        val t2 = mapController.getTile(2)

        assertEquals(900, t1.soilMoisture) // reduced by 100
        assertEquals(0, t2.soilMoisture) // 70 - 100 correct to 0
    }

    @Test
    fun testFieldReduceMoisture() {
        val t3 = mapController.getTile(3)
        if (t3.plant != null) { // has plant, reduced by 100
            assertEquals(400, t3.soilMoisture)
        } else { // no plant, reduced by 70
            assertEquals(430, t3.soilMoisture)
        }
    }

    // test checkThresholdField and checkThresholdPlantation

    @Test
    fun testSunlightSet() {
        for (tile in mapController.simulationMap.idToTile.values) {
            val sunLevel = tile.amountSunlight
            when (Tick.yTick) {
                YearTick.JANUARY_1, YearTick.JANUARY_2 -> assertEquals(sunLevel, 98)
                YearTick.FEBRUARY_1, YearTick.FEBRUARY_2 -> assertEquals(sunLevel, 112)
                YearTick.MARCH_1, YearTick.MARCH_2 -> assertEquals(sunLevel, 126)
                YearTick.APRIL_1, YearTick.APRIL_2 -> assertEquals(sunLevel, 140)
                YearTick.MAY_1, YearTick.MAY_2 -> assertEquals(sunLevel, 168)
                YearTick.JUNE_1, YearTick.JUNE_2 -> assertEquals(sunLevel, 168)
                YearTick.JULY_1, YearTick.JULY_2 -> assertEquals(sunLevel, 168)
                YearTick.AUGUST_1, YearTick.AUGUST_2 -> assertEquals(sunLevel, 154)
                YearTick.SEPTEMBER_1, YearTick.SEPTEMBER_2 -> assertEquals(sunLevel, 126)
                YearTick.OCTOBER_1, YearTick.OCTOBER_2 -> assertEquals(sunLevel, 112)
                YearTick.NOVEMBER_1, YearTick.NOVEMBER_2 -> assertEquals(sunLevel, 98)
                YearTick.DECEMBER_1, YearTick.DECEMBER_2 -> assertEquals(sunLevel, 84)
            }
        }
    }

    @Test
    fun getCorrectTileWithId() {
        assertEquals(1, mapController.getTile(1).id)
        assertEquals(2, mapController.getTile(2).id)
        assertEquals(3, mapController.getTile(3).id)
    }

    @Test
    fun getTileUnknownId() {
        val exception = assertThrows<IllegalStateException> {
            mapController.getTile(10)
        }
        assertEquals("A Tile with the given id is not found", exception.message)
    }
}
