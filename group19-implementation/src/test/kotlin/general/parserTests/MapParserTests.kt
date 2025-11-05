package general.parserTests
import de.unisaarland.cs.se.selab.parser.MapParser
import org.junit.jupiter.api.Test
import kotlin.test.assertNotNull

object TestStrings {
    const val EXAMPLE_STRING = """
  {
  "tiles": [
    {
      "id": 0,
      "coordinates": {
        "x": 1,
        "y": 1
      },
      "category": "FARMSTEAD",
      "farm": 0,
      "shed": true,
      "airflow": false
    },
    {
      "id": 1,
      "coordinates": {
        "x": 0,
        "y": 2
      },
      "category": "PLANTATION",
      "farm": 0,
      "capacity": 8000,
      "plant": "APPLE",
      "airflow": false
    },
    {
      "id": 2,
      "coordinates": {
        "x": 2,
        "y": 2
      },
      "category": "FIELD",
      "farm": 0,
      "capacity": 10000,
      "possiblePlants": [
        "PUMPKIN",
        "WHEAT"
      ],
      "airflow": false
    }
  ]
}
    """
    const val S2 = """{
        "tiles": [
        {
            "id": 1, 
            "coordinates": {
        "x": 0,
        "y": 0
      }, 
            "category": "FIELD", 
            "farm": 0, 
            "capacity": 1000, 
            "plant": 
            "POTATO", 
            "possiblePlants": ["POTATO"], 
            "airflow": false 
        },
        { 
            "id": 1, 
            "coordinates": {
        "x": 2,
        "y": 0
      }, 
            "category": "FIELD", 
            "farm": 0, 
            "capacity": 1000, 
            "plant": "WHEAT", 
            "possiblePlants": ["WHEAT"], 
            "airflow": false }
        ]
    }"""
    const val S3 = """{
        "tiles": [
        {
            "id": 3,
            "coordinates": {
        "x": 1,
        "y": 2
      },
            "category": "ROAD",
            "airflow": false
        }
        ],
    }"""
    const val S4 = """{
        "tiles": [
            {
              "id": 6,
              "coordinates": {
        "x": 2,
        "y": 0
      },
              "category": "FIELD",
              "farm": 0,
              "plant": "WHEAT",
              "possiblePlants": ["WHEAT"],
              "airflow": false
            }
        ],
    }"""
    const val S5 = """{
        "tiles": [
            {
                "id": 8,
                "coordinates": {
        "x": 1,
        "y": 1
      },
                "category": "FARMSTEAD",
                "farm": 0,
                "shed": false,
                "airflow": true,
                "direction": 90
            }
        ],
        }"""
    const val S6 = """{
        "tiles": [
            {
                "id": 11,
                "coordinates": {
        "x": 1,
        "y": 1
      },
                "category": "ROAD",
                "airflow": false,
                "direction": 45
            }
        ],
    }"""
}

class ExampleTest {
    @Test
    fun test() {
        assert(true)
    }
}

class ParserTest {
    @Test
    fun tilesWithSameId() {
        val parser = MapParser(config = TestStrings.S2)
        val smapRes = parser.createMap()
        assert(value = smapRes.isFailure, lazyMessage = { "Two tiles are with the same id" })
    }

    @Test
    fun tilesOnWrongCoordinate() {
        val parser = MapParser(config = TestStrings.S3)
        val smapRes = parser.createMap()
        assert(value = smapRes.isFailure, lazyMessage = { "Tile with an odd even coordinate pair" })
    }

    @Test
    fun fieldTileWithMissingProperty() {
        val parser = MapParser(config = TestStrings.S4)
        val smapRes = parser.createMap()
        assert(value = smapRes.isFailure, lazyMessage = { "Field is missing capacity property" })
    }

    @Test
    fun farmsteadOnEvenCoordinates() {
        val parser = MapParser(config = TestStrings.S5)
        val smapRes = parser.createMap()
        assert(value = smapRes.isFailure, lazyMessage = { "invalid direction for a square tile" })
    }

    @Test
    fun fieldMissingCapacity() {
        val parser = MapParser(config = TestStrings.S6)
        val smapRes = parser.createMap()
        assert(value = smapRes.isFailure, lazyMessage = { "airflow is false but there is a direction" })
    }

    @Test
    fun correctfile() {
        val parser = MapParser(config = TestStrings.EXAMPLE_STRING)
        val smapRes = parser.createMap()
        assert(value = smapRes.isSuccess, lazyMessage = { "should parse" })
        val smap = smapRes.getOrThrow()
        // Tile 0: FARMSTEAD
        val t0 = smap.idToTile[0]
        assertNotNull(t0)
        kotlin.test.assertEquals(0, t0.id)
        kotlin.test.assertEquals(1, t0.coordinate.x)
        kotlin.test.assertEquals(1, t0.coordinate.y)
        kotlin.test.assertEquals(de.unisaarland.cs.se.selab.map.TileType.FARMSTEAD, t0.category)
        kotlin.test.assertEquals(0, t0.farm)
        kotlin.test.assertEquals(true, t0.shed)
        kotlin.test.assertEquals(false, t0.airflow)
        kotlin.test.assertEquals(null, t0.direction)
        kotlin.test.assertEquals(null, t0.maxCapacity)
        kotlin.test.assertEquals(null, t0.plant)
        kotlin.test.assertEquals(null, t0.fallowPeriod)
        // Tile 1: PLANTATION (APPLE), capacity 8000
        val t1 = smap.idToTile[1]
        assertNotNull(t1)
        kotlin.test.assertEquals(1, t1.id)
        kotlin.test.assertEquals(0, t1.coordinate.x)
        kotlin.test.assertEquals(2, t1.coordinate.y)
        kotlin.test.assertEquals(de.unisaarland.cs.se.selab.map.TileType.PLANTATION, t1.category)
        kotlin.test.assertEquals(0, t1.farm)
        kotlin.test.assertEquals(false, t1.airflow)
        kotlin.test.assertEquals(null, t1.direction)
        kotlin.test.assertEquals(8000, t1.maxCapacity)
        kotlin.test.assertEquals(null, t1.possiblePlants)
        kotlin.test.assertEquals(false, t1.shed)
    }
}
