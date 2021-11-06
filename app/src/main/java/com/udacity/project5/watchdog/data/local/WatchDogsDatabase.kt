package com.udacity.project5.watchdog.data.local

import android.content.Context
import androidx.room.*
import com.udacity.project5.watchdog.data.dto.Quote
import com.udacity.project5.watchdog.data.dto.WatchDogsDataItem
import java.util.*

@Database(entities = [WatchDogsDataItem::class, Quote::class], version = 5, exportSchema = false)
abstract class WatchDogsDatabase : RoomDatabase() {

    abstract fun watchDogsDao(): WatchDogsDao
    abstract fun quotesDao(): QuotesDao

    companion object {
        @Volatile
        private var INSTANCE: WatchDogsDatabase? = null

        fun getDatabase(context: Context): WatchDogsDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WatchDogsDatabase::class.java,
                    "watch_dogs_db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}

