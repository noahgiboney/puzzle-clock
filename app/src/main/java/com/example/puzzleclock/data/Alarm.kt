package com.example.puzzleclock.data

import kotlinx.serialization.Serializable
import java.time.LocalTime

enum class Meridiem {
    AM, PM
}

@Serializable
data class Alarm(val id: Int, val hours: Int, val minutes: Int, val meridiem: Meridiem,
                 val title: String, var isSet: Boolean)