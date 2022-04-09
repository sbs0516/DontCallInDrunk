package com.example.dontcallindrunk.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.dontcallindrunk.data.Work
import com.example.dontcallindrunk.databinding.RecyclerListItemBinding

class ListRecyclerViewAdapter(private val viewModel: ListFragmentViewModel): ListAdapter<Work, ListRecyclerViewAdapter.MyViewHolder>(WorkDiffCallback()){

    class MyViewHolder private constructor(val binding: RecyclerListItemBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(viewModel: ListFragmentViewModel, item: Work) {
            binding.viewModel = viewModel
            binding.work = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): MyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RecyclerListItemBinding.inflate(layoutInflater, parent, false)
                return MyViewHolder(binding)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(viewModel, item)
    }

}
class WorkDiffCallback : DiffUtil.ItemCallback<Work>() {
    override fun areItemsTheSame(oldItem: Work, newItem: Work): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: Work, newItem: Work): Boolean {
        return oldItem == newItem
    }
}