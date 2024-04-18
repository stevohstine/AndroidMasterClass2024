package com.masterclass.contactsmanagerappwithkotlinroomdbmvvm.repositories

import android.annotation.SuppressLint
import android.app.Application
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import com.masterclass.contactsmanagerappwithkotlinroomdbmvvm.database.ContactDAO
import com.masterclass.contactsmanagerappwithkotlinroomdbmvvm.database.ContactDatabase
import com.masterclass.contactsmanagerappwithkotlinroomdbmvvm.database.Contacts
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class ContactsRepository(application: Application) {

    private val contactDAO: ContactDAO
    private val executorService: ExecutorService
    private val handler: Handler

    init {
        val contactDatabase = ContactDatabase.getInstance(application)
        contactDAO = contactDatabase.getContactDAO()!!
        executorService = Executors.newSingleThreadExecutor()
        handler = Handler(Looper.getMainLooper())
    }

    fun addContact(contact: Contacts) {
        executorService.execute {
            // code is executed asynchronously in a separate thread
            contactDAO.insert(contact)
        }
    }

    fun deleteContact(contact: Contacts) {
        executorService.execute {
            contactDAO.delete(contact)
        }
    }

    fun getAllContacts(): LiveData<List<Contacts>> {
        return contactDAO.getAllContacts()
    }
}