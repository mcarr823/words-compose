package dev.mcarr.words.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.mcarr.words.ui.components.Heading
import dev.mcarr.words.ui.components.NavCard
import dev.mcarr.words.ui.components.PaddedText
import dev.mcarr.words.ui.components.PreviewComponent

/**
 * Local file import screen.
 *
 * Shows a list of different file formats which this app knows
 * how to import.
 *
 * @param paddingValues Padding around the screen components
 * @param goToTxtFileImportScreen Callback to invoke when TXT format is selected
 * @param goToCsvFileImportScreen Callback to invoke when CSV format is selected
 * @param goToJsonFileImportScreen Callback to invoke when JSON format is selected
 * @param goToXmlFileImportScreen Callback to invoke when XML format is selected
 * */
@Composable
fun ImportLocalFileScreen(
    paddingValues: PaddingValues,
    goToTxtFileImportScreen: () -> Unit,
    goToCsvFileImportScreen: () -> Unit,
    goToJsonFileImportScreen: () -> Unit,
    goToXmlFileImportScreen: () -> Unit,
) {

    Column(
        modifier = Modifier.padding(paddingValues)
    ) {
        Heading(text = "Local File")

        PaddedText(text = "Words supports importing dictionaries in TXT, CSV, JSON or XML format.")
        PaddedText(text = "For more information on the expected format of a dictionary, choose one of those four file types.")

        NavCard(text = "TXT file (.txt)", onClick = goToTxtFileImportScreen)
        NavCard(text = "CSV file (.csv)", onClick = goToCsvFileImportScreen)
        NavCard(text = "JSON file (.json)", onClick = goToJsonFileImportScreen)
        NavCard(text = "XML file (.xml)", onClick = goToXmlFileImportScreen)
    }

}

@Preview
@Composable
fun PreviewImportLocalFileScreen(){
    PreviewComponent {
        ImportLocalFileScreen(
            paddingValues = PaddingValues(0.dp),
            goToJsonFileImportScreen = {},
            goToCsvFileImportScreen = {},
            goToTxtFileImportScreen = {},
            goToXmlFileImportScreen = {}
        )
    }
}