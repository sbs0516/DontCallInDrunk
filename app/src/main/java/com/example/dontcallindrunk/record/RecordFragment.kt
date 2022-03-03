package com.example.dontcallindrunk.record

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.dontcallindrunk.R
import com.example.dontcallindrunk.databinding.FragmentRecordBinding

class RecordFragment: Fragment() {

    lateinit var recordFragmentBinding: FragmentRecordBinding

    private val recordFragmentViewModel by lazy { ViewModelProviders.of(this).get(RecordFragmentViewModel::class.java) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        recordFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_record, container, false)
        return recordFragmentBinding.root


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        recordFragmentBinding.recordFragmentViewModel = recordFragmentViewModel
    }
}