package dev.mcarr.words.data.ui.components

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import dev.mcarr.words.ui.components.PaddedText
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class PaddedTextTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testPaddedTextString(){

        val text = "Test text"

        composeTestRule.setContent {
            PaddedText(text = text)
        }

        composeTestRule.onNodeWithText(text).run {
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

        composeTestRule.setContent {
            PaddedText(text = text)
        }

        composeTestRule.onNodeWithText(textCombined).run {
            assertExists()
            assertIsDisplayed()
            assertTextEquals(textCombined)
        }

    }

}