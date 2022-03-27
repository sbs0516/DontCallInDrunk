package com.example.dontcallindrunk.addlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.room.Room
import com.example.dontcallindrunk.R
import com.example.dontcallindrunk.`interface`.OnClickAddListListener
import com.example.dontcallindrunk.`interface`.OnClickSaveListener
import com.example.dontcallindrunk.data.WorkDatabase
import com.example.dontcallindrunk.databinding.FragmentAddListBinding
import com.example.dontcallindrunk.list.ListFragment

class AddListFragment: Fragment() {

    lateinit var addListFragmentBinding: FragmentAddListBinding

    private val addListViewModel by lazy { ViewModelProviders.of(this).get(AddListViewModel::class.java).apply {
        this.dao = Room.databaseBuilder(this@AddListFragment.requireContext(), WorkDatabase::class.java, "workDB").build().workDao()
    } }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        addListFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_list, container, false)
        return addListFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addListFragmentBinding.viewModel = addListViewModel

        addListViewModel.setOnClickSaveListener(object : OnClickSaveListener {
            override fun onClickSave() {
                finishFragment()
            }
        })

    }

    private fun finishFragment() {
        val fragmentManager = (activity as AppCompatActivity).supportFragmentManager.beginTransaction()
        fragmentManager.replace(R.id.mainFrameLayout, ListFragment()).commit()
    }
}