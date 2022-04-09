package com.example.dontcallindrunk.list

import android.util.Log
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.example.dontcallindrunk.MainViewModel
import com.example.dontcallindrunk.`interface`.OnClickAddListListener
import com.example.dontcallindrunk.`interface`.OnClickListItemListener
import com.example.dontcallindrunk.data.Work
import com.example.dontcallindrunk.data.WorkDao
import kotlin.concurrent.thread

@BindingAdapter("app:items")
fun setItems(listView: RecyclerView, items: List<Work>?) {
    items?.let {
        (listView.adapter as ListRecyclerViewAdapter).submitList(items)
    }
}

class ListFragmentViewModel: MainViewModel() {

    lateinit var dao: WorkDao

    var items = ObservableField<List<Work>>()

    val tempWorkId = MutableLiveData<Int>()

    var clickAddListListener: OnClickAddListListener? = null

    var clickListItemListener: OnClickListItemListener? = null

    fun updateWorkList() {
        thread {
            items.set(dao.getWorkList())
        }
    }

    fun setOnClickListItemListener(listener: OnClickListItemListener) {
        this.clickListItemListener = listener
    }

    fun onClickListItem(workId: Int) {
        Log.d("listDetail", "onClickListItem : ${workId}")
        selectWorkId.value = workId
        tempWorkId.value = workId
        Log.d("listDetail", "onClickListItem2 : ${selectWorkId.value}")
        Log.d("listDetail", "onClickListItem3 : ${tempWorkId.value}")
        clickListItemListener?.onClickListItem()
    }

    fun setOnClickAddListListener(listener: OnClickAddListListener) {
        this.clickAddListListener = listener
    }

    fun onClickAddListButton() {
        clickAddListListener?.onClickAddList()
    }

}