package dev.mcarr.words.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

/**
 * Displays a guessed word on the screen.
 *
 * Contains a row of LetterComponents and displays
 * them side by side to spell out a word.
 *
 * @param word The word to display
 * @see LetterComponent
 * */
@Composable
fun WordComponent(
    word: String,
    targetLength: Int,
) {

    val letters = remember {
        word.padEnd(targetLength, ' ').chunked(1)
    }

    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        letters.forEach {
            LetterComponent(letter = it)
        }
    }

}

@Preview
@Composable
fun PreviewWordComponent(){
    val targetLength = 5
    PreviewComponent {
        Column {
            WordComponent(word = "ABCDE", targetLength = targetLength)
            WordComponent(word = "FGHIJ", targetLength = targetLength)
            WordComponent(word = "KLMNO", targetLength = targetLength)
            WordComponent(word = "PQRST", targetLength = targetLength)
            WordComponent(word = "UVWXY", targetLength = targetLength)
            WordComponent(word = "Z", targetLength = targetLength)
        }
    }
}