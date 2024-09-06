package dev.mcarr.words.ui.components

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import dev.mcarr.words.classes.HintedLetter
import dev.mcarr.words.enums.Hint
import dev.mcarr.words.ui.AbstractUiUnitTest
import org.junit.Test

class LetterComponentTest : AbstractUiUnitTest() {

    @Test
    fun testHintNone(){

        val hintedLetter = HintedLetter(letter = "A", hint = Hint.NONE)

        setContent {
            LetterComponent(hintedLetter)
        }

        onNodeWithTag("LetterComponentText")
            .assertExists()
            .assertIsDisplayed()
            .assertTextEquals("A")
            .assertTextColor(Color.Black)

        onNodeWithTag("LetterComponentCard")
            .assertExists()
            .assertIsDisplayed()
            .assertBackgroundColor(Color.White)

    }

    @Test
    fun testHintCorrect(){

        val hintedLetter = HintedLetter(letter = "A", hint = Hint.CORRECT)

        setContent {
            LetterComponent(hintedLetter)
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

        val hintedLetter = HintedLetter(letter = "A", hint = Hint.INCORRECT)

        setContent {
            LetterComponent(hintedLetter)
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

        val hintedLetter = HintedLetter(letter = "A", hint = Hint.WRONG_PLACEMENT)

        setContent {
            LetterComponent(hintedLetter)
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

        val hintedLetter = HintedLetter(letter = "A", hint = Hint.CORRECT_ANOTHER)

        setContent {
            LetterComponent(hintedLetter)
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