package com.blue.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.blue.data.local.dao.MediaDao
import com.blue.data.local.dto.MediaEntity


@Database(
    entities = [MediaEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDataBase: RoomDatabase() {
    abstract fun getMediaDao(): MediaDao
}