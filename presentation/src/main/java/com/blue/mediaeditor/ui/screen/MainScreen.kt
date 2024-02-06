package com.blue.mediaeditor.ui.screen

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.navArgument
import com.blue.mediaeditor.navigation.Destination
import com.blue.mediaeditor.ui.component.ProjectComponent
import com.blue.mediaeditor.ui.state.BottomSheetUiState
import com.blue.mediaeditor.ui.state.MainUiState
import com.blue.mediaeditor.viewModel.MainViewModel
import com.google.android.exoplayer2.MediaItem

@Composable
fun MainScreen(
    navController: NavController,
    mainViewModel: MainViewModel = hiltViewModel()
) {
    val mainUiState by mainViewModel.mainUiState.collectAsStateWithLifecycle()
    val bottomSheetUiState by mainViewModel.bottomSheetUiState.collectAsStateWithLifecycle()

    val launcher =  rememberLauncherForActivityResult(contract =
    ActivityResultContracts.PickVisualMedia()) { uri: Uri? ->
        if(uri!=null) {
            navController.currentBackStackEntry?.savedStateHandle?.set(key = "uri", value = uri.toString())
            navController.navigate(Destination.Setting.name)
        }
    }

    Scaffold(
        containerColor = Color.DarkGray,
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(
                onClick = { launcher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageAndVideo)) },
                containerColor = Color.Red,
                shape = CircleShape
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier.padding(padding)
        ) {
            MainContentWithStatus(
                uiState = mainUiState,
                bottomSheetUiState = bottomSheetUiState,
                mainViewModel = mainViewModel
            )
        }
    }
}

@Composable
fun MainContentWithStatus(
    uiState: MainUiState,
    bottomSheetUiState: BottomSheetUiState,
    mainViewModel: MainViewModel
) {
    when (uiState) {
        is MainUiState.Loading -> {}
        is MainUiState.Error -> {}
        is MainUiState.Success -> {
            MainContent(
                uiState,
                bottomSheetUiState,
                mainViewModel = mainViewModel
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainContent(
    uiData: MainUiState.Success,
    bottomSheetUiState: BottomSheetUiState,
    mainViewModel: MainViewModel
) {
    val bottomSheetState = rememberModalBottomSheetState()

    if (bottomSheetUiState is BottomSheetUiState.Up) {
        MainBottomSheet(sheetState = bottomSheetState, mainViewModel)
    }
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
    ) {
        item {
            Column(
                modifier = Modifier.fillMaxWidth().height(250.dp).background(Color.Black),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Media Editor", color = Color.White)
            }
        }

        items(uiData.mediaList, key = { it.id }) {
            ProjectComponent(uiData = it)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainBottomSheet(
    sheetState: SheetState,
    mainViewModel: MainViewModel
) {
    ModalBottomSheet(
        onDismissRequest = { mainViewModel.changeBottomSheet(false) },
        sheetState = sheetState
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(350.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                IconButton(
                    onClick = {},
                ) {
                    Row {
                        Icon(imageVector = Icons.Default.Create, contentDescription = "")
                        Text(text = "이름 변경")
                    }
                    Row {
                        Icon(imageVector = Icons.Default.ExitToApp, contentDescription = "")
                        Text(text = "복제")
                    }
                    Row {
                        Icon(imageVector = Icons.Default.Clear, contentDescription = "")
                        Text(text = "삭제")
                    }
                }

            }
        }
    }
}

