package com.example.karshsoni.demosqlmodule

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.content_main.view.*
import kotlinx.android.synthetic.main.custom_list.view.*

class UserAdapter(val items : List<MyTable>, val context: Context): RecyclerView.Adapter<ViewHolder>(){

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView1.text = items[position].name
        holder.textView2.text = items[position].bDate
        var includeUi:View? = holder.itemView.include
        var communication: Communication = context as Communication
        holder.itemView.setOnClickListener {
            communication.communicate(items[holder.adapterPosition].id!!,items[holder.adapterPosition].name, items[holder.adapterPosition].bDate)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.custom_list, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

}

class ViewHolder (var view: View) : RecyclerView.ViewHolder(view) {
    val textView1 = view.findViewById<TextView>(R.id.textView1)
    val textView2 = view.findViewById<TextView>(R.id.textView2)
}

