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

@Composable
fun MeditationScreen(navController: NavController, duration: Int, pattern: BreathingPattern) {
    val meditationTimeInSeconds = duration * 60
    val meditationTimePassed = remember { mutableStateOf(0) }
    val animationState = remember { mutableStateOf(AnimationState.EXHALE) }
    val targetValue = remember { mutableStateOf(25f) }
    val animationDuration = remember { mutableStateOf(0) }

    LaunchedEffect(key1 = meditationTimePassed.value) {
        delay(animationDuration.value.toLong())
        meditationTimePassed.value += pattern.hold

        if (meditationTimePassed.value < meditationTimeInSeconds) {
            when (animationState.value) {
                AnimationState.EXHALE -> {
                    targetValue.value = 350f
                    animationDuration.value = pattern.inhale * 1000
                    animationState.value = AnimationState.HOLD_INHALE
                    delay((pattern.inhale * 1000).toLong())
                    meditationTimePassed.value += pattern.inhale
                }
                AnimationState.HOLD_INHALE -> {
                    targetValue.value = 25f
                    animationDuration.value = pattern.exhale * 1000
                    animationState.value = AnimationState.EXHALE
                    delay((pattern.hold * 1000).toLong())
                    meditationTimePassed.value += pattern.hold
                }
                AnimationState.HOLD_EXHALE -> {
                    targetValue.value = 350f
                    animationDuration.value = pattern.inhale * 1000
                    animationState.value = AnimationState.INHALE
                    delay((pattern.hold * 1000).toLong())
                    meditationTimePassed.value += pattern.hold
                }
                AnimationState.INHALE -> {
                    targetValue.value = 25f
                    animationDuration.value = pattern.exhale * 1000
                    animationState.value = AnimationState.HOLD_EXHALE
                    delay((pattern.inhale * 1000).toLong())
                    meditationTimePassed.value += pattern.inhale
                }
            }
        } else {
            navController.popBackStack()
        }
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

enum class AnimationState {
    INHALE, HOLD_INHALE, EXHALE, HOLD_EXHALE
}

