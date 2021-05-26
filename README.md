
![marvel-studios-1420x670](https://user-images.githubusercontent.com/75734211/119407049-f5a8a380-bce3-11eb-9400-dada8041d744.jpg)


# MarvelApp

### Hello👋
The project was created using [Marvel API](https://developer.marvel.com/). 

### :hammer: In the application I used:

:white_check_mark:Retrofit

:white_check_mark:Picasso

:white_check_mark:ViewBinding

:white_check_mark:Kotlin coroutines

:white_check_mark:Dagger Hilt

:white_check_mark:Hilt

:white_check_mark:ViewModel


First of all, I focused on the quality of the code. I divided the code into smaller parts and tried to keep the clean code rules.

### Code and description

I used Picasso to download photos. I changed http to https. Same as the previous Marvel app.

   ```Kotlin
       val url = "${result?.thumbnail?.path}.${result?.thumbnail?.extension}".replace(
            "http", "https"
        )
        Picasso.get().load(url).into(binding.backgroundImageViewComic)
```

After clicking the button, we will go to the specific page with the comic.

   ```Kotlin
       binding.btnLink.setOnClickListener {
            val website = result?.urls?.firstOrNull()
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(website?.url)
            startActivity(intent)
        }
```

