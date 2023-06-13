package com.zenzo.zenzo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    val navController = rememberNavController()
                    NavHost(navController, startDestination = "meditationOptions") {
                        composable("meditationOptions") {
                            MeditationOptionsScreen(navController = navController)
                        }
                        composable("meditationScreen") { backStackEntry ->
                            val duration = backStackEntry.arguments?.getInt("duration") ?: 13 // default value
                            val pattern = backStackEntry.arguments?.getString("pattern") ?: BreathingPattern.BALANCE // default value
                            MeditationScreen(duration = duration, pattern = pattern)

                            // The following line assumes that you have a MeditationScreen composable that takes duration and pattern as parameters.
                            // Replace it with your actual MeditationScreen composable.
                            MeditationScreen(duration = duration, pattern = pattern)
                        }
                    }
                }
            }
        }
    }
}

