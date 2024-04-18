package com.masterclass.contactsmanagerappwithkotlinroomdbmvvm.activityClickHandlers

import android.content.Context
import android.content.Intent
import android.view.View
import com.masterclass.contactsmanagerappwithkotlinroomdbmvvm.activities.AddNewContactActivity

class MainActivityClickHandlers(private val context: Context) {

    fun onFABClick(view: View) {
        val intent = Intent(context.applicationContext, AddNewContactActivity::class.java)
        context.startActivity(intent)
    }
}