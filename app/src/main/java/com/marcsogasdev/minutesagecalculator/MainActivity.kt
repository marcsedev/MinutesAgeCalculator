package com.marcsogasdev.minutesagecalculator

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private var tvSelectedDate:TextView?=null
    private var tvSelectedAgeInMinutes:TextView?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val btnDataPicker:Button= findViewById(R.id.btnDatePicker)
        tvSelectedDate= findViewById(R.id.tvSelectedDate)
        tvSelectedAgeInMinutes= findViewById(R.id.tvSelectedAgeInMinutes)

        btnDataPicker.setOnClickListener{ view ->
            view
             //data Picker
            clickDatePicker()

        }


    }

    @SuppressLint("NewApi")
    private fun clickDatePicker() {

        //create claendar
        val myCalendar=Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(
            this,
            { view, selectedYear, selectedMonth, selectedDayOfMonth ->
                Toast.makeText(
                    this,
                    "year: $selectedYear, month:${selectedMonth + 1}, day: $selectedDayOfMonth",
                    Toast.LENGTH_LONG
                ).show()

                val selectedDate = "$selectedDayOfMonth/${selectedMonth + 1}/$selectedYear"

                tvSelectedDate?.text = selectedDate

                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

                val theDate = sdf.parse(selectedDate)
                theDate?.let {//ejecuta el código si no es está vacia

                    //time te da la respuesta en milisegundos, por eso lo dividimos entre 60000 para obtener minuts
                    val selectedDateInMinutes = theDate.time / 60000

                    val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                    currentDate?.let {//ejecuta el código si no es está vacia

                        val currentDateInMinutes = currentDate.time / 60000

                        val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes

                        tvSelectedAgeInMinutes?.text = differenceInMinutes.toString()
                    }
                }


            },
            year,
            month,
            day

        )


        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000 // la operación hace referencia a ayer (son los milisegundos que hay en un dia)
        dpd.show()

    }
}