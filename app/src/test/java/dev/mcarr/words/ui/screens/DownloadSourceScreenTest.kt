package dev.mcarr.words.ui.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.unit.dp
import dev.mcarr.words.enums.WordSource
import dev.mcarr.words.ui.screens.DownloadSourceScreen
import org.junit.Test

class DownloadSourceScreenTest : AbstractScreenTest() {

    @Test
    fun test(){

        var herokuClicked = 0

        setContent {
            DownloadSourceScreen(
                paddingValues = PaddingValues(0.dp),
            ) {
                if (it == WordSource.ONLINE_HEROKU) {
                    herokuClicked++
                }
            }
        }

        testButton("Heroku"){ herokuClicked }

    }
}