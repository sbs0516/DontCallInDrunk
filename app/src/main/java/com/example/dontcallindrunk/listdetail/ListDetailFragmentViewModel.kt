package com.example.dontcallindrunk.listdetail

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dontcallindrunk.`interface`.OnClickSaveListener
import com.example.dontcallindrunk.data.Work
import com.example.dontcallindrunk.data.WorkDao
import java.util.*
import kotlin.concurrent.thread

class ListDetailFragmentViewModel: ViewModel() {

    lateinit var dao: WorkDao

    var title = ObservableField<String>()

    val blockNumberOne = ObservableField<String>()

    val blockNumberTwo = ObservableField<String>()

    val setWorkTime = ObservableField(Date(System.currentTimeMillis()))

    val setWorkHour = ObservableInt()

    val setWorkMinute = ObservableInt()

    val setEndTime = ObservableInt()

    val emergencyNumber = ObservableField<String>()

    val isLostFunActivated = ObservableBoolean()

    val work = ObservableField<Work>()

    var selectWork = ObservableField<Work>()

    val spinnerItem = listOf("0시간","1시간","2시간","3시간","4시간","5시간","6시간","7시간","8시간","9시간","10시간","11시간","12시간")

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

    fun initializeSelectedWork(id: Int) {
        thread {
            Log.d("initialize", "id: $id")
            Log.d("initialize", "getWork: ${dao.getWork(id)}")
            selectWork.set(dao.getWork(id))

            mainHandler.post {
                Log.d(TAG, "initializeProperty: ${selectWork.get()?.title}")

                title.set(selectWork.get()?.title)
                blockNumberOne.set(selectWork.get()?.blockNumberOne)
                blockNumberTwo.set(selectWork.get()?.blockNumberTwo)
//        setWorkTime.value = work?.get()?.setWorkTime
                selectWork.get()?.setWorkHour?.let { setWorkHour.set(it) }
                selectWork.get()?.setWorkMinute?.let { setWorkMinute.set(it) }
                selectWork.get()?.setEndTime?.let { setEndTime.set(it) }
                emergencyNumber.set(selectWork.get()?.emergencyNumber)
                selectWork.get()?.isLostFunActivated?.let { isLostFunActivated.set(it) }
            }
        }

    }

    fun updateWork() {

        val currentTitle = title.get()
        val currentBlockOne = blockNumberOne.get()
        val currentBlockTwo = blockNumberTwo.get()
        val setWorkTime = setWorkTime.get()
        val setWorkHour = setWorkHour.get()
        val setWorkMinute = setWorkMinute.get()
        val setEndTime = setEndTime.get()
        val setEmergencyNum = emergencyNumber.get()
        val isActivated = isLostFunActivated.get()

        selectWork.get()?.title = currentTitle
        selectWork.get()?.blockNumberOne = currentBlockOne
        selectWork.get()?.blockNumberTwo = currentBlockTwo
//        selectWork.get()?.setWorkTime = setWorkTime
        selectWork.get()?.setWorkHour = setWorkHour
        selectWork.get()?.setWorkMinute = setWorkMinute
        selectWork.get()?.setEndTime = setEndTime
        selectWork.get()?.emergencyNumber = setEmergencyNum
        selectWork.get()?.isLostFunActivated = isActivated

        thread {
            Log.d(ContentValues.TAG, "saveWork: 1")
            try {
                selectWork.get()?.let { dao.updateWorks(it) }
            } catch (e: Exception) {
                Log.e(ContentValues.TAG, "saveWork: $e", e)
            }
            Log.d(ContentValues.TAG, "saveWork: 2")
            onClickSave()
        }

    }
}