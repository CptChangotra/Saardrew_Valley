package de.unisaarland.cs.se.selab.cloud

import de.unisaarland.cs.se.selab.map.MapController
import de.unisaarland.cs.se.selab.map.Tile
import de.unisaarland.cs.se.selab.map.TileType
import de.unisaarland.cs.se.selab.utils.Logger

/**
 * Cloud Controller responsible for controlling and manipulating the clouds.
 * @property clouds Mutable List of Clouds that take care of the clouds being handled by the Cloud Controller.
 * @property cloudMap Mutable Map Maps the tileId to its respective Cloud on what the cloud is on.
 * @property mapController Used to fetch tileId and other map related information.
 * @property maxId ID of the most recently created cloud. Used in increments to assign cloud ID's
 * @property isCloudPhaseOver Boolean check to toggle between cloud phase and incident phase.
 */

class CloudController(
    val clouds: MutableList<Cloud>,
    val cloudMap: MutableMap<Int, Cloud>,
    val mapController: MapController
) {
    var maxId = clouds.maxOfOrNull { it.id } ?: -1
    var isCloudPhaseOver = false

    // To avoid concurrency errors, we use a Queue.
    val processingClouds = ArrayDeque<Cloud>()

    /**
     * Facade trigger to update all the clouds.
     */
    fun updateOnTick() {
        isCloudPhaseOver = false

        // To make sure that the n+1 tick does not process any clouds from n tick.
        processingClouds.clear()
        // Add all the SORTED original clouds to the processing queue.
        clouds.sortedBy { it.id }.forEach { processingClouds.addLast(it) }

        // Process clouds until queue is empty
        while (processingClouds.isNotEmpty()) {
            val cloud = processingClouds.removeFirst()
            // Verify cloud still exists (not dissipated by another cloud's action)
            if (cloud in clouds) {
                // The trigger the makes all the clouds rain -> move -> merge? -> rain
                processSingleCloud(cloud)
            }
        }

        /**
         * Final cloud fix: Instead of reducing the -50 sunlight during the processing,
         * that lead to multiple reductions, we just do it at the end of the tick,
         * when all the clouds have been processed, to make sure the
         * reduction only happens once.
         */
        for (cloud in clouds) {
            reduceEndingSunlight(cloud)
        }

        // We have process all the original clouds and the newly merged clouds.
        // Now if Cloud Creation incident makes any new cloud in tick n, it is to be processed in tick n+1.
        isCloudPhaseOver = true
        logEndPositionsAndResetDistance()
    }

    /**
     * Helper function to reduce the sunlight once we are done processing all the clouds.
     */
    private fun reduceEndingSunlight(cloud: Cloud) {
        val endingTile = mapController.getTile(cloud.location)
        if (endingTile.category == TileType.FIELD || endingTile.category == TileType.PLANTATION) {
            endingTile.amountSunlight = maxOf(0, endingTile.amountSunlight - Cloud.REDUCE_SUNLIGHT_END)
        }
    }

    /**
     * Log end positions and reset cloud distances efficiently.
     */
    private fun logEndPositionsAndResetDistance() {
        // Create sorted entries only once and iterate.
        cloudMap.entries
            .sortedBy { it.key }
            .forEach { (tileId, cloud) ->
                val endingTile = mapController.getTile(tileId)
                if (endingTile.category == TileType.FIELD || endingTile.category == TileType.PLANTATION) {
                    Logger.cloudPosition(cloud.id, cloud.location, endingTile.amountSunlight)
                }
                cloud.distance = Cloud.MAX_MOVES
            }
    }

    /**
     * Execute the full processing cycle for a single cloud.
     */
    private fun processSingleCloud(cloud: Cloud) {
        val currentTile = mapController.getTile(cloud.location)

        if (currentTile.category == TileType.VILLAGE) {
            dissipate(cloud)
        } else {
            rain(cloud)
            // Only proceed if cloud wasn't dissipated during rain.
            if (cloud in clouds) {
                val survivingCloud = move(cloud)
                survivingCloud?.let { applyEndPhaseEffects(it) }
            }
        }
    }

    /**
     * A helper function for updateOnTick() to reduce a tile's sunlight at the end of tick.
     * @param cloud The cloud the tile is on at the end of the tick,
     */
    private fun applyEndPhaseEffects(cloud: Cloud) {
        // Handle duration reduction for non-infinite clouds.
        if (cloud.duration != Cloud.INFINITE_CLOUD_DURATION) {
            cloud.duration--
            if (cloud.duration <= 0) {
                dissipate(cloud)
                return
            }
        }
    }

    /**
     * Assigns the largest maxId and updates the cloudMap.
     * If a cloud exists at the intended tile already, the two are merged. Else a fresh new cloud is created
     * @param c Cloud is the cloud that is created and added to the MutableList clouds.
     */
    fun createCloud(c: Cloud) {
        c.id = ++maxId // Assign ID immediately

        // Early return for village tiles.
        if (mapController.getTile(c.location).category == TileType.VILLAGE) {
            dissipate(c)
            return
        }

        // Check for existing cloud and merge or add new.
        val existingCloud = cloudMap[c.location]
        if (existingCloud != null) {
            merge(existingCloud, c)
        } else {
            clouds.add(c)
            cloudMap[c.location] = c
            // Add to processing queue if we're still in cloud phase.
            if (!isCloudPhaseOver) {
                processingClouds.addLast(c)
            }
        }
    }

    /**
     * Dissipates a cloud if it is either stuck on a tile (village) or it has rained entirely.
     * @param c Cloud meant to be dissipated.
     */
    fun dissipate(c: Cloud) {
        val tile = mapController.getTile(c.location)

        // Remove the entire existence of the cloud. RIP Cloud.
        cloudMap.remove(c.location)
        clouds.remove(c)
        processingClouds.remove(c)

        // If dissipated on Village, then Cloud Stuck, else Cloud Dissipated.
        when (tile.category) {
            TileType.VILLAGE -> Logger.cloudStuck(c.id, c.location)
            else -> Logger.cloudDissipation(c.id, c.location)
        }
    }

    /**
     * Helper function to check if a cloud can continue moving
     */
    private fun canCloudMove(cloud: Cloud): Boolean {
        return cloud.duration != 0 &&
            cloud.distance > Cloud.NO_MORE_MOVES_LEFT &&
            clouds.contains(cloud)
    }

    /**
     * Moves a cloud w.r.t the airflow and the direction extracted from the tile the cloud is on.
     * @param c Cloud meant to be moved.
     * @return If cloud dissipates/merges amid the process, returns null, else the moved cloud with updated location.
     */
    fun move(c: Cloud): Cloud? {
        // Early checks for movement viability
        if (c.duration == 0 || c.distance <= Cloud.NO_MORE_MOVES_LEFT) {
            if (c.duration == 0) dissipate(c)
            return if (c.duration == 0) null else c
        }

        var currentCloud: Cloud? = c
        val originalCloudId = c.id

        while (currentCloud != null && canCloudMove(currentCloud)) {
            val originTile = mapController.getTile(currentCloud.location)
            val neighbourTile = mapController.getNeighbourTile(currentCloud.location)
                ?: return currentCloud // No valid neighbor, stop movement.

            // Apply sunlight reduction for movement.
            originTile.amountSunlight = maxOf(0, originTile.amountSunlight - Cloud.REDUCE_SUNLIGHT_MOVE)

            currentCloud = executeMove(currentCloud, originTile, neighbourTile, originalCloudId)
                ?: return null // Cloud was merged or dissipated.

            // Rain after movement
            rain(currentCloud)

            // Check if cloud still exists after raining.
            if (!clouds.contains(currentCloud)) {
                return null
            }
        }

        return currentCloud
    }

    /**
     * A helper function for move(c: Cloud) to execute the movement of the cloud (c: Cloud)
     * If the target tile (neighbourTile) already has a cloud, it merges the two by calling merge.
     * Else the manipulates the map and clouds' list to finalise the move.
     * @param cloud The cloud (c) set to move by the caller move(c: Cloud).
     * @param originTile The tile FROM where the cloud (c) had to be moved by the caller move(c: Cloud).
     * @param neighbourTile The tile TO where the cloud (c) had to be moved by the caller move(c: Cloud).
     * @param originalCloudId The original ID to track if merging occurred.
     * @return If merged, returns null, else the moved cloud with updated location.
     */
    private fun executeMove(
        cloud: Cloud,
        originTile: Tile,
        neighbourTile: Tile,
        originalCloudId: Int
    ): Cloud? {
        val stationaryCloud = cloudMap[neighbourTile.id]

        val resultCloud = if (stationaryCloud != null) {
            // De-assign the cloud from the tile.
            cloudMap.remove(cloud.location)
            logEndOfMovement(cloud, originTile, neighbourTile)
            merge(stationaryCloud, cloud)
        } else {
            // Simple movement
            cloudMap.remove(originTile.id)
            cloudMap[neighbourTile.id] = cloud
            cloud.location = neighbourTile.id
            logEndOfMovement(cloud, originTile, neighbourTile)
            cloud
        }

        // If merge happened, new cloud shouldn't move this tick.
        if (resultCloud.id != originalCloudId) {
            return null
        }

        // Check if cloud landed on village.
        if (mapController.getTile(resultCloud.location).category == TileType.VILLAGE) {
            dissipate(resultCloud)
            return null
        }

        resultCloud.distance--
        return resultCloud
    }

    /**
     * A helper function for move(c: Cloud) to log the cloud's movement once the move has been executed.
     * @param cloud The cloud (c) set to move by the caller move(c: Cloud).
     * @param originTile The tile from where the cloud (c) had to be moved by the caller move(c: Cloud).
     * @param neighbourTile The tile TO where the cloud (c) had to be moved by the caller move(c: Cloud).
     */
    private fun logEndOfMovement(cloud: Cloud, originTile: Tile, neighbourTile: Tile) {
        Logger.cloudMovement(cloud.id, cloud.amount, originTile.id, neighbourTile.id)
        if (originTile.category == TileType.PLANTATION || originTile.category == TileType.FIELD) {
            Logger.cloudMovementSunlight(originTile.id, originTile.amountSunlight)
        }
    }

    /**
     * Merges two clouds on a tile if they collide.
     * @param c1 The cloud that DID NOT MOVE.
     * @param c2 The cloud that DID MOVE onto c1's tile.
     * @return Returns the newly created MERGED cloud.
     */
    fun merge(c1: Cloud, c2: Cloud): Cloud {
        require(c1.amount > 0 && c2.amount > 0) { "A dissipated cloud cannot be merged" }

        val newDuration: Int = when {
            c1.duration == Cloud.INFINITE_CLOUD_DURATION -> c2.duration
            c2.duration == Cloud.INFINITE_CLOUD_DURATION -> c1.duration
            else -> minOf(c1.duration, c2.duration)
        }
        val newAmount = c1.amount + c2.amount
        val newDistance = maxOf(c1.distance, c2.distance)

        val newCloud = Cloud(++maxId, newDuration, c1.location, newAmount, newDistance)

        // Remove old clouds from all data structures
        clouds.removeAll(listOf(c1, c2))
        processingClouds.removeAll(listOf(c1, c2))

        // Add new cloud
        clouds.add(newCloud)
        cloudMap[newCloud.location] = newCloud

        if (!isCloudPhaseOver) {
            processingClouds.addLast(newCloud)
        }

        Logger.cloudUnion(c1.id, c2.id, newCloud.id, newAmount, newDuration, c1.location)
        return newCloud
    }

    /**
     *  Makes a cloud rain, and dissipates it if needed.
     *  Changes the tile soilMoisture and @param c's amount.
     *  @param c The cloud being operated on.
     */
    fun rain(c: Cloud) {
        // Early termination if the cloud.amount is below 5000. (5000 not included)
        if (c.amount < Cloud.RAINABLE) return

        val tile = mapController.getTile(c.location)
        val maxCapacity = tile.maxCapacity

        // Handle tiles without capacity (non-farmable tiles)
        if (maxCapacity == null) {
            Logger.cloudRain(c.id, c.location, c.amount)
            dissipate(c)
            return
        }

        val rainNeeded = maxOf(0, maxCapacity - tile.soilMoisture)

        // No rain needed if soil is already at capacity.
        if (rainNeeded <= 0) return

        if (c.amount <= rainNeeded) {
            // Cloud will be fully consumed.
            tile.soilMoisture = minOf(c.amount + tile.soilMoisture, maxCapacity)
            Logger.cloudRain(c.id, c.location, c.amount)
            dissipate(c)
        } else {
            // Rains a bit and moves on with his/her life.
            tile.soilMoisture = maxCapacity
            c.amount -= rainNeeded
            Logger.cloudRain(c.id, c.location, rainNeeded)
        }
    }
}
