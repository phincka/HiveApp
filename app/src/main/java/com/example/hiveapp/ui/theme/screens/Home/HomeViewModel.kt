package com.example.hiveapp.ui.theme.screens.Home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hiveapp.data.model.Hive
import com.example.hiveapp.data.repository.HiveRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class HomeViewModel(private val hiveRepository: HiveRepository) : ViewModel() {
    val getAll: Flow<List<Hive>> = hiveRepository.getAll()

    fun insertHive(hive: Hive) {
        CoroutineScope(viewModelScope.coroutineContext).launch {
            hiveRepository.insert(hive)
        }
    }
}