package com.udacity.project5.watchdog.data.api

import com.squareup.moshi.Json

data class Quote(
    val tags: List<String>,
    @Json(name = "_id") val id: String,
    val content: String,
    val author: String,
    val authorSlug: String,
    val length: Number,
    val dateAdded: String,
    val dateModified: String
)

data class QuotesList(
    val count: Int,
    val totalCount: Int,
    val page: Int,
    val totalPages: Int,
    val lastItemIndex: Number,
    val results: List<Any>
)
