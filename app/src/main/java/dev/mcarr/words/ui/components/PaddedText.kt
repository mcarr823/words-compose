package dev.mcarr.words.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * Text component with pre-defined padding.
 *
 * Used to provide uniform padding (and other styling later)
 * to all screens.
 *
 * @param text Text to display
 * */
@Composable
fun PaddedText(
    text: String
) {
    Text(
        text = text,
        modifier = Modifier.padding(8.dp)
    )
}

@Composable
fun PaddedText(
    text: AnnotatedString
) {
    Text(
        text = text,
        modifier = Modifier.padding(8.dp)
    )
}

@Preview
@Composable
fun PreviewPaddedText(){
    PreviewComponent {
        PaddedText(text = "Preview")
    }
}