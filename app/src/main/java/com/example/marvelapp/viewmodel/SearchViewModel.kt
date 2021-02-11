package com.example.marvelapp.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelapp.repository.Repository
import com.example.marvelapp.model.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val useCases: Repository
) : ViewModel() {
    private val resultsMutable = MutableLiveData<ViewState>()
    val observeResults: LiveData<ViewState> = resultsMutable

    fun getCharacterByTitle(title: String) {
        viewModelScope.launch {
            try {
                delay(2000)
                resultsMutable.postValue(ViewState.Loading)
                val response = useCases.getSearchAllComics(title)
                if (response.isSuccessful && !response.body()?.data?.results.isNullOrEmpty() ) {
                    resultsMutable.postValue(ViewState.Success(response.body()?.data?.results))
                    }else{
                    resultsMutable.postValue(ViewState.NotFound)
                }
            } catch (e: Exception) {
                resultsMutable.postValue(ViewState.Error)
            }
        }
    }
    sealed class ViewState {
        object Loading : ViewState()
        data class Success(val results: List<Result>?) : ViewState()
        object Error : ViewState()
        object NotFound : ViewState()
    }
}
