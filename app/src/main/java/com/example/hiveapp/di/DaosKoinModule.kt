package com.example.hiveapp.di

import com.example.hiveapp.data.database.HiveDatabase
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module(includes = [DatabaseKoinModule::class])
class DaosKoinModule {
    @Single
    fun hiveDao(hiveDatabase: HiveDatabase) = hiveDatabase.hiveDao()
}