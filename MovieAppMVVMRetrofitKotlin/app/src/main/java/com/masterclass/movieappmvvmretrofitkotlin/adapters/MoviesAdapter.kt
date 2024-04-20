package com.masterclass.movieappmvvmretrofitkotlin.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.masterclass.movieappmvvmretrofitkotlin.databinding.MovieListItemBinding
import com.masterclass.movieappmvvmretrofitkotlin.models.Movie

class MoviesAdapter(private val context: Context, private val movieArrayList: ArrayList<Movie>) :
    RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = MovieListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movieArrayList[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int {
        return movieArrayList.size
    }

    inner class MovieViewHolder(private val binding: MovieListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) {
            binding.movie = movie
            binding.executePendingBindings()
            binding.root.setOnClickListener {
                // Handle item click here
            }
        }
    }
}