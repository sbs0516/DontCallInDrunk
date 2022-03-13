package com.example.dontcallindrunk.addlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.dontcallindrunk.R
import com.example.dontcallindrunk.databinding.FragmentAddListBinding

class AddListFragment: Fragment() {

    lateinit var addListFragmentBinding: FragmentAddListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        addListFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_list, container, false)
        return addListFragmentBinding.root
    }
}