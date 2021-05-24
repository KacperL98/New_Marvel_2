package com.example.marvelapp.module

import com.example.marvelapp.api.ComicApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideCase(): ComicApi {
        return ComicApi.create()
    }
}