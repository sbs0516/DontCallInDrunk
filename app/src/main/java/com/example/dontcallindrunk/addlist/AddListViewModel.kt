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
import com.example.dontcallindrunk.`interface`.OnClickAddListListener
import com.example.dontcallindrunk.`interface`.OnClickSaveListener
import com.example.dontcallindrunk.data.Work
import com.example.dontcallindrunk.data.WorkDao
import com.example.dontcallindrunk.list.ListRecyclerViewAdapter
import java.lang.Exception
import java.util.*

@BindingAdapter("app:items")
fun setItems(listView: RecyclerView, items: List<Work>?) {
    Log.d(TAG, "setItems: setItem ${items?.size}")
    items?.let {
        (listView.adapter as ListRecyclerViewAdapter).submitList(items)
    }
}

class AddListViewModel: ViewModel() {

    lateinit var dao: WorkDao

    val title = MutableLiveData<String>()

    val blockNumberOne = MutableLiveData<String>()

    val blockNumberTwo = MutableLiveData<String>()

    val setWorkTime = MutableLiveData(Date(System.currentTimeMillis()))

    val setEndTime = MutableLiveData<Int>()

    val emergencyNumber = MutableLiveData<String>()

    val isLostFunActivated = MutableLiveData<Boolean>()

    val work = ObservableField<Work>()

    private val handlerThread = HandlerThread("HandlerThread")

    init {
        handlerThread.start()
    }

    private val handler = Handler(handlerThread.looper)

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

        handler.post {
            insertWork(workObject)
        }
        handlerThread.quit()

        onClickSave()

    }

    private fun insertWork(work: Work) {
        try{
            dao.insertWorks(work)
        } catch (e: Exception) {
            Log.e(TAG, "insertWork: 24124 $e  ", e)
        }

    }

}