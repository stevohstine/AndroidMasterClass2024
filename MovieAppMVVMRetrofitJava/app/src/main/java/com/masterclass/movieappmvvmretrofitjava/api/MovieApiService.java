package com.masterclass.movieappmvvmretrofitjava.api;

import com.masterclass.movieappmvvmretrofitjava.models.Result;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieApiService {
    @GET("movie/popular")
    Call<Result> getPopularMovies(
      @Query("api_key") String api_key
    );
}
