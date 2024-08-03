package dev.mcarr.words.ui.components

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onParent
import androidx.compose.ui.test.performClick
import dev.mcarr.words.ui.components.ColoredTextButton
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class ColoredTextButtonTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testColoredTextButton(){

        val text = "Test text"
        val bgColor = Color.Black
        val fgColor = Color.White
        var clicked = 0

        composeTestRule.setContent {
            ColoredTextButton(
                backgroundColor = bgColor,
                textColor = fgColor,
                text = text
            ) {
                clicked++
            }
        }

        composeTestRule.onNodeWithText(text).run {
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