package dev.mcarr.words.viewmodels

import dev.mcarr.words.enums.Hint
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GuessViewModelTest {

    private lateinit var model: GuessViewModel

    @Before
    fun setup(){
        model = GuessViewModel()
    }

    /**
     * Check the default values of each variable to make
     * sure they match our expectations.
     * */
    @Test
    fun defaultValuesTest(){

        assertEquals("", model.wordToGuess)
        assertEquals("", model.guess.joinToString(""))
        assertEquals(false, model.canSubmit)
        assertEquals(false, model.victory)
        assertEquals(true, model.previousGuesses.isEmpty())
        assertEquals(true, model.hints.isEmpty())

    }

    /**
     * Test the start() function by checking the values of
     * each variable which should be affected by that function.
     * */
    @Test
    fun startGameTest(){
        model.start("WORDS")
        assertEquals("WORDS", model.wordToGuess)
        assertEquals(true, model.previousGuesses.isEmpty())
        assertEquals(true, model.guess.isEmpty())
        assertEquals(false, model.canSubmit)
    }

    /**
     * Test the pressKey() function.
     *
     * Every time it runs, a letter should be added
     * to the guess, and the value of canSubmit should
     * be re-evaluated.
     *
     * Also, the guess shouldn't be able to become
     * longer than the word we're trying to guess.
     * So this also tests to make sure it doesn't
     * exceed the allowed length.
     * */
    @Test
    fun pressKeyTest(){
        model.start("WORDS")

        assertEquals(true, model.guess.isEmpty())
        assertEquals(false, model.canSubmit)

        model.pressKey("A")
        assertEquals(1, model.guess.size)
        assertEquals(false, model.canSubmit)

        model.pressKey("B")
        assertEquals(2, model.guess.size)
        assertEquals(false, model.canSubmit)

        model.pressKey("C")
        assertEquals(3, model.guess.size)
        assertEquals(false, model.canSubmit)

        model.pressKey("D")
        assertEquals(4, model.guess.size)
        assertEquals(false, model.canSubmit)

        model.pressKey("E")
        assertEquals(5, model.guess.size)
        assertEquals(true, model.canSubmit)

        // Length should still be 5, since the game was
        // started with the word "WORDS", which is 5
        // letters, and thus the max number we can enter.
        model.pressKey("F")
        assertEquals(5, model.guess.size)
        assertEquals(true, model.canSubmit)
    }

    /**
     * Tests the backspace() function.
     *
     * The backspace function should delete the final
     * character of a guessed word.
     *
     * If the word is already empty, then it does nothing.
     * */
    @Test
    fun backspaceTest(){
        model.start("WORDS")

        assertEquals(0, model.guess.size)
        assertEquals(false, model.canSubmit)

        // Enter a character, then check the guess length
        model.pressKey("A")
        assertEquals(1, model.guess.size)

        // Press backspace to delete that character, then
        // confirm that the length is back to 0
        model.backspace()
        assertEquals(0, model.guess.size)

        // Press backspace again. It shouldn't change
        // anything this time, since a string can't have
        // a negative length.
        model.backspace()
        assertEquals(0, model.guess.size)

        // Now let's do the same, but with more characters,
        // to make sure it works with longer strings.
        model.pressKey("A")
        model.pressKey("B")
        model.pressKey("C")
        model.pressKey("D")
        model.pressKey("E")
        assertEquals(5, model.guess.size)

        model.backspace()
        assertEquals(4, model.guess.size)

        model.backspace()
        assertEquals(3, model.guess.size)

        model.backspace()
        assertEquals(2, model.guess.size)

        model.backspace()
        assertEquals(1, model.guess.size)

        model.backspace()
        assertEquals(0, model.guess.size)

        // Finally, let's make sure it's removing the LAST
        // character from the string, not the first character.
        model.pressKey("A")
        model.pressKey("B")
        assertEquals("AB", model.guess.joinToString(""))
        model.backspace()
        assertEquals("A", model.guess[0])

    }

    /**
     * Tests the submit() function.
     *
     * Provides an invalid (too short) value to the
     * model, which means submission can't occur.
     *
     * This test makes sure that submission isn't
     * possible when an invalid guess is made.
     * */
    @Test
    fun submitInvalidTest(){
        model.start("WORDS")

        // Our guess is "WORD", which is 1 char short,
        // and thus invalid.
        model.pressKey("W")
        model.pressKey("O")
        model.pressKey("R")
        model.pressKey("D")

        assertEquals(0, model.previousGuesses.size)
        assertEquals(4, model.guess.size)
        assertEquals(false, model.canSubmit)

        // Try to submit, even though canSubmit is false
        model.submit()

        // The number of previous guesses should still be 0,
        // and the current guess should still be the same length
        // as before since it wasn't wiped.
        assertEquals(0, model.previousGuesses.size)
        assertEquals(4, model.guess.size)

    }

    /**
     * Tests the submit() and getHint() functions.
     *
     * This test guesses a valid word and submits it.
     *
     * It also checks the relevant variables before
     * and after submitting the guess to make sure the
     * results match our expectations.
     * */
    @Test
    fun submitValidTest(){
        model.start("WORDS")

        // Our guess is "WORDY". Which is incorrect, but
        // it is long enough to submit, and is thus a valid
        // guess.
        model.pressKey("W")
        model.pressKey("O")
        model.pressKey("R")
        model.pressKey("D")
        model.pressKey("Y")

        assertEquals(0, model.previousGuesses.size)
        assertEquals(5, model.guess.size)
        assertEquals(true, model.canSubmit)

        // Try to submit
        model.submit()

        // The number of previous guesses should now be 1.
        // The current guess should be reset to an empty string,
        // and we can't submit again yet because we haven't entered
        // another guess.
        assertEquals(1, model.previousGuesses.size)
        assertEquals(0, model.guess.size)
        assertEquals(false, model.canSubmit)

        // We should also have 5 hints now, since our guess WORDY
        // is 5 chars and doesn't have any duplicate characters.
        assertEquals(5, model.hints.size)

        // While we're there, we might as well double check that
        // the individual hints were applied correctly.
        assertEquals(Hint.CORRECT, model.getHint("W"))
        assertEquals(Hint.CORRECT, model.getHint("O"))
        assertEquals(Hint.CORRECT, model.getHint("R"))
        assertEquals(Hint.CORRECT, model.getHint("D"))
        assertEquals(Hint.INCORRECT, model.getHint("Y"))

    }

}