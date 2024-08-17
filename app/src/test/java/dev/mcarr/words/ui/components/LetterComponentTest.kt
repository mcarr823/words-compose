package dev.mcarr.words.ui.components

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import dev.mcarr.words.enums.Hint
import dev.mcarr.words.ui.AbstractUiUnitTest
import org.junit.Test

class LetterComponentTest : AbstractUiUnitTest() {

    @Test
    fun testHintNone(){

        setContent {
            LetterComponent(letter = "A", hint = Hint.NONE)
        }

        onNodeWithTag("LetterComponentText")
            .assertExists()
            .assertIsDisplayed()
            .assertTextEquals("A")
            .assertTextColor(Color.Black)

        onNodeWithTag("LetterComponentCard")
            .assertExists()
            .assertIsDisplayed()

    }

    @Test
    fun testHintCorrect(){

        setContent {
            LetterComponent(letter = "A", hint = Hint.CORRECT)
        }

        onNodeWithTag("LetterComponentText")
            .assertExists()
            .assertIsDisplayed()
            .assertTextEquals("A")
            .assertTextColor(Color.White)

        onNodeWithTag("LetterComponentCard")
            .assertExists()
            .assertIsDisplayed()

    }

    @Test
    fun testHintIncorrect(){

        setContent {
            LetterComponent(letter = "A", hint = Hint.INCORRECT)
        }

        onNodeWithTag("LetterComponentText")
            .assertExists()
            .assertIsDisplayed()
            .assertTextEquals("A")
            .assertTextColor(Color.White)

        onNodeWithTag("LetterComponentCard")
            .assertExists()
            .assertIsDisplayed()

    }

    @Test
    fun testHintWrongPlacement(){

        setContent {
            LetterComponent(letter = "A", hint = Hint.WRONG_PLACEMENT)
        }

        onNodeWithTag("LetterComponentText")
            .assertExists()
            .assertIsDisplayed()
            .assertTextEquals("A")
            .assertTextColor(Color.White)

        onNodeWithTag("LetterComponentCard")
            .assertExists()
            .assertIsDisplayed()

    }

    @Test
    fun testHintCorrectAnother(){

        setContent {
            LetterComponent(letter = "A", hint = Hint.CORRECT_ANOTHER)
        }

        onNodeWithTag("LetterComponentText")
            .assertExists()
            .assertIsDisplayed()
            .assertTextEquals("A")
            .assertTextColor(Color.White)

        onNodeWithTag("LetterComponentCard")
            .assertExists()
            .assertIsDisplayed()

    }

}