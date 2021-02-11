package com.example.marvelapp.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.example.marvelapp.model.Result
import com.example.marvelapp.adapter.ListComicsAdapter
import com.example.marvelapp.databinding.ItemComicsBinding
import com.squareup.picasso.Picasso

class ComicsViewHolder(private val binding: ItemComicsBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(result: Result, listener: ListComicsAdapter.ComicsListener?) {
        with(binding) {
            titleComics.text = result.title
            val url =
                "${result.thumbnail.path}.${result.thumbnail.extension}".replace("http", "https")
            Picasso.get().load(url).into(thumbNailComic)
            root.setOnClickListener { listener?.onClickComics(result) }
        }
    }
}