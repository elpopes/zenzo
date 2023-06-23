package com.zenzo.zenzo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun CompletionScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Blue)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Session Complete!",
            style = TextStyle(fontSize = 24.sp, color = Color.White)
        )
        ConsecutiveDaysTracker()

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { navController.navigate("meditationOptions") },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black, contentColor = Color.White)
        ) {
            Text(text = "New Session")
        }
    }
}

