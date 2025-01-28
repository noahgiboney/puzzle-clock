package com.example.puzzleclock

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TimeInput
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import java.util.Calendar

class NewAlarmViewModel: ViewModel() {

    var isAlarmSet by mutableStateOf(false)

    private val currentDay = Calendar.getInstance()

    fun addAlarm() {

    }
}