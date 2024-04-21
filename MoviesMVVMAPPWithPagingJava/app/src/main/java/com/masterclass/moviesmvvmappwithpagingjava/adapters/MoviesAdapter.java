package com.masterclass.moviesmvvmappwithpagingjava.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagingDataAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.RequestManager;
import com.masterclass.moviesmvvmappwithpagingjava.databinding.SingleMovieLayoutBinding;
import com.masterclass.moviesmvvmappwithpagingjava.models.Movie;


public class MoviesAdapter extends PagingDataAdapter<Movie, MoviesAdapter.MovieViewHolder> {

    public static final int LOADING_ITEM = 0;
    public static final int MOVIE_ITEM = 1;
    RequestManager glide;

    public MoviesAdapter(@NonNull DiffUtil.ItemCallback<Movie> diffCallback, RequestManager glide) {
        super(diffCallback);
        this.glide = glide;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MovieViewHolder(SingleMovieLayoutBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent,false));
    }

    @SuppressLint("CheckResult")
    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie currentMovie = getItem(position);

        if (currentMovie != null){
            glide.load("https://image.tmdb.org/t/p/w500" + currentMovie.getPosterPath())
                    .into(holder.movieItemBinding.imageViewMovie);
            holder.movieItemBinding.textViewRating.setText(String.valueOf(currentMovie.getVoteAverage()));
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position == getItemCount() ? MOVIE_ITEM : LOADING_ITEM;
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder{
        SingleMovieLayoutBinding movieItemBinding;
        public MovieViewHolder(@NonNull SingleMovieLayoutBinding movieItemBinding){
            super(movieItemBinding.getRoot());
            this.movieItemBinding = movieItemBinding;
        }
    }
}
