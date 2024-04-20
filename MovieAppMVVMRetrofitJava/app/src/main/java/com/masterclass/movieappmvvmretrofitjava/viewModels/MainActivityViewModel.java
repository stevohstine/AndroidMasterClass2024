package com.masterclass.movieappmvvmretrofitjava.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.masterclass.movieappmvvmretrofitjava.models.Movie;
import com.masterclass.movieappmvvmretrofitjava.repositories.MovieRepository;

import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {
    //ViewModel is suitable for non Android specific logics with no UI interactions
    //AndroidViewModel is specifically designed for use in Android to access Android Components

    MovieRepository repository;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        this.repository = new MovieRepository(application);
    }

    //LiveData [Get movies]
    public LiveData<List<Movie>> getAllMovies(){
        return repository.getMutableLiveData();
    }
}
