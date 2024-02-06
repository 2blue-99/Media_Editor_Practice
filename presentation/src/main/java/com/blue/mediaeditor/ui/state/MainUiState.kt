package com.blue.mediaeditor.ui.state

import com.blue.domain.model.Media


sealed interface MainUiState{
    data object Loading: MainUiState

    data class Error(val msg: String): MainUiState

    data class Success(
        val mediaList: List<Media>
    ): MainUiState
}