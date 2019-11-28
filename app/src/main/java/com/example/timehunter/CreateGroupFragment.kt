package com.example.timehunter

import android.content.pm.PackageManager
import android.os.Bundle
import android.Manifest
import android.app.Activity.RESULT_OK
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.provider.ContactsContract
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_create_group.*
import android.widget.TextView
import androidx.core.content.PermissionChecker.*
import androidx.core.view.isVisible

class CreateGroupFragment : Fragment() {

    private lateinit var groupName:String
    private lateinit var groupDesc:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        groupName=""
        groupDesc=""
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val layout = inflater.inflate(R.layout.fragment_create_group, container, false)
        val cancelAction = layout.findViewById<TextView>(R.id.cancel_button)
        val confirm = layout.findViewById<TextView>(R.id.confirm_button)
        val groupPhotoButton = layout.findViewById<ImageButton>(R.id.groupPhotoAdd)
        val groupNameText = layout.findViewById<EditText>(R.id.group_name)
        val groupDescText = layout.findViewById<EditText>(R.id.group_description1)
        val contactAddButton = layout.findViewById<Button>(R.id.add_contact_button)

        val context = requireContext()

        (activity as MainActivity).supportActionBar?.title = "Create Group"


        confirm.setOnClickListener{
            val intent = Intent(context, ConfrimGroup::class.java)
            intent.putExtra("GroupName", groupName)
            intent.putExtra("GroupDesc", groupDesc)
            startActivity(intent)
        }

        cancelAction.setOnClickListener{
            val dialogBuilder = AlertDialog.Builder(context)
            dialogBuilder.setMessage("Do you want cancel creating this group?")
                .setCancelable(false)
                .setPositiveButton("Delete", DialogInterface.OnClickListener(){_,_ ->
                    //We can add people in a sec
                    val navController = findNavController()
                    navController.popBackStack()
                })
                .setNeutralButton("Continue Creating Group", DialogInterface.OnClickListener(){
                        dialog, id ->  dialog.cancel()
                })
            val alert = dialogBuilder.create()
            alert.setTitle("Cancel")
            alert.show()
        }

        groupPhotoButton.setOnClickListener {
            if (checkSelfPermission(context,Manifest.permission.READ_EXTERNAL_STORAGE) ==  PERMISSION_DENIED){
                val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
                requestPermissions(permissions, PHOTO_PERMISSION_CODE)
            } else {
                pickImageFromGallery()
            }
        }

        contactAddButton.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.contactsPage))

        /*contactAddButton.setOnClickListener{

            /*if (checkSelfPermission(context, Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_DENIED){
                val permissions = arrayOf(Manifest.permission.READ_CONTACTS)
                requestPermissions(permissions, CONTACT_PERMISSION_CODE)
            } else {
                contactPicker()
            }*/

        }*/

        groupNameText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable){
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                groupName = s.toString()
            }
        })

        groupDescText.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable){
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                groupDesc = s.toString()
            }
        })

        return layout
    }

    companion object {
        //image pick code
        val IMAGE_PICK_CODE = 0
        //Photo Permission code
        val PHOTO_PERMISSION_CODE = 1
        //Contact Permission code
        val CONTACT_PERMISSION_CODE = 2
    }

    private fun pickImageFromGallery() {
        //Intent to pick image
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_PICK_CODE)
    }

    private fun contactPicker(){
        val intent = Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI)
        startActivityForResult(intent, CONTACT_PERMISSION_CODE)

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        val context = requireContext()
        when(requestCode){
            PHOTO_PERMISSION_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    pickImageFromGallery()
                }
                else{
                    Toast.makeText(context, "Permission denied", Toast.LENGTH_SHORT).show()
                }
            }

            CONTACT_PERMISSION_CODE -> {
                if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    contactPicker()
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == IMAGE_PICK_CODE){
            groupPhotoAdd.setImageURI(data?.data)
        }
    }
}