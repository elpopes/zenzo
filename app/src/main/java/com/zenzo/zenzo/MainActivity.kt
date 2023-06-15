package com.zenzo.zenzo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController, startDestination = "meditationOptions") {
                composable("meditationOptions") {
                    MeditationOptionsScreen(navController = navController)
                }
                composable("meditationScreen/duration={duration}&pattern={pattern}") { backStackEntry ->
                    val duration = backStackEntry.arguments?.getString("duration")?.toIntOrNull() ?: 13
                    val patternName = backStackEntry.arguments?.getString("pattern") ?: BreathingPattern.BALANCE.name
                    val pattern = BreathingPattern.valueOf(patternName)
                    MeditationScreen(duration = duration, pattern = pattern)
                }
            }
        }
    }
}
