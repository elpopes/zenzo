package com.zenzo.zenzo

import BreathingPattern
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Row
import androidx.compose.ui.Alignment
import androidx.navigation.NavController
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.Box
import androidx.compose.material.ButtonDefaults
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.text.style.TextAlign


@Composable
fun MeditationOptionsScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Blue)
    ) {
        Text(
            text = "Meditation Tracker:",
            color = Color.White,
            style = TextStyle(fontSize = 18.sp),
            modifier = Modifier.padding(16.dp)
        )
        ConsecutiveDaysTracker(modifier = Modifier.padding(start = 16.dp))
        var duration by remember { mutableStateOf(13) }
        Box(modifier = Modifier.padding(24.dp)) {
            SetTimer(duration = duration, onDurationChange = { duration = it })
        }

        var selectedPattern by remember { mutableStateOf(BreathingPattern.BALANCE) }
        BreathingPattern.values().forEach { pattern ->
            Box(modifier = Modifier.padding(16.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = selectedPattern == pattern,
                        onClick = { selectedPattern = pattern },
                        colors = RadioButtonDefaults.colors(selectedColor = Color.White, unselectedColor = Color.White)
                    )
                    Text(
                        text = pattern.name,
                        color = Color.White, // Set text color to white
                        style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    )
                }
            }
        }


        Box(modifier = Modifier.padding(16.dp)) {
            Button(
                onClick = { startMeditation(duration, selectedPattern, navController) },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Black,
                    contentColor = Color.White,
                    disabledBackgroundColor = Color.Gray,
                    disabledContentColor = Color.White,
                ),
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(16.dp),
                content = {
                    Text(
                        "Begin",
                        color = Color.White,
                        style = TextStyle(fontSize = 22.sp), // Increase font size
                        modifier = Modifier.fillMaxWidth(), // Fill the width of the button
                        textAlign = TextAlign.Center // Center the text
                    )
                }
            )
        }
    }
}



fun startMeditation(duration: Int, pattern: BreathingPattern, navController: NavController) {
    val route = "meditationScreen/duration=$duration&pattern=${pattern.name}"
    navController.navigate(route)
}



