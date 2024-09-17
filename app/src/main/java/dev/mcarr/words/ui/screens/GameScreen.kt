package dev.mcarr.words.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import dev.mcarr.words.classes.HintedString
import dev.mcarr.words.ui.components.ColoredTextButton
import dev.mcarr.words.ui.components.Heading
import dev.mcarr.words.ui.components.KeyboardComponent
import dev.mcarr.words.ui.components.PaddedText
import dev.mcarr.words.ui.components.PreviewComponent
import dev.mcarr.words.ui.components.WordComponent
import dev.mcarr.words.ui.theme.Blue
import dev.mcarr.words.ui.theme.Gray
import dev.mcarr.words.ui.theme.Green
import dev.mcarr.words.ui.theme.Orange
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
    guessModel: GuessViewModel,
    playAgain: () -> Unit,
    goHome: () -> Unit
) {

    // val kc = LocalSoftwareKeyboardController.current

    var wordToGuess = guessModel.wordToGuess

    var currentGuess by remember {
        mutableStateOf(guessModel.guess)
    }

    var guesses by remember {
        mutableStateOf(guessModel.previousGuesses)
    }

    var victory by remember {
        mutableStateOf(guessModel.victory)
    }

    val guessesToShow = remember {
        guessModel.guessesAllowed - 1
    }

    ConstraintLayout(
        modifier = Modifier.fillMaxSize().padding(paddingValues)
    ){

        val (refPreviousGuesses, refCurrentGuess, refKeyboard) = createRefs()

        LazyColumn(
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier.fillMaxWidth().constrainAs(refPreviousGuesses){
                top.linkTo(parent.top)
                bottom.linkTo(refCurrentGuess.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                height = Dimension.fillToConstraints
            }
        ) {

            items(guessesToShow){ i ->
                if (i < guesses.size){
                    WordComponent(guesses[i])
                }else{
                    WordComponent(listOf(), wordToGuess.length)
                }
            }

        }

        WordComponent(
            currentGuess,
            guessModel.wordToGuess.length,
            modifier = Modifier.constrainAs(refCurrentGuess){
                bottom.linkTo(refKeyboard.top, margin = 16.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )

        KeyboardComponent(
            guessModel,
            modifier = Modifier.constrainAs(refKeyboard){
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )

    }

    if (victory){
        Dialog(onDismissRequest = {}){

            Column {
                Heading("Victory")
                PaddedText("The word was: $wordToGuess")
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.padding(8.dp).fillMaxWidth()
                ) {

                    ColoredTextButton(
                        backgroundColor = Gray,
                        textColor = Color.White,
                        text = "Finish",
                        onClick = goHome
                    )

                    ColoredTextButton(
                        backgroundColor = Blue,
                        textColor = Color.White,
                        text = "Play Again",
                        onClick = playAgain
                    )

                }
            }

        }
    }

}

@Preview
@Composable
fun PreviewGameScreen(){

    val model = GameScreenViewModel()
    val guessModel = GuessViewModel()
    guessModel.start("WORDS")

    val guesses = listOf("ABOUT", "WOUND", "WORDY")
    guesses.forEach { word ->
        word.chunked(1).forEach(guessModel::pressKey)
        guessModel.submit()
    }

    guessModel.guess.addAll("WORDS".chunked(1))
    PreviewComponent {
        GameScreen(
            paddingValues = PaddingValues(0.dp),
            model = model,
            guessModel = guessModel,
            playAgain = {},
            goHome = {}
        )
    }
}