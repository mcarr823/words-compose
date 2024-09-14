package dev.mcarr.words.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.mcarr.words.ui.theme.WordsTheme

/**
 * Component used purely for previewing other components.
 *
 * Should be used inside of @Preview functions in order to
 * abstract away the theme, padding and layout, so as to
 * provide a consistent environment for previews of all
 * components and screens.
 *
 * @param darkMode If true, preview the view in dark mode
 * @param content The composable to be displayed in the preview.
 * */
@Composable
fun PreviewComponent(
    darkMode: Boolean = false,
    content: @Composable ColumnScope.() -> Unit
) {
    WordsTheme(darkTheme = darkMode) {
        Scaffold(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                Modifier.padding(it),
                content = content
            )
        }
    }
}

@Preview
@Composable
fun PreviewPreviewComponentLight() {
    PreviewComponent(
        darkMode = false
    ){
        Text(text = "Preview")
    }
}

@Preview
@Composable
fun PreviewPreviewComponentDark() {
    PreviewComponent(
        darkMode = true
    ){
        Text(text = "Preview")
    }
}