package com.example.karshsoni.demosqlmodule

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class MyTable(@PrimaryKey(autoGenerate = true) var id: Long?,
                   @ColumnInfo(name = "Name") var name: String,
                   @ColumnInfo(name = "Birth Date") var bDate: String)
{
    constructor():this(null, "", "")
}