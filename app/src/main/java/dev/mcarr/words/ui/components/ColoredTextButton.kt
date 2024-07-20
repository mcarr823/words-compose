package dev.mcarr.words.ui.components

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.graphics.toColorInt

/**
 * A text button composable with specified colors.
 *
 * Provides a reusable component for two-color buttons
 * which contain nothing but text within them.
 *
 * @param backgroundColor Color of the button. Must be a valid hex string
 * @param textColor Color of the text. Must be a valid hex string
 * @param text Text to display within the button
 * @param onClick Callback to invoke when the button is clicked
 * */
@Composable
fun ColoredTextButton(
    backgroundColor: Color,
    textColor: Color,
    text: String,
    onClick: () -> Unit
) {

    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            contentColor = textColor
        )
    ) {
        Text(text = text)
    }

}

@Preview
@Composable
fun PreviewColoredTextButton(){
    PreviewComponent {
        ColoredTextButton(backgroundColor = Color.Black, textColor = Color.White, text = "Test") {
            // Do nothing
        }
    }
}