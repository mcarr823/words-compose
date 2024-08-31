package dev.mcarr.words.ui.screens

import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.mcarr.words.ui.components.ColoredTextButton
import dev.mcarr.words.ui.components.FilePickerButton
import dev.mcarr.words.ui.components.Heading
import dev.mcarr.words.ui.components.PaddedText
import dev.mcarr.words.ui.components.PreviewComponent
import dev.mcarr.words.ui.theme.Blue

/**
 * CSV file import screen.
 *
 * Describes the expected format of a CSV file for this app
 * to import, and provides an Import button to select the file.
 *
 * @param paddingValues Padding around the screen components
 * */
@Composable
fun ImportCsvFileScreen(
    paddingValues: PaddingValues,
    processFile: (Uri) -> Unit
) {

    Column(
        modifier = Modifier.padding(paddingValues)
    ) {
        Heading(text = "CSV File")

        PaddedText(text = "CSV files are expected to be in the following format:")

        PaddedText(text = """
            a,1
            few,3
            example,7
            words,5
        """.trimIndent())

        PaddedText(text = "A single word is expected on each line.")
        PaddedText(text = "Optionally, the word length may be supplied as a second argument.")

        Row(
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            horizontalArrangement = Arrangement.End
        ) {
            FilePickerButton(mimetype = "text/csv", callback = processFile)
        }
    }

}

@Preview
@Composable
fun PreviewImportCsvFileScreen(){
    PreviewComponent {
        ImportCsvFileScreen(
            paddingValues = PaddingValues(0.dp),
            processFile = {}
        )
    }
}