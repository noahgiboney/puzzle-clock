package com.example.puzzleclock.ui.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.puzzleclock.data.Alarm
import com.example.puzzleclock.data.Meridiem

class NewAlarmViewModel: ViewModel() {

    var hours by mutableStateOf(2)
    var minutes by mutableStateOf(4)
    var time by mutableStateOf("12:00")
    var meridiem by mutableStateOf(Meridiem.AM)
    var alarmTitle by mutableStateOf("")
    var isAlarmSet by mutableStateOf(false)

    fun addAlarm() {

    }

    fun createAlarm(): Alarm {
        val formattedTime = String.format("%02d:%02d", hours, minutes)
        return Alarm(
            // Unique ID
            id = System.currentTimeMillis().toInt(),
            time = formattedTime,
            meridiem = meridiem,
            title = alarmTitle,
            isSet = isAlarmSet
        )
    }
}