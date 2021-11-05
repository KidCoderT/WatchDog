package com.udacity.project5.watchdog.data.local

import android.content.Context
import androidx.room.*
import com.udacity.project5.watchdog.data.dto.WatchDogsDataItem
import java.time.LocalDateTime
import java.util.*

@Database(entities = [WatchDogsDataItem::class], version = 3, exportSchema = false)
abstract class WatchDogsDatabase : RoomDatabase() {

    abstract fun watchDogsDao(): WatchDogsDao

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
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}

