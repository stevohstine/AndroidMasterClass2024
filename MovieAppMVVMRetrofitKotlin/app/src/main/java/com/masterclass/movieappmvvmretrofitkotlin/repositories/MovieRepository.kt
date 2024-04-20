package com.masterclass.movieappmvvmretrofitkotlin.repositories

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.masterclass.movieappmvvmretrofitkotlin.R
import com.masterclass.movieappmvvmretrofitkotlin.api.RetrofitInstance
import com.masterclass.movieappmvvmretrofitkotlin.models.Movie
import com.masterclass.movieappmvvmretrofitkotlin.models.MoviesResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRepository(private val application: Application) {
    private var movies = ArrayList<Movie>()
    private val mutableLiveData = MutableLiveData<List<Movie>>()

    fun getMutableLiveData(): MutableLiveData<List<Movie>> {
        val movieApiService = RetrofitInstance.getService()
        val apiKey = application.getString(R.string.api_key)

        val call = movieApiService.getPopularMovies(apiKey)

        call.enqueue(object : Callback<MoviesResult> {
            override fun onResponse(call: Call<MoviesResult>, response: Response<MoviesResult>) {
                val result = response.body()
                if (result?.results != null) {
                    movies.clear()
                    mutableLiveData.value = (result.results ?: emptyList()) as List<Movie>?
                }
            }

            override fun onFailure(call: Call<MoviesResult>, t: Throwable) {
                // Handle failure
            }
        })

        return mutableLiveData
    }
}
