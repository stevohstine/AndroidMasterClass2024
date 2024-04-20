package com.masterclass.moviesmvvmappwithpagingjava.utils;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.masterclass.moviesmvvmappwithpagingjava.models.Movie;

public class MovieComparator extends DiffUtil.ItemCallback<Movie> {
    @Override
    public boolean areItemsTheSame(@NonNull Movie oldItem, @NonNull Movie newItem) {
        return oldItem.getId() == newItem.getId();
    }

    @SuppressLint("DiffUtilEquals")
    @Override
    public boolean areContentsTheSame(@NonNull Movie oldItem, @NonNull Movie newItem) {
        return oldItem.getId() == newItem.getId();
    }
}
