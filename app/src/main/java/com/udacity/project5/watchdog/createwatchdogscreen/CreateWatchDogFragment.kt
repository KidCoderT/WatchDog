package com.udacity.project5.watchdog.createwatchdogscreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.udacity.project5.watchdog.databinding.CreateWatchDogFragmentBinding

class CreateWatchDogFragment : Fragment() {

    private lateinit var binding: CreateWatchDogFragmentBinding
    private var countdownTimerTimeInMinutes = 5

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CreateWatchDogFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = this

        // Setup Number Picker
        binding.minutesNumberPicker.minValue = 1
        binding.minutesNumberPicker.maxValue = 60
        binding.minutesNumberPicker.value = countdownTimerTimeInMinutes
        binding.minutesNumberPicker.setOnValueChangedListener { _, _, newVal ->
            countdownTimerTimeInMinutes = newVal
        }

        // On create click listener
        binding.createWatchDogButton.setOnClickListener {
            // Check if name is set
            if (binding.watchDogNameField.text.isEmpty() or binding.watchDogNameField.text.isBlank()) {
                // if its not, send toast to user
                Toast.makeText(context?.applicationContext,"Please set a name for the watch dog",Toast.LENGTH_SHORT).show()
            } else {
                //else
                // Send minutes selected & watchdog name
                // navigate to runWatchDogsReminderFragment
                findNavController().navigate(
                    CreateWatchDogFragmentDirections.actionCreateWatchDogFragmentToRunWatchDogFragment(
                        countdownTimerTimeInMinutes,
                        binding.watchDogNameField.text.toString())
                )
            }
        }

        return binding.root
    }
}