package de.unisaarland.cs.se.selab.plant

import de.unisaarland.cs.se.selab.utils.YearTick

/**
 * Plant
 */
interface Plant {
    /**the tick when the plant was last sown for beehappy inchident
     * u stoopid inchident is not a type it's a f1 meme u silly*/
    var lastSowingTick: Int

    /**function to return the harvest estimate*/
    fun harvestEstimate(): Int

    /**function to return the Moisture*/
    fun moisture(): Int

    /**function to return the Sunlight*/
    fun sunlight(): Int

    /**function to return the status of Pollination*/
    fun selfPollination(): Boolean
}

/**
 * FieldPlant
 */
enum class FieldPlant(
    val harvestEstimate: Int,
    val moisture: Int,
    val sunlight: Int,
    val selfPollination: Boolean,
    val sowingPeriod: Pair<Int, Int>,
    val harvestingPeriod: Pair<Int, Int>,
    override var lastSowingTick: Int = 0,
) : Plant {
    POTATO(
        harvestEstimate = 1000000,
        moisture = 500,
        sunlight = 130,
        selfPollination = false,
        sowingPeriod = Pair(YearTick.APRIL_1, YearTick.MAY_2),
        harvestingPeriod = Pair(YearTick.SEPTEMBER_1, YearTick.OCTOBER_2)
    ),
    WHEAT(
        harvestEstimate = 1500000,
        moisture = 450,
        sunlight = 90,
        selfPollination = true,
        sowingPeriod = Pair(YearTick.OCTOBER_1, YearTick.OCTOBER_2),
        harvestingPeriod = Pair(YearTick.JUNE_1, YearTick.JULY_1)
    ),
    OAT(
        harvestEstimate = 1200000,
        moisture = 300,
        sunlight = 90,
        selfPollination = true,
        sowingPeriod = Pair(YearTick.MARCH_2, YearTick.MARCH_2),
        harvestingPeriod = Pair(YearTick.JULY_1, YearTick.AUGUST_2)
    ),
    PUMPKIN(
        harvestEstimate = 500000,
        moisture = 600,
        sunlight = 120,
        selfPollination = false,
        sowingPeriod = Pair(YearTick.MAY_2, YearTick.JUNE_2),
        harvestingPeriod = Pair(YearTick.SEPTEMBER_1, YearTick.OCTOBER_2)
    );
    override fun harvestEstimate(): Int = harvestEstimate
    override fun moisture(): Int = moisture
    override fun sunlight(): Int = sunlight
    override fun selfPollination(): Boolean = selfPollination
}

/**
 * Plantation Plant
 */
enum class PlantationPlant(
    val harvestEstimate: Int,
    val moisture: Int,
    val sunlight: Int,
    val selfPollination: Boolean,
    val cuttingPeriod: List<Pair<Int, Int>>,
    val mowingPeriod: Pair<Int, Int>,
    val bloomingPeriod: Pair<Int, Int>,
    val harvestingPeriod: Pair<Int, Int>,
    override var lastSowingTick: Int = 0,
) : Plant {
    APPLE(
        harvestEstimate = 1700000,
        moisture = 100,
        sunlight = 50,
        selfPollination = false,
        cuttingPeriod = listOf(
            Pair(YearTick.NOVEMBER_1, YearTick.NOVEMBER_2),
            Pair(YearTick.FEBRUARY_1, YearTick.FEBRUARY_2)
        ),
        mowingPeriod = Pair(YearTick.JUNE_1, YearTick.SEPTEMBER_1),
        bloomingPeriod = Pair(YearTick.APRIL_2, YearTick.MAY_1),
        harvestingPeriod = Pair(YearTick.SEPTEMBER_1, YearTick.OCTOBER_1)
    ),
    ALMOND(
        harvestEstimate = 800000,
        moisture = 400,
        sunlight = 130,
        selfPollination = false,
        cuttingPeriod = listOf(
            Pair(YearTick.NOVEMBER_1, YearTick.NOVEMBER_2),
            Pair(YearTick.FEBRUARY_1, YearTick.FEBRUARY_2)

        ),
        mowingPeriod = Pair(YearTick.JUNE_1, YearTick.SEPTEMBER_1),
        bloomingPeriod = Pair(YearTick.FEBRUARY_2, YearTick.MARCH_1),
        harvestingPeriod = Pair(YearTick.AUGUST_2, YearTick.OCTOBER_1)
    ),
    CHERRY(
        harvestEstimate = 1200000,
        moisture = 150,
        sunlight = 120,
        selfPollination = false,
        cuttingPeriod = listOf(
            Pair(YearTick.NOVEMBER_1, YearTick.NOVEMBER_2),
            Pair(YearTick.FEBRUARY_1, YearTick.FEBRUARY_2)
        ),
        mowingPeriod = Pair(YearTick.JUNE_1, YearTick.JUNE_1),
        bloomingPeriod = Pair(YearTick.APRIL_2, YearTick.MAY_1),
        harvestingPeriod = Pair(YearTick.JULY_1, YearTick.JULY_2)
    ),
    GRAPE(
        harvestEstimate = 1200000,
        moisture = 250,
        sunlight = 150,
        selfPollination = true,
        cuttingPeriod = listOf(
            Pair(YearTick.JULY_2, YearTick.JULY_2),
            Pair(YearTick.AUGUST_1, YearTick.AUGUST_2)
        ),
        mowingPeriod = Pair(YearTick.APRIL_1, YearTick.JULY_1),
        bloomingPeriod = Pair(YearTick.JUNE_2, YearTick.JULY_1),
        harvestingPeriod = Pair(YearTick.SEPTEMBER_1, YearTick.SEPTEMBER_1)
    );

    override fun harvestEstimate(): Int = harvestEstimate
    override fun moisture(): Int = moisture
    override fun sunlight(): Int = sunlight
    override fun selfPollination(): Boolean = selfPollination
}
