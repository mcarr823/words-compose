package dev.mcarr.words.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
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
    disableHints: Boolean = false,
    modifier: Modifier = Modifier
) {

    val letters = remember {
        word.letters
    }

    val hints = remember {
        if (disableHints){
            val size = word.hints.size
            List(size){ Hint.NONE }
        }else {
            word.hints
        }
    }

    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxWidth()
    ) {
        letters.forEachIndexed { index, letter ->
            LetterComponent(letter, hints[index])
        }
    }

}

@Composable
fun WordComponent(
    word: List<String>,
    targetLength: Int,
    modifier: Modifier = Modifier
) {

    LazyRow(
        horizontalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxWidth()
    ) {
        items(targetLength){
            if (it < word.size)
                LetterComponent(word[it], Hint.NONE)
            else
                LetterComponent("", Hint.NONE)
        }
    }
}

@Preview
@Composable
fun PreviewWordComponent(){
    PreviewComponent {
        Column {
            HintedString(displayWord = "ABCDE", targetWord = "WORDS")
            WordComponent(HintedString(displayWord = "ABCDE", targetWord = "WORDS"))
            WordComponent(HintedString(displayWord = "FGHIJ", targetWord = "WORDS"))
            WordComponent(HintedString(displayWord = "KLMNO", targetWord = "WORDS"))
            WordComponent(HintedString(displayWord = "PQRST", targetWord = "WORDS"))
            WordComponent(HintedString(displayWord = "UVWXY", targetWord = "WORDS"))
            WordComponent(word = listOf("Z"), targetLength = 5)
        }
    }
}