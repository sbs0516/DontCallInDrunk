package com.example.dontcallindrunk.list

import android.util.Log
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.room.Room
import com.example.dontcallindrunk.MainViewModel
import com.example.dontcallindrunk.R
import com.example.dontcallindrunk.`interface`.OnClickAddListListener
import com.example.dontcallindrunk.addlist.AddListFragment
import com.example.dontcallindrunk.data.Work
import com.example.dontcallindrunk.data.WorkDao
import com.example.dontcallindrunk.record.RecordFragment
import com.example.dontcallindrunk.setting.SettingFragment

//@BindingAdapter(value = ["fragmentManager"])
//fun setOnFragmentListener(view: ImageView, fragmentManager: FragmentManager) {
//
//    val fragmentTransAction = fragmentManager.beginTransaction()
//    fragmentTransAction.replace(R.id.mainFrameLayout, AddListFragment()).commit()
//
//}

class ListFragmentViewModel: ViewModel() {

    lateinit var dao: WorkDao

    lateinit var items: LiveData<List<Work>>

    var clickAddListListener: OnClickAddListListener? = null

    fun getWorksItem() {
        items = dao.getWorks()
    }

    fun setOnClickAddListListener(listener: OnClickAddListListener) {
        this.clickAddListListener = listener
    }

    fun onClickAddListButton() {
        clickAddListListener?.onClickAddList()
    }


}