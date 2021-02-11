package com.example.marvelapp.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelapp.model.Result
import com.example.marvelapp.R
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

    companion object {
        fun create(parent: ViewGroup): ComicsViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_comics, parent, false)
            val binding = ItemComicsBinding.bind(view)
            return ComicsViewHolder(binding)
        }
    }


}