package com.masterclass.contactsmanagerappwithkotlinroomdbmvvm.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contacts_table")
data class Contacts(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "contact_id")
    var id: Int? = 0,

    @ColumnInfo(name = "contact_name")
    var name: String = "",

    @ColumnInfo(name = "contact_phone_number")
    var phoneNumber: String = "",

    @ColumnInfo(name = "contact_email")
    var email: String = ""
)