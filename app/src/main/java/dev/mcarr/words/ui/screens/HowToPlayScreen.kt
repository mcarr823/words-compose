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
import dev.mcarr.words.ui.components.ColoredBulletPoint
import dev.mcarr.words.ui.components.ColoredTextButton
import dev.mcarr.words.ui.components.Heading
import dev.mcarr.words.ui.components.PaddedText
import dev.mcarr.words.ui.components.PreviewComponent
import dev.mcarr.words.ui.theme.Blue
import dev.mcarr.words.ui.theme.Gray
import dev.mcarr.words.ui.theme.Green
import dev.mcarr.words.ui.theme.Orange

/**
 * Info screen.
 *
 * Displays instructions on how to play the game.
 *
 * @param paddingValues Padding around the screen components
 * */
@Composable
fun HowToPlayScreen(
    paddingValues: PaddingValues
) {

    Column(
        modifier = Modifier.padding(paddingValues)
    ) {
        Heading(text = "How To Play")

        PaddedText(text = "A random word will be selected for you to guess.")
        PaddedText(text = "To guess what it is, use the on-screen keyboard to type a word.")
        PaddedText(text = "The letters will appear in the bottom row of boxes on the screen.")
        PaddedText(text = "Once you've filled the bottom row of boxes, click on the Tick button to submit your guess.")
        PaddedText(text = "Every time you make a guess, letters will come up in the following colors:")

        ColoredBulletPoint(
            coloredText = "Green",
            regularText = "Correct guess",
            textColor = Green
        )

        ColoredBulletPoint(
            coloredText = "Blue",
            regularText = "Correct guess, and that letter also appears somewhere else in the word",
            textColor = Blue
        )

        ColoredBulletPoint(
            coloredText = "Yellow",
            regularText = "Incorrect guess, but that letter does appear somewhere in the word",
            textColor = Orange
        )

        ColoredBulletPoint(
            coloredText = "Grey",
            regularText = "Incorrect guess",
            textColor = Gray
        )
    }

}

@Preview
@Composable
fun PreviewHowToPlayScreen(){
    PreviewComponent {
        HowToPlayScreen(
            paddingValues = PaddingValues(0.dp)
        )
    }
}