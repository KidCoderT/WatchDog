package com.udacity.project5.watchdog.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.ClipData.newIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.TaskStackBuilder
import androidx.core.content.res.ResourcesCompat.getColor
import com.udacity.project5.watchdog.BuildConfig
import com.udacity.project5.watchdog.MainActivity
import com.udacity.project5.watchdog.R

private const val NOTIFICATION_CHANNEL_ID = BuildConfig.APPLICATION_ID + ".channel"

fun sendNotification(context: Context, timesRung: Int) {
    val notificationManager = context
        .getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    // We need to create a NotificationChannel associated with our CHANNEL_ID before sending a notification.
    val name = context.getString(R.string.app_name)
    val channel = NotificationChannel(
        NOTIFICATION_CHANNEL_ID,
        name,
        NotificationManager.IMPORTANCE_DEFAULT
    )
    notificationManager.createNotificationChannel(channel)

    // build the notification object with the data to be shown
    val notification = NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
        .setSmallIcon(R.drawable.ic_timer)
        .setContentTitle("TIMER RANG!")
        .setContentText("The timer rang again, its now the $timesRung'th time running maybe its time to go back to work?!")
        .setAutoCancel(true)
        .build()

    notificationManager.notify(getUniqueId(), notification)
}

private fun getUniqueId() = ((System.currentTimeMillis() % 10000).toInt())