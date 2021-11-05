package com.udacity.project5.watchdog.runwatchdogscreen

import android.app.Application
import androidx.lifecycle.*
import com.udacity.project5.watchdog.data.dto.WatchDogsDataItem
import com.udacity.project5.watchdog.data.local.WatchDogsDatabase
import kotlinx.coroutines.launch

enum class TimerState {
    Stopped, Paused, Running
}

class RunWatchDogViewModel(application: Application) : AndroidViewModel(application) {
    private val watchDogsDao = WatchDogsDatabase.getDatabase(application).watchDogsDao()

    private val _timerLengthSeconds = MutableLiveData(0L)
    val timerLengthSeconds: LiveData<Long>
        get() = _timerLengthSeconds

    private val _timesRang = MutableLiveData(0)
    val timesRang: LiveData<Int>
        get() = _timesRang

    private val _timerState = MutableLiveData(TimerState.Stopped)
    val timerState: LiveData<TimerState>
        get() = _timerState

    init {
        _timesRang.value = 0
        _timerLengthSeconds.value = 0L
        _timerState.value = TimerState.Stopped
    }

    fun setTimerState(newState: TimerState) {
        _timerState.value = newState
    }

    fun setupTimerLengthAndCountdownSeconds(timeInMinutes: Int, testing: Boolean = false) {
        if (!testing) {
            _timerLengthSeconds.value = timeInMinutes * 60L
        } else {
            _timerLengthSeconds.value = 30L
        }
    }

    fun incrementTimesRang() {
        _timesRang.value = _timesRang.value?.plus(1)
    }

    fun saveWatchDog(newDog: WatchDogsDataItem) {
        viewModelScope.launch {
            watchDogsDao.saveDog(newDog)
        }
    }
}