package com.udacity.project5.watchdog.createwatchdogscreen

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.udacity.project5.watchdog.R
import com.udacity.project5.watchdog.databinding.CreateWatchDogFragmentBinding

class CreateWatchDogFragment : Fragment() {

    private lateinit var binding: CreateWatchDogFragmentBinding
    private val viewModel: CreateWatchDogViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CreateWatchDogFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = this

        return binding.root
    }

}