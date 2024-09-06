package dev.mcarr.words.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dev.mcarr.words.classes.HintedString
import dev.mcarr.words.enums.Hint

/**
 * Viewmodel used by the GameScreen component.
 *
 * @see dev.mcarr.words.ui.screens.GameScreen
 * */
class GameScreenViewModel : ViewModel() {

    /**
     * Length of the word we're trying to guess
     * */
    var targetLength = 5

    /**
     * Maximum number of previous guesses to show
     * on the game screen
     * */
    private val guessesToShow = 4

    /**
     * The player's current guess as to what the word
     * might be
     * */
    var currentGuess = ""

    var wordToGuess = ""
    var canSubmit = false
    var hints = HashMap<String, Hint>()

    /**
     * Arraylist holding the player's previous guesses.
     * Defaults to a list of `guessesToShow` blank strings.
     * Each string is padded to a length of `targetLength`.
     * */
    val guesses = ArrayList<HintedString>()

    /**
     * Adds a new guess to the list.
     * If we've already hit the limit of guesses we can show,
     * then it removes the first item in the list before adding
     * a new one.
     * */
    fun addGuess(word: HintedString){
        if (guesses.size == guessesToShow){
            guesses.removeAt(0)
        }
        guesses.add(word)
    }
    fun addGuess(word: String){
        val hintedString = HintedString(word, wordToGuess)
        addGuess(hintedString)
    }

    /**
     * Tells us whether the current guess is long enough to be
     * submitted as a legitimate guess.
     *
     * @return True if the current guess is the correct length
     * */
    fun canSubmit(): Boolean {
        return currentGuess.length == targetLength
    }

    /**
     * Add `currentGuess` to the list of previous guesses and
     * clear it afterwards.
     * */
    fun submit(){
        addGuess(currentGuess)
        currentGuess = ""
        canSubmit = false
        // TODO reassess hints whenever a submit occurs
    }

    fun pressKey(letter: String){

        if (currentGuess.length == targetLength){
            return
        }

        currentGuess += letter

        if (currentGuess.length == targetLength){
            canSubmit = true
        }
    }

    fun backspace(){
        val len = currentGuess.length
        if (len > 0) currentGuess = currentGuess.take(len - 1)
        canSubmit = false
    }

    fun start(word: String){
        wordToGuess = word
        guesses.clear()
        guesses.addAll(List(guessesToShow){ HintedString("", wordToGuess) })
        currentGuess = ""
        canSubmit = false
        targetLength = word.length
    }

    fun getHint(letter: String): Hint {
        return hints.get(letter) ?: Hint.NONE
    }

}