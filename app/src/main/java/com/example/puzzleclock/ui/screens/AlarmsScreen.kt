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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.puzzleclock.ui.viewModels.AlarmsViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.puzzleclock.data.Alarm
import com.example.puzzleclock.data.Meridiem
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlarmsScreen(
    onNavigateToSettings: () -> Unit,
    onNavigateToEditAlarm: () -> Unit,
    viewModel: AlarmsViewModel
) {
    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(
                        text = "Puzzle Clock"
                    )
                },
                actions = {
                    IconButton(onClick = { onNavigateToSettings() }) {
                        Icon(
                            imageVector = Icons.Filled.Settings,
                            contentDescription = "Modify settings"
                        )
                    }
                },
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { onNavigateToEditAlarm() }) {
                Log.d("tag", "test")
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Create new alarm"
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
    var currAlarmsList = viewModel.alarms
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


@Composable
fun AlarmCard(
    alarm: Alarm,
    modifier: Modifier = Modifier,
    viewModel: AlarmsViewModel = viewModel()
) {

    val formattedTime = LocalTime.of(alarm.hours, alarm.minutes)
        .format(DateTimeFormatter.ofPattern("hh:mm a"))

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp)
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
                Text(alarm.title, fontSize = 18.sp)
                // TODO: format hours and minutes
                Text(formattedTime)
                /*Text(alarm.hours.toString() +
                        alarm.minutes.toString() +
                        alarm.meridiem, fontSize = 14.sp)*/
            }
            Switch(
                checked = viewModel.isAlarmSet,
                onCheckedChange = { viewModel.isAlarmSet = it }
            )
        }
    }
}