package com.example.hiveapp

import android.app.Application
import com.example.hiveapp.di.appModule
import com.example.hiveapp.notifications.NotificationChannels
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class HiveApp : Application(){
    override fun onCreate() {
        super.onCreate()

        createNotificationChannels()

        startKoin{
            androidContext(this@HiveApp)
            modules(appModule)
        }
    }

    private fun createNotificationChannels() {
        val notificationChannels = NotificationChannels(this)
        notificationChannels.hiveChannel()
    }
}