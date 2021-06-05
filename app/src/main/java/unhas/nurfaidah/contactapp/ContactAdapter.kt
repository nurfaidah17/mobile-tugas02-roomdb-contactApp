package unhas.nurfaidah.contactapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.adapter_contact.view.*
import unhas.nurfaidah.contactapp.room.Contact

class ContactAdapter (private val contacts: ArrayList<Contact>, private val listener: OnAdapterListener)
    : RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        return ContactViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.adapter_contact, parent, false)
        )
    }

    override fun getItemCount() = contacts.size

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val contact = contacts[position]
        holder.view.nama_kontak.text = contact.nama_kontak
        holder.view.nama_kontak.setOnClickListener {
            listener.onClick(contact)
        }
        holder.view.icon_edit.setOnClickListener {
            listener.onUpdate(contact)
        }
        holder.view.icon_delete.setOnClickListener {
            listener.onDelete(contact)
        }
    }

    class ContactViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    fun setData(list: List<Contact>) {
        contacts.clear()
        contacts.addAll(list)
        notifyDataSetChanged()
    }

    interface OnAdapterListener {
        fun onClick(contact: Contact)
        fun onUpdate(contact: Contact)
        fun onDelete(contact: Contact)
    }
}