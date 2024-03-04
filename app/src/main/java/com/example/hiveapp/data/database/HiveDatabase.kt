package com.example.hiveapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.hiveapp.data.dao.HiveDao
import com.example.hiveapp.data.model.HiveModel

@Database(entities = [HiveModel::class], version = 9)
abstract class HiveDatabase: RoomDatabase() {
    abstract fun hiveDao(): HiveDao
}