package com.example.dontcallindrunk.setting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.dontcallindrunk.R
import com.example.dontcallindrunk.databinding.FragmentSettingBinding

class SettingFragment: Fragment() {

    lateinit var settingFragmentBinding: FragmentSettingBinding

    private val settingFragmentViewModel = ViewModelProviders.of(this).get(SettingFragmentViewModel::class.java)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        settingFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_setting, container, false)
        return settingFragmentBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        settingFragmentBinding.settingFragmentViewModel = settingFragmentViewModel
    }
}