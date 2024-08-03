package dev.mcarr.words.ui.components

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import dev.mcarr.words.ui.AbstractUiUnitTest
import org.junit.Test

class WordComponentTest : AbstractUiUnitTest() {

    @Test
    fun testWordComponent(){

        setContent {
            WordComponent(word = "WORD", targetLength = 5)
        }

        onNodeWithText("W"){
            assertExists()
            assertIsDisplayed()
            assertTextEquals("W")
        }

        onNodeWithText("O"){
            assertExists()
            assertIsDisplayed()
            assertTextEquals("O")
        }

        onNodeWithText("R"){
            assertExists()
            assertIsDisplayed()
            assertTextEquals("R")
        }

        onNodeWithText("D"){
            assertExists()
            assertIsDisplayed()
            assertTextEquals("D")
        }

        onNodeWithText(" "){
            assertExists()
            assertIsDisplayed()
            assertTextEquals(" ")
        }

    }

    @Test
    fun testWordComponentDuplicateLetters(){

        setContent {
            WordComponent(word = "TEST", targetLength = 5)
        }

        onAllNodesWithText("T", 2){
            assertExists()
            assertIsDisplayed()
            assertTextEquals("T")
        }

        onNodeWithText("E"){
            assertExists()
            assertIsDisplayed()
            assertTextEquals("E")
        }

        onNodeWithText("S"){
            assertExists()
            assertIsDisplayed()
            assertTextEquals("S")
        }

        onNodeWithText(" "){
            assertExists()
            assertIsDisplayed()
            assertTextEquals(" ")
        }

    }

}