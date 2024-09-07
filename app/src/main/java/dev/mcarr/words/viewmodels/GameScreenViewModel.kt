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
     * Maximum number of previous guesses to show
     * on the game screen
     * */
    private val guessesToShow = 4

}