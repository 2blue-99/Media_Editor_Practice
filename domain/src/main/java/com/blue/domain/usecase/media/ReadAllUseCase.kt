package com.blue.domain.usecase.media

import com.blue.domain.model.Media
import com.blue.domain.repo.MediaRepo
import kotlinx.coroutines.flow.Flow

class ReadAllUseCase(
    private val repo: MediaRepo
) {
    operator fun invoke(): Flow<List<Media>> =
        repo.readAllData()

}