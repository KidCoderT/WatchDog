package com.udacity.project5.watchdog.data.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quotes")
data class Quote(
    @PrimaryKey @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "quote") val content: String,
    @ColumnInfo(name = "author") val author: String
)
