package com.udacity.project5.watchdog.data.dto

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "quotes_db")
data class Quote(
    @PrimaryKey @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "quote") val content: String,
    @ColumnInfo(name = "author") val author: String
)
