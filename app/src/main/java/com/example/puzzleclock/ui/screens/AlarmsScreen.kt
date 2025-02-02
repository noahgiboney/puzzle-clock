package com.example.puzzleclock.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ALarmsScreen() {
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
                    // TODO: navigate to settings activity
                    IconButton(onClick = { /* do something */ }) {
                        Icon(
                            imageVector = Icons.Filled.Settings,
                            contentDescription = "Modify settings"
                        )
                    }
                },
            )
        },
        floatingActionButton = {
            // TODO: navigate to NewAlarmActivity
            FloatingActionButton(onClick = {}) {
                Log.d("tag", "test")
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Create new alarm"
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding),
        ) {
            // TODO: display list of current alarms in viewmodel (w/ loop)
            AlarmToggle()
        }
    }
}

@Composable
fun AlarmToggle(
    modifier: Modifier = Modifier,
    // TODO: apply viewmodel
) {
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
                // TODO: populate with alarm data from list
                Text("Alarm Title", fontSize = 18.sp)
                Text("10:20am", fontSize = 14.sp)
            }
            Switch(
                // TODO:
                checked = false/*viewModel.isAlarmSet*/,
                onCheckedChange = { /*viewModel.isAlarmSet = it*/ }
            )
        }
    }
}