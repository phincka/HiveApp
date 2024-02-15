package com.example.hiveapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.hiveapp.data.dao.HiveDao
import com.example.hiveapp.data.model.Hive

@Database(entities = [Hive::class], version = 8)
abstract class HiveDatabase: RoomDatabase() {
    abstract fun hiveDao(): HiveDao
}