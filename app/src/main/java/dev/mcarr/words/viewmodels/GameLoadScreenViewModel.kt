package dev.mcarr.words.viewmodels

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dev.mcarr.words.api.http.HerokuHttpClient
import dev.mcarr.words.api.http.RandoHttpClient
import dev.mcarr.words.api.http.RyanrkHttpClient
import dev.mcarr.words.data.repos.WordRepository
import dev.mcarr.words.enums.WordSource

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
    var source = mutableStateOf(WordSource.TEST)

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

    var startAutomatically = true

    fun reset(){
        success.value = false
        error.value = false
        downloadAttempt.value = 1
    }

    suspend fun getWord(context: Context): String? =
        when (source.value){
            WordSource.ONLINE_HEROKU -> HerokuHttpClient().getRandomWord()
            WordSource.ONLINE_RANDO -> RandoHttpClient().getRandomWord()
            WordSource.ONLINE_RYANRK -> RyanrkHttpClient().getRandomWord()
            WordSource.DATABASE -> WordRepository.getInstance(context).getRandom()?.text
            WordSource.TEST -> "WORDS" // Hard-coded test value
            else -> null
        }

}