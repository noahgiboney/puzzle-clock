package com.example.puzzleclock.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.puzzleclock.ui.viewModels.AlarmsViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.puzzleclock.data.Alarm
import com.example.puzzleclock.data.AlarmRepository
import com.example.puzzleclock.data.Meridiem
import com.example.puzzleclock.ui.viewModels.AlarmsViewModelFactory
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlarmsScreen(
    onNavigateToSettings: () -> Unit,
    onNavigateToEditAlarm: () -> Unit,
    viewModel: AlarmsViewModel = viewModel(
        factory = AlarmsViewModelFactory(
            repository = AlarmRepository(LocalContext.current),
            context = LocalContext.current
        )
    )
) {
    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                    titleContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
                ),
                title = {
                    Text(
                        text = "Puzzle Clock",
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
                    )
                },
                actions = {
                    IconButton(onClick = { onNavigateToSettings() }) {
                        Icon(
                            imageVector = Icons.Filled.Settings,
                            contentDescription = "Modify settings",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                },
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onNavigateToEditAlarm() },
                containerColor = MaterialTheme.colorScheme.primary,
                elevation = FloatingActionButtonDefaults.elevation(defaultElevation = 8.dp)
            ) {
                Log.d("tag", "test")
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Create new alarm",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    ) { innerPadding ->
       AlarmCardList(viewModel = viewModel, innerPadding = innerPadding)
    }
}

@Composable
fun AlarmCardList(
    modifier: Modifier = Modifier,
    viewModel: AlarmsViewModel,
    innerPadding: PaddingValues = PaddingValues(0.dp)
) {
    val currAlarmsList by viewModel.alarms.collectAsState()

    if (currAlarmsList.isEmpty()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "No alarms set",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            )
        }
    } else {
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            items(currAlarmsList) { alarm ->
                AlarmCard(alarm, modifier, viewModel)
            }
        }
    }
}


@Composable
fun AlarmCard(
    alarm: Alarm,
    modifier: Modifier = Modifier,
    viewModel: AlarmsViewModel = viewModel()
) {
    val adjustedHours = when (alarm.meridiem) {
        Meridiem.AM -> if (alarm.hours == 12) 0 else alarm.hours
        Meridiem.PM -> if (alarm.hours == 12) 12 else alarm.hours + 12
    }

    val formattedTime = LocalTime.of(adjustedHours, alarm.minutes)
        .format(DateTimeFormatter.ofPattern("hh:mm a"))

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clip(RoundedCornerShape(16.dp))
            .shadow(6.dp, RoundedCornerShape(16.dp)), // Soft elevation
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer,
            contentColor = MaterialTheme.colorScheme.onSurface
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .padding(vertical = 14.dp)
            ) {
                Text(alarm.title, fontSize = 18.sp, style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.SemiBold))
                Text(formattedTime, style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Normal))
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Switch(
                    checked = alarm.isSet,
                    onCheckedChange = { viewModel.toggleAlarm(alarm) }
                )
                IconButton(onClick = { viewModel.deleteAlarm(alarm) }) {
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        contentDescription = "Delete alarm",
                        tint = MaterialTheme.colorScheme.error
                    )
                }
            }
        }
    }
}