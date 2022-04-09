package com.example.dontcallindrunk.addlist

import android.content.ContentValues.TAG
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.databinding.*
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dontcallindrunk.R
import com.example.dontcallindrunk.`interface`.OnClickSaveListener
import com.example.dontcallindrunk.data.Work
import com.example.dontcallindrunk.data.WorkDao
import java.sql.Time
import kotlin.concurrent.thread

@BindingAdapter("entries")
fun Spinner.setEntries(entries: List<String>?) {
    entries?.run {
        val arrayAdapter = ArrayAdapter(AddListFragment.buildFragment().requireContext(), R.layout.spinner_item, entries)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        adapter = arrayAdapter
    }
}

@InverseBindingAdapter(attribute = "selectedValue", event = "selectedValueAttrChanged")
fun Spinner.getSelectedValue(): Any? {
    return selectedItem
}

@BindingAdapter("selectedValueAttrChanged")
fun Spinner.setInverseBindingListener(inverseBindingListener: InverseBindingListener?) {

    inverseBindingListener?.run {
        onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                if (tag != position) {
                    inverseBindingListener.onChange()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }
}

@BindingAdapter("selectedValue")
fun Spinner.setSelectedValue(selectedValue: Any?) {
    adapter?.run {
        val position =
            (adapter as ArrayAdapter<Any>).getPosition(selectedValue)
        setSelection(position, false)
        tag = position
    }
}

class AddListViewModel: ViewModel() {

    lateinit var dao: WorkDao

    val title = MutableLiveData<String>()

    val blockNumberOne = MutableLiveData<String>()

    val blockNumberTwo = MutableLiveData<String>()

    val setWorkTime = ObservableField<Time>()

    val setWorkHour = ObservableInt()

    val setWorkMinute = ObservableInt()

    val setEndTime = ObservableInt()

    val emergencyNumber = MutableLiveData<String>()

    val isLostFunActivated = MutableLiveData<Boolean>()

    val work = ObservableField<Work>()

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

    fun saveWork() {

        val currentTitle = title.value
        val currentBlockOne = blockNumberOne.value
        val currentBlockTwo = blockNumberTwo.value
        val setWorkTime = setWorkTime.get()
        val setWorkHour = setWorkHour.get()
        val setWorkMinute = setWorkMinute.get()
        val setEndTime = setEndTime.get()
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