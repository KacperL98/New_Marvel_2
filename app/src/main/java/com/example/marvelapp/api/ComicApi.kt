package com.example.marvelapp.api

import com.example.marvelapp.basic.Const.Companion.API_KEY
import com.example.marvelapp.basic.Const.Companion.HASH
import com.example.marvelapp.model.MarvelModelData
import retrofit2.Response
import retrofit2.http.GET

interface ComicApi {
    @GET("comics?ts=1&apikey=${API_KEY}&hash=${HASH}&limit=25&offset=0&orderBy=-onsaleDate")
    suspend fun getAllComics(): Response<MarvelModelData>
}
