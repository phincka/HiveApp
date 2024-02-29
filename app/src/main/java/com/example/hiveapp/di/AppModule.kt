package com.example.hiveapp.di

import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module

@Module(includes = [DataKoinModule::class])
@ComponentScan("com.example.hiveapp")
class AppModule