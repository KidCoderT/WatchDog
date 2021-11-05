package com.udacity.project5.watchdog.main

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.udacity.project5.watchdog.data.dto.WatchDogsDataItem
import com.udacity.project5.watchdog.databinding.WatchDogsGridListAdapterItemBinding

class WatchDogsGridListAdapter() : androidx.recyclerview.widget.ListAdapter<WatchDogsDataItem,
        WatchDogsGridListAdapter.ViewHolder>(WatchDogsDiffCallback()) {
    var data = listOf<WatchDogsDataItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        holder.bind(getItem(position)!!)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: WatchDogsGridListAdapterItemBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: WatchDogsDataItem) {
            val res = itemView.context.resources

            binding.dog = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = WatchDogsGridListAdapterItemBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }
    }
}

class WatchDogsDiffCallback : DiffUtil.ItemCallback<WatchDogsDataItem>() {
    override fun areItemsTheSame(oldItem: WatchDogsDataItem, newItem: WatchDogsDataItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: WatchDogsDataItem, newItem: WatchDogsDataItem): Boolean {
        return oldItem == newItem
    }
}