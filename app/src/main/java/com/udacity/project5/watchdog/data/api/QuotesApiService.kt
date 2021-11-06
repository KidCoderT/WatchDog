package com.udacity.project5.watchdog.data.api

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://quotable.io/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()

interface QuotesApiService {
    @GET("/quotes?tags=famous-quotes,inspirational&page=1&maxLength=177")
    fun getQuotes():
            Call<String>
}

object QuotesApi {
    val retrofitService: QuotesApiService by lazy {
        retrofit.create(QuotesApiService::class.java)
    }
}