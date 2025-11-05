package de.unisaarland.cs.se.selab.parser

import de.unisaarland.cs.se.selab.cloud.CloudController
import de.unisaarland.cs.se.selab.farms.Action
import de.unisaarland.cs.se.selab.farms.CuttingAction
import de.unisaarland.cs.se.selab.farms.Farm
import de.unisaarland.cs.se.selab.farms.FarmController
import de.unisaarland.cs.se.selab.farms.HarvestAction
import de.unisaarland.cs.se.selab.farms.MachineRouter
import de.unisaarland.cs.se.selab.farms.MowingAction
import de.unisaarland.cs.se.selab.incidents.Incident
import de.unisaarland.cs.se.selab.incidents.IncidentController
import de.unisaarland.cs.se.selab.incidents.PERCENT
import de.unisaarland.cs.se.selab.map.MapController
import de.unisaarland.cs.se.selab.map.SimulationMap
import de.unisaarland.cs.se.selab.map.Tile
import de.unisaarland.cs.se.selab.map.TileType
import de.unisaarland.cs.se.selab.plant.FieldPlant
import de.unisaarland.cs.se.selab.plant.Plant
import de.unisaarland.cs.se.selab.plant.PlantationPlant
import de.unisaarland.cs.se.selab.simulation.SimulationController
import de.unisaarland.cs.se.selab.utils.LogLevel
import de.unisaarland.cs.se.selab.utils.Logger
import de.unisaarland.cs.se.selab.utils.Stats
import de.unisaarland.cs.se.selab.utils.Tick
import de.unisaarland.cs.se.selab.utils.YearTick
import kotlinx.serialization.Serializable
import java.io.File
import java.io.PrintWriter
import kotlin.collections.contains
import kotlin.collections.plus
import kotlin.collections.plusAssign
import kotlin.collections.set
import kotlin.math.max

/**
 * Set to true to enable Map Parser outputting to stderr.
 */
internal const val DEBUG_MAP_PARSER = false

/**
 * Set to true to enable Farm Parser outputting to stderr.
 */

internal const val DEBUG_FARM_PARSER = false

/**
 * set to true to enable scenario parser outputting to stderr.
 */
internal const val DEBUG_SCENARIO_PARSER = false

/**
 * set to false to disable most validation (to check if parser is too restrictive)
 */
internal const val ENABLE_VALIDATION = true

const val MAX_TICKS_UPPER_BOUND = 1000
const val START_TICK_LOWER_BOUND = 1
const val START_TICK_UPPER_BOUND = 24
const val HELP_MESSAGE = """
            The simulator is started with these command line parameters:
                --map                 Path to map config file (required)
                --farms               Path to farms config file (required)
                --scenario            Path to scenario config file (required)
                --start_year_tick     The tick start within a year (default 1, between 1 and 24)
                --max_ticks           Maximum allowed number of ticks (required, max 1000)
                --log_level           DEBUG, INFO or IMPORTANT
                --out                 Path to output file (default stdout)
                --help                Print this message and exit
                """
const val UNREACHABLE = "unreachable"

const val MAP_STR = "map"
const val FARMS_STR = "farms"
const val SCENARIO_STR = "scenario"
const val OUT_STR = "out"
const val LOG_LEVEL_STR = "log_level"
const val MAX_TICKS_STR = "max_ticks"

private const val APPLE_REDUCTION = 50
private const val ALMOND_REDUCTION = 10
private const val CHERRY_REDUCTION = 70
private const val GRAPE_REDUCTION = 5

private const val CHERRY_PERIOD_START = 16
private const val CHERRY_PERIOD_END = 20

/**
 * Main Parser class
 * @param args The CLI arguments
 * @param mapConfig If present, the parser will not look for a map config in the CLI arguments
 * @param farmConfig If present, the parser will not look for a farm config in the CLI arguments
 * @param scenarioConfig If present, the parser will not look for a scenario config in the CLI arguments
 */
class Parser(
    val args: Array<String>,
    var mapConfig: String? = null,
    var farmConfig: String? = null,
    var scenarioConfig: String? = null
) {
    private val simStats = Stats(mutableMapOf(), mutableMapOf())

    /**
     * Error while parsing/validating JSON files
     */
    class ParsingBuildError(val path: String) : Exception(path)

    /**
     * Error while parsing/validating CLI args
     */
    class ParsingCLIError(message: String) : Exception(message)

    /**
     * While parsing CLI args, help message flag was present
     */
    class HelpMessage : Exception("help message")

    /**
     * Parses & validates configuration, then creates `SimulationController`
     */
    fun build(): SimulationController {
        val params = parseCLI().getOrElse { throw it }

        Tick.yTick = params.startTick
        Tick.currentTick = 0

        Logger.logLevel = params.logLevel
        Logger.writer = params.out

        val mapC = createMapController(params)
        Logger.fileValid(params.mapConfigPath)
        val farmC = createFarmControllers(params, mapC)
        Logger.fileValid(params.farmConfigPath)
        val (cloudC, incidentC) = createCloudAndIncidentController(params, mapC, farmC)
        Logger.fileValid(params.scenarioConfigPath)

        val simC = SimulationController(
            params.maxTicks,
            mapC,
            cloudC,
            farmC,
            incidentC,
            simStats
        )

        return simC
    }

    private fun createMapController(params: Parameters): MapController {
        val mapPath = params.mapConfigPath
        val mapConfig = readConfig(mapPath, mapConfig).getOrElse { throw ParsingBuildError(mapPath) }
        val mapP = MapParser(mapConfig)
        val map = mapP.createMap().getOrElse { throw ParsingBuildError(mapPath) }
        initPlantationHarvestEstimates(map, params.startTick)
        return MapController(map)
    }

    private fun createFarmControllers(params: Parameters, mapC: MapController): List<FarmController> {
        val farmPath = params.farmConfigPath
        val farmConfig = readConfig(farmPath, farmConfig).getOrElse { throw ParsingBuildError(farmPath) }
        val farmP = FarmParser(farmConfig)
        val farms = farmP.createFarms(mapC).getOrElse { throw ParsingBuildError(farmPath) }

        // Initialise Stats object
        simStats.statsPerPlant = (PlantationPlant.entries.map { it.name } + FieldPlant.entries.map { it.name })
            .associateWith { 0 }
        simStats.statsPerFarm = farms.map { it.id }.associateWith { 0 }

        return createFarmControllers(farms, mapC, simStats).sortedBy { it.farm.id }
    }

    private fun createCloudAndIncidentController(
        params: Parameters,
        mapC: MapController,
        farmC: List<FarmController>
    ): Pair<CloudController, IncidentController> {
        val scenarioPath = params.scenarioConfigPath
        val scenarioConfig = readConfig(scenarioPath, scenarioConfig)
            .getOrElse { throw ParsingBuildError(scenarioPath) }
        val scenarioP = ScenarioParser(scenarioConfig)

        val clouds = scenarioP.createClouds(mapC.simulationMap).getOrElse { throw ParsingBuildError(scenarioPath) }
        val tileIdToCloud = clouds.associateBy { it.location }.toMutableMap()
        val cloudC = CloudController(clouds.toMutableList(), tileIdToCloud, mapC)
        cloudC.maxId = clouds.maxOfOrNull { it.id } ?: -1 // -1, so that first assigned id will be 0

        val incidents = scenarioP.createIncidents(mapC, farmC, cloudC)
            .getOrElse { throw ParsingBuildError(scenarioPath) }
            .sortedWith(compareBy<Incident> { it.tick }.thenBy { it.id })
        return cloudC to IncidentController(incidents)
    }

    /**
     * For each Plantation tile, it sets the initial correct harvest estimate (e.g. if in late harvest period)
     */
    private fun initPlantationHarvestEstimates(map: SimulationMap, yTick: Int) {
        for (tile in map.idToTile.values) {
            if (tile.category != TileType.PLANTATION) continue
            val plant = (tile.plant ?: error("plant cannot be null on Plantation")) as PlantationPlant
            val (reduction, startTick, endTick) = when (plant) {
                PlantationPlant.APPLE -> Triple(APPLE_REDUCTION, YearTick.OCTOBER_2, YearTick.OCTOBER_2)
                PlantationPlant.ALMOND -> Triple(ALMOND_REDUCTION, YearTick.OCTOBER_2, YearTick.OCTOBER_2)
                PlantationPlant.CHERRY -> Triple(CHERRY_REDUCTION, YearTick.AUGUST_1, YearTick.AUGUST_1)
                PlantationPlant.GRAPE -> Triple(GRAPE_REDUCTION, YearTick.SEPTEMBER_2, YearTick.OCTOBER_2)
            }
            var initialHarvest = reduceHarvestBasedOnTick(yTick, plant, reduction, startTick, endTick)
            if (yTick in CHERRY_PERIOD_START..CHERRY_PERIOD_END && plant == PlantationPlant.CHERRY) {
                initialHarvest = 0
            }
            tile.harvestEstimate = initialHarvest
            tile.startOfTickEstimate = initialHarvest
        }
    }

    /**
     * If the [yTick] is in [[startTick], [endTick]],
     *  then it applies the [reduction], `[yTick] - [startTick] + 1` times.
     * @param reduction percentage (e.g. 30 for 30% reduction per tick)
     */
    private fun reduceHarvestBasedOnTick(
        yTick: Int,
        plant: PlantationPlant,
        reduction: Int,
        startTick: Int,
        endTick: Int
    ): Int {
        var harvest = plant.harvestEstimate
        if (yTick !in startTick..endTick) return harvest
        for (tick in startTick..yTick) harvest = harvest * (PERCENT - reduction) / PERCENT
        return harvest
    }

    /**
     * Validates that all CLI args have correct values.
     */
    private fun parseCLI(): Result<Parameters> {
        val cliArgs = cliArgsToMap().getOrElse { return Result.failure(it) }
        checkFlags(cliArgs).onFailure { return Result.failure(it) }
        cleanPaths(cliArgs).onFailure { return Result.failure(it) }

        var errorMessage: String? = null

        val maxTicks = when (val res = cliArgs[MAX_TICKS_STR]?.toIntOrNull()) {
            null -> {
                errorMessage = "max tick must be an int"
                0
            }
            else -> { res }
        }
        if (maxTicks > MAX_TICKS_UPPER_BOUND) errorMessage = "max ticks must not exceed 1000"
        if (maxTicks < 0) errorMessage = "max ticks must not be below 0"

        val startTickString = cliArgs["start_year_tick"] ?: "1"
        val startTick = when (val res = startTickString.toIntOrNull()) {
            null -> {
                errorMessage = "start tick must be an int"
                0
            }
            else -> { res }
        }
        if (startTick !in START_TICK_LOWER_BOUND..START_TICK_UPPER_BOUND) {
            errorMessage = "start year tick must be in [1, 24]"
        }

        val out = if (OUT_STR in cliArgs) {
            PrintWriter(File(cliArgs[OUT_STR] ?: error(UNREACHABLE)))
        } else {
            PrintWriter(System.out)
        }

        val logLevel =
            when (val res = LogLevel.entries.find { it.name == (cliArgs[LOG_LEVEL_STR] ?: error(UNREACHABLE)) }) {
                null -> {
                    errorMessage = "log level must be in ${LogLevel.entries}"
                    LogLevel.DEBUG
                }
                else -> { res }
            }

        if (errorMessage != null) return Result.failure(ParsingCLIError(errorMessage))

        val parameters = Parameters(
            startTick,
            maxTicks,
            cliArgs[MAP_STR] ?: error(UNREACHABLE),
            cliArgs[FARMS_STR] ?: error(UNREACHABLE),
            cliArgs[SCENARIO_STR] ?: error(UNREACHABLE),
            out,
            logLevel
        )

        return Result.success(parameters)
    }

    /**
     * @return Ok(...) if flags are in pairs
     * @return Failure(HelpMessage) if `--help flag is present`
     * @return Failure(ParsingCLIError) if flags do not have an input or do not start with --
     */
    private fun cliArgsToMap(): Result<MutableMap<String, String>> {
        if (args.size == 1 && args.first() == "--help") {
            return Result.failure(HelpMessage())
        }
        if (args.size % 2 != 0) {
            return Result.failure(ParsingCLIError("each flag must have an input value"))
        }
        var cliArgs = args.toList().chunked(2).associate { it[0] to it[1] }

        val correctFlagStart = cliArgs.all { it.key.startsWith("--") }
        if (!correctFlagStart) {
            return Result.failure(ParsingCLIError("flag must start with --"))
        }

        cliArgs = cliArgs.map { (k, v) -> Pair(k.drop(2), v) }.associate { it }.toMutableMap()
        return Result.success(cliArgs)
    }

    /**
     * Checks all required flags are present in CLI args.
     */
    private fun checkFlags(cliArgs: Map<String, String>): Result<Unit> {
        val correctFlags = cliArgs.all {
            it.key in listOf(
                MAP_STR,
                FARMS_STR,
                SCENARIO_STR,
                "start_year_tick",
                OUT_STR,
                LOG_LEVEL_STR,
                MAX_TICKS_STR
            )
        }
        if (!correctFlags) return Result.failure(ParsingCLIError("unknown flag"))

        for (flag in listOf(MAP_STR, FARMS_STR, SCENARIO_STR, MAX_TICKS_STR, LOG_LEVEL_STR)) {
            if (flag !in cliArgs) return Result.failure(ParsingCLIError("expected $flag flag"))
        }

        return Result.success(Unit)
    }

    /**
     * Cleans up paths and returns Ok(Unit) if successful.
     */
    private fun cleanPaths(cliArgs: MutableMap<String, String>): Result<Unit> {
        for (flag in listOf(MAP_STR, FARMS_STR, SCENARIO_STR, OUT_STR)) {
            if (flag !in cliArgs) continue
            val path = cliArgs[flag] ?: error(UNREACHABLE)
            if (path.first() !in listOf('\'', '"')) continue

            if (path.first() != path.last()) return Result.failure(ParsingCLIError("unclosed parentheses"))

            cliArgs[flag] = path.replace(Regex("""^['"]|['"]$"""), "")
        }

        return Result.success(Unit)
    }

    /**
     * @return Ok(fileContents) if file exists
     * @return Failure(ParsingBuildError) if the file does not exist
     */
    private fun readConfig(path: String, config: String?): Result<String> =
        Result.success(
            config ?: run {
                val file = File(path)
                if (!file.exists()) {
                    return Result.failure(ParsingBuildError(path))
                }
                file.readText()
            }
        )

    private fun createFarmControllers(farms: List<Farm>, mapC: MapController, stats: Stats): List<FarmController> =
        farms.map { farm ->
            val machineRouter = MachineRouter(farm, mapC, mutableMapOf())

            val schedule = mutableMapOf<Int, MutableList<Action>>()

            farm.plantations.map { mapC.getTile(it) }.map { tile ->
                val plant = tile.plant as? PlantationPlant ?: error("expected plantation tile")

                val isGrape = plant == PlantationPlant.GRAPE

                val (hTick, hAction) = getHarvestAction(plant, tile)
                schedule.getOrPut(hTick) { mutableListOf() }.add(hAction)

                getMowingActions(plant, tile).forEach { schedule.getOrPut(it.first) { mutableListOf() }.add(it.second) }

                val cuttingPair =
                    if (!isGrape) getCuttingActionForNonGrape(plant, tile) else getCuttingActionForGrape(plant, tile)
                if (cuttingPair != null) {
                    val (tick, action) = cuttingPair
                    schedule.getOrPut(tick) { mutableListOf() }.add(action)
                }
            }
            FarmController(farm, mapC, schedule, machineRouter, stats)
        }

    /**
     * Creates the initial (first) Harvest Action for [tile], based on the start year tick.
     */
    private fun getHarvestAction(plant: PlantationPlant, tile: Tile): Pair<Int, HarvestAction> {
        val isGrape = plant == PlantationPlant.GRAPE
        val (startHarvest, endHarvest) = plant.harvestingPeriod
        val lateHarvestPeriod = if (isGrape) 3 else 1
        val isHarvestThisYear = Tick.yTick <= endHarvest + lateHarvestPeriod

        val tick =
            if (isHarvestThisYear) {
                max(0, startHarvest - Tick.yTick)
            } else {
                startHarvest + START_TICK_UPPER_BOUND - Tick.yTick
            }
        return tick to HarvestAction(plant, startHarvest, endHarvest, tile)
    }

    /**
     * Creates the initial Mowing Actions for [tile] (up to the first Harvest Action), based on the start year tick.
     */
    private fun getMowingActions(plant: PlantationPlant, tile: Tile): List<Pair<Int, MowingAction>> {
        val isGrape = plant == PlantationPlant.GRAPE
        val (_, endHarvest) = plant.harvestingPeriod
        val lateHarvestPeriod = if (isGrape) 3 else 1
        val isHarvestThisYear = Tick.yTick <= endHarvest + lateHarvestPeriod

        val actions = mutableListOf<Pair<Int, MowingAction>>()

        val (fstMowing, sndMowing) = plant.mowingPeriod
        if (fstMowing >= Tick.yTick) {
            actions += fstMowing - Tick.yTick to MowingAction(plant, fstMowing, fstMowing, tile)
        }
        if (!isHarvestThisYear) {
            actions += fstMowing - Tick.yTick + START_TICK_UPPER_BOUND to
                MowingAction(plant, fstMowing, fstMowing, tile)
        }
        // both Mowing might be the same for cherries, hence the `fstMowing != sndMowing`
        if (sndMowing >= Tick.yTick && fstMowing != sndMowing) {
            actions += sndMowing - Tick.yTick to MowingAction(plant, sndMowing, sndMowing, tile)
        }
        if (!isHarvestThisYear && fstMowing != sndMowing) {
            actions += sndMowing - Tick.yTick + START_TICK_UPPER_BOUND to
                MowingAction(plant, sndMowing, sndMowing, tile)
        }

        return actions
    }

    /**
     * Creates the initial Cutting Action for [tile] (up to the first Harvest Action), based on the start year tick.
     * For all plants, apart from Grape.
     */
    private fun getCuttingActionForNonGrape(plant: PlantationPlant, tile: Tile): Pair<Int, CuttingAction>? {
        val (_, endHarvest) = plant.harvestingPeriod
        val lateHarvestPeriod = 1
        val isHarvestThisYear = Tick.yTick <= endHarvest + lateHarvestPeriod

        var tick: Int? = null
        var start = 0
        var end = 0

        val (startLateCutting, endLateCutting) = plant.cuttingPeriod[0]
        val (startEarlyCutting, endEarlyCutting) = plant.cuttingPeriod[1]
        // (E/L)C - (early/late) cut
        // (L)HV - (late) harvest
        if (Tick.yTick <= endEarlyCutting) {
            // |--|--|EC|EC|--|--|--|--|--|--|--|--|--|HV|HV|LH|--|--|--|--|LC|LC|--|--|
            // |==|==|==|==|  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |
            tick = max(startEarlyCutting - Tick.yTick, 0)
            start = startEarlyCutting
            end = endEarlyCutting
        } else if (!isHarvestThisYear) {
            // |--|--|EC|EC|--|--|--|--|--|--|--|--|--|HV|HV|LH|--|--|--|--|LC|LC|--|--|
            // |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |==|==|==|==|==|==|==|==|
            if (Tick.yTick <= endLateCutting) {
                // |--|--|EC|EC|--|--|--|--|--|--|--|--|--|HV|HV|LH|--|--|--|--|LC|LC|--|--|
                // |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |==|==|==|==|==|==|  |  |
                tick = max(startLateCutting - Tick.yTick, 0)
                start = startLateCutting
                end = endLateCutting
            } else {
                // |--|--|EC|EC|--|--|--|--|--|--|--|--|--|HV|HV|LH|--|--|--|--|LC|LC|--|--|
                // |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |==|==|
                tick = startEarlyCutting - Tick.yTick + START_TICK_UPPER_BOUND
                start = startEarlyCutting
                end = endEarlyCutting
            }
        }

        return if (tick != null) tick to CuttingAction(plant, start, end, tile) else null
    }

    /**
     * Helper that creates the initial Cutting Action for [tile] (up to the first Harvest Action),
     * based on the start year tick, only for Grape.
     */
    private fun getCuttingActionForGrape(plant: PlantationPlant, tile: Tile): Pair<Int, CuttingAction>? {
        require(plant == PlantationPlant.GRAPE) { "plant should be a grape" }
        val (_, endHarvest) = plant.harvestingPeriod
        val lateHarvestPeriod = 3
        val isHarvestThisYear = Tick.yTick <= endHarvest + lateHarvestPeriod

        var tick: Int? = null
        var start = 0
        var end = 0

        val (startLateCutting, endLateCutting) = plant.cuttingPeriod[1]
        val (startEarlyCutting, endEarlyCutting) = plant.cuttingPeriod[0]
        // (E/L)C - (early/late) cut
        // (L)HV - (late) harvest
        if (Tick.yTick <= endEarlyCutting) {
            // |--|--|--|--|--|--|--|--|--|--|EC|LC|LC|HV|HV|LH|--|--|--|--|--|--|--|--|
            // |==|==|==|==|==|==|==|==|==|==|==|  |  |  |  |  |  |  |  |  |  |  |  |  |
            tick = max(startEarlyCutting - Tick.yTick, 0)
            start = startEarlyCutting
            end = endEarlyCutting
        } else if (Tick.yTick <= endLateCutting) {
            // |--|--|--|--|--|--|--|--|--|--|EC|LC|LC|HV|HV|LH|--|--|--|--|--|--|--|--|
            // |  |  |  |  |  |  |  |  |  |  |  |==|==|  |  |  |  |  |  |  |  |  |  |  |
            tick = max(startLateCutting - Tick.yTick, 0)
            start = startLateCutting
            end = endLateCutting
        } else if (!isHarvestThisYear) {
            // |--|--|--|--|--|--|--|--|--|--|EC|LC|LC|HV|HV|LH|--|--|--|--|--|--|--|--|
            // |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |==|==|==|==|==|==|==|==|
            tick = startEarlyCutting - Tick.yTick + START_TICK_UPPER_BOUND
            start = startEarlyCutting
            end = endEarlyCutting
        }

        return if (tick != null) tick to CuttingAction(plant, start, end, tile) else null
    }
}

/**
 * Data class containing the CLI arguments
 */
data class Parameters(
    val startTick: Int = 1,
    val maxTicks: Int,
    val mapConfigPath: String,
    val farmConfigPath: String,
    val scenarioConfigPath: String,
    val out: PrintWriter = PrintWriter(System.out),
    val logLevel: LogLevel
)

/**
 * Serializable JSON classes for Plantation Plants used by Map/Farm parsers
 */
@Serializable
internal enum class JSONPlantationPlant {
    APPLE, GRAPE, ALMOND, CHERRY;

    fun toPlantationPlant(): PlantationPlant = when (this) {
        APPLE -> PlantationPlant.APPLE
        GRAPE -> PlantationPlant.GRAPE
        ALMOND -> PlantationPlant.ALMOND
        CHERRY -> PlantationPlant.CHERRY
    }
}

/**
 * Serializable JSON classes for Field Plants used by Map/Farm parsers
 */
@Serializable
internal enum class JSONFieldPlant {
    POTATO, WHEAT, OAT, PUMPKIN;

    fun toFieldPlant(): FieldPlant = when (this) {
        OAT -> FieldPlant.OAT
        PUMPKIN -> FieldPlant.PUMPKIN
        WHEAT -> FieldPlant.WHEAT
        POTATO -> FieldPlant.POTATO
    }
}

/**
 * Serializable JSON classes for Plants used by Map/Farm parsers
 */
@Serializable
internal enum class JSONPlant {
    POTATO, WHEAT, OAT, PUMPKIN, APPLE, GRAPE, ALMOND, CHERRY;

    override fun toString(): String = when (this) {
        POTATO -> "POTATO"
        WHEAT -> "WHEAT"
        OAT -> "OAT"
        PUMPKIN -> "PUMPKIN"
        APPLE -> "APPLE"
        GRAPE -> "GRAPE"
        ALMOND -> "ALMOND"
        CHERRY -> "CHERRY"
    }

    fun toPlant(): Plant = when (this) {
        OAT -> FieldPlant.OAT
        PUMPKIN -> FieldPlant.PUMPKIN
        WHEAT -> FieldPlant.WHEAT
        POTATO -> FieldPlant.POTATO
        APPLE -> PlantationPlant.APPLE
        GRAPE -> PlantationPlant.GRAPE
        ALMOND -> PlantationPlant.ALMOND
        CHERRY -> PlantationPlant.CHERRY
    }
}
