package dev.mcarr.words.data.ui.components

import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import dev.mcarr.words.ui.components.NavCard
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class NavCardTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testNavCard(){

        val text = "Test text"
        var clicked = 0

        composeTestRule.setContent {
            NavCard(text = text) {
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