package com.example.puzzleclock.ui.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.puzzleclock.data.Alarm
import com.example.puzzleclock.data.Meridiem

class NewAlarmViewModel: ViewModel() {
    var alarmTitle by mutableStateOf("")
    var isAlarmSet by mutableStateOf(false)

    fun createAlarm(hours: Int, minutes: Int, meridiem: Meridiem, alarmTitle: String, isSet: Boolean): Alarm {
        val adjustedHours = when {
            hours == 0 -> 12
            hours > 12 -> hours - 12
            else -> hours
        }
        return Alarm(
            id = java.util.UUID.randomUUID().hashCode(),
            hours = adjustedHours,
            minutes = minutes,
            meridiem = meridiem,
            title = alarmTitle,
            isSet = isSet
        )
    }
}