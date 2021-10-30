package com.udacity.project5.watchdog.runwatchdogscreen

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.udacity.project5.watchdog.R
import com.udacity.project5.watchdog.databinding.CreateWatchDogFragmentBinding
import com.udacity.project5.watchdog.databinding.RunWatchDogFragmentBinding

class RunWatchDogFragment : Fragment() {

    private lateinit var binding: RunWatchDogFragmentBinding
    private val viewModel: RunWatchDogViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = RunWatchDogFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = this

        return binding.root
    }

}