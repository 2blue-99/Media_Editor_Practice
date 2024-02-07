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
object UseCaseModule {
    @Provides
    @Singleton
    fun provideDeleteUseCase(repo: MediaRepo) =
        DeleteUseCase(repo)

    @Provides
    @Singleton
    fun provideInsertUseCase(repo: MediaRepo) =
        InsertUseCase(repo)

    @Provides
    @Singleton
    fun provideReadUseCase(repo: MediaRepo) =
        ReadAllUseCase(repo)
}