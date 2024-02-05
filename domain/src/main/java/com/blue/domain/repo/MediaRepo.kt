package com.blue.domain.repo

import com.blue.domain.model.Media
import kotlinx.coroutines.flow.Flow

interface MediaRepo {
    fun readAllData(): Flow<List<Media>>
    suspend fun deleteData(id: Int)
    suspend fun insertData(data: Media)

}