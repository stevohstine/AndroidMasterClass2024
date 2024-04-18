package com.masterclass.contactsmanagerappwithjavaroomdbmvvm.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.masterclass.contactsmanagerappwithjavaroomdbmvvm.database.Contacts;
import com.masterclass.contactsmanagerappwithjavaroomdbmvvm.repositories.ContactsRepository;

import java.util.List;

public class MyViewModel extends AndroidViewModel {

    private ContactsRepository repository;
    private LiveData<List<Contacts>> allContacts;

    public MyViewModel(@NonNull Application application) {
        super(application);
        this.repository = new ContactsRepository(application);
    }

    public LiveData<List<Contacts>> getAllContacts(){
        allContacts = repository.getAllContacts();
        return allContacts;
    }

    public void addNewContact(Contacts contact){
        repository.addContact(contact);
    }

    public void deleteContact(Contacts contact){
        repository.deleteContact(contact);
    }
}
