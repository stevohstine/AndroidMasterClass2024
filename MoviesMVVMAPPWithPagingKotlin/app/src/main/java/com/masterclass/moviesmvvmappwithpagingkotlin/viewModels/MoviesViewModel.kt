package com.masterclass.moviesmvvmappwithpagingkotlin.viewModels

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.masterclass.moviesmvvmappwithpagingkotlin.models.Movie
import com.masterclass.moviesmvvmappwithpagingkotlin.paging.MoviePagingSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MoviesViewModel : ViewModel() {
    var flowableMoviePagingData: Flow<PagingData<Movie>>? = null
    init {
        init()
    }

    private fun init() {

        //Define Paging Source
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

        //Flowable
        flowableMoviePagingData = pager.flow
            .map { pagingData -> pagingData }
    }
}
