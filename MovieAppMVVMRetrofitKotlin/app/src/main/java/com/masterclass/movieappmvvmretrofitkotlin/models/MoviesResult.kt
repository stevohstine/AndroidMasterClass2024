package com.masterclass.movieappmvvmretrofitkotlin.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MoviesResult(
    @SerializedName("page")
    @Expose
    val page:Int,
    @SerializedName("total_pages")
    @Expose
    val totalPages:Int,
    @SerializedName("total_results")
    @Expose
    val totalResults:Int,
    @SerializedName("results")
    @Expose
    var results: List<Movie?>? = null
)
