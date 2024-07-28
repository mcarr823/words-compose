package dev.mcarr.words.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * Clickable card component with text inside of it.
 *
 * @param text Text to display within the card
 * @param onClick Callback to invoke when the card is tapped
 * */
@Composable
fun NavCard(
    text: String,
    onClick: () -> Unit
) {
    val annotated = AnnotatedString(text)
    NavCard(
        text = annotated,
        onClick = onClick
    )
}

/**
 * Clickable card component with text inside of it.
 *
 * @param text Text to display within the card
 * @param onClick Callback to invoke when the card is tapped
 * */
@Composable
fun NavCard(
    text: AnnotatedString,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 4.dp, end = 4.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            PaddedText(text = text)
            Icon(
                imageVector = Icons.AutoMirrored.Default.ArrowForward,
                contentDescription = "Right arrow",
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Preview
@Composable
fun PreviewNavCard(){
    PreviewComponent {
        NavCard(text = "Regular string") {
            //
        }
        NavCard(text = "Annotated string") {
            //
        }
    }
}