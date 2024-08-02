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
    word: String
) {

    val letters = remember {
        word.chunked(1)
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
    PreviewComponent {
        Column {
            WordComponent(word = "ABCDE")
            WordComponent(word = "FGHIJ")
            WordComponent(word = "KLMNO")
            WordComponent(word = "PQRST")
            WordComponent(word = "UVWXY")
            WordComponent(word = "Z    ")
        }
    }
}