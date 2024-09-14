package dev.mcarr.words.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.mcarr.words.ui.theme.Typography

/**
 * Text component with pre-defined padding.
 *
 * Used to provide uniform padding (and other styling later)
 * to all screens.
 *
 * Padding can optionally be overridden on any side.
 * eg. In case you want to keep padding uniform on all
 * sides except one.
 *
 * @param text Text to display
 * @param style Style to apply to the text
 * @param padLeft Padding at the start
 * @param padRight Padding at the end
 * @param padTop Padding at the top
 * @param padBottom Padding at the bottom
 * */
@Composable
fun PaddedText(
    text: String,
    style: TextStyle = Typography.bodyMedium,
    padLeft: Int = 8,
    padRight: Int = 8,
    padTop: Int = 8,
    padBottom: Int = 8
) {
    Text(
        text = text,
        style = style,
        modifier = Modifier.padding(
            start = padLeft.dp,
            end = padRight.dp,
            top = padTop.dp,
            bottom = padBottom.dp
        )
    )
}

@Composable
fun PaddedText(
    text: AnnotatedString,
    style: TextStyle = Typography.bodyMedium,
    padLeft: Int = 8,
    padRight: Int = 8,
    padTop: Int = 8,
    padBottom: Int = 8
) {
    Text(
        text = text,
        style = style,
        modifier = Modifier.padding(
            start = padLeft.dp,
            end = padRight.dp,
            top = padTop.dp,
            bottom = padBottom.dp
        )
    )
}

@Preview
@Composable
fun PreviewPaddedTextLight(){
    PreviewComponent {
        PaddedText(text = "Preview")
    }
}

@Preview
@Composable
fun PreviewPaddedTextDark(){
    PreviewComponent(
        darkMode = true
    ) {
        PaddedText(text = "Preview")
    }
}