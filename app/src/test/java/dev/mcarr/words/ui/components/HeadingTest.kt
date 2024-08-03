package dev.mcarr.words.ui.components

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import dev.mcarr.words.ui.AbstractUiUnitTest
import org.junit.Test

class HeadingTest : AbstractUiUnitTest() {

    @Test
    fun testHeading(){

        val text = "Test text"

        setContent {
            Heading(text = text)
        }

        onNodeWithText(text){
            assertExists()
            assertIsDisplayed()
            assertTextEquals(text)
        }

    }

}