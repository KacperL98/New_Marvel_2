package com.example.marvelapp.repository

import com.example.marvelapp.api.ComicApi
import com.example.marvelapp.api.SearchComicApi
import javax.inject.Inject

class ComicsRepository @Inject constructor(
    private val comicApi: ComicApi,
    private val searchComicApi: SearchComicApi
) {
    suspend fun getAllComics() = comicApi.getAllComics()
    suspend fun getSearchAllComics(title: String) = searchComicApi.getSearchAllComics(title)
}