package com.masterclass.contactsmanagerappwithjavaroomdbmvvm.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.masterclass.contactsmanagerappwithjavaroomdbmvvm.R;
import com.masterclass.contactsmanagerappwithjavaroomdbmvvm.activityClickHandlers.MainActivityClickHandlers;
import com.masterclass.contactsmanagerappwithjavaroomdbmvvm.adapters.ContactsAdapter;
import com.masterclass.contactsmanagerappwithjavaroomdbmvvm.database.ContactDatabase;
import com.masterclass.contactsmanagerappwithjavaroomdbmvvm.database.Contacts;
import com.masterclass.contactsmanagerappwithjavaroomdbmvvm.databinding.ActivityMainBinding;
import com.masterclass.contactsmanagerappwithjavaroomdbmvvm.viewModels.MyViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //DataSource
    private ContactDatabase contactDatabase;
    private ArrayList<Contacts> contactsArrayList = new ArrayList<>();

    //Adapters
    private ContactsAdapter adapter;

    //Bindings
    private ActivityMainBinding mainBinding;
    private MainActivityClickHandlers clickHandlers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Binding
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        clickHandlers = new MainActivityClickHandlers(this);
        mainBinding.setClickHandler(clickHandlers);

        //Recyclerview
        RecyclerView recyclerView = mainBinding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        //Database
        contactDatabase = ContactDatabase.getInstance(this);

        //ViewModel
        MyViewModel viewModel = new ViewModelProvider(this).get(MyViewModel.class);

        //Insert 1 record
//        Contacts contact1 = new Contacts("Stephen", "0718024761","stephen@gmail.com");
//        viewModel.addNewContact(contact1);

        //Observe data changes
        viewModel.getAllContacts().observe(this, new Observer<List<Contacts>>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onChanged(List<Contacts> contacts) {
                contactsArrayList.clear();

                contactsArrayList.addAll(contacts);

                adapter.notifyDataSetChanged();
            }
        });

        //Adapter
        adapter = new ContactsAdapter(contactsArrayList);

        //Set adapter to recycler view
        recyclerView.setAdapter(adapter);

        //Implement swipe to delete
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                Contacts c = contactsArrayList.get(viewHolder.getAdapterPosition());
                viewModel.deleteContact(c);
                Toast.makeText(getApplicationContext(), c.Name + " contact deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);
    }
}