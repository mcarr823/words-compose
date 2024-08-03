package dev.mcarr.words.ui.screens

import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import dev.mcarr.words.enums.WordSource
import dev.mcarr.words.ui.screens.OnlineSourceScreen
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class OnlineSourceScreenTest : AbstractScreenTest() {

    @Test
    fun test(){

        var source: WordSource? = null

        setContent {
            OnlineSourceScreen {
                source = it
            }
        }

        testHeading("Online Source")

        assertNull(source)
        composeTestRule.onNodeWithText("Heroku").performClick()
        assertEquals(WordSource.ONLINE_HEROKU, source)
        composeTestRule.onNodeWithText("Rando").performClick()
        assertEquals(WordSource.ONLINE_RANDO, source)
        composeTestRule.onNodeWithText("Ryanrk").performClick()
        assertEquals(WordSource.ONLINE_RYANRK, source)

    }
}