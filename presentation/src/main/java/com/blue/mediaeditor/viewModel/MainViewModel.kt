package com.blue.mediaeditor.viewModel

import com.blue.domain.repo.MediaRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mediaRepo: MediaRepo
) {

}