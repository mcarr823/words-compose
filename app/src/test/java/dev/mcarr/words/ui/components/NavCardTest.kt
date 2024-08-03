package dev.mcarr.words.ui.components

import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.performClick
import dev.mcarr.words.ui.AbstractUiUnitTest
import org.junit.Assert.assertEquals
import org.junit.Test

class NavCardTest : AbstractUiUnitTest() {

    @Test
    fun testNavCard(){

        val text = "Test text"
        var clicked = 0

        setContent {
            NavCard(text = text) {
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