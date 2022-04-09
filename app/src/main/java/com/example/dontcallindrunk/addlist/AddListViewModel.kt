package com.example.dontcallindrunk.addlist

import android.content.ContentValues.TAG
import android.os.Handler
import android.os.HandlerThread
import android.os.Looper
import android.util.Log
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
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
import java.util.*
import kotlin.concurrent.thread

class AddListViewModel: MainViewModel() {

    lateinit var dao: WorkDao

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
        val setWorkTime = setWorkTime.value
        val setEndTime = setEndTime.value
        val setEmergencyNum = emergencyNumber.value
        val isActivated = isLostFunActivated.value

        val workObject = Work(currentTitle, currentBlockOne, currentBlockTwo, setWorkTime?.time, setEndTime, setEmergencyNum, isActivated)
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