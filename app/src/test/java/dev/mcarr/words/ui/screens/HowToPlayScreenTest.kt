package dev.mcarr.words.ui.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.unit.dp
import dev.mcarr.words.ui.screens.HowToPlayScreen
import org.junit.Test

class HowToPlayScreenTest : AbstractScreenTest() {

    @Test
    fun test(){

        val padding = PaddingValues(0.dp)
        var clicked = 0

        setContent {
            HowToPlayScreen(paddingValues = padding) {
                clicked++
            }
        }

        testHeading("How To Play")
        testButton("Done", false){ clicked }

    }
}