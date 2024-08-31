package dev.mcarr.words.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.net.toFile
import dev.mcarr.words.data.imports.CsvFile
import dev.mcarr.words.data.imports.JsonFile
import dev.mcarr.words.data.imports.WordFile
import dev.mcarr.words.data.imports.XmlFile
import dev.mcarr.words.data.repos.WordRepository
import dev.mcarr.words.ui.components.ColoredTextButton
import dev.mcarr.words.ui.components.Heading
import dev.mcarr.words.ui.components.PaddedText
import dev.mcarr.words.ui.theme.Blue
import dev.mcarr.words.viewmodels.ProcessLocalFileScreenViewModel

/**
 * Screen for processing a locally imported database file.
 *
 * Displays text and a progress indicator while attempting
 * to parse a local file and import it into the database.
 *
 * @param paddingValues Padding around the screen components
 * @param model Viewmodel containing the current state
 * of the screen.
 * @param playNow Callback to invoke when the user taps
 * on the Play button.
 * @param goBack Callback to invoke when the user taps
 * on the Back button.
 *
 * @see ProcessLocalFileScreenViewModel
 * */
@Composable
fun ProcessLocalFileScreen(
    paddingValues: PaddingValues,
    model: ProcessLocalFileScreenViewModel,
    playNow: () -> Unit,
    goBack: () -> Unit
) {

    val context = LocalContext.current

    var error by remember {
        model.error
    }

    var success by remember {
        model.success
    }

    Column(
        modifier = Modifier.padding(paddingValues),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (success) {

            Heading(text = "Success")
            Spacer(modifier = Modifier.height(16.dp))
            PaddedText(text = "Word list has been imported.")
            PaddedText(text = "You are now ready to play.")
            Spacer(modifier = Modifier.height(16.dp))
            ColoredTextButton(
                backgroundColor = Blue,
                textColor = Color.White,
                text = "Play Now!",
                onClick = playNow
            )

        } else if (error) {

            Heading(text = "Error")
            Spacer(modifier = Modifier.height(16.dp))
            PaddedText(text = "Failed to import word list.")
            Spacer(modifier = Modifier.height(16.dp))
            ColoredTextButton(
                backgroundColor = Blue,
                textColor = Color.White,
                text = "Go Back",
                onClick = goBack
            )

        } else {

            Heading(text = "Processing")
            Spacer(modifier = Modifier.height(16.dp))
            PaddedText(text = "Parsing the word list...")
            Spacer(modifier = Modifier.height(16.dp))
            CircularProgressIndicator()

        }
    }

    LaunchedEffect(key1 = Unit) {
        try {
            val f = model.uri.toFile()
            val wordFile: WordFile = when (f.extension.lowercase()) {
                "xml" -> XmlFile(f)
                "json" -> JsonFile(f)
                "csv", "txt" -> CsvFile(f)
                else -> throw Exception("Invalid file format")
            }
            if (wordFile.words.isEmpty()){
                error = true
            }else{
                val repo = WordRepository.getInstance(context)
                repo.insert(wordFile.words)
                success = true
            }
        }catch (e: Exception){
            error = true
        }
    }

}