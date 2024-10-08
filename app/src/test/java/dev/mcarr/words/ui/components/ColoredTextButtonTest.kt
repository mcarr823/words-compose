package dev.mcarr.words.ui.components

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.performClick
import dev.mcarr.words.ui.AbstractUiUnitTest
import org.junit.Assert.assertEquals
import org.junit.Test

class ColoredTextButtonTest : AbstractUiUnitTest() {

    @Test
    fun testColoredTextButton(){

        val text = "Test text"
        val bgColor = Color.Black
        val fgColor = Color.White
        var clicked = 0

        setContent {
            ColoredTextButton(
                backgroundColor = bgColor,
                textColor = fgColor,
                text = text
            ) {
                clicked++
            }
        }

        onNodeWithText(text){
            assertExists()
            assertIsDisplayed()
            assertTextEquals(text)
            assertHasClickAction()
            assertIsEnabled()

            assertEquals(0, clicked)

            performClick()
            assertEquals(1, clicked)

            performClick()
            assertEquals(2, clicked)
        }

    }

}