package dev.mcarr.words.ui.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.unit.dp
import dev.mcarr.words.ui.screens.ImportXmlFileScreen
import org.junit.Test

class ImportXmlFileScreenTest : AbstractScreenTest() {

    @Test
    fun test(){

        setContent {
            ImportXmlFileScreen(
                paddingValues = PaddingValues(0.dp),
                processFile = {}
            )
        }

        testHeading("XML File")

    }
}