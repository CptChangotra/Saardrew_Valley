package de.unisaarland.cs.se.selab.cloud

/**
 * Cloud
 * @param id Unique number (ID) for a cloud.
 * @param duration Number of ticks a cloud can exist.
 * @param location tileId of the tile the cloud is on.
 * @param amount Amount of water present in the cloud.
 * @param distance How many tiles the cloud can still move in one tick.
 */

data class Cloud(
    internal var id: Int,
    internal var duration: Int,
    internal var location: Int,
    internal var amount: Int,
    internal var distance: Int
) {
    /**
     * Companion object containing constants used by the CloudController.
     */
    companion object {
        /**
         * The minimum amount of moisture needed in a cloud for it to rain.
         */
        const val RAINABLE = 5000

        /**
         * The maximum amount of tiles a cloud can move in a tick.
         */
        const val MAX_MOVES = 10

        /**
         * A cloud has infinite duration if its duration is set to -1
         */
        const val INFINITE_CLOUD_DURATION = -1

        /**
         * The number of hours a tile's sunlight is reduced if a cloud is on it present at the end of tick.
         */
        const val REDUCE_SUNLIGHT_END = 50

        /**
         * The number of hours a tile's sunlight is reduced if a cloud transverses over it
         */
        const val REDUCE_SUNLIGHT_MOVE = 3

        /**
         * A check value if a cloud has travelled 10 times and commence dissipation.
         */
        const val NO_MORE_MOVES_LEFT = 0
    }
}
