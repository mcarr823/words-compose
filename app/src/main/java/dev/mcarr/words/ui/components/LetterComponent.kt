package dev.mcarr.words.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardColors
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.mcarr.words.enums.Hint
import dev.mcarr.words.ui.theme.Typography
import dev.mcarr.words.ui.theme.Blue
import dev.mcarr.words.ui.theme.Orange
import dev.mcarr.words.ui.theme.Green
import dev.mcarr.words.ui.theme.Gray

/**
 * Displays a single letter of a guessed word.
 *
 * A series of LetterComponents should be displayed
 * side by side to spell out a word, such as in a
 * WordComponent.
 *
 * @param letter The letter to display
 * @param hint Color hinting to apply
 * @see WordComponent
 * */
@Composable
fun LetterComponent(
    letter: String,
    hint: Hint
) {

    val textColor = if (hint == Hint.NONE) Color.Black else Color.White
    val bgColor = when(hint) {
        Hint.CORRECT -> Green
        Hint.CORRECT_ANOTHER -> Blue
        Hint.INCORRECT -> Gray
        Hint.WRONG_PLACEMENT -> Orange
        else -> Color.White
    }

    OutlinedCard(
        modifier = Modifier.padding(2.dp).testTag("LetterComponentCard"),
        colors = CardColors(
            containerColor = bgColor,
            contentColor = textColor,
            disabledContentColor = Color.Black,
            disabledContainerColor = Color.White
        )
    ) {
        Text(
            text = letter,
            style = Typography.displayLarge,
            modifier = Modifier.padding(
                vertical = 8.dp,
                horizontal = 16.dp
            ).testTag("LetterComponentText"),
            fontFamily = FontFamily.Monospace
        )
    }

}

@Preview
@Composable
fun PreviewLetterComponent(){
    PreviewComponent {
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            LetterComponent(letter = "A", hint = Hint.NONE)
            LetterComponent(letter = "B", hint = Hint.CORRECT)
            LetterComponent(letter = "C", hint = Hint.INCORRECT)
            LetterComponent(letter = "D", hint = Hint.CORRECT_ANOTHER)
            LetterComponent(letter = "E", hint = Hint.WRONG_PLACEMENT)
        }
    }
}