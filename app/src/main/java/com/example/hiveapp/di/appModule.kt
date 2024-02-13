package com.example.hiveapp.di

import androidx.room.Room
import com.example.hiveapp.ui.theme.screens.Home.HomeViewModel
import com.example.hiveapp.data.database.HiveDatabase
import com.example.hiveapp.data.repository.HiveRepository
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { Room.databaseBuilder(androidContext(), HiveDatabase::class.java, "hive-database").build() }
    single { get<HiveDatabase>().hiveDao() }
    single { HiveRepository(get()) }
    viewModel { HomeViewModel(get()) }
}