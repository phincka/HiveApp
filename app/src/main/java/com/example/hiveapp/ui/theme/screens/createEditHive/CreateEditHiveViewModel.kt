package com.example.hiveapp.ui.theme.screens.createEditHive

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hiveapp.data.model.HiveModel
import com.example.hiveapp.domain.usecase.hive.CreateHiveUseCase
import com.example.hiveapp.domain.usecase.notification.HiveCreatedNotificationUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class CreateEditHiveViewModel(
    private val createHiveUseCase: CreateHiveUseCase,
    private val hiveCreatedNotificationUseCase: HiveCreatedNotificationUseCase
) : ViewModel() {
    fun insertHive(hive: HiveModel) {
        CoroutineScope(viewModelScope.coroutineContext).launch {
            createHiveUseCase(hive)
        }
    }

    fun hiveCreatedNotification() = hiveCreatedNotificationUseCase()
}