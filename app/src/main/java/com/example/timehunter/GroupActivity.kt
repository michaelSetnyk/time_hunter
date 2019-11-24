package com.example.timehunter

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import android.widget.ImageButton
import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.provider.ContactsContract
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import kotlinx.android.synthetic.main.group_activity.*


class GroupActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.group_activity)

        var groupName = ""
        var groupDesc = ""
        val confirm = findViewById<TextView>(R.id.confirm_text)
        confirm.setOnClickListener{
            val intent = Intent(this@GroupActivity, ConfrimGroup::class.java)
            intent.putExtra("GroupName", groupName)
            intent.putExtra("GroupDesc", groupDesc)
            startActivity(intent)
        }

        val groupPhotoButton = findViewById<ImageButton>(R.id.groupPhotoAdd)
        groupPhotoButton.setOnClickListener {

            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){
                val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
                requestPermissions(permissions, PHOTO_PERMISSION_CODE)
            } else {
                pickImageFromGallery()
            }
        }

        val contactAddButton = findViewById<ImageButton>(R.id.add_contact)
        contactAddButton.setOnClickListener{

            if (checkSelfPermission(Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_DENIED){
                val permissions = arrayOf(Manifest.permission.READ_CONTACTS)
                requestPermissions(permissions, CONTACT_PERMISSION_CODE)
            } else {
                contactPicker()
            }
        }

        val cancelAction = findViewById<TextView>(R.id.cancel_action)
        cancelAction.setOnClickListener{
            val dialogBuilder = AlertDialog.Builder(this)

            dialogBuilder.setMessage("Do you want cancel creating this group?")

                .setCancelable(false)

                .setPositiveButton("Delete", DialogInterface.OnClickListener(){
                        dialog, id -> finish()
                })

                .setNeutralButton("Continue Creating Group", DialogInterface.OnClickListener(){
                        dialog, id ->  dialog.cancel()
                })

            val alert = dialogBuilder.create()
            alert.setTitle("Cancel")
            alert.show()
        }

        val groupNameText = findViewById<EditText>(R.id.group_name)
        groupNameText.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable){
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                groupName = s.toString()
            }
        })

        val groupDescText = findViewById<EditText>(R.id.group_description1)
        groupDescText.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable){
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                groupDesc = s.toString()
            }
        })
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
        when(requestCode){
            PHOTO_PERMISSION_CODE -> {
                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    pickImageFromGallery()
                }
                else{
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
                }
            }

            CONTACT_PERMISSION_CODE -> {
                if(grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    contactPicker()
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE){
            groupPhotoAdd.setImageURI(data?.data)
        }

        if (requestCode == CONTACT_PERMISSION_CODE && resultCode == RESULT_OK) {

        }
    }
}