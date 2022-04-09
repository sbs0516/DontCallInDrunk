package com.example.dontcallindrunk

import android.util.Log
import android.widget.FrameLayout
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableInt
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dontcallindrunk.list.ListFragment
import com.example.dontcallindrunk.list.ListFragmentViewModel
import com.example.dontcallindrunk.record.RecordFragment
import com.example.dontcallindrunk.setting.SettingFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

interface OnMenuClickListener {
    fun onMenuClicked(itemId: Int)
}

@BindingAdapter("itemSelectedListener")
fun setOnItemSelectedListener(view: BottomNavigationView, listener: OnMenuClickListener) {
    view.setOnItemSelectedListener { item ->
        listener.onMenuClicked(item.itemId)
        true
    }
}

@BindingAdapter(value = ["fragmentManager", "viewState"])
fun setOnFragmentListener(view: FrameLayout, fragmentManager: FragmentManager, state: Int) {
//    (view.context as? AppCompatActivity)?.supportFragmentManager

    val fragmentTransAction = fragmentManager.beginTransaction()
    fragmentTransAction.replace(view.id, when(state) {
        MainViewModel.VIEWSTATE_LIST -> ListFragment.buildFragment()
        MainViewModel.VIEWSTATE_RECORD -> RecordFragment()
        MainViewModel.VIEWSTATE_SETTING -> SettingFragment()
        else -> ListFragment.buildFragment()
    })
    fragmentTransAction.commit()

}

open class MainViewModel: ViewModel() {

    companion object {
        const val VIEWSTATE_DEFAULT = 0
        const val VIEWSTATE_LIST = 1
        const val VIEWSTATE_RECORD = 2
        const val VIEWSTATE_SETTING = 3

    }

    val selectWorkId = MutableLiveData<Int>()

    var viewState = ObservableInt(VIEWSTATE_DEFAULT)

    fun onResume() {
        selectWorkId.value = ListFragmentViewModel().selectedWorkId.get()
    }

    fun onNavigationItemSelected(itemId: Int) {
        when(itemId) {
            R.id.list_item -> viewState.set(VIEWSTATE_LIST)
            R.id.record_item -> viewState.set(VIEWSTATE_RECORD)
            R.id.setting_item -> viewState.set(VIEWSTATE_SETTING)
        }
    }

    fun onStateChanged(viewState: Int) {
        when(viewState) {
            VIEWSTATE_DEFAULT -> {
            }
            VIEWSTATE_LIST -> {
            }
            VIEWSTATE_RECORD -> {
            }
            VIEWSTATE_SETTING -> {
            }
        }
    }

}