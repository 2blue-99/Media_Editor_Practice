package com.blue.mediaeditor.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.blue.mediaeditor.navigation.Destination

@Composable
fun MainScreen(navController: NavController){
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.Blue
    ) {
        Column {
            Button(
                onClick = {navController.navigate(Destination.Choice.name)}
            ){
               Text(text = "To Choice")
            }

            Button(
                onClick = {navController.navigate(Destination.Project.name)}
            ){
                Text(text = "To Choice")
            }
        }
    }
}