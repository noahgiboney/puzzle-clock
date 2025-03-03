package com.example.puzzleclock.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import androidx.lifecycle.ViewModelProvider
import com.example.puzzleclock.data.AlarmRepository
import com.example.puzzleclock.ui.PuzzleClockApp
import com.example.puzzleclock.ui.viewModels.AlarmsViewModel
import com.example.puzzleclock.ui.viewModels.AlarmsViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Create repository instance with application context
        val repository = AlarmRepository(applicationContext)
        // Pass both repository and context to the updated factory
        val factory = AlarmsViewModelFactory(repository, applicationContext)
        // Get the AlarmsViewModel instance
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