package com.example.dontcallindrunk.addlist

import android.app.TimePickerDialog
import android.content.ContentValues.TAG
import android.os.Handler
import android.os.HandlerThread
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import android.widget.TimePicker
import androidx.core.view.get
import androidx.databinding.*
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.example.dontcallindrunk.Event
import com.example.dontcallindrunk.MainViewModel
import com.example.dontcallindrunk.`interface`.OnClickAddListListener
import com.example.dontcallindrunk.`interface`.OnClickSaveListener
import com.example.dontcallindrunk.data.Work
import com.example.dontcallindrunk.data.WorkDao
import com.example.dontcallindrunk.list.ListRecyclerViewAdapter
import java.lang.Exception
import java.sql.Time
import java.util.*
import kotlin.concurrent.thread

class AddListViewModel: ViewModel() {

    lateinit var dao: WorkDao

    val title = MutableLiveData<String>()

    val blockNumberOne = MutableLiveData<String>()

    val blockNumberTwo = MutableLiveData<String>()

    val setWorkTime = ObservableField<Time>()

    val setWorkHour = ObservableInt()

    val setWorkMinute = ObservableInt()

    val setEndTime = MutableLiveData<Int>()

    val emergencyNumber = MutableLiveData<String>()

    val isLostFunActivated = MutableLiveData<Boolean>()

    val work = ObservableField<Work>()

    private val mainHandler = Handler(Looper.getMainLooper())

    private var saveListener: OnClickSaveListener? = null

    fun setOnClickSaveListener(listener: OnClickSaveListener) {
        this.saveListener = listener
    }

    fun onClickSave() {

        mainHandler.post {
            saveListener?.onClickSave()
        }

    }

    fun saveWork() {

        val currentTitle = title.value
        val currentBlockOne = blockNumberOne.value
        val currentBlockTwo = blockNumberTwo.value
        val setWorkTime = setWorkTime.get()
        val setWorkHour = setWorkHour.get()
        val setWorkMinute = setWorkMinute.get()
        val setEndTime = setEndTime.value
        val setEmergencyNum = emergencyNumber.value
        val isActivated = isLostFunActivated.value

        Log.d("hour","$setWorkHour")
        Log.d("minute","$setWorkMinute")

        val workObject = Work(currentTitle, currentBlockOne, currentBlockTwo, setWorkHour, setWorkMinute, setEndTime, setEmergencyNum, isActivated)
        work.set(workObject)

        thread {
            Log.d(TAG, "saveWork: 1")
            try {
                dao.insertWorks(workObject)
            } catch (e: Exception) {
                Log.e(TAG, "saveWork: $e", e)
            }
            Log.d(TAG, "saveWork: 2")
            onClickSave()
        }

    }

}