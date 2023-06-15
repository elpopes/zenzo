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
import kotlinx.coroutines.delay
import androidx.navigation.NavController

@Composable
fun MeditationScreen(navController: NavController, duration: Int, pattern: BreathingPattern) {
    val meditationTimeInSeconds = duration * 60
    val meditationTimePassed = remember { mutableStateOf(0) }
    val animationState = remember { mutableStateOf(AnimationState.INHALE) }

    LaunchedEffect(key1 = meditationTimePassed.value) {
        if (meditationTimePassed.value < meditationTimeInSeconds) {
            when (animationState.value) {
                AnimationState.INHALE -> {
                    delay((pattern.inhale * 1000).toLong())
                    animationState.value = AnimationState.HOLD
                    meditationTimePassed.value += pattern.inhale
                }
                AnimationState.HOLD -> {
                    delay((pattern.hold * 1000).toLong())
                    animationState.value = AnimationState.EXHALE
                    meditationTimePassed.value += pattern.hold
                }
                AnimationState.EXHALE -> {
                    delay((pattern.exhale * 1000).toLong())
                    animationState.value = AnimationState.INHALE
                    meditationTimePassed.value += pattern.exhale
                }
            }
        } else {
            navController.popBackStack()
        }
    }

    val circleSize = animateFloatAsState(
        targetValue = when (animationState.value) {
            AnimationState.INHALE, AnimationState.HOLD -> 300f
            AnimationState.EXHALE -> 100f
        },
        animationSpec = when (animationState.value) {
            AnimationState.INHALE -> tween(durationMillis = pattern.inhale * 1000)
            AnimationState.HOLD -> tween(durationMillis = pattern.hold * 1000)
            AnimationState.EXHALE -> tween(durationMillis = pattern.exhale * 1000)
        }
    )

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Canvas(modifier = Modifier.size(circleSize.value.dp)) {
            drawCircle(color = Color.Blue)
        }
    }
}

enum class AnimationState {
    INHALE, HOLD, EXHALE
}

