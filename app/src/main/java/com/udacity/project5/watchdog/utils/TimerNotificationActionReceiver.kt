package com.udacity.project5.watchdog.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.resocoder.timertutorial.AppConstants
import com.udacity.project5.watchdog.runwatchdogscreen.RunWatchDogFragment

class TimerNotificationActionReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        when (intent.action){
            AppConstants.ACTION_STOP -> {
                RunWatchDogFragment.removeAlarm(context)
                PrefUtil.setTimerState(RunWatchDogFragment.TimerState.Stopped, context)
                NotificationUtil.hideTimerNotification(context)
            }
            AppConstants.ACTION_PAUSE -> {
                var secondsRemaining = PrefUtil.getSecondsRemaining(context)
                val alarmSetTime = PrefUtil.getAlarmSetTime(context)
                val nowSeconds = RunWatchDogFragment.nowSeconds

                secondsRemaining -= nowSeconds - alarmSetTime
                PrefUtil.setSecondsRemaining(secondsRemaining, context)

                RunWatchDogFragment.removeAlarm(context)
                PrefUtil.setTimerState(RunWatchDogFragment.TimerState.Paused, context)
                NotificationUtil.showTimerPaused(context)
            }
            AppConstants.ACTION_RESUME -> {
                val secondsRemaining = PrefUtil.getSecondsRemaining(context)
                val wakeUpTime = RunWatchDogFragment.setAlarm(context, RunWatchDogFragment.nowSeconds, secondsRemaining)
                PrefUtil.setTimerState(RunWatchDogFragment.TimerState.Running, context)
                NotificationUtil.showTimerRunning(context, wakeUpTime)
            }
            AppConstants.ACTION_START -> {
                val minutesRemaining = PrefUtil.getTimerLength(context)
                val secondsRemaining = minutesRemaining * 60L
                val wakeUpTime = RunWatchDogFragment.setAlarm(context, RunWatchDogFragment.nowSeconds, secondsRemaining)
                PrefUtil.setTimerState(RunWatchDogFragment.TimerState.Running, context)
                PrefUtil.setSecondsRemaining(secondsRemaining, context)
                NotificationUtil.showTimerRunning(context, wakeUpTime)
            }
        }
    }
}
