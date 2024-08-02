package dev.mcarr.words.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

/**
 * Viewmodel used by the GameScreen component.
 * */
class GameScreenViewModel : ViewModel() {

    /**
     * Length of the word we're trying to guess
     * */
    val targetLength = 5

    /**
     * Maximum number of previous guesses to show
     * on the game screen
     * */
    val guessesToShow = 4

    /**
     * An empty string padded to the target length.
     * This is used to "clear" a WordComponent
     * */
    private val defaultString = "".padEnd(targetLength)

    /**
     * The player's current guess as to what the word
     * might be
     * */
    var currentGuess = mutableStateOf(defaultString)

    /**
     * Arraylist holding the player's previous guesses.
     * Defaults to a list of `guessesToShow` blank strings.
     * Each string is padded to a length of `targetLength`.
     * */
    val guesses = ArrayList<String>(
        List(guessesToShow){ defaultString }
    )

    /**
     * Adds a new guess to the list.
     * If we've already hit the limit of guesses we can show,
     * then it removes the first item in the list before adding
     * a new one.
     * */
    fun addGuess(word: String){
        if (guesses.size == guessesToShow){
            guesses.removeAt(0)
        }
        guesses.add(word)
    }

    /**
     * Tells us whether the current guess is long enough to be
     * submitted as a legitimate guess.
     *
     * @return True if the current guess is the correct length
     * */
    fun canSubmit(): Boolean {
        return currentGuess.value.length == targetLength
    }

    /**
     * Add `currentGuess` to the list of previous guesses and
     * clear it afterwards.
     * */
    fun submit(){
        addGuess(currentGuess.value)
        currentGuess.value = defaultString
    }

}