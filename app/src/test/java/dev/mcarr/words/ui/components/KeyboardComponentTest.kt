package dev.mcarr.words.ui.components

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.assertValueEquals
import androidx.compose.ui.test.onChild
import androidx.compose.ui.test.performClick
import dev.mcarr.words.enums.Hint
import dev.mcarr.words.ui.AbstractUiUnitTest
import dev.mcarr.words.viewmodels.GuessViewModel
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class KeyboardComponentTest : AbstractUiUnitTest() {

    private lateinit var model: GuessViewModel

    @Before
    fun setup(){
        model = GuessViewModel()

        // It's important to start the game.
        // Otherwise most of the model functions won't work
        // and will prevent these tests from running.
        model.start("WORDS")
    }

    /**
     * Test all of the alpha keys on the keyboard, one after
     * another, by:
     * - checking if a key exists for each letter of the alphabet
     * - pressing that key and checking if it updated model.guess
     * */
    @Test
    fun testAlphaKeys(){

        setContent {
            KeyboardComponent(model)
        }

        "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
            .chunked(1)
            .forEach { letter ->
                onNodeWithText(letter) {
                    assertExists()
                    assertIsDisplayed()

                    // Click on the key. That should append
                    // its value to model.guess
                    performClick()

                    // Confirm that the value of model.guess
                    // equals the letter of the key we just
                    // pressed
                    assertEquals(letter, model.guess)

                    // Delete it afterwards so we can check
                    // the next key.
                    model.backspace()
                }
            }

    }

    /**
     * Test the non-alpha keys by pressing each one
     * and comparing the results with the expected behavior
     * of each key.
     *
     * Unlike the alpha keys, the non-alpha keys don't append
     * the pressed character onto the guess.
     * 
     * They all independently behave in different ways, so they
     * each need different tests.
     * */
    @Test
    fun testNonAlphaKeys(){

        setContent {
            KeyboardComponent(model)
        }

        val nodes = allNodesWithTag("KeyComponentIcon")
        nodes.assertCountEquals(2)

        // The first result should be the Backspace button,
        // so let's try clicking it and see if it deletes
        // a character from the current guess.
        model.pressKey("A")
        assertEquals("A", model.guess)
        nodes[0].performClick()
        assertEquals("", model.guess)

        // The second result should be the Enter button,
        // so let's enter a valid guess and see if it submits.
        model.pressKey("A")
        model.pressKey("B")
        model.pressKey("C")
        model.pressKey("D")
        model.pressKey("E")
        assertEquals("ABCDE", model.guess)
        assertEquals(0, model.previousGuesses.size)
        nodes[1].performClick()
        assertEquals("", model.guess)
        assertEquals(1, model.previousGuesses.size)


    }

}