package dev.mcarr.words.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.mcarr.words.ui.components.GuessTextField
import dev.mcarr.words.ui.components.PreviewComponent
import dev.mcarr.words.ui.components.WordComponent
import dev.mcarr.words.viewmodels.GameScreenViewModel

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
    model: GameScreenViewModel
) {

    // val kc = LocalSoftwareKeyboardController.current

    var currentGuess by remember {
        model.currentGuess
    }

    val guesses = remember {
        model.guesses
    }

    Column(
        modifier = Modifier.padding(paddingValues)
    ) {

        GuessTextField(
            guess = currentGuess,
            setGuess = { currentGuess = it },
            targetLength = model.targetLength
        ) {
            if (model.canSubmit()){
                model.submit()
            }else{
                // Show error? Or just don't proceed?
            }
            // TODO if game over
            // kc?.hide()
        }

        guesses.forEach {
            WordComponent(
                word = it,
                targetLength = model.targetLength
            )
        }
        WordComponent(
            word = currentGuess,
            targetLength = model.targetLength
        )
    }

}

@Preview
@Composable
fun PreviewGameScreen(){

    val model = GameScreenViewModel()
    model.addGuess("ABCDE")
    model.addGuess("FGHIJ")
    model.addGuess("KLMNO")
    model.addGuess("PQRST")
    model.currentGuess.value = "UVWX "
    PreviewComponent {
        GameScreen(
            paddingValues = PaddingValues(0.dp),
            model = model
        )
    }
}