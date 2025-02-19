package com.example.puzzleclock.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.puzzleclock.ui.NavRoutes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    onNavigateUp: () -> Unit,
    onNavigateToPracticePuzzle: ()-> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Settings") },
                navigationIcon =  {
                    IconButton(onClick = onNavigateUp ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        },
    ) { innerPadding ->
        Column(
            modifier = modifier.padding(innerPadding).padding(20.dp)
        ) {
            Button(
                modifier = modifier
                    .fillMaxWidth(),
                onClick = onNavigateToPracticePuzzle
            ) {
                Text("Practice Puzzle")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PracticePuzzleScreen(
    onNavigateUp: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Practice") },
                navigationIcon = {
                    IconButton(onClick = onNavigateUp ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
            )
        }

    ) { innerPadding ->
        PuzzleScreen(
            modifier = Modifier.padding(innerPadding)
        )
    }
}
