package com.example.marvelapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.marvelapp.model.Result
import com.example.marvelapp.databinding.ItemComicsBinding
import com.example.marvelapp.viewholder.ComicsViewHolder

class ListComicsAdapter(private val listener: ComicsListener) :
    ListAdapter<Result, ComicsViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicsViewHolder {
        val binding =
            ItemComicsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ComicsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ComicsViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem, listener)
        }
    }

    interface ComicsListener {
        fun onClickComics(result: Result?)
    }
}

private val DiffCallback: DiffUtil.ItemCallback<Result> =

    object : DiffUtil.ItemCallback<Result>() {
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean =
            oldItem == newItem
    }