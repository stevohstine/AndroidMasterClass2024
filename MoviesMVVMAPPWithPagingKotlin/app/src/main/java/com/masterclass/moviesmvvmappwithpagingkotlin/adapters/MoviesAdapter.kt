package com.masterclass.moviesmvvmappwithpagingkotlin.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.masterclass.moviesmvvmappwithpagingkotlin.databinding.SingleMovieLayoutBinding
import com.masterclass.moviesmvvmappwithpagingkotlin.models.Movie
import com.masterclass.moviesmvvmappwithpagingkotlin.utils.MovieComparator

class MoviesAdapter(private val glide: RequestManager, movieComparator: MovieComparator) :
    PagingDataAdapter<Movie, MoviesAdapter.MovieViewHolder>(MovieDiffCallback()) {

    companion object {
        const val LOADING_ITEM = 0
        const val MOVIE_ITEM = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = SingleMovieLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MovieViewHolder(binding)
    }

    @SuppressLint("CheckResult")
    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val currentMovie = getItem(position)
        currentMovie?.let {
            glide.load("https://image.tmdb.org/t/p/w500${it.posterPath}")
                .into(holder.binding.imageViewMovie)
            holder.binding.textViewRating.text = it.voteAverage.toString()
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == itemCount - 1) MOVIE_ITEM else LOADING_ITEM
    }

    class MovieViewHolder(val binding: SingleMovieLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)
}

class MovieDiffCallback : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }
}