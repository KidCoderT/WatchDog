package com.udacity.project5.watchdog.data.api

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Quote(
    val tags: List<String>,
    @Json(name = "_id") val id: String,
    val content: String,
    val author: String,
    val authorSlug: String,
    val length: Number,
    val dateAdded: String,
    val dateModified: String
) : Parcelable

@Parcelize
data class QuotesList(
    val count: Int,
    val totalCount: Int,
    val page: Int,
    val totalPages: Int,
    val lastItemIndex: Int,
    val results: List<Quote>
) : Parcelable
