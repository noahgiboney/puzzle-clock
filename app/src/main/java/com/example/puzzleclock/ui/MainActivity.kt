package com.example.puzzleclock.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import com.example.puzzleclock.ui.screens.PuzzleScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            com.example.puzzleclock.ui.theme.PuzzleClockTheme {
                Surface {
                    PuzzleScreen()
                }
            }
        }
    }
}