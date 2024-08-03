package dev.mcarr.words.ui.components

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import dev.mcarr.words.ui.components.Heading
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class HeadingTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testHeading(){

        val text = "Test text"

        composeTestRule.setContent {
            Heading(text = text)
        }

        composeTestRule.onNodeWithText(text).run {
            assertExists()
            assertIsDisplayed()
            assertTextEquals(text)
        }

    }

}