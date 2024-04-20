package com.masterclass.moviesmvvmappwithpagingkotlin.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("id")
    @Expose
    var id: Int? = null,

    @SerializedName("poster_path")
    @Expose
    var posterPath: String? = null,

    @SerializedName("vote_average")
    @Expose
    var voteAverage: Double? = null
)
