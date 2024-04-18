package com.masterclass.contactsmanagerappwithkotlinroomdbmvvm.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.masterclass.contactsmanagerappwithkotlinroomdbmvvm.R
import com.masterclass.contactsmanagerappwithkotlinroomdbmvvm.database.Contacts
import com.masterclass.contactsmanagerappwithkotlinroomdbmvvm.databinding.ContactListItemBinding


class ContactsAdapter(private var contacts: ArrayList<Contacts>) :
    RecyclerView.Adapter<ContactsAdapter.ContactsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsViewHolder {
        val contactListItemBinding = DataBindingUtil.inflate<ContactListItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.contact_list_item,
            parent,
            false
        )
        return ContactsViewHolder(contactListItemBinding)
    }

    override fun onBindViewHolder(holder: ContactsViewHolder, position: Int) {
        val currentContact = contacts[position]
        holder.contactListItemBinding.contact = currentContact
    }

    override fun getItemCount(): Int {
        return contacts?.size ?: 0
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setContacts(contacts: ArrayList<Contacts>) {
        this.contacts = contacts
        notifyDataSetChanged()
    }

    class ContactsViewHolder(val contactListItemBinding: ContactListItemBinding) :
        RecyclerView.ViewHolder(contactListItemBinding.root)
}