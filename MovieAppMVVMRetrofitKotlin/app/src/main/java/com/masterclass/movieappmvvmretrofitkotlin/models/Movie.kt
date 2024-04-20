package com.masterclass.movieappmvvmretrofitkotlin.models

import android.widget.ImageView
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.masterclass.movieappmvvmretrofitkotlin.BR

class Movie : BaseObservable() {
    @SerializedName("adult")
    @Expose
    var adult: Boolean? = null

    @SerializedName("backdrop_path")
    @Expose
    var backdropPath: String? = null

    @SerializedName("genre_ids")
    @Expose
    var genreIds: List<Int>? = null

    @SerializedName("id")
    @Expose
    var id: Int? = null

    @SerializedName("original_language")
    @Expose
    var originalLanguage: String? = null

    @SerializedName("original_title")
    @Expose
    var originalTitle: String? = null

    @SerializedName("overview")
    @Expose
    var overview: String? = null

    @SerializedName("popularity")
    @Expose
    var popularity: Double? = null

    @SerializedName("poster_path")
    @Expose
    var posterPath: String? = null

    @SerializedName("release_date")
    @Expose
    var releaseDate: String? = null

    @SerializedName("title")
    @Expose
    @get:Bindable
    var title: String? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.title)
        }

    @SerializedName("video")
    @Expose
    var video: Boolean? = null

    @SerializedName("vote_average")
    @Expose
    @get:Bindable
    var voteAverage: Double? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.voteAverage)
        }

    @SerializedName("vote_count")
    @Expose
    var voteCount: Int? = null

    companion object {
        @BindingAdapter("posterPath")
        @JvmStatic
        fun loadImage(imageView: ImageView, imageUrl: String?) {
            imageUrl?.let {
                val imagePath = "https://image.tmdb.org/t/p/w500/$imageUrl"
                Glide.with(imageView.context)
                    .load(imagePath)
                    .into(imageView)
            }
        }
    }
}


