package dev.mcarr.words.viewmodels

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dev.mcarr.words.classes.HintedString
import dev.mcarr.words.enums.Hint

class GuessViewModel : ViewModel() {

    /**
     * The word which the player needs to guess.
     * */
    var wordToGuess = mutableStateOf("")

    /**
     * The player's current guess as to what the word
     * might be.
     * */
    var guess = mutableStateOf("")

    /**
     * Boolean indicating whether the guess is in a
     * state where it can be submitted or not.
     * */
    var canSubmit = false

    /**
     * If true, the player has guessed the word correctly
     * and won the game.
     * */
    var victory = false

    /**
     * Arraylist holding the player's previous guesses.
     * Defaults to a list of `guessesToShow` blank strings.
     * Each string is padded to a length of `targetLength`.
     * */
    var previousGuesses = mutableStateListOf<HintedString>()

    /**
     * A map of hints for each letter of the alphabet.
     * Used to provide hinting for individual letters
     * after they've been guessed.
     * */
    var hints = HashMap<String, Hint>()

    /**
     * Responds to a key input event by adding to the
     * current guess string, or ignoring it if the
     * target guess length has been reached.
     *
     * Also determines whether or not the current guess
     * is long enough to submit or not.
     *
     * @param letter The letter to add to the current
     * guess.
     * */
    fun pressKey(letter: String){

        if (guess.value.length == wordToGuess.value.length){
            return
        }

        guess.value += letter

        if (guess.value.length == wordToGuess.value.length){
            canSubmit = true
        }
    }

    /**
     * Deletes the last character of the current guess.
     * */
    fun backspace(){
        val len = guess.value.length
        if (len > 0) guess.value = guess.value.take(len - 1)
        canSubmit = false
    }

    /**
     * Starts the game by reinitializing all variables.
     *
     * @param word The word which the player needs to guess
     * for the new game we're about to start.
     * */
    fun start(word: String){
        wordToGuess.value = word
        previousGuesses.clear()
        guess.value = ""
        canSubmit = false
        hints.clear()
    }

    /**
     * Adds the current guess into the previousGuesses list
     * and resets the state of the current guess.
     *
     * Also calculates new hints based on the new guess.
     * */
    fun submit(){

        if (!canSubmit) return

        victory = guess.value == wordToGuess.value

        val newGuess = HintedString(guess.value, wordToGuess.value)
        previousGuesses.add(newGuess)
        guess.value = ""
        canSubmit = false

        newGuess.asList().forEach {
            val (letter, hint) = it.asPair()
            val oldHint = getHint(letter)
            if (oldHint != Hint.CORRECT){
                hints[letter] = hint
            }
        }

    }

    /**
     * Get the most recently calculated hint associated with
     * a given letter.
     *
     * This hint can then be used to color GUI elements associated
     * with that letter.
     *
     * eg. To provide coloring for the keyboard component.
     *
     * @param letter Letter for which to retrieve the hint
     * @return Hint associated with the given letter, or NONE
     * if there is no hint associated with it.
     *
     * @see Hint
     * */
    fun getHint(letter: String): Hint {
        return hints[letter] ?: Hint.NONE
    }

}