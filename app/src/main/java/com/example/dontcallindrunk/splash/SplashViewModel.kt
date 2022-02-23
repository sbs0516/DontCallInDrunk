package com.example.dontcallindrunk.splash

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.ViewModel

class SplashViewModel: ViewModel() {

    private var splashListener: OnDelayListener? = null

    fun setListener(listener: OnDelayListener) {
        this.splashListener = listener
    }

    fun onActivityResume() {
//        val myHandler = Handler()
//        myHandler.postDelayed({
//            splashListener?.onDelay()
//        }, 3000)

        val myHandler = Handler(Looper.getMainLooper())
        myHandler.postDelayed({
            splashListener?.onDelay()
        }, 3000)
    }

}
