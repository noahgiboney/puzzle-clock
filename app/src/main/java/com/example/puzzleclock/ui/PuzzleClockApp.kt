package com.example.puzzleclock.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.puzzleclock.ui.screens.AlarmsScreen
import com.example.puzzleclock.ui.screens.NewAlarmScreen
import kotlinx.serialization.Serializable

sealed class NavRoutes {
    @Serializable
    data object Alarms

    @Serializable
    data object EditAlarm

    @Serializable
    data object Settings
}

@Composable
fun PuzzleClockApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = NavRoutes.Alarms) {
        composable<NavRoutes.Alarms> {
            AlarmsScreen(
                navController = navController
            )
        }

        composable<NavRoutes.EditAlarm> {
           NewAlarmScreen(
               navController = navController
           )
        }

        composable<NavRoutes.Settings> {
            Text("Settings Soon")
        }
    }
}
