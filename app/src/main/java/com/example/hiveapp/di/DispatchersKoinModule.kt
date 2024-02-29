package com.example.hiveapp.di

import kotlinx.coroutines.Dispatchers
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module
class DispatchersKoinModule{
    @Single
    fun dispatcher() = Dispatchers.IO
}