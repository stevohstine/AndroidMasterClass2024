package com.school.androidmvvmfirsttest.ktViewModels

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel2: ViewModel() {

    private var counter: MutableLiveData<Int> = MutableLiveData()

    fun incrementCounter(view: View) {
        val currentValue: Int = counter.value ?: 0
        counter.value = currentValue + 1
    }

    fun getCounter(): LiveData<Int>{
        return counter
    }

}