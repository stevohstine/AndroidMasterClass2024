package com.masterclass.moviesmvvmappwithpagingkotlin.api

import com.masterclass.moviesmvvmappwithpagingkotlin.models.MoviesResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface APIInterface {
    @GET("movie/popular")
    fun getMoviesByPage(
        @Query("page") page: Int
    ): Single<MoviesResponse>
}