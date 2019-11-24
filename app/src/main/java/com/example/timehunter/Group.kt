package com.example.timehunter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Group (val name: String, val summary: String, val icon :Int)

class GroupAdapter (private val groups:ArrayList<Group>):
    RecyclerView.Adapter<GroupAdapter.GroupHolder>() {

    class GroupHolder(v: View) : RecyclerView.ViewHolder(v) {
        val nameView: TextView
        val imageView: ImageView
        val summaryView : TextView

        init {
            nameView = v.findViewById(R.id.name)
            summaryView = v.findViewById(R.id.summary)
            imageView = v.findViewById(R.id.photo)
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
        holder.summaryView.text = group.summary
    }

    override fun getItemCount() = groups.size

}

