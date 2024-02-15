package com.example.hiveapp.di

import androidx.room.Room
import com.example.hiveapp.data.database.HiveDatabase
import com.example.hiveapp.data.repository.HiveRepository
import com.example.hiveapp.notifications.NotificationService
import com.example.hiveapp.ui.theme.screens.createEditHive.CreateEditHiveViewModel
import com.example.hiveapp.ui.theme.screens.hive.HiveViewModel
import com.example.hiveapp.ui.theme.screens.home.HomeViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { Room.databaseBuilder(androidContext(), HiveDatabase::class.java, "hive-database").fallbackToDestructiveMigration().build() }
    single { get<HiveDatabase>().hiveDao() }
    single { HiveRepository(get()) }

    viewModel { HomeViewModel(get()) }
    viewModel { HiveViewModel(get()) }
    viewModel { CreateEditHiveViewModel(get()) }
}