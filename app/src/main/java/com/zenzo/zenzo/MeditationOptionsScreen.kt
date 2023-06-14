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
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.core.os.bundleOf



@Composable
fun MeditationOptionsScreen(navController: NavController) {
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
        Button(onClick = { startMeditation(duration, selectedPattern, navController) }) {
            Text("Start")
        }
    }
}

fun startMeditation(duration: Int, pattern: BreathingPattern, navController: NavController) {
    val route = "meditationScreen/duration=$duration&pattern=${pattern.name}"
    navController.navigate(route)
}



