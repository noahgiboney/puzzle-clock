package com.example.puzzleclock.ui.activities

import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.puzzleclock.R
import com.example.puzzleclock.ui.screens.PuzzleScreen
import com.example.puzzleclock.ui.theme.PuzzleClockTheme

class PuzzleActivity : ComponentActivity() {
    private lateinit var mediaPlayer: MediaPlayer

    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Show over lock screen and turn screen on
        setShowWhenLocked(true)
        setTurnScreenOn(true)
        window.addFlags(android.view.WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        // loop over media player
        mediaPlayer = MediaPlayer.create(this, R.raw.alarm_sound)
        mediaPlayer.isLooping = true
        mediaPlayer.start()

        setContent {
            PuzzleClockTheme {
                PuzzleActivityContent(
                    onDismiss = {
                        mediaPlayer.stop()
                        finish()
                    }
                )
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mediaPlayer.isPlaying) {
            mediaPlayer.stop()
        }
        mediaPlayer.release()
    }
}

@Composable
fun PuzzleActivityContent(onDismiss: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {
        PuzzleScreen(onDismiss = onDismiss) // Pass onDismiss to PuzzleScreen
    }
}