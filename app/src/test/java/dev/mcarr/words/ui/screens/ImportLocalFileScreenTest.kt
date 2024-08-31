package dev.mcarr.words.ui.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.unit.dp
import dev.mcarr.words.ui.screens.ImportLocalFileScreen
import org.junit.Test

class ImportLocalFileScreenTest : AbstractScreenTest() {

    @Test
    fun test(){

        var txtButtonCount = 0
        var csvButtonCount = 0
        var jsonButtonCount = 0
        var xmlButtonCount = 0

        setContent {
            ImportLocalFileScreen(
                paddingValues = PaddingValues(0.dp),
                goToTxtFileImportScreen = { txtButtonCount++ },
                goToCsvFileImportScreen = { csvButtonCount++ },
                goToJsonFileImportScreen = { jsonButtonCount++ },
                goToXmlFileImportScreen = { xmlButtonCount++ }
            )
        }

        testHeading("Local File")
        testButton("TXT file (.txt)"){ txtButtonCount }
        testButton("CSV file (.csv)"){ csvButtonCount }
        testButton("JSON file (.json)"){ jsonButtonCount }
        testButton("XML file (.xml)"){ xmlButtonCount }

    }
}