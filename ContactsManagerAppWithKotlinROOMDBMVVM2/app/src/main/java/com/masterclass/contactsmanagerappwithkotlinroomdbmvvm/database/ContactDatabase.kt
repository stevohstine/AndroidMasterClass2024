package com.masterclass.contactsmanagerappwithkotlinroomdbmvvm.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Contacts::class], version = 2)
abstract class ContactDatabase : RoomDatabase() {

    abstract fun getContactDAO(): ContactDAO

    companion object {
        @Volatile
        private var dbInstance: ContactDatabase? = null

        fun getInstance(context: Context): ContactDatabase {
            return dbInstance ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ContactDatabase::class.java,
                    "contacts_db"
                ).fallbackToDestructiveMigration()
                    .build()
                dbInstance = instance
                instance
            }
        }
    }
}