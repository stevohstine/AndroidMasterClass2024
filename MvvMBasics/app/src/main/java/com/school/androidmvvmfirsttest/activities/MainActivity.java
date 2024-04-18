package com.school.androidmvvmfirsttest.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import com.school.androidmvvmfirsttest.databinding.ActivityMainBinding;
import com.school.androidmvvmfirsttest.viewModels.MyViewModel;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding mainBinding;
    MyViewModel viewModel = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainBinding= ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());

        if (viewModel == null) {
            viewModel = new ViewModelProvider(this).get(MyViewModel.class);
        }

//        mainBinding.btnIncrement.setOnClickListener(new View.OnClickListener() {
//            @SuppressLint("SetTextI18n")
//            @Override
//            public void onClick(View v) {
//                viewModel.increaseCounter(mainBinding.btnIncrement);
//            }
//        });

        //You can replace button click by binding viewModel method with dataBinding
        mainBinding.setMyviewmodel(viewModel);

        //observe livedata
        viewModel.getCounter().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer counter) {
                //update ui when livedata changes
                mainBinding.textValue.setText(String.valueOf(counter));
            }
        });
    }
}