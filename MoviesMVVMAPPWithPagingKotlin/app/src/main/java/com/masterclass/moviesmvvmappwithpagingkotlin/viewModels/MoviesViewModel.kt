package com.masterclass.moviesmvvmappwithpagingkotlin.viewModels

import androidx.lifecycle.ViewModel

import androidx.lifecycle.*

import androidx.paging.Pager

import androidx.paging.PagingConfig

import androidx.paging.PagingData

import androidx.paging.rxjava3.*

import com.masterclass.moviesmvvmappwithpagingkotlin.models.Movie

import com.masterclass.moviesmvvmappwithpagingkotlin.paging.MoviePagingSource

import kotlinx.coroutines.flow.Flow

import androidx.paging.rxjava3.*

import io.reactivex.rxjava3.core.Flowable

import kotlinx.coroutines.CoroutineScope


class MoviesViewModel : ViewModel() {
    lateinit var flowableMoviePagingData: Flow<PagingData<Movie>>

    init {
        init()
    }

    private fun init() {

        // Define Paging Source
        val moviePagingSource = MoviePagingSource()

        val pager = Pager(
            PagingConfig(
                pageSize = 20,
                prefetchDistance = 20,
                enablePlaceholders = false,
                initialLoadSize = 20,
                maxSize = 20 * 499
            )
        ) { moviePagingSource }

        // Flowable
        val flowableMoviePagingData = PagingRx.getFlowable(pager)
        val coroutineScope = ViewModelKt.getViewModelScope(this)
        PagingRx.cachedIn(flowableMoviePagingData, coroutineScope)
    }
}
