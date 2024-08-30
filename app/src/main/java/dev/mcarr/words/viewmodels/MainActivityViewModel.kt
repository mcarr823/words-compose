package dev.mcarr.words.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dev.mcarr.words.enums.Destination

class MainActivityViewModel : ViewModel() {

    var route = mutableStateOf("")

    var startDetination = Destination.HOME

}