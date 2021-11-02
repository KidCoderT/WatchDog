package com.udacity.project5.watchdog.runwatchdogscreen

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.udacity.project5.watchdog.R
import com.udacity.project5.watchdog.databinding.RunWatchDogFragmentBinding

class RunWatchDogFragment : Fragment() {

    // TODO: Add notification or indication for when 1 reminder done

    private lateinit var binding: RunWatchDogFragmentBinding
    private val viewModel: RunWatchDogViewModel by viewModels()

    var secondsRemaining: Long = 0

    private lateinit var timer: CountDownTimer

    val args: RunWatchDogFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = RunWatchDogFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = this

        viewModel.setupTimerLengthAndCountdownSeconds(args.minutes)
        secondsRemaining = viewModel.timerLengthSeconds.value!!

        binding.progressCountdown.max = viewModel.timerLengthSeconds.value!!.toInt()

        binding.timesRangText.text = getString(R.string.times_rang, viewModel.timesRang.value.toString())

        binding.fabStop.setOnClickListener { _ ->
            viewModel.setTimerState(TimerState.Stopped)
            resetTimer()
            val alertDialog: AlertDialog? = activity?.let {
                val builder = AlertDialog.Builder(it)
                builder.setTitle("Do you want to stop this watchDog and exit?")
                builder.apply {
                    setPositiveButton(R.string.ok,
                        DialogInterface.OnClickListener { _, _ ->
                            findNavController().navigate(
                                RunWatchDogFragmentDirections.actionRunWatchDogFragmentToCreateWatchDogFragment()
                            )
                        })
                    setNegativeButton(R.string.cancel,
                        DialogInterface.OnClickListener { _, _ ->
                            // User cancelled the dialog
                        })
                }
                // Create the AlertDialog
                builder.create()
            }
            alertDialog?.show()
        }

        secondsRemaining = viewModel.timerLengthSeconds.value!!
        updateCountdownUI()
        binding.timesRangText.text = getString(R.string.times_rang, viewModel.timesRang.value)

        binding.fabStart.setOnClickListener {
            startTimer()
            viewModel.setTimerState(TimerState.Running)
        }

        binding.fabPause.setOnClickListener {
            timer.cancel()
            viewModel.setTimerState(TimerState.Paused)
            updateCountdownUI()
        }

        viewModel.timerState.observe(viewLifecycleOwner, Observer {
            when (it) {
                TimerState.Running -> {
                    binding.fabStart.visibility = View.GONE
                    binding.fabPause.visibility = View.VISIBLE
                    binding.fabStop.visibility = View.VISIBLE
                }
                TimerState.Paused -> {
                    binding.fabStart.visibility = View.VISIBLE
                    binding.fabPause.visibility = View.GONE
                    binding.fabStop.visibility = View.VISIBLE
                }
                TimerState.Stopped -> {
                    binding.fabStart.visibility = View.VISIBLE
                    binding.fabPause.visibility = View.GONE
                    binding.fabStop.visibility = View.GONE
                }
            }
        })

        return binding.root
    }

    private fun startTimer(){
        viewModel.setTimerState(TimerState.Running)

        timer = object : CountDownTimer(secondsRemaining*1000L, 1000) {
            override fun onFinish() {
                resetTimer(true)
            }

            override fun onTick(millisUntilFinished: Long) {
                secondsRemaining = millisUntilFinished / 1000
                updateCountdownUI()
            }
        }.start()
    }

    private fun resetTimer(continueTimer: Boolean = false) {
        timer.cancel()
        secondsRemaining = viewModel.timerLengthSeconds.value!!
        updateCountdownUI()
        if (continueTimer) {
            viewModel.incrementTimesRang()
            binding.timesRangText.text = getString(R.string.times_rang, viewModel.timesRang.value)
            continueTimer()
        }
    }

    private fun continueTimer() {
        timer.start()
    }

    private fun updateCountdownUI() {
        val minutesUntilFinished = secondsRemaining / 60
        val secondsInMinuteUntilFinished = secondsRemaining - minutesUntilFinished * 60
        val secondsStr = secondsInMinuteUntilFinished.toString()
        binding.timerCountdownText.text =
            "$minutesUntilFinished:${if (secondsStr.length == 2) secondsStr else "0" + secondsStr}"
        binding.progressCountdown.progress = secondsRemaining.toInt()
    }
}