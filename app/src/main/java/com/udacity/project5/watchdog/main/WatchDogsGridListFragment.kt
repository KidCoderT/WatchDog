package com.udacity.project5.watchdog.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
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

        binding.fabCreateNewWatchDog.setOnClickListener {
            findNavController().navigate(
                WatchDogsGridListFragmentDirections.actionWatchDogsGridListFragmentToCreateWatchDogFragment()
            )
        }

        return binding.root
    }
}