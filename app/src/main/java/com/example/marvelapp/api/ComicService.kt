package com.example.marvelapp.api

import com.example.desafiomobilemarvel.service.model.comic.ResponseComicModel
import com.example.marvelapp.basic.Const
import com.example.marvelapp.model.MarvelModelData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ComicService {
    @GET("characters/{characterId}/comics?ts=1&apikey=${Const.API_KEY}&hash=${Const.HASH}&limit=${Const.LIMIT}&offset=0&orderBy=-onsaleDate")
    suspend fun getAllComic(@Path("character_id") character_id: Int) : Response<ResponseComicModel>
}
