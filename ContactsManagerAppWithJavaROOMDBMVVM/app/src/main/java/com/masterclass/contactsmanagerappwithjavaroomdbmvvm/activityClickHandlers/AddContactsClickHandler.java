package com.masterclass.contactsmanagerappwithjavaroomdbmvvm.activityClickHandlers;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.masterclass.contactsmanagerappwithjavaroomdbmvvm.activities.MainActivity;
import com.masterclass.contactsmanagerappwithjavaroomdbmvvm.database.Contacts;
import com.masterclass.contactsmanagerappwithjavaroomdbmvvm.viewModels.MyViewModel;

public class AddContactsClickHandler {

    Contacts contact;
    Context context;
    MyViewModel myViewModel;

    public AddContactsClickHandler(Contacts contact, Context context, MyViewModel myViewModel) {
        this.contact = contact;
        this.context = context;
        this.myViewModel = myViewModel;
    }

    public void onSubmitButtonClicked(View view){
        if (contact.getName() == null || contact.getPhoneNumber() == null || contact.getEmail() == null){
            Toast.makeText(context, "All fields are required", Toast.LENGTH_SHORT).show();
        }
        else{
            Intent i = new Intent(context, MainActivity.class);

            Contacts c = new Contacts(contact.getName(), contact.getPhoneNumber(), contact.getEmail());
            myViewModel.addNewContact(c);

            context.startActivity(i);
        }
    }
}
