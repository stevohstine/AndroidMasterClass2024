package com.masterclass.contactsmanagerappwithjavaroomdbmvvm.repositories;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;
import androidx.lifecycle.LiveData;
import com.masterclass.contactsmanagerappwithjavaroomdbmvvm.database.ContactDAO;
import com.masterclass.contactsmanagerappwithjavaroomdbmvvm.database.ContactDatabase;
import com.masterclass.contactsmanagerappwithjavaroomdbmvvm.database.Contacts;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ContactsRepository {
    //Available data sources: RoomDb
    //Execute all methods from the DAO
    private final ContactDAO contactDAO;

    //To avoid app hanging/app crash, we do this task in the background service/ background thread
    //creates a different thread/Threads separation
    ExecutorService executorService;

    //Post results to main thread/Updating the Ui/
    Handler handler;

    public ContactsRepository(Application application) {
        ContactDatabase contactDatabase = ContactDatabase.getInstance(application);
        this.contactDAO = contactDatabase.getContactDAO();
        executorService = Executors.newSingleThreadExecutor();
        handler = new Handler(Looper.getMainLooper());
    }

    public void addContact(Contacts contact){

        //Execute the task in a separate thread using runnable
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                //code is executed asynchronously in a separate thread
                contactDAO.insert(contact);
            }
        });
    }

    public void deleteContact(Contacts contact){
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                contactDAO.delete(contact);
            }
        });
    }

    public LiveData<List<Contacts>> getAllContacts(){
        return contactDAO.getAllContacts();
    }


}
