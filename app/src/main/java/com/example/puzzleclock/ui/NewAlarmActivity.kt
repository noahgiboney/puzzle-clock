package com.example.puzzleclock.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
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
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.puzzleclock.ui.theme.PuzzleClockTheme
import java.util.Calendar
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.puzzleclock.viewModels.NewAlarmViewModel
import com.example.puzzleclock.R

class NewAlarmActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PuzzleClockTheme {
                NewAlarmScaffold()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewAlarmScaffold(
    viewModel: NewAlarmViewModel = viewModel()
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text("New Alarm") },
                navigationIcon =  {
                    IconButton(onClick = { /*navigation back*/ }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { viewModel.addAlarm() }) {
                Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = "Add Alarm"
                )
            }
        },
    )
    { innerPadding ->
        AddAlarmScreen(
            modifier = Modifier.padding(innerPadding),
            viewModel = viewModel
            )
    }
}

@Composable
fun AddAlarmScreen(
    modifier: Modifier = Modifier,
    viewModel: NewAlarmViewModel
) {
    Column(
        modifier = modifier
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        AlarmTitleInput(viewModel = viewModel)

        TimePicker()

        AlarmToggle(viewModel = viewModel)
    }
}

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePicker(modifier: Modifier = Modifier) {
    val currentTime = Calendar.getInstance()

    val timePickerState = rememberTimePickerState(
        initialHour = currentTime.get(Calendar.HOUR_OF_DAY),
        initialMinute = currentTime.get(Calendar.MINUTE),
        is24Hour = false,
    )

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
    viewModel: NewAlarmViewModel) {
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