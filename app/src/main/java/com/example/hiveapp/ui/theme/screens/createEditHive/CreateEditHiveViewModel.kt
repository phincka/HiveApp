package com.example.hiveapp.ui.theme.screens.createEditHive

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hiveapp.data.model.HiveModel
import com.example.hiveapp.data.repository.HiveRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class CreateEditHiveViewModel(private val hiveRepository: HiveRepository) : ViewModel() {
    fun insertHive(hive: HiveModel) {
        CoroutineScope(viewModelScope.coroutineContext).launch {
            hiveRepository.insertHive(hive)
        }
    }
}