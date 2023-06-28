package com.zenzo.zenzo

enum class BreathingPattern(val inhale: Int, val holdIn: Int, val exhale: Int, val holdOut: Int) {
    CALM(4, 7, 8, 0),
    ENERGY(1, 0, 1, 0),
    BALANCE(4, 4, 4, 4),
    SLOW(15, 0, 15, 0)
}
