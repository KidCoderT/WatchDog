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
import androidx.recyclerview.widget.GridLayoutManager
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

        binding.url = "https://images.unsplash.com/photo-1635863762520-d952874f4d1e?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1470&q=80"

        binding.createNewWatchDogBtn.setOnClickListener {
            findNavController().navigate(
                WatchDogsGridListFragmentDirections.actionWatchDogsGridListFragmentToCreateWatchDogFragment()
            )
        }

        viewModel.response.observe(viewLifecycleOwner, {
            Log.i("Response", it)
        })

        val manager = GridLayoutManager(activity, 4)
        binding.watchDogsGridListRecyclerView.layoutManager = manager

        val adapter = WatchDogsGridListAdapter()
        binding.watchDogsGridListRecyclerView.adapter = adapter

        viewModel.allDogs.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        return binding.root
    }
}