
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

* After clicking on the text field, the bottom navigation bar does not appear above the keyboard. I added this line to the Android Manifest

```Kotlin
android:windowSoftInputMode="stateAlwaysHidden|adjustPan"
```

* The description of comic books was often very long, so I limited its display on the home screen to 5 lines, and the creators to 1 line. 
In the comic details, I added a ScrollView and an extendable screen. Above the button, the starting text is transparent. 
bottom sheet behavior:

```Kotlin
    private fun extendableView() {
        BottomSheetBehavior.from(binding.sheetShape).apply {
            peekHeight = 600
            this.state = BottomSheetBehavior.STATE_COLLAPSED
        }
    }
```
### GIF's 💡
![ezgif com-gif-maker (5)](https://user-images.githubusercontent.com/75754448/119700970-27407c80-be54-11eb-8e87-7ca658b58486.gif)

![ezgif com-gif-maker (6)](https://user-images.githubusercontent.com/75754448/119701332-843c3280-be54-11eb-8734-8f36b3162f6e.gif)

![ezgif com-gif-maker (7)](https://user-images.githubusercontent.com/75754448/119701809-0fb5c380-be55-11eb-8ac4-12d85af36e74.gif)

#### :warning:
