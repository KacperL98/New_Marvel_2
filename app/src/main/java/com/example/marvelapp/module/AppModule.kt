package com.example.marvelapp.module

import com.example.marvelapp.api.ComicApi
import com.example.marvelapp.repository.Repository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson): Retrofit {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder()
        client.addInterceptor(loggingInterceptor)
        return Retrofit.Builder()
            .baseUrl("https://gateway.marvel.com/v1//public/")
            .client(client.build())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }
    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideCharacterApi(retrofit: Retrofit): ComicApi = retrofit.create(
        ComicApi::class.java
    )

    @Singleton
    @Provides
    fun provideCase(apiService: ComicApi) =
        Repository(apiService)


}