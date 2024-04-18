package com.masterclass.contactsmanagerappwithjavaroomdbmvvm.activityClickHandlers;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.masterclass.contactsmanagerappwithjavaroomdbmvvm.activities.AddNewContactActivity;

public class MainActivityClickHandlers {
    Context context;

    public MainActivityClickHandlers(Context context) {
        this.context = context;
    }

    public void onFABClick(View view){
        Intent i = new Intent(context.getApplicationContext(), AddNewContactActivity.class);
        context.startActivity(i);
    }
}
