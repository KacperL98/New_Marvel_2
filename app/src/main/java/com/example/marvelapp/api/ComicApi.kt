package com.example.marvelapp.api

import com.example.marvelapp.model.MarvelModelData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ComicApi {

    @GET("comics?ts=1&apikey=080a502746c8a60aeab043387a56eef0&hash=6edc18ab1a954d230c1f03c590d469d2&limit=25&offset=0&orderBy=-onsaleDate")
    suspend fun getAllComics(): Response<MarvelModelData>

    @GET("comics?ts=1&apikey=080a502746c8a60aeab043387a56eef0&hash=6edc18ab1a954d230c1f03c590d469d2&limit=25&offset=0&orderBy=-onsaleDate")
    suspend fun getSearchAllComics(@Query("title") title: String): Response<MarvelModelData>
}
