package com.blue.data.repo

import com.blue.data.local.dao.MediaDao
import com.blue.data.local.dto.MediaEntity
import com.blue.data.local.dto.asMedia
import com.blue.domain.model.Media
import com.blue.domain.repo.MediaRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MediaRepoImpl @Inject constructor(
    private val dao: MediaDao
) : MediaRepo {
    override fun readAllData(): Flow<List<Media>> =
        dao.realAll().map { list -> list.map { it.asMedia() } }


    override suspend fun deleteData(id: Int) {
        dao.delete(id)
    }

    override suspend fun insertData(data: Media) {
        dao.insert(
            MediaEntity(
                id = data.id,
                title = data.title,
                date = data.date,
                path = data.path
            )
        )
    }
}