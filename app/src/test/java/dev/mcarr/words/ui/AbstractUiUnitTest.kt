package dev.mcarr.words.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.SemanticsNodeInteractionCollection
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import androidx.compose.ui.text.TextLayoutResult as TextLayoutResult1

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
     * Run some unit tests on a group of nodes which display
     * strings matching the `text` parameter.
     *
     * This is used when you want to run tests on the entire
     * collection, rather than the individual components.
     *
     * @param text String used to identify the components
     * @param callback Block of code to run within the
     * retrieved component group's context
     * */
    fun onAllNodesWithText(
        text: String,
        callback: SemanticsNodeInteractionCollection.() -> Unit
    ){
        composeTestRule.onAllNodesWithText(text, useUnmergedTree = true).run {
            callback()
        }
    }

    /**
     * Run some unit tests on each nodes which displays
     * a string matching the `text` parameter.
     *
     * This is used when you want to run the same set of
     * tests for each of the nodes which match the `text`
     * parameter.
     *
     * @param text String used to identify the components
     * @param expectedNumberOfNodes Number of nodes we expect
     * to be displaying `text`
     * @param callback Block of code to run within each of
     * the retrieved components' context
     * */
    fun onAllNodesWithText(
        text: String,
        expectedNumberOfNodes: Int,
        callback: SemanticsNodeInteraction.() -> Unit
    ){
        onAllNodesWithText(text){
            assertCountEquals(expectedNumberOfNodes)
            (0 until expectedNumberOfNodes)
                .map(this::get)
                .forEach(callback)
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
        composeTestRule.onNodeWithTag(tag, useUnmergedTree = true).run {
            callback()
        }
    }

    fun onNodeWithTag(
        tag: String
    ): SemanticsNodeInteraction {
        return composeTestRule.onNodeWithTag(tag, useUnmergedTree = true)
    }

    fun SemanticsNodeInteraction.assertTextColor(
        color: Color
    ): SemanticsNodeInteraction = assert(isOfColor(color))

    private fun isOfColor(color: Color): SemanticsMatcher = SemanticsMatcher(
        "${SemanticsProperties.Text.name} is of color '$color'"
    ) {
        val textLayoutResults = mutableListOf<TextLayoutResult1>()
        it.config.getOrNull(SemanticsActions.GetTextLayoutResult)
            ?.action
            ?.invoke(textLayoutResults)
        return@SemanticsMatcher if (textLayoutResults.isEmpty()) {
            false
        } else {
            textLayoutResults.first().layoutInput.style.color == color
        }
    }

    /**
     * FIXME
     * This test is currently broken since Robolectric can't run captureToImage.
     * @see <a href="https://github.com/robolectric/robolectric/issues/8071">the github issue</a>
     * */
    fun SemanticsNodeInteraction.assertBackgroundColor(color: Color): SemanticsNodeInteraction {
        return this
        /*
        val array = IntArray(20)
        captureToImage()
            .readPixels(array, startY = 10, startX = 10, width = 5, height = 4)
        val allMatch = array.all { it == color.convert(ColorSpaces.Srgb).hashCode() }
        assert(allMatch)
        return this
        */
    }

}