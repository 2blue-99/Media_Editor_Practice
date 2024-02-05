package com.blue.data.local.di

import android.content.Context
import androidx.room.Room
import com.blue.data.local.AppDataBase
import com.blue.data.local.dao.MediaDao
import com.blue.data.repo.MediaRepoImpl
import com.blue.domain.repo.MediaRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MediaModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDataBase =
        Room.databaseBuilder(context, AppDataBase::class.java, "Database").build()

    @Provides
    @Singleton
    fun provideMediaDao(database: AppDataBase) = database.getMediaDao()

    @Provides
    @Singleton
    fun provideMediaRepo(dao: MediaDao): MediaRepo = MediaRepoImpl(dao)


}