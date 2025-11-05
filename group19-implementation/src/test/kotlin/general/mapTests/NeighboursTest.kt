package general.mapTests

import de.unisaarland.cs.se.selab.map.MapController
import de.unisaarland.cs.se.selab.map.SimulationMap
import de.unisaarland.cs.se.selab.parser.MapParser
import de.unisaarland.cs.se.selab.utils.Direction.EAST
import de.unisaarland.cs.se.selab.utils.Direction.NORTH
import de.unisaarland.cs.se.selab.utils.Direction.NORTHEAST
import de.unisaarland.cs.se.selab.utils.Direction.NORTHWEST
import de.unisaarland.cs.se.selab.utils.Direction.SOUTH
import de.unisaarland.cs.se.selab.utils.Direction.SOUTHEAST
import de.unisaarland.cs.se.selab.utils.Direction.SOUTHWEST
import de.unisaarland.cs.se.selab.utils.Direction.WEST
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

const val BIG_MAP = """{
    "tiles": [
        {
            "id": 0,
            "coordinates": {
                "x": 0,
                "y": 0
            },
            "category": "ROAD",
            "airflow": false
        },
        {
            "id": 1,
            "coordinates": {
                "x": 0,
                "y": 2
            },
            "category": "ROAD",
            "airflow": false
        },
        {
            "id": 2,
            "coordinates": {
                "x": 0,
                "y": 4
            },
            "category": "ROAD",
            "airflow": false
        },
        {
            "id": 3,
            "coordinates": {
                "x": 0,
                "y": 6
            },
            "category": "ROAD",
            "airflow": false
        },
        {
            "id": 4,
            "coordinates": {
                "x": 0,
                "y": 8
            },
            "category": "ROAD",
            "airflow": false
        },
        {
            "id": 5,
            "coordinates": {
                "x": 0,
                "y": 10
            },
            "category": "ROAD",
            "airflow": false
        },
        {
            "id": 6,
            "coordinates": {
                "x": 0,
                "y": 12
            },
            "category": "ROAD",
            "airflow": false
        },
        {
            "id": 7,
            "coordinates": {
                "x": 0,
                "y": 14
            },
            "category": "ROAD",
            "airflow": false
        },
        {
            "id": 8,
            "coordinates": {
                "x": 1,
                "y": 1
            },
            "category": "ROAD",
            "airflow": false
        },
        {
            "id": 9,
            "coordinates": {
                "x": 1,
                "y": 3
            },
            "category": "ROAD",
            "airflow": false
        },
        {
            "id": 10,
            "coordinates": {
                "x": 1,
                "y": 5
            },
            "category": "ROAD",
            "airflow": false
        },
        {
            "id": 11,
            "coordinates": {
                "x": 1,
                "y": 7
            },
            "category": "ROAD",
            "airflow": false
        },
        {
            "id": 12,
            "coordinates": {
                "x": 1,
                "y": 9
            },
            "category": "ROAD",
            "airflow": false
        },
        {
            "id": 13,
            "coordinates": {
                "x": 1,
                "y": 11
            },
            "category": "ROAD",
            "airflow": false
        },
        {
            "id": 14,
            "coordinates": {
                "x": 1,
                "y": 13
            },
            "category": "ROAD",
            "airflow": false
        },
        {
            "id": 15,
            "coordinates": {
                "x": 2,
                "y": 0
            },
            "category": "ROAD",
            "airflow": false
        },
        {
            "id": 16,
            "coordinates": {
                "x": 2,
                "y": 2
            },
            "category": "ROAD",
            "airflow": false
        },
        {
            "id": 17,
            "coordinates": {
                "x": 2,
                "y": 4
            },
            "category": "ROAD",
            "airflow": false
        },
        {
            "id": 18,
            "coordinates": {
                "x": 2,
                "y": 6
            },
            "category": "ROAD",
            "airflow": false
        },
        {
            "id": 19,
            "coordinates": {
                "x": 2,
                "y": 8
            },
            "category": "ROAD",
            "airflow": false
        },
        {
            "id": 20,
            "coordinates": {
                "x": 2,
                "y": 10
            },
            "category": "ROAD",
            "airflow": false
        },
        {
            "id": 21,
            "coordinates": {
                "x": 2,
                "y": 12
            },
            "category": "ROAD",
            "airflow": false
        },
        {
            "id": 22,
            "coordinates": {
                "x": 2,
                "y": 14
            },
            "category": "ROAD",
            "airflow": false
        },
        {
            "id": 23,
            "coordinates": {
                "x": 3,
                "y": 1
            },
            "category": "ROAD",
            "airflow": false
        },
        {
            "id": 24,
            "coordinates": {
                "x": 3,
                "y": 3
            },
            "category": "ROAD",
            "airflow": false
        },
        {
            "id": 25,
            "coordinates": {
                "x": 3,
                "y": 5
            },
            "category": "ROAD",
            "airflow": false
        },
        {
            "id": 26,
            "coordinates": {
                "x": 3,
                "y": 9
            },
            "category": "ROAD",
            "airflow": false
        },
        {
            "id": 27,
            "coordinates": {
                "x": 3,
                "y": 11
            },
            "category": "ROAD",
            "airflow": false
        },
        {
            "id": 28,
            "coordinates": {
                "x": 3,
                "y": 13
            },
            "category": "ROAD",
            "airflow": false
        },
        {
            "id": 29,
            "coordinates": {
                "x": 4,
                "y": 0
            },
            "category": "ROAD",
            "airflow": false
        },
        {
            "id": 30,
            "coordinates": {
                "x": 4,
                "y": 2
            },
            "category": "ROAD",
            "airflow": false
        },
        {
            "id": 31,
            "coordinates": {
                "x": 4,
                "y": 4
            },
            "category": "ROAD",
            "airflow": false
        },
        {
            "id": 32,
            "coordinates": {
                "x": 4,
                "y": 10
            },
            "category": "ROAD",
            "airflow": false
        },
        {
            "id": 33,
            "coordinates": {
                "x": 4,
                "y": 12
            },
            "category": "ROAD",
            "airflow": false
        },
        {
            "id": 34,
            "coordinates": {
                "x": 4,
                "y": 14
            },
            "category": "ROAD",
            "airflow": false
        },
        {
            "id": 35,
            "coordinates": {
                "x": 5,
                "y": 1
            },
            "category": "ROAD",
            "airflow": false
        },
        {
            "id": 36,
            "coordinates": {
                "x": 5,
                "y": 3
            },
            "category": "ROAD",
            "airflow": false
        },
        {
            "id": 37,
            "coordinates": {
                "x": 5,
                "y": 11
            },
            "category": "ROAD",
            "airflow": false
        },
        {
            "id": 38,
            "coordinates": {
                "x": 5,
                "y": 13
            },
            "category": "ROAD",
            "airflow": false
        },
        {
            "id": 39,
            "coordinates": {
                "x": 6,
                "y": 0
            },
            "category": "ROAD",
            "airflow": false
        },
        {
            "id": 40,
            "coordinates": {
                "x": 6,
                "y": 2
            },
            "category": "ROAD",
            "airflow": false
        },
        {
            "id": 41,
            "coordinates": {
                "x": 6,
                "y": 12
            },
            "category": "ROAD",
            "airflow": false
        },
        {
            "id": 42,
            "coordinates": {
                "x": 6,
                "y": 14
            },
            "category": "ROAD",
            "airflow": false
        },
        {
            "id": 43,
            "coordinates": {
                "x": 7,
                "y": 1
            },
            "category": "ROAD",
            "airflow": false
        },
        {
            "id": 44,
            "coordinates": {
                "x": 7,
                "y": 7
            },
            "category": "ROAD",
            "airflow": false
        },
        {
            "id": 45,
            "coordinates": {
                "x": 7,
                "y": 13
            },
            "category": "ROAD",
            "airflow": false
        },
        {
            "id": 46,
            "coordinates": {
                "x": 8,
                "y": 0
            },
            "category": "ROAD",
            "airflow": false
        },
        {
            "id": 47,
            "coordinates": {
                "x": 8,
                "y": 2
            },
            "category": "ROAD",
            "airflow": false
        },
        {
            "id": 48,
            "coordinates": {
                "x": 8,
                "y": 12
            },
            "category": "ROAD",
            "airflow": false
        },
        {
            "id": 49,
            "coordinates": {
                "x": 8,
                "y": 14
            },
            "category": "ROAD",
            "airflow": false
        },
        {
            "id": 50,
            "coordinates": {
                "x": 9,
                "y": 1
            },
            "category": "ROAD",
            "airflow": false
        },
        {
            "id": 51,
            "coordinates": {
                "x": 9,
                "y": 3
            },
            "category": "ROAD",
            "airflow": false
        },
        {
            "id": 52,
            "coordinates": {
                "x": 9,
                "y": 11
            },
            "category": "ROAD",
            "airflow": false
        },
        {
            "id": 53,
            "coordinates": {
                "x": 9,
                "y": 13
            },
            "category": "ROAD",
            "airflow": false
        },
        {
            "id": 54,
            "coordinates": {
                "x": 10,
                "y": 0
            },
            "category": "ROAD",
            "airflow": false
        },
        {
            "id": 55,
            "coordinates": {
                "x": 10,
                "y": 2
            },
            "category": "ROAD",
            "airflow": false
        },
        {
            "id": 56,
            "coordinates": {
                "x": 10,
                "y": 4
            },
            "category": "ROAD",
            "airflow": false
        },
        {
            "id": 57,
            "coordinates": {
                "x": 10,
                "y": 10
            },
            "category": "ROAD",
            "airflow": false
        },
        {
            "id": 58,
            "coordinates": {
                "x": 10,
                "y": 12
            },
            "category": "ROAD",
            "airflow": false
        },
        {
            "id": 59,
            "coordinates": {
                "x": 10,
                "y": 14
            },
            "category": "ROAD",
            "airflow": false
        },
        {
            "id": 60,
            "coordinates": {
                "x": 11,
                "y": 1
            },
            "category": "ROAD",
            "airflow": false
        },
        {
            "id": 61,
            "coordinates": {
                "x": 11,
                "y": 3
            },
            "category": "ROAD",
            "airflow": false
        },
        {
            "id": 62,
            "coordinates": {
                "x": 11,
                "y": 5
            },
            "category": "ROAD",
            "airflow": false
        },
        {
            "id": 63,
            "coordinates": {
                "x": 11,
                "y": 9
            },
            "category": "ROAD",
            "airflow": false
        },
        {
            "id": 64,
            "coordinates": {
                "x": 11,
                "y": 11
            },
            "category": "ROAD",
            "airflow": false
        },
        {
            "id": 65,
            "coordinates": {
                "x": 11,
                "y": 13
            },
            "category": "ROAD",
            "airflow": false
        },
        {
            "id": 66,
            "coordinates": {
                "x": 12,
                "y": 0
            },
            "category": "ROAD",
            "airflow": false
        },
        {
            "id": 67,
            "coordinates": {
                "x": 12,
                "y": 2
            },
            "category": "ROAD",
            "airflow": false
        },
        {
            "id": 68,
            "coordinates": {
                "x": 12,
                "y": 4
            },
            "category": "ROAD",
            "airflow": false
        },
        {
            "id": 69,
            "coordinates": {
                "x": 12,
                "y": 6
            },
            "category": "ROAD",
            "airflow": false
        },
        {
            "id": 70,
            "coordinates": {
                "x": 12,
                "y": 8
            },
            "category": "ROAD",
            "airflow": false
        },
        {
            "id": 71,
            "coordinates": {
                "x": 12,
                "y": 10
            },
            "category": "ROAD",
            "airflow": false
        },
        {
            "id": 72,
            "coordinates": {
                "x": 12,
                "y": 12
            },
            "category": "ROAD",
            "airflow": false
        },
        {
            "id": 73,
            "coordinates": {
                "x": 12,
                "y": 14
            },
            "category": "ROAD",
            "airflow": false
        },
        {
            "id": 74,
            "coordinates": {
                "x": 13,
                "y": 1
            },
            "category": "ROAD",
            "airflow": false
        },
        {
            "id": 75,
            "coordinates": {
                "x": 13,
                "y": 3
            },
            "category": "ROAD",
            "airflow": false
        },
        {
            "id": 76,
            "coordinates": {
                "x": 13,
                "y": 5
            },
            "category": "ROAD",
            "airflow": false
        },
        {
            "id": 77,
            "coordinates": {
                "x": 13,
                "y": 7
            },
            "category": "ROAD",
            "airflow": false
        },
        {
            "id": 78,
            "coordinates": {
                "x": 13,
                "y": 9
            },
            "category": "ROAD",
            "airflow": false
        },
        {
            "id": 79,
            "coordinates": {
                "x": 13,
                "y": 11
            },
            "category": "ROAD",
            "airflow": false
        },
        {
            "id": 80,
            "coordinates": {
                "x": 13,
                "y": 13
            },
            "category": "ROAD",
            "airflow": false
        },
        {
            "id": 81,
            "coordinates": {
                "x": 14,
                "y": 0
            },
            "category": "ROAD",
            "airflow": false
        },
        {
            "id": 82,
            "coordinates": {
                "x": 14,
                "y": 2
            },
            "category": "ROAD",
            "airflow": false
        },
        {
            "id": 83,
            "coordinates": {
                "x": 14,
                "y": 4
            },
            "category": "ROAD",
            "airflow": false
        },
        {
            "id": 84,
            "coordinates": {
                "x": 14,
                "y": 6
            },
            "category": "ROAD",
            "airflow": false
        },
        {
            "id": 85,
            "coordinates": {
                "x": 14,
                "y": 8
            },
            "category": "ROAD",
            "airflow": false
        },
        {
            "id": 86,
            "coordinates": {
                "x": 14,
                "y": 10
            },
            "category": "ROAD",
            "airflow": false
        },
        {
            "id": 87,
            "coordinates": {
                "x": 14,
                "y": 12
            },
            "category": "ROAD",
            "airflow": false
        },
        {
            "id": 88,
            "coordinates": {
                "x": 14,
                "y": 14
            },
            "category": "ROAD",
            "airflow": false
        }
    ]
}"""

object MapWithNeighbours {
    const val MAP = """
    {
        "tiles": [
            {
                "id": 1,
                "coordinates": { "x": 0, "y": 2 },
                "category": "PLANTATION",
                "farm": 0,
                "capacity": 1000,
                "plant": "APPLE", 
                "airflow": true,
                "direction": "270"
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
                "coordinates": { "x": 0, "y": 4 },
                "category": "VILLAGE"
            },
            {
                "id": 4,
                "coordinates": { "x": 4, "y": 2 },
                "category": "FIELD",
                "farm": 0,
                "capacity": 500,
                "possiblePlants": ["PUMPKIN", "WHEAT"],
                "airflow": true,
                "direction": "180"
            },
            {
                "id": 5,
                "coordinates": { "x": 4, "y": 4 },
                "category": "FOREST",
                "airflow": true,
                "direction": "315"
            },
            {
                "id": 6,
                "coordinates": { "x": 3, "y": 3 },
                "category": "FARMSTEAD",
                "farm": 0,
                "shed": true,
                "airflow": false
            },
            {
                "id": 7,
                "coordinates": { "x": 2, "y": 6 },
                "category": "ROAD",
                "airflow": false
            },
            {
                "id": 8,
                "coordinates": { "x": -1, "y": 3 },
                "category": "ROAD",
                "airflow": false
            },
            {
                "id": 15,
                "coordinates": { "x": 30, "y": 30 },
                "category": "ROAD",
                "airflow": false
            }
        ]
    }
    """
}

class NeighboursTest {

    lateinit var mapController: MapController

    @BeforeEach
    fun setup() {
        // set up the map
        val mp = MapParser(MapWithNeighbours.MAP)
        val simulationMap: SimulationMap = mp.createMap().getOrElse { error("Unreachable") }

        mapController = MapController(simulationMap)
        mapController.tick() // run environment phase
    }

    @Test
    fun `getNeighbourTile unknown id`() {
        val exception = assertThrows<IllegalStateException> {
            mapController.getNeighbourTile(100)
        }
        assertEquals("A Tile with the given id is not found", exception.message)
    }

    @Test
    fun `getNeighbourTile success case`() {
        val neigh4 = mapController.getNeighbourTile(4)
        val neigh5 = mapController.getNeighbourTile(5)
        assertNotNull(neigh4)
        assertNotNull(neigh5)
        assertEquals(mapController.getTile(5), neigh4)
        assertEquals(mapController.getTile(6), neigh5)
    }

    @Test
    fun `getNeighbourTile VILLAGE`() { // tile id = 3, VILLAGE tile
        val neigh = mapController.getNeighbourTile(3)
        assertNull(neigh)
    }

    @Test
    fun `getNeighbourTile airflow false`() {
        val neigh = mapController.getNeighbourTile(2)
        assertNull(neigh)
    }

    @Test
    fun `getNeighbourTile edge tile`() {
        val neigh = mapController.getNeighbourTile(1)
        assertNull(neigh)
    }

    @Test
    fun `getNeighbourTiles unknown input tileId`() {
        val exception = assertThrows<IllegalStateException> {
            mapController.getNeighbourTiles(20, 3)
        }
        assertEquals("A Tile with the given id is not found", exception.message)
    }

    @Test
    fun `getNeighbourTiles radius less than one`() {
        val neighbours2 = mapController.getNeighbourTiles(2, 0)
        assertEquals(0, neighbours2.size)

        val neighbours6 = mapController.getNeighbourTiles(6, 0)
        assertEquals(0, neighbours6.size)
    }

    @Test
    fun `getNeighbourTiles radius one`() {
        val neighbours = mapController.getNeighbourTiles(4, 1)
        val expectedNeigh = setOf(2, 5, 6)
        val actualNeigh = neighbours.map { it.id }.toSet()
        assertEquals(3, neighbours.size)
        assertEquals(expectedNeigh, actualNeigh)
    }

    @Test
    fun `getNeighbourTiles radius two`() {
        val neighbours = mapController.getNeighbourTiles(6, 2)
        val expectedNeigh = setOf(1, 2, 4, 5)
        val actualNeigh = neighbours.map { it.id }.toSet()
        assertEquals(4, neighbours.size)
        assertEquals(expectedNeigh, actualNeigh)
    }

    @Test
    fun `getNeighbourTiles isolated tile`() {
        val neighbours = mapController.getNeighbourTiles(15, 1)
        assertEquals(0, neighbours.size)
    }

    @Test
    fun `getNeighboursGeometries radius 0 square tile`() {
        val neighbours = mapController.getNeighboursGeometries(6, 0)
        val actualNeigh = neighbours.map { it.id }.toSet()
        // assertEquals(1, neighbours.size)
        // ssertEquals(setOf(6), actualNeigh)
    }

    @Test
    fun `getNeighboursGeometries radius 1 square tile`() {
        val neighbours = mapController.getNeighboursGeometries(6, 1)
        val expectedNeigh = setOf(2, 4, 5, 6)
        val actualNeigh = neighbours.map { it.id }.toSet()
        assertEquals(4, neighbours.size)
        assertEquals(expectedNeigh, actualNeigh)
    }

    @Test
    fun `getNeighboursGeometries radius 2 square tile`() {
        val neighbours = mapController.getNeighboursGeometries(6, 2)
        val expectedNeigh = setOf(1, 2, 3, 4, 5, 6, 7)
        val actualNeigh = neighbours.map { it.id }.toSet()
        assertEquals(7, neighbours.size)
        assertEquals(expectedNeigh, actualNeigh)
    }

    @Test
    fun `getNeighboursGeometries radius 1 octagonal tile`() {
        val neighbours = mapController.getNeighboursGeometries(5, 2)
        val expectedNeigh = setOf(2, 3, 4, 5, 6, 7)
        val actualNeigh = neighbours.map { it.id }.toSet()
        assertEquals(6, neighbours.size)
    }

    @Test
    fun `getNeighboursGeometries radius 3`() {
        val mp = MapParser(BIG_MAP)
        val simulationMap: SimulationMap = mp.createMap().getOrElse { error("Unreachable") }
        val mapController = MapController(simulationMap)
        val neigh = mapController.getNeighboursGeometries(44, 3).map { it.id }.toSet()
        val expectedNeigh = setOf(44, 18, 19, 26, 32, 37, 41, 48, 52, 57, 63, 70, 69, 62, 56, 51, 47, 40, 36, 31, 25)
        assertEquals(neigh.size, expectedNeigh.size)
        assert(expectedNeigh.all { neigh.contains(it) }) { "Neighbours do not match" }
    }

    @Test
    fun `getNeighboursGeometries isolated`() {
        val mp = MapParser(BIG_MAP)
        val simulationMap: SimulationMap = mp.createMap().getOrElse { error("Unreachable") }
        val mapController = MapController(simulationMap)
        val neigh = mapController.getNeighboursGeometries(44, 2).map { it.id }
        assert(neigh.size == 1 && neigh.first() == 44) { "Expected [44], got $neigh" }
    }

    @Test
    fun `test octagonal tile neighbours`() {
        val mp = MapParser(BIG_MAP)
        val simulationMap: SimulationMap = mp.createMap().getOrElse { error("Unreachable") }
        val mapController = MapController(simulationMap)
        val neigh = mapController.getTile(16).neighbours
        val expectedNeigh = listOf(
            NORTH to 15,
            NORTHEAST to 23,
            EAST to 30,
            SOUTHEAST to 24,
            SOUTH to 17,
            SOUTHWEST to 9,
            WEST to 1,
            NORTHWEST to 8,
        )
        for ((dir, id) in expectedNeigh) {
            assertEquals(id, neigh[dir])
        }
    }

    @Test
    fun `test square tile neighbours`() {
        val mp = MapParser(BIG_MAP)
        val simulationMap: SimulationMap = mp.createMap().getOrElse { error("Unreachable") }
        val mapController = MapController(simulationMap)
        val neigh = mapController.getTile(8).neighbours
        val expectedNeigh = listOf(
            NORTHEAST to 15,
            SOUTHEAST to 16,
            SOUTHWEST to 1,
            NORTHWEST to 0,
        )
        for ((dir, id) in expectedNeigh) {
            assertEquals(id, neigh[dir])
        }
    }
}
