package dev.mcarr.words.ui.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.unit.dp
import dev.mcarr.words.ui.screens.ImportJsonFileScreen
import org.junit.Test

class ImportJsonFileScreenTest : AbstractScreenTest() {

    @Test
    fun test(){

        setContent {
            ImportJsonFileScreen(
                paddingValues = PaddingValues(0.dp),
                processFile = {}
            )
        }

        testHeading("JSON File")

    }
}