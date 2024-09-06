package dev.mcarr.words.viewmodels

import org.junit.Assert.assertEquals
import org.junit.Test

class GuessViewModelTest {

    @Test
    fun startGameTest(){
        val model = GuessViewModel()
        model.start("WORDS")

        assertEquals("WORDS", model.wordToGuess)
        assertEquals(true, model.previousGuesses.isEmpty())
        assertEquals(true, model.guess.isEmpty())
        assertEquals(false, model.canSubmit)
    }

}