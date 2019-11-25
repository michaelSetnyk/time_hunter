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

        val receive_name = findViewById<TextView>(R.id.confirm_group_name)
        receive_name.setText(intent.getStringExtra("GroupName"))

        val receive_desc = findViewById<TextView>(R.id.confirm_group_desc)
        receive_desc.setText(intent.getStringExtra("GroupDesc"))

        val editButton = findViewById<TextView>(R.id.edit_text)
        editButton.setOnClickListener{
            finish()
        }

        val cancelButton = findViewById<TextView>(R.id.cancel_action)
        cancelButton.setOnClickListener{
            finish()
        }

        val createButton = findViewById<TextView>(R.id.create_text)
        createButton.setOnClickListener{
            Toast.makeText(this, "Group Saved", Toast.LENGTH_SHORT).show()
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

}