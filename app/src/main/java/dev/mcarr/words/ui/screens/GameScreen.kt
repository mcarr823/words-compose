package dev.mcarr.words.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
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

    val currentGuess by remember {
        mutableStateOf(guessModel.guess)
    }

    val guesses by remember {
        mutableStateOf(guessModel.previousGuesses)
    }

    ConstraintLayout(
        modifier = Modifier.fillMaxSize().padding(paddingValues)
    ){

        val (refPreviousGuesses, refCurrentGuess, refKeyboard) = createRefs()

        LazyColumn(
            modifier = Modifier.fillMaxWidth()
                .constrainAs(refPreviousGuesses){
                    top.linkTo(parent.top)
                    bottom.linkTo(refCurrentGuess.top)
                }
        ) {

            items(model.guessesToShow){ i ->
                if (i < guesses.size){
                    WordComponent(guesses[i])
                }else{
                    WordComponent("")
                }
            }

        }

        WordComponent(
            currentGuess,
            modifier = Modifier.constrainAs(refCurrentGuess){
                bottom.linkTo(refKeyboard.top)
            }
        )

        KeyboardComponent(
            guessModel,
            modifier = Modifier.constrainAs(refKeyboard){
                bottom.linkTo(parent.bottom)
            }
        )

    }

    LaunchedEffect(Unit) {
        // TODO hard-coded for testing. Change this
        guessModel.start("WORDS")
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