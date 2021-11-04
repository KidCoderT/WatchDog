package com.udacity.project5.watchdog.main

import java.io.Serializable
import java.time.LocalDateTime
import java.util.*

data class WatchDogDataItem(
    val id: String = UUID.randomUUID().toString(),
    var timerPeriodInMinutes: Int,
    var timerTimesRang: Int,
    var totalTimeRanInSeconds: Long,
    var dateOfCreation: LocalDateTime = LocalDateTime.now()
) : Serializable
