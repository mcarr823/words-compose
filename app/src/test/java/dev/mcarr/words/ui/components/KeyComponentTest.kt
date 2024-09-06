package dev.mcarr.words.ui.components

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.performClick
import dev.mcarr.words.enums.Hint
import dev.mcarr.words.ui.AbstractUiUnitTest
import org.junit.Assert.assertEquals
import org.junit.Test

class KeyComponentTest : AbstractUiUnitTest() {

    @Test
    fun testClick(){

        var clicked = 0

        setContent {
            KeyComponent(letter = "A", hint = Hint.NONE){
                clicked++
            }
        }

        onNodeWithTag("KeyComponentButton").performClick()

        assertEquals(1, clicked)

        onNodeWithTag("KeyComponentButton").performClick()

        assertEquals(2, clicked)

    }

    @Test
    fun testHintNone(){

        setContent {
            KeyComponent(letter = "A", hint = Hint.NONE){}
        }

        onNodeWithTag("KeyComponentText")
            .assertExists()
            .assertIsDisplayed()
            .assertTextEquals("A")
            .assertTextColor(Color.Black)

        onNodeWithTag("KeyComponentButton")
            .assertExists()
            .assertIsDisplayed()
            .assertBackgroundColor(Color.White)

    }

    @Test
    fun testHintCorrect(){

        setContent {
            KeyComponent(letter = "A", hint = Hint.CORRECT){}
        }

        onNodeWithTag("KeyComponentText")
            .assertExists()
            .assertIsDisplayed()
            .assertTextEquals("A")
            .assertTextColor(Color.White)

        onNodeWithTag("KeyComponentButton")
            .assertExists()
            .assertIsDisplayed()

    }

    @Test
    fun testHintIncorrect(){

        setContent {
            KeyComponent(letter = "A", hint = Hint.INCORRECT){}
        }

        onNodeWithTag("KeyComponentText")
            .assertExists()
            .assertIsDisplayed()
            .assertTextEquals("A")
            .assertTextColor(Color.White)

        onNodeWithTag("KeyComponentButton")
            .assertExists()
            .assertIsDisplayed()

    }

    @Test
    fun testHintWrongPlacement(){

        setContent {
            KeyComponent(letter = "A", hint = Hint.WRONG_PLACEMENT){}
        }

        onNodeWithTag("KeyComponentText")
            .assertExists()
            .assertIsDisplayed()
            .assertTextEquals("A")
            .assertTextColor(Color.White)

        onNodeWithTag("KeyComponentButton")
            .assertExists()
            .assertIsDisplayed()

    }

    @Test
    fun testHintCorrectAnother(){

        setContent {
            KeyComponent(letter = "A", hint = Hint.CORRECT_ANOTHER){}
        }

        onNodeWithTag("KeyComponentText")
            .assertExists()
            .assertIsDisplayed()
            .assertTextEquals("A")
            .assertTextColor(Color.White)

        onNodeWithTag("KeyComponentButton")
            .assertExists()
            .assertIsDisplayed()

    }

}