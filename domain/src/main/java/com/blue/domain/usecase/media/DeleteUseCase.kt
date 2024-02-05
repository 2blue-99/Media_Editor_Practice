package com.blue.domain.usecase.media

import com.blue.domain.repo.MediaRepo

class DeleteUseCase(
    private val repo: MediaRepo
) {
    suspend operator fun invoke(id: Int){
        repo.deleteData(id)
    }
}