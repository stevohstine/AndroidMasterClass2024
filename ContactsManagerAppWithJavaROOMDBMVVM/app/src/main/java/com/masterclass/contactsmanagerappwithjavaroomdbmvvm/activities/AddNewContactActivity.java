package com.masterclass.contactsmanagerappwithjavaroomdbmvvm.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.masterclass.contactsmanagerappwithjavaroomdbmvvm.R;
import com.masterclass.contactsmanagerappwithjavaroomdbmvvm.activityClickHandlers.AddContactsClickHandler;
import com.masterclass.contactsmanagerappwithjavaroomdbmvvm.database.Contacts;
import com.masterclass.contactsmanagerappwithjavaroomdbmvvm.databinding.ActivityAddNewContactBinding;
import com.masterclass.contactsmanagerappwithjavaroomdbmvvm.viewModels.MyViewModel;

public class AddNewContactActivity extends AppCompatActivity {

    ActivityAddNewContactBinding binding;
    AddContactsClickHandler clickHandler;
    Contacts contact;

    MyViewModel myViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_contact);

        contact = new Contacts();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_new_contact);

        myViewModel = new ViewModelProvider(this).get(MyViewModel.class);

        clickHandler = new AddContactsClickHandler(contact, this, myViewModel);
        binding.setContact(contact);
        binding.setClickHandler(clickHandler);

    }
}