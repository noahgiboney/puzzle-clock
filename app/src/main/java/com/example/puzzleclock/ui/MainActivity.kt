package com.example.puzzleclock.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import androidx.lifecycle.ViewModelProvider
import com.example.puzzleclock.data.AlarmRepository
import com.example.puzzleclock.ui.screens.PuzzleScreen
import com.example.puzzleclock.ui.viewModels.AlarmsViewModel
import com.example.puzzleclock.ui.viewModels.AlarmsViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val repository = AlarmRepository(applicationContext) // Create repository instance
        val factory = AlarmsViewModelFactory(repository) // Pass it to the factory
        val alarmsViewModel: AlarmsViewModel = ViewModelProvider(this, factory)[AlarmsViewModel::class.java]

        setContent {
            com.example.puzzleclock.ui.theme.PuzzleClockTheme {
                Surface {
                    PuzzleClockApp(alarmsViewModel)
                }
            }
        }
    }
}