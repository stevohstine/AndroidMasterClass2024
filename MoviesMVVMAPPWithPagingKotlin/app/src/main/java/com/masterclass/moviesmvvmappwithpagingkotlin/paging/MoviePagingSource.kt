package com.masterclass.moviesmvvmappwithpagingkotlin.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.masterclass.moviesmvvmappwithpagingkotlin.api.APIClient
import com.masterclass.moviesmvvmappwithpagingkotlin.models.Movie
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers


class MoviePagingSource : PagingSource<Int, Movie>() {
    override fun getRefreshKey(pagingState: PagingState<Int, Movie>): Int? {
        // No need to provide a refresh key for now
        return null
    }

    override fun load(params: LoadParams<Int>): Single<LoadResult<Int, Movie>> {
        val page = params.key ?: 1
        return APIClient.getApiInterface()
            .getMoviesByPage(page)
            .subscribeOn(Schedulers.io())
            .map { response -> toLoadResult(response.movies!!, page) }
            .onErrorReturn { error -> LoadResult.Error(error) }
    }

    private fun toLoadResult(movies: List<Movie>, page: Int): LoadResult<Int, Movie> {
        return LoadResult.Page(
            data = movies,
            prevKey = if (page == 1) null else page - 1,
            nextKey = page + 1
        )
    }
}
