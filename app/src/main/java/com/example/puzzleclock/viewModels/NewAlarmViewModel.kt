package com.example.puzzleclock.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class NewAlarmViewModel: ViewModel() {

    var alarmTitle by mutableStateOf("")
    var isAlarmSet by mutableStateOf(false)

    fun addAlarm() {

    }
}