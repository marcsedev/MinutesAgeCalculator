package com.marcsogasdev.minutesagecalculator

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val btnDataPicker:Button= findViewById(R.id.btnDatePicker)

        btnDataPicker.setOnClickListener{ view ->
            view
             //data Picker
            clickDatePicker()

        }


    }

    @SuppressLint("NewApi")
    fun clickDatePicker() {

        //create claendar
        val myCalendar=Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

        DatePickerDialog(this,
        {view,selectedYear, selectedMonth, selectedDay ->
            Toast.makeText(this, "year: $selectedYear, month:${selectedMonth+1}, day: $selectedDay", Toast.LENGTH_LONG).show()

        },
            year,
            month,
            day

        ).show()

    }
}