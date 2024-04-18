package com.masterclass.contactsmanagerappwithjavaroomdbmvvm.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "contacts_table")
public class Contacts {
    @ColumnInfo(name = "contact_id")
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "contact_name")
    public String Name;
    @ColumnInfo(name = "contact_phone_number")
    public String PhoneNumber;
    @ColumnInfo(name = "contact_email")
    public String Email;

    public Contacts(String name, String phoneNumber, String email) {
        Name = name;
        PhoneNumber = phoneNumber;
        Email = email;
    }

    //to prevent null pointer exceptions
    public Contacts() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
