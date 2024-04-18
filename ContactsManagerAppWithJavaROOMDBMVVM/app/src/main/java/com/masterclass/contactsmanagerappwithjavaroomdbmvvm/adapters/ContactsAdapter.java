package com.masterclass.contactsmanagerappwithjavaroomdbmvvm.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.masterclass.contactsmanagerappwithjavaroomdbmvvm.R;
import com.masterclass.contactsmanagerappwithjavaroomdbmvvm.database.Contacts;
import com.masterclass.contactsmanagerappwithjavaroomdbmvvm.databinding.ContactListItemBinding;

import java.util.ArrayList;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ContactsViewHolder> {

    private ArrayList<Contacts> contacts;

    public ContactsAdapter(ArrayList<Contacts> contacts) {
        this.contacts = contacts;
    }

    @NonNull
    @Override
    public ContactsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ContactListItemBinding contactListItemBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.contact_list_item,
                parent,
                false
        );

        return new ContactsViewHolder(contactListItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactsViewHolder holder, int position) {
        Contacts currentContact = contacts.get(position);
        holder.contactListItemBinding.setContact(currentContact);
    }

    @Override
    public int getItemCount() {
        if (contacts != null){
            return contacts.size();
        }else {
            return 0;
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setContacts(ArrayList<Contacts> contacts) {
        this.contacts = contacts;

        notifyDataSetChanged();
    }

    static class ContactsViewHolder extends RecyclerView.ViewHolder{
        private final ContactListItemBinding contactListItemBinding;

        public ContactsViewHolder(@NonNull ContactListItemBinding contactListItemBinding) {
            super(contactListItemBinding.getRoot());
            this.contactListItemBinding = contactListItemBinding;
        }
    }
}
