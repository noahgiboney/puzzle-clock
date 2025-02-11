package com.example.puzzleclock.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TimeInput
import androidx.compose.material3.TimePickerState
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.puzzleclock.R
import com.example.puzzleclock.data.Meridiem
import com.example.puzzleclock.ui.viewModels.AlarmsViewModel
import com.example.puzzleclock.ui.viewModels.NewAlarmViewModel
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewAlarmScreen(
    viewModel: NewAlarmViewModel = viewModel(),
    onNavigateUp: () -> Unit,
    alarmsViewModel: AlarmsViewModel
) {
    val currentTime = Calendar.getInstance()

    val timePickerState = rememberTimePickerState(
        initialHour = currentTime.get(Calendar.HOUR_OF_DAY),
        initialMinute = currentTime.get(Calendar.MINUTE),
        is24Hour = false,
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text("New Alarm") },
                navigationIcon =  {
                    IconButton(onClick = { onNavigateUp() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                val selectedHour = timePickerState.hour
                val selectedMinute = timePickerState.minute

                val meridiem = if (selectedHour < 12) Meridiem.AM else Meridiem.PM

                val newAlarm = viewModel.createAlarm(
                    selectedHour,
                    selectedMinute,
                    meridiem,
                    viewModel.alarmTitle,
                    viewModel.isAlarmSet
                )
                alarmsViewModel.addAlarm(newAlarm)
                // pop backstack
                onNavigateUp()
            }) {
                Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = "Add Alarm"
                )
            }
        },
    )
    { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            AlarmTitleInput(viewModel = viewModel)

            TimePicker(timePickerState)

            AlarmToggle(viewModel = viewModel)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePicker(timePickerState: TimePickerState, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp),
            contentAlignment = Alignment.Center
        ) {
            TimeInput(
                modifier = modifier.padding(top = 20.dp),
                state = timePickerState,
                )
        }
    }
}

@Composable
fun AlarmTitleInput(
    modifier: Modifier = Modifier,
    viewModel: NewAlarmViewModel
) {
    TextField(
        modifier = modifier.fillMaxWidth(),
        value = viewModel.alarmTitle,
        onValueChange = { viewModel.alarmTitle = it },
        label = { Text("Alarm Title") }
    )
}

@Composable
fun AlarmToggle(
    modifier: Modifier = Modifier,
    viewModel: NewAlarmViewModel
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(stringResource(R.string.set_alarm))
            Switch(
                checked = viewModel.isAlarmSet,
                onCheckedChange = { viewModel.isAlarmSet = it }
            )
        }
    }
}