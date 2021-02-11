package com.example.marvelapp.repository

import com.example.marvelapp.api.ComicApi
import javax.inject.Inject

class Repository @Inject constructor(private val comicApi: ComicApi) {

    suspend fun getAllComics() = comicApi.getAllComics()

    suspend fun getSearchAllComics(title: String) = comicApi.getSearchAllComics(title)
}