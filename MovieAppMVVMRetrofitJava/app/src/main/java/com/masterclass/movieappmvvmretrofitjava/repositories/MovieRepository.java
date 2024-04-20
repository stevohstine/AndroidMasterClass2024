package com.masterclass.movieappmvvmretrofitjava.repositories;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import com.masterclass.movieappmvvmretrofitjava.R;
import com.masterclass.movieappmvvmretrofitjava.api.MovieApiService;
import com.masterclass.movieappmvvmretrofitjava.api.RetrofitInstance;
import com.masterclass.movieappmvvmretrofitjava.models.Movie;
import com.masterclass.movieappmvvmretrofitjava.models.Result;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository {
    //A repository is used for abstracting all the data sources details and provides
    //a clean API for the ViewModel to fetch and manage data

    private ArrayList<Movie> movies = new ArrayList<>();
    private MutableLiveData<List<Movie>> mutableLiveData = new MutableLiveData<List<Movie>>();
    private Application application;

    public MovieRepository(Application application) {
        this.application = application;
    }

    public MutableLiveData<List<Movie>> getMutableLiveData(){
        MovieApiService movieApiService = RetrofitInstance.getService();

        Call<Result> call = movieApiService.
                getPopularMovies(application.getApplicationContext().getString(R.string.api_key));

        //enqueue executes the network request in the background and handles the response in the Main UI Thread
        //Unlike the execute which handles everything in the Main UI Thread
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                Result result = response.body();
                if (result != null && result.getResults() != null){
                    movies = (ArrayList<Movie>) result.getResults();
                    mutableLiveData.setValue(movies);
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {

            }
        });

        return mutableLiveData;
    }
}
