package com.masterclass.contactsmanagerappwithjavaroomdbmvvm.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Contacts.class}, version = 1)
public abstract class ContactDatabase extends RoomDatabase {
    public abstract ContactDAO getContactDAO();

    //Singleton pattern(Ensuring you only have one instance of the db)
    public static ContactDatabase dbInstance;
    public static synchronized ContactDatabase getInstance(Context context){
        if (dbInstance == null){
            dbInstance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    ContactDatabase.class, "contacts_db")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return dbInstance;
    }
}
