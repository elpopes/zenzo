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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalDensity
import kotlin.math.ceil
import kotlinx.coroutines.launch

@Composable
fun MeditationScreen(navController: NavController, duration: Int, pattern: BreathingPattern) {
    val meditationTimeInSeconds = duration * 60
    val targetValue = remember { mutableStateOf(25f) }
    val animationDuration = remember { mutableStateOf(0) }

    val context = LocalContext.current
    val activity = context as Activity
    val sharedPreferences = context.getSharedPreferences("com.zenzo.zenzo", Context.MODE_PRIVATE)

    val holdInActive = remember { mutableStateOf(false) }
    val holdOutActive = remember { mutableStateOf(false) }

    val scope = rememberCoroutineScope()

    DisposableEffect(Unit) {
        activity.window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        onDispose {
            activity.window.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        }
    }

    LaunchedEffect(key1 = duration) {
        val cycleTime = pattern.inhale + pattern.holdIn + pattern.exhale + pattern.holdOut
        val cyclesNeeded = ceil(meditationTimeInSeconds.toDouble() / cycleTime).toInt()

        for (i in 0 until cyclesNeeded) {
            targetValue.value = 350f
            animationDuration.value = (pattern.inhale * 1000f).toInt()
            delay((pattern.inhale * 1000f).toLong())

            if (pattern.holdIn > 0) {
                holdInActive.value = true
                scope.launch {
                    delay((pattern.holdIn * 1000 - 500).toLong())
                    holdInActive.value = false
                }
                delay((pattern.holdIn * 1000f).toLong())
            }

            targetValue.value = 25f
            animationDuration.value = (pattern.exhale * 1000f).toInt()
            delay((pattern.exhale * 1000f).toLong())

            if (pattern.holdOut > 0) {
                holdOutActive.value = true
                scope.launch {
                    delay((pattern.holdOut * 1000 - 500).toLong())
                    holdOutActive.value = false
                }
                delay((pattern.holdOut * 1000f).toLong())
            }
        }

        val currentDate = LocalDate.now()

        val lastMeditationDate = LocalDate.parse(
            sharedPreferences.getString("lastMeditationDate", "2000-01-01")
        )

        val nextDayAfterLastSession = lastMeditationDate.plusDays(1)

        val editor = sharedPreferences.edit()
        if (currentDate == lastMeditationDate) {
        } else if (currentDate == nextDayAfterLastSession) {
            val consecutiveDays = sharedPreferences.getInt("consecutiveDays", 0)
            editor.putInt("consecutiveDays", consecutiveDays + 1)
            editor.putString("lastMeditationDate", currentDate.toString())
        } else {
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
        val circleSizeInPixels = with(LocalDensity.current) { circleSize.value.dp.toPx() }

        val holdInGradientSize = animateFloatAsState(
            targetValue = if (holdInActive.value) circleSize.value * 2f else circleSize.value * 0.1f,
            animationSpec = tween(durationMillis = 500)
        )

        val holdOutGradientSize = animateFloatAsState(
            targetValue = if (holdOutActive.value) circleSize.value * 3f else circleSize.value * 0.1f,
            animationSpec = tween(durationMillis = 500)
        )

        val holdInGradientSizePixels = with(LocalDensity.current) { holdInGradientSize.value.dp.toPx() }
        val holdOutGradientSizePixels = with(LocalDensity.current) { holdOutGradientSize.value.dp.toPx() }

        Canvas(modifier = Modifier.size(circleSize.value.dp)) {
            val center = size / 2f
            val centerOffset = Offset(center.width, center.height)

            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(Color.White, Color.Transparent),
                    center = centerOffset,
                    radius = holdInGradientSizePixels / 2
                ),
                center = centerOffset,
                radius = holdInGradientSizePixels / 2
            )

            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(Color.White, Color.Transparent),
                    center = centerOffset,
                    radius = holdOutGradientSizePixels / 2
                ),
                center = centerOffset,
                radius = holdOutGradientSizePixels / 2
            )

            drawCircle(center = centerOffset, color = pattern.color, radius = circleSizeInPixels / 2)
        }
    }
}
