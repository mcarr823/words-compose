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
 * @param goToLocalFileScreen Callback to invoke when Local File button is clicked
 * @param goToOnlineSourceScreen Callback to invoke when Online Source button is clicked
 * */
@Composable
fun SetupScreen(
    goToLocalFileScreen: () -> Unit,
    goToOnlineSourceScreen: () -> Unit
) {

    Column {
        Heading(text = "Setup")

        PaddedText(text = "This game needs to be supplied with a list of words in order to be played.")
        PaddedText(text = "If you upload your own list of words, you can play this game offline.")
        PaddedText(text = "If you choose an online source, you will need to be online to play.")

        NavCard(text = "Local File", onClick = goToLocalFileScreen)
        NavCard(text = "Online Source", onClick = goToOnlineSourceScreen)
    }

}

@Preview
@Composable
fun PreviewSetupScreen(){
    PreviewComponent {
        SetupScreen(
            goToLocalFileScreen = {},
            goToOnlineSourceScreen = {}
        )
    }
}