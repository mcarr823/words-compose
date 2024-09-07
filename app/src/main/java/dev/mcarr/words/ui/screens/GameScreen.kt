package dev.mcarr.words.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.mcarr.words.classes.HintedString
import dev.mcarr.words.ui.components.GuessTextField
import dev.mcarr.words.ui.components.KeyboardComponent
import dev.mcarr.words.ui.components.PreviewComponent
import dev.mcarr.words.ui.components.WordComponent
import dev.mcarr.words.viewmodels.GameScreenViewModel
import dev.mcarr.words.viewmodels.GuessViewModel

/**
 * Screen on which the Words game is actually played.
 *
 * @param paddingValues Padding around the screen components
 * @param model Viewmodel containing the settings and
 * current guesses.
 *
 * @see GameScreenViewModel
 * */
@Composable
fun GameScreen(
    paddingValues: PaddingValues,
    model: GameScreenViewModel,
    guessModel: GuessViewModel
) {

    // val kc = LocalSoftwareKeyboardController.current

    var currentGuess by remember {
        mutableStateOf(guessModel.guess)
    }

    val guesses = remember {
        guessModel.previousGuesses
        // TODO chunk with model.max number of guesses
    }

    Column(
        modifier = Modifier.padding(paddingValues)
    ) {

        guesses.forEach {
            WordComponent(it)
        }
        WordComponent(currentGuess)

        KeyboardComponent(guessModel)
    }

}

@Preview
@Composable
fun PreviewGameScreen(){

    val model = GameScreenViewModel()
    val guessModel = GuessViewModel()
    guessModel.start("WORDS")

    val guesses = listOf("ABCDE", "FGHIJ", "KLMNO", "PQRST")
    guesses.forEach {
        guessModel.guess = it
        guessModel.submit()
    }

    guessModel.guess = "UVWX "
    PreviewComponent {
        GameScreen(
            paddingValues = PaddingValues(0.dp),
            model = model,
            guessModel = guessModel
        )
    }
}