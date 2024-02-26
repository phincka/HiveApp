package com.example.hiveapp.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import com.example.hiveapp.R
import org.koin.core.annotation.Single

@Single
class NotificationChannels(
    private val context: Context
){
    fun hiveChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = context.getString(R.string.notification_channel_title)
            val descriptionText = context.getString(R.string.notification_channel_desscription)
            val importance = NotificationManager.IMPORTANCE_DEFAULT

            val channel = NotificationChannel(
                NotificationService.HIVE_CHANNEL_ID,
                name,
                importance
            ).apply { description = descriptionText }

            val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}