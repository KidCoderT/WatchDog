package com.udacity.project5.watchdog.createwatchdogscreen

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.udacity.project5.watchdog.databinding.CreateWatchDogFragmentBinding

class CreateWatchDogFragment : Fragment() {

    private lateinit var binding: CreateWatchDogFragmentBinding
    private val viewModel: CreateWatchDogViewModel by viewModels()
    private var timerIntervalMinutesAmount = 5

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CreateWatchDogFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = this

        binding.createWatchDogViewModel = viewModel

        // Setup Number Picker
        binding.minutesNumberPicker.minValue = 1
        binding.minutesNumberPicker.maxValue = 60
        binding.minutesNumberPicker.value = timerIntervalMinutesAmount
        binding.minutesNumberPicker.setOnValueChangedListener { _, _, newVal ->
            timerIntervalMinutesAmount = newVal
        }

        // On create click listener
        binding.createWatchDogButton.setOnClickListener {
            findNavController().navigate(
                CreateWatchDogFragmentDirections.actionCreateWatchDogFragmentToRunWatchDogFragment(
                    timerIntervalMinutesAmount
                )
            )
        }

        return binding.root
    }

}