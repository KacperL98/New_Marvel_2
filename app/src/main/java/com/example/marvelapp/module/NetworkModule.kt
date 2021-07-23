package com.example.marvelapp.module

import com.example.marvelapp.api.ComicApi
import com.example.marvelapp.api.ComicService
import com.example.marvelapp.api.SearchComicApi
import com.example.marvelapp.basic.Const.Companion.BASE_URL
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideCase(gson: Gson): Retrofit {
        val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }
        val client = OkHttpClient.Builder()
            .addInterceptor(logger)
            .build()
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Singleton
    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    internal fun comicApi(retrofit: Retrofit): ComicApi {
        return retrofit.create(ComicApi::class.java)
    }

    @Provides
    internal fun searchComicApi(retrofit: Retrofit): SearchComicApi {
        return retrofit.create(SearchComicApi::class.java)
    }

    @Provides
    internal fun charactersComicsService(retrofit: Retrofit): ComicService {
        return retrofit.create(ComicService::class.java)
    }

}

