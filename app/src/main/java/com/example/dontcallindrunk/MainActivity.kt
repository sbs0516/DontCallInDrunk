package com.example.dontcallindrunk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.dontcallindrunk.databinding.ActivityMainBinding
import com.example.dontcallindrunk.list.ListFragment
import com.example.dontcallindrunk.record.RecordFragment
import com.example.dontcallindrunk.setting.SettingFragment

class MainActivity : AppCompatActivity() {

    private val mainActivityBinding by lazy { DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main) }

    private val mainViewModel by lazy { ViewModelProviders.of(this@MainActivity).get(MainViewModel::class.java)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivityBinding.mainViewModel = mainViewModel

        setBottomNavigationItemClickListener()

        mainViewModel.fragmentStatus.observe(this, Observer {
            when(it) {
                "list_item" -> {
                    replaceFragment(ListFragment())
                }
                "record_item" -> {
                    replaceFragment(RecordFragment())
                }
                else -> {
                    replaceFragment(SettingFragment())
                }
            }
        })
    }

    private fun setBottomNavigationItemClickListener() {
        mainActivityBinding.bottomNavigation.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.list_item -> {
                    mainViewModel.setFragmentStatus("list_item")
                    return@setOnItemSelectedListener true
                }
                R.id.record_item -> {
                    mainViewModel.setFragmentStatus("record_item")
                    return@setOnItemSelectedListener true
                }
                else -> {
                    mainViewModel.setFragmentStatus("setting_item")
                    return@setOnItemSelectedListener true
                }
            }
        }
    }

    open fun replaceFragment(fragment: Fragment) {
        val fragmentTransAction = supportFragmentManager.beginTransaction()
        fragmentTransAction.replace(R.id.mainFrameLayout, fragment)
        fragmentTransAction.commit()
    }
}
