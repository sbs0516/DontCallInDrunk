package com.example.dontcallindrunk.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.dontcallindrunk.utility.Converters

@Database(entities = [Work::class], version = 4)
//@TypeConverters(Converters::class)
abstract class WorkDatabase: RoomDatabase() {
    abstract fun workDao(): WorkDao
}
