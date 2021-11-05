package com.udacity.project5.watchdog.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.udacity.project5.watchdog.data.dto.WatchDogsDataItem

@Dao
interface WatchDogsDao {
    @Query("SELECT * FROM watch_dogs")
    suspend fun getAllDogs(): LiveData<List<WatchDogsDataItem>>

    @Query("SELECT * FROM watch_dogs WHERE dog_id = :dogId")
    suspend fun getDogById(dogId: String): WatchDogsDataItem?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveDog(dog: WatchDogsDataItem)

    @Query("DELETE FROM watch_dogs")
    suspend fun deleteAllDogs()
}