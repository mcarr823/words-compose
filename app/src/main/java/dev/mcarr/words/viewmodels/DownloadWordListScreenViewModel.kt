package dev.mcarr.words.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dev.mcarr.words.enums.WordSource

/**
 * Viewmodel used by the DownloadWordListScreen component.
 *
 * @see dev.mcarr.words.ui.screens.DownloadWordListScreen
 * */
class DownloadWordListScreenViewModel : ViewModel() {

    /**
     * Source from which to download a word list.
     * @see WordSource
     * */
    var source = mutableStateOf(WordSource.UNDEFINED)

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

}