package com.zenzo.zenzo

import com.zenzo.zenzo.ui.theme.*
import androidx.compose.ui.graphics.Color

enum class BreathingPattern(val inhale: Float, val holdIn: Float, val exhale: Float, val holdOut: Float, val color: Color, val description: String) {
    CALM(4f, 7f, 8f, 0f, LightBlue, "Calm breathing, also known as the 4-7-8 breathing technique, involves inhaling for 4 seconds, holding the breath for 7 seconds, and exhaling for 8 seconds. This technique is great for calming the mind and reducing stress."),
    ENERGY(0.1f, 0f, 0.4f, 0f, EnergyRed, "Energy breathing involves quick, short inhales and longer exhales. This technique can help increase your energy levels and keep you alert."),
    BALANCE(4f, 4f, 4f, 4f, BalanceOrange, "Balance breathing, also known as box breathing, involves inhaling, holding the breath, exhaling, and holding the breath again, all for an equal amount of time. This technique can help create a sense of balance and calm."),
    SLOW(15f, 0f, 15f, 0f, SlowYellow, "Slow breathing involves long, slow inhales and exhales. This technique can help slow your heart rate and relax your body, making it a great choice for winding down before sleep.")
}



