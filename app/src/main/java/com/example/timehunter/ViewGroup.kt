package com.example.timehunter


import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.google.android.material.navigation.NavigationView
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.fragment_view_group.view.*
import java.lang.IllegalStateException

class ViewGroupFragment : Fragment() {

    companion object {
        private lateinit var GROUP :Group
        const val ARG_NAME = "name"
        const val ARG_ICON = "icon"
        const val ARG_DESCRIPTION = "description"
        const val ARG_USERS = "users"

        fun newInstance(group: Group): ViewGroupFragment {
            GROUP = group
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
        val membersView = layout.findViewById<RecyclerView>(R.id.contacts)
        val eventsView = layout.findViewById<RecyclerView>(R.id.upcomingEvents)
        val contactsListViewer = layout.findViewById<RecyclerView>(R.id.contacts_list)
        val calendarButton = layout.findViewById<MaterialButton>(R.id.calendarButton)
        val weekCalendarView = layout.findViewById<MaterialCalendarView>(R.id.weekCalendarView)
        val leaveButton = layout.findViewById<MaterialButton>(R.id.leave)
        val openContacts = layout.findViewById<MaterialButton>(R.id.open_contacts)
        val contactsDrawer = layout.findViewById<DrawerLayout>(R.id.contacts_drawer)

        val icon = arguments?.getInt(ARG_ICON)
        val title = arguments?.getString(ARG_NAME)
        val description = arguments?.getString(ARG_DESCRIPTION)
        var users = arguments?.getParcelableArrayList<User>(ARG_USERS)

        if (icon != null) {
            groupPhotoView.setImageResource(icon)
        }
        if (title != null) {
            titleView.text = title
        }
        if (description != null) {
            descriptionView.text = description
        }

        if (users == null || users.isEmpty()) {
            users = ArrayList<User>()
        }

        weekCalendarView.topbarVisible=false

        configureContacts(membersView,contactsListViewer, users)

        val events = ArrayList<Group>()
        confiureEvents(eventsView, events)

        val fm = childFragmentManager

        calendarButton.setOnClickListener {
            val calendarFragment = CalendarDialog()
            calendarFragment.show(fm,"Calendar")
        }

        openContacts.setOnClickListener {
            if (contactsDrawer.isDrawerOpen(GravityCompat.END)){
                contactsDrawer.closeDrawer(GravityCompat.END)
            } else {
                contactsDrawer.openDrawer(GravityCompat.END)
            }
        }

        leaveButton.setOnClickListener {
            GroupsData.groups.remove(GROUP)
            findNavController().popBackStack()
        }

        return layout
    }



    fun configureContacts(iconsViewer: RecyclerView, contactsList: RecyclerView, contacts: ArrayList<User>) {
        val layoutManger = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        iconsViewer.apply {
            setHasFixedSize(true)
            layoutManager = layoutManger
            adapter = ContactsAdapter(contacts)
        }

        val contactsListManger = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        contactsList.apply {
            setHasFixedSize(true)
            layoutManager = contactsListManger
            adapter = UserListAdapter(contacts)
        }





    }

    fun confiureEvents(recyclerView: RecyclerView, events: ArrayList<Group>) {
        val layoutManger = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = layoutManger
            adapter = EventsAdapter(events)
        }
    }
}

class CalendarDialog : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater

            builder.setView(inflater.inflate(R.layout.group_calendar, null))
            builder.create()
        } ?: throw IllegalStateException("Activity Cannot be Null")
    }


}


// This should be an event but  group event items have the layout we need
// No interaction on this part
class EventsAdapter (private val events:ArrayList<Group>):
    RecyclerView.Adapter<EventsAdapter.ContactHolder>() {

    class ContactHolder(v: View) : RecyclerView.ViewHolder(v) {
        val photoView = v.findViewById<CircleImageView>(R.id.photo)
        val nameView = v.findViewById<TextView>(R.id.name)
        val summaryView = v.findViewById<TextView>(R.id.summary)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ContactHolder {
        val layout =
            LayoutInflater.from(parent.context).inflate(R.layout.group_item, parent, false)

        return ContactHolder(layout)
    }

    override fun onBindViewHolder(holder: ContactHolder, position: Int) {
        val item = events[position]
        holder.photoView.setImageResource(item.icon)
        holder.nameView.text = item.name
        holder.summaryView .text = item.summary
    }

    override fun getItemCount() = events.size
}

class ContactsAdapter (private val users:ArrayList<User>):
    RecyclerView.Adapter<ContactsAdapter.ContactHolder>() {

    class ContactHolder(v: View) : RecyclerView.ViewHolder(v) {
        val photoView = v.findViewById<CircleImageView>(R.id.photo)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ContactHolder {
        val layout =
            LayoutInflater.from(parent.context).inflate(R.layout.group_contact, parent, false)

        return ContactHolder(layout)
    }

    override fun onBindViewHolder(holder: ContactHolder, position: Int) {
        val user = users[position]
        holder.photoView.setImageResource(user.photo)
    }

    override fun getItemCount() = users.size
}
