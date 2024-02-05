package com.blue.mediaeditor.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.blue.mediaeditor.navigation.Destination

@Composable
fun RememberMediaState(
    navController: NavHostController = rememberNavController()
): MediaState =
    remember(navController){ MediaState(navController) }

@Stable
class MediaState(
    val navController: NavHostController
){
    val destinations: List<Destination> = Destination.entries

    val currentLocation: NavDestination?
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination

    fun navigationToDestination(name: String){
        val navOptions = navOptions {
            popUpTo(navController.graph.findStartDestination().id){
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
        when(name){
            Destination.Main.name -> {}
            Destination.Choice.name -> {}
            Destination.Setting.name -> {}
            Destination.Project.name -> {}
        }
    }
}