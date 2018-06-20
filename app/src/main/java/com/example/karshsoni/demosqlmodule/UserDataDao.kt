package com.example.karshsoni.demosqlmodule

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update

@Dao
interface UserDataDao {

    @Query("SELECT * from MyTable")
    fun getAll(): List<MyTable>

    @Insert(onConflict = REPLACE)
    fun insert(myTable: MyTable)

    @Query("DELETE from MyTable")
    fun deleteAll()

    @Update
    fun updateData(myTable: MyTable)
}