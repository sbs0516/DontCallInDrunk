package com.example.dontcallindrunk.utility

import androidx.room.TypeConverter
import java.util.*

open class Converters {

    @TypeConverter
    fun getLongFromDate(long: Long?): Date? {
        return long?.let { Date(it) }
    }

    @TypeConverter
    fun getDateFromLong(date: Date?): Long? {
        if(date == null) {
            return null
        }
        return date.time
    }
}