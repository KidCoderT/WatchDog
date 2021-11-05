package com.udacity.project5.watchdog.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.udacity.project5.watchdog.data.dto.WatchDogsDataItem
import com.udacity.project5.watchdog.data.local.WatchDogsDatabase.Companion.getDatabase

class WatchDogsGridListViewModel(application: Application) : AndroidViewModel(application) {
    private val watchDogsDao = getDatabase(application).watchDogsDao()
    val allDogs: LiveData<List<WatchDogsDataItem>> = watchDogsDao.getAllDogs()
}