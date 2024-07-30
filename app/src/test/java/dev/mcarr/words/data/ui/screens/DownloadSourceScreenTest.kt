package dev.mcarr.words.data.ui.screens

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import dev.mcarr.words.enums.WordSource
import dev.mcarr.words.ui.screens.DownloadSourceScreen
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class DownloadSourceScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun test(){

        val navCardText = "Heroku"
        var herokuClicked = 0

        composeTestRule.setContent {
            DownloadSourceScreen {
                if (it == WordSource.ONLINE_HEROKU) {
                    herokuClicked++
                }
            }
        }

        composeTestRule.onNodeWithText(navCardText).run {
            assertExists()
            assertIsDisplayed()
            assertTextEquals(navCardText)

            assertEquals(0, herokuClicked)

            performClick()
            assertEquals(1, herokuClicked)

            performClick()
            assertEquals(2, herokuClicked)
        }

    }
}