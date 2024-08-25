package dev.mcarr.words.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import dev.mcarr.words.api.http.HerokuHttpClient
import dev.mcarr.words.data.entities.Word
import dev.mcarr.words.data.repos.WordRepository
import dev.mcarr.words.enums.WordSource
import dev.mcarr.words.ui.components.ColoredTextButton
import dev.mcarr.words.ui.components.Heading
import dev.mcarr.words.ui.components.PaddedText
import dev.mcarr.words.ui.components.PreviewComponent
import dev.mcarr.words.ui.theme.Blue
import dev.mcarr.words.viewmodels.DownloadWordListScreenViewModel

/**
 * Word list download screen.
 *
 * Shows a loading progress spinner while the app attempts to
 * download a word list from a remote endpoint.
 *
 * It then shows either a success or error message, depending
 * on the result.
 *
 * @param model Viewmodel containing the screen variables
 * @param playNow Callback to invoke when the user taps on
 * the Play button after a successful download.
 * @param goBack Callback to invoke when the user taps on
 * the Back button after a failed download.
 *
 * @see DownloadWordListScreenViewModel
 * */
@Composable
fun DownloadWordListScreen(
    model: DownloadWordListScreenViewModel,
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

    if (success){

        Column {
            Heading(text = "Success")
            PaddedText(text = "Word list has been downloaded successfully.")
            PaddedText(text = "You are now ready to play.")
            ColoredTextButton(
                backgroundColor = Blue,
                textColor = Color.White,
                text = "Play Now!",
                onClick = playNow
            )
        }

    }else if (error) {

        Column {
            Heading(text = "Error")
            PaddedText(text = "Failed to download word list")
            Row {
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
        }

        // TODO try again button and Back button

    }else{

        Column {
            Heading(text = "Downloading")
            CircularProgressIndicator()
            PaddedText(text = "Downloading word list...")
        }

    }

    LaunchedEffect(key1 = downloadAttempt) {

        if (model.source.value == WordSource.ONLINE_HEROKU){

            val repo = WordRepository.getInstance(context)
            val client = HerokuHttpClient()
            client.downloadAllWords()
                .map { Word(it) }
                .let { repo.insert(it) }

        }else{
            error = true
        }

    }

}

@Preview
@Composable
fun PreviewDownloadWordListScreenDownloading(){

    val model = DownloadWordListScreenViewModel()
    model.source.value = WordSource.ONLINE_HEROKU

    PreviewComponent {
        DownloadWordListScreen(
            model = model,
            playNow = {},
            goBack = {}
        )
    }
}

@Preview
@Composable
fun PreviewDownloadWordListScreenError(){

    val model = DownloadWordListScreenViewModel()
    model.error.value = true

    PreviewComponent {
        DownloadWordListScreen(
            model = model,
            playNow = {},
            goBack = {}
        )
    }
}

@Preview
@Composable
fun PreviewDownloadWordListScreenSuccess(){

    val model = DownloadWordListScreenViewModel()
    model.success.value = true

    PreviewComponent {
        DownloadWordListScreen(
            model = model,
            playNow = {},
            goBack = {}
        )
    }
}