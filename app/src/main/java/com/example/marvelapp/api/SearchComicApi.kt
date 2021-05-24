package com.example.marvelapp.api

import com.example.marvelapp.basic.Const
import com.example.marvelapp.model.MarvelModelData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchComicApi {
    @GET("comics?ts=1&apikey=${Const.API_KEY}&hash=${Const.HASH}&limit=25&offset=0&orderBy=-onsaleDate")
    suspend fun getSearchAllComics(@Query("title") title: String): Response<MarvelModelData>
}