package dev.mcarr.words.data.ui.screens

import dev.mcarr.words.enums.WordSource
import dev.mcarr.words.ui.screens.DownloadSourceScreen
import org.junit.Test

class DownloadSourceScreenTest : AbstractScreenTest() {

    @Test
    fun test(){

        var herokuClicked = 0

        setContent {
            DownloadSourceScreen {
                if (it == WordSource.ONLINE_HEROKU) {
                    herokuClicked++
                }
            }
        }

        testButton("Heroku"){ herokuClicked }

    }
}