package com.example.marvelapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.marvelapp.databinding.ItemCharactersBinding
import com.example.marvelapp.model.Result
import com.example.marvelapp.viewholder.CharactersComicsViewHolder

class VerticalComicsModel() :
    ListAdapter<Result, CharactersComicsViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersComicsViewHolder {
        val binding =
            ItemCharactersBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharactersComicsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharactersComicsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Result>() {

            override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean =
                oldItem == newItem
        }
    }
}
