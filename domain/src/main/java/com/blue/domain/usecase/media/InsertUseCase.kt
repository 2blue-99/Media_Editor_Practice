package com.blue.domain.usecase.media

import com.blue.domain.model.Media
import com.blue.domain.repo.MediaRepo

class InsertUseCase(
    private val repo: MediaRepo
) {
    suspend operator fun invoke(data: Media){
        repo.insertData(data)
    }
}