package dev.mcarr.words.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.mcarr.words.ui.components.ColoredTextButton
import dev.mcarr.words.ui.components.Heading
import dev.mcarr.words.ui.components.PaddedText
import dev.mcarr.words.ui.components.PreviewComponent
import dev.mcarr.words.ui.theme.Blue
import dev.mcarr.words.ui.theme.Orange

/**
 * Main screen of the app.
 *
 * Displays a welcome message and links to other
 * portions of the app.
 *
 * @param paddingValues Padding around the screen components
 * @param howToPlay Callback to invoke when the How To Play button is pressed
 * @param playNow Callback to invoke when the Play Now button is pressed
 * */
@Composable
fun HomeScreen(
    paddingValues: PaddingValues,
    howToPlay: () -> Unit,
    playNow: () -> Unit
) {

    Column(
        modifier = Modifier.padding(paddingValues)
    ) {
        Heading(text = "Welcome to Words!")

        PaddedText(text = "Words is a free, open source, ad-free Wordle clone.")
        PaddedText(text = "Press Play to get started, or How To Play to learn the rules of the game.")
        PaddedText(text = "To report bugs, request features, or learn more about the app's development, tap on About.")

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.padding(8.dp).fillMaxWidth()
        ) {

            ColoredTextButton(
                backgroundColor = Orange,
                textColor = Color.Black,
                text = "How To Play",
                onClick = howToPlay
            )

            ColoredTextButton(
                backgroundColor = Blue,
                textColor = Color.White,
                text = "Play Now!",
                onClick = playNow
            )

        }
    }

}

@Preview
@Composable
fun PreviewHomeScreen() {
    PreviewComponent {
        HomeScreen(
            paddingValues = PaddingValues(0.dp),
            howToPlay = {},
            playNow = {}
        )
    }
}