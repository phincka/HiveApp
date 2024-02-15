package com.example.hiveapp.ui.theme.screens.home

import androidx.lifecycle.ViewModel
import com.example.hiveapp.data.model.Hive
import com.example.hiveapp.data.repository.HiveRepository
import kotlinx.coroutines.flow.Flow

class HomeViewModel(private val hiveRepository: HiveRepository) : ViewModel() {
    val getAll: Flow<List<Hive>> = hiveRepository.getAll()
}