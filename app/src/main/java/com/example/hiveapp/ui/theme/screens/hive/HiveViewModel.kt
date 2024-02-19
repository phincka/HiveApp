package com.example.hiveapp.ui.theme.screens.hive

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hiveapp.data.model.HiveModel
import com.example.hiveapp.data.repository.HiveRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class HiveViewModel(private val hiveRepository: HiveRepository) : ViewModel() {
    fun getHive(id: Int): Flow<List<HiveModel>> {
        return hiveRepository.getHive(id)
    }

    fun removeHive(hive: HiveModel) = CoroutineScope(viewModelScope.coroutineContext).launch {
        hiveRepository.delete(listOf(hive))
    }
}