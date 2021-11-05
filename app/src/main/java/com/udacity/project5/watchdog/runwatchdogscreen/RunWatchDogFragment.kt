package com.udacity.project5.watchdog.runwatchdogscreen

import android.app.AlertDialog
import android.content.DialogInterface
import android.media.MediaPlayer
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
import com.udacity.project5.watchdog.data.dto.WatchDogsDataItem
import com.udacity.project5.watchdog.databinding.RunWatchDogFragmentBinding
import com.udacity.project5.watchdog.utils.sendNotification

class RunWatchDogFragment : Fragment() {
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

        viewModel.setupTimerLengthAndCountdownSeconds(args.minutes, testing = false)
        secondsRemaining = viewModel.timerLengthSeconds.value!!

        binding.progressCountdown.max = viewModel.timerLengthSeconds.value!!.toInt()

        binding.timesRangText.text = getString(R.string.times_rang, viewModel.timesRang.value)

        secondsRemaining = viewModel.timerLengthSeconds.value!!
        updateCountdownUI()
        binding.timesRangText.text = getString(R.string.times_rang, viewModel.timesRang.value)

        binding.watchDogNameText.text = args.watchDogName

        binding.fabStart.setOnClickListener {
            startTimer()
            viewModel.setTimerState(TimerState.Running)
        }

        binding.fabPause.setOnClickListener {
            timer.cancel()
            viewModel.setTimerState(TimerState.Paused)
            updateCountdownUI()
        }

        binding.fabDone.setOnClickListener { _ ->
            viewModel.setTimerState(TimerState.Stopped)
            timer.cancel()
            viewModel.setTimerState(TimerState.Paused)
            updateCountdownUI()
            val alertDialog: AlertDialog? = activity?.let {
                val builder = AlertDialog.Builder(it, R.style.AlertDialogCustomTheme)
                builder.setTitle("ARE YOU DONE WITH THE BREAK?")
                builder.apply {
                    setPositiveButton(
                        R.string.ok
                    ) { _, _ ->
                        val newWatchDogsDataItem = WatchDogsDataItem(
                            args.watchDogName,
                            args.minutes,
                            viewModel.timesRang.value!!,
                            totalTimeTakenInSec()
                        )

                        viewModel.saveWatchDog(newWatchDogsDataItem)

                        findNavController().navigate(
                            RunWatchDogFragmentDirections.actionRunWatchDogFragmentToWatchDogsGridListFragment()
                        )
                    }
                    setNegativeButton(
                        R.string.cancel
                    ) { _, _ ->
                        // Continue timer
                        startTimer()
                    }
                    setMessage("Are you done with this watchDog and stopped the disruptive activity and gotten up from there? (PLEASE DON'T LIE as it doest make you smarter just more of a time waster!)")
                }
                // Create the AlertDialog
                builder.create()
            }
            alertDialog?.show()
        }

        viewModel.timerState.observe(viewLifecycleOwner, Observer {
            when (it) {
                TimerState.Running -> {
                    binding.fabStart.visibility = View.GONE
                    binding.fabPause.visibility = View.VISIBLE
                    binding.fabDone.visibility = View.VISIBLE
                }
                TimerState.Paused -> {
                    binding.fabStart.visibility = View.VISIBLE
                    binding.fabPause.visibility = View.GONE
                    binding.fabDone.visibility = View.VISIBLE
                }
                TimerState.Stopped -> {
                    binding.fabStart.visibility = View.VISIBLE
                    binding.fabPause.visibility = View.GONE
                    binding.fabDone.visibility = View.GONE
                }
            }
        })

        return binding.root
    }

    private fun totalTimeTakenInSec(): Long {
        return (args.minutes * 60L * viewModel.timesRang.value!!) + ((args.minutes * 60L) - secondsRemaining)
    }

    private fun startTimer() {
        viewModel.setTimerState(TimerState.Running)

        timer = object : CountDownTimer(secondsRemaining * 1000L, 1000) {
            override fun onFinish() {
                // Play sound effect
                val dingSoundEffect: MediaPlayer =
                    MediaPlayer.create(requireContext(), R.raw.ding_sound_effect)
                dingSoundEffect.setVolume(1000F, 1000F)
                dingSoundEffect.start()
                // Show notification
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
            sendNotification(requireContext(), viewModel.timesRang.value!!)
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