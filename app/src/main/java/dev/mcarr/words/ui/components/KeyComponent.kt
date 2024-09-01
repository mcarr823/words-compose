package dev.mcarr.words.ui.components

import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.CardColors
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.mcarr.words.enums.Hint
import dev.mcarr.words.ui.theme.Blue
import dev.mcarr.words.ui.theme.Gray
import dev.mcarr.words.ui.theme.Green
import dev.mcarr.words.ui.theme.KeyComponentTextStyle
import dev.mcarr.words.ui.theme.LetterComponentTextStyle
import dev.mcarr.words.ui.theme.Orange

@Composable
fun KeyComponent(
    letter: String,
    hint: Hint,
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

    OutlinedCard(
        modifier = Modifier
            .padding(2.dp)
            .focusable(false)
            .testTag("KeyComponentCard"),
        colors = CardColors(
            containerColor = bgColor,
            contentColor = textColor,
            disabledContentColor = Color.Black,
            disabledContainerColor = Color.White
        ),
        onClick = onClick
    ) {
        Text(
            text = letter,
            style = KeyComponentTextStyle,
            modifier = Modifier.padding(
                vertical = 4.dp,
                horizontal = 8.dp
            ).testTag("KeyComponentText")
        )
    }

}

@Composable
fun KeyComponent(
    icon: ImageVector,
    hint: Hint,
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

    OutlinedCard(
        modifier = Modifier
            .padding(2.dp)
            .testTag("KeyComponentCard"),
        colors = CardColors(
            containerColor = bgColor,
            contentColor = textColor,
            disabledContentColor = Color.Black,
            disabledContainerColor = Color.White
        ),
        onClick = onClick
    ) {
        Icon(
            icon,
            "",
            //style = KeyComponentTextStyle,
            modifier = Modifier.padding(
                vertical = 1.dp,
                horizontal = 4.dp
            ).testTag("KeyComponentText")
        )
    }

}

@Preview
@Composable
fun PreviewKeyComponent(){
    PreviewComponent {
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            KeyComponent(letter = "Q", hint = Hint.NONE){}
            KeyComponent(letter = "W", hint = Hint.CORRECT){}
            KeyComponent(letter = "E", hint = Hint.INCORRECT){}
            KeyComponent(letter = "R", hint = Hint.CORRECT_ANOTHER){}
            KeyComponent(letter = "T", hint = Hint.WRONG_PLACEMENT){}
            KeyComponent(letter = "Y", hint = Hint.NONE){}
            KeyComponent(letter = "U", hint = Hint.NONE){}
            KeyComponent(letter = "I", hint = Hint.NONE){}
            KeyComponent(letter = "O", hint = Hint.NONE){}
            KeyComponent(letter = "P", hint = Hint.NONE){}
            KeyComponent(icon = Icons.AutoMirrored.Default.ArrowBack, hint = Hint.NONE){}
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            KeyComponent(letter = "A", hint = Hint.NONE){}
            KeyComponent(letter = "S", hint = Hint.NONE){}
            KeyComponent(letter = "D", hint = Hint.NONE){}
            KeyComponent(letter = "F", hint = Hint.NONE){}
            KeyComponent(letter = "G", hint = Hint.NONE){}
            KeyComponent(letter = "H", hint = Hint.NONE){}
            KeyComponent(letter = "J", hint = Hint.NONE){}
            KeyComponent(letter = "K", hint = Hint.NONE){}
            KeyComponent(letter = "L", hint = Hint.NONE){}
            KeyComponent(icon = Icons.Default.Done, hint = Hint.NONE){}
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            KeyComponent(letter = "Z", hint = Hint.NONE){}
            KeyComponent(letter = "X", hint = Hint.NONE){}
            KeyComponent(letter = "C", hint = Hint.NONE){}
            KeyComponent(letter = "V", hint = Hint.NONE){}
            KeyComponent(letter = "B", hint = Hint.NONE){}
            KeyComponent(letter = "N", hint = Hint.NONE){}
            KeyComponent(letter = "M", hint = Hint.NONE){}
        }
    }
}