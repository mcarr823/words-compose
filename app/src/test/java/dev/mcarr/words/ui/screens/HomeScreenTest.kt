package dev.mcarr.words.ui.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.unit.dp
import org.junit.Test

class HomeScreenTest : AbstractScreenTest() {

    @Test
    fun testHomeScreen(){

        val padding = PaddingValues(0.dp)
        var howToPlayClicked = 0
        var playNowClicked = 0

        setContent {
            HomeScreen(
                paddingValues = padding,
                howToPlay = { howToPlayClicked++ },
                playNow = { playNowClicked++ }
            )
        }

        testButton("How To Play"){ howToPlayClicked }
        testButton("Play Now!"){ playNowClicked }

    }

}