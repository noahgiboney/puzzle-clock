package com.example.puzzleclock.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

enum class Meridiem {
    AM, PM
}

// TODO: finalize Alarm data class (where should it live?)
data class Alarm(val id: Int, val hours: Int, val minutes: Int, val meridiem: Meridiem,
    val title: String, val isSet: Boolean)

// FIXME: Is similar to NewAlarmViewModel
class AlarmsViewModel: ViewModel() {

    var alarms = mutableListOf<Alarm>()

    var isAlarmSet by mutableStateOf(false)

    fun toggleAlarm(alarmId: Int) {

    }
}
