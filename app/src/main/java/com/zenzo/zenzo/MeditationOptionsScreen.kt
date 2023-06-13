package com.zenzo.zenzo

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.RadioButton
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Row
import androidx.compose.ui.Alignment
import com.zenzo.zenzo.SetTimer
import com.zenzo.zenzo.BreathingPattern

@Composable
fun MeditationOptionsScreen() {
    Column(modifier = Modifier.padding(16.dp)) {
        var duration by remember { mutableStateOf(13) }
        SetTimer(duration = duration, onDurationChange = { duration = it })

        var selectedPattern by remember { mutableStateOf(BreathingPattern.BALANCE) }
        BreathingPattern.values().forEach { pattern ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = selectedPattern == pattern,
                    onClick = { selectedPattern = pattern }
                )
                Text(text = pattern.name)
            }
        }


        // Start button
        Button(onClick = { startMeditation(duration, selectedPattern) }) {
            Text("Start")
        }
    }
}

fun startMeditation(duration: Int, pattern: BreathingPattern) {
    // Here you would navigate to a new screen with the animation
}

