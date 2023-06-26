package com.zenzo.zenzo

import androidx.compose.runtime.Composable
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import kotlinx.coroutines.delay
import androidx.navigation.NavController
import androidx.compose.ui.platform.LocalContext
import android.view.WindowManager
import android.app.Activity
import androidx.compose.runtime.DisposableEffect
import android.content.Context
import org.threeten.bp.LocalDate
import android.util.Log

@Composable
fun MeditationScreen(navController: NavController, duration: Int, pattern: BreathingPattern) {
    val meditationTimeInSeconds = duration * 60
    val targetValue = remember { mutableStateOf(25f) }
    val animationDuration = remember { mutableStateOf(0) }

    val context = LocalContext.current
    val activity = context as Activity
    val sharedPreferences = context.getSharedPreferences("com.zenzo.zenzo", Context.MODE_PRIVATE)

    DisposableEffect(Unit) {
        activity.window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        onDispose {
            activity.window.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        }
    }

    LaunchedEffect(key1 = duration) {
        val cycleTime = pattern.inhale + pattern.hold + pattern.exhale
        val cyclesNeeded = (meditationTimeInSeconds / cycleTime)

        for (i in 0 until cyclesNeeded) {
            targetValue.value = 350f
            animationDuration.value = pattern.inhale * 1000
            delay((pattern.inhale * 1000).toLong())

            animationDuration.value = pattern.hold * 1000
            delay((pattern.hold * 1000).toLong())

            targetValue.value = 25f
            animationDuration.value = pattern.exhale * 1000
            delay((pattern.exhale * 1000).toLong())
        }

        val currentDate = LocalDate.now()
        Log.d("MeditationScreen", "Current date: $currentDate")

        val lastMeditationDate = LocalDate.parse(
            sharedPreferences.getString("lastMeditationDate", "2000-01-01")
        )
        Log.d("MeditationScreen", "Last meditation date: $lastMeditationDate")

        val nextDayAfterLastSession = lastMeditationDate.plusDays(1)

        val editor = sharedPreferences.edit()
        if (currentDate == lastMeditationDate) {
            Log.d("MeditationScreen", "Current date is the same as the last session, doing nothing")
        } else if (currentDate == nextDayAfterLastSession) {
            val consecutiveDays = sharedPreferences.getInt("consecutiveDays", 0)
            Log.d("MeditationScreen", "Current date is the day after the last session, incrementing consecutiveDays from $consecutiveDays")
            editor.putInt("consecutiveDays", consecutiveDays + 1)
            editor.putString("lastMeditationDate", currentDate.toString())
        } else {
            Log.d("MeditationScreen", "Current date is not the day after the last session, resetting consecutiveDays to 1")
            editor.putInt("consecutiveDays", 1)
            editor.putString("lastMeditationDate", currentDate.toString())
        }
        editor.apply()

        navController.navigate("completion")
    }

    val circleSize = animateFloatAsState(
        targetValue = targetValue.value,
        animationSpec = tween(durationMillis = animationDuration.value)
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.size(circleSize.value.dp)) {
            drawCircle(color = Color.Blue)
        }
    }
}


