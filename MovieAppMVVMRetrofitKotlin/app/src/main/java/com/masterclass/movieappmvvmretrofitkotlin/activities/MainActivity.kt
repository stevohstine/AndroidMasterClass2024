package com.masterclass.movieappmvvmretrofitkotlin.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.masterclass.movieappmvvmretrofitkotlin.R
import com.masterclass.movieappmvvmretrofitkotlin.adapters.MoviesAdapter
import com.masterclass.movieappmvvmretrofitkotlin.databinding.ActivityMainBinding
import com.masterclass.movieappmvvmretrofitkotlin.models.Movie
import com.masterclass.movieappmvvmretrofitkotlin.viewModels.MainActivityViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var movies: ArrayList<Movie>
    private lateinit var recyclerView: RecyclerView
    private lateinit var moviesAdapter: MoviesAdapter
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        getPopularMovies()

        swipeRefreshLayout = binding.swipeLayout
        swipeRefreshLayout.setColorSchemeResources(R.color.black)
        swipeRefreshLayout.setOnRefreshListener {
            getPopularMovies()
        }
    }

    private fun getPopularMovies() {
        viewModel.getAllMovies().observe(this, Observer { moviesFromLiveData ->
            movies = moviesFromLiveData as ArrayList<Movie>
            displayMoviesInRecyclerView()
            if (swipeRefreshLayout.isRefreshing) {
                swipeRefreshLayout.isRefreshing = false
            }
        })
    }

    @Suppress("NotifyDataSetChanged")
    private fun displayMoviesInRecyclerView() {
        recyclerView = binding.recyclerView
        moviesAdapter = MoviesAdapter(this, movies)
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.adapter = moviesAdapter
        moviesAdapter.notifyDataSetChanged()
    }
}