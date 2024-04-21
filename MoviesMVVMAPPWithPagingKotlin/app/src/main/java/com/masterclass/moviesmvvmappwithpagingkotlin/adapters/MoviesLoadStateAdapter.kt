package com.masterclass.moviesmvvmappwithpagingkotlin.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.masterclass.moviesmvvmappwithpagingkotlin.R
import com.masterclass.moviesmvvmappwithpagingkotlin.databinding.LoadStateItemBinding

class MoviesLoadStateAdapter(private val retryCallback: () -> Unit) :
    LoadStateAdapter<MoviesLoadStateAdapter.LoadStateViewHolder>() {

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        return LoadStateViewHolder.create(parent, retryCallback)
    }

    class LoadStateViewHolder private constructor(itemView: View, retryCallback: () -> Unit) :
        RecyclerView.ViewHolder(itemView) {

        private val progressBar: ProgressBar = itemView.findViewById(R.id.progressBar)
        private val errorMsg: TextView = itemView.findViewById(R.id.errorMsg)
        private val retry: Button = itemView.findViewById(R.id.retryButton)

        init {
            retry.setOnClickListener { retryCallback.invoke() }
        }

        fun bind(loadState: LoadState) {
            if (loadState is LoadState.Error) {
                errorMsg.text = loadState.error.localizedMessage
            }

            progressBar.visibility = if (loadState is LoadState.Loading) View.VISIBLE else View.GONE
            retry.visibility = if (loadState is LoadState.Error) View.VISIBLE else View.GONE
            errorMsg.visibility = if (loadState is LoadState.Error) View.VISIBLE else View.GONE

        }

        companion object {
            fun create(parent: ViewGroup, retryCallback: () -> Unit): LoadStateViewHolder {
                val binding = LoadStateItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return LoadStateViewHolder(binding.root, retryCallback)
            }
        }
    }
}