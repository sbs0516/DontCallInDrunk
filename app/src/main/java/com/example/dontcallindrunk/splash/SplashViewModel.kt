package com.example.dontcallindrunk.splash

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.ViewModel

class SplashViewModel: ViewModel() {

    private var splashListener: OnDelayListener? = null

    private val myHandler = Handler(Looper.getMainLooper())

    fun setListener(listener: OnDelayListener) {
        this.splashListener = listener
    }

    fun onActivityResume() {

        myHandler.postDelayed({
            splashListener?.onDelay()
        }, 3000)
    }

}
