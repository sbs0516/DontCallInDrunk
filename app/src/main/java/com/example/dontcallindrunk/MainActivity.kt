package com.example.dontcallindrunk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.dontcallindrunk.databinding.ActivityMainBinding
import com.example.dontcallindrunk.list.ListFragment
import com.example.dontcallindrunk.record.RecordFragment
import com.example.dontcallindrunk.setting.SettingFragment

class MainActivity : AppCompatActivity() {

    private val mainActivityBinding by lazy { DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main) }

    private val viewModel by lazy { ViewModelProviders.of(this@MainActivity).get(MainViewModel::class.java)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainActivityBinding.viewModel = viewModel

    }

//    fun replaceFragment(fragment: Fragment) {
//        val fragmentTransAction = supportFragmentManager.beginTransaction()
//        fragmentTransAction.replace(R.id.mainFrameLayout, fragment)
//        fragmentTransAction.commit()
//    }
}
