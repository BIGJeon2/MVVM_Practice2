package com.example.aac_mvvm_practice

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Contact_Adapter(val ContactOnClicked: (Contact) -> Unit, val ContactOnLongCLicked: (Contact) -> Unit)
    :RecyclerView.Adapter<Contact_Adapter.ViewHolder>(){

    private var contacts: List<Contact> = listOf()

    inner class ViewHolder(itemview: View): RecyclerView.ViewHolder(itemview) {
        val name: TextView = itemview.findViewById(R.id.item_tv_name)
        val number: TextView = itemview.findViewById(R.id.item_tv_number)
        val initial: TextView = itemview.findViewById(R.id.item_tv_initial)
        fun bind(contact: Contact){
            name.text = contact.name
            number.text = contact.number
            initial.text = contact.initial.toString()

            itemView.setOnClickListener {
                ContactOnClicked(contact)
            }

            itemView.setOnLongClickListener {
                ContactOnLongCLicked(contact)
                true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Contact_Adapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_contact, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: Contact_Adapter.ViewHolder, position: Int) {
        holder.bind(contacts[position])
    }

    override fun getItemCount(): Int = contacts.size
//갱신시 사용할 함수
    fun Set_Contact(contacts: List<Contact>){
        this.contacts = contacts
        notifyDataSetChanged()
    }
}