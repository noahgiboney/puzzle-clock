package com.example.puzzleclock.data

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat
import com.example.puzzleclock.ui.activities.PuzzleActivity

class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        // Start your PuzzleActivity when alarm triggers
        val puzzleIntent = Intent(context, PuzzleActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        ContextCompat.startActivities(context, arrayOf(puzzleIntent), null)
    }
}