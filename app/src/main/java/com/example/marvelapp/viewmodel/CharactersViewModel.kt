package com.example.marvelapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.desafiomobilemarvel.service.model.comic.ComicModel
import com.example.desafiomobilemarvel.service.model.comic.ResponseComicModel
import com.example.marvelapp.model.Result
import com.example.marvelapp.repository.ComicsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val characterComicsRepository: ComicsRepository
) : ViewModel() {
    private val characterComicsResultMutable = MutableLiveData<List<Result>>()
    val observeCharacterComicsResult: LiveData<List<Result>> = characterComicsResultMutable

    fun getCharacterComics(characterId: Int) {

        viewModelScope.launch {
            try {
                val response = characterComicsRepository.getAllComic(characterId)
                if (response.isSuccessful) {
                    characterComicsResultMutable.postValue(response.body()?.data?.results)
                }
            } catch (e: Exception) {
            }
        }
    }
}


