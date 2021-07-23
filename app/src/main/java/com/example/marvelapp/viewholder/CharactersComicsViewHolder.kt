package com.example.marvelapp.viewholder


import androidx.recyclerview.widget.RecyclerView
import com.example.marvelapp.databinding.ItemCharactersBinding
import com.example.marvelapp.model.Result
import com.example.marvelapp.databinding.ItemComicsBinding
import com.squareup.picasso.Picasso

class CharactersComicsViewHolder(private val binding: ItemCharactersBinding) :
    RecyclerView.ViewHolder(binding.root) {
    private var creators: String = ""
    private var description: String = ""

    fun bind(result: Result) {

        with(binding) {
            textTitle.text = result.title

            result.creators.items.forEach {
                creators += "${it.name}\n"
            }
            binding.textDescriptionComic.text = creators

            result.textObjects.forEach {
                description += "${it.text}\n"
            }
            binding.textNumPages.text = description

            val url =
                "${result.thumbnail.path}.${result.thumbnail.extension}".replace("http", "https")
            Picasso.get().load(url).into(imageComic)
        }
    }
}