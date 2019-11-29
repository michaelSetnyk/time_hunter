package com.example.timehunter

import android.content.Intent
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.confirm_group.*
import org.w3c.dom.Text
import java.util.ArrayList

class ConfrimGroup : Fragment(){

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val layout = inflater.inflate(R.layout.confirm_group, container, false)
        val editButton = layout.findViewById<TextView>(R.id.edit_button)
        val cancelButton = layout.findViewById<TextView>(R.id.confirm_page_cancel_button)
        val createButton = layout.findViewById<TextView>(R.id.confirm_page_confirm_button)
        val contactList = layout.findViewById<TextView>(R.id.contactList)
        val groupName = layout.findViewById<TextView>(R.id.confirm_group_name)
        val groupDesc = layout.findViewById<TextView>(R.id.confirm_group_desc)
        val navController = findNavController()

        groupName.setText(GroupsData.groups[GroupsData.groups.size - 1].name)
        groupDesc.setText(GroupsData.groups[GroupsData.groups.size - 1].summary)

        for(contacts in GroupContacts.contacts){
            contactList.append(contacts.name + "\n")
        }

        editButton.setOnClickListener{
            navController.popBackStack()
        }

        cancelButton.setOnClickListener{
            navController.popBackStack()
        }

        createButton.setOnClickListener{
            //Toast.makeText(activity.applicationContext, "Group Saved", Toast.LENGTH_SHORT).show()
            // MOVE INTO FRAGMENT and navigate using UI to the group that's created
            val newContactList = ArrayList<User>()
            ContactsData.contacts = newContactList
            val a = ViewGroupFragment.newInstance(GroupsData.groups[GroupsData.groups.size - 1]).arguments
            navController.navigate(R.id.viewGroupFragment,a)
        }

        return layout
    }

}