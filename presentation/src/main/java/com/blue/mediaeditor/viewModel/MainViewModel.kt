package com.blue.mediaeditor.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blue.domain.usecase.media.ReadAllUseCase
import com.blue.mediaeditor.ui.state.BottomSheetUiState
import com.blue.mediaeditor.ui.state.MainUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val readAllUseCase: ReadAllUseCase
) : ViewModel() {
    private val _bottomSheetUiState = MutableStateFlow<BottomSheetUiState>(BottomSheetUiState.Down)
    val bottomSheetUiState: StateFlow<BottomSheetUiState> get() = _bottomSheetUiState

    val mainUiState: StateFlow<MainUiState> =
        readAllUseCase().map {
            MainUiState.Success(
                mediaList = it
            )
        }.catch {
            MainUiState.Error(it.message ?: "err")
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = MainUiState.Loading
        )

    fun changeBottomSheet(state: Boolean){
        if(state){
            _bottomSheetUiState.value = BottomSheetUiState.Up
        }else{
            _bottomSheetUiState.value = BottomSheetUiState.Down
        }
    }
}