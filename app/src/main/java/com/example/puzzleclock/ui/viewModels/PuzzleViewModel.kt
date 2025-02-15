package com.example.puzzleclock.ui.viewModels

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.puzzleclock.data.PuzzleShape
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

enum class PuzzleState {
    START, SHOWCASE, PLAYING
}

class PuzzleViewModel: ViewModel() {

    var gameState = mutableStateOf(PuzzleState.START)

    var currentGameShapes = PuzzleShape.entries.shuffled().toTypedArray()

    private var _currentShownShapeIndex = MutableStateFlow(0)

    val currentShownShapeIndex: StateFlow<Int> get() = _currentShownShapeIndex.asStateFlow()

    var userSelections: SnapshotStateList<PuzzleShape> = mutableStateListOf()

    var mostRecentSelection = mutableStateOf<PuzzleShape?>(null)

    var hasLost = mutableStateOf(false)

    fun handleUserSelectShape(shape: PuzzleShape) {
        mostRecentSelection.value = shape

        val currentComparisonShape = currentGameShapes[userSelections.size]

        if (shape != currentComparisonShape) {
            hasLost.value = true
        } else {
            userSelections.add(shape)
        }
    }

    fun startNewGame() {
        currentGameShapes = PuzzleShape.entries.shuffled().toTypedArray()
        gameState.value = PuzzleState.SHOWCASE
        hasLost.value = false
        userSelections.clear()
        _currentShownShapeIndex.value = 0
        showcaseCurrentShapes()
    }

    fun showcaseCurrentShapes() {
        viewModelScope.launch {
            while(currentShownShapeIndex.value < 4) {
                delay(1500)
                _currentShownShapeIndex.value++
            }

            gameState.value = PuzzleState.PLAYING
        }
    }
}