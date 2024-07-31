package dev.mcarr.words.data.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

/**
 * Abstract class for any UI testing classes to inherit from.
 *
 * Provides a set of different common tests which they can run
 * on components which any screen is likely to have.
 * */
@RunWith(RobolectricTestRunner::class)
abstract class AbstractScreenTest {

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
     * Set of tests to conduct on a Heading component.
     *
     * @param text The text which is expected to be displayed in the heading
     * */
    fun testHeading(
        text: String
    ){

        composeTestRule.onNodeWithText(text).run {
            assertExists()
            assertTextEquals(text)
            assertIsDisplayed()
        }

    }

    /**
     * Set of tests to conduct on a Button component.
     *
     * @param text The text which is expected to be displayed in the button.
     * @param alwaysVisible If true, assert that the button must be visible, and run more
     * comprehensive tests which are only possible if it's visible.
     * @param getButtonClickedCount Callback to retrieve the variable which is incremented
     * whenever the button is pressed.
     * */
    fun testButton(
        text: String,
        alwaysVisible: Boolean = true,
        getButtonClickedCount: () -> Int
    ){

        composeTestRule.onNodeWithText(text).run {
            assertExists()
            assertTextEquals(text)
            assertIsEnabled()

            // If the button won't necessarily be visible
            // (eg. it's too far down the screen and might not
            // be rendered) then we shouldn't run the full set
            // of tests unless the button is visible.
            if (!alwaysVisible){
                if (!isDisplayed()){
                    return@run
                }
            }

            assertIsDisplayed()

            assertEquals(0, getButtonClickedCount())

            performClick()
            assertEquals(1, getButtonClickedCount())

            performClick()
            assertEquals(2, getButtonClickedCount())
        }

    }

}