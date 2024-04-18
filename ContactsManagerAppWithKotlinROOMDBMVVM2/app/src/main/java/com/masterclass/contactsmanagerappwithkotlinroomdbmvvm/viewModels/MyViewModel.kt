package com.masterclass.contactsmanagerappwithkotlinroomdbmvvm.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.masterclass.contactsmanagerappwithkotlinroomdbmvvm.database.Contacts
import com.masterclass.contactsmanagerappwithkotlinroomdbmvvm.repositories.ContactsRepository

class MyViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: ContactsRepository = ContactsRepository(application)
    private var allContacts: LiveData<List<Contacts>>? = null

    init {
        allContacts = repository.getAllContacts()
    }

    fun getAllContacts(): LiveData<List<Contacts>> {
        return allContacts!!
    }

    fun addNewContact(contact: Contacts) {
        repository.addContact(contact)
    }

    fun deleteContact(contact: Contacts) {
        repository.deleteContact(contact)
    }
}