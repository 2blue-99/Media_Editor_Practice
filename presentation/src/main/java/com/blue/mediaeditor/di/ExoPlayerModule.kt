package com.blue.mediaeditor.di

import android.content.Context
import com.google.android.exoplayer2.SimpleExoPlayer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ExoPlayerModule {
    @Provides
    @Singleton
    fun provideExoPlayer(@ApplicationContext context: Context): SimpleExoPlayer =
        SimpleExoPlayer.Builder(context).build()
}