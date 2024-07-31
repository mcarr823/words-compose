package dev.mcarr.words.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.mcarr.words.ui.components.ColoredTextButton
import dev.mcarr.words.ui.components.Heading
import dev.mcarr.words.ui.components.PaddedText
import dev.mcarr.words.ui.components.PreviewComponent
import dev.mcarr.words.ui.theme.Blue

/**
 * JSON file import screen.
 *
 * Describes the expected format of a JSON file for this app
 * to import, and provides an Import button to select the file.
 * */
@Composable
fun ImportJsonFileScreen() {

    Column {
        Heading(text = "JSON File")

        PaddedText(text = "JSON files are expected to be in either of the following formats:")

        PaddedText(text = """
            {"words":["a", "few", "example", "words"]}
        """.trimIndent())
        PaddedText(text = "or")
        PaddedText(text = """
            ["a", "few", "example", "words"]
        """.trimIndent())

        PaddedText(text = "The word list should be an array of strings, optionally within the \"words\" attribute of an object.")

        Row(
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            horizontalArrangement = Arrangement.End
        ) {
            ColoredTextButton(backgroundColor = Blue, textColor = Color.White, text = "Import") {
                // TODO file access permission or file picker
            }
        }
    }

}

@Preview
@Composable
fun PreviewImportJsonFileScreen(){
    PreviewComponent {
        ImportJsonFileScreen()
    }
}