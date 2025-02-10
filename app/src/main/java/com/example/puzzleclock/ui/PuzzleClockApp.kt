package com.example.puzzleclock.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.puzzleclock.ui.screens.AlarmsScreen
import com.example.puzzleclock.ui.screens.NewAlarmScreen
import com.example.puzzleclock.ui.screens.SettingsScreen
import com.example.puzzleclock.ui.viewModels.AlarmsViewModel
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
    val alarmsViewModel: AlarmsViewModel = viewModel()

    NavHost(navController = navController, startDestination = NavRoutes.Alarms) {
        composable<NavRoutes.Alarms> {
            AlarmsScreen(
                onNavigateToEditAlarm = { navController.navigate(NavRoutes.EditAlarm) },
                onNavigateToSettings = { navController.navigate(NavRoutes.Settings) },
                viewModel = alarmsViewModel
            )
        }

        composable<NavRoutes.EditAlarm> {
           NewAlarmScreen(
               onNavigateUp = { navController.navigateUp() },
               alarmsViewModel = alarmsViewModel
           )
        }

        composable<NavRoutes.Settings> {
            SettingsScreen(
                onNavigateUp = { navController.navigateUp() }
            )
        }
    }
}
