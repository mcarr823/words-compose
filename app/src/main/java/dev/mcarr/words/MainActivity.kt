package dev.mcarr.words

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.mcarr.words.enums.Destination
import dev.mcarr.words.ui.screens.AboutScreen
import dev.mcarr.words.ui.screens.DownloadSourceScreen
import dev.mcarr.words.ui.screens.DownloadWordListScreen
import dev.mcarr.words.ui.screens.GameLoadScreen
import dev.mcarr.words.ui.screens.GameScreen
import dev.mcarr.words.ui.screens.HomeScreen
import dev.mcarr.words.ui.screens.HowToPlayScreen
import dev.mcarr.words.ui.screens.ImportCsvFileScreen
import dev.mcarr.words.ui.screens.ImportJsonFileScreen
import dev.mcarr.words.ui.screens.ImportLocalFileScreen
import dev.mcarr.words.ui.screens.ImportTxtFileScreen
import dev.mcarr.words.ui.screens.ImportXmlFileScreen
import dev.mcarr.words.ui.screens.OnlineSourceScreen
import dev.mcarr.words.ui.screens.ProcessLocalFileScreen
import dev.mcarr.words.ui.screens.SetupScreen
import dev.mcarr.words.ui.theme.WordsTheme
import dev.mcarr.words.viewmodels.DownloadWordListScreenViewModel
import dev.mcarr.words.viewmodels.GameLoadScreenViewModel
import dev.mcarr.words.viewmodels.GuessViewModel
import dev.mcarr.words.viewmodels.MainActivityViewModel
import dev.mcarr.words.viewmodels.ProcessLocalFileScreenViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WordsTheme {
                val model = MainActivityViewModel()
                MainActivityScreen(model)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainActivityScreen(
    model: MainActivityViewModel
){

    val guessModel = GuessViewModel()
    val loadGameModel = GameLoadScreenViewModel()
    val downloadWordListModel = DownloadWordListScreenViewModel()
    val processFileModel = ProcessLocalFileScreenViewModel()

    var route by remember {
        model.route
    }

    val navController = rememberNavController()
    navController.addOnDestinationChangedListener { _, destination, _ ->
        route = destination.route ?: ""
    }

    val goTo: (destination: String) -> Unit = { destination ->
        navController.navigate(destination)
    }

    val goBack: () -> Unit = {
        navController.popBackStack()
    }

    val processFileCallback: (uri: Uri) -> Unit = { uri ->
        processFileModel.reset(uri)
        goTo(Destination.PROCESS_LOCAL_FILE)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {},
                actions = {

                    // Disable the settings screen in the playstore version
                    // for the time being.
                    // The only settings available right now are word source
                    // selections, and those haven't been tested enough to
                    // be released.
                    if (BuildConfig.FLAVOR != "playstore") {
                        if (route == Destination.HOME) {
                            Row {
                                TextButton(onClick = { goTo(Destination.SETUP) }) {
                                    Text("Settings")
                                }
                            }
                        }
                    }

                },
                navigationIcon = {
                    if (route == Destination.HOME) {
                        TextButton(
                            onClick = { goTo(Destination.ABOUT) }
                        ) {
                            Text("About")
                        }
                    }else {
                        IconButton(
                            onClick = { goBack() },
                            content = { Icon(Icons.AutoMirrored.Default.ArrowBack, "") }
                        )
                    }
                }
            )
        }
    ) { padding ->

        NavHost(
            navController = navController,
            startDestination = model.startDestination
        ){

            composable(Destination.ABOUT){ AboutScreen(padding) }

            composable(Destination.HOME){
                HomeScreen(
                    paddingValues = padding,
                    howToPlay = { goTo(Destination.HOW_TO_PLAY) },
                    playNow = { goTo(Destination.LOAD_GAME) }
                )
            }

            composable(Destination.HOW_TO_PLAY){ HowToPlayScreen(padding) }
            composable(Destination.PLAY_GAME){
                GameScreen(
                    paddingValues = padding,
                    guessModel = guessModel,
                    goHome = { navController.popBackStack(Destination.HOME, false) },
                    playAgain = {
                        navController.navigate(Destination.LOAD_GAME){
                            // Pop up first, so we have a consistent direction
                            // of composables in the stack and don't add any twice.
                            // ie. Home -> Load -> Play -> back to Home
                            // instead of Home -> Load -> Play -> Load -> Play -> etc.
                            popUpTo(Destination.HOME)
                        }
                    }
                )
            }
            composable(Destination.LOAD_GAME){
                GameLoadScreen(
                    padding,
                    guessViewModel = guessModel,
                    model = loadGameModel,
                    goBack = goBack,
                    playNow = {
                        navController.navigate(Destination.PLAY_GAME){
                            // Pop up first, since the LOAD_GAME screen
                            // redirects to the PLAY_GAME screen, and we
                            // don't want to end up in a loop.
                            popUpTo(Destination.HOME)
                        }
                    }
                )
            }
            composable(Destination.ONLINE_SOURCE){
                OnlineSourceScreen(
                    padding,
                    done = { source ->
                        // TODO save selection? or do that on the screen?
                        // Maybe a connectivity test?
                    }
                )
            }
            composable(Destination.DOWNLOAD_SOURCE){
                DownloadSourceScreen(
                    padding,
                    done = { source ->
                        // TODO same here
                    }
                )
            }
            composable(Destination.DOWNLOAD_WORD_LIST){
                DownloadWordListScreen(
                    padding,
                    model = downloadWordListModel,
                    playNow = { goTo(Destination.LOAD_GAME) },
                    goBack = goBack
                )
            }
            composable(Destination.IMPORT_CSV){ ImportCsvFileScreen(padding, processFileCallback) }
            composable(Destination.IMPORT_JSON){ ImportJsonFileScreen(padding, processFileCallback) }
            composable(Destination.IMPORT_XML){ ImportXmlFileScreen(padding, processFileCallback) }
            composable(Destination.IMPORT_TXT){ ImportTxtFileScreen(padding, processFileCallback) }
            composable(Destination.IMPORT_LOCAL_FILE){
                ImportLocalFileScreen(
                    padding,
                    goToCsvFileImportScreen = { goTo(Destination.IMPORT_CSV) },
                    goToJsonFileImportScreen = { goTo(Destination.IMPORT_JSON) },
                    goToTxtFileImportScreen = { goTo(Destination.IMPORT_TXT) },
                    goToXmlFileImportScreen = { goTo(Destination.IMPORT_XML) }
                )
            }
            composable(Destination.PROCESS_LOCAL_FILE){
                ProcessLocalFileScreen(
                    padding,
                    model = processFileModel,
                    playNow = { goTo(Destination.LOAD_GAME) },
                    goBack = goBack
                )
            }
            composable(Destination.SETUP){
                SetupScreen(
                    padding,
                    goToOnlineSourceScreen = { goTo(Destination.ONLINE_SOURCE) },
                    goToLocalFileScreen = { goTo(Destination.IMPORT_LOCAL_FILE) },
                    goToDownloadSourceScreen = { goTo(Destination.DOWNLOAD_SOURCE) }
                )
            }

        }


    }
}

@Preview(showBackground = true)
@Composable
fun MainActivityHomePreview() {
    WordsTheme {
        val model = MainActivityViewModel()
        // Defaults to HOME
        MainActivityScreen(model)
    }
}

@Preview(showBackground = true)
@Composable
fun MainActivityHowToPlayPreview() {
    WordsTheme {
        val model = MainActivityViewModel()
        model.startDestination = Destination.HOW_TO_PLAY
        MainActivityScreen(model)
    }
}

@Preview(showBackground = true)
@Composable
fun MainActivityPlayGamePreview() {
    WordsTheme {
        val model = MainActivityViewModel()
        model.startDestination = Destination.PLAY_GAME
        MainActivityScreen(model)
    }
}

@Preview(showBackground = true)
@Composable
fun MainActivityLoadGamePreview() {
    WordsTheme {
        val model = MainActivityViewModel()
        model.startDestination = Destination.LOAD_GAME
        MainActivityScreen(model)
    }
}

@Preview(showBackground = true)
@Composable
fun MainActivityOnlineSourcePreview() {
    WordsTheme {
        val model = MainActivityViewModel()
        model.startDestination = Destination.ONLINE_SOURCE
        MainActivityScreen(model)
    }
}

@Preview(showBackground = true)
@Composable
fun MainActivityDownloadSourcePreview() {
    WordsTheme {
        val model = MainActivityViewModel()
        model.startDestination = Destination.DOWNLOAD_SOURCE
        MainActivityScreen(model)
    }
}

@Preview(showBackground = true)
@Composable
fun MainActivityImportWordListPreview() {
    WordsTheme {
        val model = MainActivityViewModel()
        model.startDestination = Destination.DOWNLOAD_WORD_LIST
        MainActivityScreen(model)
    }
}

@Preview(showBackground = true)
@Composable
fun MainActivityImportCsvPreview() {
    WordsTheme {
        val model = MainActivityViewModel()
        model.startDestination = Destination.IMPORT_CSV
        MainActivityScreen(model)
    }
}

@Preview(showBackground = true)
@Composable
fun MainActivityImportJsonPreview() {
    WordsTheme {
        val model = MainActivityViewModel()
        model.startDestination = Destination.IMPORT_JSON
        MainActivityScreen(model)
    }
}

@Preview(showBackground = true)
@Composable
fun MainActivityImportXmlPreview() {
    WordsTheme {
        val model = MainActivityViewModel()
        model.startDestination = Destination.IMPORT_XML
        MainActivityScreen(model)
    }
}

@Preview(showBackground = true)
@Composable
fun MainActivityImportTxtPreview() {
    WordsTheme {
        val model = MainActivityViewModel()
        model.startDestination = Destination.IMPORT_TXT
        MainActivityScreen(model)
    }
}

@Preview(showBackground = true)
@Composable
fun MainActivityImportLocalFilePreview() {
    WordsTheme {
        val model = MainActivityViewModel()
        model.startDestination = Destination.IMPORT_LOCAL_FILE
        MainActivityScreen(model)
    }
}

@Preview(showBackground = true)
@Composable
fun MainActivityProcessLocalFilePreview() {
    WordsTheme {
        val model = MainActivityViewModel()
        model.startDestination = Destination.PROCESS_LOCAL_FILE
        MainActivityScreen(model)
    }
}

@Preview(showBackground = true)
@Composable
fun MainActivitySetupPreview() {
    WordsTheme {
        val model = MainActivityViewModel()
        model.startDestination = Destination.SETUP
        MainActivityScreen(model)
    }
}

