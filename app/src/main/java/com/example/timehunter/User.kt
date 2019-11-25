package com.example.timehunter

import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.parcel.Parcelize

@Parcelize
class User (val photo :Int, val name:String) : Parcelable

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

        return UserHolder(layout)
    }

    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        val user = users[position]
        holder.nameView.text = user.name
        holder.photoView.setImageResource(user.photo)
    }

    override fun getItemCount() = users.size
}