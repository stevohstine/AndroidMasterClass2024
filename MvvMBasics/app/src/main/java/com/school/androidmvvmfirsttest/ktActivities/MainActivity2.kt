package com.school.androidmvvmfirsttest.ktActivities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.school.androidmvvmfirsttest.R
import com.school.androidmvvmfirsttest.databinding.ActivityMain2Binding
import com.school.androidmvvmfirsttest.ktViewModels.MyViewModel2
import com.school.androidmvvmfirsttest.viewModels.MyViewModel

class MainActivity2 : AppCompatActivity() {

    private lateinit var binding: ActivityMain2Binding

    private val viewModel: MyViewModel2 by lazy {
        ViewModelProvider(this).get(MyViewModel2::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

//        binding.btnIncrement.setOnClickListener {
//            viewModel.incrementCounter(binding.btnIncrement)
//            binding.textValue.text = viewModel.getCounter().toString()
//        }

        //binding myviewModel with my view
        binding.myViewModel = viewModel

        viewModel.getCounter().observe(this) {counter ->
            binding.textValue.text = counter.toString()
        }

    }
}