package com.udacity.project5.watchdog.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.udacity.project5.watchdog.runwatchdogscreen.RunWatchDogFragment

class TimerExpiredReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        NotificationUtil.showTimerExpired(context)

        PrefUtil.setTimerState(RunWatchDogFragment.TimerState.Stopped, context)
        PrefUtil.setAlarmSetTime(0, context)
    }
}
