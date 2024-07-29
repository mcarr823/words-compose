package dev.mcarr.words.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import dev.mcarr.words.enums.WordSource
import dev.mcarr.words.ui.components.Heading
import dev.mcarr.words.ui.components.NavCard
import dev.mcarr.words.ui.components.PaddedText

/**
 * Word list download selection screen.
 *
 * Shows a list of different online API endpoints this app knows
 * how to query.
 *
 * A word list is then downloaded from the selected endpoint.
 *
 * @param done Callback to invoke when an endpoint is selected
 * */
@Composable
fun DownloadSourceScreen(
    done: (choice: WordSource) -> Unit
) {

    Column {
        Heading(text = "Download Word List")

        PaddedText(text = "Words can download a word list from an online API, then cache those results for later.")
        PaddedText(text =
                "This means you'll need an internet connection initially, to download the list. " +
                "But afterwards you'll be able to play offline."
        )
        PaddedText(text = "Please note, however, that the online word list APIs are hosted by other people, so they could shut down at any time.")
        PaddedText(text = "So if the download doesn't work, that might be the reason why.")
        PaddedText(text = "Also, there is only one provider to choose from at the moment.")
        PaddedText(text = "If you want to proceed, tap on the provider below.")

        NavCard(text = "Heroku", onClick = { done(WordSource.ONLINE_HEROKU) })

    }

}