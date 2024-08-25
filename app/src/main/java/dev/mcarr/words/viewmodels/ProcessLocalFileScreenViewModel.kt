package dev.mcarr.words.viewmodels

import android.net.Uri
import androidx.compose.runtime.mutableStateOf
import androidx.core.net.toUri
import androidx.lifecycle.ViewModel

/**
 * Viewmodel for the ProcessLocalFileScreen component.
 *
 * @see dev.mcarr.words.ui.screens.ProcessLocalFileScreen
 * */
class ProcessLocalFileScreenViewModel : ViewModel() {

    /**
     * URI of the file to be processed.
     * */
    lateinit var uri: Uri

    /**
     * Boolean indicating that the import was
     * successful.
     * */
    var success = mutableStateOf(false)

    /**
     * Boolean indicating that the import failed.
     * */
    var error = mutableStateOf(false)

}