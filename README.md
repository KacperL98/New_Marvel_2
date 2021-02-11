# MarvelApp

![gleasonmarvel_promo](https://user-images.githubusercontent.com/75754448/107693724-cc24b880-6cae-11eb-98ff-4d0a95e39f6e.jpg)

### Hello👋

The Project was created using

The project was created using [Marvel API](https://developer.marvel.com/). 

### Views
![149293209_121368806556552_2869867575346425538_n](https://user-images.githubusercontent.com/75754448/107694803-13f80f80-6cb0-11eb-9d7e-3518ecfb829a.jpg)

The first view is responsible for displaying the comic list (thumbnail and name). To correctly download photos
from Picasso , I change http to https.

    ```Kotlin
    fun bind(result: Result, listener: ListComicsAdapter.ComicsListener?) {
        with(binding) {
            titleComics.text = result.title
            val url =
                "${result.thumbnail.path}.${result.thumbnail.extension}".replace("http", "https")
            Picasso.get().load(url).into(thumbNailComic)
            root.setOnClickListener { listener?.onClickComics(result) }
        }
    }
    ```
    
Search for comic books by title



### SearchViewModel

   ```Kotlin
fun getCharacterByTitle(title: String) {
        viewModelScope.launch {
            try {
                delay(3000)
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
```
   
   ### SearchListFragment
    ```Kotlin
    binding.etQuery.addTextChangedListener {
            if (it.toString().isNotEmpty()) {
                viewModel.getCharacterByTitle(it.toString())
            }
        }
        ```

