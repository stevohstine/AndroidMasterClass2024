package com.masterclass.movieappmvvmretrofitkotlin.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.masterclass.movieappmvvmretrofitkotlin.models.Movie
import com.masterclass.movieappmvvmretrofitkotlin.repositories.MovieRepository

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {
    // ViewModel is suitable for non-Android specific logics with no UI interactions
    // AndroidViewModel is specifically designed for use in Android to access Android Components

    private val repository: MovieRepository = MovieRepository(application)

    // LiveData [Get movies]
    fun getAllMovies(): LiveData<List<Movie>> {
        return repository.getMutableLiveData()
    }
}