package com.example.marvelapp.api

import com.example.marvelapp.basic.Const.Companion.API_KEY
import com.example.marvelapp.basic.Const.Companion.HASH
import com.example.marvelapp.basic.Const.Companion.LIMIT
import com.example.marvelapp.model.MarvelModelData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchComicApi {
    @GET("comics?ts=1&apikey=${API_KEY}&hash=${HASH}&limit=${LIMIT}&offset=0&orderBy=-onsaleDate")
    suspend fun getSearchAllComics(@Query("title") title: String): Response<MarvelModelData>
}