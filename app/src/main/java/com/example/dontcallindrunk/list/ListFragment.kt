package com.example.dontcallindrunk.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.ViewModelProviders
import androidx.room.Room
import com.example.dontcallindrunk.R
import com.example.dontcallindrunk.`interface`.OnClickAddListListener
import com.example.dontcallindrunk.`interface`.OnClickListItemListener
import com.example.dontcallindrunk.addlist.AddListFragment
import com.example.dontcallindrunk.data.Work
import com.example.dontcallindrunk.data.WorkDatabase
import com.example.dontcallindrunk.databinding.FragmentListBinding
import com.example.dontcallindrunk.listdetail.ListDetailFragment

class ListFragment private constructor(): Fragment() {

    companion object {

        lateinit var listFragment: ListFragment

        fun buildFragment(): ListFragment {
            listFragment = ListFragment()
            return listFragment
        }
    }

    lateinit var listFragmentBinding: FragmentListBinding

    private val listFragmentViewModel by lazy { ViewModelProviders.of(this).get(ListFragmentViewModel::class.java).apply {
        this.dao = Room.databaseBuilder(this@ListFragment.requireContext(), WorkDatabase::class.java, "workDB").build().workDao()
    } }

    private val mListRecyclerViewAdapter by lazy { ListRecyclerViewAdapter(listFragmentViewModel) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        listFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_list, container, false)
        return listFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listFragmentViewModel.updateWorkList()

        listFragmentBinding.viewModel = listFragmentViewModel

        listFragmentViewModel.setOnClickAddListListener(object : OnClickAddListListener {
            override fun onClickAddList() {
                goAddListFragment()
            }
        })

        listFragmentViewModel.setOnClickListItemListener(object : OnClickListItemListener {
            override fun onClickListItem() {
                goListDetailFragment()
            }
        })
        setUpListRecyclerAdapter()
    }

    override fun onResume() {
        super.onResume()
        listFragmentViewModel.updateWorkList()
    }

    fun goAddListFragment() {
        val fragmentManager = (activity as AppCompatActivity).supportFragmentManager.beginTransaction()
        fragmentManager.replace(R.id.mainFrameLayout, AddListFragment.buildFragment()).addToBackStack("AddListFragment").commit()
    }

    fun goListDetailFragment() {
        val fragmentManager = (activity as AppCompatActivity).supportFragmentManager.beginTransaction()
        fragmentManager.replace(R.id.mainFrameLayout, ListDetailFragment.buildFragment()).addToBackStack("AddListFragment").commit()
    }

    private fun setUpListRecyclerAdapter() {
        if(listFragmentViewModel != null) {
            listFragmentBinding.listRecyclerView.adapter = mListRecyclerViewAdapter
        }
    }

}