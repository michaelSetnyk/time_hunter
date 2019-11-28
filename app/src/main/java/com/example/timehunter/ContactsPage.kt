package com.example.timehunter

import android.app.AlertDialog
import android.content.DialogInterface
import android.graphics.drawable.Drawable
import android.media.Image
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_create_group.*

class ContactsPage : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val layout = inflater.inflate(R.layout.fragment_contact_page, container, false)
        val contactsView = layout.findViewById<RecyclerView>(R.id.contact_recycler)
        val layoutManger = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val confirm = layout.findViewById<Button>(R.id.done_button)
        confirm.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.createGroupFragment2))

        contactsView.apply {
            setHasFixedSize(true)
            layoutManager = layoutManger
            adapter = UserListAdapter(ContactsData.contacts)
        }



        return layout
    }
}