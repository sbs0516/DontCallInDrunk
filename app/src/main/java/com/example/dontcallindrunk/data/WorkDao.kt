package com.example.dontcallindrunk.data

import androidx.databinding.ObservableArrayList
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
    fun getWorkList(): List<Work>

    @Query("SELECT * FROM works WHERE id = :workId")
    fun getWork(workId: Int): Work

}