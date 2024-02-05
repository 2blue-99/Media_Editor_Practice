package com.blue.data.local.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.blue.domain.model.Media

@Entity(tableName = "Media")
data class MediaEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo val title: String,
    @ColumnInfo val date: String,
    @ColumnInfo val path: String
)

fun MediaEntity.asMedia():Media =
    Media(
        id = id,
        title = title,
        date = date,
        path = path
    )
