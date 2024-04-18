package com.masterclass.contactsmanagerappwithkotlinroomdbmvvm.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ContactDAO {
    @Insert
    fun insert(contact: Contacts)
    @Delete
    fun delete(contact: Contacts)
    @Query("SELECT * FROM contacts_table")
    fun getAllContacts():LiveData<List<Contacts>>
}