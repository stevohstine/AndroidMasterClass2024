package com.masterclass.moviesmvvmappwithpagingjava.viewModels;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import androidx.paging.Pager;
import androidx.paging.PagingConfig;
import androidx.paging.PagingData;
import androidx.paging.rxjava3.PagingRx;

import com.masterclass.moviesmvvmappwithpagingjava.models.Movie;
import com.masterclass.moviesmvvmappwithpagingjava.paging.MoviePagingSource;

import io.reactivex.rxjava3.core.Flowable;
import kotlinx.coroutines.CoroutineScope;

public class MoviesViewModel extends ViewModel {
    Flowable<PagingData<Movie>> flowableMoviePagingData;

    public MoviesViewModel(){
        init();
    }

    public void init(){

        //Define Paging Source
        MoviePagingSource moviePagingSource = new MoviePagingSource();

        Pager<Integer, Movie> pager = new Pager(
            new PagingConfig(
                    20,
                    20,
                    false,
                    20,
                    20*499
            ), () -> moviePagingSource);

        //Flowable
        flowableMoviePagingData = PagingRx.getFlowable(pager);
        CoroutineScope coroutineScope = ViewModelKt.getViewModelScope(this);
        PagingRx.cachedIn(flowableMoviePagingData, coroutineScope);
    }
}
