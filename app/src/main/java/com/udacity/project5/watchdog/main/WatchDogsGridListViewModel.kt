package com.udacity.project5.watchdog.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.udacity.project5.watchdog.data.dto.Quote
import com.udacity.project5.watchdog.data.api.QuotesApi
import com.udacity.project5.watchdog.data.dto.WatchDogsDataItem
import com.udacity.project5.watchdog.data.local.WatchDogsDatabase.Companion.getDatabase
import kotlinx.coroutines.launch
import retrofit2.await

class WatchDogsGridListViewModel(application: Application) : AndroidViewModel(application) {
    private val watchDogsDao = getDatabase(application).watchDogsDao()
    val allDogs: LiveData<List<WatchDogsDataItem>> = watchDogsDao.getAllDogs()

    private val _response = MutableLiveData<String>()
    val response: LiveData<String>
        get() = _response

    init {
        getQuotes()
    }

    private fun getQuotes() {
        viewModelScope.launch {
            try {
                val result = QuotesApi.retrofitService.getQuotes()
                _response.value = "Success: ${result.await()}"
            } catch (e: Exception) {
                _response.value = "Failure: ${e.message}"
            }
        }
    }
}