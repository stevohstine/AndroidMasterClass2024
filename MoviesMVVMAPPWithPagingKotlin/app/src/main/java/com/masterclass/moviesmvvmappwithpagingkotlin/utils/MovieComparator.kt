package com.masterclass.moviesmvvmappwithpagingkotlin.utils

import androidx.recyclerview.widget.DiffUtil
import com.masterclass.moviesmvvmappwithpagingkotlin.models.Movie

class MovieComparator : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }

    @Suppress("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }
}