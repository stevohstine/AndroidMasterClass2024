package com.masterclass.moviesmvvmappwithpagingkotlin.paging

import androidx.paging.PagingState
import androidx.paging.rxjava3.RxPagingSource
import com.masterclass.moviesmvvmappwithpagingkotlin.api.APIClient
import com.masterclass.moviesmvvmappwithpagingkotlin.models.Movie
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers


class MoviePagingSource : RxPagingSource<Int, Movie>() {
    override fun getRefreshKey(pagingState: PagingState<Int, Movie>): Int? {
        return null
    }

    override fun loadSingle(loadParams: LoadParams<Int>): Single<LoadResult<Int, Movie>> {
        val page = loadParams.key ?: 1
        return APIClient.getApiInterface()!!
            .getMoviesByPage(page)
            .subscribeOn(Schedulers.io())
            .map { response -> response.movies!! }
            .map { movies -> toLoadResult(movies, page) }
            .onErrorReturn { error -> LoadResult.Error(error) }
    }

    private fun toLoadResult(movies: List<Movie>, page: Int): LoadResult<Int, Movie> {
        return LoadResult.Page(data = movies, prevKey = if (page == 1) null else page - 1, nextKey = page + 1)
    }
}
