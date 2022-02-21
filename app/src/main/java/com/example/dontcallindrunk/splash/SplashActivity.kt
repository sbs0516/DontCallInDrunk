package com.example.dontcallindrunk.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.*
import com.example.dontcallindrunk.MainActivity
import com.example.dontcallindrunk.R
import com.example.dontcallindrunk.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    lateinit var splashViewDataBinding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        splashViewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_splash)

        val splashViewModel = ViewModelProviders.of(this).get(SplashViewModel::class.java)
        splashViewDataBinding.lifecycleOwner = this

        splashViewModel.delay().observe(this, Observer {
            if(it) {
                goMainActivity()
                splashViewModel.check.value = false
            }
        })

    }
    private fun goMainActivity() {
        val myIntent = Intent(this, MainActivity::class.java)
        startActivity(myIntent)
        finish()
    }
}