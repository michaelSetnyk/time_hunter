package com.example.timehunter

import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.parcel.Parcelize

data class GroupEventItem(val title: String, val description:String, val iconId: Int)

// We should have an Event class but due to time group is fine
@Parcelize
data class Group (val name: String,
                  val summary: String,
                  val icon :Int = R.drawable.ic_group_black_24dp,
                  var people: ArrayList<User> = ArrayList<User>(),
                  var events: ArrayList<Group> = ArrayList<Group>()) :Parcelable

class GroupAdapter (private val groups:ArrayList<Group>):
    RecyclerView.Adapter<GroupAdapter.GroupHolder>() {

    class GroupHolder(v: View) : RecyclerView.ViewHolder(v) {
        val nameView: TextView
        val imageView: ImageView
        val summaryView : TextView
        val cardView: CardView
        var manager: FragmentManager

        init {
            nameView = v.findViewById(R.id.name)
            summaryView = v.findViewById(R.id.summary)
            imageView = v.findViewById(R.id.photo)
            cardView = v.findViewById(R.id.group_item)
            manager = (v.context as FragmentActivity).supportFragmentManager
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GroupHolder {
        val layout =
            LayoutInflater.from(parent.context).inflate(R.layout.group_item, parent, false)
        return GroupHolder(layout)
    }


    override fun onBindViewHolder(holder: GroupHolder, position: Int) {
        val group = groups[position]
        holder.imageView.setImageResource(group.icon)
        holder.nameView.text = group.name
        val a = ViewGroupFragment.newInstance(group).arguments
        holder.cardView.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.viewGroupFragment,a))
    }

    override fun getItemCount() = groups.size

}

