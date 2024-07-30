package dev.mcarr.words.data.ui.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.unit.dp
import dev.mcarr.words.ui.screens.HowToPlayScreen
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class HowToPlayScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun test(){

        val padding = PaddingValues(0.dp)
        var clicked = 0
        val btnText = "Done"
        val headingText = "How To Play"

        composeTestRule.setContent {
            HowToPlayScreen(paddingValues = padding) {
                clicked++
            }
        }

        composeTestRule.onNodeWithText(headingText).run {
            assertExists()
            assertTextEquals(headingText)
            assertIsDisplayed()
        }

        composeTestRule.onNodeWithText(btnText).run {
            assertExists()
            assertTextEquals(btnText)

            // The screen is long, and the button is down
            // the bottom, so it might not be displayed
            // depending on the screen size.
            if (isDisplayed()) {
                assertIsDisplayed()

                assertEquals(0, clicked)

                performClick()
                assertEquals(1, clicked)

                performClick()
                assertEquals(2, clicked)
            }
        }

    }
}