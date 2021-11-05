package com.udacity.project5.watchdog.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.udacity.project5.watchdog.databinding.WatchDogsGridListFragmentBinding

class WatchDogsGridListFragment : Fragment() {
    private lateinit var binding: WatchDogsGridListFragmentBinding
    private val viewModel: WatchDogsGridListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = WatchDogsGridListFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = this

        binding.createNewWatchDogBtn.setOnClickListener {
            findNavController().navigate(
                WatchDogsGridListFragmentDirections.actionWatchDogsGridListFragmentToCreateWatchDogFragment()
            )
        }

        viewModel.allDogs.observe(viewLifecycleOwner, {
            Log.i("DataHasChanged", "Here is the newList")
            for (dataItem in it) {
                Log.i("DataItem", dataItem.toString())
            }
        })

        return binding.root
    }
}