package com.example.puzzleclock.ui.viewModels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.puzzleclock.data.Alarm
import com.example.puzzleclock.data.AlarmRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AlarmsViewModel(private val repository: AlarmRepository): ViewModel() {

    // Private version (updates alarms)
    private val _alarms = MutableStateFlow<List<Alarm>>(emptyList())
    // Ready only version
    val alarms: StateFlow<List<Alarm>> = _alarms

    init {
        viewModelScope.launch {
            repository.getAlarms().collect { savedAlarms ->
                _alarms.value = savedAlarms
            }
        }
    }

    var isAlarmSet by mutableStateOf(false)

    fun addAlarm(alarm: Alarm) {
        //alarms.update { currentList -> currentList + alarm }
        _alarms.value += alarm
        saveAlarms()
    }

    fun toggleAlarm(alarm: Alarm) {
        _alarms.value = _alarms.value.map{
             if (it.id == alarm.id) it.copy(isSet = !it.isSet) else it
        }
        saveAlarms()
    }

    private fun saveAlarms() {
        viewModelScope.launch {
            repository.saveAlarms(_alarms.value)
        }
    }
}
