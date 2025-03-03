package com.example.puzzleclock.ui.viewModels

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.puzzleclock.data.AlarmReceiver
import com.example.puzzleclock.data.Alarm
import com.example.puzzleclock.data.AlarmRepository
import com.example.puzzleclock.data.Meridiem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Calendar

class AlarmsViewModel(
    private val repository: AlarmRepository,
    private val context: Context
) : ViewModel() {

    private val _alarms = MutableStateFlow<List<Alarm>>(emptyList())
    val alarms: StateFlow<List<Alarm>> = _alarms

    private val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

    init {
        viewModelScope.launch {
            repository.getAlarms().collect { savedAlarms ->
                _alarms.value = savedAlarms
            }
        }
    }

    fun addAlarm(alarm: Alarm) {
        _alarms.value += alarm
        saveAlarms()
        if (alarm.isSet) {
            scheduleAlarm(alarm)
        }
    }

    fun deleteAlarm(alarm: Alarm) {
        if (alarm.isSet) {
            cancelAlarm(alarm) // Cancel the scheduled alarm if it’s set
        }
        _alarms.value = _alarms.value.filter { it.id != alarm.id } // Remove from list
        saveAlarms() // Update repository
    }


    fun toggleAlarm(alarm: Alarm) {
        _alarms.value = _alarms.value.map {
            if (it.id == alarm.id) {
                val newAlarm = it.copy(isSet = !it.isSet)
                if (newAlarm.isSet) {
                    scheduleAlarm(newAlarm)
                } else {
                    cancelAlarm(newAlarm)
                }
                newAlarm
            } else it
        }
        saveAlarms()
    }

    private fun scheduleAlarm(alarm: Alarm) {
        val intent = Intent(context, AlarmReceiver::class.java).apply {
            action = "com.example.puzzleclock.ALARM_TRIGGERED"
            putExtra("alarmId", alarm.id)
        }

        val pendingIntent = PendingIntent.getBroadcast(
            context,
            alarm.id.hashCode(),
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val calendar = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, alarm.hours)
            set(Calendar.MINUTE, alarm.minutes)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
            // If time is in PM, add 12 hours
            if (alarm.meridiem == Meridiem.PM && alarm.hours != 12) {
                add(Calendar.HOUR_OF_DAY, 12)
            }
            // If time is in AM and it's 12, set to 0
            if (alarm.meridiem == Meridiem.AM && alarm.hours == 12) {
                set(Calendar.HOUR_OF_DAY, 0)
            }
            // If time has already passed today, schedule for tomorrow
            if (timeInMillis < System.currentTimeMillis()) {
                add(Calendar.DAY_OF_MONTH, 1)
            }
        }

        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            pendingIntent
        )
    }

    private fun cancelAlarm(alarm: Alarm) {
        val intent = Intent(context, AlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            alarm.id.hashCode(),
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        alarmManager.cancel(pendingIntent)
    }

    private fun saveAlarms() {
        viewModelScope.launch {
            repository.saveAlarms(_alarms.value)
        }
    }
}