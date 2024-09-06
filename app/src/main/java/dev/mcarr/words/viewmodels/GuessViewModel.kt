package dev.mcarr.words.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dev.mcarr.words.enums.Hint

class GuessViewModel : ViewModel() {

    var wordToGuess = ""
    var guess = ""
    var targetLength = 0
    var canSubmit = false
    var previousGuesses = arrayListOf<String>()
    var hints = HashMap<String, Hint>()

    fun pressKey(letter: String){

        if (guess.length == targetLength){
            return
        }

        guess += letter

        if (guess.length == targetLength){
            canSubmit = true
        }
    }

    fun backspace(){
        val len = guess.length
        if (len > 0) guess = guess.take(len - 1)
        canSubmit = false
    }

    fun start(word: String){
        wordToGuess = word
        previousGuesses = ArrayList()
        guess = ""
        canSubmit = false
        targetLength = word.length
    }

    fun submit(){
        guess.chunked(1).filter { it.isNotEmpty() }.forEach { letter ->
            //hints
        }
        previousGuesses.add(guess)
        guess = ""
        canSubmit = false
        // TODO reassess hints whenever a submit occurs
    }

    fun getHint(letter: String): Hint {
        return hints.get(letter) ?: Hint.NONE
    }

}