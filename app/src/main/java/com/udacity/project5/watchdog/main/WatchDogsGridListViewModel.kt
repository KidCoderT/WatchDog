package com.udacity.project5.watchdog.main

import android.app.Application
import androidx.lifecycle.*
import com.udacity.project5.watchdog.data.api.QuotesApi
import com.udacity.project5.watchdog.data.dto.Quote
import com.udacity.project5.watchdog.utils.parseQuotesJsonResult
import com.udacity.project5.watchdog.data.dto.WatchDogsDataItem
import com.udacity.project5.watchdog.data.local.WatchDogsDatabase.Companion.getDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.await

class WatchDogsGridListViewModel(application: Application) : AndroidViewModel(application) {
    private val watchDogsDao = getDatabase(application).watchDogsDao()
    private val quotesDao = getDatabase(application).quotesDao()
    val allDogs: LiveData<List<WatchDogsDataItem>> = watchDogsDao.getAllDogs()
    private var _dailyQuote = MutableLiveData<Quote?>()
    val dailyQuote: LiveData<Quote?> get() = _dailyQuote

    init {
        viewModelScope.launch {
            getAndSaveQuotes()
            refreshQuote()
        }
    }

    private suspend fun getAndSaveQuotes() {
        withContext(Dispatchers.IO) {
            val result = QuotesApi.retrofitService.getQuotes()
            quotesDao.saveAllQuotes(*parseQuotesJsonResult(result.await()).toTypedArray())
        }
    }

    suspend fun refreshQuote() {
        withContext(Dispatchers.IO) {
            _dailyQuote.postValue(quotesDao.getRandQuote())
        }
    }
}