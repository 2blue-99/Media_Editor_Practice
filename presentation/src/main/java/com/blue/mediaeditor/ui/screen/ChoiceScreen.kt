package com.blue.mediaeditor.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController

@Composable
fun ChoiceScreen(
    navController: NavController
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            IconButton(onClick = { /*TODO*/ }) {

            }
        }
    ) { padding ->
        Column(
            modifier = Modifier.padding(padding)
        ) {
            ChoiceContentWithStaus(

            )
        }
    }
}

@Composable
fun ChoiceContentWithStaus(

){

}

@Composable
fun ChoiceContent(){
    LazyColumn(
        modifier = Modifier
    ) {
        item {
            Surface(color = Color.Black) {
                Text(text = "Media Editor")
            }
        }

    }
}