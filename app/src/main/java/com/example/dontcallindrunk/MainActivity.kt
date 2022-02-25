package com.example.dontcallindrunk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.dontcallindrunk.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val mainActivityBinding by lazy { DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main) }

    private val mainViewModel by lazy { ViewModelProviders.of(this@MainActivity).get(MainViewModel::class.java)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }
}