package com.zenzo.zenzo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.zenzo.zenzo.ui.theme.ZenzoTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ZenzoTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
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
    }
}

