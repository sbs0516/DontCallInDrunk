package com.example.dontcallindrunk.data

import androidx.lifecycle.LiveData
import androidx.room.*
import java.util.*

@Dao
interface WorkDao {
    @Insert
    fun insertWorks(works: Work)

    @Delete
    fun deleteWorks(works: Work)

    @Update
    fun updateWorks(works: Work)

    @Query("SELECT * FROM works")
    fun getWorks(): LiveData<List<Work>>

//    @Query("SELECT block_number_one FROM works")
//    fun getBlockNumberOne(): String
}