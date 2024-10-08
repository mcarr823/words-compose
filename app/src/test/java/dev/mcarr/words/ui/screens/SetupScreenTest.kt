package dev.mcarr.words.ui.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.unit.dp
import dev.mcarr.words.ui.screens.SetupScreen
import org.junit.Test

class SetupScreenTest : AbstractScreenTest() {

    @Test
    fun test(){

        var localFileCount = 0
        var downloadSourceCount = 0
        var onlineSourceCount = 0

        setContent {
            SetupScreen(
                paddingValues = PaddingValues(0.dp),
                goToLocalFileScreen = { localFileCount++ },
                goToDownloadSourceScreen = { downloadSourceCount++ },
                goToOnlineSourceScreen = { onlineSourceCount++ }
            )
        }

        testHeading("Setup")
        testButton("Upload Your Own List"){ localFileCount }
        testButton("Download A Word List"){ downloadSourceCount }
        testButton("Use An Online Word List API"){ onlineSourceCount }

    }
}