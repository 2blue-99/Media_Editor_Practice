package com.blue.mediaeditor.ui.state


sealed interface BottomSheetUiState{
    data object Down: BottomSheetUiState

    data object Up: BottomSheetUiState
}

