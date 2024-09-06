package dev.mcarr.words.classes

import androidx.compose.ui.graphics.Color
import dev.mcarr.words.enums.Hint
import dev.mcarr.words.ui.theme.Blue
import dev.mcarr.words.ui.theme.Gray
import dev.mcarr.words.ui.theme.Green
import dev.mcarr.words.ui.theme.Orange
import org.junit.Assert.assertEquals
import org.junit.Test

class HintedLetterTest {

    @Test
    fun testHintedLetterNone(){

        val letter = "A"
        val hint = Hint.NONE
        val l = HintedLetter(letter, hint)

        val pair = l.asPair()
        assertEquals(letter, pair.first)
        assertEquals(hint, pair.second)

        assertEquals(l.getTextColor(), Color.Black)
        assertEquals(l.getBgColor(), Color.White)

    }

    @Test
    fun testHintedLetterCorrect(){

        val letter = "A"
        val hint = Hint.CORRECT
        val l = HintedLetter(letter, hint)

        val pair = l.asPair()
        assertEquals(letter, pair.first)
        assertEquals(hint, pair.second)

        assertEquals(l.getTextColor(), Color.White)
        assertEquals(l.getBgColor(), Green)

    }

    @Test
    fun testHintedLetterIncorrect(){

        val letter = "A"
        val hint = Hint.INCORRECT
        val l = HintedLetter(letter, hint)

        val pair = l.asPair()
        assertEquals(letter, pair.first)
        assertEquals(hint, pair.second)

        assertEquals(l.getTextColor(), Color.White)
        assertEquals(l.getBgColor(), Gray)

    }

    @Test
    fun testHintedLetterCorrectAnother(){

        val letter = "A"
        val hint = Hint.CORRECT_ANOTHER
        val l = HintedLetter(letter, hint)

        val pair = l.asPair()
        assertEquals(letter, pair.first)
        assertEquals(hint, pair.second)

        assertEquals(l.getTextColor(), Color.White)
        assertEquals(l.getBgColor(), Blue)

    }

    @Test
    fun testHintedLetterWrongPlacement(){

        val letter = "A"
        val hint = Hint.WRONG_PLACEMENT
        val l = HintedLetter(letter, hint)

        val pair = l.asPair()
        assertEquals(letter, pair.first)
        assertEquals(hint, pair.second)

        assertEquals(l.getTextColor(), Color.White)
        assertEquals(l.getBgColor(), Orange)

    }

}