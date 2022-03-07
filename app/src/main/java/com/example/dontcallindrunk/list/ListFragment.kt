package com.example.dontcallindrunk.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProviders
import com.example.dontcallindrunk.MainActivity
import com.example.dontcallindrunk.R
import com.example.dontcallindrunk.`interface`.OnClickAddListListener
import com.example.dontcallindrunk.addlist.AddListFragment
import com.example.dontcallindrunk.databinding.FragmentListBinding

class ListFragment: Fragment() {

//    private val listFragmentBinding by lazy { DataBindingUtil.inflate<FragmentListBinding>(layoutInflater, R.layout.fragment_list,false) }
    lateinit var listFragmentBinding: FragmentListBinding

    private val listFragmentViewModel by lazy { ViewModelProviders.of(this).get(ListFragmentViewModel::class.java) }

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

        listFragmentBinding.listFragmentViewModel = listFragmentViewModel

        listFragmentViewModel.setOnClickAddListListener(object : OnClickAddListListener {
            override fun onClickAddList() {
                goAddListFragment()
            }
        })
    }

    fun goAddListFragment() {
        val fragmentManager = (activity as AppCompatActivity).supportFragmentManager.beginTransaction()
        fragmentManager.replace(R.id.mainFrameLayout, AddListFragment()).addToBackStack("AddListFragment").commit()
    }

}