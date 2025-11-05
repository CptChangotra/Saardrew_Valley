package de.unisaarland.cs.se.selab

import de.unisaarland.cs.se.selab.parser.HELP_MESSAGE
import de.unisaarland.cs.se.selab.parser.Parser
import de.unisaarland.cs.se.selab.utils.Logger

/**
 * Main function
 */
fun main(args: Array<String>) {
    val p = Parser(args)
    try {
        val simController = p.build()
        simController.runSimulation()
    } catch (_: Parser.HelpMessage) {
        // QUESTION: how do I print the help message?
        error(HELP_MESSAGE)
    } catch (e: Parser.ParsingCLIError) {
        error("Error while parsing the command line arguments: ${e.message.orEmpty()}\n${HELP_MESSAGE}")
    } catch (e: Parser.ParsingBuildError) {
        Logger.fileInvalid(e.path)
    } catch (_: Exception) {
        error("An unexpected error occurred")
    }
}
