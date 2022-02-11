import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*


internal class MainTest {

    @Test
    fun testGetPlayedGames() {
        assertEquals(0, playedGames)
        play()
        assertEquals(1, playedGames)
        for (i in 1..9) {
            play()
        }
        assertEquals(10, playedGames)
    }

    @Test
    fun testDetermineWinner() {
        assertEquals(Result.Draw, determineWinner(Sign.Rock, Sign.Rock))
        assertEquals(Result.Draw, determineWinner(Sign.Paper, Sign.Paper))
        assertEquals(Result.Draw, determineWinner(Sign.Scissors, Sign.Scissors))

        assertEquals(Result.P1Win, determineWinner(Sign.Rock, Sign.Scissors))
        assertEquals(Result.P1Win, determineWinner(Sign.Paper, Sign.Rock))
        assertEquals(Result.P1Win, determineWinner(Sign.Scissors, Sign.Paper))

        assertEquals(Result.P2Win, determineWinner(Sign.Rock, Sign.Paper))
        assertEquals(Result.P2Win, determineWinner(Sign.Paper, Sign.Scissors))
        assertEquals(Result.P2Win, determineWinner(Sign.Scissors, Sign.Rock))
    }

    @Test
    fun testGetRandomSign() {
        for (i in 1..50) {
            getRandomSign()   //checks for thrown exceptions
        }
    }

    @Test
    fun testGameResultToString() {
        assertTrue(gameResultToString(Triple(Sign.Rock, Sign.Rock, Result.Draw)).contains("Draw"))
        assertTrue(gameResultToString(Triple(Sign.Rock, Sign.Paper, Result.P1Win)).contains("P1"))
        assertTrue(gameResultToString(Triple(Sign.Rock, Sign.Scissors, Result.P2Win)).contains("P2"))
    }

    @Test
    fun testFinalResultToString() {
        results = arrayOf(3, 2, 1)
        var str = finalResultToString()
        assertTrue(str.contains("Player 1: 3"))
        assertTrue(str.contains("Player 2: 2"))
        assertTrue(str.contains("draw: 1"))
        assertTrue(str.contains("Player 1 won most"))

        results = arrayOf(1, 3, 0)
        str = finalResultToString()
        assertTrue(str.contains("Player 2 won most"))

        results = arrayOf(1, 1, 4)
        str = finalResultToString()
        assertTrue(str.contains("result is a draw"))
    }
}