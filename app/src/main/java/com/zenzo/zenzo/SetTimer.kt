package com.zenzo.zenzo

import androidx.compose.material.Slider
import androidx.compose.runtime.Composable
import androidx.compose.material.Text

@Composable
fun SetTimer(duration: Int, onDurationChange: (Int) -> Unit) {
    Text(text = "Set Duration:_____________")
    Slider(
        value = duration.toFloat(),
        onValueChange = { onDurationChange(it.toInt()) },
        valueRange = 1f..60f,
        steps = 59
    )
    Text(text = "______________ $duration minutes")
}