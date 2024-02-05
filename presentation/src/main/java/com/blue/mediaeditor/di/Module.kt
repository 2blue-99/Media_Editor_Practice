package com.blue.mediaeditor.di

import com.blue.domain.repo.MediaRepo
import com.blue.domain.usecase.media.DeleteUseCase
import com.blue.domain.usecase.media.InsertUseCase
import com.blue.domain.usecase.media.ReadAllUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Module {
    @Provides
    @Singleton
    fun provideUseCase1(repo: MediaRepo) =
        DeleteUseCase(repo)

    @Provides
    @Singleton
    fun provideUseCase2(repo: MediaRepo) =
        InsertUseCase(repo)

    @Provides
    @Singleton
    fun provideUseCase3(repo: MediaRepo) =
        ReadAllUseCase(repo)
}