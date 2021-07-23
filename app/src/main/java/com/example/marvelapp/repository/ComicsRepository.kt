package com.example.marvelapp.repository

import com.example.marvelapp.api.ComicApi
import com.example.marvelapp.api.ComicService
import com.example.marvelapp.api.SearchComicApi
import javax.inject.Inject

class ComicsRepository @Inject constructor(
    private val comicApi: ComicApi,
    private val searchComicApi: SearchComicApi,
    private val comicService: ComicService

) {
    suspend fun getAllComics() = comicApi.getAllComics()
    suspend fun getSearchAllComics(title: String) = searchComicApi.getSearchAllComics(title)
    suspend fun getAllComic(character_id: Int) = comicService.getAllComic(character_id)
}