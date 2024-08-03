package dev.mcarr.words.ui.components

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.text.buildAnnotatedString
import dev.mcarr.words.ui.AbstractUiUnitTest
import org.junit.Test

class PaddedTextTest : AbstractUiUnitTest() {

    @Test
    fun testPaddedTextString(){

        val text = "Test text"

        setContent {
            PaddedText(text = text)
        }

        onNodeWithText(text){
            assertExists()
            assertIsDisplayed()
            assertTextEquals(text)
        }

    }

    @Test
    fun testPaddedTextAnnotated(){

        val text1 = "Annotated "
        val text2 = "String"
        val textCombined = text1+text2
        val text = buildAnnotatedString {
            append(text1)
            append(text2)
        }

        setContent {
            PaddedText(text = text)
        }

        onNodeWithText(textCombined){
            assertExists()
            assertIsDisplayed()
            assertTextEquals(textCombined)
        }

    }

}