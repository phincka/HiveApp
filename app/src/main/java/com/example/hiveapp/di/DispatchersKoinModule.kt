package com.example.hiveapp.di

import kotlinx.coroutines.Dispatchers
import org.koin.core.annotation.Module
import org.koin.core.annotation.Named
import org.koin.core.annotation.Single

@Module
class DispatchersKoinModule{
    @Single
    @Named(DISPATCHER_IO)
    fun ioDispatcher() = Dispatchers.IO

    @Single
    @Named(DISPATCHER_MAIN)
    fun mainDispatcher() = Dispatchers.Main
}


const val DISPATCHER_IO = "Dispacher IO"
const val DISPATCHER_MAIN = "Dispacher MAIN"
