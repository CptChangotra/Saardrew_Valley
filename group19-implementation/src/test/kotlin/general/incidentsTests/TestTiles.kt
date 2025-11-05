package general.incidentsTests

import de.unisaarland.cs.se.selab.map.Coordinate
import de.unisaarland.cs.se.selab.map.Tile
import de.unisaarland.cs.se.selab.map.TileType

/**
 * Test helper that provides a small set of concrete Tile instances.
 * The tiles are created in a single expression (one list) but formatted
 * across multiple lines to satisfy the project's style checks.
 */
object TestTiles {
    val tiles = listOf(
        Tile(
            2,
            Coordinate(0, 0),
            TileType.FIELD,
            1,
            true,
            0,
            1000,
            null,
            null,
            0,
            0,
            false,
            null,
            500,
            120,
            mapOf(0 to 3),
            mutableListOf()
        ),
        Tile(
            3,
            Coordinate(1, 0),
            TileType.PLANTATION,
            1,
            true,
            0,
            800,
            null,
            null,
            0,
            0,
            false,
            null,
            300,
            110,
            mapOf(0 to 2),
            mutableListOf()
        ),
        Tile(
            4,
            Coordinate(0, 1),
            TileType.MEADOW,
            null,
            true,
            0,
            null,
            null,
            null,
            0,
            0,
            false,
            null,
            0,
            130,
            mapOf(0 to 2),
            mutableListOf()
        ),
        Tile(
            5,
            Coordinate(1, 1),
            TileType.PLANTATION,
            null,
            true,
            0,
            null,
            null,
            null,
            0,
            0,
            false,
            null,
            0,
            140,
            mapOf(0 to 3),
            mutableListOf()
        )
    )
}
