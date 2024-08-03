package dev.mcarr.words.ui.components

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import dev.mcarr.words.ui.AbstractUiUnitTest
import org.junit.Test

class LetterComponentTest : AbstractUiUnitTest() {

    @Test
    fun testLetterComponent(){

        setContent {
            LetterComponent(letter = "A")
        }

        onNodeWithTag("LetterComponentText"){
            assertExists()
            assertIsDisplayed()
            assertTextEquals("A")
        }

        onNodeWithTag("LetterComponentCard"){
            assertExists()
            assertIsDisplayed()
            // TODO test colors and such once they're implemented
        }

    }

}