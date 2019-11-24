package com.example.timehunter

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

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
    }

}