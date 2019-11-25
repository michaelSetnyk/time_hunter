package com.example.timehunter


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView
import de.hdodenhof.circleimageview.CircleImageView


class ViewGroupFragment : Fragment() {

    companion object {
        const val ARG_NAME = "name"
        const val ARG_ICON = "icon"
        const val ARG_DESCRIPTION = "description"
        const val ARG_USERS = "users"

        fun newInstance(group: Group): ViewGroupFragment {
            val fragment = ViewGroupFragment()

            val bundle = Bundle().apply {
                putString(ARG_NAME, group.name)
                putInt(ARG_ICON, group.icon)
                putString(ARG_DESCRIPTION, group.summary)
                putParcelableArrayList(ARG_USERS, group.people)
            }
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val layout = inflater.inflate(R.layout.fragment_view_group, container, false)
        val groupPhotoView = layout.findViewById<CircleImageView>(R.id.photo)
        val titleView = layout.findViewById<TextView>(R.id.name)
        val descriptionView = layout.findViewById<TextView>(R.id.description)
        val membersView = layout.findViewById<RecyclerView>(R.id.users)

        val icon = arguments?.getInt(ARG_ICON)
        val title = arguments?.getString(ARG_NAME)
        val description = arguments?.getString(ARG_DESCRIPTION)
        val users = arguments?.getParcelableArrayList<User>(ARG_USERS)

        if (icon != null) {
            groupPhotoView.setImageResource(icon)
        }

        if (title != null) {
            titleView.text = title
        }

        if (description != null) {
            descriptionView.text = description
        }

        val textView = layout.findViewById<TextView>(R.id.empty_warning)

        if (users != null && users.isNotEmpty()  ){
            val layoutManger = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            membersView.apply {
                setHasFixedSize(true)
                layoutManager = layoutManger
                adapter = UserListAdapter(users)
            }
            textView.visibility=View.GONE
        } else{
            membersView.visibility=View.GONE
            textView.text = "Your Group is empty"
        }
        return layout
    }
}