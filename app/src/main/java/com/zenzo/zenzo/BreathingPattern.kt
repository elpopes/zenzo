package com.zenzo.zenzo

enum class BreathingPattern(val inhale: Int, val holdIn: Int, val exhale: Int, val holdOut: Int) {
    CALM(4, 7, 7, 1),
    ENERGY(6, 2, 1, 1),
    BALANCE(4, 4, 4, 4),
    SLOW(14, 1, 14, 1)
}
