package dev.mcarr.words.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

/**
 * Abstract class for any UI testing classes to inherit from.
 *
 * Provides the default runner (Robolectric) and a compose rule
 * for generating UI components.
 * */
@RunWith(RobolectricTestRunner::class)
abstract class AbstractUiUnitTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    /**
     * Convenience function for calling composeTestRule.setContent{}
     *
     * @param composable Composable to render
     * */
    fun setContent(
        composable: @Composable () -> Unit
    ){
        composeTestRule.setContent(composable)
    }

    /**
     * Run some unit tests on a node which displays a
     * string matching the `text` parameter.
     *
     * @param text String used to identify the component
     * @param callback Block of code to run within the
     * retrieved component's context
     * */
    fun onNodeWithText(
        text: String,
        callback: SemanticsNodeInteraction.() -> Unit
    ){
        composeTestRule.onNodeWithText(text).run {
            callback()
        }
    }

    /**
     * Run some unit tests on a node which has a
     * test tag matching the `tag` parameter.
     *
     * @param tag Tag used to identify the component
     * @param callback Block of code to run within the
     * retrieved component's context
     * */
    fun onNodeWithTag(
        tag: String,
        callback: SemanticsNodeInteraction.() -> Unit
    ){
        composeTestRule.onNodeWithTag(tag).run {
            callback()
        }
    }

}