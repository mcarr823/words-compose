package dev.mcarr.words.data.ui.screens

import dev.mcarr.words.ui.screens.ImportXmlFileScreen
import org.junit.Test

class ImportXmlFileScreenTest : AbstractScreenTest() {

    @Test
    fun test(){

        setContent {
            ImportXmlFileScreen()
        }

        testHeading("XML File")

    }
}