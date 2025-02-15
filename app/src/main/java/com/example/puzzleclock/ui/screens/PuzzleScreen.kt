package com.example.puzzleclock.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.puzzleclock.data.PuzzleShape
import com.example.puzzleclock.ui.viewModels.PuzzleState
import com.example.puzzleclock.ui.viewModels.PuzzleViewModel

@Composable
fun PuzzleScreen(viewModel: PuzzleViewModel = viewModel()) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(Color.White)
            .padding(top = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
        , verticalArrangement = Arrangement.Top
    ) {
        when(viewModel.gameState.value) {
            PuzzleState.START -> StartGameView(viewModel = viewModel)
            PuzzleState.SHOWCASE -> PuzzleShowcaseView(viewModel = viewModel)
            PuzzleState.PLAYING -> PlayingView(viewModel = viewModel)
        }
    }
}

@Composable
fun StartGameView(viewModel: PuzzleViewModel) {
    Text(
        text = "Begin Puzzle When Ready",
        style = TextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        ),
        modifier = Modifier.padding(20.dp)
    )

    Button(
        onClick = {
            viewModel.startNewGame()
        }
    ) {
        Text("Start")
    }
}

@Composable
fun PuzzleShowcaseView(viewModel: PuzzleViewModel) {
    Text(
        text = "Memorize The Order Of The Shapes",
        style = TextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        ),
        modifier = Modifier.padding(20.dp)
    )

    val currentShapeIndex = viewModel.currentShownShapeIndex.collectAsState().value
    val currentShape = viewModel.currentGameShapes[currentShapeIndex]

    Image(
        painter = painterResource(currentShape.drawableResId),
        contentDescription = currentShape.contentDescription,
        modifier = Modifier
            .size(175.dp)
            .clip(RoundedCornerShape(16.dp))
    )
}

@Composable
fun PlayingView(viewModel: PuzzleViewModel) {
    Text(
        text = "Tap The Shapes In The Correct Order",
        style = TextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        ),
        modifier = Modifier.padding(20.dp)
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.padding(8.dp),
        contentPadding = PaddingValues(8.dp),
        horizontalArrangement = Arrangement.spacedBy(15.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        items(PuzzleShape.entries.toTypedArray()) { shape ->
            GameButton(
                shape = shape,
                viewModel = viewModel
            )
        }
    }

    if (viewModel.hasLost.value) {
        Button(
            onClick = { viewModel.startNewGame() }
        )
        { Text("Reset Game") }
    }
}

@Composable
fun GameButton(shape: PuzzleShape, viewModel: PuzzleViewModel) {
    val borderColor = when {
        viewModel.userSelections.contains(shape) -> Color.Green
        viewModel.hasLost.value && viewModel.mostRecentSelection.value == shape -> Color.Red
        else -> Color.White
    }

    Image(
        painter = painterResource(shape.drawableResId),
        contentDescription = shape.contentDescription,
        modifier = Modifier
            .size(175.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable (
                enabled = !viewModel.hasLost.value && viewModel.userSelections.size < 4
            ) {
                viewModel.handleUserSelectShape(shape)
            }
            .border(
                width = 6.dp,
                color = borderColor,
                shape = RoundedCornerShape(16.dp)
            )
    )
}



