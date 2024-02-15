package com.example.hiveapp.ui.theme.screens.hive

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hiveapp.data.model.Hive
import com.example.hiveapp.data.repository.HiveRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class HiveViewModel(private val hiveRepository: HiveRepository) : ViewModel() {
    fun getHive(id: Int): Flow<List<Hive>> {
        return hiveRepository.getHive(id)
    }

    fun removeHive(hive: Hive) = CoroutineScope(viewModelScope.coroutineContext).launch {
        hiveRepository.delete(listOf(hive))
    }
}