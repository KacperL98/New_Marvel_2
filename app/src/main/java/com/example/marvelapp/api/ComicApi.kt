package com.example.marvelapp.api

import com.example.marvelapp.model.MarvelModelData
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ComicApi {

    @GET("comics?ts=1&apikey=$API_KEY&hash=$HASH&limit=25&offset=0&orderBy=-onsaleDate")
    suspend fun getAllComics(): Response<MarvelModelData>

    @GET("comics?ts=1&apikey=080a502746c8a60aeab043387a56eef0&hash=6edc18ab1a954d230c1f03c590d469d2&limit=25&offset=0&orderBy=-onsaleDate")
    suspend fun getSearchAllComics(@Query("title") title: String): Response<MarvelModelData>

    companion object {
        private const val BASE_URL = "https://gateway.marvel.com/v1//public/"
        const val API_KEY = "080a502746c8a60aeab043387a56eef0"
        const val HASH = "6edc18ab1a954d230c1f03c590d469d2"

        fun create () : ComicApi {
            val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC}
            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()


            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ComicApi::class.java)
        }

    }
}
