package com.example.puzzleclock.data

import com.example.puzzleclock.R

enum class PuzzleShape(
    val drawableResId: Int,
    val contentDescription: String
) {
    CIRCLE(
        R.drawable.circle,
        "Circle shape"
    ),
    SQUARE(
        R.drawable.square,
        "Square shape"
    ),
    TRIANGLE(
        R.drawable.triangle,
        "Triangle shape"
    ),
    X(
        R.drawable.x,
        "X shape"
    );
}