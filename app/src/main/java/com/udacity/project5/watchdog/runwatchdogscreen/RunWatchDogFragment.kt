package com.udacity.project5.watchdog.runwatchdogscreen

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.udacity.project5.watchdog.databinding.RunWatchDogFragmentBinding

class RunWatchDogFragment : Fragment() {

    // TODO 1: Make most variables into viewModel liveData's
    // TODO 2: Add counting for number of times reminder finished
    // TODO 3: Hide and show fab buttons based on different timer states
    // TODO 4: Add notification or indication for when 1 reminder done

    private lateinit var binding: RunWatchDogFragmentBinding
    private val viewModel: RunWatchDogViewModel by viewModels()

    private lateinit var timer: CountDownTimer
    private var timerLengthSeconds: Long = 0
    private var secondsRemaining: Long = 0

    enum class TimerState {
        Stopped, Paused, Running
    }

    private var timerState = TimerState.Stopped

    val args: RunWatchDogFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = RunWatchDogFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = this

        timerLengthSeconds = (args.minutes * 60).toLong()
        secondsRemaining = timerLengthSeconds

        binding.progressCountdown.max = timerLengthSeconds.toInt()

        timer = object : CountDownTimer(secondsRemaining * 1000, 1000) {
            override fun onFinish() {
                resetTimer(true)
            }

            override fun onTick(millisUntilFinished: Long) {
                secondsRemaining = millisUntilFinished / 1000
                updateCountdownUI()
                binding.fabStop.setOnClickListener { _ ->
                    timerState = TimerState.Stopped
                    resetTimer()
                }
            }
        }

        resetTimer()

        updateCountdownUI()

        binding.fabStart.setOnClickListener {
            timer.start()
            timerState = TimerState.Running
        }

        binding.fabPause.setOnClickListener {
            timer.cancel()
            timerState = TimerState.Paused
            updateCountdownUI()
        }

        return binding.root
    }

    private fun resetTimer(continueTimer: Boolean = false) {
        timer.cancel()
        secondsRemaining = timerLengthSeconds
        updateCountdownUI()
        if (continueTimer) {
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