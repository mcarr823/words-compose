package dev.mcarr.words.viewmodels

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dev.mcarr.words.R
import dev.mcarr.words.api.http.HerokuHttpClient
import dev.mcarr.words.api.http.RandoHttpClient
import dev.mcarr.words.api.http.RyanrkHttpClient
import dev.mcarr.words.data.imports.JsonFile
import dev.mcarr.words.data.repos.WordRepository
import dev.mcarr.words.enums.WordSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Viewmodel used by the GameLoadScreen component.
 *
 * @see dev.mcarr.words.ui.screens.GameLoadScreen
 * */
class GameLoadScreenViewModel : ViewModel() {

    /**
     * Source from which to retrieve a word.
     * @see WordSource
     * */
    var source = mutableStateOf(WordSource.DATABASE)

    /**
     * Boolean indicating that the download request
     * was successful.
     * */
    var success = mutableStateOf(false)

    /**
     * Boolean indicating that the download request
     * failed.
     * */
    var error = mutableStateOf(false)

    /**
     * Integer reflecting the number of times a download
     * has been attempted
     * */
    var downloadAttempt = mutableStateOf(1)

    /**
     * The length of the random word we want the player
     * to guess.
     * TODO make this a setting so players can choose
     * their own word length.
     * */
    var targetWordLength = 5

    /**
     * Reset the viewmodel to its default state.
     *
     * This should be called before entering the screen,
     * so that the screen state (eg. which modals are visible)
     * is always in a consistent state upon entry.
     * */
    fun reset(){
        success.value = false
        error.value = false
        downloadAttempt.value = 1
    }

    /**
     * Retrieves a single word from the specified word source.
     *
     * This could result in a database query, a HTTP query, or
     * a hard-coded word for testing.
     *
     * @param context Context needed for database access
     *
     * @see WordSource
     * */
    suspend fun getWord(context: Context): String? = withContext(Dispatchers.IO) {
        when (source.value) {
            WordSource.ONLINE_HEROKU -> HerokuHttpClient().getRandomWord(targetWordLength)
            WordSource.ONLINE_RANDO -> RandoHttpClient().getRandomWord(targetWordLength)
            WordSource.ONLINE_RYANRK -> RyanrkHttpClient().getRandomWord(targetWordLength)
            WordSource.DATABASE -> WordRepository.getInstance(context).getRandom(targetWordLength)?.text
            WordSource.TEST -> "WORDS" // Hard-coded test value
            else -> null
        }
    }

    /**
     * Initializes the word source with default values.
     *
     * This only takes effect if the word source is DATABASE,
     * since other word sources retrieve data dynamically by
     * doing HTTP requests (for example) rather than pulling
     * from a local, set list of words.
     *
     * In theory this function shouldn't actually do anything,
     * since AppDatabase is preloaded from a local database
     * file in the assets directory.
     *
     * But if that fails for some reason, then this function
     * should serve as a fallback.
     *
     * @param context Context needed for database access
     *
     * @see WordSource
     * @see dev.mcarr.words.data.databases.AppDatabase
     * */
    suspend fun firstTimeInit(context: Context) = withContext(Dispatchers.IO) {
        if (source.value == WordSource.DATABASE) {
            val repo = WordRepository.getInstance(context)
            if (repo.count() == 0){
                val str = context.resources
                    .openRawResource(R.raw.default_word_list)
                    .bufferedReader()
                    .use { it.readText() }
                val wordFile = JsonFile(str)
                val words = wordFile.words
                repo.insert(words)
            }
        }
    }

}