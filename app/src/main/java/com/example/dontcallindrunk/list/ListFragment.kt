package com.example.dontcallindrunk.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.dontcallindrunk.R
import com.example.dontcallindrunk.databinding.FragmentListBinding

class ListFragment: Fragment() {

//    private val listFragmentBinding by lazy { DataBindingUtil.inflate<FragmentListBinding>(layoutInflater, R.layout.fragment_list,false) }
    lateinit var listFragmentBinding: FragmentListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        listFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_list, container, false)
        return listFragmentBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    override fun onResume() {
        super.onResume()

    }
}