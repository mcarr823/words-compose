package dev.mcarr.words.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardColors
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.mcarr.words.classes.HintedLetter
import dev.mcarr.words.enums.Hint
import dev.mcarr.words.ui.theme.Typography
import dev.mcarr.words.ui.theme.Blue
import dev.mcarr.words.ui.theme.Orange
import dev.mcarr.words.ui.theme.Green
import dev.mcarr.words.ui.theme.Gray
import dev.mcarr.words.ui.theme.LetterComponentTextStyle

/**
 * Displays a single letter of a guessed word.
 *
 * A series of LetterComponents should be displayed
 * side by side to spell out a word, such as in a
 * WordComponent.
 *
 * @param hintedLetter The letter to display, including the
 * color hinting to apply to the component
 * @see WordComponent
 * */
@Composable
fun LetterComponent(
    hintedLetter: HintedLetter
) {

    val textColor = hintedLetter.getTextColor()
    val bgColor = hintedLetter.getBgColor()

    val letter = remember {
        if (hintedLetter.letter.isBlank())
            " "
        else
            hintedLetter.letter
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
            style = LetterComponentTextStyle,
            modifier = Modifier.padding(
                vertical = 8.dp,
                horizontal = 16.dp
            ).testTag("LetterComponentText")
        )
    }

}

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
    text: String,
    hint: Hint
) {
    val hintedLetter = HintedLetter(text, hint)
    LetterComponent(hintedLetter)
}

@Preview
@Composable
fun PreviewLetterComponent(){
    PreviewComponent {
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            LetterComponent(HintedLetter("", Hint.NONE))
            LetterComponent(HintedLetter("B", Hint.CORRECT))
            LetterComponent(HintedLetter("C", Hint.INCORRECT))
            LetterComponent(HintedLetter("D", Hint.CORRECT_ANOTHER))
            LetterComponent(HintedLetter("E", Hint.WRONG_PLACEMENT))
        }
    }
}

@Preview
@Composable
fun PreviewLetterComponentDark(){
    PreviewComponent(darkMode = true) {
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            LetterComponent(HintedLetter("", Hint.NONE))
            LetterComponent(HintedLetter("B", Hint.CORRECT))
            LetterComponent(HintedLetter("C", Hint.INCORRECT))
            LetterComponent(HintedLetter("D", Hint.CORRECT_ANOTHER))
            LetterComponent(HintedLetter("E", Hint.WRONG_PLACEMENT))
        }
    }
}