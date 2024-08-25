package dev.mcarr.words.ui.screens

import android.net.Uri
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
import dev.mcarr.words.ui.components.FilePickerButton
import dev.mcarr.words.ui.components.Heading
import dev.mcarr.words.ui.components.PaddedText
import dev.mcarr.words.ui.components.PreviewComponent
import dev.mcarr.words.ui.theme.Blue

/**
 * XML file import screen.
 *
 * Describes the expected format of a XML file for this app
 * to import, and provides an Import button to select the file.
 * */
@Composable
fun ImportXmlFileScreen(
    processFile: (Uri) -> Unit
) {

    Column {
        Heading(text = "XML File")

        PaddedText(text = "XML files are expected to be in the following format:")

        PaddedText(text = """
            <words>
                <word>a</word>
                <word>few</word>
                <word>example</word>
                <word>words</word>
            </words>
        """.trimIndent())

        PaddedText(text = "The root element is expected to be a \"words\" node.")
        PaddedText(text = "Its children are all expected to be \"word\" nodes.")
        PaddedText(text = "Each word node should have a word between its start and end tags.")

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.End
        ) {
            FilePickerButton(mimetype = "text/xml", callback = processFile)
        }
    }

}

@Preview
@Composable
fun PreviewImportXmlFileScreen(){
    PreviewComponent {
        ImportXmlFileScreen(
            processFile = {}
        )
    }
}