package com.zenzo.zenzo

enum class BreathingPattern(val inhale: Float, val holdIn: Float, val exhale: Float, val holdOut: Float) {
    CALM(4f, 7f, 8f, 0f),
    ENERGY(0.25f, 0f, 0.75f, 0f),
    BALANCE(4f, 4f, 4f, 4f),
    SLOW(15f, 0f, 15f, 0f)
}

