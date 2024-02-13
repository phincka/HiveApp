package com.example.hiveapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.hiveapp.data.model.Hive
import kotlinx.coroutines.flow.Flow

@Dao
interface HiveDao {
    @Insert
    suspend fun insert(hive: Hive)

    @Query("SELECT * FROM hive_table")
    fun getAll(): Flow<List<Hive>>
}