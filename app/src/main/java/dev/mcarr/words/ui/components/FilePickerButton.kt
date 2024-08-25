package dev.mcarr.words.ui.components

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

/**
 * Button which triggers a file selection intent,
 * based on the mimetype passed in.
 *
 * Used to select local files for importing.
 * ie. Local flat file databases to use as the word
 * source to play the game.
 *
 * @param mimetype Mimetype of the file to retrieve.
 * eg. text/xml
 * @param callback Callback to invoke when a file is
 * selected.
 * */
@Composable
fun FilePickerButton(
    mimetype: String,
    callback: (file: Uri) -> Unit
) {

    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.OpenDocument()) {
        if (it != null){
            callback(it)
        }
    }

    Button(
        onClick = {
            launcher.launch(arrayOf(mimetype))
        },
    ) {
        Text(text = "Select File")
    }

}

@Preview
@Composable
fun PreviewFilePickerButton(){
    MaterialTheme {
        FilePickerButton(""){
            //
        }
    }
}