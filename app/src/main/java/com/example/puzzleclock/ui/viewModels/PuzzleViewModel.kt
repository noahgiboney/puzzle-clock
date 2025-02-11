package com.example.puzzleclock.ui.viewModels

import androidx.lifecycle.ViewModel
import com.example.puzzleclock.data.PuzzleShape

class PuzzleViewModel: ViewModel() {

    val shapes = PuzzleShape.entries.toTypedArray()

    val gameShapes = PuzzleShape.entries.toTypedArray().shuffle()
}