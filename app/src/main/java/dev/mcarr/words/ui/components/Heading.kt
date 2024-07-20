package dev.mcarr.words.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.mcarr.words.ui.theme.Typography

/**
 * Larger-sized, center-aligned text composable.
 *
 * Should be displayed at the top of a given screen.
 *
 * @param text Text to display in the heading
 * */
@Composable
fun Heading(
    text: String
) {

    Text(
        text = text,
        style = Typography.headlineMedium,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
    )

}

@Preview
@Composable
fun PreviewHeading() {
    PreviewComponent {
        Heading("Test heading")
    }
}