package com.example.karshsoni.demosqlmodule

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(entities = arrayOf(MyTable::class), version = 1)
abstract class UserDataHolderClass : RoomDatabase() {

    abstract fun UserDataDao(): UserDataDao

}