package com.zenzo.zenzo

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState) // Don't forget to call super.onCreate

        // Hide the status bar
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
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
