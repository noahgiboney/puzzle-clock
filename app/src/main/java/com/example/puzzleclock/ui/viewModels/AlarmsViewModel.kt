package com.example.puzzleclock.ui.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.puzzleclock.data.Alarm

// FIXME: Is similar to NewAlarmViewModel
class AlarmsViewModel: ViewModel() {

    var alarms = mutableListOf<Alarm>()

    var isAlarmSet by mutableStateOf(false)

    fun toggleAlarm(alarmId: Int) {

    }
}
