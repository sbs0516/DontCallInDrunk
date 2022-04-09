package com.example.dontcallindrunk.data

import android.telephony.emergency.EmergencyNumber
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.*

@Entity(tableName = "works")
data class Work(
    @ColumnInfo(name = "title") var title: String?,
    @ColumnInfo(name = "block_number_one") var blockNumberOne: String?,
    @ColumnInfo(name = "block_number_two") var blockNumberTwo: String?,
    @ColumnInfo(name = "set_work_hour") var setWorkHour: Int?,
    @ColumnInfo(name = "set_work_minute") var setWorkMinute: Int?,
    @ColumnInfo(name = "set_end_time") var setEndTime: Int?,
    @ColumnInfo(name = "emergency_number") var emergencyNumber: String?,
    @ColumnInfo(name = "isLostFunActivated") var isLostFunActivated: Boolean?,
    @PrimaryKey(autoGenerate = true) var id: Int = 0
) {

    val titleForWork: String
        get() = if(!title.isNullOrEmpty()) title!! else ""

    val workTimeForWork: String
        get() = if(setWorkHour != null || setWorkMinute != null) {
            var meridiem = "오전"
            var hour = setWorkHour
            if (setWorkHour!! >= 12) {
                meridiem = "오후"
                if(setWorkHour == 12) {
                    hour = 12
                }
                hour = setWorkHour!! - 12
            }
            "$meridiem ${hour}시 ${setWorkMinute}분 부터 12시간"
        } else {
            Date(System.currentTimeMillis()).toString()
        }

    val isActivated: Boolean
        get() = isLostFunActivated == true
}
