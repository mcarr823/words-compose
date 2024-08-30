package dev.mcarr.words

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.mcarr.words.enums.Destination
import dev.mcarr.words.ui.screens.GameScreen
import dev.mcarr.words.ui.screens.HomeScreen
import dev.mcarr.words.ui.screens.HowToPlayScreen
import dev.mcarr.words.ui.theme.WordsTheme
import dev.mcarr.words.viewmodels.GameScreenViewModel
import dev.mcarr.words.viewmodels.MainActivityViewModel

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

    val gameModel = GameScreenViewModel()

    var route by remember {
        model.route
    }

    val navController = rememberNavController()
    navController.addOnDestinationChangedListener { _, destination, _ ->
        route = destination.route ?: ""
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {},
                actions = {
                    if (route == Destination.HOME) {
                        Row {
                            TextButton(onClick = {}) {
                                Text("Settings")
                            }
                        }
                    }
                },
                navigationIcon = {
                    if (route != Destination.HOME) {
                        IconButton(
                            onClick = { navController.popBackStack() },
                            content = { Icon(Icons.AutoMirrored.Default.ArrowBack, "") }
                        )
                    }
                }
            )
        }
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = model.startDetination
        ){

            composable(Destination.HOME){
                HomeScreen(
                    paddingValues = innerPadding,
                    howToPlay = { navController.navigate(Destination.HOW_TO_PLAY) },
                    playNow = { navController.navigate(Destination.PLAY_GAME) }
                )
            }

            composable(Destination.HOW_TO_PLAY){ HowToPlayScreen(innerPadding) }
            composable(Destination.PLAY_GAME){ GameScreen(innerPadding, gameModel) }

        }


    }
}

@Preview(showBackground = true)
@Composable
fun MainActivityHomePreview() {
    WordsTheme {
        val model = MainActivityViewModel()
        MainActivityScreen(model)
    }
}

@Preview(showBackground = true)
@Composable
fun MainActivityHowToPlayPreview() {
    WordsTheme {
        val model = MainActivityViewModel()
        model.startDetination = Destination.HOW_TO_PLAY
        MainActivityScreen(model)
    }
}

@Preview(showBackground = true)
@Composable
fun MainActivityPlayGamePreview() {
    WordsTheme {
        val model = MainActivityViewModel()
        model.startDetination = Destination.PLAY_GAME
        MainActivityScreen(model)
    }
}