package com.masterclass.contactsmanagerappwithkotlinroomdbmvvm.activities

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.masterclass.contactsmanagerappwithkotlinroomdbmvvm.R
import com.masterclass.contactsmanagerappwithkotlinroomdbmvvm.activityClickHandlers.MainActivityClickHandlers
import com.masterclass.contactsmanagerappwithkotlinroomdbmvvm.adapters.ContactsAdapter
import com.masterclass.contactsmanagerappwithkotlinroomdbmvvm.database.ContactDatabase
import com.masterclass.contactsmanagerappwithkotlinroomdbmvvm.database.Contacts
import com.masterclass.contactsmanagerappwithkotlinroomdbmvvm.databinding.ActivityMainBinding
import com.masterclass.contactsmanagerappwithkotlinroomdbmvvm.viewModels.MyViewModel

class MainActivity : AppCompatActivity() {

    // DataSource
    private lateinit var contactDatabase: ContactDatabase
    private val contactsArrayList = ArrayList<Contacts>()

    // Adapters
    private lateinit var adapter: ContactsAdapter

    // Bindings
    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var clickHandlers: MainActivityClickHandlers

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // Binding
        clickHandlers = MainActivityClickHandlers(this)
        mainBinding.clickHandler = clickHandlers

        // RecyclerView setup
        val recyclerView = mainBinding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        // Database setup
        contactDatabase = ContactDatabase.getInstance(this)

        // ViewModel setup
        val viewModel = ViewModelProvider(this)[MyViewModel::class.java]

        //Insert 1 record

//        val contact1:Contacts = Contacts(2,"John", "0718024761","stephen@gmail.com")
//        viewModel.addNewContact(contact1)

        // Observe data changes
        viewModel.getAllContacts().observe(this, Observer { contacts ->
            contactsArrayList.clear()
            contactsArrayList.addAll(contacts)
            adapter.notifyDataSetChanged()
        })

        // Adapter setup
        adapter = ContactsAdapter(contactsArrayList)

        // Set adapter to RecyclerView
        recyclerView.adapter = adapter

        // Attach ItemTouchHelper to RecyclerView
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(@NonNull recyclerView: RecyclerView, @NonNull viewHolder: RecyclerView.ViewHolder, @NonNull target: RecyclerView.ViewHolder): Boolean {
                return false
            }

            override fun onSwiped(@NonNull viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val c = contactsArrayList[viewHolder.adapterPosition]
                viewModel.deleteContact(c)
                Toast.makeText(applicationContext, "${c.name} contact deleted", Toast.LENGTH_SHORT).show()
            }
        }).attachToRecyclerView(recyclerView)
    }
}