package com.masterclass.contactsmanagerappwithkotlinroomdbmvvm.activityClickHandlers

import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.Toast
import com.masterclass.contactsmanagerappwithkotlinroomdbmvvm.activities.MainActivity
import com.masterclass.contactsmanagerappwithkotlinroomdbmvvm.database.Contacts
import com.masterclass.contactsmanagerappwithkotlinroomdbmvvm.viewModels.MyViewModel
import kotlin.random.Random


class AddContactsClickHandler(
    private val contact: Contacts,
    private val context: Context,
    private val myViewModel: MyViewModel
) {

    fun onSubmitButtonClicked(view: View) {
        if (contact.name == null || contact.phoneNumber == null || contact.email == null) {
            Toast.makeText(context, "All fields are required", Toast.LENGTH_SHORT).show()
        } else {
            val intent = Intent(context, MainActivity::class.java)
            val random = Random.Default
            val newContact = Contacts(null, contact.name, contact.phoneNumber, contact.email)
            myViewModel.addNewContact(newContact)

            context.startActivity(intent)
        }
    }
}
