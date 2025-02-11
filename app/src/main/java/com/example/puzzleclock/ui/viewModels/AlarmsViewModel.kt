package com.example.puzzleclock.ui.viewModels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.puzzleclock.data.Alarm
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

// FIXME: Is similar to NewAlarmViewModel
class AlarmsViewModel: ViewModel() {

    var alarms = MutableStateFlow<List<Alarm>>(emptyList())

    var isAlarmSet by mutableStateOf(false)

    fun addAlarm(alarm: Alarm) {
        alarms.update { currentList -> currentList + alarm }
    }

    fun toggleAlarm(alarm: Alarm) {
        alarms.update { currentList ->
            currentList.map { if (it.id == alarm.id) it.copy(isSet = !it.isSet) else it }
        }
    }
}
