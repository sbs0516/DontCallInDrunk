package com.example.dontcallindrunk.splash

import android.os.Handler
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SplashViewModel: ViewModel() {

    var check = MutableLiveData<Boolean>()

    fun delay(): LiveData<Boolean> {

        val handler = Handler()
        handler.postDelayed( {
            check.value = true
        }, 3000)

        return check
    }

}
