package dev.mcarr.words.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.mcarr.words.api.http.HerokuHttpClient
import dev.mcarr.words.data.entities.Word
import dev.mcarr.words.data.repos.WordRepository
import dev.mcarr.words.enums.WordSource
import dev.mcarr.words.ui.components.ColoredTextButton
import dev.mcarr.words.ui.components.Heading
import dev.mcarr.words.ui.components.PaddedText
import dev.mcarr.words.ui.components.PreviewComponent
import dev.mcarr.words.ui.theme.Blue
import dev.mcarr.words.viewmodels.GameLoadScreenViewModel
import dev.mcarr.words.viewmodels.GuessViewModel

/**
 * Loading screen to display prior to starting the game.
 *
 * This screen displays a generic loading message while
 * the app attempts to retrieve a random word and setup
 * the game before moving onto the GameScreen component.
 *
 * @param paddingValues Padding around the screen components
 * @param model Viewmodel containing the screen variables
 * @param guessViewModel Viewmodel containing the state of the
 * game which we are preparing
 * @param playNow Callback to invoke when the user taps on
 * the Play button after the viewmodel is initialized
 * @param goBack Callback to invoke when the user taps on
 * the Back button after initialization fails
 *
 * @see GameLoadScreenViewModel
 * @see GuessViewModel
 * */
@Composable
fun GameLoadScreen(
    paddingValues: PaddingValues,
    model: GameLoadScreenViewModel,
    guessViewModel: GuessViewModel,
    playNow: () -> Unit,
    goBack: () -> Unit
) {

    val context = LocalContext.current

    var error by remember {
        model.error
    }

    var success by remember {
        model.success
    }

    var downloadAttempt by remember {
        model.downloadAttempt
    }

    Column(
        modifier = Modifier.padding(paddingValues),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        if (success) {

            Heading(text = "Success")
            Spacer(modifier = Modifier.height(16.dp))
            PaddedText(text = "You are now ready to play.")
            Spacer(modifier = Modifier.height(16.dp))
            ColoredTextButton(
                backgroundColor = Blue,
                textColor = Color.White,
                text = "Play Now!",
                onClick = playNow
            )

        } else if (error) {

            Heading(text = "Error")
            Spacer(modifier = Modifier.height(16.dp))
            PaddedText(text = "Failed to load word.")
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            ) {
                ColoredTextButton(
                    backgroundColor = Blue,
                    textColor = Color.White,
                    text = "Try Again",
                    onClick = {
                        error = false
                        downloadAttempt++
                    }
                )
                ColoredTextButton(
                    backgroundColor = Blue,
                    textColor = Color.White,
                    text = "Go Back",
                    onClick = goBack
                )
            }

        } else {
            Heading(text = "Loading")
            Spacer(modifier = Modifier.height(16.dp))
            if (model.source.value == WordSource.DATABASE){
                PaddedText(text = "Performing first-time setup...")
            }else{
                PaddedText(text = "Preparing your game...")
            }
            Spacer(modifier = Modifier.height(16.dp))
            CircularProgressIndicator()
        }
    }

    LaunchedEffect(key1 = downloadAttempt) {

        try{

            // Run firstTimeInit every time the screen displays.
            // It won't actually do anything unless it needs to,
            // so it's safe to run multiple times.
            model.firstTimeInit(context)

            val word = model.getWord(context) ?: throw Exception("Failed to load word")
            if (word.isBlank()) throw Exception("Invalid word")
            guessViewModel.start(word)
            success = true

            // Try to redirect to the game screen automatically.
            // If this fails for some reason, the player can press
            // Play Now instead.
            playNow()

        }catch (e: Exception){
            error = true
        }

    }

}

@Preview
@Composable
fun PreviewGameLoadScreenDownloading(){

    val model = GameLoadScreenViewModel()
    model.source.value = WordSource.TEST
    val guessViewModel = GuessViewModel()

    PreviewComponent {
        GameLoadScreen(
            paddingValues = PaddingValues(0.dp),
            model = model,
            guessViewModel = guessViewModel,
            playNow = {},
            goBack = {}
        )
    }

}

@Preview
@Composable
fun PreviewGameLoadScreenError(){

    val model = GameLoadScreenViewModel()
    model.source.value = WordSource.TEST
    model.error.value = true

    val guessViewModel = GuessViewModel()

    PreviewComponent {
        GameLoadScreen(
            paddingValues = PaddingValues(0.dp),
            model = model,
            guessViewModel = guessViewModel,
            playNow = {},
            goBack = {}
        )
    }

}