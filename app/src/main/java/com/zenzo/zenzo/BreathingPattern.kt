import com.zenzo.zenzo.ui.theme.*
import androidx.compose.ui.graphics.Color

enum class BreathingPattern(val inhale: Float, val holdIn: Float, val exhale: Float, val holdOut: Float, val color: Color) {
    CALM(4f, 7f, 8f, 0f, LightBlue),
    ENERGY(0.1f, 0f, 0.4f, 0f, EnergyRed),
    BALANCE(4f, 4f, 4f, 4f, BalanceGreen),
    SLOW(15f, 0f, 15f, 0f, SlowYellow)
}


