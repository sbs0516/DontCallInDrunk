package com.example.dontcallindrunk.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.*
import com.example.dontcallindrunk.MainActivity
import com.example.dontcallindrunk.`interface`.OnDelayCompleteListener
import com.example.dontcallindrunk.R
import com.example.dontcallindrunk.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    private val splashViewDatabinding by lazy {
        DataBindingUtil.setContentView<ActivitySplashBinding>(this, R.layout.activity_splash).apply {
            lifecycleOwner = this@SplashActivity
        }
    }
    private val splashViewModel by lazy { ViewModelProviders.of(this@SplashActivity).get(SplashViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        splashViewDatabinding.delayBtn

        splashViewModel.setOnDelayListener(object: OnDelayCompleteListener {
            override fun onDelayComplete() {
                goMainActivity()
            }
        })
    }

    override fun onResume() {
        super.onResume()
        splashViewModel.onActivityResume()
    }

    fun goMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

}
