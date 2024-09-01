package dev.mcarr.words.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.mcarr.words.enums.Hint

@Composable
fun KeyboardComponent(
    // TODO viewmodel with hints
) {

    val pressKey: (letter: String) -> Unit = { letter ->
        // TODO
    }
    val backspace = {
        // TODO
    }
    val submit = {
        // TODO
    }

    val row1 = "QWERTYUIOP".split("").filter { it.isNotEmpty() }
    val row2 = "ASDFGHJKL".split("").filter { it.isNotEmpty() }
    val row3 = "ZXCVBNM".split("").filter { it.isNotEmpty() }

    BoxWithConstraints(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {

        val width = (this.maxWidth / 12)
        val hint = Hint.NONE

        Column(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(2.dp, Alignment.CenterHorizontally),
            ) {
                row1.forEach { letter ->
                    KeyComponent(letter, hint, width, pressKey)
                }
                KeyComponent(icon = Icons.AutoMirrored.Default.ArrowBack, width = width, hint = hint, onClick = backspace)
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(2.dp, Alignment.CenterHorizontally),
            ) {
                row2.forEach { letter ->
                    KeyComponent(letter, hint, width, pressKey)
                }
                KeyComponent(icon = Icons.Default.Done, width = width, hint = hint, onClick = submit)
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(2.dp, Alignment.CenterHorizontally),
            ) {
                row3.forEach { letter ->
                    KeyComponent(letter, hint, width, pressKey)
                }
            }
        }
    }

}

@Preview
@Composable
fun PreviewKeyboardComponent(){
    PreviewComponent {
        KeyboardComponent()
    }
}