package dev.mcarr.words.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import dev.mcarr.words.enums.WordSource
import dev.mcarr.words.ui.components.Heading
import dev.mcarr.words.ui.components.NavCard
import dev.mcarr.words.ui.components.PaddedText
import dev.mcarr.words.ui.components.PreviewComponent

/**
 * Online source selection screen.
 *
 * Shows a list of different online API endpoints this app knows
 * how to query.
 *
 * @param done Callback to invoke when an endpoint is selected
 * */
@Composable
fun OnlineSourceScreen(
    done: (choice: WordSource) -> Unit
) {

    Column {
        Heading(text = "Online Source")

        PaddedText(text = "Words can query an online API to retrieve a new word whenever you play the game.")
        PaddedText(text = "This is the quickest way to get started. But the downside is that you'll need an internet connection whenever you want to play.")
        PaddedText(text = "Also, the online word list APIs are hosted by other people, so they could shut down at any time.")
        PaddedText(text = "If you want to proceed, tap on one of the providers below to get started.")

        NavCard(text = "Heroku", onClick = { done(WordSource.ONLINE_HEROKU) })
        NavCard(text = "Rando", onClick = { done(WordSource.ONLINE_RANDO) })
        NavCard(text = "Ryanrk", onClick = { done(WordSource.ONLINE_RYANRK) })

        // TODO also add a Random option

    }

}

@Preview
@Composable
fun PreviewOnlineSourceScreen(){
    PreviewComponent {
        OnlineSourceScreen(
            done = {}
        )
    }
}