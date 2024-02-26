package com.example.hiveapp.di

import androidx.room.Room
import com.example.hiveapp.data.database.HiveDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {
    single { Room.databaseBuilder(androidContext(), HiveDatabase::class.java, "hive-database").fallbackToDestructiveMigration().build() }
    single { get<HiveDatabase>().hiveDao() }
}