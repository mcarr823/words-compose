package dev.mcarr.words.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.mcarr.words.classes.HintedString
import dev.mcarr.words.enums.Hint

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
    word: HintedString,
    modifier: Modifier = Modifier
) {

    val letters = remember {
        word.asList()
    }

    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxWidth()
    ) {
        letters.forEach {
            LetterComponent(it)
        }
    }

}

@Composable
fun WordComponent(
    word: String,
    modifier: Modifier = Modifier
) {
    val hintedString = HintedString(word, "")
    WordComponent(hintedString, modifier)
}

@Preview
@Composable
fun PreviewWordComponent(){
    val targetLength = 5
    PreviewComponent {
        Column {
            HintedString(displayWord = "ABCDE", targetWord = "WORDS")
            WordComponent(HintedString(displayWord = "ABCDE", targetWord = "WORDS"))
            WordComponent(HintedString(displayWord = "FGHIJ", targetWord = "WORDS"))
            WordComponent(HintedString(displayWord = "KLMNO", targetWord = "WORDS"))
            WordComponent(HintedString(displayWord = "PQRST", targetWord = "WORDS"))
            WordComponent(HintedString(displayWord = "UVWXY", targetWord = "WORDS"))
            WordComponent(HintedString(displayWord = "Z    ", targetWord = "WORDS"))
        }
    }
}