package com.example.hiveapp.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.hiveapp.data.model.Hive
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
}