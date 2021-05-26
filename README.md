
![marvel-studios-1420x670](https://user-images.githubusercontent.com/75734211/119407049-f5a8a380-bce3-11eb-9400-dada8041d744.jpg)


# MarvelApp

### Hello👋
The project was created using [Marvel API](https://developer.marvel.com/). 

### :hammer: In the application I used:

:white_check_mark:Retrofit

:white_check_mark:Picasso

:white_check_mark:ViewBinding

:white_check_mark:Kotlin Coroutines

:white_check_mark:Dagger Hilt

:white_check_mark:Hilt

:white_check_mark:Architecture Components (LiveData, ViewModel)


First of all, I focused on the quality of the code. I divided the code into smaller parts and tried to keep the clean code rules.

I wanted to take care of details such as:

* The function to hide the keyboard when the user is scrolling the list

```Kotlin
    fun Fragment.hideKeyboard() {
    val inputMethodManager =
        requireContext().getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    var view = requireActivity().currentFocus
    if (view == null) {
        view = View(requireContext())
    }

    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
```

```Kotlin
    private fun closeKeyboardAfterScroll() {
        binding.listOfHeroesRV.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 20) {
                    hideKeyboard()
                }
                super.onScrolled(recyclerView, dx, dy)
            }
        })
    }
```
