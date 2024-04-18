package com.school.androidmvvmfirsttest.viewModels;

import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyViewModel extends ViewModel {

    MutableLiveData<Integer> counter = new MutableLiveData<>();

    public void increaseCounter(View view){
        int currentValue = counter.getValue() != null ? counter.getValue() : 0;
        counter.setValue(currentValue + 1);
    }

    public LiveData<Integer> getCounter(){
        return counter;
    }
}
