package dev.mcarr.words.viewmodels

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dev.mcarr.words.classes.HintedLetter
import dev.mcarr.words.classes.HintedString
import dev.mcarr.words.enums.Hint

/**
 * Viewmodel containing all of the data needed to play
 * a game.
 *
 * This viewmodel is used by several different components
 * in order to provide coloring hints, display previous
 * guesses, and to generally facilitate the actual gameplay.
 * */
class GuessViewModel : ViewModel() {

    /**
     * Maximum number of guesses the player is allowed
     * to make.
     * */
    var guessesAllowed = 6

    /**
     * The word which the player needs to guess.
     * */
    var wordToGuess = ""

    /**
     * The player's current guess as to what the word
     * might be.
     * */
    var guess = mutableStateListOf<String>()

    /**
     * Boolean indicating whether the guess is in a
     * state where it can be submitted or not.
     * */
    var canSubmit = false

    /**
     * If true, the player has guessed the word correctly
     * and won the game.
     * */
    var victory = mutableStateOf(false)

    /**
     * If true, the player has reached the maximum number
     * of allowed guesses.
     *
     * Note that gameOver and victory are not mutually exclusive.
     * They can both be true at the same time.
     * */
    var gameOver = mutableStateOf(false)

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
    var hints = mutableStateListOf<HintedLetter>()

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

        if (guess.size == wordToGuess.length){
            return
        }

        guess.add(letter)

        if (guess.size == wordToGuess.length){
            canSubmit = true
        }
    }

    /**
     * Deletes the last character of the current guess.
     * */
    fun backspace(){
        guess.removeLastOrNull()
        canSubmit = false
    }

    /**
     * Starts the game by reinitializing all variables.
     *
     * @param word The word which the player needs to guess
     * for the new game we're about to start.
     * */
    fun start(word: String){
        wordToGuess = word
        previousGuesses.clear()
        guess.clear()
        canSubmit = false
        hints.clear()
        victory.value = false
        gameOver.value = false
    }

    /**
     * Adds the current guess into the previousGuesses list
     * and resets the state of the current guess.
     *
     * Also calculates new hints based on the new guess.
     * */
    fun submit(){

        if (!canSubmit) return

        val guessString = guess.joinToString("")

        victory.value = guessString == wordToGuess
        //println("Comparing $guessString to $wordToGuess: ${victory.value}")

        val newGuess = HintedString(guessString, wordToGuess)

        newGuess.asList().forEach {
            val (letter, hint) = it.asPair()
            val oldHint = getHint(letter)
            if (oldHint != Hint.CORRECT){
                hints.removeIf { it.letter == letter }
                hints.add(it)
            }
        }

        if (victory.value || previousGuesses.size == guessesAllowed - 1){
            gameOver.value = true
            canSubmit = false
        }else{
            previousGuesses.add(newGuess)
            guess.clear()
            canSubmit = false
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
        return hints.find { it.letter == letter }?.hint ?: Hint.NONE
    }

    /**
     * Look up the word which was meant to be guessed.
     *
     * The lookup occurs by opening a web browser, rather
     * than by directly talking to an API, since those usually
     * have limitations on usage.
     *
     * At this point in time the lookup uses Merriam-Webster.
     * eg. https://www.merriam-webster.com/dictionary/test
     *
     * @param c Context used to open the webview
     * */
    fun lookUpWord(c: Context){
        val openURL = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.Builder()
                .scheme("https")
                .authority("www.merriam-webster.com")
                .appendPath("dictionary")
                .appendEncodedPath(wordToGuess)
                .build()
        }
        c.startActivity(openURL)
    }

}