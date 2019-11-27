package com.example.timehunter

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.w3c.dom.Text

class ConfrimGroup : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.confirm_group)

        val receiveName = findViewById<TextView>(R.id.confirm_group_name)
        val receiveDesc = findViewById<TextView>(R.id.confirm_group_desc)
        val editButton = findViewById<TextView>(R.id.edit_text)
        val cancelButton = findViewById<TextView>(R.id.cancel_action)
        val createButton = findViewById<TextView>(R.id.create_text)

        val name = intent.getStringExtra("GroupName")
        val desc = intent.getStringExtra("GroupDesc")

        receiveName.text = name
        receiveDesc.text = desc

        editButton.setOnClickListener{
            finish()
        }

        cancelButton.setOnClickListener{
            finish()
        }

        createButton.setOnClickListener{
            if(name!=null && desc != null){
                val newGroup = Group(name,desc)
                GroupsData.groups.add(newGroup)
            }
            Toast.makeText(this, "Group Saved", Toast.LENGTH_SHORT).show()
            // MOVE INTO FRAGMENT and navigate using UI to the group that's created
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

}