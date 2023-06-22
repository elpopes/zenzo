package com.zenzo.zenzo

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.RadioButton
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


@Composable
fun MeditationOptionsScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Blue)
    ) {
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
                        onClick = { selectedPattern = pattern }
                    )
                    Text(text = pattern.name, color = Color.White) // Set text color to white
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
                )
            ) {
                Text("Start", color = Color.White)
            }

        }
    }
}



fun startMeditation(duration: Int, pattern: BreathingPattern, navController: NavController) {
    val route = "meditationScreen/duration=$duration&pattern=${pattern.name}"
    navController.navigate(route)
}



