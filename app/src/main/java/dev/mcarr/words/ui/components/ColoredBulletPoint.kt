package dev.mcarr.words.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import dev.mcarr.words.ui.theme.Green

/**
 * Component for displaying a String in two segments.
 * One colored, one not colored.
 *
 * The colored string is prefixed with a dot.
 * ie. A bullet point.
 *
 * The colored string is followed by ": ".
 * So the end result is something like:
 *   •  $coloredText: $regularText
 *
 * @param coloredText Text at the start of the string, colored with `textColor`
 * @param regularText Text at the end of the string, colored black
 * @param textColor Which color to make the bullet point and `coloredText`
 * */
@Composable
fun ColoredBulletPoint(
    coloredText: String,
    regularText: String,
    textColor: Color
) {

    val text = buildAnnotatedString {
        withStyle(style = SpanStyle(color = textColor)){
            append("  •  ")
            append(coloredText)
            append(": ")
        }
        append(regularText)
    }
    PaddedText(text = text)

}

@Preview
@Composable
fun PreviewColoredBulletPoint(){
    PreviewComponent {
        ColoredBulletPoint(
            coloredText = "Green",
            regularText = "Correct guess",
            textColor = Green
        )
    }
}