package dev.mcarr.words.ui.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.unit.dp
import dev.mcarr.words.ui.screens.ImportCsvFileScreen
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

class ImportCsvFileScreenTest : AbstractScreenTest() {

    @Test
    fun test(){

        setContent {
            ImportCsvFileScreen(
                paddingValues = PaddingValues(0.dp),
                processFile = {}
            )
        }

        testHeading("CSV File")

    }
}