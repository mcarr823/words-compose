package dev.mcarr.words.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import dev.mcarr.words.ui.components.Heading
import dev.mcarr.words.ui.components.NavCard
import dev.mcarr.words.ui.components.PaddedText
import dev.mcarr.words.ui.components.PreviewComponent

/**
 * Setup screen.
 *
 * This screen leads to either the Local File import screen,
 * or the Remote Source selection screen, depending on how
 * the user wants to retrieve words when playing the game.
 *
 * @param goToLocalFileScreen Callback to invoke when the Upload button is clicked
 * @param goToDownloadSourceScreen Callback to invoke when the Download button is clicked
 * @param goToOnlineSourceScreen Callback to invoke when Online Source button is clicked
 * */
@Composable
fun SetupScreen(
    goToLocalFileScreen: () -> Unit,
    goToDownloadSourceScreen: () -> Unit,
    goToOnlineSourceScreen: () -> Unit
) {

    Column {
        Heading(text = "Setup")

        PaddedText(text = "This game needs to be supplied with a list of words in order to be played.")
        PaddedText(text = "There are three ways you can do this:")
        PaddedText(text = "1. upload your own list of words")
        PaddedText(text = "2. download a list of words")
        PaddedText(text = "3. query an online API whenever you play")

        NavCard(text = "Upload Your Own List", onClick = goToLocalFileScreen)
        NavCard(text = "Download A Word List", onClick = goToDownloadSourceScreen)
        NavCard(text = "Use An Online Word List API", onClick = goToOnlineSourceScreen)
    }

}

@Preview
@Composable
fun PreviewSetupScreen(){
    PreviewComponent {
        SetupScreen(
            goToLocalFileScreen = {},
            goToDownloadSourceScreen = {},
            goToOnlineSourceScreen = {}
        )
    }
}