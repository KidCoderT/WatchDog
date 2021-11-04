package com.udacity.project5.watchdog.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.udacity.project5.watchdog.data.dto.WatchDogsDTO

@Database(entities = [WatchDogsDTO::class], version = 1, exportSchema = false)
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