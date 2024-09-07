package dev.mcarr.words.ui.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.unit.dp
import dev.mcarr.words.ui.AbstractUiUnitTest
import dev.mcarr.words.viewmodels.GameScreenViewModel
import dev.mcarr.words.viewmodels.GuessViewModel
import org.junit.Assert.assertEquals
import org.junit.Test

class GameScreenTest : AbstractUiUnitTest() {

    @Test
    fun testGameScreen(){

        val model = GameScreenViewModel()
        val guessModel = GuessViewModel()

        setContent {
            GameScreen(
                paddingValues = PaddingValues(0.dp),
                model = model,
                guessModel = guessModel
            )
        }

        //assertEquals("", model.currentGuess.value)
        //assertEquals(4, model.guesses.size)

        /*onNodeWithTag("GuessTextField"){

            // Starts empty
            assertTextEquals("")

            // Normal input
            performTextInput("T")
            assertTextEquals("T")
            assertEquals("T", model.currentGuess.value)

        }*/

    }

}