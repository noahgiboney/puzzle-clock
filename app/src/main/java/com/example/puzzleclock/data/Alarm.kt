package com.example.puzzleclock.data

import java.time.LocalTime

enum class Meridiem {
    AM, PM
}

data class Alarm(val id: Int, val time: String, val meridiem: Meridiem,
                 val title: String, val isSet: Boolean)