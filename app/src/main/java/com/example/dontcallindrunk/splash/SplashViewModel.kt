package com.example.dontcallindrunk.splash

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.ViewModel
import com.example.dontcallindrunk.`interface`.OnDelayCompleteListener

class SplashViewModel: ViewModel() {

    private var splashListener: OnDelayCompleteListener? = null

    private val myHandler = Handler(Looper.getMainLooper())

    fun setOnDelayListener(listener: OnDelayCompleteListener) {
        this.splashListener = listener
    }

    fun onActivityResume() {

        myHandler.postDelayed({
            splashListener?.onDelayComplete()
        }, 2300)
    }

}
