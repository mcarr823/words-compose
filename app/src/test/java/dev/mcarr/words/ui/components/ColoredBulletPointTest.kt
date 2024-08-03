package dev.mcarr.words.ui.components

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import dev.mcarr.words.ui.components.ColoredBulletPoint
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class ColoredBulletPointTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testColoredBulletPoint(){

        val coloredText = "Test"
        val regularText = "text"
        val fgColor = Color.Green

        composeTestRule.setContent {
            ColoredBulletPoint(
                coloredText = coloredText,
                regularText = regularText,
                textColor = fgColor
            )
        }

        val combinedText = coloredText+regularText
        composeTestRule.onNodeWithText(combinedText).run {
            assertDoesNotExist()
        }

        val formattedText = "  •  $coloredText: $regularText"
        composeTestRule.onNodeWithText(formattedText).run {
            assertExists()
            assertIsDisplayed()
            assertTextEquals(formattedText)
        }

    }

}