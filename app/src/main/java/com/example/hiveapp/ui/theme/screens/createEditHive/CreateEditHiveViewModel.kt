package com.example.hiveapp.ui.theme.screens.createEditHive

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hiveapp.data.model.HiveModel
import com.example.hiveapp.domain.hive.CreateHiveUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class CreateEditHiveViewModel(
    private val createHiveUseCase: CreateHiveUseCase
) : ViewModel() {
    fun insertHive(hive: HiveModel) {
        CoroutineScope(viewModelScope.coroutineContext).launch {
            createHiveUseCase(hive)
        }
    }
}