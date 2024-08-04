package dev.mcarr.words.ui.components

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.performImeAction
import androidx.compose.ui.test.performTextClearance
import androidx.compose.ui.test.performTextInput
import dev.mcarr.words.ui.AbstractUiUnitTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GuessTextFieldTest : AbstractUiUnitTest() {

    private var pressedDone = 0
    private var guess = mutableStateOf("")

    @Before
    fun setup(){

        pressedDone = 0
        guess = mutableStateOf("")

        setContent {
            var guessValue by remember {
                guess
            }
            GuessTextField(
                guess = guessValue,
                setGuess = { guessValue = it },
                targetLength = 5,
                submit = {
                    pressedDone++
                }
            )
        }

    }

    /**
     * Test if the Done button works
     * */
    @Test
    fun testButtonPress(){

        onNodeWithTag("GuessTextField") {

            assertEquals(0, pressedDone)
            performImeAction()
            assertEquals(1, pressedDone)

        }

    }

    /**
     * Types in letters one by one to check if they are added
     * in the correct order, cased correctly, and limited at
     * the right amount.
     * */
    @Test
    fun testStandardInput(){

        onNodeWithTag("GuessTextField"){

            // Starts empty
            assertTextEquals("")

            // Normal input
            performTextInput("T")
            assertTextEquals("T")
            assertEquals("T", guess.value)

            // Invalid character. Will be ignored
            performTextInput("!")
            assertTextEquals("T")
            assertEquals("T", guess.value)

            // Lower case character. Will be converted to upper case
            performTextInput("e")
            assertTextEquals("TE")
            assertEquals("TE", guess.value)

            // Two characters at once
            performTextInput("ST")
            assertTextEquals("TEST")
            assertEquals("TEST", guess.value)

            performTextInput("I")
            assertTextEquals("TESTI")
            assertEquals("TESTI", guess.value)

            // Target length is 5, so these next 2 inserts
            // should both fail to take effect
            performTextInput("N")
            assertTextEquals("TESTI")
            assertEquals("TESTI", guess.value)

            performTextInput("G")
            assertTextEquals("TESTI")
            assertEquals("TESTI", guess.value)

        }

    }

    /**
     * Test if deleting the text updates the variable correctly
     * */
    @Test
    fun testClear(){

        onNodeWithTag("GuessTextField"){

            performTextInput("A")
            assertTextEquals("A")
            assertEquals("A", guess.value)

            performTextClearance()
            assertTextEquals("")
            assertEquals("", guess.value)

        }

    }

    /**
     * The guess text field should be capped at `targetLength` length
     * */
    @Test
    fun testLength(){

        onNodeWithTag("GuessTextField"){

            performTextInput("testing")
            assertTextEquals("TESTI")
            assertEquals("TESTI", guess.value)

        }

    }

    /**
     * The guess text field should automatically convert the string
     * to upper case.
     * */
    @Test
    fun testCapitalization(){

        onNodeWithTag("GuessTextField"){

            performTextInput("test")
            assertTextEquals("TEST")
            assertEquals("TEST", guess.value)

        }

    }

    /**
     * The guess text field should only accept letters
     * */
    @Test
    fun testInvalidChars(){

        onNodeWithTag("GuessTextField"){

            performTextInput("a1!@'")
            assertTextEquals("A")
            assertEquals("A", guess.value)

        }

    }

}