package com.udacity.project5.watchdog.runwatchdogscreen

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.udacity.project5.watchdog.R
import com.udacity.project5.watchdog.databinding.CreateWatchDogFragmentBinding
import com.udacity.project5.watchdog.databinding.RunWatchDogFragmentBinding
import java.util.*

class RunWatchDogFragment : Fragment() {

    private lateinit var binding: RunWatchDogFragmentBinding
    private val viewModel: RunWatchDogViewModel by viewModels()
    val args: RunWatchDogFragmentArgs by navArgs()

    private lateinit var timer: CountDownTimer
    private var timerLengthSeconds: Long = 0
    private var timerState = TimerState.Stopped

    private var secondsRemaining: Long = 0

    enum class TimerState{
        Stopped, Paused, Running
    }

//    companion object {
//        fun setAlarm(context: Context, nowSeconds: Long, secondsRemaining: Long): Long{
//            val wakeUpTime = (nowSeconds + secondsRemaining) * 1000
//            val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
//            val intent = Intent(context, TimerExpiredReceiver::class.java)
//            val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0)
//            alarmManager.setExact(AlarmManager.RTC_WAKEUP, wakeUpTime, pendingIntent)
//            PrefUtil.setAlarmSetTime(nowSeconds, context)
//            return wakeUpTime
//        }
//
//        fun removeAlarm(context: Context){
//            val intent = Intent(context, TimerExpiredReceiver::class.java)
//            val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0)
//            val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
//            alarmManager.cancel(pendingIntent)
//            PrefUtil.setAlarmSetTime(0, context)
//        }
//
//        val nowSeconds: Long
//            get() = Calendar.getInstance().timeInMillis / 1000
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = RunWatchDogFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = this

        timerLengthSeconds = (args.minutes * 60).toLong()

//        binding.fabStart.setOnClickListener{ _ ->
//            startTimer()
//            timerState =  TimerState.Running
//            updateButtons()
//        }
//
//        binding.fabPause.setOnClickListener { _ ->
//            timer.cancel()
//            timerState = TimerState.Paused
//            updateButtons()
//        }
//
//        binding.fabStop.setOnClickListener { _ ->
//            timer.cancel()
//            onTimerFinished()
//        }

        return binding.root
    }

//    override fun onResume() {
//        super.onResume()
//
//        initTimer()
//
//        removeAlarm(requireContext())
//        NotificationUtil.hideTimerNotification(this)
//    }
//
//    override fun onPause() {
//        super.onPause()
//
//        if (timerState == TimerState.Running){
//            timer.cancel()
//            val wakeUpTime = setAlarm(this, nowSeconds, secondsRemaining)
//            NotificationUtil.showTimerRunning(this, wakeUpTime)
//        }
//        else if (timerState == TimerState.Paused){
//            NotificationUtil.showTimerPaused(this)
//        }
//
//        PrefUtil.setPreviousTimerLengthSeconds(timerLengthSeconds, this)
//        PrefUtil.setSecondsRemaining(secondsRemaining, this)
//        PrefUtil.setTimerState(timerState, this)
//    }
//
//    private fun initTimer(){
//        timerState = PrefUtil.getTimerState(this)
//
//        //we don't want to change the length of the timer which is already running
//        //if the length was changed in settings while it was backgrounded
//        if (timerState == TimerState.Stopped)
//            setNewTimerLength()
//        else
//            setPreviousTimerLength()
//
//        secondsRemaining = if (timerState == TimerState.Running || timerState == TimerState.Paused)
//            PrefUtil.getSecondsRemaining(this)
//        else
//            timerLengthSeconds
//
//        val alarmSetTime = PrefUtil.getAlarmSetTime(this)
//        if (alarmSetTime > 0)
//            secondsRemaining -= nowSeconds - alarmSetTime
//
//        if (secondsRemaining <= 0)
//            onTimerFinished()
//        else if (timerState == TimerState.Running)
//            startTimer()
//
//        updateButtons()
//        updateCountdownUI()
//    }
//
//    private fun onTimerFinished(){
//        timerState = TimerState.Stopped
//
//        //set the length of the timer to be the one set in SettingsActivity
//        //if the length was changed when the timer was running
//        setNewTimerLength()
//
//        binding.progressCountdown.progress = 0
//
//        PrefUtil.setSecondsRemaining(timerLengthSeconds, this)
//        secondsRemaining = timerLengthSeconds
//
//        updateButtons()
//        updateCountdownUI()
//    }
//
//    private fun startTimer(){
//        timerState = TimerState.Running
//
//        timer = object : CountDownTimer(secondsRemaining * 1000, 1000) {
//            override fun onFinish() = onTimerFinished()
//
//            override fun onTick(millisUntilFinished: Long) {
//                secondsRemaining = millisUntilFinished / 1000
//                updateCountdownUI()
//            }
//        }.start()
//    }
//
//    private fun setNewTimerLength(){
//        val lengthInMinutes = PrefUtil.getTimerLength(this)
//        timerLengthSeconds = (lengthInMinutes * 60L)
//        binding.progressCountdown.max = timerLengthSeconds.toInt()
//    }
//
//    private fun setPreviousTimerLength(){
//        timerLengthSeconds = PrefUtil.getPreviousTimerLengthSeconds(this)
//        binding.progressCountdown.max = timerLengthSeconds.toInt()
//    }
//
//    private fun updateCountdownUI(){
//        val minutesUntilFinished = secondsRemaining / 60
//        val secondsInMinuteUntilFinished = secondsRemaining - minutesUntilFinished * 60
//        val secondsStr = secondsInMinuteUntilFinished.toString()
//        binding.timerCountdownText.text = "$minutesUntilFinished:${if (secondsStr.length == 2) secondsStr else "0" + secondsStr}"
//        binding.progressCountdown.progress = (timerLengthSeconds - secondsRemaining).toInt()
//    }
//
//    private fun updateButtons(){
//        when (timerState) {
//            TimerState.Running ->{
//                binding.fabStart.isEnabled = false
//                binding.fabPause.isEnabled = true
//                binding.fabStop.isEnabled = true
//            }
//            TimerState.Stopped -> {
//                binding.fabStart.isEnabled = true
//                binding.fabPause.isEnabled = false
//                binding.fabStop.isEnabled = false
//            }
//            TimerState.Paused -> {
//                binding.fabStart.isEnabled = true
//                binding.fabPause.isEnabled = false
//                binding.fabStop.isEnabled = true
//            }
//        }
//    }
}