package com.example.hiveapp.di

import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module

@Module(includes = [DaosKoinModule::class, DispatchersKoinModule::class, FirebaseModule::class])
@ComponentScan("com.example.hiveapp.data.repository")
class DataKoinModule