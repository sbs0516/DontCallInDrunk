package com.example.dontcallindrunk.list

import androidx.lifecycle.ViewModel
import com.example.dontcallindrunk.`interface`.OnClickAddListListener

class ListFragmentViewModel: ViewModel() {

    var clickAddListListener: OnClickAddListListener? = null

    fun setOnClickAddListListener(listener: OnClickAddListListener) {
        this.clickAddListListener = listener
    }

    fun onClickAddList() {
        clickAddListListener?.onClickAddList()
    }

}