package com.udacity.project5.watchdog.data.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*

@Entity(tableName = "watch_dogs")
data class WatchDogsDataItem(
    @ColumnInfo(name = "timer_period") var timerPeriodInMinutes: Int,
    @ColumnInfo(name = "times_rang") var timesTimerRang: Int,
    @ColumnInfo(name = "total_time_in_sec") var totalTimeInSeconds: Long,
    @ColumnInfo(name = "doc") var dateOfCreation: String = LocalDateTime.now()
        .format(DateTimeFormatter.ISO_DATE_TIME),
    @PrimaryKey @ColumnInfo(name = "dog_id") val id: String = UUID.randomUUID().toString()
)
