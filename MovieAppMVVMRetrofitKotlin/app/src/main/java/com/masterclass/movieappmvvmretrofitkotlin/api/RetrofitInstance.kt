package com.masterclass.movieappmvvmretrofitkotlin.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private const val BASE_URL = "https://api.themoviedb.org/3/"

    fun getService(): MovieApiService {
        return retrofit.create(MovieApiService::class.java)
    }
}
