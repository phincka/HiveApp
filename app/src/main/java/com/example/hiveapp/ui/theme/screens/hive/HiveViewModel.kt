package com.example.hiveapp.ui.theme.screens.hive

import androidx.lifecycle.ViewModel
import com.example.hiveapp.data.model.Hive
import com.example.hiveapp.data.repository.HiveRepository
import kotlinx.coroutines.flow.Flow

class HiveViewModel(private val hiveRepository: HiveRepository) : ViewModel() {
    fun getHive(id: Int): Flow<List<Hive>> {
        return hiveRepository.getHive(id)
    }
}