package dev.mcarr.words.data.ui.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.unit.dp
import dev.mcarr.words.ui.screens.HomeScreen
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class HomeScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testHomeScreen(){

        val padding = PaddingValues(0.dp)
        val howToPlayText = "How To Play"
        var howToPlayClicked = 0
        val playNowText = "Play Now!"
        var playNowClicked = 0

        composeTestRule.setContent {
            HomeScreen(
                paddingValues = padding,
                howToPlay = {
                    howToPlayClicked++
                },
                playNow = {
                    playNowClicked++
                }
            )
        }

        composeTestRule.onNodeWithText(howToPlayText).run {
            assertExists()
            assertIsDisplayed()
            assertTextEquals(howToPlayText)

            assertEquals(0, howToPlayClicked)

            performClick()
            assertEquals(1, howToPlayClicked)

            performClick()
            assertEquals(2, howToPlayClicked)
        }

        composeTestRule.onNodeWithText(playNowText).run {
            assertExists()
            assertIsDisplayed()
            assertTextEquals(playNowText)

            assertEquals(0, playNowClicked)

            performClick()
            assertEquals(1, playNowClicked)

            performClick()
            assertEquals(2, playNowClicked)
        }

    }

}