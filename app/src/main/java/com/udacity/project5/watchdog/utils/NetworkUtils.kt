package com.udacity.project5.watchdog.utils

import com.udacity.project5.watchdog.data.dto.Quote
import org.json.JSONObject

fun parseQuotesJsonResult(response: String): ArrayList<Quote> {
    val jsonResult = JSONObject(response)
    val jsonArray = jsonResult.getJSONArray("results")

    val quotesList = ArrayList<Quote>()

    for (i in 0 until jsonArray.length()) {
        val quotesListItem = jsonArray.getJSONObject(i)

        val id = quotesListItem.getString("_id")
        val author = if (quotesListItem.isNull("author")) "Anonymous" else quotesListItem.getString("author")
        val content = quotesListItem.getString("content")

        val quote = Quote(id, author, content)
        quotesList.add(quote)
    }

    return quotesList
}