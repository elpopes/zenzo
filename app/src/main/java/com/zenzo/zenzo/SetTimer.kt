package com.zenzo.zenzo

import androidx.compose.material.Slider
import androidx.compose.runtime.Composable
import androidx.compose.material.Text
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.graphics.Color
import androidx.compose.material.SliderDefaults
import androidx.compose.ui.Modifier

@Composable
fun SetTimer(duration: Int, onDurationChange: (Int) -> Unit) {
    Text(
        text = "Set Duration:",
        style = TextStyle(fontSize = 20.sp, color = Color.White) // Adjust the size and color here
    )
    Slider(
        value = duration.toFloat(),
        onValueChange = { onDurationChange(it.toInt()) },
        valueRange = 1f..60f,
        steps = 59,
        colors = SliderDefaults.colors(
            thumbColor = Color.Black,
            activeTrackColor = Color.Black,
            inactiveTrackColor = Color.Gray
        ),
        modifier = Modifier.padding(top = 40.dp) // Add space above the slider
    )
    Text(
        text = "$duration minutes",
        style = TextStyle(fontSize = 24.sp, color = Color.White, fontWeight = FontWeight.Bold), // Adjust the size and color here
        modifier = Modifier.padding(top = 16.dp) // Add space above the text
    )
}