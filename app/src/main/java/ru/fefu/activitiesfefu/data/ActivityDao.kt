package ru.fefu.activitiesfefu.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ActivityDao {
    @Query("SELECT * FROM activities ORDER BY startDate DESC")
    fun getAllActivities(): LiveData<List<ActivityEntity>>

    @Insert
    suspend fun insertActivity(activity: ActivityEntity)
} 