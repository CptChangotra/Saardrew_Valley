package de.unisaarland.cs.se.selab.farms

import de.unisaarland.cs.se.selab.plant.FieldPlant

/**
 * Represents a plan for sowing crops on a farm.
 *
 * A [SowingPlan] defines the details of a sowing action, including the start tick,
 * the type of plant to be sown, and the specific tiles or area where the sowing should occur.
 *
 * @property id A unique identifier for the sowing plan.
 * @property startTick The game tick when the sowing should begin.
 * @property plant The type of plant to be sown, represented by a [FieldPlant].
 * @property tileIDs An optional list of specific tile IDs where the plants should be sown.
 *                   If null, the sowing will be done in a circular area defined by [location] and [radius].
 * @property location An optional central tile ID for circular sowing. Used if [tileIDs] is null.
 * @property radius An optional radius defining the area around [location] for circular sowing.
 *                  Used if [tileIDs] is null.
 */
data class SowingPlan(
    val id: Int,
    val startTick: Int,
    val plant: FieldPlant,
    val tileIDs: List<Int>?,
    val location: Int?,
    val radius: Int?,
)
