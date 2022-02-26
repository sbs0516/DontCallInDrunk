package com.example.dontcallindrunk

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {

    private var _fragmentStatus = MutableLiveData<String>()
    val fragmentStatus: LiveData<String>
        get() = _fragmentStatus

    init {
        _fragmentStatus.value = "list_item"
    }

    fun setFragmentStatus(id: String) {
        _fragmentStatus.value = id
    }

}