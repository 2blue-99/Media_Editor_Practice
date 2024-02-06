package com.blue.mediaeditor.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.core.net.toUri
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.blue.mediaeditor.ui.MediaState
import com.blue.mediaeditor.ui.screen.ChoiceScreen
import com.blue.mediaeditor.ui.screen.MainScreen
import com.blue.mediaeditor.ui.screen.ProjectScreen
import com.blue.mediaeditor.ui.screen.SettingScreen

@Composable
fun MediaNavHost(
    modifier: Modifier,
    appState: MediaState
) {
    val navController = appState.navController
    NavHost(
        navController = navController,
        startDestination = Destination.Main.name,
        modifier = modifier,
    ) {
        composable(Destination.Main.name) {
            MainScreen(navController)
        }
        composable(Destination.Choice.name) {
            ChoiceScreen(navController)
        }
        composable(Destination.Setting.name) {
            val uri = remember { navController.previousBackStackEntry?.savedStateHandle?.get<String>("uri") }
            if (uri != null) {
                SettingScreen(navController, uri.toUri())
            }
        }
        composable(Destination.Project.name) {
            ProjectScreen(navController)
        }
    }
}