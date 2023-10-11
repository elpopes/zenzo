package com.zenzo.zenzo

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import android.os.Build
import android.view.View
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)  // Important to call superclass method

        // For newer versions
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.let { controller ->
                controller.hide(android.view.WindowInsets.Type.statusBars())
                controller.systemBarsBehavior = android.view.WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
        } else {
            // For older versions
            @Suppress("DEPRECATION")
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
            supportActionBar?.hide()
        }

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
                    MeditationScreen(navController = navController, duration = duration, pattern = pattern)
                }
                composable("completion") {
                    CompletionScreen(navController = navController)
                }
            }
        }
    }
}


