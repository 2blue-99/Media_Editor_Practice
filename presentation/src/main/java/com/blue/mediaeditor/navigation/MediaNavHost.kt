package com.blue.mediaeditor.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
    ){
        composable(Destination.Main.name){
            MainScreen(navController)
        }
        composable(Destination.Choice.name){
            ChoiceScreen(navController)
        }
        composable(Destination.Setting.name){
            SettingScreen(navController)
        }
        composable(Destination.Project.name){
            ProjectScreen(navController)
        }
    }
}