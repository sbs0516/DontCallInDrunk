package com.example.dontcallindrunk

import android.util.Log
import android.view.View
import android.widget.FrameLayout
import androidx.databinding.BindingAdapter
import androidx.lifecycle.ViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

interface OnMenuClickListener {
    fun onMenuClicked(itemId: Int)
}

interface OnSetFrameLayoutListener {
    fun onFrameLayoutChanged()
}

@BindingAdapter("itemSelectedListener")
fun setOnItemSelectedListener(view: BottomNavigationView, listener: OnMenuClickListener) {
    view.setOnItemSelectedListener { item ->
        listener.onMenuClicked(item.itemId)
        true
    }
}

@BindingAdapter("android:frameLayoutVisibility")
fun setFrameLayoutVisibility(view: FrameLayout, visible: Boolean) {
    view.visibility = if(visible) {
        View.VISIBLE
    } else {
        View.GONE
    }
}

class MainViewModel: ViewModel() {

    companion object {
        const val VIEWSTATE_DEFAULT = 0
        const val VIEWSTATE_LIST = 1
        const val VIEWSTATE_RECORD = 2
        const val VIEWSTATE_SETTING = 3

    }

    var viewState : Int = VIEWSTATE_DEFAULT
        set(value) {
            if(field != value) {
                field = value

            }
        }

    fun onStateChanged(viewState: Int): Boolean {
        when(viewState) {
            VIEWSTATE_DEFAULT -> {
                Log.d("디폴트","Default")
                return onVisibleListFrameLayout()
            }
            VIEWSTATE_LIST -> {
                Log.d("리스트","List")
                return onVisibleListFrameLayout()
            }
            VIEWSTATE_RECORD -> {
                Log.d("레코드","Record")
                return onVisibleRecordFrameLayout()
            }
            VIEWSTATE_SETTING -> {
                Log.d("세팅","Setting")
                return onVisibleSettingFrameLayout()
            }
        }
        return false
    }

    fun onNavigationItemSelected(itemId: Int) {
        when(itemId) {
            R.id.list_item -> viewState = VIEWSTATE_LIST
            R.id.record_item -> viewState = VIEWSTATE_RECORD
            R.id.setting_item -> viewState = VIEWSTATE_SETTING
        }
        onStateChanged(viewState)
    }

    fun onVisibleListFrameLayout(): Boolean {
        Log.d("리스트프레임", "ListFrame")
        return viewState == VIEWSTATE_LIST || viewState == VIEWSTATE_DEFAULT
    }
    fun onVisibleRecordFrameLayout(): Boolean {
        Log.d("레코드프레임", "RecordFrame")
        return viewState == VIEWSTATE_RECORD
    }
    fun onVisibleSettingFrameLayout(): Boolean {
        Log.d("세팅프레임", "SettingFrame")
        return viewState == VIEWSTATE_SETTING
    }

}