package com.blue.mediaeditor.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.blue.mediaeditor.navigation.MediaNavHost

@Composable
fun MediaApp(
    navController: MediaState = RememberMediaState()
){
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { padding ->
        MediaNavHost(
            modifier = Modifier.padding(padding),
            appState = navController
        )
    }
}