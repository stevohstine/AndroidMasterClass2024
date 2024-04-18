package com.masterclass.contactsmanagerappwithkotlinroomdbmvvm.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.masterclass.contactsmanagerappwithkotlinroomdbmvvm.R
import com.masterclass.contactsmanagerappwithkotlinroomdbmvvm.activityClickHandlers.AddContactsClickHandler
import com.masterclass.contactsmanagerappwithkotlinroomdbmvvm.database.Contacts
import com.masterclass.contactsmanagerappwithkotlinroomdbmvvm.databinding.ActivityAddNewContactBinding
import com.masterclass.contactsmanagerappwithkotlinroomdbmvvm.viewModels.MyViewModel

class AddNewContactActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddNewContactBinding
    private lateinit var clickHandler: AddContactsClickHandler
    private lateinit var contact: Contacts
    private lateinit var myViewModel: MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_new_contact)

        contact = Contacts()


        myViewModel = ViewModelProvider(this)[MyViewModel::class.java]

        clickHandler = AddContactsClickHandler(contact, this, myViewModel)
        binding.contact = contact
        binding.clickHandler = clickHandler
    }
}
