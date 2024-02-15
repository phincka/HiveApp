package com.example.hiveapp.notifications

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.hiveapp.R

class NotificationService(
    private val context: Context
) {
    private val notificationManager = NotificationManagerCompat.from(context)
    fun showCreateNotification() {
        val notification = NotificationCompat.Builder(context, HIVE_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentTitle("Utworzono ul!")
            .setContentText("Twój ul został utworzony :)")
            .build()

        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            Log.d("ERR", "No notification perm")
            return
        }
        notificationManager.notify(1, notification)
    }

    fun showGeoNotification() {
        val notification = NotificationCompat.Builder(context, HIVE_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentTitle("Możesz dodać lokalizacje do ula")
            .setContentText("Możesz dodać lokalizacje do ula. Nie czekaj zrób to teraz.")
            .build()

        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            Log.d("ERR", "No notification perm")
            return
        }
        notificationManager.notify(2, notification)
    }
    companion object {
        const val HIVE_CHANNEL_ID = "hive_channel"
    }
}