package com.masterclass.moviesmvvmappwithpagingkotlin.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.RequestManager
import com.masterclass.moviesmvvmappwithpagingkotlin.adapters.MoviesAdapter
import com.masterclass.moviesmvvmappwithpagingkotlin.adapters.MoviesLoadStateAdapter
import com.masterclass.moviesmvvmappwithpagingkotlin.databinding.ActivityMainBinding
import com.masterclass.moviesmvvmappwithpagingkotlin.utils.GridSpace
import com.masterclass.moviesmvvmappwithpagingkotlin.utils.MovieComparator
import com.masterclass.moviesmvvmappwithpagingkotlin.utils.Utils
import com.masterclass.moviesmvvmappwithpagingkotlin.viewModels.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var moviesAdapter: MoviesAdapter
    private lateinit var mainActivityViewModel: MoviesViewModel

    @Inject
    lateinit var requestManager: RequestManager

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (Utils.API_KEY == null || Utils.API_KEY.isEmpty()) {
            Toast.makeText(this, "Api Key has an error", Toast.LENGTH_SHORT).show()
        }

        moviesAdapter = MoviesAdapter(requestManager,MovieComparator())
        mainActivityViewModel = ViewModelProvider(this)[MoviesViewModel::class.java]

        initRecyclerViewAndAdapter()

        lifecycleScope.launch {
            mainActivityViewModel.flowableMoviePagingData!!
                .onEach { moviePagingData ->
                    moviesAdapter.submitData(lifecycle, moviePagingData)
                }
                .collect()
        }
    }

    private fun initRecyclerViewAndAdapter() {
        val gridLayoutManager = GridLayoutManager(this, 2)
        binding.recyclerView.layoutManager = gridLayoutManager
        binding.recyclerView.addItemDecoration(GridSpace(2, 20, true))
        binding.recyclerView.adapter = moviesAdapter.withLoadStateFooter(MoviesLoadStateAdapter {
            moviesAdapter.retry()
        })

        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (moviesAdapter.getItemViewType(position) == MoviesAdapter.LOADING_ITEM) 1 else 2
            }
        }
    }
}