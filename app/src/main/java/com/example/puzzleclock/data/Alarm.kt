package com.example.puzzleclock.data

enum class Meridiem {
    AM, PM
}

// TODO: finalize Alarm data class (where should it live?)
data class Alarm(val id: Int, val hours: Int, val minutes: Int, val meridiem: Meridiem,
                 val title: String, val isSet: Boolean)