package dev.mcarr.words.classes

import androidx.compose.ui.graphics.Color
import dev.mcarr.words.enums.Hint
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Unit tests for the HintedString class.
 *
 * @see HintedString
 * */
class HintedStringTest {

    /**
     * Test the length of a invalid guess.
     *
     * Guesses must be the same length as the target word
     * in order to be valid.
     *
     * If a guess is too short, then it will be padded
     * for display purposes (asList) and unpadded for
     * validation purposes (isTargetLength).
     * */
    @Test
    fun testHintedStringTooShort(){

        val displayWord = "A"
        val targetWord = "WORDS"
        val l = HintedString(displayWord, targetWord)

        // displayWord should be padded out to 5 to match targetWord
        assertEquals(5, l.asList().size)

        // It should fail the target length check though, since the
        // target length check removes any padding
        assertEquals(false, l.isTargetLength(5))

        // Just for good measure, let's confirm the actual length
        assertEquals(true, l.isTargetLength(1))

    }

    /**
     * Test the length of a valid guess.
     *
     * Unlike the first test, this time around the guess
     * is the expected length.
     * */
    @Test
    fun testHintedStringCorrectLength(){

        val displayWord = "TESTY"
        val targetWord = "WORDS"
        val l = HintedString(displayWord, targetWord)

        // displayWord should be 5 chars
        assertEquals(5, l.asList().size)

        // Length check should pass
        assertEquals(true, l.isTargetLength(5))

    }

    /**
     * Test for a letter guessed correctly, but in the
     * wrong place.
     *
     * "S" is guessed in displayWord, but it's guessed
     * at index 2 instead of index 4.
     *
     * So that letter should be marked as WRONG_PLACEMENT
     * instead of INCORRECT.
     * */
    @Test
    fun testHintedStringWrongPlacement(){

        val displayWord = "TESTY"
        val targetWord = "WORDS"
        val l = HintedString(displayWord, targetWord)

        val lettersAndHints = l.asList()

        val expectedLetters = displayWord.chunked(1)
        val expectedHints = listOf(
            Hint.INCORRECT,
            Hint.INCORRECT,
            Hint.WRONG_PLACEMENT,
            Hint.INCORRECT,
            Hint.INCORRECT
        )
        expectedLetters.forEachIndexed { index, letter ->
            val hint = expectedHints[index]
            val l = lettersAndHints[index]
            assertEquals(letter, l.letter)
            assertEquals(hint, l.hint)
        }

    }

    /**
     * Check the string for correct guesses.
     *
     * 4/5 of the guessed letters are correct, and there
     * are no duplicate letters or incorrect placements.
     * So we should get a result of 4 correct and 1 incorrect.
     * */
    @Test
    fun testHintedStringCorrect(){

        val displayWord = "WORDY"
        val targetWord = "WORDS"
        val l = HintedString(displayWord, targetWord)

        val lettersAndHints = l.asList()

        val expectedLetters = displayWord.chunked(1)
        val expectedHints = listOf(
            Hint.CORRECT,
            Hint.CORRECT,
            Hint.CORRECT,
            Hint.CORRECT,
            Hint.INCORRECT
        )
        expectedLetters.forEachIndexed { index, letter ->
            val hint = expectedHints[index]
            val l = lettersAndHints[index]
            assertEquals(letter, l.letter)
            assertEquals(hint, l.hint)
        }

    }

    /**
     * Test for a correct guess which occurs twice, but is only
     * correctly guessed once.
     *
     * ie. The letter "T" is guessed once, but occurs twice.
     *
     * So instead of marking the guess as CORRECT, it is marked
     * as CORRECT_ANOTHER.
     * */
    @Test
    fun testHintedStringCorrectAnother(){

        val displayWord = "TUNIC"
        val targetWord = "TESTY"
        val l = HintedString(displayWord, targetWord)

        val lettersAndHints = l.asList()

        val expectedLetters = displayWord.chunked(1)
        val expectedHints = listOf(
            Hint.CORRECT_ANOTHER,
            Hint.INCORRECT,
            Hint.INCORRECT,
            Hint.INCORRECT,
            Hint.INCORRECT
        )
        expectedLetters.forEachIndexed { index, letter ->
            val hint = expectedHints[index]
            val l = lettersAndHints[index]
            assertEquals(letter, l.letter)
            assertEquals(hint, l.hint)
        }

    }

    /**
     * This test is the opposite of testHintedStringCorrectAnother.
     *
     * Instead of testing if we got a correct match which also
     * occurs elsewhere in the string, we're going to test for
     * a correct match which doesn't occur a second time.
     *
     * ie. The letter "T" is guessed twice, but only occurs once.
     * */
    @Test
    fun testHintedStringCorrectOnlyOnce(){

        val displayWord = "TESTY"
        val targetWord = "TUNIC"
        val l = HintedString(displayWord, targetWord)

        val lettersAndHints = l.asList()

        val expectedLetters = displayWord.chunked(1)
        val expectedHints = listOf(
            Hint.CORRECT,
            Hint.INCORRECT,
            Hint.INCORRECT,
            Hint.INCORRECT,
            Hint.INCORRECT
        )
        expectedLetters.forEachIndexed { index, letter ->
            val hint = expectedHints[index]
            val l = lettersAndHints[index]
            assertEquals(letter, l.letter)
            assertEquals(hint, l.hint)
        }

    }

    /**
     * Check for multiple correct occurences of the same
     * character.
     *
     * "T" occurs three times in the guess, and twice in
     * the target word.
     * Both guesses in the target are correct.
     *
     * So the result should be that two of the T guesses
     * are correct, and the third is incorrect.
     * */
    @Test
    fun testHintedStringCorrectTwice(){

        val displayWord = "TATTY"
        val targetWord = "TESTY"
        val l = HintedString(displayWord, targetWord)

        val lettersAndHints = l.asList()

        val expectedLetters = displayWord.chunked(1)
        val expectedHints = listOf(
            Hint.CORRECT,
            Hint.INCORRECT,
            Hint.INCORRECT,
            Hint.CORRECT,
            Hint.CORRECT
        )
        expectedLetters.forEachIndexed { index, letter ->
            val hint = expectedHints[index]
            val l = lettersAndHints[index]
            assertEquals(letter, l.letter)
            assertEquals(hint, l.hint)
        }

    }

}