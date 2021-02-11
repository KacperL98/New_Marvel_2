package com.example.marvelapp.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelapp.Model.Item
import com.example.marvelapp.repository.Repository
import com.example.marvelapp.Model.Result
import kotlinx.coroutines.launch

class ComicsViewModel @ViewModelInject constructor(
    private val useCases: Repository
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