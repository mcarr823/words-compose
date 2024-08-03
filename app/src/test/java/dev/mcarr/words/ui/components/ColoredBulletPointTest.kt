package dev.mcarr.words.ui.components

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import dev.mcarr.words.ui.AbstractUiUnitTest
import org.junit.Test

class ColoredBulletPointTest : AbstractUiUnitTest() {

    @Test
    fun testColoredBulletPoint(){

        val coloredText = "Test"
        val regularText = "text"
        val fgColor = Color.Green

        setContent {
            ColoredBulletPoint(
                coloredText = coloredText,
                regularText = regularText,
                textColor = fgColor
            )
        }

        val combinedText = coloredText+regularText
        onNodeWithText(combinedText){
            assertDoesNotExist()
        }

        val formattedText = "  •  $coloredText: $regularText"
        onNodeWithText(formattedText){
            assertExists()
            assertIsDisplayed()
            assertTextEquals(formattedText)
        }

    }

}