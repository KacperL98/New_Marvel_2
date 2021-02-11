package com.example.marvelapp.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelapp.model.Result
import com.example.marvelapp.R
import com.example.marvelapp.adapter.ListSearchAdapter
import com.example.marvelapp.databinding.ItemSearchBinding
import com.squareup.picasso.Picasso

class SearchViewHolder(private val binding: ItemSearchBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(result: Result, listener: ListSearchAdapter.SearchListener?) {
        with(binding) {
            titleComicsSearch.text = result.title
            val url =
                "${result.thumbnail.path}.${result.thumbnail.extension}".replace("http", "https")
            Picasso.get().load(url).into(thumbNailSearch)
            root.setOnClickListener { listener?.onClickSearch(result) }
        }
    }

    companion object {
        fun create(parent: ViewGroup): SearchViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_search, parent, false)
            val binding = ItemSearchBinding.bind(view)
            return SearchViewHolder(binding)
        }
    }
}