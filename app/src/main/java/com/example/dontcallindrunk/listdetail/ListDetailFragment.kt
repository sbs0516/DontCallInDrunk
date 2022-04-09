package com.example.dontcallindrunk.listdetail

import android.content.Context
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
import com.example.dontcallindrunk.`interface`.OnClickSaveListener
import com.example.dontcallindrunk.data.WorkDatabase
import com.example.dontcallindrunk.databinding.FragmentListDetailBinding
import com.example.dontcallindrunk.list.ListFragment

class ListDetailFragment private constructor(): Fragment() {

    companion object {
        lateinit var listDetailFragment: ListDetailFragment

        fun buildFragment(): ListDetailFragment {
            listDetailFragment = ListDetailFragment()
            return listDetailFragment
        }
    }

    lateinit var listDetailBinding: FragmentListDetailBinding

    private val listDetailViewModel by lazy { ViewModelProviders.of(this).get(ListDetailFragmentViewModel::class.java).apply {
        this.dao = Room.databaseBuilder(this@ListDetailFragment.requireContext(), WorkDatabase::class.java, "workDB").build().workDao()
    } }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        listDetailBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_list_detail, container,false)
        return listDetailBinding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listDetailViewModel.initializeSelectedWork()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listDetailBinding.viewModel = listDetailViewModel

        listDetailViewModel.setProperty("onViewCreadted")

        listDetailViewModel.setOnClickSaveListener(object : OnClickSaveListener {
            override fun onClickSave() {
                finishFragment()
            }
        })

    }

    override fun onResume() {
        super.onResume()
        listDetailViewModel.setProperty("onResume")
    }

    private fun finishFragment() {
        val fragmentManager = (activity as AppCompatActivity).supportFragmentManager.beginTransaction()
        fragmentManager.replace(R.id.mainFrameLayout, ListFragment.buildFragment()).remove(this).commit()
    }
}