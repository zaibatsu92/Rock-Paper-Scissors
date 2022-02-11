import kotlin.random.Random

/**
 * Rock Paper Scissors
 * This program plays a set number of games of rock paper scissors and presents the result of each game and the overall
 * statistics at the end.
 * @author Christian Dirks
 */

const val GAMES_TO_PLAY = 100
val P1_ALWAYS_SELECTS = Sign.Rock

enum class Sign { Rock, Paper, Scissors }
enum class Result { P1Win, P2Win, Draw }

var results = arrayOf(0, 0, 0)
val playedGames: Int get() = results.sumOf { it }

/**
 * Returns the winner or draw depending on the signs given by two players
 */
fun determineWinner(p1: Sign, p2: Sign): Result {
    if (p1 == p2) return Result.Draw
    if ((p1.ordinal + 1) % 3 == p2.ordinal) return Result.P2Win //each enum component looses against the next one
    return Result.P1Win
}

fun getRandomSign(): Sign {
    return Sign.values()[Random.nextInt(3)]
}

/**
 * Plays one round of rock paper scissors and returns a Triple of the signs each player gave and the result of the game.
 */
fun play(): Triple<Sign, Sign, Result> {
    val p1 = P1_ALWAYS_SELECTS
    val p2 = getRandomSign()
    val result = determineWinner(p1, p2)
    results[result.ordinal]++
    return Triple(p1, p2, result)
}

/**
 * Turns player's choices and result to a readable String
 */
fun gameResultToString(result: Triple<Sign, Sign, Result>): String =
    "Game $playedGames: ${result.first} vs ${result.second}. Result ${result.third}"

/**
 * Prints the overall statistics of all games played.
 */
fun finalResultToString(): String {
    var text = "__________________________\n" +
            "Total games played:    $playedGames\n" +
            "Games won by Player 1: ${results[0]}\n" +
            "Games won by Player 2: ${results[1]}\n" +
            "Games ended in a draw: ${results[2]}\n"
    text += when {
        results[0] > results[1] -> "Player 1 won most games"
        results[0] < results[1] -> "Player 2 won most games"
        else -> "Final result is a draw"
    }
    return text
}

fun main() {
    for (i in 1..GAMES_TO_PLAY) {
        val result = play()
        println(gameResultToString(result))
    }
    println(finalResultToString())
}