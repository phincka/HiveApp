package com.example.hiveapp.di

import android.content.Context
import androidx.room.Room
import com.example.hiveapp.data.database.HiveDatabase
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module
class DatabaseKoinModule {

    @Single
    fun database(context: Context) =
        Room.databaseBuilder(context, HiveDatabase::class.java, "hive-database")
            .fallbackToDestructiveMigration()
            .build()
}