package com.example.marvelapp.viewholder


import androidx.recyclerview.widget.RecyclerView
import com.example.marvelapp.model.Result
import com.example.marvelapp.databinding.ItemComicsBinding
import com.squareup.picasso.Picasso

class ComicsViewHolder(private val binding: ItemComicsBinding) :
    RecyclerView.ViewHolder(binding.root) {
    private var creators: String = ""
    private var description: String = ""

    fun bind(result: Result, listener: (Result) -> Unit) {

        with(binding) {
            titleComics.text = result.title

            result.creators.items.forEach {
                creators += "${it.name}\n"
            }
            binding.creatorsComic.text = creators

            result.textObjects.forEach {
                description += "${it.text}\n"
            }
            binding.descriptionComicsText.text = description
            val url =
                "${result.thumbnail.path}.${result.thumbnail.extension}".replace("http", "https")
            Picasso.get().load(url).into(thumbNailComic)
            root.setOnClickListener { listener.invoke(result) }
        }
    }
}