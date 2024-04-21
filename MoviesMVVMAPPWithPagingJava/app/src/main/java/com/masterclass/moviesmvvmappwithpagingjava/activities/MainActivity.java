package com.masterclass.moviesmvvmappwithpagingjava.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Toast;

import com.bumptech.glide.RequestManager;
import com.masterclass.moviesmvvmappwithpagingjava.R;
import com.masterclass.moviesmvvmappwithpagingjava.adapters.MoviesAdapter;
import com.masterclass.moviesmvvmappwithpagingjava.adapters.MoviesLoadStateAdapter;
import com.masterclass.moviesmvvmappwithpagingjava.databinding.ActivityMainBinding;
import com.masterclass.moviesmvvmappwithpagingjava.utils.GridSpace;
import com.masterclass.moviesmvvmappwithpagingjava.utils.MovieComparator;
import com.masterclass.moviesmvvmappwithpagingjava.utils.Utils;
import com.masterclass.moviesmvvmappwithpagingjava.viewModels.MoviesViewModel;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    MoviesViewModel mainActivityViewModel;
    ActivityMainBinding binding;
    MoviesAdapter moviesAdapter;

    @Inject
    RequestManager requestManager;

    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (Utils.API_KEY == null || Utils.API_KEY.isEmpty()){
            Toast.makeText(this, "Api Key has an error", Toast.LENGTH_SHORT).show();
        }

        moviesAdapter = new MoviesAdapter(new MovieComparator(), requestManager);
        mainActivityViewModel = new ViewModelProvider(this).get(MoviesViewModel.class);

        initRecyclerViewAndAdapter();

        mainActivityViewModel.flowableMoviePagingData.subscribe(moviePagingData -> {
            moviesAdapter.submitData(getLifecycle(), moviePagingData);
        });
    }

    private void initRecyclerViewAndAdapter() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        binding.recyclerView.setLayoutManager(gridLayoutManager);
        binding.recyclerView.addItemDecoration(new GridSpace(2,20,true));
        binding.recyclerView.setAdapter(
                moviesAdapter.withLoadStateFooter(new MoviesLoadStateAdapter(view ->{
                    moviesAdapter.retry();
                }))
        );

        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return moviesAdapter.getItemViewType(position) == MoviesAdapter.LOADING_ITEM ? 1 : 2;
            }
        });
    }
}