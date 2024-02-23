package com.example.hiveapp

import android.app.Application
import com.example.hiveapp.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class HiveApp : Application(){
    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidContext(this@HiveApp)
            modules(appModule)
        }
    }
}