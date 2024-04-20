package com.masterclass.movieappmvvmretrofitkotlin.api

import com.masterclass.movieappmvvmretrofitkotlin.models.MoviesResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {
    @GET("movie/popular")
    fun getPopularMovies(
        @Query("api_key") api_key: String
    ): Call<MoviesResult>
}