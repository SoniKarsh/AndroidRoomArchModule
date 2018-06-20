package com.example.karshsoni.demosqlmodule

import android.app.DatePickerDialog
import android.arch.persistence.room.Room
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast


import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity(), Communication {
    var dataId: Long? = null
    override fun communicate(id:Long, name: String, bDate: String) {
        enterName.setText(name)
        enterBirthDate.text = bDate
        dataId = id
        button2.text = getString(R.string.update)
        include.visibility = View.VISIBLE
        listView.visibility = View.GONE
    }

    var items:List<MyTable> = ArrayList<MyTable>()
    var myCalendar = Calendar.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fab.setOnClickListener {
            enterName.text.clear()
            enterBirthDate.text = ""
            button2.text = getString(R.string.add)
            include.visibility = View.VISIBLE
            listView.visibility = View.GONE

        }

        var userDataHolderClass = Room.databaseBuilder(this, UserDataHolderClass::class.java, "User.db").allowMainThreadQueries().build()
        var layout= LinearLayoutManager(this)
        val dateSetListener = DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            myCalendar.set(Calendar.YEAR, year)
            myCalendar.set(Calendar.MONTH, monthOfYear)
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateDateInView()
        }
        enterBirthDate!!.setOnClickListener {
            DatePickerDialog(this@MainActivity,
                    dateSetListener,
                    // set DatePickerDialog to point to today's date when it loads up
                    myCalendar.get(Calendar.YEAR),
                    myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH)).show()
        }
        button2.setOnClickListener {
            var text1 = enterName.text.toString()
            var text2 = enterBirthDate.text.toString()
            var myData = MyTable()
            myData.name = text1
            myData.bDate = text2
            if(button2.text.equals("Add")){
                if(text1 == "" || text2 == ""){
                    Toast.makeText(this, "Enter Some Data", Toast.LENGTH_LONG).show()
                }else{
                    userDataHolderClass.UserDataDao().insert(myData)
                }
            }else{
                if(text1 == "" || text2 == ""){
                    Toast.makeText(this, "Enter Some Data", Toast.LENGTH_LONG).show()
                }else{
                    myData.id = dataId
                    userDataHolderClass.UserDataDao().updateData(myData)
                }
            }
            items = userDataHolderClass.UserDataDao().getAll()
            listView.adapter = UserAdapter(items, this)
            listView.layoutManager=layout
            include.visibility = View.GONE
            listView.visibility = View.VISIBLE
        }

    }

    private fun updateDateInView() {
        val myFormat = "MM/dd/yyyy" // mention the format you need
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        enterBirthDate!!.text = sdf.format(myCalendar.time)
    }
}
