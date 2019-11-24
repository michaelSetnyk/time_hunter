package com.example.timehunter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.DialogInterface
import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import com.wdullaer.materialdatetimepicker.time.RadialPickerLayout
import android.icu.util.Calendar


class Create_Event : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_event)

        var eventName = ""
        var eventLocation = ""
        val confirm = findViewById<TextView>(R.id.confirm_text)
        confirm.setOnClickListener{
            val intent = Intent(this@Create_Event, pick_date::class.java)
            intent.putExtra("GroupName", eventName)
            intent.putExtra("GroupDesc", eventLocation)
            startActivity(intent)
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

        val eventNameText = findViewById<EditText>(R.id.event_name_line)
        eventNameText.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable){
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                eventName = s.toString()
            }
        })

        val eventLocationText = findViewById<EditText>(R.id.location_line)
        eventLocationText.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable){
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                eventLocation = s.toString()
            }
        })


        val eventDate = findViewById<EditText>(R.id.date_line)
        eventDate.setOnClickListener{
            val now = Calendar.getInstance()
            val dpd = DatePickerDialog.OnDateSetListener {
                this@Create_Event
                now.get(Calendar.YEAR); // Initial year selection
                now.get(Calendar.MONTH); // Initial month selection
                now.get(Calendar.DAY_OF_MONTH) // Inital day selection }()
                }
        }

        val eventTime = findViewById<EditText>(R.id.time_line)
        eventTime.setOnClickListener{
        }


        var dateTextView = findViewById<EditText>(R.id.date_line)
        var timeTextView = findViewById<EditText>(R.id.time_line)

        fun onTimeSet(view: RadialPickerLayout, hourOfDay: Int, minute: Int, second: Int) {
            var time = "You picked the following time: " + hourOfDay + "h" + minute + "m" + second;
            timeTextView.setText(time);
        }

        fun onDateSet(view: DatePickerDialog, year: Int, monthOfYear: Int, dayOfMonth: Int) {
            var date = "You picked the following date: "+dayOfMonth+ "/" + (monthOfYear + 1)+ "/ "+ year;
            dateTextView.setText(date);
        }
    }
}