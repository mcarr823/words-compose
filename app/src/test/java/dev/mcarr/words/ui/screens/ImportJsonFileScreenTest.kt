package dev.mcarr.words.ui.screens

import dev.mcarr.words.ui.screens.ImportJsonFileScreen
import org.junit.Test

class ImportJsonFileScreenTest : AbstractScreenTest() {

    @Test
    fun test(){

        setContent {
            ImportJsonFileScreen()
        }

        testHeading("JSON File")

    }
}