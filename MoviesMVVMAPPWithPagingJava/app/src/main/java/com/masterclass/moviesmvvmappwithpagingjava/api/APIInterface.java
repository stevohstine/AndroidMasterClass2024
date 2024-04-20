package com.masterclass.moviesmvvmappwithpagingjava.api;

import com.masterclass.moviesmvvmappwithpagingjava.models.MoviesResponse;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {
    @GET("movie/popular")
    Single<MoviesResponse> getMoviesByPage(@Query("page") int page);
}
