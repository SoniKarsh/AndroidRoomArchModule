package com.example.karshsoni.demosqlmodule

import android.R
import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.text.Editable
import android.widget.DatePicker
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.content_main.*
import org.w3c.dom.Text
import java.text.DateFormat
import java.util.*

class DatePickerFragment: DialogFragment(), DatePickerDialog.OnDateSetListener {
    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        Toast.makeText(
                activity,
                "Date Set : ${formatDate(year,month,dayOfMonth)}"
                , Toast.LENGTH_SHORT
        ).show()
        enterBirthDate.text = formatDate(year,month,dayOfMonth).toString()
    }

    private lateinit var calendar: Calendar

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        return DatePickerDialog(
                activity, // Context
                // Put 0 to system default theme or remove this parameter
                R.style.Theme_Holo_Light_Dialog_NoActionBar_MinWidth, // Theme
                this, // DatePickerDialog.OnDateSetListener
                year, // Year
                month, // Month of year
                day // Day of month
        )
    }

    private fun formatDate(year:Int, month:Int, day:Int): String? {
        // Create a Date variable/object with user chosen date
        calendar.set(year, month, day, 0, 0, 0)
        val chosenDate = calendar.time

        // Format the date picker selected date
        val df = DateFormat.getDateInstance(DateFormat.MEDIUM)
        return df.format(chosenDate)
    }
}