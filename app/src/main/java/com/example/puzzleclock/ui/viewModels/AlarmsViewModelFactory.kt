package com.example.puzzleclock.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.puzzleclock.data.AlarmRepository

// Tells AlarmsViewModel how to create a new ViewModel instance using the provided repository
class AlarmsViewModelFactory(private val repository: AlarmRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AlarmsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AlarmsViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}