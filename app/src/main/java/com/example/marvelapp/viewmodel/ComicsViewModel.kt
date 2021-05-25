package com.example.marvelapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelapp.repository.ComicsRepository
import com.example.marvelapp.model.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ComicsViewModel @Inject constructor(
    private val useCases: ComicsRepository

) : ViewModel() {

    private val resultsMutable = MutableLiveData<List<Result>>()
    val observeResults: LiveData<List<Result>> = resultsMutable

    init {
        viewModelScope.launch {
            try {
                val response = useCases.getAllComics()
                if (response.isSuccessful) {
                    resultsMutable.postValue(response.body()?.data?.results)
                }
            } catch (e: Exception) {
            }
        }
    }
}
