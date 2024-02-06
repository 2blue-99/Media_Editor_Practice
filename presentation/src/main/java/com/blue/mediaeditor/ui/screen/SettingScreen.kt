package com.blue.mediaeditor.ui.screen

import android.net.Uri
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun SettingScreen(
    navController: NavController,
    uri: Uri
){
    Log.e("TAG", "SettingScreen: $uri", )
}