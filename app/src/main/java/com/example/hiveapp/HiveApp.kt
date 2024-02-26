package com.example.hiveapp

import android.app.Application
import com.example.hiveapp.di.AppModule
import com.example.hiveapp.di.appModule
import com.example.hiveapp.notifications.NotificationChannels
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.ksp.generated.module

class HiveApp : Application(){
    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidContext( this@HiveApp)
            modules(
                appModule,
                AppModule().module
            )
        }

        val notificationChannels: NotificationChannels by inject()
        notificationChannels.hiveChannel()
    }
}