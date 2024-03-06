package com.example.hiveapp.domain.usecase.notification

import com.example.hiveapp.notifications.NotificationService
import org.koin.core.annotation.Single

@Single
class HiveCreatedNotificationUseCase(
    private val notificationService: NotificationService
) {
    operator fun invoke() = notificationService.hiveCreatedNotification()
}