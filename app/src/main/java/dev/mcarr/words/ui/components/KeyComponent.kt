package dev.mcarr.words.ui.components

import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.CardColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import dev.mcarr.words.enums.Hint
import dev.mcarr.words.ui.theme.Blue
import dev.mcarr.words.ui.theme.Gray
import dev.mcarr.words.ui.theme.Green
import dev.mcarr.words.ui.theme.KeyComponentTextStyle
import dev.mcarr.words.ui.theme.LetterComponentTextStyle
import dev.mcarr.words.ui.theme.Orange

/**
 * A single key on the virtual keyboard.
 *
 * Used as part of a KeyboardComponent to display
 * a single key.
 *
 * The key is potentially colored with a hint based
 * on which guesses the player has made.
 *
 * @param letter The alpha key to display
 * @param hint Hint to use for coloring purposes
 * @param width Width of the key in Dp
 * @param pressKey Callback to invoke when tapping on the component
 *
 * @see KeyboardComponent
 * */
@Composable
fun KeyComponent(
    letter: String,
    hint: Hint,
    width: Dp = 50.dp,
    pressKey: (letter: String) -> Unit
) {

    val textColor = if (hint == Hint.NONE) Color.Black else Color.White
    val bgColor = when(hint) {
        Hint.CORRECT -> Green
        Hint.CORRECT_ANOTHER -> Blue
        Hint.INCORRECT -> Gray
        Hint.WRONG_PLACEMENT -> Orange
        else -> Color.White
    }

    OutlinedIconButton(
        modifier = Modifier
            .width(width)
            .testTag("KeyComponentButton"),
        colors = IconButtonColors(
            containerColor = bgColor,
            contentColor = textColor,
            disabledContentColor = Color.Black,
            disabledContainerColor = Color.White
        ),
        shape = RectangleShape,
        onClick = { pressKey(letter) }
    ) {
        Text(
            text = letter,
            style = KeyComponentTextStyle,
            modifier = Modifier
                .testTag("KeyComponentText")
        )
    }

}

/**
 * A single key on the virtual keyboard.
 *
 * Used as part of a KeyboardComponent to display
 * a single key.
 *
 * The key is potentially colored with a hint based
 * on which guesses the player has made.
 *
 * @param icon The image to display on the key
 * @param hint Hint to use for coloring purposes
 * @param width Width of the key in Dp
 * @param onClick Callback to invoke when tapping on the component
 *
 * @see KeyboardComponent
 * */
@Composable
fun KeyComponent(
    icon: ImageVector,
    hint: Hint,
    width: Dp = 50.dp,
    onClick: () -> Unit
) {

    val textColor = if (hint == Hint.NONE) Color.Black else Color.White
    val bgColor = when(hint) {
        Hint.CORRECT -> Green
        Hint.CORRECT_ANOTHER -> Blue
        Hint.INCORRECT -> Gray
        Hint.WRONG_PLACEMENT -> Orange
        else -> Color.White
    }

    OutlinedIconButton(
        modifier = Modifier
            .width(width)
            .testTag("KeyComponentButton"),
        colors = IconButtonColors(
            containerColor = bgColor,
            contentColor = textColor,
            disabledContentColor = Color.Black,
            disabledContainerColor = Color.White
        ),
        shape = RectangleShape,
        onClick = onClick
    ) {
        Icon(
            icon,
            "",
            //style = KeyComponentTextStyle,
            modifier = Modifier
                .testTag("KeyComponentIcon")
        )
    }

}

@Preview
@Composable
fun PreviewKeyComponent(){
    PreviewComponent {
        BoxWithConstraints(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {

            val keyWidth = (this.maxWidth / 12)

            Column(
                modifier = Modifier.fillMaxWidth(),
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(2.dp, Alignment.CenterHorizontally),
                ) {
                    KeyComponent(letter = "Q", width = keyWidth, hint = Hint.NONE) {}
                    KeyComponent(letter = "W", width = keyWidth, hint = Hint.CORRECT) {}
                    KeyComponent(letter = "E", width = keyWidth, hint = Hint.INCORRECT) {}
                    KeyComponent(letter = "R", width = keyWidth, hint = Hint.CORRECT_ANOTHER) {}
                    KeyComponent(letter = "T", width = keyWidth, hint = Hint.WRONG_PLACEMENT) {}
                    KeyComponent(letter = "Y", width = keyWidth, hint = Hint.NONE) {}
                    KeyComponent(letter = "U", width = keyWidth, hint = Hint.NONE) {}
                    KeyComponent(letter = "I", width = keyWidth, hint = Hint.NONE) {}
                    KeyComponent(letter = "O", width = keyWidth, hint = Hint.NONE) {}
                    KeyComponent(letter = "P", width = keyWidth, hint = Hint.NONE) {}
                    KeyComponent(icon = Icons.AutoMirrored.Default.ArrowBack, width = keyWidth, hint = Hint.NONE) {}
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(2.dp, Alignment.CenterHorizontally),
                ) {
                    KeyComponent(letter = "A", width = keyWidth, hint = Hint.NONE){}
                    KeyComponent(letter = "S", width = keyWidth, hint = Hint.NONE){}
                    KeyComponent(letter = "D", width = keyWidth, hint = Hint.NONE){}
                    KeyComponent(letter = "F", width = keyWidth, hint = Hint.NONE){}
                    KeyComponent(letter = "G", width = keyWidth, hint = Hint.NONE){}
                    KeyComponent(letter = "H", width = keyWidth, hint = Hint.NONE){}
                    KeyComponent(letter = "J", width = keyWidth, hint = Hint.NONE){}
                    KeyComponent(letter = "K", width = keyWidth, hint = Hint.NONE){}
                    KeyComponent(letter = "L", width = keyWidth, hint = Hint.NONE){}
                    KeyComponent(icon = Icons.Default.Done, width = keyWidth, hint = Hint.NONE){}
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(2.dp, Alignment.CenterHorizontally),
                ) {
                    KeyComponent(letter = "Z", width = keyWidth, hint = Hint.NONE){}
                    KeyComponent(letter = "X", width = keyWidth, hint = Hint.NONE){}
                    KeyComponent(letter = "C", width = keyWidth, hint = Hint.NONE){}
                    KeyComponent(letter = "V", width = keyWidth, hint = Hint.NONE){}
                    KeyComponent(letter = "B", width = keyWidth, hint = Hint.NONE){}
                    KeyComponent(letter = "N", width = keyWidth, hint = Hint.NONE){}
                    KeyComponent(letter = "M", width = keyWidth, hint = Hint.NONE){}
                }
            }
        }
    }
}