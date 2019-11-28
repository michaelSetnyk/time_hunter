package com.example.timehunter

import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.core.view.marginEnd
import androidx.recyclerview.widget.RecyclerView
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User (val name:String, val photo :Int = R.drawable.male_placeholder): Parcelable

class UserListAdapter (private val users:ArrayList<User>):
    RecyclerView.Adapter<UserListAdapter.UserHolder>() {

    class UserHolder(v: View) : RecyclerView.ViewHolder(v) {
        val photoView: CircleImageView
        val nameView: TextView

        init {
            photoView = v.findViewById(R.id.photo)
            nameView = v.findViewById(R.id.name)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UserHolder {
        val layout =
            LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)
        val contactChecked = layout.findViewById<CheckBox>(R.id.contact_check)
        val contactName = layout.findViewById<TextView>(R.id.name)
        val contactImage = layout.findViewById<CircleImageView>(R.id.photo)
        val listOfContacts = ContactList.contacts

        contactChecked.setOnClickListener{
            listOfContacts.add(contactName.text.toString())
        }

        return UserHolder(layout)
    }

    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        val user = users[position]
        holder.nameView.text = user.name
        holder.photoView.setImageResource(user.photo)
    }

    override fun getItemCount() = users.size


}
