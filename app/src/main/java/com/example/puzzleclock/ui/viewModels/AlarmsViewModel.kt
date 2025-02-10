package com.example.puzzleclock.ui.viewModels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.puzzleclock.data.Alarm

// FIXME: Is similar to NewAlarmViewModel
class AlarmsViewModel: ViewModel() {

    var alarms = mutableStateListOf<Alarm>()

    var isAlarmSet by mutableStateOf(false)

    fun addAlarm(alarm: Alarm) {
        alarms.add(alarm)
    }

    fun toggleAlarm(alarmId: Int) {

    }
}
