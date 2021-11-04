package com.udacity.project5.watchdog.main

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.udacity.project5.watchdog.data.dto.WatchDogsDTO
import com.udacity.project5.watchdog.data.local.WatchDogsDatabase.Companion.getDatabase
import kotlinx.coroutines.launch

class WatchDogsGridListViewModel(application: Application) : ViewModel() {
    lateinit var allDogs: LiveData<List<WatchDogsDTO>>

    init {
        val watchDogsDao = getDatabase(application).watchDogsDao()
        viewModelScope.launch {
            allDogs = watchDogsDao.getAllDogs()
        }
    }

    //    val dataList = ArrayList<WatchDogDataItem>()
    //    val result = watchDogsDao.getAllDogs()
    //    for (data in result.value!!) {
    //        dataList.add(
    //            WatchDogDataItem(
    //                data.id,
    //                data.timerPeriodInMinutes,
    //                data.timesTimerRang,
    //                data.totalTimeInSeconds,
    //                data.dateOfCreation
    //            )
    //        )
    //    }
    //    allDogs.value = dataList
}