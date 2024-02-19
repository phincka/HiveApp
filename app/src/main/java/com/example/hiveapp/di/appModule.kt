package com.example.hiveapp.di

import androidx.room.Room
import com.example.hiveapp.data.database.HiveDatabase
import com.example.hiveapp.data.repository.HiveRepository
import com.example.hiveapp.data.repository.WeatherRepository
import com.example.hiveapp.notifications.NotificationChannels
import com.example.hiveapp.notifications.NotificationService
import com.example.hiveapp.ui.theme.screens.addHiveLocation.AddHiveLocationViewModel
import com.example.hiveapp.ui.theme.screens.createEditHive.CreateEditHiveViewModel
import com.example.hiveapp.ui.theme.screens.hive.HiveViewModel
import com.example.hiveapp.ui.theme.screens.home.HomeViewModel
import com.example.hiveapp.ui.theme.screens.weatherScreen.WeatherViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { Room.databaseBuilder(androidContext(), HiveDatabase::class.java, "hive-database").fallbackToDestructiveMigration().build() }
    single { get<HiveDatabase>().hiveDao() }
    single { HiveRepository(get()) }
    single { WeatherRepository() }

    single { NotificationChannels(androidContext()) }
    single { NotificationService(androidContext()) }

    viewModel { HomeViewModel(get(), get()) }
    viewModel { HiveViewModel(get()) }
    viewModel { CreateEditHiveViewModel(get()) }
    viewModel { AddHiveLocationViewModel(get()) }
    viewModel { WeatherViewModel(get(), get()) }
}