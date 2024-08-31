package dev.mcarr.words.ui.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.unit.dp
import dev.mcarr.words.ui.screens.HowToPlayScreen
import org.junit.Test

class HowToPlayScreenTest : AbstractScreenTest() {

    @Test
    fun test(){

        val padding = PaddingValues(0.dp)

        setContent {
            HowToPlayScreen(paddingValues = padding)
        }

        testHeading("How To Play")

    }
}