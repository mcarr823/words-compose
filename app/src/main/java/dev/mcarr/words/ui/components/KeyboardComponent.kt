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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.mcarr.words.enums.Hint
import dev.mcarr.words.viewmodels.GameScreenViewModel
import dev.mcarr.words.viewmodels.GuessViewModel

@Composable
fun KeyboardComponent(
    model: GuessViewModel,
    modifier: Modifier = Modifier
) {

    var hints by remember {
        mutableStateOf(model.hints)
    }

    val getHint: (letter: String) -> Hint = { letter ->
        hints[letter] ?: Hint.NONE
    }
    val row1 = "QWERTYUIOP".chunked(1)
    val row2 = "ASDFGHJKL".chunked(1)
    val row3 = "ZXCVBNM".chunked(1)

    BoxWithConstraints(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {

        val width = (this.maxWidth / 12)

        Column(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(2.dp, Alignment.CenterHorizontally),
            ) {
                row1.forEach { letter ->
                    val hint = getHint(letter)
                    KeyComponent(letter, hint, width, model::pressKey)
                }
                KeyComponent(icon = Icons.AutoMirrored.Default.ArrowBack, width = width, hint = Hint.NONE, onClick = model::backspace)
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(2.dp, Alignment.CenterHorizontally),
            ) {
                row2.forEach { letter ->
                    val hint = getHint(letter)
                    KeyComponent(letter, hint, width, model::pressKey)
                }
                KeyComponent(icon = Icons.Default.Done, width = width, hint = Hint.NONE, onClick = model::submit)
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(2.dp, Alignment.CenterHorizontally),
            ) {
                row3.forEach { letter ->
                    val hint = getHint(letter)
                    KeyComponent(letter, hint, width, model::pressKey)
                }
            }
        }
    }

}

@Preview
@Composable
fun PreviewKeyboardComponent(){

    val model = GuessViewModel()
    model.hints["Q"] = Hint.CORRECT
    model.hints["W"] = Hint.CORRECT_ANOTHER
    model.hints["E"] = Hint.WRONG_PLACEMENT
    model.hints["R"] = Hint.INCORRECT

    PreviewComponent {
        KeyboardComponent(
            model = model
        )
    }
}