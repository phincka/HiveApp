package com.example.hiveapp.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.hiveapp.data.model.Hive
import com.example.hiveapp.data.model.HiveLocation
import kotlinx.coroutines.flow.Flow

@Dao
interface HiveDao {
    @Insert
    suspend fun insert(hive: Hive)

    @Update
    suspend fun update(hive: Hive)

    @Delete
    suspend fun delete(hives: List<Hive>)

    @Query("SELECT * FROM hive_table")
    fun getAll(): Flow<List<Hive>>

    @Query("SELECT * FROM hive_table WHERE id = :id")
    fun getHive(id: Int): Flow<List<Hive>>

    @Query("SELECT id, name, lat, lng FROM hive_table WHERE lat > 0 AND lng > 0")
    fun getHivesLocations(): Flow<List<HiveLocation>>

    @Query("UPDATE hive_table SET lat = :lat, lng = :lng WHERE id = :id")
    fun updateHiveLocation(id: Int, lat: Double, lng: Double)
}